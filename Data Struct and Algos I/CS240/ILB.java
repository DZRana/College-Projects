// Written by Barry Soroka
//
// Code for the IntLinkedBag example.
// Beginning very small in Spring 2013.
//
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
/////////////////////////////////////////////////////////////////////////
class ILB
{
   private IntNode head;
   private int n;
//-----------------------------------------------------------------------
   public ILB()
   {
      head = null;
      n = 0;
   }
//-----------------------------------------------------------------------
   public void add ( int element )
   {
      head = new IntNode(element,head);
      n++;
   }
//-----------------------------------------------------------------------
   public void add ( int ... elements )
   {
      for ( int i : elements ) add(i);
   }
//-----------------------------------------------------------------------
   public void add ( ILB that )
   {
      for ( IntNode c = that.head ; c != null ; c = c.getLink() )
         this.add(c.getData());
   }
//-----------------------------------------------------------------------
   public void clear()
   {
      head = null;
      n = 0;
   }
//-----------------------------------------------------------------------
   public boolean contains ( int target )
   {
      for ( IntNode c = head ; c != null ; c = c.getLink() )
         if ( c.getData() == target ) return true;

      return false;
   }
//-----------------------------------------------------------------------
   public ILB copy()
   {
      ILB out = new ILB();

      for ( IntNode c = head ; c != null ; c = c.getLink() )
         out.add(c.getData());

      return out;
   }
//-----------------------------------------------------------------------
   public int count ( int target )
   {
      int count = 0;

      for ( IntNode c = head ; c != null ; c = c.getLink() )
         if ( c.getData() == target ) count++;

      return count;
   }
//-----------------------------------------------------------------------
   public int countNodes()
   {
      int count = 0;

      for ( IntNode c = head ; c != null ; c = c.getLink() )
         count++;

      return count;
   }
//-----------------------------------------------------------------------
   public boolean equals ( Object o )
   {
      if ( !(o instanceof ILB) ) return false;
      ILB that = (ILB)o;

      if ( this.n != that.n ) return false;

      int[] a = this.toIntArray();
      int[] b = that.toIntArray();

      Arrays.sort(a);
      Arrays.sort(b);

      for ( int i = 0 ; i < a.length ; i++ )
         if ( a[i] != b[i] ) return false;

      return true;
   }
//-----------------------------------------------------------------------
   public boolean inSync() { return n == countNodes(); }
//-----------------------------------------------------------------------
   public void inc()
   {
      for ( IntNode c = head ; c != null ; c = c.getLink() )
         c.setData(c.getData() + 1);
   }
//-----------------------------------------------------------------------
   public boolean isEmpty() { return n == 0; }
//-----------------------------------------------------------------------
//
// print() – prints the bag, one per line
//
   public void print()
   {
      for ( IntNode c = head ; c != null ; c = c.getLink() )
         System.out.println(c.getData());
   } 
//-----------------------------------------------------------------------
   public void remove ( int target )
   {
      if ( head == null )   // empty bag
      {
         return;
      }

      if ( head.getData() == target )   // found at head node
      {
         head = head.getLink();
         n--;
         return;
      }

      IntNode pre = null;
      IntNode cur = head;

      while ( cur != null )
      {
         if ( cur.getData() == target )   // found it
         {
            pre.setLink(cur.getLink());
            n--;
            return;
         }

         pre = cur;
         cur = cur.getLink();
      }
      return;                       // didn't find it
   }
//-----------------------------------------------------------------------
   public int size() { return n; }
//----------------------------------------------------------------------
   public int sum()
   {
      int sum = 0;

      for ( IntNode c = head ; c != null ; c = c.getLink() )
         sum += c.getData();

      return sum;
   }
//----------------------------------------------------------------------
   public int[] toIntArray()
   {
      int[] out = new int[n];
      int i = 0;

      for ( IntNode c = head ; c != null ; c = c.getLink() )
      {
         out[i] = c.getData();
         i++;
      }

      return out;
   }
//-----------------------------------------------------------------------
   public String toString()
   {
      String result = "[ ";

      for ( IntNode c = head ; c != null ; c = c.getLink() )
         result += c.getData() + " ";

      return result + "]";
   }
//-----------------------------------------------------------------------
   public static ILB union ( ILB b1, ILB b2 )
   {
      ILB out = new ILB();
      out.add(b1);
      out.add(b2);
      return out;
   }
//-----------------------------------------------------------------------
} // end class ILB
/////////////////////////////////////////////////////////////////////////

