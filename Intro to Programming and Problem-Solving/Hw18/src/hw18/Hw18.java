package hw18;

//Written by Danzel Rana
//
//Solves CS 141, Winter 2013, Homework 18.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw18
{
//-----------------------------------------------------------------------
   public static void main (String [] args) throws Exception
   {
      Item [] items = new Item[7];

      items[0] = new UnitItem("tissue",        1.22);
      items[1] = new BulkItem("chicken,wings", 2.57);
      items[2] = new BulkItem("chicken,whole", 1.80);
      items[3] = new UnitItem("jello",         2.89);
      items[4] = new UnitItem("crackers",      2.95);
      items[5] = new BulkItem("bananas",       1.42);
      items[6] = new UnitItem("broth",         0.97);
      double total = 0;
      double finalTotal = 0;
      while (true)
      {
         System.out.println();
         System.out.print("Item? ");
		 Scanner kb = new Scanner (System.in);
		 String item = kb.nextLine();
		 int itemNum = indexOf(item, items);
		 if (itemNum <= items.length && itemNum != -1)
		 {
		    total = items[itemNum].askMultiplyPrint();
		    finalTotal += total;
		    System.out.printf("Cost is $%.2f\n", total);
		 }
		 if (item.equals("quit"))
		 {
            System.out.println();
		    System.out.printf("Your total cost is $%.2f\n", finalTotal);
		    System.out.println("Thank you for shopping with Java!");
		    break;
		 }
		 if (itemNum == -1)
		    System.out.println("Sorry, but we don't carry that item here."); 
      }
   }
//-----------------------------------------------------------------------
   public static int indexOf(String name, Item[] items)
   {
      for (int i = 0; i < items.length; i++)
      {
         if (items[i].toString().equals(name))
            return i;
      }
      return -1;
   }
//----------------------------------------------------------------------- 
} // end class Hw18
/////////////////////////////////////////////////////////////////////////
abstract class Item
{
   protected String name;
//-----------------------------------------------------------------------
   public Item (String name)
   {
      this.name = name;
   }
//-----------------------------------------------------------------------
   public String toString()
   {
      return name;
   }
//-----------------------------------------------------------------------
   abstract public double askMultiplyPrint();
//-----------------------------------------------------------------------
} // end class Item
/////////////////////////////////////////////////////////////////////////
class UnitItem extends Item
{
   private double price;
//-----------------------------------------------------------------------
   public UnitItem (String name, double price)
   {
      super(name);
      this.price = price;
   }
//-----------------------------------------------------------------------
   public double askMultiplyPrint()
   {
      System.out.print("Quantity desired? ");
      Scanner kb = new Scanner (System.in);
      int qty = kb.nextInt();
      return qty * price;
   }
//-----------------------------------------------------------------------
} // end class UnitItem
/////////////////////////////////////////////////////////////////////////
class BulkItem extends Item
{
   private double price;
//-----------------------------------------------------------------------
   public BulkItem (String name, double price)
   {
      super(name);
      this.price = price;
   }
//-----------------------------------------------------------------------
   public double askMultiplyPrint()
   {
      System.out.print("Pounds desired? ");
      Scanner kb = new Scanner (System.in);
      double qty = kb.nextDouble();
      return qty * price;
   }
//-----------------------------------------------------------------------
} // end class BulkItem
/////////////////////////////////////////////////////////////////////////
