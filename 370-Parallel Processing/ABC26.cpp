#include "mpi.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <iostream>
#include <ctime>
#include <cstdlib>
#include <cmath>

#define ARRAY_SIZE 100000000
#define ARRAY_SIZE2 8000
#define ARRAY_SIZE3 100000000
using namespace std;

int main (int argc,  char *argv[])
{
	int myid, numprocs;
 	int namelen;
 	int s, s0, startIndex, endIndex;
 	double startwtime, endwtime; 
 	double totalTime;
 	//TASK 3: int* elements = new int[ARRAY_SIZE3];
 	srand(time(0));

	char processor_name[MPI_MAX_PROCESSOR_NAME];

	MPI_Init(&argc, &argv);
 	MPI_Comm_size(MPI_COMM_WORLD, &numprocs);
 	MPI_Comm_rank(MPI_COMM_WORLD, &myid);
 	MPI_Get_processor_name(processor_name, &namelen);
 
 	fprintf(stderr, "process %d on %s\n", myid, processor_name);
	fflush(stderr);  

 	//TASK 1,2: totalTime = 0;
 	
	/*for (int i = 0; i < 5; i++)
	{
		// TASK 1
		if (myid == 0) startwtime = MPI_Wtime();
		if (myid == 0)  //master's work
 		{
   			int* elements = new int[ARRAY_SIZE];
			for (int i = 0; i < ARRAY_SIZE; i++) elements[i] = rand() % 100;
			MPI_Send(&elements, 1, MPI_INT, 1, 0, MPI_COMM_WORLD);
			MPI_Recv(&elements, 1, MPI_INT, 1, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
		
 		}
 		else if (myid == 1)//slave's work
 		{
   			int* elements = new int[ARRAY_SIZE];
			MPI_Recv(&elements, 1, MPI_INT, 0,0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
			MPI_Send(&elements, 1, MPI_INT, 0,1, MPI_COMM_WORLD);
 		}

		//MPI_Reduce(&inCircle, &totalInCircle, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

		if (myid == 0)
  		{
   			double runTime;
   			endwtime = MPI_Wtime();
   			runTime = (endwtime - startwtime)/2;
   			totalTime += runTime;
   			cout << "Communication cost: " << runTime << "\n";
  		} 

		// TASK 2
		if (myid == 0) startwtime = MPI_Wtime();
		if (myid == 0)  //master's work
 		{
   			int elements[ARRAY_SIZE2][ARRAY_SIZE2];
			for (int i = 0; i < ARRAY_SIZE2; i++) 
			{
				for (int j = 0; j < ARRAY_SIZE2; j++)
				{
					elements[i][j] = rand() % 100;
				}
			}
			MPI_Send(&elements[0][0], 1, MPI_INT, 1, 0, MPI_COMM_WORLD);
			MPI_Recv(&elements[0][0], 1, MPI_INT, 1, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
		
 		}
 		else if (myid == 1)//slave's work
 		{
   			int elements[ARRAY_SIZE2][ARRAY_SIZE2];
			MPI_Recv(&elements[0][0], 1, MPI_INT, 0,0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
			MPI_Send(&elements[0][0], 1, MPI_INT, 0,1, MPI_COMM_WORLD);
 		}

		//MPI_Reduce(&inCircle, &totalInCircle, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

		if (myid == 0)
  		{
   			double runTime;
   			endwtime = MPI_Wtime();
   			runTime = (endwtime - startwtime)/2;
   			totalTime += runTime;
   			cout << "Communication cost: " << runTime << "\n";
  		}
  	}*/

	// TASK 3
	for (int i = 0; i < 5; i++)
	{
		if (myid == 0) 
 		{
   			for (int i = 0; i < ARRAY_SIZE3; i++) elements[i] = rand() % 100;
		}
 	
 		MPI_Bcast(&elements, 1, MPI_INT, 0, MPI_COMM_WORLD);

 		totalTime = 0;
 	
		if (myid == 0) startwtime = MPI_Wtime();

 		else//slave's work
 		{
   			int* elements = new int[ARRAY_SIZE3];
 		}

		if (myid == 0)
  		{
   			double runTime;
   			endwtime = MPI_Wtime();
   			runTime = (endwtime - startwtime)/2;
   			totalTime += runTime;
   			cout << "Communication cost: " << runTime << "\n";
  		}
	}
	cout << "Avg. Cost: " << (totalTime / 5) << "\n\n";
	MPI_Finalize();
}
