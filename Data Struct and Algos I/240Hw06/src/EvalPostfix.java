// Modified by Danzel Rana
// Written by Barry Soroka
//
// Solves CS 240, Spring 2013, Homework 06.
//
// Evaluates postfix expressions 
// presented as Strings of ints and operators.
//
import java.io.*;
import java.util.Scanner;
import java.util.Stack;
//////////////////////////////////////////////////////////////////////
class EvalPostfix
{
//--------------------------------------------------------------------
   public static String eval ( String exp ) 
   {
   	Stack<Integer> s = new Stack<Integer>();
      String[] strArr = exp.split("");
      int p1 = 0;
      int p2 = 0;
      for ( int i = 0; i < strArr.length; i++)
      {
      	try
      	{
      		s.push(Integer.parseInt(strArr[i]));      		
      	}
      	catch ( Exception e )
      	{
      		if ( strArr[i].equals("*"))
      		{
      			if (s.size() < 2) return "too few operands";
      			p1 = s.pop();
      			p2 = s.pop();
      			s.push(p1 * p2);
      		}
      		if ( strArr[i].equals("+"))
      		{
      			if (s.size() < 2) return "too few operands";
      			p1 = s.pop();
      			p2 = s.pop();
      			s.push(p1 + p2);
      		}
      	}
      }
      if (s.size() == 0) return "too few operands";
      if ( s.size() > 1) return "too few operators";
      return Integer.toString(s.pop());
   }
//--------------------------------------------------------------------
} // end class EvalPostfix
//////////////////////////////////////////////////////////////////////
