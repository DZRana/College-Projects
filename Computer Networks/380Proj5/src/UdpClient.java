// Written by Danzel Rana
//
// Solves CS 380, Winter 2015, Project 5.
//
import java.io.*;
import java.net.*;
import java.util.*;
//////////////////////////////////////////////////////////////////////
public final class UdpClient
{
//--------------------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		try (Socket socket = new Socket("76.91.123.97", 38005))
		{
			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			
			output.write(makeIpv4Packet(24));
			int bResp = (Integer.decode(serverResponse(input)));
			String bStr = Integer.toBinaryString(bResp);
			char[] bArr = bStr.toCharArray();
			byte[] destPort = new byte[2]; 
			
			if (bArr.length == 13)
			{	
				destPort[0] = (byte) (0b000 + bArr[0] + bArr[1] + bArr[2] + bArr[3] + bArr[4]);
				destPort[1] =(byte) (bArr[5] + bArr[6] + bArr[7] + bArr[8] + bArr[9] + bArr[10] + bArr[11] + bArr[12]);
			}	
			if (bArr.length == 14)
			{
				destPort[0] = (byte) (0b00 + bArr[0] + bArr[1] + bArr[2] + bArr[3] + bArr[4] + bArr[5]);
				destPort[1] =(byte) (bArr[6] + bArr[7] + bArr[8] + bArr[9] + bArr[10] + bArr[11] + bArr[12] + bArr[13]);
			}
			if (bArr.length == 15)
			{
				destPort[0] = (byte) (0b0 + bArr[0] + bArr[1] + bArr[2] + bArr[3] + bArr[4] + bArr[5] + bArr[6]);
				destPort[1] =(byte) (bArr[7] + bArr[8] + bArr[9] + bArr[10] + bArr[11] + bArr[12] + bArr[13] + bArr[14]);
			}
			if (bArr.length == 16)
			{
				destPort[0] = (byte) (bArr[0] + bArr[1] + bArr[2] + bArr[3] + bArr[4] + bArr[5] + bArr[6] + bArr[7]);
				destPort[1] =(byte) (bArr[8] + bArr[9] + bArr[10] + bArr[11] + bArr[12] + bArr[13] + bArr[14] + bArr[15]);
			}
						
			output.write(makeUDPPacket1(30, destPort));
			finalResponse(1,input);
		}
	}
//--------------------------------------------------------------------
	static byte[] makeIpv4Packet (int packetSize)
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
		packet[9] = 0b00010001; //Protocol
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
		
		packet[20] = (byte) 0b11011110;
		packet[21] = (byte) 0b10101101;
		packet[22] = (byte) 0b10111110;
		packet[23] = (byte) 0b11101111;
		
		return packet;
	}
//--------------------------------------------------------------------
	static byte[] makeUDPPacket1 (int packetSize, byte[] destPort)
	{
		Random random = new Random();
		byte[] nbyte = new byte[2];
		random.nextBytes(nbyte);
		byte[] packet = new byte[packetSize];
		packet[0] = 0b01000101;
		packet[1] = 0b0;
		//packet[2]+[3] = total length (header+data)
		packet[2] = 0b0;
		packet[3] = 0b00011110; //30 CHANGE
		packet[4] = 0b0;
		packet[5] = 0b0;
		packet[6] = 0b01000000;
		packet[7] = 0b0;
		packet[8] = 0b00110010; //TTL
		packet[9] = 0b00010001; //Protocol
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
		
		packet[20] = 0b0;
		packet[21] = 0b0;
		packet[22] = destPort[0];
		packet[23] = destPort[1];
		//packet[24-25] = UDP header and data LENGTH
		packet[24] = 0b0;
		packet[25] = 0b00001010; //10 CHANGE
 		packet[26] = 0b0;
 		packet[27] = 0b0;
 		packet[28] = nbyte[0];
 		packet[29] = nbyte[1];
 		
 		String uStr = Integer.toBinaryString((int)checksumVal(packet));
		char[] udpArr = uStr.toCharArray();
		
		packet[26] = (byte) (0b00 + udpArr[0] + udpArr[1] + udpArr[2] + udpArr[3] + udpArr[4] + udpArr[5]);
		packet[27] = (byte) checksumVal(packet);
		return packet;
	}
//--------------------------------------------------------------------
	static String serverResponse(InputStream input) throws Exception
	{
		String destPort = "0x";
		for (int i = 0; i < 2; i++)
		{
			int num = input.read();
			destPort += (Integer.toHexString(num).toUpperCase());
		}
		System.out.println("Server> " + destPort + " - Destination Port");
		return destPort;
	}
//--------------------------------------------------------------------
	static void finalResponse(int pktNum, InputStream input) throws Exception
	{
		System.out.print("Server> " + pktNum + ". 0x");
		for (int i = 0; i < 4; i++)
		{
			int val = input.read();
			System.out.print(Integer.toHexString(val).toUpperCase());
		}
		System.out.println();
	}
//--------------------------------------------------------------------
	static long checksumVal(byte[] ba) 
	{
		int i = 0;
		int length = ba.length;
		long sum = 0;
		long data;
		while (length > 1)
		{
			data = (((ba[i] << 8) & 0xFF00 | (ba[i+1]) & 0xFF));
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
			sum += (ba[i] << 8 & 0xFF00);
			if ((sum & 0xFFFF0000) > 0)
			{
				sum = sum & 0xFFFF;
				sum += 1;
			}
		}
		sum = ~sum;
		sum = sum & 0xFFFF;
	   return sum;
	}
//--------------------------------------------------------------------
} // end class UdpClient
//////////////////////////////////////////////////////////////////////



