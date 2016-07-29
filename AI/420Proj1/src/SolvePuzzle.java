//Written by Danzel Rana
//
//Solves CS 420, Spring 2016, Project 01.
//
import java.util.*;
import java.io.*;
/////////////////////////////////////////////////////////////////////////
public class SolvePuzzle 
{
	PriorityQueue<Node> frontier;
	HashMap<String, Integer> explored;
	String goalConfig = "012345678";
	int totalNodes = 0;
	boolean heuristic;
	char[] goalArr = goalConfig.toCharArray();
//-----------------------------------------------------------------------	
	public SolvePuzzle(Node start, boolean heuristic)
	{
		frontier = new PriorityQueue<Node>(1, new NodeComparator());
		explored = new HashMap<String, Integer>();
		this.heuristic = heuristic;
		setH(start);
		frontier.add(start);
	}
//-----------------------------------------------------------------------	
	//public int solve()
	//{
	public void solve()
	{
		Node current;
		
		while (!frontier.isEmpty())
		{
			current = frontier.poll();
			
			if (current.getConfig().equals(goalConfig))
			{
				ArrayList<Node> depth = new ArrayList<Node>();
				depth.add(current);
				while (current.getParent() != null) 
				{
					depth.add(current.getParent());
					current = current.getParent();
				}
				for (int i = depth.size() - 1; i > -1; i--) System.out.println("Configuration: " + depth.get(i).getConfig());
				System.out.println("Depth: " + (depth.size() - 1));
				if (heuristic == true) System.out.println("A*(h1): " + totalNodes);
				else System.out.println("A*(h2): " + totalNodes);
				//return totalNodes;
				return;
			}
			
			if (!(explored.containsKey(current.getConfig())))
			{
				explored.put(current.getConfig(), current.getTotalCost(heuristic));
				
				ArrayList<char[]> successors = new ArrayList<char[]>();
				char[] currentConfig = current.getConfig().toCharArray();
				
				int a = 0;
				int b = 0;
				for (int j = 0; j < 3; j++) 
				{
					for (int i = 0; i < 3; i++) 
					{
						if ((currentConfig[(i * 3) + j]) == '0') 
						{
							a = i;
							b = j;
						}
					}
				}
				switchTiles(currentConfig, a, b, a + 1, b, successors);												
				switchTiles(currentConfig, a, b, a - 1, b, successors);
				switchTiles(currentConfig, a, b, a, b + 1, successors); 
				switchTiles(currentConfig, a, b, a, b - 1, successors);
				addSuccessors(current, successors);
			}
		}
		//return 0;
	}
//-----------------------------------------------------------------------	
	public void switchTiles(char[] config, int aPos, int bPos, int newAPos, int newBPos,  ArrayList<char[]> successors)
	{
		char[] currentConfig = Arrays.copyOf(config, config.length);

		if (newAPos > 2 || newBPos > 2) return;													
		else if (newAPos < 0 || newBPos < 0) return;

		int oldPos = (aPos * 3) + bPos;
		int newPos = (newAPos * 3) + newBPos;
		char temp = currentConfig[newPos];
		currentConfig[newPos] = currentConfig[oldPos];
		currentConfig[oldPos] = temp;

		successors.add(currentConfig);
		totalNodes++;
	}
//-----------------------------------------------------------------------	
	public void addSuccessors(Node parent, ArrayList<char[]> successors)
	{
		Node newN;
		char[] currentConfig = parent.getConfig().toCharArray();
		String nextConfig = "";
		int nextCost;

		for (int i = 0; i < successors.size(); i++) 
		{
			currentConfig = successors.get(i);
			for (int j = 0; j < currentConfig.length; j++) nextConfig = nextConfig + currentConfig[j];
			nextCost = parent.getGCost();
			newN = new Node(nextConfig, nextCost + 1);
			setH(newN);

			if (explored.containsKey(newN.getConfig())) 
			{
				if (explored.get(newN.getConfig()) > newN.getTotalCost(heuristic)) 
				{
					explored.remove(newN.getConfig());
					explored.put(newN.getConfig(), newN.getTotalCost(heuristic));
				}
			} 
			else 
			{
				frontier.add(newN);
				newN.setParent(parent);
			}
			nextConfig = "";
		}
	}
//-----------------------------------------------------------------------	
   void setH(Node n)
   {
   	if (heuristic == true)
		{
			char[] currentConfig = n.getConfig().toCharArray();

			int cntr = 0;
			for (int i = 0; i < goalArr.length; i++) 
			{
				if (currentConfig[i] == '0') continue;
				if (currentConfig[i] != goalArr[i]) cntr++;
			}
			n.setNumMisplaced(cntr);
		}
		else
		{
			char[] currentConfig = n.getConfig().toCharArray();	
			char currentNum;
			int manhattan = 0;
			int iPos = 0;
			int jPos = 0;
			for (int i = 0; i < 3; i++) 
			{
				for (int j = 0; j < 3; j++) 
				{
					currentNum = currentConfig[(i * 3) + j];
					if (currentNum != '0')
					{
						iPos = Integer.parseInt(Character.toString(currentNum)) / 3;
						jPos = Integer.parseInt(Character.toString(currentNum)) % 3;
						manhattan += Math.abs(iPos - i) + Math.abs(jPos - j);
					}
				}		
			}
			n.setManhattan(manhattan);
		}
   }
//-----------------------------------------------------------------------
	public class NodeComparator implements Comparator<Node>
	{
		public int compare(Node x, Node y)
		{
			return x.getTotalCost(heuristic) - y.getTotalCost(heuristic);
		}
	}
//-----------------------------------------------------------------------
} // end class SolvePuzzle
/////////////////////////////////////////////////////////////////////////