// Written by Danzel Rana
//
// Solves CS 380, Winter 2015, Project 1.
//
import java.io.*;
import java.net.Socket;
//////////////////////////////////////////////////////////////////////
public final class EchoClient
{
//--------------------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		try (Socket socket = new Socket("localhost", 22222)) 
		{
			PrintStream out = new PrintStream(socket.getOutputStream());
			BufferedReader input = new BufferedReader(new InputStreamReader(
				  	  System.in));
			BufferedReader br = new BufferedReader(new InputStreamReader(
					  socket.getInputStream()));
			String text;
			System.out.print("Client> ");
			while ((text = input.readLine()) != null)
			{
				out.println(text);
				System.out.println("Server> " + br.readLine());	
				System.out.print("Client> ");
			}
		}
   }
//--------------------------------------------------------------------
} // end class EchoClient
//////////////////////////////////////////////////////////////////////