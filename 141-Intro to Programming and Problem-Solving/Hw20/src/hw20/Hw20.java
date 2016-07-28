package hw20;

//Written by Danzel Rana
//
//Solves CS 141, Winter 2013, Homework 20.
//
import java.io.*;
import java.util.*;
/////////////////////////////////////////////////////////////////////////
class Hw20
{
//-----------------------------------------------------------------------
   public static void main (String [] args) throws Exception
   {
      int cntr = 0;
      while (true)
      {
         System.out.print("Input file? ");
         Scanner kb = new Scanner(System.in);
         String fileName = kb.nextLine();
         try 
         {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNextLine())
            {
               cntr++;
               sc.nextLine();
            }
            System.out.println("That file contains " + cntr + " line(s).\n");
            cntr = 0;
         }
         catch (Exception e) {System.out.println("Sorry, but that file" +
					                             " does not exist.\n");}
      }
   }
//-----------------------------------------------------------------------
} // end class Hw20
/////////////////////////////////////////////////////////////////////////