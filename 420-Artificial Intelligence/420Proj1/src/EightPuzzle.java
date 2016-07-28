//Written by Danzel Rana
//
//Solves CS 420, Spring 2016, Project 01.
//
import java.util.*;
import java.io.*;
/////////////////////////////////////////////////////////////////////////
public class EightPuzzle 
{
//-----------------------------------------------------------------------
	public static void main(String[] args) throws IOException 
	{
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter heuristic: ");
		int hChoice = kb.nextInt();
		boolean heuristic;
		if (hChoice == 1) heuristic = true;
		else heuristic = false;
		//int avgTotal = 0;
		System.out.print("Enter puzzle configuration: ");
		String puzzle = kb.next();
		kb.close();
		//FileInputStream fstream = new FileInputStream("100.txt");
		//BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		//String puzzle;
		long startTime = System.currentTimeMillis();
		//while ((puzzle = br.readLine()) != null)
		//{
			if(solveable(puzzle))
			{
				Node start = new Node(puzzle, 0);
				SolvePuzzle unsolved = new SolvePuzzle(start, heuristic);
				//avgTotal += unsolved.solve();
				unsolved.solve();
			}
			else 
			{
				System.out.println("The starting puzzle configuration, "
							    	 	+ puzzle + ", is unsolveable.");
			}
		//}
		long stopTime = System.currentTimeMillis();
		long totalTime = stopTime - startTime;
		System.out.println("Total time: " + totalTime + "ms");
		//br.close()
		//System.out.println("Avg Nodes: " + (avgTotal/100));
	}
//-----------------------------------------------------------------------
	static boolean solveable(String puzzle)
	{
		int inversions = 0;
		char[] puzzArr = puzzle.toCharArray();
		
		for (int i = 0; i < puzzArr.length; i++)
		{
			if (puzzArr[i] == '0') continue;
			for (int j = i + 1; j < puzzArr.length; j++)
			{
				if (puzzArr[j] != '0' && (puzzArr[i] > puzzArr[j])) inversions++;
			}
		}
		return inversions % 2 == 0;
	}
//-----------------------------------------------------------------------
} // end class EightPuzzle
/////////////////////////////////////////////////////////////////////////