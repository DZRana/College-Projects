// Written by Danzel Rana
//
// Solves CS 380, Winter 2015, Project 7.
//
import java.io.*;
import java.net.*;
import java.util.*;
//////////////////////////////////////////////////////////////////////
public class TicTacToeClient implements Serializable
{
//--------------------------------------------------------------------	
	public static void main(String[] args) throws Exception
	{
		try (Socket socket = new Socket("45.50.5.238", 38007)) 
		{
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			Scanner kb = new Scanner(System.in);
			byte row;
			byte col;
			output.writeObject(new ConnectMessage("DZRana"));
			output.writeObject(new CommandMessage(CommandMessage.Command.NEW_GAME));
			while (true)
			{
				Object in = input.readObject();

				if (((Message) in).getType() == MessageType.ERROR)
				{
					ErrorMessage error = (ErrorMessage)in;
					System.out.println(error.getError());
					System.out.print("Move(Row): ");
					row = (byte)kb.nextInt();
					System.out.print("Move(Col): ");
					col = (byte)kb.nextInt();
					output.writeObject(new MoveMessage(row, col));
				}
				else
				{
					BoardMessage board = (BoardMessage)in;
					System.out.print(board);
					byte[][] rAndC = board.getBoard();
					for (int i = 0; i < 3; i++)
					{
						for (int j = 0; j < 3; j++)
						{
							if (j == 0) System.out.println();
							System.out.print(rAndC[i][j] + " ");
						}
					}
					System.out.println("\nStatus: " + board.getStatus());
					System.out.println("Turn: " + board.getTurn());
					if (board.getStatus().equals(BoardMessage.Status.IN_PROGRESS))
					{
						System.out.print("move or surrender? ");
						if(kb.next().equals("surrender"))
						{
							output.writeObject(new CommandMessage(CommandMessage.Command.SURRENDER));
							ErrorMessage error = (ErrorMessage)input.readObject();
							System.out.println(error.getError());
							output.writeObject(new CommandMessage(CommandMessage.Command.EXIT));
							break;
						}
						System.out.print("Move(Row): ");
						row = (byte)kb.nextInt();
						System.out.print("Move(Col): ");
						col = (byte)kb.nextInt();
						output.writeObject(new MoveMessage(row, col));
					}
					else 
					{
						output.writeObject(new CommandMessage(CommandMessage.Command.EXIT));
						break;
					}
				}
			}
		}
	}
//--------------------------------------------------------------------
} // end class TicTacToeClient
//////////////////////////////////////////////////////////////////////