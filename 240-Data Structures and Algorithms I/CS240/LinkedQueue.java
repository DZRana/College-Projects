// Written by Barry Soroka
//
// Implement Queue<E> using a linked list.
//
import java.io.*;
import java.util.Scanner;
////////////////////////////////////////////////////////////////////////////////
public class LinkedQueue <E> implements Queue <E>
{
   private Node<E> head;
   private Node<E> tail;
   private int n;
//------------------------------------------------------------------------------
   public LinkedQueue()
   {
      head = null;
      tail = null;
      n = 0;
   }
//------------------------------------------------------------------------------
   public void enqueue ( E element )
   {
      if ( head == null )   // empty list
      {
         head = new Node<E>(element,null);
         tail = head;
         n = 1;
      }
      else
      {
         Node<E> node = new Node<E>(element,null);
         tail.setLink(node);
         tail = node;
         n++;    
      }
   }
//------------------------------------------------------------------------------
   public E dequeue() throws Exception
   {
      if ( isEmpty() ) throw new Exception("Queue is empty!");
      if ( head == tail ) // one-element list
      {
         E result = head.getData();
         head = null;
         tail = null;
         n = 0;
         return result;
      }
      else
      {
         E result = head.getData();
         head = head.getLink();
         n--;
         return result;
      }
   }
//------------------------------------------------------------------------------
   public E peek() throws Exception
   {
      if ( isEmpty() ) throw new Exception("Queue is empty!");
      return head.getData();      
   }
//------------------------------------------------------------------------------
   public boolean isEmpty() { return n == 0; }
//------------------------------------------------------------------------------
   public int size() { return n; }
//------------------------------------------------------------------------------
   public String toString()
   {
      String result = "";
      Node<E> cursor = head;
      while ( cursor != null )
      {
         result += (cursor.getData() + " ");
         cursor = cursor.getLink();
      }
      return result;
   }
//------------------------------------------------------------------------------
} // end class LinkedQueue
////////////////////////////////////////////////////////////////////////////////

