package hw02;

//Modified by Danzel Rana
//Written by Barry Soroka
//
//Solves CS 141, Winter 2013, Homework 2.
//
import java.io.*;
import java.util.Scanner;
//////////////////////////////////////////////////////////////////////
class Hw02
{
//--------------------------------------------------------------------
public static void main ( String [] args ) throws Exception
{
   test(1,2,3);
   test(3,1,4,1,5,9,2,6,5,3,5);
   test(5);
   test(2,7);
   test(2,7,1,8,2,8,1,8,2,8);
   test();
}
//--------------------------------------------------------------------
public static void test ( int ... a )
{
   int[] in = a;
   System.out.print("\nin:  ");
   for ( int n : in ) System.out.print(n + " ");
   int[] out = shiftLeft(a);
   System.out.print("\nout: ");
   for ( int n : out ) System.out.print(n + " ");
   System.out.println();
}
//--------------------------------------------------------------------
public static int[] shiftLeft ( int[] a )
{
   if ( a.length > 0 )
   {
      int marker = a[0];
	  for ( int i = 0; i < a.length - 1; i++)
	     a[i] = a[i + 1];
	  a[a.length - 1] = marker;
   }
   return a;
}
//--------------------------------------------------------------------
} // end class Hw02
//////////////////////////////////////////////////////////////////////
