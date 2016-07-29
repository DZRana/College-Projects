//Modified by Danzel Rana
//Written by Barry Soroka
//
//Solves CS 240, Spring 2013, Homework 05.
//
import java.io.*;
import java.util.*;
/////////////////////////////////////////////////////////////////////////
class ILS
{
   private IntNode head;
   private int n;
//------------------------------------------------------------------------------
   public ILS()
   {
      head = null;
      n = 0;
   }
//------------------------------------------------------------------------------
   public void add ( int element )
   {
   	for ( IntNode c = head ; c != null ; c = c.getLink() )
   		if ( c.getData() == element) return;
      head = new IntNode(element,head);
      n++;
   }
//------------------------------------------------------------------------------
   public void add ( int ... elements )
   {
      for ( int i : elements ) add(i);
   }
//------------------------------------------------------------------------------
   public void clear()
   {
      head = null;
      n = 0;
   }
//------------------------------------------------------------------------------
   public boolean contains ( int target )
   {
      for ( IntNode c = head ; c != null ; c = c.getLink() )
         if ( c.getData() == target ) return true;

      return false;
   }
//------------------------------------------------------------------------------
   public ILS copy()
   {
   	ILS result = new ILS();
   	for ( IntNode c = head ; c != null ; c = c.getLink() )
   		result.add(c.getData());
   	return result;
   }
//------------------------------------------------------------------------------
   public boolean equals ( Object o )
   {
      if ( !(o instanceof ILS) ) return false;
      ILS that = (ILS)o;

      if ( this.n != that.n ) return false;

      int[] a = this.toIntArray();
      int[] b = that.toIntArray();

      Arrays.sort(a);
      Arrays.sort(b);

      for ( int i = 0 ; i < a.length ; i++ )
         if ( a[i] != b[i] ) return false;

      return true;
   }
//------------------------------------------------------------------------------
   public boolean isEmpty() { return n == 0; }
//------------------------------------------------------------------------------
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
//------------------------------------------------------------------------------
   public int size() { return n; }
//------------------------------------------------------------------------------
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
//------------------------------------------------------------------------------
   public String toString()
   {
      String result = "{ ";

      for ( IntNode c = head ; c != null ; c = c.getLink() )
         result += c.getData() + " ";

      return result + "}";
   }
//------------------------------------------------------------------------------
   public static ILS union (ILS ils1, ILS ils2)
   {
   	ILS result = new ILS();
   	result.add(ils1.toIntArray());
   	result.add(ils2.toIntArray());
   	return result;
   }
//------------------------------------------------------------------------------
} // end class ILS
////////////////////////////////////////////////////////////////////////////////
