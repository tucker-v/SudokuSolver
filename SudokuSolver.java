/**
 * @author - Tucker Vann
 */
package sudokusolver;
import java.util.Scanner;
/**
 * Sudoku solver
 * Takes an input of a sudoku board and solves the board and prints the board.
 */
public class SudokuSolver 
{
	public static int board [][] = new int[9][9];
	/**
         * Runs main method, takes an input of the board we're solving and then calls upon solveBoard to solve the puzzle.
	 */
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
	/**
         * Checks if a number is in row
   	 * @param board
     	 * board with values
       	 * @param number
	 * number we're searching for
      	 * @param row
	 * row we're checking
  	 * @return true if number is in row, false otherwise
	 */
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
	/**
 	 * Checks if a number is in column
   	 * @param board
     	 * board with values
       	 * @param number
	 * number we're searching for
      	 * @param column
	 * column we're checking
  	 * @return true if number is in column, false otherwise
   	 */
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
	/**
 	 * Checks if a number is in 3 x 3 box
   	 * @param board
     	 * board with values
       	 * @param number
	 * number we're searching for
  	 * @param row
    	 * row we're using to identiy which box we are in
      	 * @param column
	 * column we're using to identify which box we are in
  	 * @return true if number is in box, false otherwise
   	*/
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

	/**
 	 * Checks the 3 helper method if the number is in row, column, or box
   	 * @param board
     	 * board with values
       	 * @param number
	 * number we're searching for
  	 * @param row
    	 * row we're checking
      	 * @param column
	 * column we're checking
  	 * @return true if all 3 methods return false, indicating our number isn't in the row, column, or box
   	 */
	private static boolean isValidPlacement(int[][] board, int number, int row, int column)
	{
		return !isNumberInRow(board, number, row) && 
				!isNumberInColumn(board, number, column) && 
				!isNumberInBox(board, number, row, column);
	}

	/** 
 	 * Solves board using the isValidPlacement() method, if it's true, then we try to insert a potential number.
   	 * We then use recursion to call solveBoard with the latest number now inserted. If eventually the board becomes 
     	 * impossible to solve, there then becomes backtracking and we set the previously set number to 0. Returns true if solved.
   	 */
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

	/**
	 * Displays board
	 */
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
