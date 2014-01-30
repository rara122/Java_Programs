import java.util.Scanner;

public class Pi
{
	private double [] ApproxList = {4.0};
	
	public Pi() 
	{  //Default Constructor: 1 Term
		this(1);
	}
	
	public Pi(int terms)
	{  //Negative # error check
		if (terms > 0)
		{
			//System.out.println("You did a good job, using " + terms);
			ApproxList = new double[terms];
			CalculatePi(terms);
		} 
		else 
		{
			System.out.println ("You cannot use Negative or Zero terms, defaulting: 1 term.");
		}
	}

	public void PrintOutput()
	{
		System.out.println("terms   PI approximation");
		for(int i = 0; i < ApproxList.length; i++ )
		{
			System.out.printf("%-8d", (i+1));
			System.out.println(ApproxList[i]);
		}
	}


	private void CalculatePi(int terms)
	{
		ApproxList[0] = 4.0;
		int sign = 1; //For Alternating +-
		for(int i = 1; i < ApproxList.length; i++ )
		{  //Sequence Pi = 4 - 4/3 + 4/5 - 4/7 + 4/9 - 4/11 + ...
			sign = sign * -1;
			ApproxList[i] = ApproxList[i-1] + (sign*4.0/(2*i+1));
		}
	}
	
	
	/* Sample Driver:[Create "onePie", INPUT terms and Print] */	
	public static void main (String[] args)
	{
		Scanner input = new Scanner (System.in);
		System.out.print("Exercise 5.20: 'Approximating PI'\n"
		  + "Compute to how many terms of the series? ");
		int terms = input.nextInt();
		Pi onePie = new Pi (terms);
		onePie.PrintOutput();
	}
}