//Written by Danzel Rana
//
//Solves CS 241, Summer 2014, Homework 03.
//
import java.io.*;
import java.util.*;
//////////////////////////////////////////////////////////////////////
class GraphMatrix
{
//--------------------------------------------------------------------
	public GraphMatrix (int r, int c)
	{
		Vector vertices = new Vector();
		boolean directedOrUndirected;
		BitSet[] rows = new BitSet[r];
		for (int i = 0; i < r; i++)
			rows[i] = new BitSet(c);
	}
//--------------------------------------------------------------------
	public LinkedList[] convertLink()
	{
		LinkedList[] AL = new LinkedList[Name.size()];                 
	   for( int i = 0; i < Name.size(); i++)
	   	AL[i] = new LinkedList();
	   for( int i = 0; i < Name.size(); i++  ) 
	   {
	   	for( int j = 0; j < Name.size(); j++)
	   		if (Matrix[i].get(j)) AL[i].add( new Integer(j)) ;
	   }
	   return AL;       
	 }
//--------------------------------------------------------------------
} // end class GraphMatrix
/////////////////////////////////////////////////////////////////////////