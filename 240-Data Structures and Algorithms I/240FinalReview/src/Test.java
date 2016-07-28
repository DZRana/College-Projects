import java.util.*;
import java.io.*;

public class Test
{
	public static void main (String [] args)
	{
		Integer[] iar = {1,2,3};
		Character[] car = {'c','a','r'};
		printMe(reverse(iar));
		printMe(reverse(car));
	}
	public static <T> void printMe (T[] arr)
	{
		
		for (T i : arr)
			System.out.printf("%s ", i);
		System.out.println();
	}
	public static <T> T[] reverse (T[] arr)
	{
		T[] result = (T[]) new Object[arr.length];
		int i = arr.length - 1;
		for (T e : arr) 
		{
			result[i] = e;
			i--;
		}
		return result;
	}
}