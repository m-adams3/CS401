// Michael Adams
// TuTh 1pm
// Assignment 3

public class Lingo
{
	//INSTANCE VARS
	private String word;
	private String guess;
	private char firstChar;
	private int temp = 0, diff;
	private int[] result = new int[5];
	private boolean[] ao1, ao2;
	StringBuilder sbWord, sbGuess, sbGuessTrim;
	
	
	//CONSTRUCTOR
	public Lingo(String w)
	{
		word = w;
	}
	
	//first method
	public char first()
	{
		firstChar = Character.toUpperCase(word.charAt(0));
		
		return firstChar;
	}
	
	//guessWord method
	public int[] guessWord(String g)
	{
		guess = g;
		
		sbWord = new StringBuilder(word.toUpperCase());
		sbGuess = new StringBuilder(guess.toUpperCase());
		
		for (int i = 0; i < 5; i++) //initialize result array to 0
		{
			result[i] = 0;
		}
		
		if (sbGuess.length() < 5) //fill in empty indicies with '-' (Dr. Ramirez said user will enter only letters)
		{
			//for (int i = 0; i < sbGuess.length(); i++) //makes sbGuess all uppercase
			//{
			//	sbGuess.setCharAt(i, Character.toUpperCase(sbGuess.charAt(i)));
			//}
			
			diff = 5 - sbGuess.length(); //difference in entered vs expected length
			
			for (int j = 0; j < diff; j++)
			{
				sbGuess.append('-');
			}
			
		}
		else if (sbGuess.length() > 5) //trim extra characters if length of guess > 5
		{
			sbGuessTrim = new StringBuilder();
		
			for (int i = 0; i < 5; i++)
			{
				sbGuessTrim.append(sbGuess.charAt(i));
			}
			
			sbGuess = new StringBuilder(sbGuessTrim);
		}
		
		ao1 = new boolean[5];
		ao2 = new boolean[5];
		
		for (int i = 0; i < 5; i++) //create boolean where true corresponds to correct letter in correct place
		{
			if (sbWord.charAt(i) == sbGuess.charAt(i))
			{
				ao2[i] = true;
				sbGuess.setCharAt(i, '-');
				sbWord.setCharAt(i, '~');
			}
			else
			{
				ao2[i] = false;
			}
		}
		
		for (int i = 0; i < 5; i++) //create boolean where true corresponds to correct letter in wrong place
		{
			if (sbWord.indexOf(Character.toString(sbGuess.charAt(i))) != -1)
			{
				ao1[i] = true;
				sbWord.setCharAt(sbWord.indexOf(Character.toString(sbGuess.charAt(i))), '~');
				sbGuess.setCharAt(i, '-');
			}
			else
			{
				ao1[i] = false;
				sbGuess.setCharAt(i, '-');
			}
		}
		
		for (int i = 0; i < 5; i++) //use boolean arrays to assing values to results array
		{
			if (ao2[i])
				result[i] = 2;
			else if (ao1[i])
				result[i] = 1;
		}
		
		return result;
	}
	
	//toString method
	public String toString()
	{
		StringBuilder S = new StringBuilder();
	
		S.append(word.toUpperCase());
		return S.toString();
	}
	
	
	
	
	
	
	
	
	
}