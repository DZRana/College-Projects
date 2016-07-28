#include "mpi.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <iostream>

#define DOTS 100000

using namespace std;

int main (int argc,  char *argv[])
{
 int myid, numprocs;
 int namelen;
 int i;
 int s, s0, startIndex, endIndex;
 double startwtime, endwtime; 
 double totalTime;

 double inCircle;
 double totalInCircle;
 double inSquare = 100000.0;
 float x, y = 0.0;
 srand(static_cast <unsigned> (time(0)));

 char processor_name[MPI_MAX_PROCESSOR_NAME];

 MPI_Init(&argc, &argv);
 MPI_Comm_size(MPI_COMM_WORLD, &numprocs);
 MPI_Comm_rank(MPI_COMM_WORLD, &myid);
 MPI_Get_processor_name(processor_name, &namelen);
 
 fprintf(stderr, "process %d on %s\n", myid, processor_name);
 fflush(stderr);  
 
 if (myid == 0) 
 {
   s = (int)floor(DOTS/numprocs);
   s0 = s + DOTS%numprocs;
   //printf("s=%d , s0= %d\n", s, s0);
 }

 MPI_Bcast(&s, 1, MPI_INT, 0, MPI_COMM_WORLD);
 MPI_Bcast(&s0, 1, MPI_INT, 0, MPI_COMM_WORLD);

 startIndex = s0 + (myid-1)*s;
 endIndex = startIndex + s;

 totalTime = 0;
if (myid == 0)
{
 startwtime = MPI_Wtime();
}
 
 inCircle = 0.0;
 totalInCircle = 0.0;
 
 if (myid == 0) //master's work
 {  
    for (i=0; i<s0; i++)
    {
      x = static_cast <float> (rand()) / static_cast <float> (RAND_MAX); 
      y = static_cast <float> (rand()) / static_cast <float> (RAND_MAX); 

      if (((x*x) + (y*y)) <= 1) inCircle += 1;
    }
 }
 else //slave's work
 {
    for (i= startIndex; i<endIndex; i++)
    {
      x = static_cast <float> (rand()) / static_cast <float> (RAND_MAX); 
      y = static_cast <float> (rand()) / static_cast <float> (RAND_MAX); 

      if (((x*x) + (y*y)) <= 1) inCircle += 1;
    }
 }

 MPI_Reduce(&inCircle, &totalInCircle, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD); 

  if(myid == 0)
  {
   double runTime;
   endwtime = MPI_Wtime();
   runTime = endwtime - startwtime;
   totalTime += runTime;
   cout << "Execution Time (sec): " << totalTime;
  }
  if (myid == 0)
  {
    cout << "\nNumber in Circle = " << totalInCircle << "\n";
    cout << "Pi = " << (double)((4 * totalInCircle) / inSquare) << "\n";
  }
  MPI_Finalize();

 }

