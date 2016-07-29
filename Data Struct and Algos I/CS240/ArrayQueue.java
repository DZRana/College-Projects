// Written by Barry Soroka
//
// Implement Queue<E> using an array -- non-circular & inefficient.
//
import java.io.*;
import java.util.Scanner;
////////////////////////////////////////////////////////////////////////////////
public class ArrayQueue <E> implements Queue <E>
{
   private E[] a;
   private int n;
//------------------------------------------------------------------------------
   public ArrayQueue()
   {
      final int INITIAL_SIZE = 2;
      a = (E[]) new Object[INITIAL_SIZE];
      n = 0;
   }
//------------------------------------------------------------------------------
   public void enqueue ( E element )
   {
      if ( n == a.length )
      {
         E[] newA = (E[]) new Object[ 2 * a.length + 1 ];
         for ( int i = 0 ; i < n ; i++ ) newA[i] = a[i];
         a = newA;
      }

      a[n] = element;
      n++;    
   }
//------------------------------------------------------------------------------
   public E dequeue() throws Exception
   {
      if ( isEmpty() ) throw new Exception("Queue is empty!");

      E result = a[0];
      for ( int i = 0 ; i < n-1 ; i++ ) a[i] = a[i+1];
      n--;
      return result;
   }
//------------------------------------------------------------------------------
   public E peek() throws Exception
   {
      if ( isEmpty() ) throw new Exception("Queue is empty!");
      return a[0];      
   }
//------------------------------------------------------------------------------
   public boolean isEmpty() { return n == 0; }
//------------------------------------------------------------------------------
   public int size() { return n; }
//------------------------------------------------------------------------------
   public String toString()
   {
      String result = "";
      for ( int i = 0 ; i < n ; i++ ) result += (a[i] + " ");
      return result;
   }
//------------------------------------------------------------------------------
} // end class ArrayQueue
////////////////////////////////////////////////////////////////////////////////

