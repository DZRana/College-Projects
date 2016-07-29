//------------------------------------------------------------------------------
// Project Name: ISP
// Written by: Danzel Rana
// Last Modified: 04/21/2014
//------------------------------------------------------------------------------

#include <iostream>

using namespace std;

int main()
{
    int package;
    double hours_used;
    double total_due;

    // Generate the menu and ask for a package.

    cout << "Select a subscription package: \n";
    cout << "1. Package A\n";
    cout << "2. Package B\n";
    cout << "3. Package C\n";
    cout << "4. Quit\n";
    cout << "\n";
    cin >> package;

    if (package > 0 && package < 5) // Check if input is between 0 and 5.
    {
        if (package == 4) cout << "Program ending.\n"; // User ends program.
        else
        {
            cout << "How many hours were used? ";
            cin >> hours_used;
            
            // Check if hours given by the user are within the parameters set.
            if (hours_used >= 0.00 && hours_used <= 744.00)
            {

                // Handling Package 1
                if (package == 1)
                {
                    if (hours_used > 10.00) // Check for additional hours.
                    {
                        total_due = ((hours_used - 10) * 2) + 9.95;
                        cout << "The total amount due is $" << total_due
                             << "\n";
                    }
                    else cout << "The total amount due is $9.95\n";
                }

                // Handling Package 2
                if (package == 2)
                {
                    if (hours_used > 20.00) // Check for additional hours.
                    {
                        total_due = ((hours_used - 20) * 1) + 14.95;
                        cout << "The total amount due is $" << total_due
                             << "\n";
                    }
                    else cout << "The total amount due is $14.95\n";
                }
                // Handling Package 3
                if (package == 3) cout << "The total amount due is $19.95\n";
            }
            else
                cout << "The hours used must be between 0.00 and 744.00.\n";
        }    
    }
    else
    {
        cout << "The valid choices are 1 through 4. Run the\nprogram again "
                "and select one of those.\n";
    }
}
