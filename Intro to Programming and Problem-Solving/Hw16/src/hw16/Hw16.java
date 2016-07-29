package hw16;

//Modified by Danzel Rana
//Written by Barry Soroka
//
//Solves CS 141, Winter 2013, Homework 16.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw16
{
//-----------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      Account a = new Account("linda","1233");
      System.out.println("\n" + a);

      SavingsAccount sa = new SavingsAccount("bill","0385",200);
      System.out.println("\n" + sa);

      sa.deposit(100);
      System.out.println("\n" + sa);

      sa.withdraw(50);
      System.out.println("\n" + sa);
   }
//-----------------------------------------------------------------------
} // end class Hw16
/////////////////////////////////////////////////////////////////////////
class Account
{
   protected String name;
   protected String accountNumber;
//-----------------------------------------------------------------------
   public Account (String name, String accountNumber)
   {
      this.name = name;
      this.accountNumber = accountNumber;
   }
//-----------------------------------------------------------------------
   public String toString()
   {
      return String.format("%s\n%s", accountNumber, name);
   }
//-----------------------------------------------------------------------
} // end class Account
/////////////////////////////////////////////////////////////////////////
class SavingsAccount extends Account
{
   private double balance;
//-----------------------------------------------------------------------
   public SavingsAccount (String name, String accountNumber, double balance)
   {
      super(name, accountNumber);
      this.balance = balance;
   }
//-----------------------------------------------------------------------
   public void deposit (double deposit)
   {
      balance = balance + deposit;
   }
//-----------------------------------------------------------------------
   public void withdraw (double withdrawal)
   {
      balance = balance - withdrawal;
   }
//-----------------------------------------------------------------------
   public String toString()
   {
      return String.format("%s\n%s\n$%.2f",accountNumber, name, balance);
   }
//-----------------------------------------------------------------------
} // end class SavingsAccount
/////////////////////////////////////////////////////////////////////////
