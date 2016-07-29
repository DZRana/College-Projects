package hw05;

//Modified by Danzel Rana.
//Written by Barry Soroka.
//
//Solves CS 141, Winter 2013, Homework 5.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw05
{
//-----------------------------------------------------------------------
public static void main (String [] args) throws Exception
{
   Scanner kb = new Scanner(System.in);

   System.out.print("\nEnter name of a file of Students: ");
   String filename = kb.nextLine();

   Section sec = new Section(filename);

   System.out.println("\nIn forward order:");
   sec.print(System.out);

   System.out.println("\nThat Section contains " +
                      sec.howMany() +
                      " Student(s).");

   System.out.println("\nThat Section contains " +
                      sec.nPassing() +
                      " passing Student(s).");

   System.out.println("\nThe highest grade is " +
                      sec.highestGrade() + ".");
} 
//-----------------------------------------------------------------------
} // end class Hw05
/////////////////////////////////////////////////////////////////////////
class Section
{
private Student [] a;
private int used;
private static final int INIT_SIZE = 20;
//-----------------------------------------------------------------------
public Section ( String filename ) throws Exception
{
   Scanner sc = new Scanner(new File(filename));

   a = new Student[INIT_SIZE];
   used = 0;

   while ( sc.hasNext() )
   {
      Student s = Student.read(null,sc);

      if ( used == a.length )
      {
         Student[] newA = new Student[2*a.length+1];
         for ( int i = 0 ; i < used ; i++ ) newA[i] = a[i];
         newA[used] = s;
         used++;
         a = newA;
      }
      else
      {
         a[used] = s;
         used++;
      }
   }
}
//-----------------------------------------------------------------------
public void print ( PrintStream ps )
{
   for ( int i = 0 ; i < used ; i++ )
   {
      ps.println(a[i]);
   }
}
//-----------------------------------------------------------------------
public int howMany() { return used; }
//-----------------------------------------------------------------------
public int nPassing()
{
   int cntr=0;
   for(int i = 0; i < used; i++)
   {
      Student marker = a[i];
	  if(marker.getGrade()>=60)
	     cntr++;
   }
   return cntr;
}
//-----------------------------------------------------------------------
public int highestGrade()
{
   Student firstGrade = a[0];
   if ( firstGrade == null)
	   throw new Error ("Attempt to find the maximum of an empty Section");
   int high = firstGrade.getGrade();
   for(int i = 0; i < used; i++)
   {
      Student marker = a[i];
      int nextGrade = marker.getGrade();
      if(high < nextGrade)
    	  high = nextGrade;
   }
   return high;
}
//-----------------------------------------------------------------------
} // end class Section
/////////////////////////////////////////////////////////////////////////
class Student
{
   private String name;
   private int grade;
//-----------------------------------------------------------------------
public Student ( String name, int grade )
{
   this.name = name;
   this.grade = grade;
}
//-----------------------------------------------------------------------
public String toString ()
{
   return name + " (" + grade + ")";
}
//-----------------------------------------------------------------------
public String getName() { return name; }
//-----------------------------------------------------------------------
public int getGrade() { return grade; }
//-----------------------------------------------------------------------
public void setGrade( int newGrade ) { grade = newGrade; }
//-----------------------------------------------------------------------
public static Student read ( PrintStream ps, Scanner sc ) 
{
   if ( ps != null ) ps.println("Reading a Student record ...");
   if ( ps != null ) ps.print("Enter the name: ");
   String name = sc.nextLine();
   if ( ps != null ) ps.print("Enter the grade: ");
   int grade = sc.nextInt(); sc.nextLine();
   return new Student(name,grade);
}
//-----------------------------------------------------------------------
} // end class Student
/////////////////////////////////////////////////////////////////////////
