// Program to perform ranked choice voting algorithm
// using a data file of voting preferences.
// This file should be *almost* identical to the code explained in the book
// Lines have been added to main to ask the user for the file to read
import java.util.;

public class TallyVotes
{
	private ArrayList<Ballot> ballots = new ArrayList<Ballot>();
	private ArrayList<String> candidates = new ArrayList<String>();
	private int original_ballots;
	/**
	 * @param c
	 */
	public TallyVotes(ArrayList<String> c)
	{
		candidates = new ArrayList<String>(c);
	}

	public final void addBallot(Ballot b)
	{
		ballots.add(b);
	}

	public final void processFile(String filename)
	{
		ifstream input_file = new ifstream(filename);
		String line = "";
		while (getline(input_file, line))
		{
			// Ignore blank lines
			if (line.isEmpty())
			{
				continue;
			}
			ArrayList<String> choices = new ArrayList<String>();
			String choice = "";
			for (int i = 0; i < line.length(); i++)
			{
				if (line.charAt(i) == ' ')
				{
					choices.add(choice);
					choice = "";
				}
				else
				{
					choice += line.charAt(i);
				}
			}
			choices.add(choice);
			Ballot b = new Ballot(choices);
			addBallot(new Ballot(b));
		}
		original_ballots = ballots.size();
	}

	public final void eliminateCandidate(String candidate)
	{
		for (int i = 0; i < ballots.size(); i++)
		{
			if (candidate.equals(ballots.get(i).getCandidate()))
			{
				ballots.get(i).eliminate();
			}
		}
	}

	public final void removeEmptyBallots()
	{
		for (int i = 0; i < ballots.size(); i++)
		{
			if (ballots.get(i).isEmpty())
			{
				ballots.remove(i);
				i--;
			}
		}
	}

	public final String getWinner()
	{
		int winner_count = 0;
		String winner = "none";
		while (winner_count <= original_ballots / 2)
		{
			// Count the number of votes for each candidate
			ArrayList<Integer> vote_count = tangible.VectorHelper.initializedArrayList(candidates.size(), 0);
			for (int i = 0; i < ballots.size(); i++)
			{
				for (int j = 0; j < candidates.size(); j++)
				{
					if (candidates.get(j).equals(ballots.get(i).getCandidate()))
					{
						vote_count.get(j)++;
					}
				}
			}
			// Find the candidate with the most votes
			int max_votes = 0;
			int max_index = 0;
			for (int i = 0; i < candidates.size(); i++)
			{
  if (vote_count.get(i) > max_votes)
  {
max_votes = vote_count.get(i);
max_index = i;
  }
			}
if (max_votes > original_ballots / 2)
{
return candidates.get(max_index);
}
else
{
eliminateCandidate(candidates.get(max_index));
removeEmptyBallots();
winner_count = max_votes;
winner = candidates.get(max_index);
}
		}
return winner;
	}
}

public class Globals
{
	public static import_Keyword java.util.* = new import_Keyword();
	public static import_Keyword java.io.* = new import_Keyword();

	public static int main()
	{
	ArrayList<String> candidates = new ArrayList<String>(Arrays.asList("A", "B", "C", "D"));
	TallyVotes tv = new TallyVotes(candidates);
	String file_name = "";
	while (true)
	{
	System.out.print("Enter file name or type 'quit' to exit: ");
	file_name = ConsoleInput.readToWhiteSpace(true);
	if (file_name.equals("quit"))
	{
	break;
	}
	tv.processFile(file_name);
	}
	String winner = tv.getWinner();
	System.out.print("Winner: ");
	System.out.print(winner);
	System.out.print("\n");
	return 0;
	}

//Helper class added by C++ to Java Converter:

package tangible;

//----------------------------------------------------------------------------------------
//	Copyright © 2006 - 2023 Tangible Software Solutions, Inc.
//	This class can be used by anyone provided that the copyright notice remains intact.
//
//	This class is used to convert some of the C++ std::vector methods to Java.
//----------------------------------------------------------------------------------------
import java.util.*;

public final class VectorHelper
{
	public static <T> void resize(ArrayList<T> list, int newSize)
	{
		resize(list, newSize, null);
	}

	public static <T> void resize(ArrayList<T> list, int newSize, T value)
	{
		if (list.size() > newSize)
		{
			for (int i = list.size() - 1; i >= newSize; i--)
			{
				list.remove(i);
			}
		}
		else if (list.size() < newSize)
		{
			for (int i = list.size(); i < newSize; i++)
			{
				list.add(value);
			}
		}
	}

	public static <T> void swap(ArrayList<T> list1, ArrayList<T> list2)
	{
		ArrayList<T> temp = new ArrayList<T>(list1);
		list1.clear();
		list1.addAll(list2);
		list2.clear();
		list2.addAll(temp);
	}

	public static <T> ArrayList<T> initializedArrayList(int size, T value)
	{
		ArrayList<T> temp = new ArrayList<T>();
		for (int count = 1; count <= size; count++)
		{
			temp.add(value);
		}

		return temp;
	}

	public static <T> ArrayList<ArrayList<T>> nestedArrayList(int outerSize, int innerSize)
	{
		ArrayList<ArrayList<T>> temp = new ArrayList<ArrayList<T>>();
		for (int count = 1; count <= outerSize; count++)
		{
			temp.add(new ArrayList<T>(innerSize));
		}

		return temp;
	}

	public static <T> ArrayList<ArrayList<T>> nestedArrayList(int outerSize, int innerSize, T value)
	{
		ArrayList<ArrayList<T>> temp = new ArrayList<ArrayList<T>>();
		for (int count = 1; count <= outerSize; count++)
		{
			temp.add(initializedArrayList(innerSize, value));
		}

		return temp;
	}
}

//Helper class added by C++ to Java Converter:

package tangible;

//----------------------------------------------------------------------------------------
//	Copyright © 2006 - 2023 Tangible Software Solutions, Inc.
//	This class can be used by anyone provided that the copyright notice remains intact.
//
//	This class provides the ability to convert basic C++ 'cin' behavior.
//----------------------------------------------------------------------------------------
public final class ConsoleInput
{
	private static boolean goodLastRead = false;
	public static boolean lastReadWasGood()
	{
		return goodLastRead;
	}

	public static String readToWhiteSpace(boolean skipLeadingWhiteSpace)
	{
		String input = "";
		char nextChar;
		while (Character.isWhitespace(nextChar = (char)System.in.read()))
		{
			//accumulate leading white space if skipLeadingWhiteSpace is false:
			if (!skipLeadingWhiteSpace)
			{
				input += nextChar;
			}
		}
		//the first non white space character:
		input += nextChar;

		//accumulate characters until white space is reached:
		while (!Character.isWhitespace(nextChar = (char)System.in.read()))
		{
			input += nextChar;
		}

		goodLastRead = input.length() > 0;
		return input;
	}

	public static String scanfRead()
	{
		return scanfRead(null, -1);
	}

	public static String scanfRead(String unwantedSequence)
	{
		return scanfRead(unwantedSequence, -1);
	}

	public static String scanfRead(String unwantedSequence, int maxFieldLength)
	{
		String input = "";

		char nextChar;
		if (unwantedSequence != null)
		{
			nextChar = '\0';
			for (int charIndex = 0; charIndex < unwantedSequence.length(); charIndex++)
			{
				if (Character.isWhitespace(unwantedSequence.charAt(charIndex)))
				{
					//ignore all subsequent white space:
					while (Character.isWhitespace(nextChar = (char)System.in.read()))
					{
					}
				}
				else
				{
					//ensure each character matches the expected character in the sequence:
					nextChar = (char)System.in.read();
					if (nextChar != unwantedSequence.charAt(charIndex))
						return null;
				}
			}

			input = (new Character(nextChar)).toString();
			if (maxFieldLength == 1)
				return input;
		}

		while (!Character.isWhitespace(nextChar = (char)System.in.read()))
		{
			input += nextChar;
			if (maxFieldLength == input.length())
				return input;
		}

		return input;
	}
}
