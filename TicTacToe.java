import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class TicTacToe 
{
	private static char [][] Board = {{' ', ' ', ' '},
									  {' ', ' ', ' '},
									  {' ', ' ', ' '}};
	private static int Turn = 0;
	private Player[] player;
	final private char[] Mark = {'X', 'O'};
	
	
	
	// ::::::::: Constructors ::::::::: //
	
		//Default Constructor 2 humans
	TicTacToe()
	{
		player = new Player [2];
		player[0] = new Human();
		player[1] = new Human();
	}
	
		//Constructor with Computers. (CPUnum = 0: No Humans)
	TicTacToe(int CPUnum)
	{
		player = new Player [2];
		if(CPUnum == 1)
		{
			player[0] = new Computer();
			player[1] = new Human();
		}
		else if (CPUnum == 2)
		{
			player[0] = new Human();
			player[1] = new Computer();
		}
		else
		{
			player[0] = new Computer();
			player[1] = new Computer();
		}
	}
	
	
	
	// ::::::::: Public Functions ::::::::: //
	
	public int TakeTurn()
	{	//RETURNS [-1 = No Winner], [0 = Tie], [1 = P1 Win], [2 = P2 Win]
		player[Turn%2].MakeMove();
		int winner = WinnerFound();
		IncrementTurn();
		return winner;
	}
	
	public void PrintWinner(int winNum)
	{	//Print Board and Winner
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n" + this);
		if(winNum > 0)
		{
			System.out.println("\n\nWINNER IS PLAYER " + ((Turn-1)%2 + 1) + " (" + Mark[(Turn-1)%2] + ")");
		}
		else
		{
			System.out.println("\n\nNO WINNER, THERE WAS A TIE");
		}
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder ();
		sb.append("Game Board:                Positions:\n\n");
		for (int i=0; i < 3; i++)
		{
			sb.append(" " + Board[i][0] + " | " + Board[i][1] 
					  + " | " + Board[i][2] + "                  "
					  + ((3*i)+1) + " | " + ((3*i)+2) + " | " 
					  + ((3*i)+3) + " \n");
			if(i < 2)
				sb.append("-----------                -----------\n");
		}
		return sb.toString();
	}
	
	
	
	// ::::::::: Private Functions ::::::::: //	
	
	private boolean FillSlot (int pos)
	{
		if(pos < 1 | pos > 9) return false;
		int[] rowCol = FindSlot(pos);
		if(Board[rowCol[0]][rowCol[1]] == ' ')
		{
			Board[rowCol[0]][rowCol[1]] = Mark[Turn%2];
			return true;
		}
		else 
			return false;
	}
	
	private void IncrementTurn()
	{
		Turn = Turn +1;
	}

	private int WinnerFound()
	{	  //RETURNS [-1 = No Winner], [0 = Tie], [1 = P1 Win], [2 = P2 Win]
		int row, col, diag, diag2;
		diag = diag2 = 0;
		for(int i=0; i<3; i++)
		{
			row = col = 0;
			for(int j=0; j<3; j++)
			{
				if(Board[i][j] == Mark[(Turn)%2])
				{	//Check Rows
					row += 1;
				}
				if(Board[j][i] == Mark[(Turn)%2])
				{	//Check Columns
					col += 1;
				}				
				if (row > 2| col > 2)
				{	//Found a win in row or col
					return (Turn-1)%2 + 1;
				}				
			}
			if(Board[i][i] == Mark[(Turn)%2])
			{	//Check Diagonals
				diag += 1;
			}			
			if(Board[2-i][i] == Mark[(Turn)%2])
			{	//Check Diagonals Reversed
				diag2 += 1;
			}
			if(diag > 2 | diag2 > 2)
			{	//Found a win in diag or diag2	
				return (Turn-1)%2 + 1;
			}
		}
		if(Turn >= 8)
		{	//Found a tie
			return 0;
		}
		return -1;
	}
	
	private ArrayList<Integer> AvailableSlots()
	{
		ArrayList<Integer> available = new ArrayList<Integer>();
		for(int i=0; i < 3; i++)
		{
			for(int j=0; j<3; j++)
			{
				if(Board[i][j] == ' ')
				{
					available.add((3*i)+(j+1));
				}
			}
		}
		return available;
	}
	
	private int[] FindSlot (int pos)
	{	//Position 1-9 to slot [3]X[3]
		int[] slot = new int [2];
		slot[0] = (pos-1)/3;
		slot[1] = (pos-1)%3;
		return slot;
	}
	
	private int FindPos (int [] slot)
	{	//Slot [3]X[3] to position 1-9
		return (3*slot[0] + 1 + slot[1]);
	}
	
	
	
	// ::::::::: Nested Classes ::::::::: //

	abstract class Player 
	{
		public void MakeMove()
		{
			System.out.println("PLAYER TAKE TURN");
		}
	}

	class Human extends Player
	{
		Human()
		{
		}

		public void MakeMove()
		{
			int pos = -1;
			while(true)
			{
				Scanner input = new Scanner (System.in);
				System.out.print("\nPlayer " + ((Turn%2)+1) 
					+ ", please enter a move (1-9): ");
				pos = input.nextInt();
				if(FillSlot(pos))
				{
					break;
				}
				else
				{
					System.out.print("\nInvalid Slot Choice: " 
						+ "\nMake sure that it is not already filled "
						+ "and is between the positions 1-9.\n");
				}
			}
		}
	}

	class Computer extends Player
	{
		Computer()
		{
		}
		
		public void MakeMove()
		{
			int pos = -1;
			int [] choiceMove = FindMove();
			Random rand = new Random(System.currentTimeMillis());
			
			if (choiceMove[0] != -1)
				pos = FindPos(choiceMove);
			else
			{
				ArrayList<Integer> available = AvailableSlots();
				pos = rand.nextInt() % available.size();
				if (pos <= 0) pos += available.size();
				pos = available.get(pos-1);
			}
			FillSlot(pos);
			System.out.print("\nPlayer (computer) chooses position " + pos);
		}
		
		private int[] FindMove()
		{
			int [] pos = {-1, -1};
			int row, col, diag, diag2;
			for(int h=0; h<2; h++)
			{
			diag = diag2 = 0;
				for(int i=0; i<3; i++)
				{
					row = col = 0;
					for(int j=0; j<3; j++)
					{
						if(Board[i][j] == Mark[(Turn+h)%2])
						{	//Check Rows
							row += 1;
						}
						if(Board[j][i] == Mark[(Turn+h)%2])
						{	//Check Columns
							col += 1;
						}				
						if (row >= 2)
						{	//Found a win in row
							for(int k=0; k<3; k++)
							{
								if(Board[i][k] == ' ')
								{
									pos[0] = i;
									pos[1] = k;								
									return pos;
								}
							}
						}		
						if (col >= 2)
						{	//Found a win in col
							for(int k=0; k<3; k++)
							{
								if(Board[k][i] == ' ')
								{
									pos[0] = k;
									pos[1] = i;								
									return pos;
								}
							}
						}
					}
					if(Board[i][i] == Mark[(Turn+h)%2])
					{	//Check Diagonals
						diag += 1;
					}
					if(Board[2-i][i] == Mark[(Turn+h)%2])
					{	//Check Diagonals Reversed
						diag2 += 1;
					}
					if (diag >= 2)
					{	//Found a win in diag
						for(int k=0; k<3; k++)
						{
							if(Board[k][k] == ' ')
							{
								pos[0] = k;
								pos[1] = k;								
								return pos;
							}
						}
					}
					if (diag2 >= 2)
					{	//Found a win in diag2
						for(int k=0; k<3; k++)
						{
							if(Board[2-k][k] == ' ')
							{
								pos[0] = 2 - k;
								pos[1] = k;								
								return pos;
							}
						}
					}
				}	//end for i 
			}	//end for h
			return pos;
		}
	}

	
	
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	/*~~~~~~~~~~~~~~~~~~~~~~~~~ MAIN Method ~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	public static void main (String [] args)
	{
		TicTacToe t1 = null;
		if(args.length >= 0 && args.length <= 2){
			if(args.length == 2)
			{
				if(args[0].equals("-c") && (args[1].equals("1") || args[1].equals("2")))
				{	//1 CPU
					t1 = new TicTacToe(Integer.parseInt(args[1]));
				}
				else
				{
					System.out.println("Usage:  java TicTacToe [-c [1|2]]");
					System.exit(-1);
				}
			}
			else if (args.length == 1)
			{	//2 CPUs
				if(args[0].equals("-c"))
				{
					t1 = new TicTacToe(0);
				}
				else
				{
					System.out.println("Usage:  java TicTacToe [-c [1|2]]");
					System.exit(-1);
				}
			}
			else
			{	//No CPUs
				t1 = new TicTacToe();
			}
		}
		else
		{	//Args Error
			System.out.println("Usage:  java TicTacToe [-c [1|2]]");
			System.exit(-1);
		}

		int winner;
		do
		{	//Take turns Playing
			System.out.print(t1);
			winner = t1.TakeTurn();
			System.out.println("\n\n\n");
		}while(winner < 0);
		
		t1.PrintWinner(winner);
	}
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
}

