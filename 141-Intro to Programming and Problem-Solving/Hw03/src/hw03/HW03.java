package hw03;
// Modified by Danzel Rana
// Written by Barry Soroka
//
// Solves CS 141, Winter 2013, Homework 3.
//
import java.io.*;
import java.util.Scanner;
//////////////////////////////////////////////////////////////////////
class Hw03
{
//--------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      printLoHi("abc","ad","ab","ac");
      printLoHi("ygrec","xylophone","x");
      printLoHi("chupacabra");
      printLoHi("b","a","");
   }
//--------------------------------------------------------------------
   public static void printLoHi( String ... str) 
   {
      int markValLo = 0;
	  int markValHi = 0;
	  
	  String[] arrStr = str;
	  String marker = arrStr[0];
	  String markLoStr = "";
	  String markHiStr = "";
	  
	  System.out.print("\nStrings are: ");
	  for(String i : str) System.out.print('"' + i + '"' + ",");
	  for(String j : arrStr)
	  {
	     int loTest = j.compareTo(marker); 
		 int hiTest = j.compareTo(marker);
		 if (loTest <= markValLo)
		 {
		    markValLo = loTest;
		    markLoStr = j;
		 }
		 if (hiTest >= markValHi)
		 {
		    markValHi = hiTest;
		    markHiStr = j;
		 }
	  }
	  System.out.println("\nlo: " + '"' + markLoStr + '"');
	  System.out.println("hi: " + '"' + markHiStr + '"');
    }
//--------------------------------------------------------------------
} // end class Hw03
//////////////////////////////////////////////////////////////////////