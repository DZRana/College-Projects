package hw23;

//Modified by Danzel Rana
//Written by Barry Soroka
//
//Solves CS 141, Winter 2013, Homework 23.
//
import java.io.*;
import java.util.Scanner;
//////////////////////////////////////////////////////////////////////
class Hw23
{
//--------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      tester("");
      tester("x");
      tester("xx");
      tester("hello");
      tester("abbba");
      tester("yyzzza");
      tester("abcccdeefff");
      tester("333.14444155555999999999");
   }
//--------------------------------------------------------------------
   public static void tester ( String in )
   {
      String out = clean(in);
      System.out.printf("\"%s\" becomes \"%s\"\n",in,out);
   }
//--------------------------------------------------------------------
   public static String clean ( String s )
   {
      if (s.length() < 2) return s;
      if (s.substring(1,2).equals(s.substring(0,1))) return clean(s.substring(1));
      return s.substring(0,1) + clean(s.substring(1));
   }
//--------------------------------------------------------------------
} // end class Hw23
//////////////////////////////////////////////////////////////////////