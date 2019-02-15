* Udemy: JUnit and Mockito Crash Course, Bharath Thrippireddy
  - URL: https://www.udemy.com/junitandmockitocrashcourse/learn/v4/content
  - Software: Java 1.8 or higher, Eclipse Mars or higher, JUnit4 (*not* Junit5/Jupiter)

* Sub-projects:
Project                 Lessons:  Notes:
-------                 -------   -----
HelloJUnit              1-9       Using JUnit
HelloJUnit5             10-17     Migrating JUnit4 => JUnit5 (Jupiter)
OrderProcessingService  18-31     Mocking: mock everything *except* the unit under test; initMocks() > set expections > verify
    "     "             32-39     Code coverage (eclEmma)
MockitoScrapBook        40-52     F.I.R.S.T; stubbing "void" methods; "doNothing()" for consecutive calls; "Test Doubles": {dummy objects, stubs, mocks, fakes, spies}
Calculator              53-57     Parameterized JUnit tests
UserAdminService        58-63     PowerMock
SpringJUnit             64-75     Spring, spring-test module, DI with Mockito

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

  ADDITIONAL NOTES:
  - Discrepencies can crop up between:
    - source/target JDK versions and actual runtime JRE
    - Eclipse project source/target JDK versions and Maven
    - Maven build plugin versions and Eclipse IDE actions
    - Etc.

===================================================================================================

* BO/DAO/DTO
    - "Business object" uses DAO; "Data Access Object" interacts with underlying DB
    - DTO: "A simple container for aggregated data"; to avoid unnecessary remote calls to server
    <= What Bharath calls "dto" is more what I'd call an "entity": EX: "Order", "User", etc.

===================================================================================================

* HelloMSTest:
https://docs.microsoft.com/en-us/visualstudio/test/getting-started-with-unit-testing
  <= Transcribed Junit "Hello world" test => C#/.Net:
  1. MSVS > File > New Project > C#/Console app, Name= HelloDotnet

  2. Add > New > Item > 
       Interface > IGreeting
       Class > GreetingImpl
         <= public interface IGreeting {}:
            Single method: "string greet(string name);"

  3. Fill in basic implementations: 
       {IGreeting.cs, GreetingImpl.cs, Program.cs}
       <= Verified OK

  4. MSVS > File > Add > New Project > Test > Unit Test Project, Name= HelloTest
      <= Adds new MSTest Unit Test project to "HelloDotNet" console application project

  5. References > Add Reference > Projects > HelloDotnet

  6. Code Test(s):
     GreetingImplTest.cs:
     --------------------
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
  7. HelloTest > Build
     Tests > Run > run All tests
     <= Greet

ADDITIONAL LINKS:
https://stackoverflow.com/questions/4101617/what-are-the-differences-between-microsoft-testtools-unittesting-and-nunit
https://docs.microsoft.com/en-us/visualstudio/test/walkthrough-creating-and-running-unit-tests-for-managed-code
https://docs.microsoft.com/en-us/previous-versions/ms243147(v=vs.90)
https://raygun.com/blog/unit-testing-frameworks-c/
https://github.com/nunit/nunit/releases  // Current- NUnit 3.11, Oct 2018
https://xunit.github.io/  // Current XUnit core v2.4.1, Nov 2018

===================================================================================================

* OrderProcessing.dotnet: Unit testing with EF6 console-mode app + Moq
https://docs.microsoft.com/en-us/ef/ef6/fundamentals/testing/mocking
  <= Transcribed Junit "OrderProcessingService"
  
* GAMEPLAN:
  1. Create simple console-mode app based on JEclipse/Junit/Mockito "OrderProcessingService" project
  2. Implement "Order" object as an EF6 entity
  3. OrderProcessing.dotnet project modules:
     - Program.cs: Main (EF-based integration test)
     - entities.Order.cs: EF6 entity
     - OrderProcessingDbContext.cs: EF6 DbContext
     - OrderProcessingService: our "Business Object"
     <= 1st cut, we'll use "Program.cs" to verify "OrderProcessingService" against LocalDB
        This is an "integration test"; it exercises the entire project (vs. isolating a single test class)
  4. Create "OrderProcessingTest" subproject, with "OrderProcessingServiceTest" test case.  
     Use Moq to mock out EF layer, isolating OrderProcessingTest" (the unit under test).

* Implementation:
  1. MSVS > File > New Project > 
       Type= C#/Console app, Name= OrderProcessing, Create subfolder= Y
     Exit out of MSVS; rename toplevel folder "OrderProcessing.dotnet"

  2. Enable Entity Framework:
     NuGet Pkg Mgr > Browser > EntityFramework
     <= Installed EF 6.2.0 (.Net version); Install at project-level= Y
     
  3. Add > New > Item > 
       Class= OrderProcessingService
       Folder= entities; Class= entities.Order
       Class > OrderProcessingDbContext

  4. entities.Order.cs:
     -----------------
   [Table("ORDERS_TB")]
    public class Order
    {
        public Order() { }

        public Order(string status)
        {
            this.Status = status;
        }

        [Key]
        public int Id { get; set; }
        public string Status { get; set; }
    }
  5. OrderProcessingDbContext.cs:
     ---------------------------
    public class OrderProcessingDbContext : DbContext
    {
       public virtual DbSet<Order> Orders { get; set; }
    }
    <= NOTE: Moq won't let us mock this unless we declare it "virtual" (or create an interface wrapper)

  6. OrderProcessingService.cs:
     -------------------------
     public class OrderProcessingService
    {
        private OrderProcessingDbContext ctx;

        public OrderProcessingService (OrderProcessingDbContext ctx)
        {
            this.ctx = ctx;
        }

        public int placeOrder(Order order) {
            {
                ctx.Orders.Add(order);
                ctx.SaveChanges();
            }
            return order.Id;
        }

        public List<Order> GetAllOrders ()
        {
            var query =
                from b in ctx.Orders
                select b;
            return query.ToList();
        }
        ...

  7. Program.cs:
     ----------
     static void Main(string[] args)
     {
         // Integration test: test "OrderService" against LocalDB
         using (var ctx = new OrderProcessingDbContext())
         {
             OrderProcessingService service = new OrderProcessingService(ctx);
             int id = service.placeOrder(new entities.Order("PENDING"));
             System.Console.WriteLine("Created new order# " + id);
             List<Order> orders = service.GetAllOrders();
             foreach (Order o in orders)
             {
                 System.Console.WriteLine("  Order id: " + o.Id + ", status: " + o.Status);
             }
         }
         System.Console.ReadLine();
     }

  9. Run app 
     <= Verified OK: Created new "Order" ID

 10. MSVS > View > SQL Object Explorer >
       Connect > LocalDB > Databases > OrderProcessing.OrderProcessingDbContext
       <= Able to see dbo.ORDERS_TB table; 7 rows of "Order" data
 --------------------------------------------------------------------------------------------------
* Add MSTests:

 11. MSVS > File > Add > New Project > 
       Type= C#/Test > Unit Test Project, Name= OrderProcessingTests, Create subfolder= Y

 12. OrderProcessingTests > Right-Click, NuGet Pkg Mgr > Browse > Moq >
       Install Moq 4.10.1, install at OrderProcessingTests level= Y
     Brings in following dependent NuGet packages:
        Castle.Core.4.3.1
        EntityFramework.6.2.0
        Moq.4.10.1
        System.Runtime.CompilerServices.Unsafe.4.5.0
        System.Threading.Tasks.Extensions.4.5.1

 13. OrderProcessingTests > References > Add Reference >
       Projects > Add "OrderProcessing"
       <= This makes Order, OrderProcessingService and OrderProcessingDbContext available for unit testing...

 14. Rename and implement unit test:
    OrderPRocessingServiceTest.cs:
    -----------------------------
   [TestClass]
    public class OrderProcessingServiceTest
    {
        [TestMethod]
        public void PlaceOrderShouldCreateAnOrder()
        {
            Order order = new Order("PENDING");
            var mockSet = new Mock<DbSet<Order>>();
            var mockContext = new Mock<OrderProcessingDbContext>();
            mockContext.Setup(m => m.Orders).Returns(mockSet.Object);

            var service = new OrderProcessingService(mockContext.Object);
            int result = service.placeOrder(order);

            Assert.AreEqual(0, result);
            mockSet.Verify(m => m.Add(It.IsAny<Order>()), Times.Once());
            mockContext.Verify(m => m.SaveChanges(), Times.Once());
        }

        [TestMethod]
        public void GetAllOrdersShouldReturnList()
        {
            var data = new List<Order>
            {
                new Order ("AAA"),
                new Order ("BBB"),
                new Order ("CCC")
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Order>>();
            mockSet.As<IQueryable<Order>>().Setup(m => m.Provider).Returns(data.Provider);
            mockSet.As<IQueryable<Order>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Order>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Order>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<OrderProcessingDbContext>();
            mockContext.Setup(c => c.Orders).Returns(mockSet.Object);

            var service = new OrderProcessingService(mockContext.Object);
            var orders = service.GetAllOrders();

            Assert.AreEqual(3, orders.Count);
            Assert.AreEqual("AAA", orders[0].Status);
            Assert.AreEqual("BBB", orders[1].Status);
            Assert.AreEqual("CCC", orders[2].Status);
        }

    }

 15. Test > Run > All Tests
     <= Verify OK

     NOTE:
     - *Cannot* "debug" OrderProcessingTests from Solution Explorer:
       <= Error: A project wit an output Type of Class Library cannot be started directly
     - Instead, you must:
       a) Set breakpoints as needed
       b) Test > Debug > All Tests
          <= This will execute the test running, and stop at the configured breakpoints
    



===================================================================================================

