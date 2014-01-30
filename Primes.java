import java.util.Scanner;
import java.util.Arrays;

public class Primes
{
	private boolean [] PrimesList;
	
	public Primes ()
	{
		this(-1);
	}
	
	public Primes (int num) 
	{  //Negative # error check
		if (num > 1)
		{
			isPrime(num);
		} 
		else 
		{
			System.out.println ("Primes start at 2.");
		}
	}
	
	public boolean isPrime (int num) 
	{  
		return SieveofEraatttaaa(num);
	}
	
	public void PrintOutput(int num)
	{
		System.out.println("The prime numbers less than or equal to " + num + " are:");
		int j = 0;
		for (int i = 2; i < PrimesList.length; i++)
		{
			if(PrimesList[i])
			{
				System.out.printf("%-8d", i);
				j = j+1;
				if ((j % 8) == 0)
				{
					System.out.print ("\n"); 
					j = 0;
				}
			}
		}
	}

	private boolean SieveofEraatttaaa (int num)
	{
		  //(PrimesList[] == true) ? Prime : NotPrime
		PrimesList = new boolean[num];
		Arrays.fill(PrimesList, true);
		PrimesList[0] = PrimesList[1] = false;
		for (int i = 2; i < num; i++)
		{
			if(PrimesList[i])
			{
				  //All Multiples are composite
				for(int j = 2; i*j < PrimesList.length; j++)
				{
					PrimesList[i*j] = false;
				}
			}
		}
		return PrimesList[PrimesList.length-1];
	}
	
	  //Sample Driver
	public static void main (String[] args)
	{
		Scanner input = new Scanner (System.in);
		System.out.print("Exercise 6.25: 'Primes'\n"
		  + "Please input a positive number: ");
		int num = input.nextInt() + 1; //OFFSET so Array[0] is 0;
		Primes onePrime = new Primes (num);
		onePrime.PrintOutput(num);
	}
}