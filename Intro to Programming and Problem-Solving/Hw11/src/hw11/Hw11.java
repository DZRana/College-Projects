package hw11;

//Written by Danzel Rana
//
//Solves CS 141, Winter 2013, Homework 11.
//
import java.io.*;
import java.util.*;
//////////////////////////////////////////////////////////////////////
class Hw11
{
//--------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      Scanner kb = new Scanner(System.in);
	
      System.out.print("Filename? ");
      String filename = kb.nextLine();
	
      Scanner sc = new Scanner(new File(filename));
      String longStr="";
      while(sc.hasNext())
      {
         longStr+=sc.next().toLowerCase().replaceAll("'", "").replaceAll(",","")
        		  .replaceAll("\\d","").replaceAll("-","").replaceAll("\\.","") + " ";
      }
      char[] charArr = longStr.toCharArray();
      int letterCntr = 0;
      for(int i = 0; i < charArr.length; i++)
         {
            if (Character.isLetter(charArr[i]))
               letterCntr++;
         }
      StringTokenizer st = new StringTokenizer(longStr);
      int wordCntr = st.countTokens();
      String[] strArr = longStr.split(" ");
      int aAnCntr=0;
      int theCntr=0;
      for(int j = 0; j < strArr.length; j++)
      {
         if (strArr[j].equals("a") || strArr[j].equals("an"))
            aAnCntr++;
         if (strArr[j].equals("the"))
            theCntr++;
      }
      System.out.println("letters " + letterCntr );
      System.out.println("words " + wordCntr);
      System.out.printf("average word length " + "%.2f\n", (double)letterCntr/wordCntr);
      if(aAnCntr >= 10 && theCntr >= 10)
      {
         System.out.printf("a/an" + "%4s%11s%.3f\n", aAnCntr,"frequency ", 
        		           (double)aAnCntr/wordCntr);
         System.out.printf("the" + "%5s%11s%.3f\n" , theCntr, "frequency ", 
        		           (double)theCntr/wordCntr);
      }
      else
      {
         System.out.printf("a/an" + "%3s%11s%.3f\n", aAnCntr,"frequency ", 
        		           (double)aAnCntr/wordCntr);
         System.out.printf("the" + "%4s%11s%.3f\n" , theCntr, "frequency ", 
        		           (double)theCntr/wordCntr);	
      }
   }
//--------------------------------------------------------------------
} // end class Hw11
//////////////////////////////////////////////////////////////////////