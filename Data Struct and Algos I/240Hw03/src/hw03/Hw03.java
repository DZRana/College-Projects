package hw03;

//Written by Danzel Rana
//
//Solves CS 240, Spring 2013, Homework 03.
//
import java.util.Arrays;
/////////////////////////////////////////////////////////////////////////
class Memory
{
	private int size = 0;
	private final int STR_SIZE = 10;
	private final int STR_INCREASE = (STR_SIZE*STR_SIZE*STR_SIZE);
	private String[] keys = new String[STR_SIZE + STR_INCREASE];
	private String[] values = new String[STR_SIZE + STR_INCREASE];
//------------------------------------------------------------------------------
	public Memory() {}
//------------------------------------------------------------------------------
	public int size() {return size;}
//------------------------------------------------------------------------------
	public void put(String k, String v) 
	{
		values[size] = v;
		keys[size] = k;
		for (int i = 0; i < size; i++)
		{
			if (keys[i].equals (k))
			{
				values[i] = v;
				values[size] = null;
				keys[size] = null;
				size--;
			}
			
		}
		size++;
	}
//------------------------------------------------------------------------------
	public String get(String k)
	{
		if (keys[0] == null) return null;
		for (int i = 0; i < size; i++)
			if (keys[i].equals(k)) return values[i];
		return null;
	}
//------------------------------------------------------------------------------
	public void remove(String k) 
	{
		for (int i = 0; i < size; i++)
		{
			if (keys[i].equals(k))
			{
			   keys[i] = keys[size];
				values[i] = values[size];
			}
		}
		for (int j = 0; j < size; j++)
		{
			if (keys[j] == null)
			{
				for (int n = j; n < size; n++)
				{
					keys[n] = keys[n+1];
					values[n] = values[n+1];
				}
				size--;
			}
		}
	}
//------------------------------------------------------------------------------
	public void clear() 
	{
		size = 0;
		for (int i = 0; i < keys.length; i++)
			keys[i] = null;
		for (int j = 0; j < values.length; j++)
			values[j] = null;
	}
//------------------------------------------------------------------------------
} // end class Memory
////////////////////////////////////////////////////////////////////////////////