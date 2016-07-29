//Written by Danzel Rana
//
//Solves CS 331, Summer 2014, Project 01.
//
import java.io.*;
import java.util.*;
//////////////////////////////////////////////////////////////////////
class proj1
{
//--------------------------------------------------------------------
	public static void main(String args[])
	{
		int n;
		Random rndm = new Random();
	 
	   Scanner kb = new Scanner(System.in);
	   System.out.print("Enter n of the square matrices: ");
	   n = kb.nextInt();
	 
	   int first[][] = new int[n][n];
	   int second[][] = new int[n][n];
	 
	   System.out.println("\nFirst: ");
	   for ( int i = 0 ; i < n ; i++ )
	   	for ( int j = 0 ; j < n ; j++ )
	   	{
	   		int element =rndm.nextInt(10);
	   		System.out.print(element + " ");
	   		if ( j == n-1) System.out.println();
	   		first[i][j] = element;
	   	}
	   System.out.println("\nSecond: ");
	   for ( int i = 0 ; i < n ; i++ )
	   	for ( int j = 0 ; j < n; j++ )
	   	{
	   		int element =rndm.nextInt(10);
	   		System.out.print(element + " ");
	   		if ( j == n-1) System.out.println();
	   		second[i][j] = element;
	   	}
	   long start = System.nanoTime();
	   classic (first, second, n);
	   long end = System.nanoTime();
	   double totalTime = end - start;
	   double seconds = (double)totalTime / 1000000000.0;
	   
	   long startDAndC = System.nanoTime();
	   dAndC (first, second, n);
	   long endDAndC = System.nanoTime();
	   double totalTimeDAndC = endDAndC - startDAndC;
	   double secondsDAndC = (double)totalTimeDAndC / 1000000000.0;
	   
	   long startS = System.nanoTime();
	   strassen (first, second, n);
	   long endS = System.nanoTime();
	   double totalTimeS = endS - startS;
	   double secondsS = (double)totalTimeS / 1000000000.0;
	   
	   System.out.println("\nClassic Time Taken: " + seconds + " seconds");
	   System.out.println("\nD & C Time Taken: " + secondsDAndC + " seconds");
	   System.out.println("\nStrassen Time Taken: " + secondsS + " seconds");
	 }
//--------------------------------------------------------------------
	public static void classic (int first[][], int second[][], int n)
	{
		int multiply[][] = new int[n][n];
		int sum = 0;
		for ( int i = 0; i < n; i++ )
	   {
	   	for ( int j = 0; j < n; j++ )
	      {   
	   		for ( int k = 0; k < n; k++ )
	   			sum = sum + first[i][k] * second[k][j];
	   		multiply[i][j] = sum;
	         sum = 0;
	      }
	   }
		
	   System.out.println("\nClassic Product:");
	   for ( int i = 0; i < n; i++ )
	   {
	   	for ( int j = 0; j < n; j++ )
	   		System.out.print(multiply[i][j] + "\t");
	 
	      System.out.print("\n");
	   }
	}
//--------------------------------------------------------------------
	public static void dAndC (int first[][], int second[][], int n)
	{
		int A11[][] = new int[n/2][n/2];
		int B11[][] = new int[n/2][n/2];
		int A12[][] = new int[n/2][n/2];
		int B12[][] = new int[n/2][n/2];
		int A21[][] = new int[n/2][n/2];
		int B21[][] = new int[n/2][n/2];
		int A22[][] = new int[n/2][n/2];
		int B22[][] = new int[n/2][n/2];
		int C11[][] = new int[n/2][n/2];
		int C12[][] = new int[n/2][n/2];
		int C21[][] = new int[n/2][n/2];
		int C22[][] = new int[n/2][n/2];
		int product[][] = new int[n][n];
		
		int sum = 0;
		for ( int i = 0; i < n/2; i++ )
	   {
	   	for ( int j = 0; j < n/2; j++ )
	   	{
	   		A11[i][j] = first[i][j];
	   		B11[i][j] = second[i][j];
	   	}
	   }
		for ( int i = 0; i < n/2; i++ )
	   {
	   	for ( int j = n/2; j < n; j++ )
	   	{
	   		A12[i][(j-1)/2] = first[i][j];
	   		B12[i][(j-1)/2] = second[i][j];
	   	}
	   }
		for ( int i = n/2; i < n; i++ )
	   {
	   	for ( int j = 0; j < n/2; j++ )
	   	{
	   		A21[(i-1)/2][j] = first[i][j];
	   		B21[(i-1)/2][j] = second[i][j];
	   	}
	   }
		for ( int i = n/2; i < n; i++ )
	   {
	   	for ( int j = n/2; j < n; j++ )
	   	{
	   		A22[(i-1)/2][(j-1)/2] = first[i][j];
	   		B22[(i-1)/2][(j-1)/2] = second[i][j];
	   	}
	   }
		
		for ( int i = 0; i < n/2; i++ )
	   {
	   	for ( int j = 0; j < n/2; j++ )
	      {   
	   		for ( int k = 0; k < n/2; k++ )
	   			sum = sum + (A11[i][k] * B11[k][j]) + (A12[i][k] * B21[k][j]);
	   		C11[i][j] = sum;
	         sum = 0;
	      }
	   }
		for ( int i = 0; i < n/2; i++ )
	   {
	   	for ( int j = 0; j < n/2; j++ )
	      {   
	   		for ( int k = 0; k < n/2; k++ )
	   			sum = sum + (A11[i][k] * B12[k][j]) + (A12[i][k] * B22[k][j]);
	   		C12[i][j] = sum;
	         sum = 0;
	      }
	   }
		for ( int i = 0; i < n/2; i++ )
	   {
	   	for ( int j = 0; j < n/2; j++ )
	      {   
	   		for ( int k = 0; k < n/2; k++ )
	   			sum = sum + (A21[i][k] * B11[k][j]) + (A22[i][k] * B21[k][j]);
	   		C21[i][j] = sum;
	         sum = 0;
	      }
	   }
		for ( int i = 0; i < n/2; i++ )
	   {
	   	for ( int j = 0; j < n/2; j++ )
	      {   
	   		for ( int k = 0; k < n/2; k++ )
	   			sum = sum + (A21[i][k] * B12[k][j]) + (A22[i][k] * B22[k][j]);
	   		C22[i][j] = sum;
	         sum = 0;
	      }
	   }
		for ( int i = 0; i < n/2; i++ )
	   {
	   	for ( int j = 0; j < n/2; j++ )
	      {   
	   		product[i][j] = C11[i][j];
	      }
	   }
		for ( int i = 0; i < n/2; i++ )
	   {
	   	for ( int j = n/2; j < n; j++ )
	      {   
	   		product[i][j] = C12[i][(j-1)/2];
	      }
	   }
		for ( int i = n/2; i < n; i++ )
	   {
	   	for ( int j = 0; j < n/2; j++ )
	      {   
	   		product[i][j] = C21[(i-1)/2][j];
	      }
	   }
		for ( int i = n/2; i < n; i++ )
	   {
	   	for ( int j = n/2; j < n; j++ )
	      {   
	   		product[i][j] = C22[(i-1)/2][(j-1)/2];
	      }
	   }

	   System.out.println("\nD & C Product:");
	   for ( int i = 0; i < n; i++ )
	   {
	   	for ( int j = 0; j < n; j++ )
	   		System.out.print(product[i][j] + "\t");
	 
	      System.out.print("\n");
	   }
	}
//--------------------------------------------------------------------
	public static void strassen (int first[][], int second[][], int n)
	{
		int p[][] = new int[n/2][n/2];
		int q[][] = new int[n/2][n/2];
		int r[][] = new int[n/2][n/2];
		int s[][] = new int[n/2][n/2];
		int t[][] = new int[n/2][n/2];
		int u[][] = new int[n/2][n/2];
		int v[][] = new int[n/2][n/2];
		int A11[][] = new int[n/2][n/2];
		int B11[][] = new int[n/2][n/2];
		int A12[][] = new int[n/2][n/2];
		int B12[][] = new int[n/2][n/2];
		int A21[][] = new int[n/2][n/2];
		int B21[][] = new int[n/2][n/2];
		int A22[][] = new int[n/2][n/2];
		int B22[][] = new int[n/2][n/2];
		int C11[][] = new int[n/2][n/2];
		int C12[][] = new int[n/2][n/2];
		int C21[][] = new int[n/2][n/2];
		int C22[][] = new int[n/2][n/2];
		int product[][] = new int[n][n];
		
		int sum = 0;
		for ( int i = 0; i < n/2; i++ )
	   {
	   	for ( int j = 0; j < n/2; j++ )
	   	{
	   		A11[i][j] = first[i][j];
	   		B11[i][j] = second[i][j];
	   	}
	   }
		for ( int i = 0; i < n/2; i++ )
	   {
	   	for ( int j = n/2; j < n; j++ )
	   	{
	   		A12[i][(j-1)/2] = first[i][j];
	   		B12[i][(j-1)/2] = second[i][j];
	   	}
	   }
		for ( int i = n/2; i < n; i++ )
	   {
	   	for ( int j = 0; j < n/2; j++ )
	   	{
	   		A21[(i-1)/2][j] = first[i][j];
	   		B21[(i-1)/2][j] = second[i][j];
	   	}
	   }
		for ( int i = n/2; i < n; i++ )
	   {
	   	for ( int j = n/2; j < n; j++ )
	   	{
	   		A22[(i-1)/2][(j-1)/2] = first[i][j];
	   		B22[(i-1)/2][(j-1)/2] = second[i][j];
	   	}
	   }
		
		if (n == 2)
		{
			for ( int i = 0; i < n/2; i++ )
		   {
		   	for ( int j = 0; j < n/2; j++ )
		      {   
		   		for ( int k = 0; k < n/2; k++ )
		   			sum = sum + (A11[i][k] * B11[k][j]) + (A12[i][k] * B21[k][j]);
		   		C11[i][j] = sum;
		         sum = 0;
		      }
		   }
			for ( int i = 0; i < n/2; i++ )
		   {
		   	for ( int j = 0; j < n/2; j++ )
		      {   
		   		for ( int k = 0; k < n/2; k++ )
		   			sum = sum + (A11[i][k] * B12[k][j]) + (A12[i][k] * B22[k][j]);
		   		C12[i][j] = sum;
		         sum = 0;
		      }
		   }
			for ( int i = 0; i < n/2; i++ )
		   {
		   	for ( int j = 0; j < n/2; j++ )
		      {   
		   		for ( int k = 0; k < n/2; k++ )
		   			sum = sum + (A21[i][k] * B11[k][j]) + (A22[i][k] * B21[k][j]);
		   		C21[i][j] = sum;
		         sum = 0;
		      }
		   }
			for ( int i = 0; i < n/2; i++ )
		   {
		   	for ( int j = 0; j < n/2; j++ )
		      {   
		   		for ( int k = 0; k < n/2; k++ )
		   			sum = sum + (A21[i][k] * B12[k][j]) + (A22[i][k] * B22[k][j]);
		   		C22[i][j] = sum;
		         sum = 0;
		      }
		   }
			for ( int i = 0; i < n/2; i++ )
		   {
		   	for ( int j = 0; j < n/2; j++ )
		      {   
		   		product[i][j] = C11[i][j];
		      }
		   }
			for ( int i = 0; i < n/2; i++ )
		   {
		   	for ( int j = n/2; j < n; j++ )
		      {   
		   		product[i][j] = C12[i][(j-1)/2];
		      }
		   }
			for ( int i = n/2; i < n; i++ )
		   {
		   	for ( int j = 0; j < n/2; j++ )
		      {   
		   		product[i][j] = C21[(i-1)/2][j];
		      }
		   }
			for ( int i = n/2; i < n; i++ )
		   {
		   	for ( int j = n/2; j < n; j++ )
		      {   
		   		product[i][j] = C22[(i-1)/2][(j-1)/2];
		      }
		   }

		   System.out.println("\nStrassen Product:");
		   for ( int i = 0; i < n; i++ )
		   {
		   	for ( int j = 0; j < n; j++ )
		   		System.out.print(product[i][j] + "\t");
		 
		      System.out.print("\n");
		   }
		}
		else
		{
			int p1[][] = new int[n/2][n/2];
			int p2[][] = new int[n/2][n/2];
			for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
				{
					p1[i][j] = (A11[i][j] + A22[i][j]);
					p2[i][j] = (B11[i][j] + B22[i][j]);
				}
		   }
			for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
				{
					for ( int k = 0; k < n/2; k++ )
						sum = sum + (p1[i][k] * p2[k][j]);
					p[i][j] = sum;
			      sum = 0;
				}
		   }

		   int q1[][] = new int[n/2][n/2];
		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
		   		q1[i][j] = (A21[i][j] + A22[i][j]);
		   }
		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
				{
					for ( int k = 0; k < n/2; k++ )
						sum = sum + (q1[i][k] * B11[k][j]);
					q[i][j] = sum;
			      sum = 0;
				}
		   }

		   int r1[][] = new int[n/2][n/2];
		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
		   		r1[i][j] = (B12[i][j] - B22[i][j]);
		   }
		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
				{
					for ( int k = 0; k < n/2; k++ )
						sum = sum + (A11[i][k] * r1[k][j]);
					r[i][j] = sum;
			      sum = 0;
				}
		   }
		   
		   int s1[][] = new int[n/2][n/2];
		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
		   		s1[i][j] = (B21[i][j] - B11[i][j]);
		   }
		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
				{
					for ( int k = 0; k < n/2; k++ )
						sum = sum + (A22[i][k] * s1[k][j]);
					s[i][j] = sum;
			      sum = 0;
				}
		   }
		   
		   int t1[][] = new int[n/2][n/2];
		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
		   		t1[i][j] = (A11[i][j] + A12[i][j]);
		   }
		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
				{
					for ( int k = 0; k < n/2; k++ )
						sum = sum + (t1[i][k] * B22[k][j]);
					t[i][j] = sum;
			      sum = 0;
				}
		   }
		   
		   int u1[][] = new int[n/2][n/2];
		   int u2[][] = new int[n/2][n/2];
		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
				{
		   		u1[i][j] = (A21[i][j] - A11[i][j]);
		   		u2[i][j] = (B11[i][j] + B12[i][j]);
				}
		   }
		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
				{
					for ( int k = 0; k < n/2; k++ )
						sum = sum + (u1[i][k] * u2[k][j]);
					u[i][j] = sum;
			      sum = 0;
				}
		   }
		   
		   int v1[][] = new int[n/2][n/2];
		   int v2[][] = new int[n/2][n/2];
		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
				{
		   		v1[i][j] = (A12[i][j] - A22[i][j]);
		   		v2[i][j] = (B21[i][j] + B22[i][j]);
				}
		   }
		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
				{
					for ( int k = 0; k < n/2; k++ )
						sum = sum + (v1[i][k] * v2[k][j]);
					v[i][j] = sum;
			      sum = 0;
				}
		   }
		   
		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
		   		C11[i][j] = p[i][j] + s[i][j] - t[i][j] + v[i][j];
		   }

		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
		   		C12[i][j] = r[i][j] + t[i][j];
		   }

		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
		   		C21[i][j] = q[i][j] + s[i][j];
		   }

		   for ( int i = 0; i < n/2; i++ )
		   {
				for ( int j = 0; j < n/2; j++ )
		   		C22[i][j] = p[i][j] + r[i][j] - q[i][j] + u[i][j];
		   }

		   for ( int i = 0; i < n/2; i++ )
		   {
		   	for ( int j = 0; j < n/2; j++ )
		      {   
		   		product[i][j] = C11[i][j];
		      }
		   }
			for ( int i = 0; i < n/2; i++ )
		   {
		   	for ( int j = n/2; j < n; j++ )
		      {   
		   		product[i][j] = C12[i][(j-1)/2];
		      }
		   }
			for ( int i = n/2; i < n; i++ )
		   {
		   	for ( int j = 0; j < n/2; j++ )
		      {   
		   		product[i][j] = C21[(i-1)/2][j];
		      }
		   }
			for ( int i = n/2; i < n; i++ )
		   {
		   	for ( int j = n/2; j < n; j++ )
		      {   
		   		product[i][j] = C22[(i-1)/2][(j-1)/2];
		      }
		   }

		   System.out.println("\nStrassen Product:");
		   for ( int i = 0; i < n; i++ )
		   {
		   	for ( int j = 0; j < n; j++ )
		   		System.out.print(product[i][j] + "\t");
		 
		      System.out.print("\n");
		   }
		}		
	}
//--------------------------------------------------------------------
} // end class proj1
//////////////////////////////////////////////////////////////////////