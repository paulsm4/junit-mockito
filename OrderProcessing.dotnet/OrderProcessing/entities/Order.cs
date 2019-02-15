
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace OrderProcessing.entities
{
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
}
