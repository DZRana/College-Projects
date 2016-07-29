//Written by Danzel Rana
//
//Solves CS 420, Spring 2016, Project 02.
//
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;
/////////////////////////////////////////////////////////////////////////
public class NQueen
{
//-----------------------------------------------------------------------
	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter N: ");
		int n = kb.nextInt();
		int[] steepHArr = new int[n];
		int[] cspHArr = new int[n];
		char[][] steepBoard = createBoard(n,steepHArr);
		char[][] cspBoard = steepBoard.clone();
		System.out.println("Initial: ");
		printBoard(steepBoard);
		System.out.println("\nFinal (Steepest HC): ");
		long steepStartTime = System.nanoTime();
		printBoard(steepest(steepBoard, steepHArr, calcHCost(steepHArr), n));
		long steepEndTime = System.nanoTime();
		System.out.print("Time elapsed (ms): " + (steepEndTime - steepStartTime)/1000000.0 + "\n");
		System.out.println("\nFinal (CSP): ");
		long cspStartTime = System.nanoTime();
		printBoard(csp(cspBoard, steepHArr, calcHCost(cspHArr), n));
		long cspEndTime = System.nanoTime();
		System.out.print("Time elapsed (ms): " + (cspEndTime - cspStartTime)/1000000.0 + "\n");
	}
//-----------------------------------------------------------------------
	public static char[][] createBoard(int n, int[] hArr)
	{
		char[][] board = new char[n][n];
		int qPlace = 0;
		for (int row = 0; row < n; row++)
		{
			for (int col = 0; col < n; col++)
			{
				board[row][col] = '*';
			}
		}
		for (int col = 0; col < n; col++)
		{
			qPlace = ThreadLocalRandom.current().nextInt(0, n);
			board[qPlace][col] = 'Q';
			hArr[col] = qPlace;
		}
		return board;
	}
//-----------------------------------------------------------------------
	public static void printBoard(char[][] board)
	{
		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board.length; col++)
			{
				System.out.print(board[row][col]);
				if(col == board.length-1) System.out.println();
			}
		}
	}
//-----------------------------------------------------------------------	
	public static int calcHCost(int[] hArr)
	{
		int h = 0;
		for (int row = 0; row < hArr.length; row++)
		{
			for (int col = row + 1; col < hArr.length; col++)
			{
				if (hArr[row] == hArr[col]) h += 1;
				if ((hArr[row] == (hArr[col] - (col - row))) || (hArr[row] == (hArr[col] + (col - row))))
				{
					h += 1;
				}
			}
		}
		//System.out.println(h);
		return h;
	}
//-----------------------------------------------------------------------	
	public static char[][] steepest(char[][] board, int[] hArr, int bestHCost, int n)
	{
		int[] newHArr = hArr.clone();
		int newHArrCost = 0;
		ArrayList bestMoves = new ArrayList();
		for (int row = 0; row < n; row++)
		{	
			for (int col = row + 1; col < n; col++)
			{
				newHArr[row] = col;
				newHArrCost = calcHCost(newHArr);
				if (newHArrCost < bestHCost) 
				{
					bestHCost = newHArrCost;
					bestMoves.clear();
				}
				if (newHArrCost == bestHCost) bestMoves.add(col);
				if (col == (n-1))
				{
					hArr[row] = (int) bestMoves.get(ThreadLocalRandom.current().nextInt(0, bestMoves.size()));
					newHArr = hArr.clone();
				}
			}
		}
		for (int row = 0; row < n; row++)
		{	
			for (int col = 0; col < n; col++)
			{
				board[row][col] = '*';
			}
		}
		for (int col = 0; col < n; col++)
		{
			board[hArr[col]][col] = 'Q';
		}
		return board;
	}
//-----------------------------------------------------------------------
	public static char[][] csp(char[][] board, int[] hArr, int bestHCost, int n)
	{
		int[] newHArr = hArr.clone();
		int newHArrCost = 0;
		for (int row = 0; row < newHArr.length; row++)
		{
			for (int col = row + 1; col < newHArr.length; col++)
			{
				if ((newHArr[row] == newHArr[col]) ||
					 (newHArr[row] == (newHArr[col] - (col - row))) ||
					 (newHArr[row] == (newHArr[col] + (col - row))))
				{
					for (int i = 0; i < n; i++)
					{
						newHArr[row] = i;
						newHArrCost = calcHCost(newHArr);
						if (newHArrCost < bestHCost)
						{
							bestHCost = newHArrCost;
							hArr[row] = i;
						}
						if (i == n-1) newHArr = hArr.clone();
					}
				}
			}
		}
		for (int row = 0; row < n; row++)
		{	
			for (int col = 0; col < n; col++)
			{
				board[row][col] = '*';
			}
		}
		for (int col = 0; col < n; col++)
		{
			board[hArr[col]][col] = 'Q';
		}
		return board;
	}
//-----------------------------------------------------------------------	
} // end class NQueen
/////////////////////////////////////////////////////////////////////////