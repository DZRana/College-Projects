// Driver written by Jacob Curtis, May 2008.
//
// Tests String infixToPostfix(String).
//
import java.util.Stack;
import java.io.*;
///////////////////////////////////////////////////////////////////////////////
class Hw07
{
//----------------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      System.out.println("\n-----Testing Singleton Cases-----");
      
      testPostfix( "A+   B" , "A B + " );
      testPostfix( "   A-B" , "A B - " );
      testPostfix( "A * B " , "A B * " );
      testPostfix( "A /B  " , "A B / " );
      
      System.out.println("\n-----Testing Singleton Parentheses-----");

      testPostfix( "(A+   B)" , "A B + " );
      testPostfix( "(   A-B)" , "A B - " );
      testPostfix( "(A * B )" , "A B * " );
      testPostfix( "(A /B ) " , "A B / " );

      System.out.println("\n-----Testing Equal Precedence-----");

      testPostfix( "A + B + C" , "A B + C + " );
      testPostfix( "A - B - C" , "A B - C - " );
      testPostfix( "A * B * C" , "A B * C * " );
      testPostfix( "A / B / C" , "A B / C / " );
      testPostfix( "A + B - C" , "A B + C - " );
      testPostfix( "A - B + C" , "A B - C + " );
      testPostfix( "A * B / C" , "A B * C / " );
      testPostfix( "A / B * C" , "A B / C * " );

      System.out.println("\n-----Testing Equal Prec. Parens.-----");

      testPostfix( "(A + B) + C" , "A B + C + " );
      testPostfix( "(A - B) - C" , "A B - C - " );
      testPostfix( "(A * B) * C" , "A B * C * " );
      testPostfix( "(A / B) / C" , "A B / C / " );
      testPostfix( "A + (B - C)" , "A B C - + " );
      testPostfix( "A - (B + C)" , "A B C + - " );
      testPostfix( "A * (B / C)" , "A B C / * " );
      testPostfix( "A / (B * C)" , "A B C * / " );

      System.out.println("\n-----Testing Precedence-----");

      testPostfix( "A + B * C" , "A B C * + " );
      testPostfix( "A - B / C" , "A B C / - " );
      testPostfix( "A * B + C" , "A B * C + " );
      testPostfix( "A / B - C" , "A B / C - " );
      testPostfix( "A + B / C" , "A B C / + " );
      testPostfix( "A - B * C" , "A B C * - " );
      testPostfix( "A * B - C" , "A B * C - " );
      testPostfix( "A / B + C" , "A B / C + " );

      System.out.println("\n-----Testing Precedence w/ Parens.-----");

      testPostfix( "(A + B) * C" , "A B + C * " );
      testPostfix( "(A - B) / C" , "A B - C / " );
      testPostfix( "(A * B) + C" , "A B * C + " );
      testPostfix( "(A / B) - C" , "A B / C - " );
      testPostfix( "A + (B / C)" , "A B C / + " );
      testPostfix( "A - (B * C)" , "A B C * - " );
      testPostfix( "A * (B - C)" , "A B C - * " );
      testPostfix( "A / (B + C)" , "A B C + / " );

      System.out.println("\n-----Testing Hard Cases-----");

      testPostfix( "A * X + (Y-B) - Z" , "A X * Y B - + Z - " );
      testPostfix( "A* (X +Y - B) - Z" , "A X Y + B - * Z - " );
      testPostfix( "A* (X +Y * B) - Z" , "A X Y B * + * Z - " );
      testPostfix( "A - X + (Y-B) * Z" , "A X - Y B - Z * + " );
      testPostfix( "(A / X - Y * B)+Z" , "A X / Y B * - Z + " );
      testPostfix( "A/(X+Y*B)+C-(D+Z)" , "A X Y B * + / C + D Z + - " );
      testPostfix( "A-X* (Y+ (B/C)+D)" , "A X Y B C / + D + * - " );
      testPostfix( "A/X+ (Y+ (B-C)*D)" , "A X / Y B C - D * + + " );
      testPostfix( "A+X * (Y+B)/(C-D)" , "A X Y B + * C D - / + " );
   }
//----------------------------------------------------------------------------
//
// testPostfix(String,String)
//
// takes input, runs postfix, then tests against given correct postfix
//
   public static void testPostfix ( String input, String expected )
                                                           throws Exception
   {
      System.out.println( "\nInfix:\t\t\"" + input + "\"");

      String actual = ITP.infixToPostfix(input);

      System.out.print( "Postfix:\t\"" + actual + "\":" );
      System.out.println( (actual.equals(expected) ? "pass" : 
                          ("*** FAIL ***\nShould Be: \"" + expected + "\"") ) );
   }
//----------------------------------------------------------------------------
} // end class Hw07
///////////////////////////////////////////////////////////////////////////////