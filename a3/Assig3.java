// Michael Adams
// TuTh 1pm
// Assignment 3

import java.util.*;
import java.io.*;

public class Assig3
{
	public static void main(String[] args) throws IOException
	{
		//READ FILE FROM COMMMAND LINE
		File inFile = null;
		if (0 < args.length)
		{
			inFile = new File(args[0]);
		}
		String filename = inFile.toString(); //convert file to string
		
		//VARS
		boolean done = false;
		int tries = 0;
		int action = 0;
		int[] result;
		String tempGuess;
		LingoServer LS;
		Lingo L;
		Scanner inScan = new Scanner(System.in);
		
		
		System.out.println("Welcome to Lingo!");
		
		LS = new LingoServer(filename); //create new lingoServer
		
		do{
			System.out.println("Would you like to play? 1 = YES, 2 = NO");
			action = inScan.nextInt();
			inScan.nextLine();
		} while (action != 1 && action != 2);
		
		if (action ==1)
			done = false;
		else
			done = true;
		
		while (!done && LS.hasLingo()) //lets user play til done or no more words
		{
			System.out.println("You have 5 tries to guess a 5 letter word");
			System.out.println("\t Letters that are in the word in the correct location are in CAPS");
			System.out.println("\t Letters that are in the word in the incorrect location are in lower case");
			System.out.println("\t Letters that do not appear in the word are shown as hyphens");
			
			L = LS.getLingo(); //assigns Lingo obj to L
			
			System.out.println();
			System.out.println("Your word begins with " + L.first());
			
			tries = 0;
			do {
				System.out.println("Enter guess # " + (tries + 1));
				tempGuess = inScan.nextLine();
				
				result = new int[5]; //create new result array
				result = L.guessWord(tempGuess); //get result array back from Lingo class
				
				System.out.println("Here are your results:");
				System.out.println("0 1 2 3 4");
				System.out.println("---------");
				//System.out.print(Character.toUpperCase(L.toString().charAt(0)));
				
				for (int i = 0; i < 5; i++)
				{
					if (result[i] == 2)
						System.out.print(Character.toUpperCase(tempGuess.charAt(i)) + " ");
					else if (result[i] == 1)
						System.out.print(Character.toLowerCase(tempGuess.charAt(i)) + " ");
					else if (result[i] == 0)
						System.out.print("- ");
				}
				
				System.out.println();
				
				tries++;
				
			} while (!L.toString().toUpperCase().equals(tempGuess.toUpperCase()) && tries < 5);
			
			if (L.toString().toUpperCase().equals(tempGuess.toUpperCase()))
				System.out.println("Congrats, you got it");
			else
				System.out.println("You didn't get it, the word was " + L.toString().toUpperCase());
			
			System.out.println();
			
			do { //choose action
				System.out.println("Would you like to play again? 1 = YES, 2 = NO");
				action = inScan.nextInt();
				inScan.nextLine();
			} while (action != 1 && action != 2);
			
			if (action ==1)
				done = false;
			else
				done = true;
			
		}//while !done and hasLingo
		
		if (!LS.hasLingo())
			System.out.println("Sorry no words left");
		else
			System.out.println("Thanks for playing");
	}
}