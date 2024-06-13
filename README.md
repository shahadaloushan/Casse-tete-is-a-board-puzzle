# Casse-tete-is-a-board-puzzle

This Java program initializes an n×n grid with tokens, removes m tokens randomly, and checks if each row and column has an even number of tokens using concurrent threads. The main thread initializes the grid based on user input, and the rowsThread and columnsThread validate rows and columns respectively, writing results to an output file. A shared feasible flag and semaphores are used for synchronization. The program prints whether the configuration is a feasible solution or not, logging detailed results in outFile2.txt.
