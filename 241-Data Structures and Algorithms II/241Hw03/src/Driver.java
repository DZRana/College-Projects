//Written by Danzel Rana
//
//Solves CS 241, Summer 2014, Homework 03.
//
import java.io.*;
import java.util.*;
//////////////////////////////////////////////////////////////////////
class Driver
{
//--------------------------------------------------------------------
	public static void main ( String [] args )
	{
		GraphMatrix gm = new GraphMatrix(0, 0);
		
		System.out.println("convertLink - adjacency list rep");
		System.out.println("DFS - vertices reached through DFS");
		System.out.println("BFS - vertices reached through BFS");
		System.out.println("isExist - does edge exist");
		System.out.println("add - adds edge");
		System.out.println("remove - remove edge");
		System.out.println("print - prints vertices");
		System.out.println("exit - exit");
		System.out.println();
		while (true)
      {
         System.out.print("Command? ");
         Scanner kb = new Scanner(System.in);
         String choice = kb.next();
		   if (choice.equals("exit")) break;
	      switch (choice)
		   {
		   	case "convertLink":
		   	   //gm.convertLink();
		   	   break;
		   	case "DFS":
		   		//gm.DFS();
		   		break;
		   	case "BFS":
		   		//gm.BFS();
		   		break;
		   	case "isExist":
		   		//gm.isExist();
		   		break;
		   	case "add":
		   		//gm.add();
		   		break;
		   	case "remove":
		   		//gm.remove();
		   		break;
		   	case "print":
		   		//gm.print();
		   		break;
            default:
            	System.out.println();
            	System.out.println("Sorry, but " + "\"" + choice + "\"" +
		                          	 " is not a valid command. Try again.");
            	break;
         }
      }
	}
//--------------------------------------------------------------------
} // end class Driver
/////////////////////////////////////////////////////////////////////////