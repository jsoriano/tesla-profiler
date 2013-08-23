tesla-profiler
==============

maven extension for profiling

Quick start
-----------

1. Run `mvn package`.
1. Copy generated jar file in `target` directory to `$MAVEN_HOME/lib/ext`.
1. Run maven in your project and check file `profile.xml` in the directory where you run it.

To stop using it just remove the file in `$MAVEN_HOME/lib/ext`.


Configuration
-------------

This extension can be configured only during compilation time. For that, you can choose with the `profile.renderer` property what renderer to use, there are currently two implementations:

* `xml-writer`: Writes timings to `profile.xml` file in current directory, or the file specified by `profiler.xmloutput` property.
* `file-writer`: Writes timings in a more human readable format to standard output, or to the file specified by `profiler.output` property.

In any case `System.out` and `System.err` can be used as files to write to standard output or standard error.
