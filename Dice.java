import java.util.Scanner;
import java.util.Random;

public class Dice 
{
	  //Offset by 2 < [0] is rolling a 2, [4] is 6 >
	private int ResultsList [];
	private int TotalRolls;
	
	public Dice ()
	{
		this(1);
	}	
	public Dice (int num)
	{
		ResultsList = new int [11];
		TotalRolls = num;
		Calculate(num);
	}
	
	public void PrintResults()
	{
		double Percent;
		System.out.println ("\nSum     # of times      Percentage\n");
		for(int i = 2; i < ResultsList.length +2; i++)
		{
			Percent = ((double) ResultsList[i-2] / (double) TotalRolls) * 100;
			System.out.printf("%-8d%-16d%.2f%%\n", i, ResultsList[i-2], Percent);
		}
	}
	
	private void Calculate (int num)
	{
		Random Rando = new Random (System.currentTimeMillis());
		int Roll1, Roll2, Sum;
		for (int i = 0; i < num; i++)
		{
			Roll1 = (Rando.nextInt() % 6 + 6) % 6 + 1;
			Roll2 = (Rando.nextInt() % 6 + 6) % 6 + 1;
			Sum = Roll1 + Roll2;
			
			ResultsList[Sum-2] = ResultsList[Sum-2] + 1;
		}
	}
	
	public static void main (String[] args)
	{
		Scanner input = new Scanner (System.in);
		System.out.print("Programming Exercise 7.17 'Rolling the dice'\n"
		+ "How many rolls of the dice? ");
		int num = input.nextInt();
		Dice newDice = new Dice (num);
		newDice.PrintResults();
		
	}
}