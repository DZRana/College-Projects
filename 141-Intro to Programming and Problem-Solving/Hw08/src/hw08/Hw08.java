package hw08;

//Modified by Danzel Rana
//Written by Barry Soroka
//
//Solves CS 141, Winter 2013, Homework 8.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw08
{
//-----------------------------------------------------------------------
public static void main ( String [] args ) throws Exception
{
   Item s1 = new Item("water",1.29);
   Item s2 = new Item("tissue",1.84);

   System.out.println();
   s1.buy(1);
   System.out.println();
   s2.buy(4);
   System.out.println();
   report();

   Item.setTaxRate(0.0825); 
   s2.setUnitPrice(2.05);

   System.out.println();
   s2.buy(3);
   System.out.println();
   report();
   System.out.println();
}
//-----------------------------------------------------------------------
private static void report()
{
   System.out.println("*** Transactions: " + Item.transactions());
   System.out.println("*** Total units sold: " + Item.totalUnits());
}
//-----------------------------------------------------------------------
} // end class Hw08
/////////////////////////////////////////////////////////////////////////
class Item
{
private String name;
private double unitPrice;
private static double taxRate = 0.05;
private static int transactions = 0;
private static int totalUnits = 0;
//-----------------------------------------------------------------------
public Item ( String name, double unitPrice)
{
	this.name = name;
	this.unitPrice = unitPrice;
}
//-----------------------------------------------------------------------
public void buy(int amnt)
{
	transactions++;
	totalUnits += amnt;
	double netVal = (amnt*unitPrice*100)/100.0;
	double taxVal = Math.round((amnt*unitPrice*taxRate*100))/100.0;
	double grossVal = netVal + taxVal;
	
	System.out.println(name + " " + amnt + " " + "unit(s) at unit price $" + unitPrice);
	System.out.printf ("$%6s%4s\n", netVal, "net");
	System.out.printf("%7s%4s\n", taxVal, "tax" );
	System.out.printf("$%6s%6s\n", grossVal, "gross");
}
//-----------------------------------------------------------------------
public static void setTaxRate (double newTaxRate) 
{
	taxRate = newTaxRate;
	System.out.println();
	System.out.println("*** Tax rate changed to " + newTaxRate);
}
//-----------------------------------------------------------------------
public void setUnitPrice(double newUnitPrice) { unitPrice = newUnitPrice; }
//-----------------------------------------------------------------------
public static int transactions() { return transactions; }
//-----------------------------------------------------------------------
public static int totalUnits() { return totalUnits; }
//-----------------------------------------------------------------------
} // end class Item
/////////////////////////////////////////////////////////////////////////

