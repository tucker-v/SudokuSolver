/**
 * @author - Tucker Vann
 */
package sudokusolver;
import java.util.Scanner;
/**
 * 
 */
public class SudokuSolver 
{
	public static int board [][] = new int[9][9];
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < 9; i++)
		{
			System.out.println("Enter row #" + (i + 1));
			String str = scan.next();
			for (int j = 0; j < 9; j++)
			{
				board[i][j] = Integer.valueOf(str.substring(j, j + 1));
			}
		}
		scan.close();
		if (solveBoard(board))
		{
			printBoard();
		}
		else
		{
			System.out.println("Unable to solve board");
		}
	}
	private static boolean isNumberInRow(int[][] board, int number, int row)
	{
		for (int i = 0; i < 9; i++)
		{
			if (board[row][i] == number)
			{
				return true;
			}
		}
		return false;
	}
	
	private static boolean isNumberInColumn(int[][] board, int number, int column)
	{
		for (int i = 0; i < 9; i++)
		{
			if (board[i][column] == number)
			{
				return true;
			}
		}
		return false;
	}
	
	private static boolean isNumberInBox(int[][] board, int number, int row, int column)
	{
		int localBoxRow = row - row % 3;
		int localBoxColumn = column - column % 3;
		for (int i = localBoxRow; i < localBoxRow + 3; i++)
		{
			for (int j = localBoxColumn; j < localBoxColumn + 3; j++)
			{
				if (board[i][j] == number)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean isValidPlacement(int[][] board, int number, int row, int column)
	{
		return !isNumberInRow(board, number, row) && 
				!isNumberInColumn(board, number, column) && 
				!isNumberInBox(board, number, row, column);
	}
	
	private static boolean solveBoard(int[][] board)
	{
		for (int row = 0; row < 9; row++)
		{
			for (int column = 0; column < 9; column++)
			{
				if (board[row][column] == 0)
				{
					for (int numberToTry = 1; numberToTry <= 9; numberToTry++)
					{
						if (isValidPlacement(board, numberToTry, row, column))
						{
							board[row][column] = numberToTry;
							if (solveBoard(board))
							{
								return true;
							}
							else
							{
								board[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	public static void printBoard()
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				System.out.print(board[i][j] + " ");
				if (j % 3 == 2 && j != 8)
				{
					System.out.print(" | ");
				}
				if (j == 8)
				{
					System.out.print("\n");
				}
			}
			if (i % 3 == 2)
			{
				if (i < 8)
				{
					System.out.println("------------------------");
				}
			}
		}
	}
}
