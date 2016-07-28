package hw17;

//Modified by Danzel Rana
//Written by Dr Barry Soroka
//
//Solves CS 141, Winter 2013, Homework 17.
//
import java.io.*;
//////////////////////////////////////////////////////////////////
class Hw17
{
//----------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      CPS cps = new CPS("foo");

      cps.setColumnWidth(6);
      cps.print("hello");
      cps.print(5);
      cps.print('a');
      cps.print("too big");
      cps.println(37.3);

      for ( int w = 0 ; w < 9 ; w++ )
      {
         cps.setColumnWidth(w);
         for ( int i = 0 ; i < 9 ; i++ ) cps.print(i);
         cps.println();
      }

      for ( int w = 0 ; w < 10 ; w++ )
      {
         cps.setColumnWidth(w);
         for ( char c : "abcdefg".toCharArray() ) cps.print(c);
         cps.println();
      }

      cps.println();
      cps.setColumnWidth(4);
      for ( int i = 0 ; i < 20 ; i++ )
      {
         cps.print((int)Math.pow(2,i));
         if ( i%3 == 0 ) cps.println();
      }
      cps.println();
   }
//----------------------------------------------------------------   
} // end class Hw17
//////////////////////////////////////////////////////////////////
class CPS extends PrintStream
{	
   private int ColumnWidth;
//----------------------------------------------------------------
   public CPS (String filename) throws Exception
   {
      super(new FileOutputStream(new File (filename)));
      ColumnWidth = 8;
   }
//----------------------------------------------------------------
   public void print(String str)
   {
      for (int i = str.length(); i < ColumnWidth; i++)
         str += " ";
      super.print(str);	
      if (str.length() > ColumnWidth)
      {
         for (int j = 0; j < ColumnWidth; j++)
            super.print("*");
      }
   }
//----------------------------------------------------------------
   public void print(int nmbr)
   {
      print(nmbr + "");
   }
//----------------------------------------------------------------
   public void print(char ch)
   {
      print(ch + "");
   }
//----------------------------------------------------------------
   public void print(double nmbr)
   {
      print(nmbr + "");
   }
//----------------------------------------------------------------
   public void println(String str)
   {
      print(str);
      super.print("\n");
   }
//----------------------------------------------------------------
   public void println(int nmbr)
   {
      println(nmbr + "");
   }
//----------------------------------------------------------------
   public void println(char ch)
   {
      println(ch + "");
   }
//----------------------------------------------------------------
   public void println(double nmbr)
   {
      println(nmbr + "");
   }
//----------------------------------------------------------------
   public void setColumnWidth (int w)
   {
      ColumnWidth = w;
      if (w < 0)
         System.out.println("Column width must be 0 or greater.");
   }
//----------------------------------------------------------------
} // end class CPS
//////////////////////////////////////////////////////////////////
