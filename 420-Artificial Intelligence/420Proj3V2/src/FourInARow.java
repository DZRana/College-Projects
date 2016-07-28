//Written by Danzel Rana
//
//Solves CS 420, Spring 2016, Project 03.
//
import java.util.*;
import java.io.*;
/////////////////////////////////////////////////////////////////////////
public class FourInARow
{
//-----------------------------------------------------------------------
	public static void main(String[] args)
	{
		Game g = new Game();
		Scanner kb = new Scanner(System.in);
		Random rnd = new Random();
		boolean player = false;
		
		System.out.print("Would you like to go first? (y/n): ");	
		if (kb.next().equals("y")) player = true;
		System.out.print("\nHow long should the computer think about its moves (in seconds)? : ");
		int sec = kb.nextInt();
		g.printBoard();

		if (player == false)
		{
			int[] rows = {1,2,3,4,5,6,7,8};
			int[] cols = {2,4,6,8,10,12,14,16};
			Coords randomFirstMove = new Coords(rows[rnd.nextInt(8)],cols[rnd.nextInt(8)]);
			g.markBoard(randomFirstMove, true);
			g.printBoard();
		}
		
		while (!g.gameEnd())
		{
			System.out.print("Choose your next move: ");
			String move = kb.next();
			char[] moveArr = move.toCharArray();
			int row = moveArr[0] - 96;
			int col = Character.getNumericValue(moveArr[1]) * 2;
			if (g.board[row][col] != '-')
			{
				System.out.println("\nSpot already filled!\n");
				continue;
			}
			Coords playerMove = new Coords(row, col);	
			g.markBoard(playerMove, false);
			g.printBoard();
			
			if (g.gameEnd()) break;
			
			long start = System.currentTimeMillis();
			long duration = start + (sec * 1000);
			g.alphaBeta(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0, true, duration);
			
			g.markBoard(g.getBestMove(), true);
			g.printBoard();
		}
		if (g.playerWin()) System.out.println("Player Wins!");
		else if (g.compWin()) System.out.println("Computer Wins!");
		else System.out.println("TIE!");
	}
//-----------------------------------------------------------------------
} // end class FourInARow
/////////////////////////////////////////////////////////////////////////