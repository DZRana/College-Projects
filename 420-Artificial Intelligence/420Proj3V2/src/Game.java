//Written by Danzel Rana
//
//Solves CS 420, Spring 2016, Project 03.
//
import java.util.*;
import java.io.*;
/////////////////////////////////////////////////////////////////////////
public class Game
{
//-----------------------------------------------------------------------
	List<Coords> possibleCoords;
	char[][] board = createBoard();
	List<CoordsAndHVals> allHVals = new ArrayList<>();
//-----------------------------------------------------------------------
	public static char[][] createBoard()
	{
		char[][] board = new char[][] {"  1 2 3 4 5 6 7 8".toCharArray(),
												 "A - - - - - - - -".toCharArray(),
												 "B - - - - - - - -".toCharArray(),
												 "C - - - - - - - -".toCharArray(),
												 "D - - - - - - - -".toCharArray(),
												 "E - - - - - - - -".toCharArray(),
												 "F - - - - - - - -".toCharArray(),
												 "G - - - - - - - -".toCharArray(),
												 "H - - - - - - - -".toCharArray()};		
		return board;
	}
//-----------------------------------------------------------------------
	public void printBoard()
	{
		System.out.println();
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 17; col++)
			{
				System.out.print(board[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
//-----------------------------------------------------------------------
	public int evaluateBoard()
	{
		int twos = streaks(2, 'X');
		int threes = streaks(3, 'X');
		int fours = streaks(4, 'X');
		int computerTwos = streaks(2, 'O');
		int computerThrees = streaks(3, 'O');
		int computerFours = streaks(4, 'O');
		if (computerFours > 0) return ((computerTwos * -5) + (computerThrees * -500) + (computerFours * -500000));
		else return ((twos * 5) + (threes * 500) + (fours * 500000));
	}
//-----------------------------------------------------------------------
	public int streaks(int streakCnt, char playerChar)
	{
		int numStreaks = 0;
		for (int row = 1; row < 9; row++)
		{
			for (int col = 2; col < 17; col+=2)
			{
				if (board[row][col] == playerChar)
				{
					numStreaks += horizontals(row, col, streakCnt);
					numStreaks += verticals(row, col, streakCnt);
					numStreaks += diagonals(row, col, streakCnt);
				}
			}
		}
		return numStreaks;
	}
//-----------------------------------------------------------------------
	public int horizontals(int row, int col, int streakCnt)
	{
		int inARow = 0;
		for (int j = col; j < 17; j+=2)
		{
			if (board[row][j] == board[row][col]) inARow++;
			else break;
		}
		if (inARow >= streakCnt) return 1;
		else return 0;
	}
//-----------------------------------------------------------------------
	public int verticals(int row, int col, int streakCnt)
	{
		int inARow = 0;
		for (int i = row; i < 9; i++)
		{
			if (board[i][col] == board[row][col]) inARow++;
			else break;
		}
		if (inARow >= streakCnt) return 1;
		else return 0;
	}
//-----------------------------------------------------------------------
	public int diagonals(int row, int col, int streakCnt)
	{
		int diags = 0;
		int inARow = 0;
		int j = col;
		for (int i = row; i < 9; i++)
		{
			if (j > 9) break;
			else if (board[i][j] == board[row][col]) inARow++;
			else break;
			j+=2;
		}
		if (inARow >= streakCnt) diags++;
		
		inARow = 0;
		j = col;
		for (int i = row; i < -1; i--)
		{
			if (j > 9) break;
			else if (board[i][j] == board[row][col]) inARow++;
			else break;
			j+=2;
		}
		if (inARow >= streakCnt) diags++;
		return diags;
	}
//-----------------------------------------------------------------------
	public Coords getBestMove()
	{
		double max = Double.NEGATIVE_INFINITY;
		int best = 0;
		for (int i = 0; i < allHVals.size(); i++)
		{
			if (max < allHVals.get(i).hVal)
			{
				max = allHVals.get(i).hVal;
				best = i;
			}
		}
		Coords bestMove = allHVals.get(best).coords;
		char r = (char)(bestMove.row + 96);
		int c = bestMove.col / 2;
		System.out.println("Move made: " + r + c);
		return bestMove;
	}
//-----------------------------------------------------------------------
	// player = true if Player, player = false if Computer
	public double alphaBeta(double alpha, double beta, int depth, boolean player, long duration)
	{
		if (beta <= alpha)
		{
			if (player == true) return Double.POSITIVE_INFINITY;
			else return Double.NEGATIVE_INFINITY;
		}
		if (System.currentTimeMillis() > duration || gameEnd()) return evaluateBoard();
		List<Coords> coordsAvailable = getPossibleBoards();
		if (coordsAvailable.isEmpty()) return 0;
		if (depth == 0) allHVals.clear();
		double maxVal = Double.NEGATIVE_INFINITY;
		double minVal = Double.POSITIVE_INFINITY;
		for(int i = 0; i < coordsAvailable.size(); i++)
		{
			Coords coords = coordsAvailable.get(i);
			double currentHVal = 0;
			if (player == true)
			{
				markBoard(coords, true);
				currentHVal = alphaBeta(alpha, beta, depth + 1, false, duration);
				maxVal = Math.max(maxVal, currentHVal);
				alpha = Math.max(currentHVal, alpha);
				if (depth == 0) allHVals.add(new CoordsAndHVals(coords, currentHVal));
			}
			else if (player == false)
			{
				markBoard(coords, false);
				currentHVal = alphaBeta(alpha, beta, depth + 1, true, duration);
				minVal = Math.min(minVal, currentHVal);
				beta = Math.min(currentHVal, beta);
			}
			board[coords.row][coords.col] = '-';
			if (currentHVal == Double.NEGATIVE_INFINITY || currentHVal == Double.POSITIVE_INFINITY)
				break;
		}
		if (player == true) return maxVal;
		else return minVal;
	}
//-----------------------------------------------------------------------
	public boolean checkWin(char playerChar)
	{
		if (streaks (4, playerChar) > 0) return true;
      return false;
	}
//-----------------------------------------------------------------------
	public boolean playerWin()
	{
		return checkWin('O');
	}
//-----------------------------------------------------------------------
	public boolean compWin()
	{
		return checkWin('X');
	}
//-----------------------------------------------------------------------
	public boolean gameEnd()
	{
		return playerWin() || compWin() || getPossibleBoards().isEmpty();
	}
//-----------------------------------------------------------------------
	public List<Coords> getPossibleBoards()
	{
		possibleCoords = new ArrayList<>();
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 17; col++)
			{
				if (board[row][col] == '-') possibleCoords.add(new Coords(row, col));
			}
		}
		return possibleCoords;
	}
//-----------------------------------------------------------------------
	public void markBoard(Coords coords, boolean player)
	{
		if (player == true) board[coords.row][coords.col] = 'X';
		else board[coords.row][coords.col] = 'O';
	}
//-----------------------------------------------------------------------
} // end class Game
/////////////////////////////////////////////////////////////////////////