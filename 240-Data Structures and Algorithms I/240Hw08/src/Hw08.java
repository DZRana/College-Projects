// Written by Danzel Rana
//
// Solves CS 240, Spring 2013, Homework 8.
//
import java.io.*;
import java.util.*;
////////////////////////////////////////////////////////////////////////////////
class Hw08
{
//------------------------------------------------------------------------------
   public static void main (String [] args)
   {
      System.out.print("Shuffle ");
      Scanner kb = new Scanner(System.in);
      int tracks = kb.nextInt();
      String init = kb.next();
      char[] carsArr = init.toCharArray(); 
      int[] cars = new int[carsArr.length];
      int[] sortedCars = new int[carsArr.length];
      int start = 0;
     
      for (int i = 0; i < carsArr.length; i++)
      {
         int num = Character.digit(carsArr[i], 10);
      	cars[i] = num;
      	sortedCars[i] = num;
      }
     
      Arrays.sort(sortedCars);
      if (sortedCars[cars.length-1] != cars.length)
      {
      	System.out.println("Invalid permutation. Highest digit must match the amount of digits in the permutation");
      	System.exit(0);
      }
     
      Queue<Integer>[] q = new Queue[tracks];
      for (int i = 0; i < q.length; i++)
      	q[i] = new LinkedList<Integer>();
      
      int cntr = 0;
      for (int i = 0; i < cars.length; i++)
      {
    	   if ((i + 1) < cars.length && cars[i] > cars[i+1])
    	   {
    	   	for ( int j = 0; j < q.length; j++)
    		   {
    	   		if( q[j].isEmpty() || cars[i+1] > q[j].peek())
    			   {
    	   			if (q[j].isEmpty())
    				   {
    	   				q[j].add(cars[i]);
    					   System.out.println("Move " + cars[i] + " to empty track " + j);
    					   break;
    				   }
    				   else
    				   {
    				   	q[j].add(cars[i]);
    					   System.out.println("Move " + cars[i] + " to track " + j);
    					   break;
    				   }
    			   }
    		   }
    	   }
         if ((i + 1) < cars.length && cars[i] < cars[i+1])
    	   {
         	if (cars[i] == 1)
            {
         		for (int j = 0; j < q.length; j++)
            	{
         			if(!(q[j].isEmpty()))
            		{
         				cntr++;
            			if (cntr == q.length -1)
            			{
            				System.out.println("No solution");
            				System.exit(0);
            			}
            		}
            	}
            } 
         	outer: for (int j = i; j < cars.length; j++)
    		   {
         		if ((j + 1) < cars.length && cars[i] < cars[j+1])
    			   {
         			for ( int k = 0; k < q.length; k++)
        			   {
         				if( q[k].isEmpty() || cars[k+1] > q[k].peek())
        				   {
         					if (q[k].isEmpty())
        					   {
         						q[k].add(cars[i]);
        						   System.out.println("Move " + cars[i] + " to empty track " + k);
        						   break outer;
        					   }
        					   else
        					   {
        					   	q[k].add(cars[i]);
        						   System.out.println("Move " + cars[i] + " to track " + k);
        						   break outer; 
        					   }
        				   }
        			   }
    			   }	 
    		   }
    	   }
    	   if (i == cars.length - 1)
    	   {
    	   	for (int j = 0; j < cars.length; j++)
    		   {
    	   		if( q[j].isEmpty() || cars[i] > q[j].peek() || cars[i] == 1)
        		   {
    	   			if (cars[i] == 1 && q[j].isEmpty())
      			   {
    	   				start++;
    	   				System.out.println("Move " + cars[i] + " directly to output using track " + j);
      				   break; 
      			   }
        			   if (cars[i] > q[j].peek())
        			   {
        			   	q[j].add(cars[i]);
        				   System.out.println("Move " + cars[i] + " to track " + j);
        				   break; 
        			   }
        			   if (q[j].isEmpty() )
       			   {
        			   	q[j].add(cars[i]);
   					   System.out.println("Move " + cars[i] + " to empty track " + j);
       				   break;
       			    }
    			   }	 
    		   }
    	   }	 
      }
      for (int i = 0; i < q.length; i++)
      {
      	for (Integer j : q[i])
      	{
      		if (j == start + 1 )
      		{
      			q[i].remove();
      			start++;
      			System.out.println("Move " + start + " from track " + i + " to output");
      			break;
      		}
      	}
      }
      for (int i = 0; i < q.length; i++)
      {
      	for (Integer j : q[i])
      	{
      		System.out.println("Move " + j + " from track " + i + " to output");
      	}
      }
   }
//------------------------------------------------------------------------------
} // end class Hw08
////////////////////////////////////////////////////////////////////////////////
