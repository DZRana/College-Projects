package hw21;

//Modified by Danzel Rana
//Written by Barry Soroka
//
//Solves CS 141, Winter 2013, Homework 21.
//
import java.io.*;
import java.util.Scanner;
//////////////////////////////////////////////////////////////////////
class Hw21
{
//--------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      countDown(10);
      System.out.println();
      countDown(3);
      System.out.println();
      countDown(0);
      System.out.println();
      countDown(-5);
   }
//--------------------------------------------------------------------
   public static void countDown ( int n )
   {
      if (n < 0)
      {
         throw new IllegalArgumentException("Negative argument forbidden " + n);
      }
      else if (n == 0)
      {
         System.out.println("Liftoff!");
         return;
      }
      System.out.println(n);
      countDown(n-1);
   }
//--------------------------------------------------------------------
} // end class Hw21
//////////////////////////////////////////////////////////////////////
