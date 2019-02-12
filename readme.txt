* Udemy: JUnit and Mockito Crash Course, Bharath Thrippireddy
  - URL: https://www.udemy.com/junitandmockitocrashcourse/learn/v4/content
  - Software: Java 1.8 or higher, Eclipse Mars or higher, JUnit4 (*not* Junit5/Jupiter)

===================================================================================================

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

===================================================================================================

* Transcribed Junit "Hello world" test => C#/.Net:
https://docs.microsoft.com/en-us/visualstudio/test/getting-started-with-unit-testing
    1. MSVS > File > New Project > C#/Console app, Name= HelloDotnet
    2. Add > New > Item > 
         Interface > IGreeting
         Class > GreetingImpl
    3. Fill in basic implementations: 
         {IGreeting.cs, GreetingImpl.cs, Program.cs}
         <= Verified OK
    4. MSVS > File > Add > New Project > Test > Unit Test Project, Name= HelloTest
    5. References > Add Reference > Pprojects > HelloDotnet
    6. Code Test(s):
       - Rename => GreetingImplTest
       - Implement initial test method:
    6. HelloTest > Build
       Tests > Run > run All tests
       <= Greeb

  - GreetingImplTest.cs:
    -------------------
using Microsoft.VisualStudio.TestTools.UnitTesting;
using HelloDotnet;

namespace HelloTest
{
    [TestClass]
    public class GreetingImplTest
    {
        [TestMethod]
        public void greetShouldReturnAValidValue()
        {
            GreetingImpl greeting = new GreetingImpl();
            Assert.IsNotNull(greeting);

            string result = greeting.greet("ABC");
            Assert.IsNotNull(result);
            Assert.Equals("Hello ABC", result);
        }
    }
}

  - NOTES:
    - MSVS 2015/Pro does *NOT* seem to have any "Create Unit Tests dialog" anywhere
    - It uses "Microsoft.VisualStudio.TestTools.UnitTesting"

  - ADDITIONAL LINKS:
https://stackoverflow.com/questions/4101617/what-are-the-differences-between-microsoft-testtools-unittesting-and-nunit
https://docs.microsoft.com/en-us/visualstudio/test/walkthrough-creating-and-running-unit-tests-for-managed-code
https://docs.microsoft.com/en-us/previous-versions/ms243147(v=vs.90)
https://raygun.com/blog/unit-testing-frameworks-c/
https://github.com/nunit/nunit/releases  // Current- NUnit 3.11, Oct 2018
https://xunit.github.io/  // Current XUnit core v2.4.1, Nov 2018

