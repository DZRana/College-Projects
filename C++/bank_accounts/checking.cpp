//------------------------------------------------------------------------------
// Project Name: Bank Accounts
// Written by: Danzel Rana
// Last Modified: 06/11/2014
//------------------------------------------------------------------------------

#include "checking.h"
#include <iostream>
#include <fstream>

using namespace std;

// Send amount to the deposit function in Account class.
void Checking :: deposit(double amnt)
{
  Account :: deposit(amnt);
}

// Calculate and add the service charges.
void Checking :: monthlyProc()
{
  serviceCharges += 5.0;
  serviceCharges += (withdrawals * 0.1);
  Account :: monthlyProc();
}
