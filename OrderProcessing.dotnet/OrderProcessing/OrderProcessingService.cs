using OrderProcessing.entities;
using System.Collections.Generic;
using System.Linq;

namespace OrderProcessing
{
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

        public bool updateOrder(Order order)
        {
            return false;
        }


        public bool cancelOrder(int orderId)
        {
            return false;
        }

        public bool deleteOrder(int orderId)
        {
            return false;
        }
    }
}
