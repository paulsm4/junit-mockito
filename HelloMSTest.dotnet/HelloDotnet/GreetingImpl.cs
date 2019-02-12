using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HelloDotnet
{
    public class GreetingImpl
    {
        public string greet(string name)
        {
            if (String.IsNullOrEmpty(name))
            {
                throw new ArgumentOutOfRangeException();
            }
            return "Hello " + name;
        }
    }
}
