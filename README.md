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

E.g, to generate output in `/tmp/profile.out`, compile with
```
mvn package -Dprofiler.renderer=file-writer -Dprofiler.output=/tmp/profile.out
```

Some examples of generated output:
```
org.eclipse.tesla:tesla-profiler:0.3 4s 606ms
  clean 109ms
    org.apache.maven.plugins:maven-clean-plugin:2.4.1 (default-clean) 109ms
  process-resources 335ms
    org.apache.maven.plugins:maven-resources-plugin:2.5 (default-resources) 333ms
  compile 1s 367ms
    org.apache.maven.plugins:maven-compiler-plugin:2.3.2 (default-compile) 1s 367ms
  process-test-resources 9ms
    org.apache.maven.plugins:maven-resources-plugin:2.5 (default-testResources) 8ms
  test-compile 734ms
    org.apache.maven.plugins:maven-compiler-plugin:2.3.2 (default-testCompile) 734ms
  test 813ms
    org.apache.maven.plugins:maven-surefire-plugin:2.9 (default-test) 812ms
```

In XML:
```
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<profile>
  <session>
    <time>4859</time>
  </session>
  <projects>
    <project>
      <name>tesla-profiler</name>
      <groupId>org.eclipse.tesla</groupId>
      <version>0.3</version>
      <time>4858</time>
      <id>org.eclipse.tesla:tesla-profiler:0.3</id>
      <phases>
        <phase>
          <name>clean</name>
          <time>135</time>
          <mojos>
            <mojo>
              <name>maven-clean-plugin</name>
              <groupId>org.apache.maven.plugins</groupId>
              <version>2.4.1</version>
              <time>134</time>
              <id>org.apache.maven.plugins:maven-clean-plugin:2.4.1 (default-clean)</id>
              <executionId>default-clean</executionId>
            </mojo>
          </mojos>
        </phase>
        <phase>
          <name>process-resources</name>
          <time>329</time>
          <mojos>
            <mojo>
              <name>maven-resources-plugin</name>
              <groupId>org.apache.maven.plugins</groupId>
              <version>2.5</version>
              <time>325</time>
              <id>org.apache.maven.plugins:maven-resources-plugin:2.5 (default-resources)</id>
              <executionId>default-resources</executionId>
            </mojo>
          </mojos>
        </phase>
        <phase>
          <name>compile</name>
          <time>1532</time>
          <mojos>
            <mojo>
              <name>maven-compiler-plugin</name>
              <groupId>org.apache.maven.plugins</groupId>
              <version>2.3.2</version>
              <time>1532</time>
              <id>org.apache.maven.plugins:maven-compiler-plugin:2.3.2 (default-compile)</id>
              <executionId>default-compile</executionId>
            </mojo>
          </mojos>
        </phase>
        <phase>
          <name>process-test-resources</name>
          <time>6</time>
          <mojos>
            <mojo>
              <name>maven-resources-plugin</name>
              <groupId>org.apache.maven.plugins</groupId>
              <version>2.5</version>
              <time>6</time>
              <id>org.apache.maven.plugins:maven-resources-plugin:2.5 (default-testResources)</id>
              <executionId>default-testResources</executionId>
            </mojo>
          </mojos>
        </phase>
        <phase>
          <name>test-compile</name>
          <time>834</time>
          <mojos>
            <mojo>
              <name>maven-compiler-plugin</name>
              <groupId>org.apache.maven.plugins</groupId>
              <version>2.3.2</version>
              <time>834</time>
              <id>org.apache.maven.plugins:maven-compiler-plugin:2.3.2 (default-testCompile)</id>
              <executionId>default-testCompile</executionId>
            </mojo>
          </mojos>
        </phase>
        <phase>
          <name>test</name>
          <time>822</time>
          <mojos>
            <mojo>
              <name>maven-surefire-plugin</name>
              <groupId>org.apache.maven.plugins</groupId>
              <version>2.9</version>
              <time>822</time>
              <id>org.apache.maven.plugins:maven-surefire-plugin:2.9 (default-test)</id>
              <executionId>default-test</executionId>
            </mojo>
          </mojos>
        </phase>
      </phases>
      <goals/>
    </project>
  </projects>
</profile>

```
