//------------------------------------------------------------------------------
// Project Name: Bank Accounts
// Written by: Danzel Rana
// Last Modified: 06/11/2014
//------------------------------------------------------------------------------

#ifndef CHECKING_H
#define CHECKING_H

#include "account.h"

class Checking : public Account
{
public:
  Checking() : Account() {}
	void deposit(double);
	void monthlyProc();
};

#endif
