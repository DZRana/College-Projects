package hw22;

//Modified by Danzel Rana
//Written by Barry Soroka
//
//Solves CS 141, Winter 2013, Homework 22
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw22
{
//-----------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      System.out.println("\n n   sum2I(n)   sum2R(n)");
      for ( int n = 0 ; n <= 10 ; n++ )
         System.out.printf("%2d%7d%11d\n",n,sum2I(n),sum2R(n));
   }
//-----------------------------------------------------------------------
   public static int sum2I ( int n )
   {
      int out = 0;
      for ( int i = 0 ; i <= n ; i++ ) out += i*i;
      return out;
   }
//-----------------------------------------------------------------------
   public static int sum2R ( int n ) 
   {
	   if (n > 0)
	   return n*n + sum2R(n-1);
	   return 0;
   }
//-----------------------------------------------------------------------
} // end class Hw22
/////////////////////////////////////////////////////////////////////////