
//package osProject;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class OSProjectFinal {

	static boolean feasible=true;
	static Semaphore rowsSemaphore= new Semaphore(0);
	static Semaphore columnsSemaphore = new Semaphore(0);
	static int[][] puzzle;

	public static void main(String[] args){
		Scanner read= new Scanner(System.in);
		boolean invalid= false;
		int n, m;
		do {
			//The user is asked to input values for n and m
			//within specific ranges
			System.out.println("Enter a number between 2 - 7: ");
			n = read.nextInt();
			System.out.println("Enter another number that's smaller than "+n*n+": ");
			m = read.nextInt(); 
			if(n<3 || n>6 || m>=n*n) {
				System.out.println("Numbers are out of range, please try again: ");
				invalid= true ; 
			}
			else
				invalid=false;
			//If the user's input does not meet these criteria, a message is displayed, and invalid is set to true. 
			//Otherwise, invalid is set to false.
			//The loop continues until the user provides valid input (i.e., invalid is false
		} while (invalid); 
		//create a dimensional array called puzzle with dimensions n x n. This array represents the puzzle grid, and all its cells are initialized to 1.
		puzzle = new int[n][n];
		for (int i =0 ; i<n ; i++){ 
			for (int j = 0 ; j<n ; j++)
				puzzle[i][j] = 1 ; 
		}
		System.out.println("The full puzzle is:");//drawing the initial puzzle 
		for (int i =0 ; i<n; i++){
			for (int j = 0; j<n ; j++){
				System.out.print ( puzzle[i][j]+" "); 
			}
			System.out.println();
		}
		Random randomNum=new Random() ;//initialize a random object named random to generate random numbers.
		for (int i =0 ; i < m ; i++){//Randomly select different cells and set their values to zero
			int row, column;
			do {
				row= randomNum.nextInt(n);
				column= randomNum.nextInt(n);
			} while (puzzle[row][column]==0);//makes sure the position is not already chosen
			puzzle[row][column]=0;
		}
		System.out.println("\nAfter random selection:");//drawing the puzzle 
		for(int i=0 ; i<n; i++){
			for(int j=0; j<n ; j++){
				System.out.print(puzzle[i][j]+" "); 
			}
			System.out.println();
		}
		read.close();
		//PHASE 2
		//create threads for rows and columns
		Thread rowsThread=new Thread(()->checkRows(puzzle));
		Thread columnsThread=new Thread(()->checkColumns(puzzle));
		//start threads
		rowsThread.start();
		columnsThread.start();
		//Wait for threads to join
		try{
			rowsThread.join();
			columnsThread.join();
		} catch(InterruptedException e){
			e.printStackTrace();//handle exceptions
		}
		displayEvaluationResult();
	}//End of main 

	static void checkRows(int[][] puzzle){
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("outFile2.txt"))){
			for (int i = 0; i < puzzle.length; i++){
				//check if feasible is still true
				if(!feasible){
					return;
				}
				//check if the number of tokens in the row is odd
				if (countTokens(puzzle[i])%2!=0){
					//if odd, set feasible to false and return
					feasible = false;
					writer.write("Row "+ i+ ": No Feasible Solution\n");
					return;
				}
				writer.write("Row " + i + ": Feasible Solution\n ");
			}
			rowsSemaphore.release();
		}catch (IOException e) {
			e.printStackTrace(); //handles exceptions
		}
	}

	static void checkColumns(int[][] puzzle){
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("outFile2.txt", true))) {
			for (int j = 0;j<puzzle[0].length;j++){
				if (!feasible) {
					return;
				}
				if(countTokens(getColumn(puzzle, j))%2!= 0){
					feasible=false;
					writer.write("Column "+ j +": No Feasible Solution\n");
					return;
				}
				writer.write("Column "+ j +": Feasible Solution\n ");
			}
			columnsSemaphore.release();
		}catch (IOException e) {
			e.printStackTrace(); //handles exceptions
		}
	}

	static int countTokens(int[] line){
		int total=0;
		for (int value:line) {
			total += value;
		}
		return total;
	}
	static int[] getColumn(int[][] puzzle, int columnIndex){
		int[] column = new int[puzzle.length];
		for (int i = 0;i<puzzle.length; i++){
			column[i]=puzzle[i][columnIndex];
		}
		return column;
	}
	static void displayEvaluationResult(){
		try (BufferedWriter writer= new BufferedWriter(new FileWriter("outFile2.txt", true))){
			if (feasible) {
				System.out.println("RESULT: Feasible Solution");
				writer.write("RESULT: Feasible Solution");
			} else {
				System.out.println("RESULT: No Feasible Solution");
				writer.write("RESULT: No Feasible Solution");
			}
		}catch (IOException e){
			e.printStackTrace();//handles exceptions
		}

	}
}