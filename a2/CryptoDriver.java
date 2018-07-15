// Michael Adams
// CS401 - Tu/Th 1pm
// Assignment 2 - Crypto Driver Class

import java.util.Scanner;

public class CryptoDriver
{
	public static void main(String [] args)
	{
		Scanner inScan = new Scanner(System.in);
		
		System.out.println("Welcome to CryptoTron4000");
		System.out.println("This fantastic, bug-free program allows you to encrypt or decrypt files");
		
		boolean done = false;
		int action, numFiles, nextAction;
		String passphrase;
		String [] filenames;
		Crypto C;
		
		while (!done)
		{
			do { //choose action
				System.out.println("Enter a number to choose an action: 1 = ENCRYPT, 2 = DECRYPT");
				action = inScan.nextInt();
				inScan.nextLine();
			} while (action != 1 && action != 2);
			
			if (action == 1) //choose number of files
			{
				System.out.println("Enter the number of files you wish to encrypt (with same password):");
				numFiles = inScan.nextInt();
				inScan.nextLine();
			}
			else
			{
				System.out.println("Enter the number of files you wish to decrypt (with same password):");
				numFiles = inScan.nextInt();
				inScan.nextLine();
			}
			
			filenames = new String[numFiles]; //create array of string refs with length given by user
			
			for (int i = 0; i < numFiles; i++) //fill array with filenames
			{
				System.out.println("Enter filename (with extension) for file " + (i + 1));
				filenames[i] = inScan.nextLine();
			}
			
			if (action == 1) //enter password 
			{
				System.out.println("Enter a password to protect your encrypted file(s):");
				passphrase = inScan.nextLine();
			}
			else
			{
				System.out.println("Enter the password to decrypt the file(s):");
				passphrase = inScan.nextLine();
			}
			
			C = new Crypto(passphrase); //calls Crypto constructor
			
			for (int i = 0; i < numFiles; i++) //calls Crypto 
			{
				if (action == 1)
				{
					tryEncrypt(filenames[i], C);
				}
				if (action == 2)
				{
					tryDecrypt(filenames[i], C);
				}
			}
			
			do { //continue?
				System.out.println("Enter a number to choose an action: 1 = CONTINUE, 2 = EXIT PROGRAM");
				nextAction = inScan.nextInt();
				inScan.nextLine();
			} while (nextAction != 1 && nextAction != 2);
			
			if (nextAction == 1)
			{
				done = false;
			}
			else
			{
				done = true;
				System.out.println("Thanks for choosing CryptoTron4000, have a safe day");
			}

		}
	}
	
	public static void tryEncrypt(String file, Crypto agent)
	{
		System.out.println();
		if (agent.encryptFile(file))
		{
			System.out.println("File " + file + " encrypted successfully");
			System.out.println("Encryption agent:\n" + agent.toString());
		}
		else
		{
			System.out.println("File " + file + " NOT encrypted");
			System.out.println("Encryption agent:\n" + agent.toString());
		}
		System.out.println();
	}
	
	public static void tryDecrypt(String file, Crypto agent)
	{
		System.out.println();
		if (agent.decryptFile(file))
		{
			System.out.println("File " + file + " decrypted successfully");
			System.out.println("Decryption agent\n" + agent.toString());
		}
		else
		{
			System.out.println("File " + file + " could not be decrypted");
			System.out.println("Decryption agent\n" + agent.toString());
		}
		System.out.println();
	}
}
		
	