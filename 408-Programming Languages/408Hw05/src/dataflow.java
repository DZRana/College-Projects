import java.io.*;
import java.util.*;
public class dataflow implements Sets
{
	final static int NB = 4;
	final static int ND = 7;
	
	public static void main(String[] args)
	{
		dataflow dflow = new dataflow();
		int[][] in = new int[NB][ND];
		int[][] out = new int[NB][ND];
		int[][] gen = {{1,1,1,0,0,0,0}, {0,0,0,1,1,0,0},{0,0,0,0,0,1,0}, 
            			{0,0,0,0,0,0,1}};
		int[][] kill = {{0,0,0,1,1,1,1},{1,1,0,0,0,0,1},{0,0,1,0,0,0,0}, 
            			 {1,0,0,1,0,0,0}};
		int[][] pred = {{0,0,0,0}, {1,0,1,1}, {0,1,0,0}, {0,1,0,0}};
		int[] oldout = new int[ND];
		int[] tmp = new int[ND];
		for (int blk = 0; blk < NB; blk++)
		{
			for (int df = 0; df < ND; df++) in[blk][df] = 0;
		}
		for (int blk = 0; blk < NB; blk++) dflow.set_copy(out[blk], gen[blk]);
		boolean change = true;
		int ct = 0;
		while (change)
		{
			change = false;
			System.out.print("iteration " + (ct++) + "\n");
			for (int b = 0; b < NB; b++)
			{
				for (int p = 0; p < NB; p++)
				{
					if (pred[b][p] == 1)
					{
						dflow.set_copy (tmp, in[b]);
						dflow.set_union (tmp, out[p]);
						dflow.set_copy (in[b], tmp);
						dflow.set_print (in[b]);
					}
				}
				dflow.set_copy (oldout, out[b]);
				dflow.set_copy (tmp, in[b]);
				dflow.set_diff (tmp, kill[b]);
				dflow.set_union (tmp, gen[b]);
				dflow.set_copy (out[b], tmp);
				System.out.print("oldout\n");
				dflow.set_print (oldout);
				System.out.print("out[b]\n");
				dflow.set_print(out[b]);
				
				if (!dflow.set_equal (out[b], oldout))
				{
					System.out.print("not equal\n");
					change = true;
				}
			}
		}
		System.out.print("Final in sets \n");
		for (int i = 0; i < NB; i++)
		{
			System.out.print("B" + i + ": ");
			for (int j = 0; j < ND; j++)
			{
				if (in[i][j] == 1) System.out.print("d" + (j + 1) + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.print("Final out sets\n");
		for (int i = 0; i < NB; i++)
		{
			System.out.print("B" + i + ": ");
			for (int j = 0; j < ND; j++)
			{
				if (out[i][j] == 1) System.out.print("d" + (j + 1) + " ");
			}
			System.out.println();
		}
	}
	
	public void set_copy(int[] a, int[] b)
	{
		for (int i = 0; i < ND; i++) a[i] = b[i];
		return;
	}
	
	public boolean set_equal(int[] a, int[] b)
	{
		boolean result = true;
		for (int i = 0; i < ND; i++)
		{
			if (a[i] != b[i]) result = false;
		}
		return result;
	}
	
	public void set_union(int[] a, int[] b)
	{
		for (int i = 0; i < ND; i++)
		{
			if (a[i] == 1 || b[i] == 1) a[i] = 1;
		}
		return;
	}
	
	public void set_diff(int[] a, int[] b)
	{
		for (int i = 0; i < ND; i++)
		{
			if (b[i] == 1) a[i] = 0;
			return;
		}
	}
	
	public void set_print(int[] a)
	{
		for (int i = 0; i < ND; i++)
		{
			if (a[i] == 1) System.out.print("d" + i + " ");
			System.out.println();
		}
		return;
	}
}