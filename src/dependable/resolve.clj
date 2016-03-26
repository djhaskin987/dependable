(ns dependable.resolve)

; Helper functions

(defn spec-call [f v]
  (f v))

(def safe-spec-call
  (fnil spec-call (fn [v] true)))


#_(defmacro debug [form]
  `(let [x# ~form]
     (println (str "Debug: "
                   (quote ~form)
                   " is " x#))
     x#))

; Tested functions
(defn remove-dep
  [to from node]
  (let [children (:children (to node))
        children-names (keys children)
        from' (reduce
                (fn [c [child _parents]]
                  (if (= (count _parents) 1)
                    (dissoc c child)
                    (assoc c
                           child
                           (disj _parents node))))
                from
                (map #(find from %)
                     children-names))
        to' (dissoc to
                    node)]
    (reduce (fn [c [nm version-spec]]
              (let [[to'' from''] c]
                (if (contains? from' nm)
                  c
                  (remove-dep to'' from'' nm))))
            [to' from']
            children)))






(defn realize
  "Takes a request object and a query object and
  returns an actual package object pull from the
  query object, which satisfies the request"
  [query request]
  (first
    (filter
      #(safe-spec-call (:version-spec request) (:version %))
      (query (:name request)))))





;(->package-graph)
;{:records {"name" <package>}
; :dependee-parents {"dependee" #{"depender" "depender}}}

(defrecord package-graph [records dependee-parents])
(defrecord package [name version location dependencies])
(defn resolve-dependencies [package]
  #_(reduce
    reconcile
    (->package-graph {(:name package) package} {})
    (map #(resolve-dependencies
            (realize %1))
         (:dependencies package))))


; realize is already written
; needs work to be able to handle conflicts, already-installeds, etc.
; Therefore: ONLY SOLVE THE REQUIRES / DIAMOND TESTS FIRST
(defn reconcile [a b]
  a
    )
(defn resolve-dependencies
  [specs query & {:keys [already-found conflicts] :or {already-found {} conflicts {}}}]
  nil)

#_(defn resolve-dependencies
  [specs
   query &
   {:keys [already-found
           conflicts]
    :or {already-found {}
         conflicts {}}}]
  (loop [remaining (seq specs)
         installed already-found
         conflict conflicts
         result #{}]
    (if (empty? remaining)
      [:successful result]
      (let [pkg (first remaining)
            r (rest remaining)
            pname (:name pkg)
            pspec (:version-spec pkg)]
        (cond (contains? installed pname)
              (if (not (safe-spec-call pspec (installed pname)))
                [:unsatisfiable [pname]]
                (recur r installed conflict result))
              (and (contains? conflict pname)
                   (nil? (conflict pname)))
              [:unsatisfiable [pname]]
              :else
              (let [chosen (choose-candidate pname pspec query)
                    chosen-conflicts (:conflicts chosen)]
                (if (or (nil? chosen)
                        (and (contains? conflict pname)
                             (safe-spec-call
                               (conflict pname)
                               (:version chosen))))
                  [:unsatisfiable [pname]]
                  (recur r (assoc
                             installed
                             (:name chosen)
                             (:version chosen))
                         (into
                           conflict
                           chosen-conflicts)
                         (conj result chosen)))))))))

#_(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))