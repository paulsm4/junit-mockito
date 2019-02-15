using OrderProcessing.entities;
using System.Collections.Generic;


namespace OrderProcessing
{
    class Program
    {
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
    }
}
