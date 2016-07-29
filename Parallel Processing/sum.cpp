#include "mpi.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <iostream>

#define TRIALS 20
#define ARRAY_SIZE 1000000

using namespace std;

int main (int argc,  char *argv[])
{
 int myid, numprocs;
 double startwtime, endwtime;
 int namelen;
 int* numbers = new int[ARRAY_SIZE];
 int i, j, sum, part_sum;
 int s, s0, startIndex, endIndex;
 double totalTime;

 char processor_name[MPI_MAX_PROCESSOR_NAME];

 MPI_Init(&argc, &argv);
 MPI_Comm_size(MPI_COMM_WORLD, &numprocs);
 MPI_Comm_rank(MPI_COMM_WORLD, &myid);
 MPI_Get_processor_name(processor_name, &namelen);
 
 fprintf(stderr, "process %d on %s\n", myid, processor_name);
 fflush(stderr);  
 
 for (int i=0; i<ARRAY_SIZE; i++)
    numbers[i] = i;  //could be randomly generated

 if (myid == 0) 
 {
   s = (int)floor(ARRAY_SIZE/numprocs);
   s0 = s + ARRAY_SIZE%numprocs;
   //printf("s=%d , s0= %d\n", s, s0);
 }

 MPI_Bcast(&s, 1, MPI_INT, 0, MPI_COMM_WORLD);
 MPI_Bcast(&s0, 1, MPI_INT, 0, MPI_COMM_WORLD);

 double inCircle = 0.0;
 double inSquare = 10000.0;
 float x, y = 0.0;
 srand(static_cast <unsigned> (time(0)));
 int numPoints = 10000;

 

 if (myid == 0) //master;s work
 {  //compute sum of master's numbers
    for (int i = 1; i <= numPoints; i++)
    {
      x = static_cast <float> (rand()) / static_cast <float> (RAND_MAX); 
      y = static_cast <float> (rand()) / static_cast <float> (RAND_MAX); 

      if (((x*x) + (y*y)) <= 1) inCircle++;
    }
 }
 else //slave's work
 {
    for (int i = 1; i <= numPoints; i++)
    {
      x = static_cast <float> (rand()) / static_cast <float> (RAND_MAX); 
      y = static_cast <float> (rand()) / static_cast <float> (RAND_MAX); 

      if (((x*x) + (y*y)) <= 1) inCircle++;
    }
 }
  

  if (myid == 0)
  {
    cout << "Number in Circle = " << inCircle << "\n";
    cout << "Pi = " << (double)((4 * inCircle) / inSquare) << "\n";
  }
  MPI_Finalize();

 }

