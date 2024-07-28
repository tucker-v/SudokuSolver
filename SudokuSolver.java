/**
 * @author - Tucker Vann
 */
package sudokusolver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import javax.swing.*;
import java.util.Scanner;

/**
 * Sudoku solver
 * Takes an input of a sudoku board and solves the board and prints the board.
 */
public class SudokuSolver extends JPanel implement MouseListener
{
	public static int board [][] = new int[9][9];
	public static int userBoard [][] = new int[9][9];
	public static boolean gameOver;
	public static int mistakes;
	public static Font font;
	public static int lastI;
	public static int lastJ;

	/**
	 * Adds MouseListener and initializes variables
	 */
	public SudokuSolver()
	{
		addMouseListener(this);
		mistakes = 0;
		lastI = 0;
		lastJ = 0;
	}
	
	/**
         * Runs main method, takes an input of the board we're solving and then calls upon solveBoard to solve the puzzle.
	 * Also takes an input to see if you want a solver or game
	 */
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int answer = 0;
		for (int i = 0; i < 9; i++)
		{
			while (answer != 1 && answer != 2)
			{
				System.out.println("Enter 1 for Solver. Enter 2 for game.");
				answer = scan.nextInt();
			}
			System.out.println("Enter row #" + (i + 1));
			String str = scan.next();
			for (int j = 0; j < 9; j++)
			{
				board[i][j] = Integer.valueOf(str.substring(j, j + 1));
				userBoard[i][j] = Integer.valueOf(str.substring(j, j + 1));
			}
		}
		scan.close();
		if (solveBoard(board))
		{
			if (answer == 1)
			{
				printBoard(board); 
			}
			else
			{
				createWindow();
			}
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
	/**
	 * Creates our JFrame window by adding the game to it to get the MouseListener to be able to interact with the window.
	 * Also sets the size of the window
	 */
	public static void createWindow()
	{
		JFrame window = new JFrame();
		window.getContentPane().add(new SudokuSolver());
		window.setBounds(10, 10, 1000, 700);
		window.setUndecorated(true);
		window.add(new SudokuSolver());
		window.setDefaultCloseOperation(3);
		window.setVisible(true);
		
	}
	
	@Override
	/**
	 * Paints our game
	 * First checks if the game is over to display the text signaling that you win or lose.
	 * Else we draw the game drawing a 9 x 9 grid with boxes and putting the users grid into each box
	 * We then draw a 3 x 3 grid with numbers that we can click on to add to our grid when we click on an empty box
	 * When we click on an empty box, the number will be changed to 10, signaling to paint it blue
	 * The number of mistakes will also be printed.
	 * Use of the font class is necessary to make the numbers big enough to fix in the boxes when using drawString().
	 */
	public void paint(Graphics g)
	{
		super.paint(g);
		Font text = new Font("Text", 0, 30);
		if (gameOver)
		{
			g.setColor(Color.black);
			g.setFont(text);
			if (mistakes == 3)
			{
				g.drawString("You lose.", 330, 320);
			}
			else
			{
				g.drawString("You win!", 330, 320);
			}
		}
		else
		{
			Font numbers = new Font("Sudoku", 0, 60);
			g.setFont(font);
			g.setColor(Color.black.darker());
			g.drawRect(20, 20, 600, 600);
		
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					g.setColor(Color.white.darker());
					g.drawRect(20 + (j * 200/3), 20 + (i * 200/3), 200/3, 200/3);
					g.setColor(Color.black);
					if (userBoard[i][j] % 10 != 0)
					{
						g.setFont(numbers);
						g.drawString("" + userBoard[i][j], 200/6 + j * 200/3, 200/6 + i * 200/3 + 40);
					}
					if (userBoard[i][j] == 10)
					{
						g.setColor(Color.blue.brighter());
						g.fillRect(20 + (j * 200/3), 20 + (i * 200/3), 200/3, 200/3);
					}
				}
			}
			g.setFont(text);
			g.setColor(Color.black.darker());
			g.drawString("Author: Tucker Vann", 620, 670);
			g.setColor(Color.black);
			g.drawString("Mistakes: " + mistakes + "/3" , 720, 70);
		
			for (int i = 0; i <= 400; i = i + 200)
			{
				for (int j = 0; j <= 400; j = j + 200)
				{
					g.setColor(Color.black.darker());
					g.drawRect(i + 20, j + 20, 200, 200);
				}
			}
		
			int count = 1;
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					g.setColor(Color.white.darker());
					g.drawRect(700 + j * 200/3, 300 + i * 200/3, 200/3, 200/3);
					g.setFont(numbers);
					g.setColor(Color.black);
					g.drawString("" + count, 710 + j * 200/3, 290 + 200/3 + i * 200/3);
					count++;
				}
			}
		}
	}

        /**
         * Removes highlight by setting numbers at 10 back to 0
	 */
	private void removeHighlight()
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if (userBoard[i][j] == 10)
				{
					userBoard[i][j] = 0;
				}
			}
		}
	}
	
	/**
	 * Checks if the number clicked on was correct
	 * If it doesn't match the correct answer and its highlighted we add a mistake
	 * Then we check if it is highlighted to change the user's board to the correct answer
	 * We use an else if in case the user tries to change the number after inputting the correct answer
	 * @param count
	 * number clicked
	 * @param i
	 * Where in the column we are checking
	 * @param j
	 * Where in the row we are checking
	 */
	public void checkAnswer(int count, int i, int j)
	{
		if (board[i][j] != count && userBoard[i][j] == 10)
		{
			mistakes++;
		}
		else if (userBoard[i][j] == 10)
		{
			userBoard[i][j] = count;
		}
		removeHighlight();
		int size = 0;
		for (int y = 0; y < 9; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				if (userBoard[y][x] != 0)
				{
					size++;
				}
				else
					return;
			}
		}
		if (size == 81 || mistakes == 3)
		{
			gameOver = true;
		}
		repaint();
	}
	
	@Override
	/**
	 * First gets where you clicked by the get method from MouseEvent
	 * Then locates where you clicked if it is any of the boxes painted.
	 * If it is on an empty box in the sudoku game, the box is highlighted blue by
	 * making the number a 10 instead of 0. Removes any other highlighted spaces before doing so.
	 * If you click on a number
	 * @param e
	 * The mouse being clicked
	 */
	public void mouseClicked(MouseEvent e) 
	{
		int x = e.getX();
		int y = e.getY();
		if (20 <= x && x <= 620 && 20 <= y && y <= 620)
		{
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					if ((20 + j * 200/3 < x) && (x < 20 + (j + 1) * 200/3)
							&& ((20 + i * 200/3 < y) && (y < 20 + (i + 1) * 200/3))
							&& (userBoard[i][j] == 0))
					{
						removeHighlight();
						userBoard[i][j] = 10;
						lastI = i;
						lastJ = j;
					}
				}
			}
		}
		int count = 1;
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if ((700 + j * 200/3 < x) && (x < 700 + (j + 1) * 200/3)
						&& (300 + i * 200/3 < y) && (y < 300 + (i + 1) * 200/3))
				{
					checkAnswer(count, lastI, lastJ);
				}
				count++;
			}
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
