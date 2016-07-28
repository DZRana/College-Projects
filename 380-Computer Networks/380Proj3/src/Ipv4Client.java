// Written by Danzel Rana
//
// Solves CS 380, Winter 2015, Project 3.
//
import java.io.*;
import java.net.*;
//////////////////////////////////////////////////////////////////////
public final class Ipv4Client
{
//--------------------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		try (Socket socket = new Socket("76.91.123.97", 38003))
		{
			OutputStream output = socket.getOutputStream();
			final BufferedReader input = new BufferedReader(new InputStreamReader(
				  	  socket.getInputStream()));
			
			output.write(makePacket(22));
			System.out.println("Server> " + input.readLine());	
			output.write(makePacket2(24));
			System.out.println("Server> " + input.readLine());	
			output.write(makePacket3(28));
			System.out.println("Server> " + input.readLine());	
		}
	}
//--------------------------------------------------------------------
	static byte[] makePacket (int packetSize)
	{
		byte[] packet = new byte[packetSize];
		packet[0] = 0b01000101;
		packet[1] = 0b0;
		//packet[2]+[3] = total length (header+data)
		packet[2] = 0b0;
		packet[3] = 0b00010110; //22 CHANGE
		packet[4] = 0b0;
		packet[5] = 0b0;
		packet[6] = 0b01000000;
		packet[7] = 0b0;
		packet[8] = 0b00110010; //TTL
		packet[9] = 0b00000110; //Protocol
		packet[10] = 0b0;
		packet[11] = 0b0;
		packet[12] = 0b01100000;
		packet[13] = 0b00100111;
		packet[14] = (byte) 0b11111110;
		packet[15] = (byte) 0b10101111;
		packet[16] = 0b01001100;
		packet[17] = 0b01011011;
		packet[18] = 0b01111011;
		packet[19] = 0b01100001;

		String pStr = Integer.toBinaryString((int)checksumVal(packet));
		char[] cArr = pStr.toCharArray();
		
		packet[10] = (byte) (0b00 + cArr[0] + cArr[1] + cArr[2] + cArr[3] + cArr[4] + cArr[5]);
		packet[11] = (byte) checksumVal(packet);
		
		return packet;
	}
//--------------------------------------------------------------------
	static byte[] makePacket2 (int packetSize)
	{
		byte[] packet = new byte[packetSize];
		packet[0] = 0b01000101;
		packet[1] = 0b0;
		//packet[2]+[3] = total length (header+data)
		packet[2] = 0b0;
		packet[3] = 0b00011000; //24 CHANGE
		packet[4] = 0b0;
		packet[5] = 0b0;
		packet[6] = 0b01000000;
		packet[7] = 0b0;
		packet[8] = 0b00110010; //TTL
		packet[9] = 0b00000110; //Protocol
		packet[10] = 0b0;
		packet[11] = 0b0;
		packet[12] = 0b01100000;
		packet[13] = 0b00100111;
		packet[14] = (byte) 0b11111110;
		packet[15] = (byte) 0b10101111;
		packet[16] = 0b01001100;
		packet[17] = 0b01011011;
		packet[18] = 0b01111011;
		packet[19] = 0b01100001;

		String pStr = Integer.toBinaryString((int)checksumVal(packet));
		char[] cArr = pStr.toCharArray();
		
		packet[10] = (byte) (0b00 + cArr[0] + cArr[1] + cArr[2] + cArr[3] + cArr[4] + cArr[5]);
		packet[11] = (byte) checksumVal(packet);
		
		return packet;
	}
//--------------------------------------------------------------------
	static byte[] makePacket3 (int packetSize)
	{
		byte[] packet = new byte[packetSize];
		packet[0] = 0b01000101;
		packet[1] = 0b0;
		//packet[2]+[3] = total length (header+data)
		packet[2] = 0b0;
		packet[3] = 0b00011100; //28 CHANGE
		packet[4] = 0b0;
		packet[5] = 0b0;
		packet[6] = 0b01000000;
		packet[7] = 0b0;
		packet[8] = 0b00110010; //TTL
		packet[9] = 0b00000110; //Protocol
		packet[10] = 0b0;
		packet[11] = 0b0;
		packet[12] = 0b01100000;
		packet[13] = 0b00100111;
		packet[14] = (byte) 0b11111110;
		packet[15] = (byte) 0b10101111;
		packet[16] = 0b01001100;
		packet[17] = 0b01011011;
		packet[18] = 0b01111011;
		packet[19] = 0b01100001;

		String pStr = Integer.toBinaryString((int)checksumVal(packet));
		char[] cArr = pStr.toCharArray();
		
		packet[10] = (byte) (0b00 + cArr[0] + cArr[1] + cArr[2] + cArr[3] + cArr[4] + cArr[5]);
		packet[11] = (byte) checksumVal(packet);
		
		return packet;
	}
//--------------------------------------------------------------------	
	static long checksumVal(byte[] packet) 
	{
		int i = 0;
		int length = packet.length;
		long sum = 0;
		long data;
		while (length > 1)
		{
			data = (((packet[i] << 8) & 0xFF00 | (packet[i+1]) & 0xFF));
			sum += data;
			if ((sum & 0xFFFF0000) > 0)
			{
				sum = sum & 0xFFFF;
				sum += 1;
			}
			i += 2;
			length -= 2;
		}
		if (length > 0)
		{
			sum += (packet[i] << 8 & 0xFF00);
			if ((sum & 0xFFFF0000) > 0)
			{
				sum = sum & 0xFFFF;
				sum += 1;
			}
		}
		sum = ~sum;
		sum = sum & 0xFFFF;
		//System.out.println("here2 " + Integer.toBinaryString((int) sum));
	   return sum;
	}
//--------------------------------------------------------------------
} // end class Ipv4Client
//////////////////////////////////////////////////////////////////////