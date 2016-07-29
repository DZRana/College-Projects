package hw02;

//Written by Danzel Rana
//
//Solves CS 240, Spring 2013, Homework 02.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw02
{
//-----------------------------------------------------------------------
	public static void main (String [] args)
   {
      int total = 0;
      System.out.println("total is " + total);
      System.out.println();
      while (true)
      {
         System.out.print("Command? ");
         Scanner kb = new Scanner(System.in);
         String choice = kb.next();
		   if (choice.equals("exit")) break;
	      switch (choice)
		   {
		   	case "add":
		   	   total = total + kb.nextInt();
		   	   break;
		   	case "sub":
		   		total = total - kb.nextInt();
		   		break;
		   	case "mul":
		   		total = total * kb.nextInt();
		   		break;
		   	case "reset":
		   		total = 0;
		   		break;
		   	case "help":
		   		System.out.println();
		   		System.out.printf("%s%26s", "add", "Add an int to total\n");
		   		System.out.printf("%s%22s", "exit", "Exit the program\n");
		   		System.out.printf("%s%23s", "help", "Display this text\n");
		   		System.out.printf("%s%31s", "mul", "Multiply total by an int\n");
		   		System.out.printf("%s%22s", "reset", "Set total to zero\n");
		   		System.out.printf("%s%33s", "sub", "Subtract an int from total\n");
		   		break;
            default:
            	System.out.println();
            	System.out.println("Sorry, but " + "\"" + choice + "\"" +
		                          	 " is not a valid command. Try again.");
            	break;
         }
	      System.out.println();
         System.out.println("total is " + total + "\n");
      }
   }
//-----------------------------------------------------------------------
} // end class Hw02
/////////////////////////////////////////////////////////////////////////