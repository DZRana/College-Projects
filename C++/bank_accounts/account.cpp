//------------------------------------------------------------------------------
// Project Name: Bank Accounts
// Written by: Danzel Rana
// Last Modified: 06/11/2014
//------------------------------------------------------------------------------

#include "account.h"
#include <iostream>
#include <fstream>

using namespace std;

Account :: Account()
{
  deposits = 0;
  withdrawals = 0;
  serviceCharges = 0.00;
  balance = 0.00;
  interestRate = 0.05;
}

// Add amount to balance and increment deposit count.
void Account :: deposit(double amnt)
{
  balance += amnt;
  deposits++;
}

// Subtract amount from balance and increment withdrawal count.
void Account :: withdraw(double amnt)
{
  balance -= amnt;
  withdrawals++;
}

// Calculate the interest and add it to the balance.
void Account :: calcInt()
{
  double month_int_rate = interestRate / 12;
  double month_int = balance * month_int_rate;
  balance += month_int;
}

// Calculates the interest first, then subtracts the service charges from the
// balance.
void Account :: monthlyProc()
{
  calcInt();
  balance -= serviceCharges;
}

int Account :: getDeposits() {return deposits;}

int Account :: getWithdrawals() {return withdrawals;}

double Account :: getServiceCharges() {return serviceCharges;}

double Account :: getBalance() {return balance;}

void Account :: setWithdrawals (int w) {withdrawals = w;}

void Account :: setDeposits (int d) {deposits = d;}

void Account :: setServiceCharges (double sc) {serviceCharges = sc;}
