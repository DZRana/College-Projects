#include <iostream>

using namespace std;

int main()
{
    char pin[6];
    pin[0] = 0x62;
    pin[1] = 0x0A;
    pin[2] = 0x8D;
    pin[3] = 0x1D;
    pin[4] = 0x19;
    pin[5] = 0x00;
    for (int i = 0; i < 6; i++)
    {
      cout << pin[i];
    }

}
