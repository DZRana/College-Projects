#include <iostream>
#include <cstdlib>
#include <ctime>
#include <cmath>
using namespace std;

int main()
{
	double inCircle, inSquare = 0.0;
	float x, y = 0.0;
	srand(static_cast <unsigned> (time(0)));
	int numPoints = 0;

	cout << "Number of points: ";
	cin >> numPoints;

	for (int i = 1; i <= numPoints; i++)
	{
		x = static_cast <float> (rand()) / static_cast <float> (RAND_MAX); 
		y = static_cast <float> (rand()) / static_cast <float> (RAND_MAX); 

		inSquare++;

		if (((x*x) + (y*y)) <= 1) inCircle++;
	}
	cout << "Number in Circle = " << inCircle << "\n";
	cout << "Pi = " << (double)((4 * inCircle) / inSquare) << "\n";
	return 0;
}