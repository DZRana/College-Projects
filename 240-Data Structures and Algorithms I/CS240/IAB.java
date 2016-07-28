// Written by Barry Soroka
//
// IAB -- IntArrayBag -- an implementation of a bag of integers
//                       using an array
//
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
//////////////////////////////////////////////////////////////////////
class IAB
{
   private int[] a;
   private int n;
//--------------------------------------------------------------------
   public IAB()
   {
      final int INITIAL_CAPACITY = 10;
      a = new int[INITIAL_CAPACITY];
      n = 0;
   }
//--------------------------------------------------------------------
   public IAB ( int capacity )
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
      if ( n == a.length ) ensureCapacity( 2*n + 1 );
      a[n] = element;
      n++;
   }
//--------------------------------------------------------------------
   public void add ( int ... elements )
   {
      if ( n + elements.length > a.length )
         ensureCapacity( 2 * ( n + elements.length ) );
      System.arraycopy(elements,0,a,n,elements.length);
      n += elements.length;
   }
//--------------------------------------------------------------------
   public void add ( IAB that )
   {
      ensureCapacity(this.n + that.n);
      System.arraycopy(that.a,0,this.a,this.n,that.n);
      this.n += that.n;
   }
//--------------------------------------------------------------------
   public void clear() { n = 0; }
//--------------------------------------------------------------------
   public int count ( int target )
   {
      int count = 0;

      for ( int i = 0 ; i < n ; i++ )
         if ( a[i] == target )
            count++;

      return count;
   }
//--------------------------------------------------------------------
   private void ensureCapacity ( int minimum )
   {
      if ( a.length < minimum )
      {
         int[] newA = new int[minimum];
         System.arraycopy(a,0,newA,0,n);
         a = newA;
      }
   }
//--------------------------------------------------------------------
   public boolean equals ( Object o )
   {
      if ( !(o instanceof IAB) ) return false;
      IAB that = (IAB)o;

      if ( this.n != that.n ) return false;

      Arrays.sort(this.a,0,this.n);
      Arrays.sort(that.a,0,that.n);

      for ( int i = 0 ; i < this.n ; i++ )
         if ( this.a[i] != that.a[i] ) return false;

      return true;
   }
//--------------------------------------------------------------------
   public int getCapacity() { return a.length; }
//--------------------------------------------------------------------
   public void remove ( int target )
   {
      for ( int i = 0 ; i < n ; i++ )
         if ( a[i] == target )
         {
            a[i] = a[n-1];
            n--;
            break;
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
   public void trim()
   {
      if ( n != a.length )
      {
         int[] newA = new int[n];
         System.arraycopy(a,0,newA,0,n);
         a = newA;
      }
   }
//--------------------------------------------------------------------
} // end class IAB
//////////////////////////////////////////////////////////////////////

