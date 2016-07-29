//Written by Danzel Rana
//
//Solves CS 420, Spring 2016, Project 01.
//
/////////////////////////////////////////////////////////////////////////
public class Node 
{
	String config;
	int gCost;
	int numMisplaced;
	int manhattan;
	Node parent;
//-----------------------------------------------------------------------	
	public Node(String puzzle, int gCost)
	{
		config = puzzle;
		this.gCost = gCost;
	}
//-----------------------------------------------------------------------	
	int getGCost()
	{
		return gCost;
	}
//-----------------------------------------------------------------------	
	int getTotalCost(boolean heuristic)
	{
		if (heuristic == true) return gCost + numMisplaced;
		else return gCost + manhattan;
	}
//-----------------------------------------------------------------------	
	String getConfig()
	{
		return config;
	}
//-----------------------------------------------------------------------	
	void setParent(Node parent)
	{
		this.parent = parent;
	}
//-----------------------------------------------------------------------	
	Node getParent()
	{
		return parent;
	}
//-----------------------------------------------------------------------	
	void setNumMisplaced(int numMisplaced)
	{
		this.numMisplaced = numMisplaced;
	}
//-----------------------------------------------------------------------	
	void setManhattan(int manhattan)
	{
		this.manhattan = manhattan;
	}
//-----------------------------------------------------------------------	
} // end class Node
/////////////////////////////////////////////////////////////////////////