# Casse-tete-is-a-board-puzzle

This Java program initializes an n×n grid with tokens, removes m tokens randomly, and checks if each row and column has an even number of tokens using concurrent threads. The main thread initializes the grid based on user input, and the rowsThread and columnsThread validate rows and columns respectively, writing results to an output file. A shared feasible flag and semaphores are used for synchronization. The program prints whether the configuration is a feasible solution or not, logging detailed results in outFile2.txt.

## usage
1) Run the Program
2) Input Values:
The program will prompt you to enter values for the grid size 
n and the number of tokens to remove m.
Enter a value for n between 3 and 6.
Enter a value for m that is less than n^2
3) View Initial and Modified Grid: The program will display the initial n×n grid with all tokens.
It will then display the grid after removing m tokens at random positions.
4) Check Feasibility:The program will use multi-threading to check if the grid configuration is feasible.
It will output the result, indicating whether the solution is feasible or not.
If not feasible, it will specify the row or column that violates the even token condition.
5) The program writes the results to an output file named outFile2.txt in the same directory.



