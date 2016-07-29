// Written by Barry Soroka
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
   public static boolean someOdd ( IN head )
   {
      if ( head == null ) return false;
      if ( head.getData() % 2 != 0 ) return true;
      return someOdd(head.getLink());
   }
//-----------------------------------------------------------------------
   public static boolean allOdd ( IN head )
   {
      System.out.println("allodd gets " + head);
      if ( head == null ) return true;
      if ( head.getData() % 2 == 0 ) return false;
      return allOdd(head.getLink());
   }
//-----------------------------------------------------------------------
   public static IN keepOdd ( IN head )
   {
      if ( head == null ) return null;
      if ( head.getData() % 2 == 0 ) return keepOdd(head.getLink());
      return new IN(head.getData(),keepOdd(head.getLink()));
   }
//-----------------------------------------------------------------------
   public static boolean equals ( IN head1, IN head2 )
   {
      if ( head1 == null && head2 == null ) return true;
      if ( head1 == null || head2 == null ) return false;
      if ( head1.getData() != head2.getData() ) return false;
      return equals(head1.getLink(),head2.getLink());
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
