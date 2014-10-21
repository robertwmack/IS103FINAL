import java.util.Scanner;

public class DieGame 
{
	static String p1 = "Player 1";
	static String p2 = "Player 2";
	static String winner;
	static String winningCombo;
	static boolean tied = false;
	static boolean trueTie = false;
	static boolean game = false;
	static boolean loaded1 = false;
	static boolean loaded2 = true;
	static boolean fileOutput = false;
	static int winningNumber;
	static int kicker;
	static int sides = 6;
	static int dice = 5;
	static int exit = 0;
	static int game1Ties = 0;
	static int game2Ties = 0;
	static int player1Game1Wins = 0;
	static int player2Game1Wins = 0;
	static int player1Game2Wins = 0;
	static int player2Game2Wins = 0;
	static String[] codes = {"Five of a Kind", "Four of a Kind", "Three of a Kind", "Pair", "High Card"};
	public static void main(String[] args)
	{
		manualGame();
		run1000Games();
		run1000LoadedGames();
		output();
	}
	public static void dieGame(boolean output)
	{
		tied = false;
		trueTie = false;
		game = false;
		int[] p1count = new int[sides];
		int[] p2count = new int[sides];
		FiveDice roll1 = new FiveDice(loaded1);
		FiveDice roll2 = new FiveDice(loaded2);
		int[] diceP1 = roll1.getDice();
		int[] diceP2 = roll2.getDice();
		System.out.println("Player 1's dice");
		for(int i = 0; i < dice; ++i)
			System.out.print(diceP1[i] + " ");
		System.out.println();
		System.out.println("Player 2's dice");
		for(int i = 0; i < dice; ++i)
			System.out.print(diceP2[i] + " ");
		System.out.println();
		for(int x = 1; x <= sides; ++x) //	Counts the number of 1-6s on each players dice.
		{
			for(int i = 0; i < dice; ++i)
			{
				if(diceP1[i] == x)
					++p1count[x-1];
				if(diceP2[i] == x)
					++p2count[x-1];
			}
		}
		for(int i = 5; i >= 0; --i) //	Checks for 5 of a kind
		{
			game = check5(p1count[i], p2count[i], i);
			if (game == true)
				break;
		}
		if (game == false)
		{
			for(int i = 5; i >= 0; --i) //	Checks for 4 of a kind
			{
				game = check4(p1count[i], p2count[i], i);
				if (game == true)
					break;		
			}
		}
		if (game == false)
		{
			for(int i = 5; i >= 0; --i) //	Checks for 3 of a kind
			{
				game = check3(p1count[i], p2count[i], i);
				if (game == true)
					break;		
			}
		}
		if (game == false)
		{
			for(int i = 5; i >= 0; --i) //	Checks for pair
			{
				game = check2(p1count[i], p2count[i], i);
				if (game == true)
					break;		
			}
		}
		if (game == false)
		{
			for(int i = 5; i >= 0; --i) // Checks for high die.
			{
				game = check1(p1count[i], p2count[i], i);
				if (game == true)
					break;
			}
		}
		if (tied == true && winningCombo != codes[0])
		{
			for(int i = 5; i >= 0; --i) // Checks for tie breaker
			{
				boolean kick = tieBreaker(p1count[i], p2count[i], i);
				if (kick == true)
					break;
			}
		} 
		if(output = false)
			display();
	}
	public static boolean check5(int a, int b, int c)
	{
		if (a == 5 && b == 5)
		{
			tied = true;
			trueTie = true;
			winningCombo = codes[0];
			winningNumber = c + 1;
			System.out.println("The odds are 1 in " + Math.pow(6,  10) + " to have tied in a five of a kind!");
			return true;
		}
		else if (a == 5)
		{
			winner = p1;
			winningCombo = codes[0];
			winningNumber = c + 1;
			return true;
		}
			
		else if (b == 5)
		{
			winner = p2;
			winningCombo = codes[0];
			winningNumber = c + 1;
			return true;
		}
		else
			return false;
	}
	public static boolean check4(int a, int b, int c)
	{
		if (a == 4 && b == 4)
		{
			tied = true;
			winningCombo = codes[1];
			winningNumber = c + 1;
			return true;
		}
		else if (a == 4)
		{
			winner = p1;
			winningCombo = codes[1];
			winningNumber = c + 1;
			return true;
		}
			
		else if (b == 4)
		{
			winner = p2;
			winningCombo = codes[1];
			winningNumber = c + 1;
			return true;
		}
		else
			return false;
	}
	public static boolean check3(int a, int b, int c)
	{
		if (a == 3 && b == 3)
		{
			tied = true;
			winningCombo = codes[2];
			winningNumber = c + 1;
			return true;
		}
		else if (a == 3)
		{
			winner = p1;
			winningCombo = codes[2];
			winningNumber = c + 1;
			return true;
		}
			
		else if (b == 3)
		{
			winner = p2;
			winningCombo = codes[2];
			winningNumber = c + 1;
			return true;
		}
		else
			return false;
	}
	public static boolean check2(int a, int b, int c)
	{
		if (a == 2 && b == 2)
		{
			tied = true;
			winningCombo = codes[3];
			winningNumber = c + 1;
			return true;
		}
		else if (a == 2)
		{
			winner = p1;
			winningCombo = codes[3];
			winningNumber = c + 1;
			return true;
		}
			
		else if (b == 2)
		{
			winner = p2;
			winningCombo = codes[3];
			winningNumber = c + 1;
			return true;
		}
		else
			return false;
	}
	public static boolean check1(int a, int b, int c)
	{
		if(a == b)
			return false;
		else if (a ==1)
		{
			winner = p1;
			winningCombo = codes[4];
			winningNumber = c + 1;
			return true;
		}
		else if (b == 1)
		{
			winner = p2;
			winningCombo = codes[4];
			winningNumber = c + 1;
			return true;
		}
		else
			return false;
	}
	public static boolean tieBreaker(int a, int b, int c)
	{
		if(winningNumber == (c + 1))
			return false;
		if(a > 1 && b > 1)
		{
			trueTie = true;
			return true;
		}
		else if(a > b)
		{
			winner = p1;
			kicker = c + 1;
			return true;
		}
		else if(b > a)
		{
			winner = p2;
			kicker = c + 1;
			return true;
		}
		else
			return false;
	}
	public static void display()
	{
		if (trueTie == true)
			System.out.println("Players tied with a " + winningCombo + " of " + winningNumber + "s with a " + kicker + " kicker!");
		System.out.print(winner + " won with a " + winningCombo + " of " + winningNumber + "s");
		if (tied == true)
			System.out.print(" and a " + kicker + " kicker.");
	}
	public static void manualGame()
	{
		Scanner in = new Scanner(System.in);
		while (true)
		{
			dieGame(fileOutput);
			System.out.println("\nEnter 0 to quit or anything to play again");
			
			if (in.next().charAt(0) == '0')
				break;
		}
		in.close();
	}
	public static void run1000Games()
	{
		fileOutput = true;
		for (int i = 0; i < 1000; ++i)
			dieGame(fileOutput);
			outputPrep();
	}
	public static void run1000LoadedGames()
	{
		loaded2 = true;
		run1000Games();
		outputPrep2();
	}
	public static void outputPrep()
	{
		if(trueTie == true)
			++game1Ties;
		if(winner.equals(p1))
			++player1Game1Wins;
		if(winner.equals(p2))
			++player2Game1Wins;
	}
	public static void outputPrep2()
	{
		if(trueTie == true)
			++game2Ties;
		if(winner.equals(p1))
			++player1Game2Wins;
		if(winner.equals(p2))
			++player2Game2Wins;
	}
	public static void output()
	{
		
	}
}

