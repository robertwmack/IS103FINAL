public class FiveDice
{
	final int DICE = 5;
	int[] Dice = new int[DICE];
	public FiveDice(boolean a)
	{
		Die die = new Die();
		for(int i = 0; i < DICE; ++i)
		{
			if (a == false) // runs standard die
			{
				die.rollDie();
				Dice[i] = die.getDie();
			}
			else
			{
				die.rollLoadedDie(); //runs loaded die
				Dice[i] = die.getDie();
			}
		}		
	}
	public int[] getDice()
	{
		return Dice;
	}
}