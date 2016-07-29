package hw12;

//Modified by Danzel Rana
//Written by Barry Soroka
//
//Solves CS 141, Winter 2013, Homework 12.
//
import java.io.*;
import java.util.*;
//////////////////////////////////////////////////////////////////////
class Hw12
{
//--------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      foo("this is a test string");
      System.out.println();
      foo("now is the time for all good men to come to the aid " +
          "of the party");
   }
//--------------------------------------------------------------------
   public static void foo (String str)
   {
      String[] strArr = str.split(" ");
      Arrays.sort(strArr);
      for ( int i = 0; i < strArr.length; i++ )
         System.out.println(strArr[i]);
   }
//--------------------------------------------------------------------
} // end class Hw12
//////////////////////////////////////////////////////////////////////
