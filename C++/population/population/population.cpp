//------------------------------------------------------------------------------
// Project Name: Population
// Written by: Danzel Rana
// Last Modified: 05/02/2014
//------------------------------------------------------------------------------

/** 
 * Instructor Feedback:
 *
 * I appreciate your use of functions to remove a bulk of the program's
 * implementation from the main function. However, you basically just dumped 
 * everything into the Prompt function.
 *
 * -5
 *
 * I would like to see just a bit more commenting please.
 *
 * -5
 *
 * Great work! 50/50 test cases passed!
 *
 * -0
*/

#include <iostream>

using namespace std;

void Prompt();
void CalcPopulation(int, double, double, double);

const int MAX_YEARS = 100;

int main()
{
  cout << "This program calculates population change.\n";
  Prompt(); //Call Prompt() function for user input.
}

void Prompt()
{
  int pop_size;
  double birth_rate;
  double death_rate;
  double years_displayed;

  cout << "Enter the starting population size: ";
  cin >> pop_size;
  
  // Handle Population Size < 2
  while (pop_size < 2)
  {
    cout << "Starting population must be 2 or more.\n";
    cout << "Please re-enter: ";
    cin >> pop_size;
  }
  cout << "Enter the annual birth rate (as % of current population): \n";
  cin >> birth_rate;
  
  // Handle Negative Birth rate.
  while (birth_rate < 0)
  {
    cout << "Birth rate percent cannot be negative.\n";
    cout << "Please re-enter: \n";
    cin >> birth_rate;
  }
  cout << "Enter the annual death rate (as % of current population): \n";
  cin >> death_rate;

  // Handle Negative Death rate.
  while (death_rate < 0)
  {
    cout << "Death rate percent cannot be negative.\n";
    cout << "Please re-enter: \n";
    cin >> death_rate;
  }
  cout << "For how many years do you wish to view population changes? \n";
  cin >> years_displayed;

  // Handle if years wanting to be displayed is negative or greater than 100.
  while (years_displayed < 1 || years_displayed > 100)
  {
    cout << "Years must be one or more, but less than or equal to 100.\n";
    cout << "Please re-enter: \n";
    cin >> years_displayed;
  }
  cout << "\n\n";
  cout << "Starting population: " << pop_size << "\n\n";

  // Call CalcPopulation() function to deal with user input. 
  CalcPopulation(pop_size, birth_rate, death_rate, years_displayed);
}

void CalcPopulation(int pop_size, double birth_rate, double death_rate,
                    double years_displayed)
{
  int year_arr[MAX_YEARS]; // Initialize array that stores population sizes.
  double n_pop = pop_size; 
  
  // Walk through array while storing values and displaying each step.
  for (int i = 1; i <= years_displayed; i++)
  {
    n_pop = n_pop + ((birth_rate * n_pop) - ((death_rate * n_pop)));
    year_arr[i-1] = n_pop; // Stores first, new population value into arr[0].
    cout << "Population at the end of year " << i << " is "
         << year_arr[i-1] << ".\n";
  }
}
