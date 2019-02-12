* Udemy: JUnit and Mockito Crash Course, Bharath Thrippireddy
  - URL: https://www.udemy.com/junitandmockitocrashcourse/learn/v4/content
  - Software: Java 1.8 or higher, Eclipse Mars or higher, JUnit4 (*not* Junit5/Jupiter)

* Lecture 16, "Run Tests using Maven"
  PROBLEM: 
    Although JUnit tests compiled/ran OK from Eclipse GUI runner, "mvn install" =>
    "Tests run: 0, Failures: 0, Errors: 0, Skipped: 0" (none of the tests appeared to run from Maven)
  CAUSE:
    Maven "install" (or maven "test") was using the JUnit4 version of Surefire, instead of the Junit5 version. 
  RESOLUTION:
https://maven.apache.org/surefire/maven-surefire-plugin/examples/junit-platform.html
  <= Modify pom.xml: 
     - Substitute maven-surefile-plugin version 2.20.1 => 3.0.0-M3
     - configurationParameters: junit.jupiter.extensions.autodetection.enabled = true, ...
  RESULTS:
    Maven "install" => Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
