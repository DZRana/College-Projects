package hw15;

//Modified by Danzel Rana
//Written by Barry Soroka
//
//Pass a Quadratic using interface Function1D --
//print a histogram -- truncate to available width.
//
import java.io.*;
////////////////////////////////////////////////////////////////////////////////
class Prog1507
{
   private static final int MAXROWS = 40;
   private static final int MAXWIDTH = 60;
//------------------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      Quadratic q = new Quadratic(0.0,0.03,0.67);
      makeGraph(q);
   }
//------------------------------------------------------------------------------
   private static void makeGraph ( Function1D f )
   {
      double hi = f.valueAt(0.0);
      double lo = f.valueAt(0.0);
      for (int i = 0; i < MAXROWS; i++)
      {
         double width = f.valueAt(i)*18.0;
         if (width > hi) hi = width;
         if (width < lo) lo = width;
      }
      for ( int j = 0 ; j < MAXROWS ; j++ )
      {
         System.out.printf("%5.1f %5.1f",(double)j,f.valueAt(j));
         double width = f.valueAt(j)*18.0;
         if ( width == hi ) width = MAXWIDTH;
         for ( double k = lo ; k < width ; k++ ) System.out.print("+");
         System.out.println();
      }
   }
//------------------------------------------------------------------------------
} // end class Prog1507
////////////////////////////////////////////////////////////////////////////////
class Quadratic implements Function1D
{
   private double a0, a1, a2;
//------------------------------------------------------------------------------
   public Quadratic ( double a0, double a1, double a2 )
   {
      this.a0 = a0;
      this.a1 = a1;
      this.a2 = a2;
   }
//------------------------------------------------------------------------------
   public double valueAt ( double x ) { return a0 +  (x*a1)*(Math.cos(a2*x)); }
//------------------------------------------------------------------------------
} // end class Quadratic
////////////////////////////////////////////////////////////////////////////////
interface Function1D
{
//------------------------------------------------------------------------------
   public double valueAt ( double x );
//------------------------------------------------------------------------------
} // end interface Function1D
////////////////////////////////////////////////////////////////////////////////
