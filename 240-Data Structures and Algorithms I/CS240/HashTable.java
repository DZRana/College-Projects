// Written by Barry Soroka
//
// Adapted from Michael Main's program
//
// HashTable<K,V>
//
import java.io.*;
////////////////////////////////////////////////////////////////////////////////
class HashTable<K,V>
{
   private int n;
   private K[] keys;
   private V[] values;
   private boolean[] hasBeenUsed;

   private boolean debug;
//------------------------------------------------------------------------------
   public HashTable ( int capacity )
   {
      debug = false;

      if ( capacity < 0 )
         throw new IllegalArgumentException("Illegal capacity: " + capacity);

      keys = (K[]) new Object[capacity];
      values = (V[]) new Object[capacity];
      hasBeenUsed = new boolean[capacity];
      n = 0;

      for ( int i = 0 ; i < capacity ; i++ ) hasBeenUsed[i] = false;
   }
//------------------------------------------------------------------------------
   private int findIndex ( K key )
   {
      int count = 0;
      int i = hash(key);

      while ( ( count < keys.length ) && hasBeenUsed[i] )
      {
         if ( key.equals(keys[i]) ) return i;
         count++;
         i = nextIndex(i);
      }

      return -1;
   }
//------------------------------------------------------------------------------
   public V get ( K key )
   {
      int index = findIndex(key);
      if ( index == -1 ) return null;
      else return values[index];
   }
//------------------------------------------------------------------------------
   private int hash ( K key )
   {
      int result = Math.abs(key.hashCode()) % keys.length;
      if ( debug ) System.out.println("hash: " + key + " --> " + result);
      return result;
   }
//------------------------------------------------------------------------------
   private int nextIndex ( int i ) { return (i+1) % values.length; }
//------------------------------------------------------------------------------
   public void put ( K key, V value ) throws Exception
   {
      int index = findIndex(key);
      if ( debug ) System.out.println("findIndex returned " + index);

      if ( index != -1 )  // key is in the table
      {
         values[index] = value;
         return;
      }

      if ( n == values.length )   // table is full
      {
         rehash();
      }
                                 // key is not in the table
      index = hash(key);
      while ( keys[index] != null ) index = nextIndex(index);
      if ( debug ) 
         System.out.println("putting " + key + " into index " + index);
      keys[index] = key;
      values[index] = value;
      hasBeenUsed[index] = true;
      n++;
   }
//------------------------------------------------------------------------------
//
// private void rehash()
//
// Creates a larger table & hashes the current entries into the new table.
//
   private void rehash()
   {
      K oldKeys[] = keys;
      V oldValues[] = values;

      int newCapacity = 1 + 2 * oldKeys.length;

      keys = (K[]) new Object[newCapacity];
      values = (V[]) new Object[newCapacity];
      hasBeenUsed = new boolean[newCapacity];
      n = 0;

      for ( int i = 0 ; i < oldKeys.length ; i++ )
      {
         K key = oldKeys[i];

         int index = hash(key);

         while ( keys[index] != null ) index = nextIndex(index);
         if ( debug ) 
            System.out.println("putting " + key + " into index " + index);

         keys[index] = key;
         values[index] = oldValues[i];
         hasBeenUsed[index] = true;
         n++;
      }
      if ( debug ) System.out.println("--- rehash performed successfully ---");
   }
//------------------------------------------------------------------------------
   public void remove ( K key )
   {
      int index = findIndex(key);

      if ( index != -1 )
      {
         keys[index] = null;
         values[index] = null;
         n--;
      }
   }
//------------------------------------------------------------------------------
   public void setDebug ( boolean newStatus ) { debug = newStatus; }
//------------------------------------------------------------------------------
   public int size() { return n; }
//------------------------------------------------------------------------------
   public String toString()
   {
      String result = "n : " + n + "\n";
      for ( int i = 0 ; i < keys.length ; i++ )
         result += i + " " + keys[i] + " : " + values[i] + " " 
                 + hasBeenUsed[i] + "\n" ;
      return result;
   }
//------------------------------------------------------------------------------
} // end class HashTable
////////////////////////////////////////////////////////////////////////////////
