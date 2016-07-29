
// Written by Danzel Rana
//
// Solves CS 380, Winter 2015, Project 1.
//
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
//////////////////////////////////////////////////////////////////////
public final class EchoClient
{
//--------------------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		try (Socket socket = new Socket("localhost", 22222)) 
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(
									  socket.getInputStream()));
         System.out.println(br.readLine());
      }
   }
//--------------------------------------------------------------------
} // end class EchoClient
//////////////////////////////////////////////////////////////////////