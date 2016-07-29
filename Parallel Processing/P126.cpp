#include "mpi.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <iostream>
#include <ctime>
#include <cstdlib>
#include <cmath>

#define ARRAY_SIZE1 20
#define ARRAY_SIZE2 200
#define ARRAY_SIZE3 20000

using namespace std;

int main (int argc,  char *argv[])
{
	int myid, numprocs;
 	int namelen;
 	double startwtime, endwtime; 
 	double totalTime;
 	int cntr = 0;
 	int BV[ARRAY_SIZE1][ARRAY_SIZE1];
 	int row[ARRAY_SIZE1][ARRAY_SIZE1];
	char processor_name[MPI_MAX_PROCESSOR_NAME];

	MPI_Init(&argc, &argv);
 	MPI_Comm_size(MPI_COMM_WORLD, &numprocs);
 	MPI_Comm_rank(MPI_COMM_WORLD, &myid);
 	MPI_Get_processor_name(processor_name, &namelen);
 	/* TEST 1
	int blksz = (int) ceil((double) ARRAY_SIZE1/numprocs);
	if (myid == 0) 
 	{
  		for(int i  = 0; i < ARRAY_SIZE1; i++)
  		{
  			for (int j = 0; j < ARRAY_SIZE1; j++) BV[i][j] = rand() % 2;
    	}
	}
	MPI_Scatter(BV, blksz*ARRAY_SIZE1, MPI_INT, row, blksz*ARRAY_SIZE1, MPI_INT, 0, MPI_COMM_WORLD);
	for (int i = 0; i < ARRAY_SIZE1/numprocs; i++)
	{	
		for (int j = 0; j < ARRAY_SIZE1; j++)
		{
			if (row[i][j] == 1) cntr++;
			if (j == ARRAY_SIZE1-1) 
			{
				if (cntr % 2 == 0) cout << "   Parity bit: 1"<< "\n";
				else cout << "   Parity bit: 0" << "\n";
			}
			cout << row[i][j];
		}
	}
	*/
	// TEST 2
	int blksz = (int) ceil((double) ARRAY_SIZE2/numprocs);
	int parArr[ARRAY_SIZE2];
	int k = 0;
	if (myid == 0) 
 	{
  		for(int i  = 0; i < ARRAY_SIZE2; i++)
  		{
  			for (int j = 0; j < ARRAY_SIZE2; j++) BV[i][j] = rand() % 2;
    	}
	}
	MPI_Scatter(BV, blksz*ARRAY_SIZE2, MPI_INT, row, blksz*ARRAY_SIZE2, MPI_INT, 0, MPI_COMM_WORLD);
	for (int i = 0; i < ARRAY_SIZE2/numprocs; i++)
	{	
		for (int j = 0; j < ARRAY_SIZE2; j++)
		{
			if (row[i][j] == 1) cntr++;
			if (j == ARRAY_SIZE2-1) 
			{
				if (cntr % 2 == 0)
				{
					parArr[k] = 1;
					k++;
				}
				else 
				{
					parArr[k] = 0;
					k++;
				}
			}
		}
	}
	MPI_Gather(row, blksz*ARRAY_SIZE3, MPI_INT, parArr, blksz*ARRAY_SIZE3, MPI_INT, 0, MPI_COMM_WORLD);
	if (myid == 0)
	{
		for (int m = 0; m < ARRAY_SIZE2; m++)
		{
			cout << parArr[m];
		}
	}
	//

	/* TEST 3
	int blksz = (int) ceil((double) ARRAY_SIZE3/numprocs);
	if (myid == 0) 
 	{
  		for(int i  = 0; i < ARRAY_SIZE3; i++)
  		{
  			for (int j = 0; j < ARRAY_SIZE3; j++) BV[i][j] = rand() % 2;
    	}
	}
	MPI_Scatter(BV, blksz*ARRAY_SIZE3, MPI_INT, row, blksz*ARRAY_SIZE3, MPI_INT, 0, MPI_COMM_WORLD);
	MPI_Gather(row, blksz*ARRAY_SIZE3, MPI_INT, BV, blksz*ARRAY_SIZE3, MPI_INT, 0, MPI_COMM_WORLD);
	*/
	MPI_Finalize();
}
