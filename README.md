# SudokuSolver

This code will solve a traditional 9 x 9 sudoku grid. To run this code, simply input each row with no spaces.
If the slot is empty, input a 0. The program will then use recursion and backtracking to test the values to see if they make a valid grid. When done, the program will print out a display of the completed grid. If it is impossible, a message will be printed signalling so.

I followed a tutorial for this, but I always struggled with the idea of recursion and backtracking, so this defintely helped me understand the idea of why we need recursion in certain instances and an efficient way of backtracking. I did add a way for users to input a grid and did my printing method on my own.

UPDATE: I have now added a functioning sudoku game with JFrame. You still have to input the grid but if you want the game, there will be a display of the grid in JFrame. From there, if you click on an empty square you can select a different empty square of select a number to input. If it is correct the number will go in, or a mistake will be added. If you reach 3 mistakes or fill out the grid, there will be a message printed signaling that you won or lost.


TO DO LIST:
I want to add a file with 9 x 9 arrays that have numbers for sudoku that can be randomly picked. From there I would
want to add a way for the difficulty to be selected.
I also would want a way to get the keyboard implemented rather than having to use the mouse for everything.

Sources: https://www.youtube.com/watch?v=mcXc8Mva2bA&t=1017s
