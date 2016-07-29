#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <iostream>
#include <ctime>
#include <cstdlib>
#include <cmath>

using namespace std;

int main ()
{
	/*
	int bv[100][100];
	for(int i  = 0; i < 100; i++)
	{
		for(int j = 0; j < 100; j++) bv[i][j] = rand() % 2;
	}
	for(int i  = 0; i < 100; i++)
	{
		for(int j = 0; j < 100; j++) 
		{
			cout << bv[i][j];
		}
	}*/
	int parArr[10];
	for (int i = 0; i < parArr.length(); i++) parArr[i] = 1;
	for (int j = 0; j < parArr.length(); j++) cout << parArr[j];
	return 0;
}