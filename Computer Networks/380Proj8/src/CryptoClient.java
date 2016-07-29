// Written by Danzel Rana
//
// Solves CS 380, Winter 2015, Project 8.
//
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
//////////////////////////////////////////////////////////////////////
public final class CryptoClient
{
//--------------------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		try (Socket socket = new Socket("45.50.5.238", 38008))
		{
			OutputStream output = socket.getOutputStream();
         InputStream input = socket.getInputStream();
          
         FileInputStream fis = new FileInputStream("public.bin");
         ObjectInputStream ois = new ObjectInputStream(fis);
         
         Object k = ois.readObject();
         
         RSAPublicKey pubKey = (RSAPublicKey) k;   //1.Deserialize given RSA public key
         
         Key key = KeyGenerator.getInstance("AES").generateKey();
         ByteArrayOutputStream bos = new ByteArrayOutputStream(); //2. Generate AES session key and serialized
         ObjectOutputStream ois2 = new ObjectOutputStream(bos);
         ois2.writeObject(key);
        
         
         Cipher cipher = Cipher.getInstance("RSA");
         cipher.init(Cipher.ENCRYPT_MODE, pubKey);					  //3. Encrypting serialized form
         byte[] cipherText = cipher.doFinal(bos.toByteArray());
         
         ois2.close();
         bos.close();
         ois.close();
         fis.close();
         
         long total = 0;
         
         byte[] firstPkt = new byte[540]; //CHANGE    
         firstPkt[0] = 0b01000101;
         firstPkt[1] = 0b0;
   		//packet[2]+[3] = total length (header+data)
         firstPkt[2] = (byte) (firstPkt.length >> 8);
         firstPkt[3] = (byte) (firstPkt.length); //28 + 512 = 540 CHANGE
         firstPkt[4] = 0b0;
         firstPkt[5] = 0b0;
         firstPkt[6] = 0b01000000;
         firstPkt[7] = 0b0;
         firstPkt[8] = 0b00110010; //TTL
         firstPkt[9] = 0b00010001; //Protocol
         firstPkt[10] = 0b0;
         firstPkt[11] = 0b0;

         firstPkt[16] = 0b01001100;
         firstPkt[17] = 0b01011011;
         firstPkt[18] = 0b01111011;
         firstPkt[19] = 0b01100001;
         
         firstPkt[10] = (byte) (checksumVal(firstPkt, 20) >> 8);
         firstPkt[11] = (byte) (checksumVal(firstPkt, 20));    
         
         firstPkt[20] = 0x0;
         firstPkt[21] = 0x0;
         firstPkt[22] = (byte) 0b10010100;
         firstPkt[23] = (byte) 0b01111000;
         
         firstPkt[24] = (byte) ((firstPkt.length - 20) >> 8); //
         firstPkt[25] = (byte) (firstPkt.length - 20); //8(header) + 512 = 520 CHANGE
         firstPkt[26] = 0x0;
         firstPkt[27] = 0x0;
         for(int i = 0; i < cipherText.length; i++) firstPkt[i + 28] = cipherText[i];
  
         byte[] pHeader = pHeader(firstPkt);
         short pChecksumVal = checksumVal(pHeader, pHeader.length);
         firstPkt[26] = (byte) (pChecksumVal >> 8);
         firstPkt[27] = (byte) pChecksumVal;
         byte responseCode[] = new byte[4];
         Long start = System.currentTimeMillis();
         output.write(firstPkt);
         input.read(responseCode);
         Long end = System.currentTimeMillis();
         total += (end - start);
         printIt(responseCode);
         System.out.println("RTT: " + (end - start) + " ms");
         
         Cipher cipher2 = Cipher.getInstance("AES");
         cipher2.init(Cipher.ENCRYPT_MODE, key);	
         
         //Create rest of packets
         for(int i = 1; i < 11; i++) 
         {
         	double exp = Math.pow(2, i);
            byte[] packet = new byte[28 + (int) exp];  
            Random r = new Random();
         	byte[] nbyte = new byte[(int)exp];
         	r.nextBytes(nbyte);
            
            for(int f = 0; f < 24; f++) packet[f] = firstPkt[f];
            
            packet[2] = (byte) (packet.length >> 8);
            packet[3] = (byte) packet.length;
                        
            packet[10] = 0x0;
            packet[11] = 0x0;
            packet[10] = (byte) (checksumVal(packet, 20) >> 8);
            packet[11] = (byte) (checksumVal(packet, 20));    
            
            packet[24] = (byte) ((packet.length - 20) >> 8); //
            packet[25] = (byte) (packet.length - 20); //8(header) + 512 + 2 = 522 CHANGE
            packet[26] = 0x0;
            packet[27] = 0x0;
            for(int m = 28; m < packet.length; m++) packet[m] = nbyte[m-28]; 
            
            pHeader = pHeader(packet);
            pChecksumVal = checksumVal(pHeader, pHeader.length);
            packet[26] = (byte) (pChecksumVal >> 8);
            packet[27] = (byte) pChecksumVal;
            
            byte[] encryptedPkt = cipher2.doFinal(packet);
            Long startTime = System.currentTimeMillis();
            output.write(encryptedPkt); 
            input.read(responseCode);
            Long endTime = System.currentTimeMillis();
            total += (endTime - startTime);
            printIt(responseCode);
            System.out.println("RTT: " + (endTime - startTime) + " ms");
         }
         System.out.println("\nAverage RTT: " + (total / 11) + " ms");
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
   public static byte[] pHeader(byte[] packet)
   {
   	byte pHeader [] = new byte[packet.length - 8];
      			
      for(int i = 12; i < 20; i++)
      	pHeader[i-12] = packet[i];
      	      
      pHeader[9] = packet[9];
      pHeader[10] = (byte) ((pHeader.length - 12) >> 8); 
      pHeader[11] = (byte) (pHeader.length - 12);
      	      
      for(int i = 20; i < packet.length; i++)
      	pHeader[i-8] = packet[i];    
      	      
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
} // end class CryptoClient
//////////////////////////////////////////////////////////////////////