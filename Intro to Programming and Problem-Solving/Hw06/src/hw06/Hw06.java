package hw06;

//Written by Danzel Rana.
//
//Solves CS 141, Winter 2013, Homework 6.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw06
{
//-----------------------------------------------------------------------
public static void main (String [] args) throws Exception
{
   Picture p = new Picture (35, 76, ' ');
   p.rectangle(10, 10, 40, 60, '-');
   p.rectangle(11, 18, 40, 41, '|');
   p.rectangle(11, 18, 59, 60, '|');
   p.rectangle(19, 30, 0, 75, 'X');
   p.circle(5.0, 10.0, 2.5, 'O');
   p.circle(6.0, 20.0, 2.5, 'O');
   p.circle(7.0, 30.0, 2.5, 'O');
   p.circle(30.0, 15.0 , 4.8, 'O');
   p.circle(30.0, 50.0, 4.8, 'O');
   p.print();
}
//-----------------------------------------------------------------------
} // end class Hw06
/////////////////////////////////////////////////////////////////////////
class Picture
{
   private int rmax;
   private int cmax;
   private char[][] a;
//-----------------------------------------------------------------------
public Picture ( int rmax, int cmax, char background )
{
   this.rmax = rmax;
   this.cmax = cmax;
   a = new char[rmax][cmax];
	
   for ( int i = 0; i < rmax; i++ )
   {
      for ( int j = 0; j < cmax; j++ )
         a[i][j] = background;
   }
}
//-----------------------------------------------------------------------
public void print()
{
   for ( int i = 0; i < rmax; i++ )
   {
      for ( int j = 0; j < cmax; j++ )
         System.out.print(a[i][j]);
      System.out.println();
   }
}
//-----------------------------------------------------------------------
public void rectangle ( int rlo, int rhi, int clo, int chi, char color )
{
   for ( int i = rlo; i <= rhi; i++ )
   {
      for ( int j = clo; j <= chi; j++ )
         a[i][j] = color;
   }
}
//-----------------------------------------------------------------------
public void circle ( double rc, double cc, double radius, char color )
{
   for (int i = 0; i < rmax; i++)
   {
      for (int j = 0; j < cmax; j++)
      {
         if ( ((i-rc)*(i-rc)) + ((j-cc)*(j-cc)) <= (radius*radius) )
            a[i][j] = color;
      }
   }
}
//-----------------------------------------------------------------------
} // end class Picture
/////////////////////////////////////////////////////////////////////////