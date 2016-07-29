//Written by Danzel Rana
//
//Solves CS 241, Summer 2014, Homework 04.
//
import java.io.*;
import java.util.*;
/////////////////////////////////////////////////////////////////////////
public class Hw04
{
//-----------------------------------------------------------------------
	public static void main(String[] args)
	{
		String[] namesR = {"Zach F","Adam K","Adam B","Bran T","Bill Y","Kent D",
								 "Dane M","Evan V","Elli R","Fred S","Stan T","Hoss J","Ivan L",
								 "Jill L","Jack K","Kate O","Matt P","Ross R","Gale W","Alex Q"};
		String[] namesArrSort = {"Zach F","Adam K","Adam B","Bran T","Bill Y","Kent D",
				 				 		 "Dane M","Evan V","Elli R","Fred S","Stan T","Hoss J","Ivan L",
				 				       "Jill L","Jack K","Kate O","Matt P","Ross R","Gale W","Alex Q"};
		String[] namesHash = {"Zach F","Adam K","Adam B","Bran T","Bill Y","Kent D",
				 					 "Dane M","Evan V","Elli R","Fred S","Stan T","Hoss J","Ivan L",
				 					 "Jill L","Jack K","Kate O","Matt P","Ross R","Gale W","Alex Q"};
		
		System.out.println("Unsorted: ");
		for (int i = 0; i < namesR.length; i++)
			System.out.print(namesR[i] + ", ");
		
		long startR = System.nanoTime();
	   radix (namesR);
	   long endR = System.nanoTime();
	   double totalTimeR = endR - startR;
	   double secondsR = (double)totalTimeR / 1000000000.0;
	   
	   long startArrSort = System.nanoTime();
	   arraySort (namesArrSort);
	   long endArrSort = System.nanoTime();
	   double totalTimeArrSort = endArrSort - startArrSort;
	   double secondsArrSort = (double)totalTimeArrSort / 1000000000.0;
	   
	   long startHash = System.nanoTime();
	   hash (namesHash);
	   long endHash = System.nanoTime();
	   double totalTimeHash = endHash - startHash;
	   double secondsHash = (double)totalTimeHash / 1000000000.0;
	   
	   System.out.println("\n\nRadix Time Taken: " + secondsR + " seconds");
	   System.out.println("Arrays.sort() Time Taken: " + secondsArrSort + " seconds");
	   System.out.println("Hash Time Taken: " + secondsHash + " seconds");
	}
//-----------------------------------------------------------------------
	public static void radix (String[] names)
	{
		int arrLength = names.length;
		int nameLength = names[0].length();
		String temp[] = new String[arrLength];
		for (int i = nameLength - 1; i >= 0; i--)
		{
			int[] cntr = new int[256];
			for (int j = 0; j < arrLength; j++)
				cntr[names[j].charAt(i) + 1]++;
			for (int k = 1; k < 256; k++)
				cntr[k] += cntr[k-1];
			for (int j = 0; j < arrLength; j++)
				temp[cntr[names[j].charAt(i)]++] = names[j];
			for (int j = 0; j < arrLength; j++)
				names[j] = temp[j];
		}
		System.out.println("\n\nRadix: ");
		for (int i = 0; i < arrLength; i++)
			System.out.print(names[i] + ", ");
	}
//-----------------------------------------------------------------------
	public static void arraySort(String[] names)
	{
		Arrays.sort(names);
		System.out.println("\n\nArrays.sort(): ");
		for (int i = 0; i < names.length; i++)
			System.out.print(names[i] + ", ");
	}
//-----------------------------------------------------------------------
	public static void hash(String[] names)
	{
		int hashVals[] = new int[names.length];
		for (int i = 0; i < names.length; i++)
			hashVals[i] = names[i].hashCode();
		Arrays.sort(hashVals);
		System.out.println("\n\nHash: ");
		for (int i = 0; i < names.length; i++)
			System.out.print(hashVals[i] + ", ");
	}
//-----------------------------------------------------------------------
} // end class Hw04
/////////////////////////////////////////////////////////////////////////