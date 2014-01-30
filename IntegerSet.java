/* Author: Je Hong Yoo */

import java.util.Scanner;

public class IntegerSet {
	private boolean [] Set;
	
	public IntegerSet()
	{
		Set = new boolean [101];
	}
	
	public IntegerSet union(IntegerSet iSet)
	{
		IntegerSet temp = new IntegerSet();
		Scanner input = new Scanner(iSet.toString());
		Scanner input2 = new Scanner(toString());
		while(input.hasNextInt())
		{
			temp.insertElement(input.nextInt());
		}
		while(input2.hasNextInt())
		{
			temp.insertElement(input2.nextInt());
		}

		return temp;
	}
	
	public IntegerSet intersection(IntegerSet iSet)
	{
		IntegerSet temp = new IntegerSet ();
		Scanner input = new Scanner (iSet.toString());
		int i;
		while (input.hasNextInt())
		{
			i = input.nextInt();
			if(Set[i])
				temp.insertElement(i);
		}
		return temp;
	}	
	
	public IntegerSet insertElement(int data)
	{
		Set[data] = true;
		return this;
	}
	
	public IntegerSet deleteElement(int data)
	{
		Set[data] = false;
		return this;
	}
	
	public boolean isEqualTo(IntegerSet iSet)
	{
		if(this.toString().length() != iSet.toString().length())
		{
			return false;
		}
		
		Scanner input = new Scanner(iSet.toString());
		while(input.hasNextInt())
		{
			if(Set[input.nextInt()] == false)
			{
				return false;
			}
		}
		return true;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder ();
		for (int i = 0; i < Set.length; i++)
		{
			if (Set[i]){
				if (sb.length() != 0){
					sb.append(" ");
				}
				sb.append(i);
			}
		}
		if (sb.toString().length() == 0)
			return("---");
		return (sb.toString());
	}
	

	public static void main(String [] args)
	{
		IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.insertElement(0);
        set1.insertElement(2);
        set1.insertElement(8);
        System.out.println("After set1.insertElement(10), set1 = " 
				+ set1.insertElement(10));
        set1.insertElement(4);
        set1.insertElement(6);
        set1.insertElement(10);
        set1.insertElement(100);
        set1.insertElement(12);
        set1.insertElement(95);

        set2.insertElement(0);
        set2.insertElement(3);
        set2.insertElement(6);
        set2.insertElement(9);
        set2.insertElement(12);

        System.out.println("default IntegerSet is = " + (new IntegerSet()));
        System.out.println("set1 = " + set1);
        System.out.println("set2 = " + set2);
        System.out.println("set1.union(set2) = " + set1.union(set2));
        System.out.println("set1.intersection(set2) = " + set1.intersection(set2));
        System.out.println("set1.deleteElement(2) = " + set1.deleteElement(2));
        System.out.println("set1.isEqualTo(set1) = " + set1.isEqualTo(set1));
        System.out.println("set1.isEqualTo(set2) = " + set1.isEqualTo(set2));
	}

}