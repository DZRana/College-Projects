// Written by Danzel Rana
//
// Solves CS 380, Winter 2015, Project 4.
//
import java.io.*;
import java.net.*;
//////////////////////////////////////////////////////////////////////
public final class Ipv6Client
{
//--------------------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		try (Socket socket = new Socket("76.91.123.97", 38004)) 
		{
			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			byte[] packet1 = new byte[42];
			packet1[0] = 0b01100000; //0-7
			packet1[1] = 0b0; //8-15
			packet1[2] = 0b0; //16-23
			packet1[3] = 0b0; //24-31
			packet1[4] = 0b0; //32-39
			packet1[5] = 0b00000010; //40-47 (Payload Length)
			packet1[6] = 0x11;		//48-55 (Next Header)
			packet1[7] = 0b00010100; //56-63 (Hop Limit)
			
			packet1[8] = 0b0;
			packet1[9] = 0b0; 
			packet1[10] = 0b0;
			packet1[11] = 0b0; 
			packet1[12] = 0b0;
			packet1[13] = 0b0;
			packet1[14] = 0b0;
			packet1[15] = 0b0;
			packet1[16] = 0b0;
			packet1[17] = 0b0;
			packet1[18] = (byte) 0b11111111;
			packet1[19] = (byte) 0b11111111;
			packet1[20] = 0b01100000;
			packet1[21] = 0b00100111;
			packet1[22] = (byte) 0b11111110;
			packet1[23] = (byte) 0b10101111;
			
			packet1[24] = 0b0; 
			packet1[25] = 0b0; 
			packet1[26] = 0b0; 
			packet1[27] = 0b0;
			packet1[28] = 0b0;
			packet1[29] = 0b0;
			packet1[30] = 0b0;
			packet1[31] = 0b0;
			packet1[32] = 0b0;
			packet1[33] = 0b0;
			packet1[34] = (byte) 0b11111111;
			packet1[35] = (byte) 0b11111111;
			packet1[36] = 0b01001100;
			packet1[37] = 0b01011011;
			packet1[38] = 0b01111011;
			packet1[39] = 0b01100001;
			output.write(packet1);
			System.out.print("1. ");
			System.out.print("0x");
			for (int i = 0; i < 4; i++)
			{
				int val = input.read();
				System.out.print(Integer.toHexString(val).toUpperCase());
			}
			System.out.println();
			
			byte[] packet2 = new byte[44];
			packet2[0] = 0b01100000; //0-7
			packet2[1] = 0b0; //8-15
			packet2[2] = 0b0; //16-23
			packet2[3] = 0b0; //24-31
			packet2[4] = 0b0; //32-39
			packet2[5] = 0b00000100; //40-47 (Payload Length)
			packet2[6] = 0x11;		//48-55 (Next Header)
			packet2[7] = 0b00010100; //56-63 (Hop Limit)
			
			packet2[8] = 0b0;
			packet2[9] = 0b0; 
			packet2[10] = 0b0;
			packet2[11] = 0b0; 
			packet2[12] = 0b0;
			packet2[13] = 0b0;
			packet2[14] = 0b0;
			packet2[15] = 0b0;
			packet2[16] = 0b0;
			packet2[17] = 0b0;
			packet2[18] = (byte) 0b11111111;
			packet2[19] = (byte) 0b11111111;
			packet2[20] = 0b01100000;
			packet2[21] = 0b00100111;
			packet2[22] = (byte) 0b11111110;
			packet2[23] = (byte) 0b10101111;
			
			packet2[24] = 0b0; 
			packet2[25] = 0b0; 
			packet2[26] = 0b0; 
			packet2[27] = 0b0;
			packet2[28] = 0b0;
			packet2[29] = 0b0;
			packet2[30] = 0b0;
			packet2[31] = 0b0;
			packet2[32] = 0b0;
			packet2[33] = 0b0;
			packet2[34] = (byte) 0b11111111;
			packet2[35] = (byte) 0b11111111;
			packet2[36] = 0b01001100;
			packet2[37] = 0b01011011;
			packet2[38] = 0b01111011;
			packet2[39] = 0b01100001;
			output.write(packet2);
			System.out.print("2. ");
			System.out.print("0x");
			for (int i = 0; i < 4; i++)
			{
				int val = input.read();
				System.out.print(Integer.toHexString(val).toUpperCase());
			}
			System.out.println();
			
			byte[] packet3 = new byte[48];
			packet3[0] = 0b01100000; //0-7
			packet3[1] = 0b0; //8-15
			packet3[2] = 0b0; //16-23
			packet3[3] = 0b0; //24-31
			packet3[4] = 0b0; //32-39
			packet3[5] = 0b00001000; //40-47 (Payload Length)
			packet3[6] = 0x11;		//48-55 (Next Header)
			packet3[7] = 0b00010100; //56-63 (Hop Limit)
			
			packet3[8] = 0b0;
			packet3[9] = 0b0; 
			packet3[10] = 0b0;
			packet3[11] = 0b0; 
			packet3[12] = 0b0;
			packet3[13] = 0b0;
			packet3[14] = 0b0;
			packet3[15] = 0b0;
			packet3[16] = 0b0;
			packet3[17] = 0b0;
			packet3[18] = (byte) 0b11111111;
			packet3[19] = (byte) 0b11111111;
			packet3[20] = 0b01100000;
			packet3[21] = 0b00100111;
			packet3[22] = (byte) 0b11111110;
			packet3[23] = (byte) 0b10101111;
		
			packet3[24] = 0b0; 
			packet3[25] = 0b0; 
			packet3[26] = 0b0; 
			packet3[27] = 0b0;
			packet3[28] = 0b0;
			packet3[29] = 0b0;
			packet3[30] = 0b0;
			packet3[31] = 0b0;
			packet3[32] = 0b0;
			packet3[33] = 0b0;
			packet3[34] = (byte) 0b11111111;
			packet3[35] = (byte) 0b11111111;
			packet3[36] = 0b01001100;
			packet3[37] = 0b01011011;
			packet3[38] = 0b01111011;
			packet3[39] = 0b01100001;
			output.write(packet3);
			System.out.print("3. ");
			System.out.print("0x");
			for (int i = 0; i < 4; i++)
			{
				int val = input.read();
				System.out.print(Integer.toHexString(val).toUpperCase());
			}
			System.out.println();
			
			byte[] packet4 = new byte[56];
			packet4[0] = 0b01100000; //0-7
			packet4[1] = 0b0; //8-15
			packet4[2] = 0b0; //16-23
			packet4[3] = 0b0; //24-31
			packet4[4] = 0b0; //32-39
			packet4[5] = 0b00010000; //40-47 (Payload Length)
			packet4[6] = 0x11;		//48-55 (Next Header)
			packet4[7] = 0b00010100; //56-63 (Hop Limit)

			packet4[8] = 0b0;
			packet4[9] = 0b0; 
			packet4[10] = 0b0;
			packet4[11] = 0b0; 
			packet4[12] = 0b0;
			packet4[13] = 0b0;
			packet4[14] = 0b0;
			packet4[15] = 0b0;
			packet4[16] = 0b0;
			packet4[17] = 0b0;
			packet4[18] = (byte) 0b11111111;
			packet4[19] = (byte) 0b11111111;
			packet4[20] = 0b01100000;
			packet4[21] = 0b00100111;
			packet4[22] = (byte) 0b11111110;
			packet4[23] = (byte) 0b10101111;
			
			packet4[24] = 0b0; 
			packet4[25] = 0b0; 
			packet4[26] = 0b0; 
			packet4[27] = 0b0;
			packet4[28] = 0b0;
			packet4[29] = 0b0;
			packet4[30] = 0b0;
			packet4[31] = 0b0;
			packet4[32] = 0b0;
			packet4[33] = 0b0;
			packet4[34] = (byte) 0b11111111;
			packet4[35] = (byte) 0b11111111;
			packet4[36] = 0b01001100;
			packet4[37] = 0b01011011;
			packet4[38] = 0b01111011;
			packet4[39] = 0b01100001;
			output.write(packet4);
			System.out.print("4. ");
			System.out.print("0x");
			for (int i = 0; i < 4; i++)
			{
				int val = input.read();
				System.out.print(Integer.toHexString(val).toUpperCase());
			}
			System.out.println();
			
			byte[] packet5 = new byte[72];
			packet5[0] = 0b01100000; //0-7
			packet5[1] = 0b0; //8-15
			packet5[2] = 0b0; //16-23
			packet5[3] = 0b0; //24-31
			packet5[4] = 0b0; //32-39
			packet5[5] = 0b00100000; //40-47 (Payload Length)
			packet5[6] = 0x11;		//48-55 (Next Header)
			packet5[7] = 0b00010100; //56-63 (Hop Limit)
			
			packet5[8] = 0b0;
			packet5[9] = 0b0; 
			packet5[10] = 0b0;
			packet5[11] = 0b0; 
			packet5[12] = 0b0;
			packet5[13] = 0b0;
			packet5[14] = 0b0;
			packet5[15] = 0b0;
			packet5[16] = 0b0;
			packet5[17] = 0b0;
			packet5[18] = (byte) 0b11111111;
			packet5[19] = (byte) 0b11111111;
			packet5[20] = 0b01100000;
			packet5[21] = 0b00100111;
			packet5[22] = (byte) 0b11111110;
			packet5[23] = (byte) 0b10101111;
			
			packet5[24] = 0b0; 
			packet5[25] = 0b0; 
			packet5[26] = 0b0; 
			packet5[27] = 0b0;
			packet5[28] = 0b0;
			packet5[29] = 0b0;
			packet5[30] = 0b0;
			packet5[31] = 0b0;
			packet5[32] = 0b0;
			packet5[33] = 0b0;
			packet5[34] = (byte) 0b11111111;
			packet5[35] = (byte) 0b11111111;
			packet5[36] = 0b01001100;
			packet5[37] = 0b01011011;
			packet5[38] = 0b01111011;
			packet5[39] = 0b01100001;
			output.write(packet5);
			System.out.print("5. ");
			System.out.print("0x");
			for (int i = 0; i < 4; i++)
			{
				int val = input.read();
				System.out.print(Integer.toHexString(val).toUpperCase());
			}
			System.out.println();
			
			byte[] packet6 = new byte[104];
			packet6[0] = 0b01100000; //0-7
			packet6[1] = 0b0; //8-15
			packet6[2] = 0b0; //16-23
			packet6[3] = 0b0; //24-31
			packet6[4] = 0b0; //32-39
			packet6[5] = 0b01000000; //40-47 (Payload Length)
			packet6[6] = 0x11;		//48-55 (Next Header)
			packet6[7] = 0b00010100; //56-63 (Hop Limit)
			
			packet6[8] = 0b0;
			packet6[9] = 0b0; 
			packet6[10] = 0b0;
			packet6[11] = 0b0; 
			packet6[12] = 0b0;
			packet6[13] = 0b0;
			packet6[14] = 0b0;
			packet6[15] = 0b0;
			packet6[16] = 0b0;
			packet6[17] = 0b0;
			packet6[18] = (byte) 0b11111111;
			packet6[19] = (byte) 0b11111111;
			packet6[20] = 0b01100000;
			packet6[21] = 0b00100111;
			packet6[22] = (byte) 0b11111110;
			packet6[23] = (byte) 0b10101111;
		
			packet6[24] = 0b0; 
			packet6[25] = 0b0; 
			packet6[26] = 0b0; 
			packet6[27] = 0b0;
			packet6[28] = 0b0;
			packet6[29] = 0b0;
			packet6[30] = 0b0;
			packet6[31] = 0b0;
			packet6[32] = 0b0;
			packet6[33] = 0b0;
			packet6[34] = (byte) 0b11111111;
			packet6[35] = (byte) 0b11111111;
			packet6[36] = 0b01001100;
			packet6[37] = 0b01011011;
			packet6[38] = 0b01111011;
			packet6[39] = 0b01100001;
			output.write(packet6);
			System.out.print("6. ");
			System.out.print("0x");
			for (int i = 0; i < 4; i++)
			{
				int val = input.read();
				System.out.print(Integer.toHexString(val).toUpperCase());
			}
			System.out.println();
			
			byte[] packet7 = new byte[168];
			packet7[0] = 0b01100000; //0-7
			packet7[1] = 0b0; //8-15
			packet7[2] = 0b0; //16-23
			packet7[3] = 0b0; //24-31
			packet7[4] = 0b0; //32-39
			packet7[5] = (byte) 0b10000000; //40-47 (Payload Length)
			packet7[6] = 0x11;		//48-55 (Next Header)
			packet7[7] = 0b00010100; //56-63 (Hop Limit)
			
			packet7[8] = 0b0;
			packet7[9] = 0b0; 
			packet7[10] = 0b0;
			packet7[11] = 0b0; 
			packet7[12] = 0b0;
			packet7[13] = 0b0;
			packet7[14] = 0b0;
			packet7[15] = 0b0;
			packet7[16] = 0b0;
			packet7[17] = 0b0;
			packet7[18] = (byte) 0b11111111;
			packet7[19] = (byte) 0b11111111;
			packet7[20] = 0b01100000;
			packet7[21] = 0b00100111;
			packet7[22] = (byte) 0b11111110;
			packet7[23] = (byte) 0b10101111;
		
			packet7[24] = 0b0; 
			packet7[25] = 0b0; 
			packet7[26] = 0b0; 
			packet7[27] = 0b0;
			packet7[28] = 0b0;
			packet7[29] = 0b0;
			packet7[30] = 0b0;
			packet7[31] = 0b0;
			packet7[32] = 0b0;
			packet7[33] = 0b0;
			packet7[34] = (byte) 0b11111111;
			packet7[35] = (byte) 0b11111111;
			packet7[36] = 0b01001100;
			packet7[37] = 0b01011011;
			packet7[38] = 0b01111011;
			packet7[39] = 0b01100001;
			output.write(packet7);
			System.out.print("7. ");
			System.out.print("0x");
			for (int i = 0; i < 4; i++)
			{
				int val = input.read();
				System.out.print(Integer.toHexString(val).toUpperCase());
			}
			System.out.println();
			
			byte[] packet8 = new byte[296];
			packet8[0] = 0b01100000; //0-7
			packet8[1] = 0b0; //8-15
			packet8[2] = 0b0; //16-23
			packet8[3] = 0b0; //24-31
			packet8[4] = 0b00000001; //32-39
			packet8[5] = (byte) 0b0; //40-47 (Payload Length)
			packet8[6] = 0x11;		//48-55 (Next Header)
			packet8[7] = 0b00010100; //56-63 (Hop Limit)
			
			packet8[8] = 0b0;
			packet8[9] = 0b0; 
			packet8[10] = 0b0;
			packet8[11] = 0b0; 
			packet8[12] = 0b0;
			packet8[13] = 0b0;
			packet8[14] = 0b0;
			packet8[15] = 0b0;
			packet8[16] = 0b0;
			packet8[17] = 0b0;
			packet8[18] = (byte) 0b11111111;
			packet8[19] = (byte) 0b11111111;
			packet8[20] = 0b01100000;
			packet8[21] = 0b00100111;
			packet8[22] = (byte) 0b11111110;
			packet8[23] = (byte) 0b10101111;
			
			packet8[24] = 0b0; 
			packet8[25] = 0b0; 
			packet8[26] = 0b0; 
			packet8[27] = 0b0;
			packet8[28] = 0b0;
			packet8[29] = 0b0;
			packet8[30] = 0b0;
			packet8[31] = 0b0;
			packet8[32] = 0b0;
			packet8[33] = 0b0;
			packet8[34] = (byte) 0b11111111;
			packet8[35] = (byte) 0b11111111;
			packet8[36] = 0b01001100;
			packet8[37] = 0b01011011;
			packet8[38] = 0b01111011;
			packet8[39] = 0b01100001;
			output.write(packet8);
			System.out.print("8. ");
			System.out.print("0x");
			for (int i = 0; i < 4; i++)
			{
				int val = input.read();
				System.out.print(Integer.toHexString(val).toUpperCase());
			}
			System.out.println();
			
			byte[] packet9 = new byte[552];
			packet9[0] = 0b01100000; //0-7
			packet9[1] = 0b0; //8-15
			packet9[2] = 0b0; //16-23
			packet9[3] = 0b0; //24-31
			packet9[4] = 0b00000010; //32-39
			packet9[5] = 0b0; //40-47 (Payload Length)
			packet9[6] = 0x11;		//48-55 (Next Header)
			packet9[7] = 0b00010100; //56-63 (Hop Limit)
		
			packet9[8] = 0b0;
			packet9[9] = 0b0; 
			packet9[10] = 0b0;
			packet9[11] = 0b0; 
			packet9[12] = 0b0;
			packet9[13] = 0b0;
			packet9[14] = 0b0;
			packet9[15] = 0b0;
			packet9[16] = 0b0;
			packet9[17] = 0b0;
			packet9[18] = (byte) 0b11111111;
			packet9[19] = (byte) 0b11111111;
			packet9[20] = 0b01100000;
			packet9[21] = 0b00100111;
			packet9[22] = (byte) 0b11111110;
			packet9[23] = (byte) 0b10101111;
			
			packet9[24] = 0b0; 
			packet9[25] = 0b0; 
			packet9[26] = 0b0; 
			packet9[27] = 0b0;
			packet9[28] = 0b0;
			packet9[29] = 0b0;
			packet9[30] = 0b0;
			packet9[31] = 0b0;
			packet9[32] = 0b0;
			packet9[33] = 0b0;
			packet9[34] = (byte) 0b11111111;
			packet9[35] = (byte) 0b11111111;
			packet9[36] = 0b01001100;
			packet9[37] = 0b01011011;
			packet9[38] = 0b01111011;
			packet9[39] = 0b01100001;
			output.write(packet9);
			System.out.print("9. ");
			System.out.print("0x");
			for (int i = 0; i < 4; i++)
			{
				int val = input.read();
				System.out.print(Integer.toHexString(val).toUpperCase());
			}
			System.out.println();
			
			byte[] packet10 = new byte[1064];
			packet10[0] = 0b01100000; //0-7
			packet10[1] = 0b0; //8-15
			packet10[2] = 0b0; //16-23
			packet10[3] = 0b0; //24-31
			packet10[4] = 0b00000100; //32-39
			packet10[5] = 0b0; //40-47 (Payload Length)
			packet10[6] = 0x11;		//48-55 (Next Header)
			packet10[7] = 0b00010100; //56-63 (Hop Limit)
			
			packet10[8] = 0b0;
			packet10[9] = 0b0; 
			packet10[10] = 0b0;
			packet10[11] = 0b0; 
			packet10[12] = 0b0;
			packet10[13] = 0b0;
			packet10[14] = 0b0;
			packet10[15] = 0b0;
			packet10[16] = 0b0;
			packet10[17] = 0b0;
			packet10[18] = (byte) 0b11111111;
			packet10[19] = (byte) 0b11111111;
			packet10[20] = 0b01100000;
			packet10[21] = 0b00100111;
			packet10[22] = (byte) 0b11111110;
			packet10[23] = (byte) 0b10101111;
			
			packet10[24] = 0b0; 
			packet10[25] = 0b0; 
			packet10[26] = 0b0; 
			packet10[27] = 0b0;
			packet10[28] = 0b0;
			packet10[29] = 0b0;
			packet10[30] = 0b0;
			packet10[31] = 0b0;
			packet10[32] = 0b0;
			packet10[33] = 0b0;
			packet10[34] = (byte) 0b11111111;
			packet10[35] = (byte) 0b11111111;
			packet10[36] = 0b01001100;
			packet10[37] = 0b01011011;
			packet10[38] = 0b01111011;
			packet10[39] = 0b01100001;
			output.write(packet10);
			System.out.print("10. ");
			System.out.print("0x");
			for (int i = 0; i < 4; i++)
			{
				int val = input.read();
				System.out.print(Integer.toHexString(val).toUpperCase());
			}
			System.out.println();
			
			byte[] packet11 = new byte[2088];
			packet11[0] = 0b01100000; //0-7
			packet11[1] = 0b0; //8-15
			packet11[2] = 0b0; //16-23
			packet11[3] = 0b0; //24-31
			packet11[4] = 0b00001000; //32-39
			packet11[5] = 0b0; //40-47 (Payload Length)
			packet11[6] = 0x11;		//48-55 (Next Header)
			packet11[7] = 0b00010100; //56-63 (Hop Limit)
			
			packet11[8] = 0b0;
			packet11[9] = 0b0; 
			packet11[10] = 0b0;
			packet11[11] = 0b0; 
			packet11[12] = 0b0;
			packet11[13] = 0b0;
			packet11[14] = 0b0;
			packet11[15] = 0b0;
			packet11[16] = 0b0;
			packet11[17] = 0b0;
			packet11[18] = (byte) 0b11111111;
			packet11[19] = (byte) 0b11111111;
			packet11[20] = 0b01100000;
			packet11[21] = 0b00100111;
			packet11[22] = (byte) 0b11111110;
			packet11[23] = (byte) 0b10101111;
			
			packet11[24] = 0b0; 
			packet11[25] = 0b0; 
			packet11[26] = 0b0; 
			packet11[27] = 0b0;
			packet11[28] = 0b0;
			packet11[29] = 0b0;
			packet11[30] = 0b0;
			packet11[31] = 0b0;
			packet11[32] = 0b0;
			packet11[33] = 0b0;
			packet11[34] = (byte) 0b11111111;
			packet11[35] = (byte) 0b11111111;
			packet11[36] = 0b01001100;
			packet11[37] = 0b01011011;
			packet11[38] = 0b01111011;
			packet11[39] = 0b01100001;
			output.write(packet11);
			System.out.print("11. ");
			System.out.print("0x");
			for (int i = 0; i < 4; i++)
			{
				int val = input.read();
				System.out.print(Integer.toHexString(val).toUpperCase());
			}
			System.out.println();
			
			byte[] packet12 = new byte[4136];
			packet12[0] = 0b01100000; //0-7
			packet12[1] = 0b0; //8-15
			packet12[2] = 0b0; //16-23
			packet12[3] = 0b0; //24-31
			packet12[4] = 0b00010000; //32-39
			packet12[5] = 0b0; //40-47 (Payload Length)
			packet12[6] = 0x11;		//48-55 (Next Header)
			packet12[7] = 0b00010100; //56-63 (Hop Limit)
			
			packet12[8] = 0b0;
			packet12[9] = 0b0; 
			packet12[10] = 0b0;
			packet12[11] = 0b0; 
			packet12[12] = 0b0;
			packet12[13] = 0b0;
			packet12[14] = 0b0;
			packet12[15] = 0b0;
			packet12[16] = 0b0;
			packet12[17] = 0b0;
			packet12[18] = (byte) 0b11111111;
			packet12[19] = (byte) 0b11111111;
			packet12[20] = 0b01100000;
			packet12[21] = 0b00100111;
			packet12[22] = (byte) 0b11111110;
			packet12[23] = (byte) 0b10101111;
			
			packet12[24] = 0b0; 
			packet12[25] = 0b0; 
			packet12[26] = 0b0; 
			packet12[27] = 0b0;
			packet12[28] = 0b0;
			packet12[29] = 0b0;
			packet12[30] = 0b0;
			packet12[31] = 0b0;
			packet12[32] = 0b0;
			packet12[33] = 0b0;
			packet12[34] = (byte) 0b11111111;
			packet12[35] = (byte) 0b11111111;
			packet12[36] = 0b01001100;
			packet12[37] = 0b01011011;
			packet12[38] = 0b01111011;
			packet12[39] = 0b01100001;
			output.write(packet12);
			System.out.print("12. ");
			System.out.print("0x");
			for (int i = 0; i < 4; i++)
			{
				int val = input.read();
				System.out.print(Integer.toHexString(val).toUpperCase());
			}
		}
   }
//--------------------------------------------------------------------
} // end class Ipv6Client
//////////////////////////////////////////////////////////////////////