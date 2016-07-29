// Written by Danzel Rana
//
// Solves CS 380, Winter 2015, Project 1.
//
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
//////////////////////////////////////////////////////////////////////
public final class EchoServer
{
//--------------------------------------------------------------------
	public static void main(String[] args) throws Exception 
	{
		try (ServerSocket serverSocket = new ServerSocket(22222)) 
      {
			while (true) 
         {
				try (Socket socket = serverSocket.accept()) 
            {
					String address = socket.getInetAddress().getHostAddress();
               System.out.printf("Client connected: %s%n", address);
               PrintStream out = new PrintStream(socket.getOutputStream());
               BufferedReader in = new BufferedReader(new InputStreamReader(
						  	  socket.getInputStream()));
               String text;
               while ((text = in.readLine()) != null)
               	out.println(text);
            }  
         }
      }
   }
//--------------------------------------------------------------------
} // end class EchoServer
//////////////////////////////////////////////////////////////////////
