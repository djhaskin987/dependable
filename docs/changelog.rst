.. _Degasolv Changelog:

Changelog
=========

All notable changes to this project will be documented here.

The format is based on `Keep a Changelog`_
and this project adheres to `Semantic Versioning`_.

.. _Semantic Versioning: http://semver.org/spec/v2.0.0.html
.. _Keep a Changelog: http://keepachangelog.com/en/1.0.0/


`Unreleased`_
-------------

Added
+++++
* Added ``packrat-install-graph``, a packrat-compliant install graph,
  to the JSON output

* Separated CLI uberjar from library jar, both dependencies and source.
  Now degasolv can be included as a dependency and its resolver used elsewhere.

* Added capability to build using graalvm, allowing for statically compiled
  builds

Changed
+++++++

Fixed
+++++

`2.2.0`_
--------

Added
+++++

* Configuration file tower: Degasolv looks for config files in pre-defined
  locations on the file system if they exist, see
  :ref:`Default Configuration Files <default-configuration-files>`.

* Environment variable support: environment variables are consulted first,
  but are merged into the option map AFTER config file material and BEFORE
  any given command line options, see
  :ref:`Environment Variables <environment-variables>` and
  :ref:`How Options are Gathered <gathering-options>`

* Added support for HTTP/HTTPS authentication using basic, oauth2 token
  and header methods, see :ref:`A Note on Specifying Files <specifying-files>`.

Changed
+++++++

* Removed deprecated, unused function ``degasolv.util.assoc-conj``

Fixed
+++++

* Option packs are expanded at the level upon which they are defined, then the
  options are merged together. This seemed like a bug that needed fixing.
  See :ref:`Option Packs <option-pack>` for more information.

`2.1.0`_
-------------

Added
+++++

* Added "version suggestion", a performance enhancement allowing for minimum
  version selection

* Added the ``:index-sort-order`` option to ``generate-repo-index``,
  allowing users to specify ``ascending`` or ``descending``. Previously
  only ``descending`` was supported. With this new option,
  users will be able to use Degasolv in a minimum version selection
  configuration.

* If the reason for failure in the ``resolve-dependencies`` function
  is ``:present-package-conflict``, add a key ``:package-present-by`` with
  value as either ``:found`` meaning the package was in conflict with a package
  found during resolution, or with value as ``:given`` meaning the package
  was in conflict with a package given via the parameter ``present-packages``.

Changed
+++++++

* Removed the deprecated functions ``->requirement``, ``->package``, and
  ``->version-predicate`` from usage in the code base.

* Removed ``dbg2`` macro in favor of keeping ``dbg``

Fixed
+++++

* Standardized capitalization of the word "Degasolv" to be title case for
  consistency in the documentation unless it is in a code snippet.

* Fix #16

* Fix spec for package id's. This should have the effect of enforcing that
  names should not have ``>``, ``<``, ``!``, ``=``, ``,``, ``;``, or ``|``
  characters in them. This simply has the effect of changing the error message,
  as this was never allowed but handled poorly.

`2.0.0`_
--------

Added
+++++

* Documentation saying what return codes are given and what they mean.

* For #15, added ability to specify output format for ``display-config``.

* Added :ref:`3rd Party Licenses <3rd-party-licenses>` document

Changed
+++++++

* In the docs, ``java -jar degasolv-<version>-standalone.jar`` changed to
  ``degasolv`` with added note for clarity

* For #13, return code for ``resolve-locations`` changed to 3 when dependency
  resolutions occur to distinguish them from normal "you got the argument
  string wrong" errors

* Default ``--{enable|disable}-error-format`` set to ``enabled`` for
  :ref:`resolve-locations <enable-error-format-resolve>` and
  :ref:`query-repo <enable-error-format-query>`.

* Default for ``--list-strat`` option for :ref:`resolve-locations
  <list-strategy>` set to ``lazy``, a much saner default.

* Option pack ``v1`` :ref:`added <option-pack>` to help administrators
  keep compatibility with version 1 of Degasolv if required.

* Default for the ``--version-comparison`` option when ``--package-system``
  is ``degasolv`` set to ``semver`` for
  :ref:`generate-repo-index <generate-repo-index>` (option :ref:`here
  <version-comparison-generate>`), :ref:`resolve-locations
  <resolve-locations>` (option :ref:`here <version-comparison-resolve>`)
  and :ref:`query-repo <query-repo>` (option :ref:`here
  <version-comparison-query>`).

* Removed less-than-useful warning about absent config files.

Fixed
+++++

* Fixed #14, "Degasolv pulls in X"

* Fixed bug where ``display-config`` didn't allow the user
  to specify valid options for other things, now it does

* Fixed bug where index.dsrepo didn't generate anything except
  an empty map inside the file. This was *completely* broken.

* Fixed #6, "If one config file fails to load, the rest do as well"

* Fixed #9, "Heading for 'Specifying Subproc Executable' is wrong in docs"

* Fixed #10, "How do you specify requirements of a package (deps) in the output
  of a subproc to Degasolv?"

`1.12.1`_
---------

*1.12.1 was strictly a docs release. No code changes were made and no runnable
artifacts were created.*

Added
+++++

* Added authors file to docs

Fixed
+++++

* Fixed changelog so that the 1.12.0 release was present in the document
* Fixed package system subheaders in command reference

`1.12.0`_
---------

Added
+++++

* Added the ``--{enable|disable}-error-format`` options to
  :ref:`resolve-locations <enable-error-format-resolve>` and
  :ref:`query-repo <enable-error-format-query>`.

* Added the ``--package-system subproc`` option, together with its
  :ref:`subproc-exe` and :ref:`subproc-output-format` options.

* Added the ``--json-config`` :ref:`global option <json-config>` allowing users
  to use JSON config files

* Added the ``--list-strat`` option to :ref:`resolve-locations
  <list-strategy>`, allowing users to have their dependencies listed in a sane
  order.

Changed
+++++++

Fixed
+++++

* JSON/EDN output for ``query-repo`` erroneously listed the subcommand as
  ``resolve-locations``. Fixed.

`1.11.0`_
---------

Added
+++++
* Added the ``--meta`` :ref:`option <meta-data>` to
  :ref:`generate-card <generate-card-options>`
* Added metadata a la ``--meta`` to the ``apt`` :ref:`package system
  <package-system>` (experimental)
* Added the ``edn`` :ref:`output format option <output-format>` for
  the ``resolve-locations`` :ref:`subcommand <resolve-locations>`
* Added the ``--output-format`` :ref:`option
  <output-format-query-repo>` to the :ref:`query-repo <query-repo>`
  command

Changed
+++++++

* Overhauled the documentation option look and feel; ensured that
  options themselves had a linking target (header)

`1.10.0`_
-------------

Added
+++++
* Added the ``--output-format`` :ref:`option <output-format>` to
  :ref:`resolve-locations <resolve-locations>`

`1.9.0`_
--------

Added
+++++
* Added the :ref:`pessimistic greater-than <pess-greater>` comparison operator ``><``.

Fixed
+++++
* Removed validation from the config file option, allowing it to be a URL or
  anything else.

* If no arguments are given, the help screen is now printed instead of a wierd
  error.

`1.8.0`_
--------

Added
+++++
* Distribution is now done via RPM and Debian package as well as JAR fil
* Added the ``--version-comparison`` option to
  :ref:`generate-repo-index <generate-repo-index>` (option :ref:`here
  <version-comparison-generate>`), :ref:`resolve-locations
  <resolve-locations>` (option :ref:`here <version-comparison-resolve>`)
  and :ref:`query-repo <query-repo>` (option :ref:`here
  <version-comparison-query>`), allowing the user to specify which
  version comparison algorithm is used.
* Added the ``--search-strat`` option to :ref:`resolve-locations
  <resolve-locations>`, allowing users to select breadth first
  search or depth first search during resolution
* Added the :ref:`matches <matches>` operator (``<>REGEX``) which
  matches a version against a regex
* Added the :ref:`in-range <in-range>` operator (``=>V``) which
  matches a version against a certain range of indexes
* Added the ability to specify ``--present-package`` multiple times using the
  same package name, but different versions. This is useful for when the
  ``:conflict-strat`` is set to ``inclusive``.
* Added tests testing to make sure that unsuccessful runs generate the proper
  error messages.

Changed
+++++++
* Reorganized the unit tests.
* Alphabetized the options for ``generate-card``.
* Alphabetized the options for ``generate-repo-index``.

Fixed
+++++
* Fixed bug wherein if the conflict strategy is set to ``:inclusive``
  and a package satisfying a requirement is already found or present,
  it is used instead of finding a new one.
* Fixed CLI of :ref:`display-config <display-config-cli>` so that
  it actually works as advertised, LOLZ
* Fixed the CLI output of ``--help`` so that default values
  of options are shown again :)
* Refreshed the CLI output of ``--help`` for all the subcommands
  as posted in the docs

`1.7.0`_
--------

Added
+++++
* Added ``--option-pack``, the ability to :ref:`specify multiple
  options at once <option-pack>`

Fixed
+++++
* Fixed how default options work, they no longer override stuff
  found in the config file (ouch)
* Fixed output of printed warning when configuration file is not used

`1.6.0`_
--------

Added
+++++
* Formatted docs better on the front page for PDF purposes
* Add ability to use any (long) option on the command line in
  :ref:`display-config <display-config-cli>`

Improved
++++++++
* Memoized core Degasolv package system repository function (should
  speed the resolver up a bit)
* Changed apt reop function from filtering a list to lookup in a map,
  increasing its speed

`1.5.1`_
--------

Added
+++++

* In just ~15 seconds, it slurps in a rather large apt repository
  Packages.gz file.  In another ~45 seconds, it resolves the
  ubuntu-desktop package, spitting out a grand total of 797 packages
  with their locations.

Fixed
+++++

* While using the apt data and package system to profile Degasolv, I
  found some rather nasty bugs. This release fixes them. This tool is
  now ready for prime time.

`1.5.0`_
--------

Added
+++++
* Added the ``--disable-alternatives`` :ref:`option
  <disable-alternatives>` and the ``--enable-alternatives``
  :ref:`option <enable-alternatives>` for debugging purposes.

`1.4.0`_
--------

Added
+++++

* Added the ``--present-package`` :ref:`option <present-package>` and
  the ``--package-system`` :ref:`option <package-system>` to the
  :ref:`resolve-locations <resolve-locations>` subcommand.  This was so
  that Degasolv could be profiled using apt package repos
  (real-world data) and thereby have its performance optimized.

`1.3.0`_
--------

Added
+++++

* Add standard input as a file type. All options which take a file name may now
  have ``-`` given as the filename, to specify that standard in should be used.

`1.2.0`_
--------

Added
+++++

* Added the ability to specify multiple configuration files,
  thus allowing for site-wide configuration.

`1.1.0`_
--------

Added
+++++

* Added the ``--conflict-strat`` :ref:`option <conflict-strat>` to the
  :ref:`resolve-locations <resolve-locations>` subcommand.

* Added docs and tests.

1.0.2
-----

* This isn't the first release, but for the purposes of these docs, it is :D

.. _Unreleased: https://github.com/djhaskin987/degasolv/compare/2.2.0...HEAD
.. _2.2.0: https://github.com/djhaskin987/degasolv/compare/2.1.0...2.2.0
.. _2.1.0: https://github.com/djhaskin987/degasolv/compare/2.0.0...2.1.0
.. _2.0.0: https://github.com/djhaskin987/degasolv/compare/1.12.1...2.0.0
.. _1.12.1: https://github.com/djhaskin987/degasolv/compare/1.12.0...1.12.1
.. _1.12.0: https://github.com/djhaskin987/degasolv/compare/1.11.0...1.12.0
.. _1.11.0: https://github.com/djhaskin987/degasolv/compare/1.10.0...1.11.0
.. _1.10.0: https://github.com/djhaskin987/degasolv/compare/1.9.0...1.10.0
.. _1.9.0: https://github.com/djhaskin987/degasolv/compare/1.8.0...1.9.0
.. _1.8.0: https://github.com/djhaskin987/degasolv/compare/1.7.0...1.8.0
.. _1.7.0: https://github.com/djhaskin987/degasolv/compare/1.6.0...1.7.0
.. _1.6.0: https://github.com/djhaskin987/degasolv/compare/1.5.1...1.6.0
.. _1.5.1: https://github.com/djhaskin987/degasolv/compare/1.5.0...1.5.1
.. _1.5.0: https://github.com/djhaskin987/degasolv/compare/1.4.0...1.5.0
.. _1.4.0: https://github.com/djhaskin987/degasolv/compare/1.3.0...1.4.0
.. _1.3.0: https://github.com/djhaskin987/degasolv/compare/1.2.0...1.3.0
.. _1.2.0: https://github.com/djhaskin987/degasolv/compare/1.1.0...1.2.0
.. _1.1.0: https://github.com/djhaskin987/degasolv/compare/1.0.2...1.1.0
