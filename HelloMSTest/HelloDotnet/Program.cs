using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HelloDotnet
{
    /**
     * SAMPLE OUTPUT:
     *   Hello Mortimer Snerd
     */
    class Program
    {
        static void Main(string[] args)
        {
            string s = new GreetingImpl().greet("Mortimer Snerd");
            Console.WriteLine(s);
            Console.ReadLine();
        }
    }
}
