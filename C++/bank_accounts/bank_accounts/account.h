//------------------------------------------------------------------------------
// Project Name: Bank Accounts
// Written by: Danzel Rana
// Last Modified: 06/11/2014
//------------------------------------------------------------------------------

#ifndef ACCOUNT_H
#define ACCOUNT_H

class Account
{
protected:
	int deposits;
	int withdrawals;
	double interestRate;
	double serviceCharges;
	virtual void calcInt();
public:
  double balance;
  Account();
	virtual void deposit(double);
	virtual void withdraw(double);
	virtual void monthlyProc();
  int getDeposits();
  int getWithdrawals();
  double getServiceCharges();
  double getBalance();
  void setWithdrawals(int);
  void setDeposits(int);
  void setServiceCharges(double);
};

#endif
