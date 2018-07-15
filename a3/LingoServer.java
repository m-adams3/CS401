// Michael Adams
// TuTh 1pm
// Assignment 3

import java.util.*;
import java.io.*;

public class LingoServer
{
	//INSTANCE VARS
	private String filename;
	private String[] words;
	
	private String garbage;
	private boolean hasNext;
	private int indx = 0;
	private int lines = 0;
	private int[] indxArray;
	private int[] randIndxArray;
	
	private Lingo temp;
	private Lingo[] lingoArray = new Lingo[10];
	
	private Scanner inScan = new Scanner(System.in);
	
	//LingoServer method
	public LingoServer(String f)
	{
		filename = f;
		
		try
		{
			File inFile = new File(filename);
			Scanner lScan = new Scanner(inFile);
			Scanner fScan = new Scanner(inFile);
			
			do { //find the number of lines in the file
				lScan.nextLine();
				lines++;			
			} while (lScan.hasNextLine());
		
			indxArray = new int[lines];
			words = new String[lines]; //create a String array of words for each word in file
		
			for (int i = 0; i < lines; i++) 
			{
				indxArray[i] = i;
			}
		
			randIndxArray = shuffle(indxArray); //shuffle the index array
			
			for (int j = 0; j < lines; j++) //randomly fill words array with Strings
			{
				//System.out.print(randIndxArray[j] + " ");
				words[randIndxArray[j]] = fScan.nextLine();
			}
			
			for (int k = 0; k < lines; k++)
			{
				if (k == lingoArray.length) //check to see if array needs to be resized
				{
					lingoArray = resize(lingoArray);
				}
				
				lingoArray[k] = new Lingo(words[k]);
			}
			
		}
		catch (IOException e)
		{
			System.out.println("File does not exist");
		}
	}
	
	//hasLingo method
	public boolean hasLingo()
	{
		return (indx < lines);
	}
	
	//getLingo method
	public Lingo getLingo()
	{	
		indx++;
		return lingoArray[indx-1];
	}
	
	//toString method
	public String toString()
	{
		StringBuilder S = new StringBuilder();
		S.append("Lingo Server Info:");
		S.append(" Number of words: " + (lines - indx));
		S.append(" Capacity: " + lingoArray.length);
		
		return S.toString();
	}
	
	//shuffle method - implement Fisher-Yates shuffling algorithim bc we cant use arrayList 
	private int[] shuffle(int[] array)
	{
		int n = array.length;
	
		for (int i = 0; i < array.length; i++) 
		{
			// Get a random index of the array past i.
			int random = i + (int) (Math.random() * (n - i));
			
			// Swap the random element with the present element.
			int randomElement = array[random];
			
			array[random] = array[i];
			array[i] = randomElement;
		}
		
		return array;
    }

	//resize method - bc we cant use arrayList
	private Lingo[] resize(Lingo[] oldA)
	{
		int oldL = oldA.length;
		int newL = 2 * oldL;
		Lingo[] newA = new Lingo[newL];
		for (int i = 0; i < oldA.length; i++)
			newA[i] = oldA[i];
		return newA;
	}
	
}