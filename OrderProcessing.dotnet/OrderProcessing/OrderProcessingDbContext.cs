using OrderProcessing.entities;
using System.Data.Entity;

namespace OrderProcessing
{
    public class OrderProcessingDbContext : DbContext
    {
       public virtual DbSet<Order> Orders { get; set; }

    }
}
