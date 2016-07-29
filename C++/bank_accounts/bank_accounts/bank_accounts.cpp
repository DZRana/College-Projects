//------------------------------------------------------------------------------
// Project Name: Bank Accounts
// Written by: Danzel Rana
// Last Modified: 06/11/2014
//------------------------------------------------------------------------------

#include "checking.h"
#include "savings.h"
#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>

using namespace std;

int prompt(ofstream& oFile, ifstream& iFile); // Calls the menu.

// Takes in an account object, whether it be savinvs or checking, and deposits.
void accntDeposit(Account&, ofstream& oFile, ifstream& iFile); 

// Updates stats and displays them.
void displayUpdateStats(Account&, Account&, ofstream& oFile);

Savings sAccnt; // Create savings account.
Checking cAccnt; // Creat checking account.

int main(int argc, char *argv[])
{
  string inputFile, outputFile; // File names.

  inputFile = argv[1];
  outputFile = argv[2];

  ifstream iFile;
  iFile.open(inputFile.c_str()); // Open input file to write to.
  ofstream oFile;
  oFile.open(outputFile.c_str()); // Open output file to write to.

  if (iFile.fail()) {return -1;} // Check if input file exists.
  if (oFile.fail()) {return -1;} // Chec if output file exists.

  int choice;

  // Loop to display prompt until user enters a 6.
  while ((choice = prompt(oFile, iFile)) != 6)
  {
    while (choice < 1 || choice > 6) // Loop for a new input if # not in range.
    {
      oFile << "Enter a number from 1 through 6 please: ";
      iFile >> choice;
    }
    switch(choice)
    {
      case 1: accntDeposit(sAccnt, oFile, iFile); // Depositing to savings.
              break;
      case 2: accntDeposit(cAccnt, oFile, iFile); // Depositing to checking.
              break;
      // Withdrawing from savings.
      case 3: double sAmnt;
              oFile << "Enter amount to withdraw: ";
              iFile >> sAmnt;

              // Check to see if account is inactive.
              if (sAccnt.getStatus() == false)
              {
                oFile << "Account is inactive.\n\n";
                break;
              }

              // Check to see if account falls below threshold, then
              // deactivates it if it is.
              else if ((sAccnt.balance - sAmnt) < 25.00)
              {
                oFile << "\nYour account has fallen below $25.00.\n";
                oFile << "It will be deactivated.\n";
                sAccnt.withdraw(sAmnt);
                break;
              }

              // Regular withdrawal from savings.
              sAccnt.withdraw(sAmnt);
              break;
      // Withdrawing from checking.
      case 4: double cAmnt;
              oFile << "Enter amount to withdraw: ";
              iFile >> cAmnt;

              // Check to see if balance will drop below 0 after withdrawal.
              // If so, no withdrawal and -$15.
              if ((cAccnt.balance - cAmnt) < 0)
              {
                oFile << "You are attempting to withdraw more than the";
                oFile << " account balance.\n";
                cAccnt.balance -= 15.0;
                break;
              }
              // Regular withdrawal from checking.
              else cAccnt.withdraw(cAmnt);
              break;

      // Updates and displays stats.
      case 5: displayUpdateStats(sAccnt, cAccnt, oFile);
              break;
      case 6: return 0; // Exit Program.
    }
  }
  oFile.close(); // Close output file.
  iFile.close(); // Close input file.
  return 0;
}

int prompt(ofstream& oFile, ifstream& iFile) // Menu.
{
  int choice;

  oFile << "\n\n******** BANK ACCOUNT MENU ********\n\n";
  oFile << "1.  Savings Account Deposit\n";
  oFile << "2.  Checking Account Deposit\n";
  oFile << "3.  Savings Account Withdrawal\n";
  oFile << "4.  Checking Account Withdrawal\n";
  oFile << "5.  Update and Display Account Statistics\n";
  oFile << "6.  Exit\n\n";
  oFile << "Your choice, please: (1-6)  ";
  iFile >> choice;

  return choice;
}

// Takes the amount and deposits it into either checking or savings.
void accntDeposit(Account &accnt, ofstream& oFile, ifstream& iFile)
{
  double amnt;
  
  oFile << "Enter amount to deposit: ";
  iFile >> amnt;

  accnt.deposit(amnt);
}

// Updated stats.
void displayUpdateStats(Account &savings, Account &checking, 
                        ofstream& oFile)
{
  checking.monthlyProc();
  savings.monthlyProc();
  oFile << "\n\nSAVINGS ACCOUNT MONTHLY STATISTICS:\n";
  oFile << "Withdrawals:\t\t" << savings.getWithdrawals() << "\n";
  savings.setWithdrawals(0);
  oFile << "Deposits:\t\t" << savings.getDeposits() << "\n";
  savings.setDeposits(0);
  oFile << fixed << setprecision(2);
  oFile << "Service Charges:\t" << savings.getServiceCharges() << "\n\n";
  oFile << "Ending Balance:\t\t" << savings.getBalance() << "\n\n";
  savings.setServiceCharges(0.00);
  oFile << setprecision(0);
  oFile << "\n\n";
  oFile << "CHECKING ACCOUNT MONTHLY STATISTICS:\n";
  oFile << "Withdrawals:\t\t" << checking.getWithdrawals() << "\n";
  checking.setWithdrawals(0);
  oFile << "Deposits:\t\t" << checking.getDeposits() << "\n";
  checking.setDeposits(0);
  oFile << setprecision(2);
  oFile << "Service Charges:\t" << checking.getServiceCharges() << "\n\n";
  oFile << "Ending Balance:\t\t" << checking.getBalance() << "\n\n";
  checking.setServiceCharges(0.00);
  oFile << setprecision(0); 
}
