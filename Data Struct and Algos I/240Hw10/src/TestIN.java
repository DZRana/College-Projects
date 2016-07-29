// Modified by Danzel Rana
// Written by Barry Soroka
//
// Solves CS 240, Spring 2013, Homework 10.
//
// Tests IN -- int node using recursion.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class TestIN
{
//-----------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
//      testInitial();
//      testLength();
//      testSum();
//      testPrint();
//      testPrintR();
//      testIncrement();
//      testCopy();
//      testEquals();
//      testKeepOdd();
//      testAllOdd();
//      testSomeOdd();
   	testDoubler();
   }
//-----------------------------------------------------------------------
   private static void testDoubler ()
   {
   	System.out.println();
      System.out.println("============================================");
      System.out.println("=== testDoubler ============================");
      System.out.println("============================================");

      testDoubler1(IN.makeChain());
      testDoubler1(IN.makeChain(2));
      testDoubler1(IN.makeChain(2,7,1,8));
      testDoubler1(IN.makeChain(0000,02,6,242));
      testDoubler1(IN.makeChain(5));
      testDoubler1(IN.makeChain(1024,4213,4));
      testDoubler1(IN.makeChain(000,000000,0,00));
      testDoubler1(IN.makeChain(1,1,1));
      testDoubler1(IN.makeChain(1073741823,11,9));
      testDoubler1(IN.makeChain(1073741824,11,9));
      testDoubler1(IN.makeChain(0));
      testDoubler1(IN.makeChain(0,0));
      testDoubler1(IN.makeChain(1));
   }
//-----------------------------------------------------------------------
   private static void testDoubler1 ( IN list1 )
   {
      System.out.println();
      System.out.println("list1 = " + list1);
      System.out.println("executing IN list2 = IN.doubler(list1)...");
      IN list2 = IN.doubler(list1);
      System.out.println("list1 = " + list1);
      System.out.println("list2 = " + list2);    
   }
//-----------------------------------------------------------------------
} // end class TestIN
/////////////////////////////////////////////////////////////////////////