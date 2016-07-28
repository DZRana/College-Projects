//Written by Danzel Rana
//
//Solves CS 241, Summer 2014, Homework 01.
//
import java.io.*;
import java.util.Scanner;
//////////////////////////////////////////////////////////////////////
class Hw01
{
//--------------------------------------------------------------------
	public static void main ( String [] args ) throws Exception
	{
		Scanner kb = new Scanner(System.in);
		
      System.out.print("Filename? ");
      String filename = kb.nextLine();
	
      Scanner sc = new Scanner(new File(filename));
      PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));
      
      double num;
      int ndx = 0;
      String longNum = "";
      String[] digits = {"ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE"};
      String[] teens = {"TEN","ELEVEN","TWELVE","THIRTEEN","FOURTEEN","FIFTEEN","SIXTEEN",
      						"SEVENTEEN","EIGHTEEN","NINETEEN"};
      String[] tens = {"TEN","TWENTY","THIRTY","FORTY","FIFTY","SIXTY","SEVENTY","EIGHTY",
      					  "NINETY"};
      while(sc.hasNext())
      {
      	num = sc.nextDouble();
      	out.println( "$" + num + " - " + printNum(num,ndx,longNum,digits,teens,tens));
      }
    
      out.close();
	}
//--------------------------------------------------------------------
	public static String printNum(double num, int ndx, String longNum,
					  						String[] digits, String[] teens, String[] tens)
	{
      String numStr = String.valueOf(num);
      char[] charArr = numStr.toCharArray();
      if ( charArr[charArr.length - 1] == '0')
      {
      	numStr += '0';
      	charArr = numStr.toCharArray();
      }
      if ( charArr[ndx + 1] == '.' && (int)num < 10)
      {
      	if ((int)num == 0) return "ZERO DOLLARS AND " + charArr[charArr.length-2] + charArr[charArr.length-1] +
      			                    " CENTS";
      	return digits[(int)num - 1] + " DOLLARS AND " + charArr[charArr.length-2] 
      			 + charArr[charArr.length-1] + " CENTS";
      }
      if ( charArr[ndx + 2] == '.' && (int)num < 20 )
      {
      	return teens[(int)num - 10] + " DOLLARS AND " + charArr[charArr.length-2] 
     			 + charArr[charArr.length-1] + " CENTS";
      }
      if ( charArr[ndx + 1] == '.' && (int)num >= 20 )
      {
      	return longNum + digits[((int)num % ((int)num/10 *10)) - 1] + " DOLLARS AND " + 
      			 charArr[charArr.length-2]+ charArr[charArr.length-1] + " CENTS";
      }
      if ( charArr[ndx + 2] == '.')
      {
      	if ((int)num < 100) longNum += tens[((((int)num/10 *10))/10) - 1] + " ";
      	if ((int)num >= 100 && (int)num < 1000) longNum += tens[((((int)num/100 *100))/100)] + " ";
      	if ((int)num >= 1000) 
      	{
      		if (((((int)num/10 *10))/10) - ((((int)num/100 *100))/10) != 0)
      			longNum += tens[(((((int)num/10 *10))/10) - ((((int)num/100 *100))/10)) - 1] + " ";
      	}
      	if (((int)num % ((int)num/10 *10)) == 0)
      	{
      		return longNum + " DOLLARS AND " + charArr[charArr.length-2]+ charArr[charArr.length-1] + 
      				 " CENTS";
      	}
      }
      if ( charArr[ndx + 3] == '.')
      {
      	if ((int)num >= 100 && (int)num < 1000) longNum += digits[((((int)num/100 *100))/100) - 1] + " HUNDRED ";
      	if ((int)num >= 1000)
      		longNum += digits[((((((int)num/100 *100))/100) - ((((int)num/1000 *1000))/100))) - 1] + " HUNDRED ";
      	if (((int)num % ((int)num/100 *100)) == 0)
      	{
      		return longNum + " DOLLARS AND " + charArr[charArr.length-2]+ charArr[charArr.length-1] + 
      				 " CENTS";
      	}
      }
      if ( (int) num >= 1000 && ndx == 0 && charArr[ndx + 4] == '.')
      {
      	longNum += digits[((((int)num/1000 *1000))/1000) - 1] + " THOUSAND ";
      	if (((int)num % ((int)num/1000 *1000)) == 0)
      	{
      		return longNum + " DOLLARS AND " + charArr[charArr.length-2]+ charArr[charArr.length-1] + 
      				 " CENTS";
      	}
      }
      if ( (int) num >= 10000 && ndx == 0 && charArr[ndx + 5] == '.')
      {
      	if ( num >= 10000 && num < 20000) longNum += teens[((((int)num/1000 *1000))/1000) - 10] + " THOUSAND ";
      	else 
      	{
      		longNum += tens[(((((int)num/10000 *10000))/10000) - ((((int)num/100000 *100000))/100000)) - 1] + " ";
      		if (charArr[ndx + 1] != '0')
      		{
      			longNum += digits[(((((int)num/10000 *10000))/10000) - ((((int)num/100000 *100000))/100000)) - 1] +
      					     " THOUSAND ";
      		}
      		else longNum += "THOUSAND";
      	}
      	if (((int)num % ((int)num/10000 *10000)) == 0)
      	{
      		return longNum + " DOLLARS AND " + charArr[charArr.length-2]+ charArr[charArr.length-1] + 
      				 " CENTS";
      	}
      }
      if ( (int) num >= 100000 && ndx == 0 && charArr[ndx + 6] == '.')
      {
      	longNum += digits[(((((int)num/100000 *100000))/100000) - ((((int)num/1000000 *1000000))/1000000)) - 1] + 
      			     " HUNDRED ";
      	if ( charArr[ndx + 2] != '0')
				longNum += digits[(((((int)num/1000 *1000))/1000)) - ((((int)num/10000 *10000))/1000) -1] + " ";
      	if ( charArr[ndx + 1] != '0' && charArr[ndx + 2] != '0')
      	{
      		if ( charArr[ndx + 1] == '1') 
      			longNum += teens[(((((int)num/1000 *1000))/1000)) - (((int)num/10000 *100000) / 10000)] + " THOUSAND ";
      		else 
      		{
      			longNum += tens[((((int)num/10000 *10000))/10000)- ((((int)num/100000 *100000))/10000) - 1] + " ";
      			if (charArr[ndx + 2] != '0')
      			{
      				longNum += digits[(((((int)num/1000 *1000))/1000)) - ((((int)num/10000 *10000))/1000) -1] +
      					     	  " THOUSAND ";
      			}
      			else longNum += "THOUSAND";
      		}
      	}
      	else longNum += "THOUSAND ";
      	if (((int)num % ((int)num/10000 *10000)) == 0)
      	{
      		return longNum + " DOLLARS AND " + charArr[charArr.length-2]+ charArr[charArr.length-1] + 
      				 " CENTS";
      	}
      }
      if ( (int) num >= 1000000 && ndx == 0 && charArr[ndx + 7] == '.')
      {
      	longNum += digits[(((((int)num/1000000 *100000))/1000000) - ((((int)num/10000000 *10000000))/10000000))] + 
      			     " MILLION ";
      	if ( charArr[ndx + 2] != '0')
				longNum += digits[(((((int)num/1000 *1000))/1000)) - ((((int)num/10000 *10000))/1000) -1] + " ";
      	if ( charArr[ndx + 1] != '0' && charArr[ndx + 2] != '0')
      	{
      		if ( charArr[ndx + 1] == '1') 
      			longNum += teens[(((((int)num/1000 *1000))/1000)) - (((int)num/10000 *100000) / 10000)] + " THOUSAND ";
      		else 
      		{
      			longNum += tens[((((int)num/10000 *10000))/10000)- ((((int)num/100000 *100000))/10000) - 1] + " ";
      			if (charArr[ndx + 2] != '0')
      			{
      				longNum += digits[(((((int)num/1000 *1000))/1000)) - ((((int)num/10000 *10000))/1000) -1] +
      					     	  " THOUSAND ";
      			}
      			else longNum += "THOUSAND";
      		}
      	}
      	if (((int)num % ((int)num/100000 *100000)) == 0)
      	{
      		return longNum + " DOLLARS AND " + charArr[charArr.length-2]+ charArr[charArr.length-1] + 
      				 " CENTS";
      	}
      }
      return printNum(num, ndx + 1, longNum, digits, teens, tens);
	}
//--------------------------------------------------------------------
} // end class Hw01
//////////////////////////////////////////////////////////////////////