package hw14;

//Modified by Danzel Rana
//Written by Barry Soroka
//
//Solves CS 141, Winter 2013, Homework 14.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw14
{
//-----------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      Shape[] shapes = { new Circle(1.0),
                         new Square(2.5),
                         new Circle(2.0),
                         new Ellipse(2.0,3.0),
                         new Rectangle(3.0,4.0),
                         new Square(2.0) 
                       };

      double totalArea = 0.0;

      for ( Shape s : shapes )
      {
         double area = s.area();
         System.out.printf("%9.4f\n",area);
         totalArea += area;
      }

      System.out.printf("%9.4f totalArea\n",totalArea);
   }
//-----------------------------------------------------------------------
} // end class Hw14
/////////////////////////////////////////////////////////////////////////
interface Shape
{
   public double area ();
}
/////////////////////////////////////////////////////////////////////////
class Circle implements Shape
{
   private double radius;
//-----------------------------------------------------------------------
   public Circle (double radius)
   {
      this.radius=radius;
   }
//-----------------------------------------------------------------------
   public double area ()
   {
      return Math.PI*(radius*radius);
   }
//-----------------------------------------------------------------------
} // end class Circle
/////////////////////////////////////////////////////////////////////////
class Square implements Shape
{
   private double side;
//-----------------------------------------------------------------------
   public Square (double side)
   {
      this.side=side;
   }
//-----------------------------------------------------------------------
   public double area ()
   {
      return side*side;
   }
//-----------------------------------------------------------------------
} // end class Square
/////////////////////////////////////////////////////////////////////////
class Ellipse implements Shape
{
   private double a;
   private double b;
//-----------------------------------------------------------------------
   public Ellipse (double a, double b)
   {
      this.a=a;
      this.b=b;
   }
//-----------------------------------------------------------------------
   public double area ()
   {
      return Math.PI*(a*b);
   }
//-----------------------------------------------------------------------
} // end class Ellipse
/////////////////////////////////////////////////////////////////////////
class Rectangle implements Shape
{
   private double length;
   private double width;
//-----------------------------------------------------------------------
   public Rectangle (double length, double width)
   {
      this.length=length;
      this.width=width;
   }
//-----------------------------------------------------------------------
   public double area ()
   {
      return length*width;
   }
//-----------------------------------------------------------------------
} // end class Rectangle
/////////////////////////////////////////////////////////////////////////