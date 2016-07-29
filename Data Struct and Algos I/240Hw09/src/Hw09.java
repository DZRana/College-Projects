// Written by Danzel Rana
//
// Solves CS 240, Spring 2013, Homework 9.
//
import java.util.*;
import java.io.*;
////////////////////////////////////////////////////////////////////////////////
class Hw09
{
//------------------------------------------------------------------------------
	public static void main (String [] args) throws Exception
	{
		double load = 0;
		double avgColl = 0;
		int maxColl = 0;
		int size = 0;
		
		Scanner sc1 = new Scanner(new File("words1000.txt"));
		size = 1009;
		HashTable ht1 = new HashTable<String, String>(size);
		while (sc1.hasNext())
		{
			ht1.put(sc1.next(), "test");
		}
		sc1.close();
		load = 1000 / (double)size;
		avgColl = ht1.avgColl/1000.0;
		maxColl = ht1.maxColl;
		System.out.print("size of hashtable      load factor      avg # of collisions" +
				           "      max # of collisions\n");
		System.out.printf("%10d%20.3f%20.2f%24d\n", size,load,avgColl,maxColl);
		
		Scanner sc2 = new Scanner(new File("words1000.txt"));
		load = 0;
		size = 1259;
		HashTable ht2 = new HashTable<String, String>(size);
		while (sc2.hasNext())
		{
			ht2.put(sc2.next(), "test");
		}
		sc2.close();
		load = 1000 / (double)size;
		avgColl = ht2.avgColl/1000.0;
		maxColl = ht2.maxColl;
		System.out.printf("%10d%20.3f%20.2f%24d\n", size,load,avgColl,maxColl);
		
		Scanner sc3 = new Scanner(new File("words1000.txt"));
		load = 0;
		size = 1511;
		HashTable ht3 = new HashTable<String, String>(size);
		while (sc3.hasNext())
		{
			ht3.put(sc3.next(), "test");
		}
		sc3.close();
		load = 1000 / (double)size;
		avgColl = ht3.avgColl/1000.0;
		maxColl = ht3.maxColl;
		System.out.printf("%10d%20.3f%20.2f%24d\n", size,load,avgColl,maxColl);
		
		Scanner sc4 = new Scanner(new File("words1000.txt"));
		load = 0;
		size = 2003;
		HashTable ht4 = new HashTable<String, String>(size);
		while (sc4.hasNext())
		{
			ht4.put(sc4.next(), "test");
		}
		sc4.close();
		load = 1000 / (double)size;
		avgColl = ht4.avgColl/1000.0;
		maxColl = ht4.maxColl;
		System.out.printf("%10d%20.3f%20.2f%24d\n", size,load,avgColl,maxColl);
		
		Scanner sc5 = new Scanner(new File("words1000.txt"));
		load = 0;
		size = 3001;
		HashTable ht5 = new HashTable<String, String>(size);
		while (sc5.hasNext())
		{
			ht5.put(sc5.next(), "test");
		}
		sc5.close();
		load = 1000 / (double)size;
		avgColl = ht5.avgColl/1000.0;
		maxColl = ht5.maxColl;
		System.out.printf("%10d%20.3f%20.2f%24d\n", size,load,avgColl,maxColl);
		
		Scanner sc6 = new Scanner(new File("words1000.txt"));
		load = 0;
		size = 4001;
		HashTable ht6 = new HashTable<String, String>(size);
		while (sc6.hasNext())
		{
			ht6.put(sc6.next(), "test");
		}
		sc6.close();
		load = 1000 / (double)size;
		avgColl = ht6.avgColl/1000.0;
		maxColl = ht6.maxColl;
		System.out.printf("%10d%20.3f%20.2f%24d\n", size,load,avgColl,maxColl);
   }
//------------------------------------------------------------------------------
} // end class Hw09
////////////////////////////////////////////////////////////////////////////////