//------------------------------------------------------------------------------
// Project Name: Bank Accounts
// Written by: Danzel Rana
// Last Modified: 06/11/2014
//------------------------------------------------------------------------------

#ifndef SAVINGS_H
#define SAVINGS_H

#include "account.h"

class Savings : public Account
{
protected:
	bool status;
public:
  Savings() : Account() {}
	void withdraw(double);
	void deposit(double);
	void monthlyProc();
  bool getStatus();
};

#endif
