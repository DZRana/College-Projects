// Modified by Danzel Rana
// Written by Barry Soroka
//
// Solves CS 240, Spring 2013, Homework 7.
//
// Infix to postfix.
//
import java.io.*;
import java.util.Stack;
////////////////////////////////////////////////////////////////////////////////
class ITP
{
//------------------------------------------------------------------------------
   public static String infixToPostfix ( String infix ) throws Exception
   {
      Stack<Character> s = new Stack<Character>();
      String out = "";
      String[] stringArr = infix.split("\\s+");
      String longStr = "";
      for (int i = 0; i < stringArr.length; i++)
      	longStr += stringArr[i];
      char[] charArr = longStr.toCharArray();
      char operator = 0;
      for (int i = 0; i < charArr.length; i++ )
      {
      	if (Character.isLetter(charArr[i]))
      		out += charArr[i] + " ";
      	else
      	{
      		operator = charArr[i];
      		switch(operator)
      		{
      			case '+' :
      				if (s.isEmpty()) s.push(operator);
      				else
      				{
      					if (s.peek() == '(') s.push(operator);
      					else if (s.peek() == '*' || s.peek() == '/' || s.peek() == '-' || s.peek() == '+') 
      					{
      						out += s.pop() + " ";
      						i--;
      					}
      				}
      				break;
      			case '-' :
      				if (s.isEmpty()) s.push(operator);
      				else
      				{
      					if (s.peek() == '(') s.push(operator);
      					else if (s.peek() == '*' || s.peek() == '/' || s.peek() == '-' || s.peek() == '+') 
      					{
      						out += s.pop() + " ";
      						i--;
      					}
      				}
      				break;
      			case '*':
      				if (s.isEmpty()) s.push(operator);
      				else
      				{
      					if (s.peek() == '(' || s.peek() == '+' || s.peek() == '-') 
      					{
      						s.push(operator);
      						break;
      					}
      					if (s.peek() == '*' || s.peek() == '/')
      					{
      						out += s.pop() + " ";
      						i--;
      					}
      				}
      				break;
      			case '/':
      				if (s.isEmpty()) s.push(operator);
      				else
      				{
      					if (s.peek() == '(' || s.peek() == '+' || s.peek() == '-') 
      					{
      						s.push(operator);
      						break;
      					}
      					if (s.peek() == '*' || s.peek() == '/')
      					{
      						out += s.pop() + " ";
      						i--;
      					}
      				}
      				break;
      			case '(':
      				s.push(operator);
      				break;
      			case ')':
      				if (s.peek() == '+' || s.peek() == '-' || s.peek() == '*' || s.peek() == '/')
   					{
      					out += s.pop() + " ";
   					   i--;
   					}
      				else s.pop();
      				break;
      		}
      	}
      }
      if (s.size() > 0)
      {
      	for ( int i = 0; i <= s.size(); i++)
      		out += s.pop() + " ";
      }
      return out;
   }
//------------------------------------------------------------------------------
} // end class ITP
////////////////////////////////////////////////////////////////////////////////
