using System.Reflection;
using System.Runtime.CompilerServices;
using System.Runtime.InteropServices;
namespace SnakeGame
{
    class Object
    {
        public int x { get; set; }
        public int y { get; set; }
        
        public Object()
        {
            x = 0;
            y = 0;
        }
    }
}