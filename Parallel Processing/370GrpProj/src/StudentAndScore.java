//Members: Danzel, Alan, Roberto
//
//CS 370, Spring 2016, Group Project (SEQUENTIAL).
//
import java.util.*;
/////////////////////////////////////////////////////////////////////////

// USED TO PRESERVE STUDENT'S INITIAL ROW BEFORE SORTING
public class StudentAndScore implements Comparable<StudentAndScore>
{
	public final int student;
	public final float score;
//-----------------------------------------------------------------------	
	public StudentAndScore(int student, float score)
	{
		this.student = student;
		this.score = score;
	}
//-----------------------------------------------------------------------
	@Override
	public int compareTo(StudentAndScore other)
	{
		return Float.valueOf(this.score).compareTo(other.score);
	}
//-----------------------------------------------------------------------
	float getScore()
	{
		return score;
	}
//-----------------------------------------------------------------------
	int getStudent()
	{
		return student;
	}
//-----------------------------------------------------------------------
} // end class StudentAndScore
/////////////////////////////////////////////////////////////////////////