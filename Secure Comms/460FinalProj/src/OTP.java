//Written by Danzel Rana
//
//Solves CS 460, Winter 2015, Final Project.
//
import java.io.*;
import java.util.*;
import java.security.*;
//////////////////////////////////////////////////////////////////////
public class OTP
{
//--------------------------------------------------------------------	
	public static void main(String[] a) throws Exception 
	{
		Hashtable userTable = new Hashtable(); // key = users, value = password index. (MAX of 100 users.)
		Hashtable passTable = new Hashtable(); // contains all the passwords for every user (MAX of 100 passwords per user).
		Scanner kb = new Scanner(System.in);
		while(true)
		{
			// prompt for a command.
			System.out.print("log in, register, or exit: ");
			String cmd = kb.nextLine();
			
			// exit program.
			if (cmd.equals("exit")) break;
	   		
			// log in with an existing user.
			if (cmd.equals("log in"))
			{
				System.out.print("\nUsername: ");
				String user = kb.nextLine();
				if (userTable.containsKey(user)) // if the user exists, allow to login.
				{
					String[] thisOTPArr = (String[]) passTable.get(user); //get the users array of passwords
	   			
					int ndx = (int) userTable.get(user); // get the number of the password that the user needs to provide.
														 // it's also used for the index of the password in the array.
					System.out.println("Use Password(i) " + (ndx + 2) + " To Log In");
					System.out.print("OTP: ");
					String OTP = kb.nextLine();
					String currentPass = authHash(OTP); // hash the password that the user provides
					if (currentPass.equals(thisOTPArr[ndx])) // check whether or not the hash of the password that the user provides
														     // is the same as the password that the server has stored for reference.
					{
						System.out.println("H(Pi) was " + authHash(OTP));
						System.out.println("Server's stored value ( H(Pi-1) ) was " + thisOTPArr[ndx]);
						System.out.println("Since they are equal, you logged in successfully!\n");
						ndx++;
						userTable.put(user, ndx);
					}
					else System.out.println("Incorrect Password!\n"); // hash of the password that the user provides is NOT the same
																	  // as the password that the server has stored for reference.
				}
				else System.out.println("doesn't exist\n"); // user doesn't exist.
			}
			
			// register a new user.
			if (cmd.equals("register"))
			{
				System.out.print("\nUsername: ");
				String user = kb.nextLine();
				if(!(userTable.containsKey(user))) // if the user doesn't exist, allow to register.
				{
					// creates file that contains the user's passwords. this file is given to the user
					PrintWriter passWriter = new PrintWriter(user.toUpperCase() + "'s passwords" + ".txt", "UTF-8");
	   		
					userTable.put(user, 0);
	   		
					String[] passwords = new String[100]; // array of the user's passwords to put in passTable.
					passTable.put(user, passwords);
	   		
					System.out.print("Secret Pass Phrase: "); // initial secret, k, provided by the user.
					String pass = kb.nextLine();
					int cntr = 100; // used to walk through the passwords array.
					passwords = sha1(pass, cntr, passWriter, passwords); // fill passwords array
					System.out.println("\nDone. Secret Pass Phrase has been discarded and your one time passwords\n"
	   									+ "have been sent to a file in this directory. In compliance with authentication\n"
	   								 	+ "standards, the server has deleted your passwords except for the last one generated\n"
	   								 	+ "which will be used for authentication. Upon first log in, scrap #1 in your passwords\n"
	   								 	+ "and start with #2.\n" );
					passWriter.close();
				}
				else System.out.println("User already in database. Please choose a different username.\n"); // user already exists.
			}
		}
	}
//--------------------------------------------------------------------	
	
	// this method is used to hash the passwords. it recursively hashes the passwords. e.g.: H(Pk), H(H(Pk)), etc.
	
	static String[] sha1(String initialPass, int cntr, PrintWriter passWriter, String[] passwords) throws Exception
	{
		if (cntr == 0) return passwords; // returns the password array.
		StringBuffer sb = new StringBuffer();	
		MessageDigest mDigest = MessageDigest.getInstance("SHA-1"); // we use SHA-1 to hash the initial secret and the recurring strings.
		byte[] result = mDigest.digest(initialPass.getBytes());
		for (int i = 0; i < result.length; i++)
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		passWriter.println(cntr + ". " + sb.toString());
		passwords[cntr-1] = sb.toString(); // write passwords from 100 - 1 because the first password that is stored on the server
										   // for reference is the last password generated.
		cntr--;
		return sha1(sb.toString(),cntr, passWriter, passwords);
	}
//--------------------------------------------------------------------
	
	// this method hashes the password that the user provides once
			
	static String authHash(String thisPass) throws NoSuchAlgorithmException
	{
		StringBuffer sb = new StringBuffer();	
		MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
		byte[] result = mDigest.digest(thisPass.getBytes());
		for (int i = 0; i < result.length; i++)
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		return sb.toString();
	}
//--------------------------------------------------------------------
} // end class OTP
//////////////////////////////////////////////////////////////////////
