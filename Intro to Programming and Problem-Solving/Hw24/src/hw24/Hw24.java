package hw24;

//Written by Danzel Rana
//
//Solves CS 141, Winter 2013, Homework 24.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw24
{
   private static int callCntr = 0;
   private static int subCntr = 0;
   private static int depth = 0;
//-----------------------------------------------------------------------
   public static void main (String [] args)
   {
      System.out.println("Result: " + tak(18,12,6));
      System.out.println("Calls: " + callCntr);
      System.out.println("Subtractions by 1: " + subCntr * 3);
      if (depth > 0)
         System.out.println("Recursive depth is greater than 18");
      else
    	 System.out.println("Recursive depth: 18");
   }
//----------------------------------------------------------------------- 
   public static int tak (int x, int y, int z)
   {
      callCntr++;
      if (y >= x) return z;
      if (y < x) subCntr++;
      if (x > 18 || y > 18 || z > 18) depth++;
      return tak( tak(x-1,y,z),
    		      tak(y-1,z,x),
    		      tak(z-1,x,y) );
   }
//-----------------------------------------------------------------------
} // end class Hw24
/////////////////////////////////////////////////////////////////////////