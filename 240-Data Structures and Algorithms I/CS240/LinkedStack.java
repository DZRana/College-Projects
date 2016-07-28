// Written by Barry Soroka
//
// Implements a Stack ADT using a linked list.
//
import java.io.*;
////////////////////////////////////////////////////////////////////////////////
public class LinkedStack <E> implements Stack <E>
{
   private Node<E> head;
   private int n;
//------------------------------------------------------------------------------
   public LinkedStack()
   {
      head = null;
      n = 0;
   }
//------------------------------------------------------------------------------
   public boolean isEmpty() { return head == null; }
//------------------------------------------------------------------------------
   public E peek()
   {
      if ( head == null ) throw new Error("Stack is empty!");

      return head.getData();
   }
//------------------------------------------------------------------------------
   public E pop()
   {
      if ( head == null ) throw new Error("Stack is empty!");

      E result = head.getData();
      head = head.getLink();
      n--;
      return result;
   }
//------------------------------------------------------------------------------
   public void push ( E element )
   {
      head = new Node<E> ( element, head );
      n++;
   }
//------------------------------------------------------------------------------
   public int size() { return n; }
//------------------------------------------------------------------------------
} // end class LinkedStack
////////////////////////////////////////////////////////////////////////////////

