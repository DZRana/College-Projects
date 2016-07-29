package hw09;

//Modified by Danzel Rana.
//Written by Barry Soroka.
//
//Solves CS 141, Winter 2013, Homework 9.
//
import java.io.*;
import java.util.Scanner;
////////////////////////////////////////////////////////////////////////////////
class Hw09
{
//------------------------------------------------------------------------------
   public static void main (String [] args) throws Exception
   {
      test(0,0,"0");
      test(0,1,"i");
      test(0,-5,"-5i");
      test(1,0,"1");
      test(1,1,"1+i");
      test(1,2,"1+2i");
      test(1,-1,"1-i");
      test(1,-2,"1-2i");
      test(3,4,"3+4i");
      test(0,4,"4i");
      test(0,-4,"-4i");
      test(3,-4,"3-4i");
      test(-3,0,"-3");
   } 
//------------------------------------------------------------------------------
   public static void test ( int real, int imag, String expected )
   {
      String actual = (new Complex(real,imag)).toString();
      System.out.print( (actual.equals(expected)) ? " ok " : "*NO*" );
      System.out.println(" expected \"" + expected +
                         "\" actual was \"" + actual + "\"");
   }
//------------------------------------------------------------------------------
} // end class Hw09
////////////////////////////////////////////////////////////////////////////////
class Complex
{
   private int real;
   private int imag;
//-----------------------------------------------------------------------
   public Complex ( int real , int imag )
   {
      this.real = real;
      this.imag = imag;
   }
//-----------------------------------------------------------------------
   public String toString ()
   {
      if( (real == 0 && imag==0) ||( real == 1 && imag == 0) || (real < 0 && 
    	   imag == 0) )
	     return "" + real;
	  if( real == 0 && imag==1)
	     return "i";
	  if(real == 0 && imag != 0)
         return imag + "i";
	  if (imag == 1)
	     return real + "+" + "i";
	  if (imag == -1)
	     return real + "-" + "i";
	  if (imag < -1)
	     return real + "" + imag + "i";
	  return real + "+" + imag + "i";
   }  
//-----------------------------------------------------------------------
} // end class Complex
/////////////////////////////////////////////////////////////////////////
