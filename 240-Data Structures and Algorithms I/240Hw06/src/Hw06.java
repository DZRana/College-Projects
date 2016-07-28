// Modified by Danzel Rana
// Written by Barry Soroka
//
// Auto-tester for CS 240, Spring 2013, Homework 6.
//
import java.io.*;
import java.util.Scanner;
//////////////////////////////////////////////////////////////////////
class Hw06
{
//--------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      String[] tests =
      {
         "2 3 *", "6",
         "2  3 +   ", "5",
         "2 3", "too few operators",
         "2 +", "too few operands",
         "", "too few operands",
         "   ", "too few operands",
         "   2    3         4 *+       ", "14",
         "23*4+", "10",
         " 23 + 4 * ", "20",
         "2+34*", "too few operands",
         "2 3 + 4", "too few operators",
         " 23*   4 5   6++   *", "90",
         "001+", "too few operators",
         "0 000 0 ++", "too few operators",
         " 0 0   00  0 +     +++", "0",
         " 0  0 0   00 +  + + + +", "too few operands",
      };

      for ( int i = 0 ; i < tests.length ; i += 2 )
         test1(tests[i],tests[i+1]);
   }
//--------------------------------------------------------------------
   public static void test1 ( String postfix, String shouldBe )
   {
      String is = EvalPostfix.eval(postfix);
      System.out.println( ((is.equals(shouldBe)) ? "ok  " : "FAIL") +
                          " -- should be \"" + shouldBe +
                          "\" IS \"" + is + "\"" );                          
   }
//--------------------------------------------------------------------
} // end class Hw06
//////////////////////////////////////////////////////////////////////
