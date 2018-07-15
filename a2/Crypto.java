// Michael Adams
// CS401 - Tu/Th 1pm
// Assignment 2 - Crypto Class

import java.util.*;
import java.io.*;

public class Crypto
{
	//instance vars
	private String passphrase;
	private int key;
	private int shift;
	private int hash;
	private int lineLength;
	private boolean worked;
	private boolean EoF = false;
	private String filename;
	private String fHash;
	private StringBuilder currLine = new StringBuilder();
	private StringBuilder tempLine = new StringBuilder();
	private Scanner inScan = new Scanner(System.in);
	
	
	//CONSTRUCTOR
	public Crypto(String p)
	{
		passphrase = p;
		key = 127 + ((int) passphrase.charAt(0));
		hash = passphrase.hashCode();
	}
	
	//ENCRYPT FILE
	public boolean encryptFile(String f)
	{
		filename = f;
		
		if (filename.toLowerCase().contains(".cyp"))
		{
			System.out.println("File is already encrypted");
			worked = false;
		}
		else if (!filename.toLowerCase().contains(".cyp"))
		{
			try
			{
				File inFile = new File(filename); //this hopefully makes the file an obj
				Scanner inF = new Scanner(inFile); //makes scanner on txt file
				
				StringBuilder outFile = new StringBuilder(filename + ".cyp"); //makes name of cyp file
				PrintWriter outF = new PrintWriter(outFile.toString()); //makes PrintWriter for cyp file
			
				outF.println(hash); //prints hashed passphrase at beginning of document
			
				while (!EoF) //loops through each file while next line exists
				{
					currLine = new StringBuilder(inF.nextLine()); //reads in next line from inFile
				
					lineLength = currLine.length(); //find length of line 
					
					tempLine = new StringBuilder("");
				
					for (int i = 0; i < lineLength; i++) //shifts each char in currLine and appends to encryptLine
					{
						tempLine.append((char) ((currLine.charAt(i) + key) % 256));
					}
					
					outF.println(tempLine); //prints encryptLine to outFile
				
					EoF = !inF.hasNextLine(); //checks if inFile hasNextLine
				}
				
				outF.close();
				inF.close();
				
				worked = true;
			}
			catch (IOException e)
			{
				System.out.println("File does not exist");
				worked = false;
			}
		}

		return worked;
	}	
	
	//DECRYPT FILE
	public boolean decryptFile(String f)
	{
		filename = f;
		
		if (!filename.toLowerCase().contains(".cyp")) //checks to see if file is encrypted
		{
			System.out.println("File is not encrypted");
			worked = false;
		}
		else
		{
			try
			{
				File inFile = new File(filename); //this hopefully makes the .cyp file an obj
				Scanner inF = new Scanner(inFile); //makes scanner on .cyp file
			
				fHash = inF.nextLine(); //reads in file hash as a string
				if (hash != Integer.parseInt(fHash)) //if hashes are not equal, dont do anything
				{
					System.out.println("Incorrect password");
					worked = false;
				}
				else //if file is encrypted and hashes match, try to decrypt
				{
					try
					{				
						StringBuilder outFile = new StringBuilder(filename.replace(".cyp",".copy")); //makes name of .copy file
						PrintWriter outF = new PrintWriter(outFile.toString()); //makes PrintWriter for .copy file
			
						while (!EoF) //loops through each line of .cyp while till end
						{
							currLine = new StringBuilder(inF.nextLine());
				
							lineLength = currLine.length(); //find length of line
				
							tempLine = new StringBuilder("");
				
							for (int i = 0; i < lineLength; i++) //shifts each char in currLine and appends to encryptLine
							{
								if ((currLine.charAt(i) - key) > 0)
								{
									tempLine.append((char) ((currLine.charAt(i) - key) % 256));
								}
								else if ((currLine.charAt(i) - key) <= 0)
								{
									tempLine.append((char) (256 - Math.abs(currLine.charAt(i) - key)));
								}
							}
			
							outF.println(tempLine); //prints decryptLine to outFile
				
							EoF = !inF.hasNextLine(); //checks if inFile hasNextLine
						}
						
						outF.close();
						inF.close();
						
						worked = true;						
					}
					catch (IOException e) //this shouldnt happen
					{
						System.out.println("theres a logic error in this program");
						worked = false;
					}
				}
			}
			catch (IOException e1)
			{
				System.out.println("File does not exist");
				worked = false;
			}
		}
		
		return worked;
	}	

	//TO STRING
	public String toString()
	{
		StringBuilder S = new StringBuilder();
		S.append("Pass: " + passphrase + "\n");
		S.append("Hash: " + hash + "\n");
		S.append("Key: " + key + "\n");
		
		return S.toString();
	}
}
	
	
	
	
	
	
	
	
	
	