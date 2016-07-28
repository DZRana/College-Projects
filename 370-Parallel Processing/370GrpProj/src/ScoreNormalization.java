//Members: Danzel, Alan, Roberto
//
//CS 370, Spring 2016, Group Project (SEQUENTIAL).
//
import java.util.*;
import java.io.*;
/////////////////////////////////////////////////////////////////////////
public class ScoreNormalization
{
//-----------------------------------------------------------------------
	public static void main(String[] args) throws IOException
	{
		// GENERATE RAW SCORES
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter data file: ");
		String fileName = kb.nextLine();
		FileReader file1 = new FileReader(fileName);
		FileReader file2 = new FileReader(fileName);
		BufferedReader br1 = new BufferedReader(file1);
		BufferedReader br2 = new BufferedReader(file2);
		
		String row1 = "";
		int studentCnt = 1;
		row1 = br1.readLine();
		String[] scoresPerStudent = row1.split(",");
		while((row1 = br1.readLine()) != null) studentCnt++;
		br1.close();

		System.out.println("\nNumber of Students: " + studentCnt);
		System.out.println("Number of Scores per Student: " + scoresPerStudent.length);
		System.out.println();
		String row2 = "";
		int cntr = 0;
		float[][] rawScores = new float[studentCnt][scoresPerStudent.length];
		while ((row2 = br2.readLine()) != null)
		{
			String[] scores = row2.split(",");
			for (int i = 0; i < scores.length; i++)
			{
				rawScores[cntr][i] = Float.parseFloat(scores[i]);
			}
			cntr++;
		}
		br2.close();
		
		long startTime = System.nanoTime();
		// GENERATE INS FROM RAW SCORES
		StudentAndScore[] initialNormalizedScores = INS(rawScores, studentCnt, scoresPerStudent.length);
		
		// SORT SCORES
		Arrays.sort(initialNormalizedScores);
		
		// GENERATE FNS FROM INS
		StudentAndScore[] finalNormalizedScores = FNS(initialNormalizedScores, studentCnt, scoresPerStudent.length);
		long endTime = System.nanoTime();
	
		// FIRST PRINT METHOD
		print1(finalNormalizedScores);
		
		System.out.println("-------------------------------------------");
		
		// SECOND PRINT METHOD
		print2(finalNormalizedScores);
		
		System.out.println("Time Elapsed (sec): " + (endTime - startTime)/1000000000.0);
	}
//-----------------------------------------------------------------------
	public static StudentAndScore[] INS(float[][] rawScores, int studentCnt, int scoresPerLine)
	{
		StudentAndScore[] initialNormalizedScores = new StudentAndScore[studentCnt];
		float scoreSquares = 0;
		for (int row = 0; row < studentCnt; row++)
		{
			for (int col = 0; col < scoresPerLine; col++)
			{
				// ADD SQUARES OF ALL DATA VALUES IN A ROW
				scoreSquares += rawScores[row][col]*rawScores[row][col];
			}
			
			// GET AVERAGE OF ALL SQUARES
			initialNormalizedScores[row] = new StudentAndScore(row,scoreSquares/scoresPerLine);		
			scoreSquares = 0;
		}
		return initialNormalizedScores;
	}
//-----------------------------------------------------------------------
	public static StudentAndScore[] FNS(StudentAndScore[] initialNormalizedScores, int studentCnt, int scoresPerLine)
	{
		StudentAndScore[] finalNormalizedScores = new StudentAndScore[studentCnt];
		
		// ASSIGN FNS
		for (int i = 0; i < studentCnt; i++)
		{
			if (i < (studentCnt*.2))
				finalNormalizedScores[i] = new StudentAndScore(initialNormalizedScores[i].getStudent(), (float) 0.0);
			if (i >= (studentCnt*.2) && i < (studentCnt*.4))
				finalNormalizedScores[i] = new StudentAndScore(initialNormalizedScores[i].getStudent(), (float) 1.0);
			if (i >=  (studentCnt*.4) && i < (studentCnt*.7))
				finalNormalizedScores[i] = new StudentAndScore(initialNormalizedScores[i].getStudent(), (float) 2.0);
			if (i >=  (studentCnt*.7) && i < (studentCnt*.9))
				finalNormalizedScores[i] = new StudentAndScore(initialNormalizedScores[i].getStudent(), (float) 3.0);
			if (i >=  (studentCnt*.9) && i < studentCnt)
				finalNormalizedScores[i] = new StudentAndScore(initialNormalizedScores[i].getStudent(), (float) 4.0);
		}
		return finalNormalizedScores;
	}
//-----------------------------------------------------------------------
	public static void print1(StudentAndScore[] finalNormalizedScores)
	{
		for (int i = 0; i < finalNormalizedScores.length; i++) 
			System.out.println("Student " + finalNormalizedScores[i].getStudent() + ": " + finalNormalizedScores[i].getScore());
	}
//-----------------------------------------------------------------------
	public static void print2(StudentAndScore[] finalNormalizedScores)
	{
		System.out.println("0.0: ");
		for (int i = 0; i < finalNormalizedScores.length; i++)
		{
			if (finalNormalizedScores[i].getScore() == 0.0)
				System.out.println("Student " + finalNormalizedScores[i].getStudent());
		}
		System.out.println("1.0: ");
		for (int i = 0; i < finalNormalizedScores.length; i++)
		{
			if (finalNormalizedScores[i].getScore() == 1.0)
				System.out.println("Student " + finalNormalizedScores[i].getStudent());
		}
		System.out.println("2.0: ");
		for (int i = 0; i < finalNormalizedScores.length; i++)
		{
			if (finalNormalizedScores[i].getScore() == 2.0)
				System.out.println("Student " + finalNormalizedScores[i].getStudent());
		}
		System.out.println("3.0: ");
		for (int i = 0; i < finalNormalizedScores.length; i++)
		{
			if (finalNormalizedScores[i].getScore() == 3.0)
				System.out.println("Student " + finalNormalizedScores[i].getStudent());
		}
		System.out.println("4.0: ");
		for (int i = 0; i < finalNormalizedScores.length; i++)
		{
			if (finalNormalizedScores[i].getScore() == 4.0)
				System.out.println("Student " + finalNormalizedScores[i].getStudent());
		}
	}
//-----------------------------------------------------------------------
} // end class ScoreNormalization
/////////////////////////////////////////////////////////////////////////
