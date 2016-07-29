// Written by Danzel Rana
//
// Solves CS 380, Winter 2015, Project 2.
//
import java.io.*;
import java.net.Socket;
import java.lang.Runnable;
//////////////////////////////////////////////////////////////////////
public final class ChatClient
{
//--------------------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		try (Socket socket = new Socket("76.91.123.97", 38002)) 
		{
			PrintStream out = new PrintStream(socket.getOutputStream());
			final BufferedReader input = new BufferedReader(new InputStreamReader(
				  	  System.in));
			final BufferedReader br = new BufferedReader(new InputStreamReader(
					  socket.getInputStream()));
			Thread t = new Thread(new Runnable() 
			{
				public void run()
				{
					try
					{
						String serverText;
						while ((serverText = br.readLine()) != null)
							System.out.println("Server> " + br.readLine());	
					}
					catch (IOException e){}
				}
			});
			t.start();
			String text;
			System.out.print("Client> ");
			while ((text = input.readLine()) != null)
			{
				out.println(text);
				System.out.print("Client> ");
			}
		}
   }
//--------------------------------------------------------------------
} // end class ChatClient
//////////////////////////////////////////////////////////////////////