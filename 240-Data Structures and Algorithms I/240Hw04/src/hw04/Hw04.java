package hw04;

//Modified by Danzel Rana
//Written by Barry Soroka
//
//Solves CS 240, Spring 2013, Homework 04.
//
import java.io.*;
import java.util.*;
/////////////////////////////////////////////////////////////////////////
class IAS
{
   private int[] a;
   private int n;
//--------------------------------------------------------------------
   public IAS()
   {
      final int INITIAL_CAPACITY = 2;
      a = new int[INITIAL_CAPACITY];
      n = 0;
   }
//--------------------------------------------------------------------
   public IAS ( int capacity )
   {
      if ( capacity < 0 )
         throw new IllegalArgumentException
            ("Illegal capacity requested: " + capacity);

      a = new int[capacity];
      n = 0;
   }
//--------------------------------------------------------------------
   public void add ( int element )
   {
      if (contains(element)) return;
      if ( n == a.length )
      {
      	int[] newA = new int [a.length*2+1];
         System.arraycopy(a,0,newA,0,n);
         a = newA;
      }    
      a[n] = element;
      n++;
   }
//--------------------------------------------------------------------
   public void add ( int ... elements )
   {
      for (int i:elements) add(i);
   }
//--------------------------------------------------------------------
   public void clear() 
   {
   	a = new int[2];
   	n = 0; 
   }
//--------------------------------------------------------------------
   public boolean contains ( int target )
   {
      for ( int i = 0 ; i < n ; i++ )
         if ( a[i] == target ) return true;
      return false;
   }
//--------------------------------------------------------------------
   public IAS copy()
   {
   	IAS result = new IAS(this.n);
   	System.arraycopy(this.a,0,result.a,0,this.n);
   	result.n = this.n;
   	return result;
   }
//--------------------------------------------------------------------
   public boolean equals ( Object o )
   {
      if ( !(o instanceof IAS) ) return false;
      IAS that = (IAS)o;

      if ( this.n != that.n ) return false;

      Arrays.sort(this.a,0,this.n);
      Arrays.sort(that.a,0,that.n);

      for ( int i = 0 ; i < this.n ; i++ )
         if ( this.a[i] != that.a[i] ) return false;
      
      return true;
   }
//--------------------------------------------------------------------
   public boolean isEmpty() { return n == 0; }
//--------------------------------------------------------------------
   public void remove ( int target )
   {
      for ( int i = 0 ; i < n ; i++ )
      {
         if ( a[i] == target )
         {
            a[i] = a[n-1];
            n--;
            break;
         }
      }
   }
//--------------------------------------------------------------------
   public int size() { return n; }
//--------------------------------------------------------------------
   public String toString()
   {
      String result = "[ ";
      for ( int i = 0 ; i < n ; i++ ) result += a[i] + " ";
      return result + "]";
   }
//--------------------------------------------------------------------
   public static IAS union (IAS ias1, IAS ias2)
   {
   	IAS result = ias1.copy();
   	System.arraycopy(ias1.a,0,result.a,0,ias1.n);
      System.arraycopy(ias2.a,0,result.a,ias1.n,ias2.n);
      return result;
   }
//--------------------------------------------------------------------
} // end class IAB
//////////////////////////////////////////////////////////////////////
