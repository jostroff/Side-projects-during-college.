package ticTacToe;

import java.util.Scanner;

public class RunTTT {
	public static void main(String[] args) {
		int playerNumber = 0; //1 for match against AI, 2 for match against human.
		int move[] = new int[9];
		Node space = new Node();
		space.turn = 0;
		System.out.println("Welcome to TicTacToe. Enter 1 to play against the AI, or enter 2"
				+ " to play against another human on the same computer.");
		Scanner sc = new Scanner(System.in);
		playerNumber = sc.nextInt();
		while (playerNumber != 1 && playerNumber != 2){
			System.out.println("The number you wrote was invalid. Please enter 1 to play"
					+ " against the AI, or 2 to play against another human.");
			playerNumber = sc.nextInt();
		}
		if (playerNumber == 1){
			System.out.println("Welcome to the AI game. Which, of X or O, would you like to "
					+ "play as? Enter X or O. Uppercase/lowercase does not matter.");
			String turnS = sc.next();
			if (turnS.equals("X") || turnS.equals("x"))
				space.turn = 1;
			else if (turnS.equals("O") || turnS.equals("o"))
				space.turn = -1;
			if (space.turn == 1){
				System.out.println("This is the grid of options that you have:");
				printBoard();
				System.out.println("Now enter a number from 1 to 9 on the board"
						+ " to put your piece there:");
				move[0] = sc.nextInt();
				retry(move, 0, sc);
				BoardFunctions.spaceChange(space, move[0] - 1, space.turn);
				printLocations(space, 1);
				for (int i = 1; i < move.length; i++){
					if (space.turn == 1)
						space.turn = -1;
					else if (space.turn == -1)
						space.turn = 1;
					//AI MAKES MOVE:
					move[i] = BoardFunctions.miniMax(space)[1] + 1;
					BoardFunctions.spaceChange(space, move[i] - 1, space.turn);
					i++;
					System.out.println("The board now looks as follows: ");
					printLocations(space, 1);
					int isTerm = BoardFunctions.isTerminal(space);
					if (isTerm != -2){
						if (endGame(isTerm, sc, 1) == 1)
							main(args);
						return;
					}
					if (space.turn == 1)
						space.turn = -1;
					else if (space.turn == -1)
						space.turn = 1;
					System.out.println("Now enter another number to place the next piece: ");
					move[i] = sc.nextInt();
					retry(move, i, sc);
					//Actually changing the piece.
					BoardFunctions.spaceChange(space, move[i] - 1, space.turn);
					printLocations(space, 1);
					isTerm = BoardFunctions.isTerminal(space);
					if (isTerm != -2){
						if (endGame(isTerm, sc, 1) == 1)
							main(args);
						return;
					}
				}
			} else if (space.turn == -1){
				System.out.println("This is the grid of options that you have:");
				printBoard();
				move[0] = BoardFunctions.miniMax(space)[1] + 1;
				BoardFunctions.spaceChange(space, move[0] - 1, space.turn);
				System.out.println("The board now looks as follows: ");
				printLocations(space, -1);
				space.turn = 1;
				System.out.println("Now enter a number from 1 to 9 on the board"
						+ " to put your piece there:");
				move[1] = sc.nextInt();
				retry(move, 1, sc);
				BoardFunctions.spaceChange(space, move[1] - 1, space.turn);
				printLocations(space, -1);
				for (int i = 2; i < move.length; i++){
					if (space.turn == 1)
						space.turn = -1;
					else if (space.turn == -1)
						space.turn = 1;
					//AI MAKES MOVE:
					move[i] = BoardFunctions.miniMax(space)[1] + 1;
					BoardFunctions.spaceChange(space, move[i] - 1, space.turn);
					i++;
					System.out.println("The board now looks as follows: ");
					printLocations(space, -1);
					int isTerm = BoardFunctions.isTerminal(space);
					if (isTerm != -2){
						if (endGame(isTerm, sc, -1) == 1)
							main(args);
						return;
					}
					if (space.turn == 1)
						space.turn = -1;
					else if (space.turn == -1)
						space.turn = 1;
					System.out.println("Now enter another number to place the next piece: ");
					move[i] = sc.nextInt();
					retry(move, i, sc);
					//Actually changing the piece.
					BoardFunctions.spaceChange(space, move[i] - 1, space.turn);
					printLocations(space, -1);
					isTerm = BoardFunctions.isTerminal(space);
					if (isTerm != -2){
						if (endGame(isTerm, sc, -1) == 1)
							main(args);
						return;
					}
				}
			}
			
			
		} else {
			System.out.println("Welcome to two player. Which, of X or O, would you like to have"
					+ " go first? Enter X or O. Uppercase/lowercase does not matter.");
			String turnS = sc.next();
			if (turnS.equals("X") || turnS.equals("x"))
				space.turn = 1;
			else if (turnS.equals("O") || turnS.equals("o"))
				space.turn = -1;
			System.out.println("This is the grid of options that you have:");
			printBoard();
			System.out.println("Now enter a number from 1 to 9 on the board to put your piece"
					+ " there:");
			move[0] = sc.nextInt();
			retry(move, 0, sc);
			BoardFunctions.spaceChange(space, move[0] - 1, space.turn);
			System.out.println("The board now looks as follows: ");
			printLocations(space, -1);
			for (int i = 1; i < move.length; i++){
				if (space.turn == 1)
					space.turn = -1;
				else if (space.turn == -1)
					space.turn = 1;
				System.out.println("Now enter another number to place the next piece: ");
				move[i] = sc.nextInt();
				retry(move, i, sc);
				//Actually changing the piece.
				BoardFunctions.spaceChange(space, move[i] - 1, space.turn);
				printLocations(space, -1);
				if (BoardFunctions.isTerminal(space) != -2) {
					System.out.println(BoardFunctions.isTerminal(space));
					return;
				}
			}
		}
	}
	public static int endGame(int isTerm, Scanner sc, int currentPlayer){
		if (isTerm == 1)
			System.out.println(convertNumber(1, currentPlayer) + " has won the game.");
		else if (isTerm == -1)
			System.out.println(convertNumber(-1, currentPlayer) + " has won the game.");
		else if (isTerm == 0)
			System.out.println("The game was a tie.");
		System.out.println("Would you like to play again? Enter 1 to play again or 0 to"
				+ " terminate: ");
		int ask = sc.nextInt();
		return ask;
	}
	/**
	 * If they enter a number already taken:
	 * @param move - list of moves already taken
	 * @param place - amount of moves taken
	 * @param sc - uses the scanner from the main() method
	 */
	public static void retry(int[] move, int place, Scanner sc){
		boolean taken = false;
		do {
			if (taken)
				taken = false;
			for (int k = 0; k < move.length; k++){
				if ((move[place] == move[k] && place != k) || move[place] < 1 || move[place] > 9){
					System.out.println("The number you have entered is already taken or is out of range."
							+ " Please enter a different number: ");
					taken = true;
					move[place] = sc.nextInt();
					break;
				}
			}
		} while (taken);
	}
	public static char convertNumber(int state, int getPlayer){
		if (state == 0)
			return ' ';
		if (getPlayer == 1){
			if (state == 1)
				return 'X';
			else if (state == -1)
				return 'O';
			return 0;
		} else {
			if (state == 1)
				return 'O';
			else if (state == -1)
				return 'X';
			return 0;
		}
	}
	public static void printBoard(){
		System.out.println("|-----------|\n| 1 | 2 | 3 |\n|-----------|\n| 4 | 5 | 6 |"
				+ "\n|-----------|\n| 7 | 8 | 9 |\n|-----------|");
	}
	public static void printLocations(Node space, int getPlayer){
		System.out.println("|-----------|\n| " + 
				convertNumber(BoardFunctions.getSpaceOnBoard(space, 0), getPlayer) +
				" | " + convertNumber(BoardFunctions.getSpaceOnBoard(space, 1), getPlayer) + " | " 
				+ convertNumber(BoardFunctions.getSpaceOnBoard(space, 2), getPlayer) + " |"
				+ "\n|-----------|\n| "
				+ convertNumber(BoardFunctions.getSpaceOnBoard(space, 3), getPlayer) + " | "
				+ convertNumber(BoardFunctions.getSpaceOnBoard(space, 4), getPlayer) + " | "
				+ convertNumber(BoardFunctions.getSpaceOnBoard(space, 5), getPlayer) + " |"
				+ "\n|-----------|\n| "
				+ convertNumber(BoardFunctions.getSpaceOnBoard(space, 6), getPlayer) + " | "
				+ convertNumber(BoardFunctions.getSpaceOnBoard(space, 7), getPlayer) + " | "
				+ convertNumber(BoardFunctions.getSpaceOnBoard(space, 8), getPlayer) + " |"
				+ "\n|-----------|");
	}
}