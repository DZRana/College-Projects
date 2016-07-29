//------------------------------------------------------------------------------
// Project Name: Bank Accounts
// Written by: Danzel Rana
// Last Modified: 06/11/2014
//------------------------------------------------------------------------------

#include "savings.h"
#include <iostream>
#include <fstream>

using namespace std;

void Savings :: withdraw(double amnt)
{
  // If the flag is true, withdraw normally.
  if (status == true) Account :: withdraw(amnt);

  // If the balance after the withdrawal is under $25, sets flag to false.
  if ((balance - amnt) < 25.00) {status = false;}

  // If the balance is currently under $25, flag is set to false.
  if (balance < 25.00) {status = false;}
}

void Savings :: deposit(double amnt)
{
  // If the deposit increases the balance to over $25, sets the flag to false.
  if (((balance + amnt) > 25) && status == false) status = true;
  Account :: deposit(amnt);
}

void Savings :: monthlyProc()
{
  // If balance  < $25 when called, flag is set to false.
  if (balance < 25.00) status = false;

  // If there have been more than 4 withdrawals, add service charges.
  if ((withdrawals - 4) > 0) serviceCharges += (4 + ((withdrawals - 4) * 1.0));
  Account :: monthlyProc();
}

bool Savings :: getStatus() {return status;}
