// Written by Danzel Rana
//
// Solves CS 380, Winter 2015, Project 6.
//
import java.io.*;
import java.util.*;
import java.net.*;
//////////////////////////////////////////////////////////////////////
public class TcpClient 
{
//--------------------------------------------------------------------
	public static void main(String[] args) throws Exception 
   {
		try (Socket socket = new Socket("45.50.5.238", 38006)) 
		{
			OutputStream output = socket.getOutputStream();
         InputStream input = socket.getInputStream();
         Random rndm = new Random(); 

         int seq = rndm.nextInt();
         byte[] firstPkt = new byte[40];
     
         int length = firstPkt.length;
         
         firstPkt[0] = 0x45;
         firstPkt[2] = (byte) (length >> 8);
         firstPkt[3] = (byte) (length);
         firstPkt[6] = 0x40;
         firstPkt[8] = 0x32; // TTL
         firstPkt[9] = 0x06; // Protocol

         firstPkt[14] = 0x0;
         firstPkt[15] = 0x0; 
         
         //Dest
         firstPkt[16] = 0x4c;
         firstPkt[17] = 0x5b;
         firstPkt[18] = 0x7b;
         firstPkt[19] = 0x61;
         
         firstPkt[10] = (byte) (checksumVal(firstPkt, 20) >> 8);
         firstPkt[11] = (byte) (checksumVal(firstPkt, 20));    
         
         firstPkt[24] = (byte) (seq >> 24);
         firstPkt[25] = (byte) (seq >> 16);
         firstPkt[26] = (byte) (seq >> 8);
         firstPkt[27] = (byte) seq;
         firstPkt[32] = 0x50;
         firstPkt[33] = 0x02;
         byte pHeader [] = pHeader(firstPkt);
         short pChecksumVal = checksumVal(pHeader, pHeader.length);
         firstPkt[36] = (byte) (pChecksumVal >> 8);
         firstPkt[37] = (byte) pChecksumVal;
         
         output.write(firstPkt); // Handshake
         byte responseCode[] = new byte[4];
         input.read(responseCode);
         printIt(responseCode);
         
         byte rPkt [] = new byte[20];
         input.read(rPkt);

         int respInc = (rPkt[4] << 24);
         
         respInc += (rPkt[5] << 16 & 0x00FFFFFF);
         respInc += (rPkt[6] << 8 & 0x0000FFFF);
         respInc += (rPkt[7] & 0x000000FF);
         respInc += 1;
                    
         seq++;
                                       
         firstPkt[24] = (byte) (seq >> 24);
         firstPkt[25] = (byte) (seq >> 16);
         firstPkt[26] = (byte) (seq >> 8);
         firstPkt[27] = (byte) seq++;
                      
         firstPkt[28] = (byte) (respInc >> 24);
         firstPkt[29] = (byte) (respInc >> 16);
         firstPkt[30] = (byte) (respInc >> 8);
         firstPkt[31] = (byte) respInc;

         firstPkt[33] = 0x12;        
         firstPkt[36] = 0x0;
         firstPkt[37] = 0x0;
                    
         pHeader = pHeader(firstPkt);
         pChecksumVal = checksumVal(pHeader, pHeader.length);
         firstPkt[36] = (byte) (pChecksumVal >> 8);
         firstPkt[37] = (byte) pChecksumVal;
         output.write(firstPkt);
                    
         input.read(responseCode);
         printIt(responseCode);
         
         // Create rest of packets
         for(int i = 1; i < 13; i++) 
         {
         	double exp = Math.pow(2, i);
            byte [] packet = new byte[40 + (int) exp];
                        
            for(int k = 0; k < 20; k++)
            	packet[k] = firstPkt[k];
            
            packet[2] = (byte) (packet.length >> 8);
            packet[3] = (byte) packet.length;
                        
            packet[10] = 0x0;
            packet[11] = 0x0;
            packet[10] = (byte) (checksumVal(packet, 20) >> 8);
            packet[11] = (byte) (checksumVal(packet, 20)); 

            packet[24] = (byte) (seq >> 24);
            packet[25] = (byte) (seq >> 16);
            packet[26] = (byte) (seq >> 8);
            packet[27] = (byte) seq;
            packet[32] = 0x50;
            
            pHeader = pHeader(packet);
            pChecksumVal = checksumVal(pHeader, pHeader.length);
            
            packet[36] = (byte) (pChecksumVal >> 8);
            packet[37] = (byte) pChecksumVal;
            
            output.write(packet); 
            input.read(responseCode); 
            printIt(responseCode);
            seq += exp;
         }
         
         firstPkt[33] = 0x01;
         firstPkt[36] = 0x0;
         firstPkt[37] = 0x0;
            
         pHeader = pHeader(firstPkt);
         pChecksumVal = checksumVal(pHeader, pHeader.length);
                    
         firstPkt[36] = (byte) (pChecksumVal >> 8);
         firstPkt[37] = (byte) pChecksumVal;
            
         output.write(firstPkt);
         input.read(responseCode);
         printIt(responseCode);
         
         input.read(rPkt);
         input.read(rPkt);
         
         firstPkt[33] = 0x10;
         firstPkt[36] = 0x0;
         firstPkt[37] = 0x0;
            
         pHeader = pHeader(firstPkt);
         pChecksumVal = checksumVal(pHeader, pHeader.length);

         firstPkt[36] = (byte) (pChecksumVal >> 8);
         firstPkt[37] = (byte) pChecksumVal;
            
         output.write(firstPkt); 
         input.read(responseCode);
         printIt(responseCode);
         
         input.read(rPkt);
         input.read(rPkt);
		}
   }		
//--------------------------------------------------------------------    
	public static void printIt (byte[] responseCode)
	{
		int mask = 0x000000ff;
		
		System.out.print("0x" + (Integer.toHexString(responseCode[0] & mask)).toUpperCase());
		System.out.print(Integer.toHexString(responseCode[1] & mask).toUpperCase());
		System.out.print(Integer.toHexString(responseCode[2] & mask).toUpperCase());
		System.out.print(Integer.toHexString(responseCode[3] & mask).toUpperCase());
		System.out.println();
	}
//--------------------------------------------------------------------    
	public static byte[] pHeader(byte[] firstPkt)
	{
		byte pHeader [] = new byte[firstPkt.length - 8];
			
	   for(int i = 12; i < 20; i++)
	   	pHeader[i-12] = firstPkt[i];
	      
	   	pHeader[9] = firstPkt[9];
	      pHeader[10] = (byte) ((pHeader.length - 12) >> 8); 
	      pHeader[11] = (byte) (pHeader.length - 12);
	      
	   for(int i = 20; i < firstPkt.length; i++)
	   	pHeader[i-8] = firstPkt[i];    
	      
	   return pHeader;
	}	
//--------------------------------------------------------------------
	public static short checksumVal(byte[] b, int length) 
	{
		int sum = 0;
      int ndx = 0;
      while (length > 1) 
      {
      	int current = (((int) b[ndx++] << 8) & 0x0000FFFF) + ((int) b[ndx++] & 0x00FF);
         sum += current;
         if ((sum & 0xFFFF0000) > 0) 
         {
         	sum = sum & 0xFFFF;
            sum++;
         }
         
         length = length - 2;
         
      }
      return (short) ~(sum);
	}
//--------------------------------------------------------------------
} // end class TcpClient
//////////////////////////////////////////////////////////////////////