package hw13;

//Modified by Danzel Rana
//Written by Barry Soroka
//
//Solves CS 141, Winter 2013, Homework 13.
//
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
/////////////////////////////////////////////////////////////////////////
class Hw13
{
//-----------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      Circle[] circles = { new Circle(3),
                           new Circle(4),
                           new Circle(3.01),
                           new Circle(1),
                           new Circle(2),
                           new Circle(2.99) };

      for ( Circle c : circles ) System.out.println(c);

      Arrays.sort(circles);

      System.out.println();
      for ( Circle c : circles ) System.out.println(c);
   }
//-----------------------------------------------------------------------
} // end class Hw13
/////////////////////////////////////////////////////////////////////////
class Circle implements Comparable
{
   private double circ;
//-----------------------------------------------------------------------
   public Circle (double circ)
   {
      this.circ = circ;
   }
//-----------------------------------------------------------------------
   public int compareTo (Object o)
   {
      Circle that = (Circle) o;
      return (int)this.circ - (int)that.circ;
   }
//-----------------------------------------------------------------------
   public String toString()
   {
      return String.format("%s%.2f%s", "Circle(", circ, ")");
   }
//-----------------------------------------------------------------------	
} // end class Circle
/////////////////////////////////////////////////////////////////////////
