public class Die
{
	private int die;
	private int side = 6;
	
	public void rollDie()
	{
		die = ((int)(Math.random() * 100) % side) + 1;
	}
	public Die()
	{
		rollDie();
	}
	public int getDie()
	{
		return die;
	}
	public void rollLoadedDie()
	{
		die = ((int)(Math.random() * 100) % (side - 1)) + 2;
	}
}