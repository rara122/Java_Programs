// class Fraction
// Author:  Bob Myers
// Edit/Updates by : Je Hong Yoo
// For COP3252, Java Programming

public class Fraction
{
  private int numerator = 0;		// numerator (and keeps sign)
  private int denominator = 1;		// always stores positive value

  public Fraction()
  {
  }

  public Fraction(int n, int d)
  {
    if (set(n,d)==false)
	set(0,1);
  }

  public boolean set(int n, int d)
  {
    if (d > 0)
    {
	numerator = n;
	denominator = d;
	return true;
    }
    else
	return false;
  }
  
  public String toString()
  {
    return (numerator + "/" + denominator);
  }

  public int getNumerator()
  {
    return numerator;
  }

  public int getDenominator()
  {
    return denominator;
  }

  public double decimal()
  {
    return (double)numerator / denominator;
  }
  
  public Fraction add(Fraction f)
  {
	int n = f.getNumerator() * denominator + numerator * f.getDenominator();
	int d = f.getDenominator() * denominator;
	Fraction newFrac = new Fraction (n, d);
	return newFrac.simplify();
  }
  
  public Fraction subtract(Fraction f)
  {
	int n = numerator * f.getDenominator() - f.getNumerator() * denominator;
	int d = f.getDenominator() * denominator;
	Fraction newFrac = new Fraction (n, d);
	return newFrac.simplify();
  }
  
  public Fraction multiply(Fraction f)
  {
	int n = numerator * f.getNumerator();
	int d = f.getDenominator() * denominator;
	Fraction newFrac = new Fraction (n, d);
	return newFrac.simplify();
  }
  
  public Fraction divide(Fraction f)
  {
	int n = f.getDenominator() * numerator;
	int d = denominator * f.getNumerator();
	if (d < 0)
	{
		d = d * -1;
		n = n * -1;
	}
	Fraction newFrac = new Fraction (n, d);
	return newFrac.simplify();
  }
  
  public Fraction simplify () 
  {
	int gcd = GCD (denominator, numerator);
	Fraction newFrac = new Fraction (numerator/gcd, denominator/gcd);
	return newFrac;
  }
  
  private int GCD (int a, int b) 
  { //Helper Function, Finds Greatest Common Divisor
	if (a < 0)
		a = a * -1;
	if (b < 0)
		b = b * -1;	
	return b == 0 ? a : GCD (b, a%b); 
  }

}