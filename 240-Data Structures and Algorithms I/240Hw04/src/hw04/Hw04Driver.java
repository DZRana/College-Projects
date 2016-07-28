package hw04;

//Written by Barry Soroka
//
//Tests IAS -- IntArraySet.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class TestIAS
{
//-----------------------------------------------------------------------
public static void main ( String [] args ) throws Exception
{
   testEasy();
   testContains();
   testAddAndContains();
   testClear();
   testEquals();
   testCopy();
   testRemove();
}
//-----------------------------------------------------------------------
//
//Tests constructor, size, isEmpty.
//
public static void testEasy()
{
   System.out.println();
   System.out.println("============================================");
   System.out.println("=== testEasy ===============================");
   System.out.println("============================================");

   IAS s = new IAS();
   System.out.println();
   System.out.println("size = " + s.size() + "   s = " + s);
   System.out.println("isEmpty = " + s.isEmpty());
}
//-----------------------------------------------------------------------
public static void testContains()
{
   System.out.println();
   System.out.println("============================================");
   System.out.println("=== testContains ===========================");
   System.out.println("============================================");

   IAS s = new IAS();
   testContains1(s,3);
}
//-----------------------------------------------------------------------
public static void testContains1 ( IAS s, int n )
{
   System.out.println();
   System.out.println("size = " + s.size() + "   s = " + s);
   System.out.println("contains " + n + "? " + s.contains(n));
}
//-----------------------------------------------------------------------
public static void testAddAndContains()
{
   System.out.println();
   System.out.println("============================================");
   System.out.println("=== testAddAndContains =====================");
   System.out.println("============================================");

   IAS s = new IAS();
   testContains1(s,3);

   s = new IAS();
   System.out.println("Filling s using add(int...) ...");
   s.add(2,7,1);
   s.add(4);
   s.add(3,1);
   System.out.println("s should be { 3 1 4 2 7 }");
   System.out.println("size = " + s.size() + "   s = " + s + "\n");

   s = new IAS();
   s.add(1,2,3);
   System.out.println("s should be { 3 2 1 }");
   System.out.println("size = " + s.size() + "   s = " + s + "\n");
   s.add(4,1,5,2,2);
   System.out.println("s should be { 5 4 3 2 1 }");
   System.out.println("size = " + s.size() + "   s = " + s);

   s.add(6);
   testAddAndContains1(s,3);
   testAddAndContains1(s,6);
   testAddAndContains1(s,7);

   s = new IAS();
   s.add(2,7,1);
   testAddAndContains1(s,2);
   testAddAndContains1(s,7);
   testAddAndContains1(s,1);
   testAddAndContains1(s,0);
}
//-----------------------------------------------------------------------
public static void testAddAndContains1 ( IAS s, int n )
{
   System.out.println();
   System.out.println("size = " + s.size() + "   s = " + s);
   System.out.println("contains " + n + "? " + s.contains(n));
}
//-----------------------------------------------------------------------
public static void testClear()
{
   System.out.println();
   System.out.println("============================================");
   System.out.println("=== testClear ==============================");
   System.out.println("============================================");

   System.out.println("\nStarting with a new IAS ...");
   IAS s = new IAS();
   testClear1(s);
   System.out.println("\nAdding 2,7,1 ...");
   s.add(2,7,1);
   testClear1(s);
   System.out.println("\ns.clear() ...");
   s.clear();
   testClear1(s);
}
//-----------------------------------------------------------------------
public static void testClear1 ( IAS s )
{
   System.out.println("size = " + s.size() + "   s = " + s);
}
//-----------------------------------------------------------------------
public static void testEquals()
{
   System.out.println();
   System.out.println("============================================");
   System.out.println("=== testEquals =============================");
   System.out.println("============================================");

   IAS s0a = new IAS();
   IAS s0b = new IAS();
   IAS s1a = new IAS(); s1a.add(2);
   IAS s1b = new IAS(); s1b.add(3);
   IAS s3a = new IAS(); s3a.add(2); s3a.add(7); s3a.add(1);
   IAS s3b = new IAS(); s3b.add(7); s3b.add(1); s3b.add(2);
   IAS s3c = new IAS(); s3c.add(2); s3c.add(7); s3c.add(4);
   IAS s4a = new IAS(); s4a.add(3,1,4,1,5);
   IAS s4b = new IAS(); s4b.add(5,1,4,1,3);

   testEquals1(s0a,s0a);
   testEquals1(s0a,s0b);
   testEquals1(s1a,s0a);
   testEquals1(s1a,s1a);
   testEquals1(s1a,s1b);
   testEquals1(s3a,s3a);
   testEquals1(s3a,s3b);
   testEquals1(s3a,s3c);
   testEquals1(s1a,s3a);
   testEquals1(s0a,s3a);
   testEquals1(s3a,s4a);
   testEquals1(s4a,s4b);
}
//-----------------------------------------------------------------------
public static void testEquals1 ( IAS s1, IAS s2 )
{
   System.out.println();
   System.out.println("s1 = " + s1);
   System.out.println("s2 = " + s2);
   System.out.println("s1.equals(s2) --> " + s1.equals(s2));
   System.out.println("s2.equals(s1) --> " + s2.equals(s1));
}
//-----------------------------------------------------------------------
public static void testCopy()
{
   System.out.println();
   System.out.println("============================================");
   System.out.println("=== testCopy ===============================");
   System.out.println("============================================");

   IAS s;

   s = new IAS();
   testCopy1(s);   // test an empty list

   s = new IAS();
   s.add(2);
   testCopy1(s);   // test a 1-element list

   s = new IAS();
   s.add(2,7,1,3);
   testCopy1(s);   // test a multi-element list
}
//-----------------------------------------------------------------------
public static void testCopy1 ( IAS s1 )
{
   System.out.println();
   IAS s2 = s1.copy();
   System.out.println("size = " + s1.size() + "   s1 = " + s1);
   System.out.println("size = " + s2.size() + "   s2 = " + s2);
   System.out.println("s1.equals(s2) -> " + s1.equals(s2));
   System.out.println("s1.add(5)...");
   s1.add(5);
   System.out.println("size = " + s1.size() + "   s1 = " + s1);
   System.out.println("size = " + s2.size() + "   s2 = " + s2);
   System.out.println("s2.add(8)...");
   s2.add(8);
   System.out.println("size = " + s1.size() + "   s1 = " + s1);
   System.out.println("size = " + s2.size() + "   s2 = " + s2);
}
//-----------------------------------------------------------------------
public static void testRemove()
{
   System.out.println();
   System.out.println("============================================");
   System.out.println("=== testRemove =============================");
   System.out.println("============================================");

   IAS s;

   s = new IAS();
   testRemove1(s,3);

   s = new IAS();
   s.add(2);
   testRemove1(s,3);
   testRemove1(s,2);
   testRemove1(s,3);

   s = new IAS();
   s.add(2,7,1,8,2,8);
   testRemove1(s,3);
   testRemove1(s,2);
   testRemove1(s,7);
   testRemove1(s,1);
   testRemove1(s,8);
   testRemove1(s,1);
}
//-----------------------------------------------------------------------
public static void testRemove1 ( IAS s, int n )
{
   System.out.println();
   System.out.println("size = " + s.size() + "   s = " + s);
   System.out.println("s.remove(" + n + ")...");
   s.remove(n);
   System.out.println("size = " + s.size() + "   s = " + s);
}
//-----------------------------------------------------------------------
} // end class TestIAS
/////////////////////////////////////////////////////////////////////////
