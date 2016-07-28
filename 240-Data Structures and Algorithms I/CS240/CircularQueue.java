// Written by Barry Soroka
//
// Implement Queue<E> using a circular queue in an array.
//
import java.io.*;
import java.util.Scanner;
////////////////////////////////////////////////////////////////////////////////
public class CircularQueue <E> implements Queue <E>
{
   private E[] a;
   private int n;
   private int head;
   private int tail;
//------------------------------------------------------------------------------
   public CircularQueue()
   {
      final int INITIAL_SIZE = 3;
      a = (E[]) new Object[INITIAL_SIZE];
      n = 0;
   }
//------------------------------------------------------------------------------
   public void enqueue ( E element )
   {
      if ( n == a.length ) throw new Error("Queue full!");

      if ( n == 0 )
      {
         head = 0;
         tail = 0;
      }
      else
         tail = (tail+1)%a.length;

      a[tail] = element;
      n++;
   }
//------------------------------------------------------------------------------
   public E dequeue()
   {
      if ( isEmpty() ) throw new Error("Queue is empty!");

      E result = a[head];
      a[head] = null;
      head = (head+1)%a.length;
      n--;
      return result;
   }
//------------------------------------------------------------------------------
   public E peek()
   {
      if ( isEmpty() ) throw new Error("Queue is empty!");
      return a[head];      
   }
//------------------------------------------------------------------------------
   public boolean isEmpty() { return n == 0; }
//------------------------------------------------------------------------------
   public int size() { return n; }
//------------------------------------------------------------------------------
   public String toString()
   {
      String result = "";
      int current = head;
      for ( int i = 0 ; i < n ; i++ )
      {
         result += (a[current] + " ");
         current = (current+1)%a.length;
      }
      return result;
   }
//------------------------------------------------------------------------------
} // end class CircularQueue
////////////////////////////////////////////////////////////////////////////////


