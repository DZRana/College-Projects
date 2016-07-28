//Written by Danzel Rana
//
//Solves CS 241, Summer 2014, Homework 02.
//
import java.io.*;
import java.util.*;
//////////////////////////////////////////////////////////////////////
class Node
{
	Node left;
	Node right;
	Node parent;
	int val;
//--------------------------------------------------------------------
	public Node(int val)
	{
		this.val = val;
	}
//--------------------------------------------------------------------
} // end class Node
/////////////////////////////////////////////////////////////////////////
class Hw02
{
//--------------------------------------------------------------------
	public static void main ( String [] args )
	{
		int[] SR = new int[31];
		SR[0] = 1;
		SR[1] = 2;
		SR[2] = 3;
		SR[3] = 4;
		SR[6] = 11;
		SR[7] = 5;
		SR[8] = 6;
		SR[13] = 12;
		SR[14] = 13;
		SR[15] = 7;
		SR[16] = 8;
		SR[17] = 9;
		SR[18] = 10;
		SR[29] = 14;
		SR[30] = 15;
		Node root = convertLink(SR, 30);
	}
//--------------------------------------------------------------------
	public static Node convertLink (int[] SR, int last)
	{
		Node[] nodeArr = new Node[last + 1];
		Node root = new Node (SR[0]);
		root.parent = null;
		nodeArr[0] = root;
		for (int i = 0; i <= last; i++)
		{
			Node ndx = new Node(SR[i]);
			nodeArr[i] = ndx;
		}
		for (int i = 0; i <= last; i++)
		{
			int left = 2*i + 1;
			int right = 2*i + 2;
			if (i > 0) nodeArr[i].parent = nodeArr[(i-1)/2];
			if (left <= last) nodeArr[i].left = nodeArr[2*i+1];
			if (right <= last) nodeArr[i].right = nodeArr[2*i+2];
		}
		System.out.println("Tree in linked representation:");
		System.out.println();
		for (int i = 0; i <= last; i++)
		{
			if (nodeArr[i].val != 0)
			{
				System.out.println("node = " + nodeArr[i].val);
				if (nodeArr[i].parent == null)
					System.out.println(nodeArr[i].val + " is the root.");
				else System.out.println("parent = "  + nodeArr[i].parent.val);
				if (nodeArr[i].left == null)
					System.out.println("left child = NULL");
				else System.out.println("left child = " + nodeArr[i].left.val);
				if (nodeArr[i].right == null)
					System.out.println("right child = NULL");
				else System.out.println("right child = " + nodeArr[i].right.val);
			}
		}
		System.out.println();
		convertSequential (root, nodeArr);
		return root;
	}
//--------------------------------------------------------------------
	public static int levelNumber(Node root)
	{
		if (root == null) return 0;
		int ll = levelNumber(root.left);
		int lr = levelNumber (root.right);
		if (ll < lr) return lr +1;
		else return ll + 1;
	}
//--------------------------------------------------------------------
	public static void placeElements (int[] SR, int ndx, Node[] nodeArr)
	{
		int left = 2*ndx + 1;
		int right = 2*ndx + 2;
		int last = SR.length -1;
		SR[0] = nodeArr[0].val;
		if (left <= last)
		{
			SR[left] = nodeArr[ndx].left.val;
			placeElements(SR, left, nodeArr);
		}
		if (right <= last)
		{
			SR[right] = nodeArr[ndx].right.val;
			placeElements(SR, right, nodeArr);
		}
	}
//--------------------------------------------------------------------
	public static int[] convertSequential (Node root, Node[] nodeArr)
	{
		int[] SR = new int[nodeArr.length];
		placeElements(SR, 0, nodeArr);
		System.out.println("\nConverted to sequential:");
		System.out.println();
		for (int i = 0; i < SR.length; i++) 
		{
			if (SR[i] == 0) System.out.print("NULL ");
			else System.out.print(SR[i] + " ");
		}
		return SR;
	}
//--------------------------------------------------------------------
} // end class Hw02
//////////////////////////////////////////////////////////////////////
