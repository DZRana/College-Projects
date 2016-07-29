//------------------------------------------------------------------------------
// Project Name: Movie Statistics
// Written by: Danzel Rana
// Last Modified: 05/18/2014
//------------------------------------------------------------------------------

/** 
 * Instructor Feedback:
 *
 * You should deallocate dynamic memory yourself.
 *
 * -5
 *
 * You should make selection sort its own function
 *
 * -3
 *
 * Your code passed 16 out of 50 test cases.
 *
 * -17
*/

#include <iostream>
#include <fstream>

using namespace std;

int *MoviesSeen(int);
double Average(int[], int);
double Median(int[], int);
double Mode(int[], int);

ofstream results_file; // Defining file stream object.

int main(int argc, char *argv[])
{
  // Creating and opening a file to write to.
  if (argc < 2) return 1; // Handle argument count edge case.
  results_file.open (argv[1]);
  
  if (results_file.is_open())
  {
    int surveyed;
    int *sorted_survey_info;
  
    // Writing all the output to the file.
    results_file << "How many students were surveyed? ";
    cin >> surveyed;
    if (surveyed < 1) // Handling surveyed < 1 edge case.
    {
      results_file << "Number of students surveyed must be greater than 1.\n";
      return 0;
    }
    results_file << "Enter the number of movies each student saw.\n";
    sorted_survey_info = MoviesSeen(surveyed);
    if (sorted_survey_info == 0) // Handling movies seen < 0 edge case.
    {
      results_file << "Number of movies must be 0 or greater.\n";
      return 0;
    }

    // Writing Average, Median, and Mode results to file.
    results_file << "The average number of movies seen is: " <<
            Average(sorted_survey_info, surveyed) << "\n";
    results_file << "The median of the number of movies seen is: " <<
            Median(sorted_survey_info, surveyed) << "\n";
    results_file << "The mode of the number of movies seen is: " <<
            Mode(sorted_survey_info, surveyed) << "\n";

    results_file.close(); // Closes the file I was writing to.
  }
  else return 1;
}

// Creating a dynamic array and sorting it in the same function
int *MoviesSeen(int surveyed)
{
  int *surveyed_arr = new int[surveyed];

  for (int i = 1; i <= surveyed; i++)
  {
    results_file << "Student " << i << ": ";
    cin >> surveyed_arr[i-1];
    if (surveyed_arr[i-1] < 0)
      return 0;
  }

  // Selection Sort
  int start, minIndex, minVal;
  for (int start = 0; start < (surveyed - 1); start++)
  {
    minIndex = start;
    minVal = surveyed_arr[start];
    for (int index = start + 1; index < surveyed; index++)
    {
      if (surveyed_arr[index] < minVal)
      {
        minVal = surveyed_arr[index];
        minIndex = index;
      }
    }
    surveyed_arr[minIndex] = surveyed_arr[start];
    surveyed_arr[start] = minVal;
  }
  return surveyed_arr;
}

// Calculating the average.
double Average(int sorted_survey_info[], int surveyed)
{
  double total;
  double avg;

  for (int i = 0; i < surveyed; i++)
    total += sorted_survey_info[i];
  avg = total / surveyed;
  return avg;
}

// Calculating the median.
double Median(int sorted_survey_info[], int surveyed)
{
  double median;
  
  // If total surveyed % 2 = 0, then add the two middle numbers and divide by 2.
  if ((surveyed % 2) == 0)
  {
    median = (sorted_survey_info[surveyed / 2] + 
              sorted_survey_info[(surveyed / 2) - 1]) / 2.0;
  }
  else median = sorted_survey_info[surveyed / 2];
  return median;
}

// Calculating the mode. This doesn't quite work, and, for the life of me, I
// couldn't figure out what I did wrong as I this fails test 7,8,and 9. The
// algorithm seems right in my head, but with such large test cases, I
// couldn't quite pin where my problem lied. 
double Mode(int sorted_survey_info[], int surveyed)
{
  double num = sorted_survey_info[0];
  double mode = num;
  int cntr = 1;
  int max = 1;

  for (int i = 1; i < surveyed; i++)
  {
    if (sorted_survey_info[i] != num)
    {
      num = sorted_survey_info[i];
      cntr = 1;
    }
    else if (cntr > max)
    {
      max = cntr;
      mode = num;
    }
    else cntr++;
  }
  return mode;
}
