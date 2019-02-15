using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using System.Collections.Generic;
using System.Linq;
using System.Data.Entity;
using OrderProcessing;
using OrderProcessing.entities;

namespace OrderProcessingTests
{
    /**
     * REFERENCE:
     * - Testing with a mocking framework (MSDN, EF6)
     *   https://docs.microsoft.com/en-us/ef/ef6/fundamentals/testing/mocking
     */
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
}
