// Written by Barry Soroka
//
// Implements a Stack ADT using an array.
//
import java.io.*;
////////////////////////////////////////////////////////////////////////////////
public class ArrayStack <E> implements Stack <E>
{
   private E[] a;
   private int n;
//------------------------------------------------------------------------------
   public ArrayStack()
   {
      final int INITIAL_SIZE = 2;
      n = 0;
      a = (E[]) new Object[INITIAL_SIZE];
   }
//------------------------------------------------------------------------------
   public boolean isEmpty() { return n == 0; }
//------------------------------------------------------------------------------
   public E peek()
   {
      if ( n == 0 ) throw new Error("Stack is empty!");
      return a[n-1];
   }
//------------------------------------------------------------------------------
   public E pop()
   {
      if ( n == 0 ) throw new Error("Stack is empty!");

      E result = a[n-1];
      n--;
      return result;
   }
//------------------------------------------------------------------------------
   public void push ( E element )
   {
      if ( n == a.length )
      {
         E[] newA = (E[]) new Object [ 1 + 2 * a.length ];
         System.arraycopy(a, 0, newA, 0, n );
         a = newA;
      }

      a[n] = element;
      n++;
   }
//------------------------------------------------------------------------------
   public int size() { return n; }
//------------------------------------------------------------------------------
} // end class ArrayStack
////////////////////////////////////////////////////////////////////////////////

