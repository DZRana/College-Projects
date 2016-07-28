// Written by Barry Soroka
//
// Node containing an int.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
public class IntNode
{
   private int data;
   private IntNode link;   
//-----------------------------------------------------------------------
   public IntNode ( int data, IntNode link )
   {
      this.data = data;
      this.link = link;
   }
//-----------------------------------------------------------------------
   public IntNode getLink() { return link; }
//-----------------------------------------------------------------------
   public int getData() { return data; }
//-----------------------------------------------------------------------
   public void setLink ( IntNode link ) { this.link = link; }
//-----------------------------------------------------------------------
   public void setData ( int data ) { this.data = data; }
//-----------------------------------------------------------------------
   public static void print ( IntNode head )
   {
      System.out.print("[ ");

      IntNode current = head;

      while ( current != null )
      {
         System.out.print(current.getData() + " ");
         current = current.getLink();
      }

      System.out.print("]");
   }
//-----------------------------------------------------------------------
} // end class IntNode
/////////////////////////////////////////////////////////////////////////

