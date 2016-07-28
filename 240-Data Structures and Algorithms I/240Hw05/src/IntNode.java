// Written by Barry Soroka
//
// Node containing an int.
//
import java.io.*;
import java.util.Scanner;
////////////////////////////////////////////////////////////////////////////////
public class IntNode
{
   private int data;
   private IntNode link;   
//------------------------------------------------------------------------------
   public IntNode ( int data, IntNode link )
   {
      this.data = data;
      this.link = link;
   }
//------------------------------------------------------------------------------
   public IntNode getLink() { return link; }
//------------------------------------------------------------------------------
   public int getData() { return data; }
//------------------------------------------------------------------------------
   public void setLink ( IntNode link ) { this.link = link; }
//------------------------------------------------------------------------------
   public void setData ( int data ) { this.data = data; }
//------------------------------------------------------------------------------
} // end class IntNode
////////////////////////////////////////////////////////////////////////////////
