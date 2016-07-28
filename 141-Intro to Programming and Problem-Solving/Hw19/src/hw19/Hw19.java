package hw19;

//Written by Danzel Rana
//
//Solves CS 141, Winter 2013, Homework 19.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw19
{
//-----------------------------------------------------------------------
   public static void main (String [] args)
   {
      String num;
      boolean isInt;
      boolean isDouble;
      while (true)
      {
         System.out.println();
         System.out.print("Enter a String: ");
         Scanner kb = new Scanner (System.in);
         num = kb.nextLine();
         isInt = intCheck(num);
         isDouble = dblCheck(num);
         if (isInt == true)
         {
            System.out.println("\"" + num + "\"" + " is an int.");
         }
         if (isDouble == true)
         {
            System.out.println("\"" + num + "\"" + " is a double.");
         }
      }
   }
//-----------------------------------------------------------------------
   public static boolean intCheck (String num)
   {
      try
      {
         int i =Integer.parseInt(num);
      }
      catch (Exception e)
      {
         return false;
      }
      return true;
   }
//-----------------------------------------------------------------------
   public static boolean dblCheck (String num)
   {
      try
      {
         double d = Double.parseDouble(num);
      }
      catch (Exception e)
      {
         return false;
      }
      return true;
   }
//-----------------------------------------------------------------------
} // end class Hw19
/////////////////////////////////////////////////////////////////////////