package hw04;
//Modified by Danzel Rana
//Written by Barry Soroka
//
//Solves CS 141, Winter 2013, Homework 4.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw04
{
//-----------------------------------------------------------------------
public static void main ( String [] args ) throws Exception
{
   Circle c1 = new Circle(1.0);
   report(c1);
   Circle c2 = new Circle(0.56418958);
   report(c2);
   c2.setRadius(0.1591549431);
   report(c2);
}
//-----------------------------------------------------------------------
public static void report ( Circle c )
{
   System.out.println();
   System.out.printf("radius     %6.4f\n",c.getRadius());
   System.out.printf("area       %6.4f\n",c.area());
   System.out.printf("perimeter  %6.4f\n",c.perimeter());
}
//-----------------------------------------------------------------------
} // end class Hw04
/////////////////////////////////////////////////////////////////////////
class Circle
{
   private double radius;
//-----------------------------------------------------------------------
   public Circle ( double newRadius )
   {
      radius = newRadius;
   }
//-----------------------------------------------------------------------
   public void setRadius(double newRadius) { radius = newRadius; }
//-----------------------------------------------------------------------
   public double getRadius() { return radius; }
//-----------------------------------------------------------------------
   public double area() { return (Math.PI * (radius * radius)); }
//-----------------------------------------------------------------------
   public double perimeter() { return (2 * (Math.PI * radius)); }
//-----------------------------------------------------------------------
} // end class Circle
/////////////////////////////////////////////////////////////////////////
