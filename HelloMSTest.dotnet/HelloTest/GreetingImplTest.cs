using System;
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
            Assert.AreEqual("Hello ABC", result);
        }
    }
}
