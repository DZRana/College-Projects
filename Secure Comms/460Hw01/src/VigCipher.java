// Written by Danzel Rana
// Bronco ID: 009077770
// Solves CS 460, Winter 2015, HW 1.
//
//////////////////////////////////////////////////////////////////////
public class VigCipher 
{
//--------------------------------------------------------------------
    public static void main(String[] args) 
    {
       String msg = "thEquIckBrownFOxJumpSOVertHeLAzYDOg"; //The message to encrypt
       String key = "TOMYHEART"; //The Key. (used uppercase so the ASCII values are easier to deal with)
       System.out.println("Message: " + msg);
       System.out.println("Key: " + key + "\n");
       String encryptedString = "";
       msg = msg.toUpperCase(); //Converted msg to uppercase so the ASCII values are easier to deal with
       for (int i = 0, j = 0; i < msg.length(); i++) //walk through the msg to encrypt with the key
       {
      	 char m = msg.charAt(i);
          if (m < 'A' || m > 'Z') continue; //i skipped ASCII values outside of the A through Z range
          encryptedString += (char)((m + key.charAt(j) - 2 * 'A') % 26 + 'A'); //C = (M + K) % 26
          j = ++j % key.length();
       }
       String decryptedString = "";
       String encMsg = encryptedString.toUpperCase(); //Converted encrypted message to uppercase so the ASCII values are easier to deal with
       for (int i = 0, j = 0; i < encMsg.length(); i++) //walk through the encrypted msg to decrypt with the key
       {
      	 char c = encMsg.charAt(i);
          if (c < 'A' || c > 'Z') continue; //i skipped ASCII values outside of the A through Z range
          decryptedString += (char)((c - key.charAt(j) + 26) % 26 + 'A'); //M = (26 + C - K) % 26
          j = ++j % key.length();
       }
       System.out.println("Encrypted Message: " + encryptedString);
       System.out.println("Decrypted Message: " + decryptedString);
    }
//--------------------------------------------------------------------
} // end class VigCipher
//////////////////////////////////////////////////////////////////////