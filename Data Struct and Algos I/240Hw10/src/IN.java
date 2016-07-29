// Modified by Danzel Rana
// Written by Barry Soroka
//
// Solves CS 240, Spring 2013, Homework 10.
//
// IN -- Node containing an int -- aimed at teaching recursion.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
public class IN
{
   private int data;
   private IN link;   
//-----------------------------------------------------------------------
   public static IN doubler ( IN head )
   {
   	if ( head == null ) return null;
   	return new IN (2*head.getData(), doubler(head.getLink()));
   }
//-----------------------------------------------------------------------
   public static IN copy ( IN head )
   {
      if ( head == null ) return null;
      return new IN ( head.getData(), copy(head.getLink()) );
   }
//-----------------------------------------------------------------------
   public static void increment ( IN head )
   {
      if ( head == null ) return;
      head.setData(1 + head.getData());
      increment(head.getLink());
   }
//-----------------------------------------------------------------------
   public static void print ( IN head )
   {
      if ( head == null ) return;
      System.out.println(head.getData());
      print(head.getLink());
   }
//-----------------------------------------------------------------------
   public static void printR ( IN head )
   {
      if ( head == null ) return;
      printR(head.getLink());
      System.out.println(head.getData());
   }
//-----------------------------------------------------------------------
   public static int length ( IN head )
   {
      System.out.println("length called for " + head);
      if ( head == null ) return 0;
      return 1 + length(head.getLink());
   }
//-----------------------------------------------------------------------
   public static int sum ( IN head )
   {
      if ( head == null ) return 0;
      return head.getData() + sum(head.getLink());
/*
      int sum = 0;
      for ( IN c = head ; c != null ; c = c.getLink() )
         sum += c.getData();
      return sum;
*/
   }
//-----------------------------------------------------------------------
   public IN ( int data, IN link )
   {
      this.data = data;
      this.link = link;
   }
//-----------------------------------------------------------------------
   public IN getLink() { return link; }
//-----------------------------------------------------------------------
   public int getData() { return data; }
//-----------------------------------------------------------------------
   public void setLink ( IN link ) { this.link = link; }
//-----------------------------------------------------------------------
   public void setData ( int data ) { this.data = data; }
//-----------------------------------------------------------------------
   public static IN makeChain ( int ... args )
   {
      IN last = null;

      for ( int i = args.length-1 ; i >= 0 ; i-- )
      {
         last = new IN ( args[i], last );
      }

      return last;
   }
//-----------------------------------------------------------------------
   public String toString()
   {
      String result = "[ ";

      for ( IN c = this ; c != null ; c = c.getLink() )
         result += c.getData() + " ";

      return result + "]";
   }
//-----------------------------------------------------------------------
} // end class IN
/////////////////////////////////////////////////////////////////////////