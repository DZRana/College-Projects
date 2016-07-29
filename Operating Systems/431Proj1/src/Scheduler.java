// Written by Danzel Rana
//
// Solves CS 431, Summer 2015, Project 1.
//
import java.io.*;
import java.util.*;
//////////////////////////////////////////////////////////////////////
public class Scheduler
{
//--------------------------------------------------------------------
	public static void main(String[] args) throws IOException
	{
		// Enter file to read from.	
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter file: ");
		String fileName = kb.nextLine();
		
		Reader jfile1 = new FileReader(fileName);
		BufferedReader br1 = new BufferedReader(jfile1);
		
		
		// Create array of user-defined jobs which include job number and burst time.
		ArrayList<Job> jobs = new ArrayList<Job>();
		
		// Read file and add jobs to array.
		while (true)
		{	
			String tmp = br1.readLine();
			if (tmp == null) break;
			
			// Cutting out the letters in the file to make it easier to work with.
			tmp = tmp.substring(3);
			jobs.add(new Job(Integer.parseInt(tmp), Integer.parseInt(br1.readLine())));
		}
		br1.close();
		
		// Create duplicate arrays for each scheduling algorithm.
		ArrayList<Job> jobsFCFS = new ArrayList<Job>(jobs);
		ArrayList<Job> jobsSJF = new ArrayList<Job>(jobs);
		ArrayList<Job> jobsRR2 = new ArrayList<Job>(jobs);
		
		
		// Run all algorithms.
		FCFS (jobsFCFS);
		SJF (jobsSJF);
		RR2 (jobsRR2);
		
		Reader jfile2 = new FileReader(fileName);
		BufferedReader br2 = new BufferedReader(jfile2);
		ArrayList<Job> jobsRR3 = new ArrayList<Job>();
		while (true)
		{	
			String tmp = br2.readLine();
			if (tmp == null) break;
			
			tmp = tmp.substring(3);
			jobsRR3.add(new Job(Integer.parseInt(tmp), Integer.parseInt(br2.readLine())));
		}
		br2.close();
		
		RR3 (jobsRR3);
	}
//--------------------------------------------------------------------
	static void FCFS (ArrayList jobs)
	{
		int totalTime = 0;
		int[] totalTimeArr = new int[jobs.size()];
		int ndx = 0;
		int cpuTime = 0;
		int numJobs = jobs.size();
		
		System.out.println("\nFCFS:");
		
		// Walk through all jobs.
		while (!jobs.isEmpty())
		{
			Job j = (Job) jobs.remove(0);
			System.out.print("Job" + j.jobNum + ": " + cpuTime + "ms");
			cpuTime += j.burstTime; // Keep track of the time after each job completion.
			totalTime += cpuTime; // Keep track of total completion time.
			totalTimeArr[ndx] = cpuTime; // Used for displaying job completions 
			ndx++;
			System.out.print(" + "+ j.burstTime + "ms = " + cpuTime + "ms\n");
		}
		System.out.print("\nAvg. completion time: ");
		for (int i = 0; i < totalTimeArr.length; i++) System.out.print(totalTimeArr[i] + "ms + ");
		
		// Calculate avg. completion time.
		System.out.print( " = " + totalTime + "ms/" + numJobs + "ms = " + ((totalTime * 1.0) / numJobs) + "ms\n");
	}
//--------------------------------------------------------------------
	static void SJF (ArrayList jobs)
	{
		int totalTime = 0;
		int[] totalTimeArr = new int[jobs.size()];
		int ndx = 0;
		int cpuTime = 0;
		int numJobs = jobs.size();
		
		System.out.println("\nSJF:");
		
		// Sort all jobs by shortest job first through comparison of burst times.
		Job[] jobsArr = (Job[]) jobs.toArray(new Job[jobs.size()]);
	    Comparator<Job> c = new Comparator<Job>()
	    {
	    	public int compare(Job j1, Job j2)
	    	{
	    		return (j1.burstTime - j2.burstTime);
	    	}
	    };
	    Arrays.sort(jobsArr, c);
	    
	    // Walk through sorted job array and run FCFS algorithm.
	    for (int i = 0; i < jobsArr.length; i++)
	    {
	    	Job j = jobsArr[i];
	    	System.out.print("Job" + j.jobNum + ": " + cpuTime + "ms");
	    	cpuTime += j.burstTime;
	    	totalTime += cpuTime;
	    	totalTimeArr[ndx] = cpuTime;
			ndx++;
	    	System.out.print(" + "+ j.burstTime + "ms = " + cpuTime + "ms\n");
	    }
	    System.out.print("\nAvg. completion time: ");
		for (int i = 0; i < totalTimeArr.length; i++) System.out.print(totalTimeArr[i] + "ms + ");
		
		// Calculate avg. completion time.
		System.out.print( " = " + totalTime + "ms/" + numJobs + "ms = " + ((totalTime * 1.0) / numJobs) + "ms\n");
	}
//--------------------------------------------------------------------
	static void RR2 (ArrayList jobs)
	{
		int numJobs = jobs.size();
		int timeSlice = 2;
	    int currentTime = 0;
	    int[] totalTimeArr = new int[jobs.size()];
		int ndx = 0;
	    int totalTime = 0;
	    
	    System.out.println("\nRR-2:");
	    
	    // Walk through all jobs.
	    while (!jobs.isEmpty())           
	    {
	    	Job j = (Job) jobs.remove(0);    
	    	System.out.print("Job" + j.jobNum + ": ");
	    	
	    	// Check to see if the remaining burst time is greater than the time slice.
	    	if (j.burstTime > timeSlice)
	    	{
	    		currentTime += timeSlice;
	    		j.burstTime -= timeSlice;
	    		jobs.add(j);                // If there's burst time remaining and it's
	    									// greater than the time slice, add the job
	    									// back to the queue, making it circular.
	    	}
	    	
	    	// If not, complete it.
	    	else
	    	{
	    		currentTime += j.burstTime;
	    		j.burstTime -= timeSlice;
	    		totalTime += currentTime;
	    	}
	    	if (j.burstTime <= 0) // Mainly used for display purposes.
	    	{
	    		j.burstTime = 0;
	    		totalTimeArr[ndx] = currentTime;
				ndx++;
	    	}
	    	System.out.print("Current Time: " + currentTime + "ms Burst Time Left: " + j.burstTime + "ms\n");
	    }
	    System.out.print("\nAvg. completion time: ");
		for (int i = 0; i < totalTimeArr.length; i++) System.out.print(totalTimeArr[i] + "ms + ");
		
		// Calculate avg. completion time.
		System.out.print( " = " + totalTime + "ms/" + numJobs + "ms = " + ((totalTime * 1.0) / numJobs) + "ms\n");
	}
//--------------------------------------------------------------------
	static void RR3 (ArrayList jobs)
	{
		int numJobs = jobs.size();
		int timeSlice = 3;
	    int currentTime = 0;
	    int[] totalTimeArr = new int[jobs.size()];
		int ndx = 0;
	    int totalTime = 0;
	    
	    System.out.println("\nRR-3:");
	    
	    // Walk through all jobs.
	    while (!jobs.isEmpty())           
	    {
	    	Job j = (Job) jobs.remove(0);      
	    	System.out.print("Job" + j.jobNum + ": ");
	    	
	    	// Check to see if the remaining burst time is greater than the time slice.
	    	if (j.burstTime > timeSlice)
	    	{
	    		currentTime += timeSlice;
	    		j.burstTime -= timeSlice;
	    		jobs.add(j);                // If there's burst time remaining and it's
											// greater than the time slice, add the job
											// back to the queue, making it circular.
	    	}
	    	
	    	// If not, complete it.
	    	else
	    	{
	    		currentTime += j.burstTime;
	    		j.burstTime -= timeSlice;
	    		totalTime += currentTime;
	    	}
	    	if (j.burstTime <= 0) 	// Mainly used for display purposes.
	    	{
	    		j.burstTime = 0;
	    		totalTimeArr[ndx] = currentTime;
				ndx++;
	    	}
	    	System.out.print("Current Time: " + currentTime + "ms Burst Time Left: " + j.burstTime + "ms\n");
	    }
	    System.out.print("\nAvg. completion time: ");
		for (int i = 0; i < totalTimeArr.length; i++) System.out.print(totalTimeArr[i] + "ms + ");
		
		// Calculate avg. completion time.
		System.out.print( " = " + totalTime + "ms/" + numJobs + "ms = " + ((totalTime * 1.0) / numJobs) + "ms\n");
	}
//--------------------------------------------------------------------
} // end class Scheduler
//////////////////////////////////////////////////////////////////////
class Job // Allows for each job element to retain both its job number and burst time.
{
	public int jobNum;
	public int burstTime;
//--------------------------------------------------------------------
	public Job(int jobNum, int burstTime)
	{
		this.jobNum = jobNum;
		this.burstTime = burstTime;
	}
//--------------------------------------------------------------------
} // end class Job
//////////////////////////////////////////////////////////////////////
