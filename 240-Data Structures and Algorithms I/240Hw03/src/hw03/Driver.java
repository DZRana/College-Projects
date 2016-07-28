package hw03;

//Written by Barry Soroka
//
//Driver for CS 240, Spring 2009, Homework 3.
//
import java.io.*;
import java.util.Scanner;
////////////////////////////////////////////////////////////////////////////////
class Hw03
{
//------------------------------------------------------------------------------
public static void main ( String [] args ) throws Exception
{
Memory m = new Memory();
requireIntEquality("memory starts with 0 elements",m.size(),0);

requireNull("ed's not there",m.get("ed"));
m.put("alan","engineer");
m.put("linda","homemaker");
requireStringEquality("alan is an engineer",m.get("alan"),"engineer");
requireStringEquality("linda is a homemaker",m.get("linda"),"homemaker");
requireNull("ed is still not there",m.get("ed"));
requireIntEquality("now remembering 2 things",m.size(),2);
m.put("james","manager");
requireStringEquality("james is a manager",m.get("james"),"manager");
m.put("lisa","teacher");
requireStringEquality("lisa is a teacher",m.get("lisa"),"teacher");
m.put("ed","programmer");
requireStringEquality("ed is a programmer",m.get("ed"),"programmer");
requireIntEquality("now remembering 5 things",m.size(),5);
m.put("james","pitcher");
requireStringEquality("james is now a pitcher",m.get("james"),"pitcher");
requireIntEquality("still remembering 5 things",m.size(),5);
m.remove("linda");
requireIntEquality("now remembering 4 things",m.size(),4);
requireNull("linda is gone from memory",m.get("linda"));

// Add a lot of items
final int MAX = 1000;
for ( int i = 0 ; i < MAX ; i++ ) m.put("k"+i,"v"+i);
requireIntEquality("now remembering " + (MAX+4) + " things",
                  m.size(),MAX+4); 
// Check all items
for ( int i = MAX-1 ; i >= 0 ; i-- )
{
  String v = m.get("k"+i);
  if ( ! m.get("k"+i).equals("v"+i) )
     System.out.println("wrong value stored for key " + i + " : FAILS"); 
}
// Modify an item
m.put("k33","foo");
requireStringEquality("k33 is foo",m.get("k33"),"foo");

m.clear();
requireIntEquality("memory has been cleared",m.size(),0);
}
//------------------------------------------------------------------------------
private static void requireNull ( String label, Object value )
{
System.out.print(label + " : ");
System.out.println( value == null ? "passes" : "FAILS" );
}
//------------------------------------------------------------------------------
private static void requireTrue ( String label, boolean value )
{
System.out.print(label + " : ");
System.out.println( value ? "passes" : "FAILS" );
}
//------------------------------------------------------------------------------
private static void requireFalse ( String label, boolean value )
{
System.out.print(label + " : ");
System.out.println( value ? "FAILS" : "passes" );
}
//------------------------------------------------------------------------------
private static void requireIntEquality ( String label, int arg1, int arg2 )
{
System.out.print(label + " : ");
System.out.println( arg1 == arg2 ? "passes" : "FAILS" );
}
//------------------------------------------------------------------------------
private static void requireStringEquality ( String label, 
                                        String arg1, String arg2 )
{
boolean result;
if      ( arg1 == null && arg2 == null ) result = true;
else if ( arg1 == null || arg2 == null ) result = false;
else                                     result = arg1.equals(arg2);

System.out.print(label + " : ");
System.out.println( result ? "passes" : "FAILS" );
}
//------------------------------------------------------------------------------
} // end class Hw03
////////////////////////////////////////////////////////////////////////////////