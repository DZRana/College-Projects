//Written by Danzel Rana
//
//Solves CS 331, Summer 2014, Project 02.
//
import java.io.*;
import java.util.*;
//////////////////////////////////////////////////////////////////////
class proj2
{
//--------------------------------------------------------------------
	public static void main(String args[])
	{
		int n;
		Random rndm = new Random();
	 
	   Scanner kb = new Scanner(System.in);
	   System.out.print("Enter n numbers to sort: ");
	   n = kb.nextInt();
	   
		int[] vals = new int[n];
		int[] temp = new int[n];
		System.out.println("\nUnsorted: ");
		for ( int i = 0 ; i < n ; i++ )
		{
			int element = rndm.nextInt(100);
   		System.out.print(element + " ");
   		vals[i]= element;
   	}
		int[] sortedArrPrint= mergeSort(vals, temp, 0, vals.length - 1);
	   
		System.out.println("\n\nSorted: ");
		for ( int i = 0 ; i < vals.length ; i++ )
			System.out.print(sortedArrPrint[i] + " ");
		for (int k = 1; k <= n; k++)
		{
			for (int r = 1; r <= 3; r++)
			{
				long start1 = System.nanoTime();
				int[] sortedArr= mergeSort(vals, temp, 0, vals.length - 1);
				System.out.println("\n\nKth smallest (Algorithm 1): " + sortedArr[k-1]);
				long end1 = System.nanoTime();
				double totalTime1 = end1 - start1;
				double seconds1 = (double)totalTime1 / 1000000000.0;

				long start2 = System.nanoTime();
				System.out.println("\nKth smallest (Algorithm 2): "
								 	+ (qSortPartition1(vals, 0, vals.length - 1, k)));
				long end2 = System.nanoTime();
				double totalTime2 = end2 - start2;
				double seconds2 = (double)totalTime2 / 1000000000.0;
	   
				long start3 = System.nanoTime();
				System.out.println("\nKth smallest (Algorithm 2 (recursion)): "
						+ (qSortPartition2(vals, 0, vals.length - 1, k)));
				long end3 = System.nanoTime();
				double totalTime3 = end3 - start3;
				double seconds3 = (double)totalTime3 / 1000000000.0;
	   
				long start4 = System.nanoTime();
				System.out.println("\nKth smallest (Algorithm 3): "
						+ (mm(vals, 0, vals.length - 1, k)));
				long end4 = System.nanoTime();
				double totalTime4 = end4 - start4;
				double seconds4 = (double)totalTime4 / 1000000000.0;
			
				System.out.println("\n\nK: " + k);
				System.out.println("\nAlgorithm 1 Time Taken: " + seconds1 + " seconds");
				System.out.println("Algorithm 2 Time Taken: " + seconds2 + " seconds");
				System.out.println("Algorithm 2 (recursion) Time Taken: " + seconds3 + " seconds");
				System.out.println("Algorithm 3 Time Taken: " + seconds4 + " seconds");
			}
		}
	}
//--------------------------------------------------------------------
	public static int[] mergeSort (int[] vals, int[] temp, int lo, int hi)
	{
		if (lo < hi)
		{
			int mid = lo + (hi - lo)/ 2;
			mergeSort(vals, temp, lo, mid);
			mergeSort(vals, temp, mid + 1, hi);
			conCat(lo, mid, hi, vals, temp);
		}
		return vals;
	}
//--------------------------------------------------------------------
	public static void conCat (int lo, int mid, int hi, int[] vals, int[] temp)
	{
		for (int i = lo; i <= hi; i++)
			temp[i] = vals[i];
		int i = lo;
		int j = mid + 1;
		int k = lo;
		while (i <= mid && j <= hi)
		{
			if (temp[i] <= temp[j])
			{
				vals[k] = temp[i];
				i++;
			}
			else
			{
				vals[k] = temp[j];
				j++;
			}
			k++;
		}
		while (i <= mid)
		{
			vals[k] = temp[i];
			i++;
			k++;
		}
	}
//--------------------------------------------------------------------
	public static int qSortPartition1 (int[] vals, int lo, int hi, int k)
	{
		int i = lo;
		int j = hi;
		int pivot = vals[lo + (hi - lo) / 2];
		
		while (i <= j)
		{
			while (vals[i] < pivot) i++;
			while (vals[j] > pivot) j--;
			if (i <= j)
			{
				swap(vals, i, j);
				i++;
				j--;
			}
		}
		return vals[k-1];
	}
//--------------------------------------------------------------------
	public static int qSortPartition2 (int[] vals, int lo, int hi, int k)
	{
		int i = lo;
		int j = hi;
		int pivot = vals[lo + (hi - lo) / 2];
		
		while (i <= j)
		{
			while (vals[i] < pivot) i++;
			while (vals[j] > pivot) j--;
			if (i <= j)
			{
				swap(vals, i, j);
				i++;
				j--;
			}
		}
		if (lo < j) qSortPartition2 (vals, lo , j, k);
		if (i < hi) qSortPartition2 (vals, i , hi, k);
		return vals[k-1];
	}
//--------------------------------------------------------------------
	public static void swap (int[] vals, int i, int j)
	{
		int temp = vals[i];
		vals[i] = vals[j];
		vals[j] = temp;
	}
//--------------------------------------------------------------------
	public static int mm (int[] vals, int lo, int hi, int k)
	{
		if (hi == 1 && k == 1) return vals[lo];
	     
	   int m = (hi + 4) / 5;
	   int[] mid = new int[m];
	     
	   for (int i = 0; i < m; i++)
	   {
	   	int r = lo + i * 5;    
	      if (hi - r > 4) 
	      {
	      	qSortPartition2(vals, r, 0, k);    
	         mid[i] = vals[r+2];
	      }
	      else 
	      {   
	      	qSortPartition2(vals, r, hi - r - 2, k); 
	         mid[i] = vals[r + (hi-r-1) / 2];
	      }
	   }
	     
	   int pivot = mm(mid, 0, m, (m+1) / 2);
	     
	   for (int i = 0; i < hi; i++) 
	   { 
	   	if (vals[lo + i] == pivot )
	   	{
	   		swap(vals, lo + i, lo + hi - 1);
	         break;
	      }
	   }
	     
	   int pos = 0;
	   for (int i = 0; i < hi - 1; i++) 
	   { 
	   	if (vals[lo + i] < pivot) 
	   	{
	   		if (i != pos) swap(vals, lo + i, lo + pos);
	         pos++;
	      }
	   }
	   swap(vals, lo + pos, lo + hi - 1);
	     
	   if (pos == k - 1) return pivot;
	   else if (pos > k - 1) return mm(vals, lo, pos, k);
	   else return mm(vals, lo + pos + 1, hi - pos - 1, k - pos - 1);
	}
//--------------------------------------------------------------------
} // end class proj2
//////////////////////////////////////////////////////////////////////