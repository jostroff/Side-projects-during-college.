package ticTacToe;

import java.util.Scanner;

public class RunAdvancedTTT {
	private static int playerNumber; //1 for match against AI, 2 for match against human.
	public static void main(String[] args) {
		playerNumber = 0;
		int[] moveBoard = new int[81];
		int[] moveCoord = new int[81];
		AdvancedNode space = new AdvancedNode();
		space.turn = 0;
		System.out.println("Welcome to Advanced TicTacToe. Enter 1 to play against the AI, or "
				+ "enter 2 to play against another human on the same computer.");
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
				printAdvancedBoard();
				System.out.println("Now enter two numbers from 1 to 9 the board to put your piece"
						+ " that goes on that coordinate there (ex of input: 1 5):");
				moveBoard[0] = sc.nextInt();
				moveCoord[0] = sc.nextInt();
				retry(space, moveBoard, moveCoord, 0, sc);
				AdvancedBoardFunctions.spaceChange(space, moveBoard[0] - 1, moveCoord[0] - 1, space.turn);
				System.out.println("The board now looks as follows: ");
				printLocations(space, 1);
				for (int i = 1; i < moveCoord.length; i++){
					space.setTurn(-1*space.turn);
					//AI MAKES MOVE:
					int[] value = AdvancedBoardFunctions.hMiniMax(space, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
//					System.out.println(value[0]);
					moveBoard[i] = value[1] + 1;
					moveCoord[i] = value[2] + 1;
					AdvancedBoardFunctions.spaceChange(space, moveBoard[i] - 1, moveCoord[i] - 1, space.turn);
					i++;
					System.out.println("The board now looks as follows: ");
					printLocations(space, 1);
					System.out.println("Moved: " + moveBoard[i - 1] + ", " + moveCoord[i - 1]);
					int isTerm = AdvancedBoardFunctions.isTerminal(space, moveBoard[i - 1] - 1);
					if (isTerm != -2){
						if (RunTTT.endGame(isTerm, sc, 1) == 1)
							main(args);
						return;
					}
					space.setTurn(-1*space.turn);
					System.out.println("Now enter another number to place the next piece: ");
					moveBoard[i] = sc.nextInt();
					moveCoord[i] = sc.nextInt();
					retry(space, moveBoard, moveCoord, i, sc);
					//Actually changing the piece.
					AdvancedBoardFunctions.spaceChange(space, moveBoard[i] - 1, moveCoord[i] - 1, space.turn);
					printLocations(space, 1);
					isTerm = AdvancedBoardFunctions.isTerminal(space, moveBoard[i] - 1);
					if (isTerm != -2){
						if (RunTTT.endGame(isTerm, sc, 1) == 1)
							main(args);
						return;
					}
				}
			} else if (space.turn == -1){
				System.out.println("This is the grid of options that you have:");
				printAdvancedBoard();
				int[] value = AdvancedBoardFunctions.hMiniMax(space, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
				moveBoard[0] = value[1];
				moveCoord[0] = value[2];
				AdvancedBoardFunctions.spaceChange(space, moveBoard[0] - 1, moveCoord[0] - 1, space.turn);
				System.out.println("The board now looks as follows: ");
				printLocations(space, -1);
				System.out.println("Moved: " + moveBoard[0] + ", " + moveCoord[0]);
				System.out.println("Now enter two numbers from 1 to 9 the board to put your piece"
						+ " that goes on that coordinate there (ex of input: 1 5):");
				space.setTurn(-1*space.turn);
				moveBoard[1] = sc.nextInt();
				moveCoord[1] = sc.nextInt();
				retry(space, moveBoard, moveCoord, 1, sc);
				AdvancedBoardFunctions.spaceChange(space, moveBoard[1] - 1, moveCoord[1] - 1, space.turn);
				System.out.println("The board now looks as follows: ");
				printLocations(space, -1);
				for (int i = 2; i < moveCoord.length; i++){
					space.setTurn(-1*space.turn);
					//AI MAKES MOVE:
					value = AdvancedBoardFunctions.hMiniMax(space, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
					moveBoard[i] = value[1] + 1;
					moveCoord[i] = value[2] + 1;
					AdvancedBoardFunctions.spaceChange(space, moveBoard[i] - 1, moveCoord[i] - 1, space.turn);
					i++;
					System.out.println("The board now looks as follows: ");
					printLocations(space, -1);
					System.out.println("Moved: " + moveBoard[i - 1] + ", " + moveCoord[i - 1]);
					int isTerm = AdvancedBoardFunctions.isTerminal(space, moveBoard[i - 1] - 1);
					if (isTerm != -2){
						if (RunTTT.endGame(isTerm, sc, -1) == 1)
							main(args);
						return;
					}
					space.setTurn(-1*space.turn);
					System.out.println("Now enter another number to place the next piece: ");
					moveBoard[i] = sc.nextInt();
					moveCoord[i] = sc.nextInt();
					retry(space, moveBoard, moveCoord, i, sc);
					//Actually changing the piece.
					AdvancedBoardFunctions.spaceChange(space, moveBoard[i] - 1, moveCoord[i] - 1, space.turn);
					printLocations(space, -1);
					isTerm = AdvancedBoardFunctions.isTerminal(space, moveBoard[i] - 1);
					System.out.println(isTerm);
					if (isTerm != -2){
						if (RunTTT.endGame(isTerm, sc, -1) == 1)
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
			printAdvancedBoard();
			System.out.println("Now enter two numbers from 1 to 9 the board to put your piece"
					+ " that goes on that coordinate there (ex of input: 1 5):");
			moveBoard[0] = sc.nextInt();
			moveCoord[0] = sc.nextInt();
			retry(space, moveBoard, moveCoord, 0, sc);
			AdvancedBoardFunctions.spaceChange(space, moveBoard[0] - 1, moveCoord[0] - 1, space.turn);
			System.out.println("The board now looks as follows: ");
			printLocations(space, -1);
			for (int i = 1; i < moveCoord.length; i++){
				if (space.turn == 1)
					space.turn = -1;
				else if (space.turn == -1)
					space.turn = 1;
				System.out.println("Now enter the next 2 numbers to place the next piece: ");
				moveBoard[i] = sc.nextInt();
				moveCoord[i] = sc.nextInt();
				retry(space, moveBoard, moveCoord, i, sc);
				//Actually changing the piece.
				AdvancedBoardFunctions.spaceChange(space, moveBoard[i] - 1, moveCoord[i] - 1, space.turn);
				printLocations(space, -1);
				int isTerm = AdvancedBoardFunctions.isTerminal(space, moveBoard[i] - 1);
				if (isTerm != -2 && isTerm != 0) {
					System.out.println(isTerm);
					return;
				}
			}
		}
		System.out.println("Game is a tie.");
	}
	public static void printAdvancedBoard(){
		System.out.println("|-----------------||-----------------||-----------------|"
				+ "\n| 1 1 | 1 2 | 1 3 || 2 1 | 2 2 | 2 3 || 3 1 | 3 2 | 3 3 |"
				+ "\n|-----------------||-----------------||-----------------|"
				+ "\n| 1 4 | 1 5 | 1 6 || 2 4 | 2 5 | 2 6 || 3 4 | 3 5 | 3 6 |"
				+ "\n|-----------------||-----------------||-----------------|"
				+ "\n| 1 7 | 1 8 | 1 9 || 2 7 | 2 8 | 2 9 || 3 7 | 3 8 | 3 9 |"
				+ "\n|-----------------||-----------------||-----------------|"
				+ "\n|-----------------||-----------------||-----------------|"
				+ "\n| 4 1 | 4 2 | 4 3 || 5 1 | 5 2 | 5 3 || 6 1 | 6 2 | 6 3 |"
				+ "\n|-----------------||-----------------||-----------------|"
				+ "\n| 4 4 | 4 5 | 4 6 || 5 4 | 5 5 | 5 6 || 6 4 | 6 5 | 6 6 |"
				+ "\n|-----------------||-----------------||-----------------|"
				+ "\n| 4 7 | 4 8 | 4 9 || 5 7 | 5 8 | 5 9 || 6 7 | 6 8 | 6 9 |"
				+ "\n|-----------------||-----------------||-----------------|"
				+ "\n|-----------------||-----------------||-----------------|"
				+ "\n| 7 1 | 7 2 | 7 3 || 8 1 | 8 2 | 8 3 || 9 1 | 9 2 | 9 3 |"
				+ "\n|-----------------||-----------------||-----------------|"
				+ "\n| 7 4 | 7 5 | 7 6 || 8 4 | 8 5 | 8 6 || 9 4 | 9 5 | 9 6 |"
				+ "\n|-----------------||-----------------||-----------------|"
				+ "\n| 7 7 | 7 8 | 7 9 || 8 7 | 8 8 | 8 9 || 9 7 | 9 8 | 9 9 |"
				+ "\n|-----------------||-----------------||-----------------|");
	}
	public static void retry(AdvancedNode space, int[] moveBoard, int[] moveCoord, int place, Scanner sc){
		boolean taken = false;
		do {
			if (taken)
				taken = false;
			for (int k = 0; k < moveCoord.length; k++){
				if ((moveCoord[place] == moveCoord[k] && place != k &&
						moveBoard[place] == moveBoard[k]) || 
						(place != 0 && moveBoard[place] != moveCoord[place - 1])
						|| moveCoord[place] < 1 || moveCoord[place] > 9
						|| moveBoard[place] < 1 || moveBoard[place] > 9){
					System.out.println("The number you have entered is already taken,"
							+ " or it is out of range, or it doesn't match with where it's supposed to go."
							+ "\nPlease enter a different set of numbers: ");
					taken = true;
					moveBoard[place] = sc.nextInt();
					moveCoord[place] = sc.nextInt();
					break;
				}
			}
		} while (taken);
		space.lastAdvancedMove[0] = moveBoard[place];
		space.lastAdvancedMove[1] = moveCoord[place];
	}
	public static void printLocations(AdvancedNode square, int getPlayer){
		System.out.print("|-----------||-----------||-----------|\n");
		printParams(square, 0, 3, 0, 3, getPlayer);
		System.out.println("|-----------||-----------||-----------|");
		printParams(square, 0, 3, 3, 6, getPlayer);
		System.out.println("|-----------||-----------||-----------|");
		printParams(square, 0, 3, 6, 9, getPlayer);
		System.out.println("|-----------||-----------||-----------|");
		System.out.println("|-----------||-----------||-----------|");
		printParams(square, 3, 6, 0, 3, getPlayer);
		System.out.println("|-----------||-----------||-----------|");
		printParams(square, 3, 6, 3, 6, getPlayer);
		System.out.println("|-----------||-----------||-----------|");
		printParams(square, 3, 6, 6, 9, getPlayer);
		System.out.println("|-----------||-----------||-----------|");
		System.out.println("|-----------||-----------||-----------|");
		printParams(square, 6, 9, 0, 3, getPlayer);
		System.out.println("|-----------||-----------||-----------|");
		printParams(square, 6, 9, 3, 6, getPlayer);
		System.out.println("|-----------||-----------||-----------|");
		printParams(square, 6, 9, 6, 9, getPlayer);
		System.out.println("|-----------||-----------||-----------|");
	}
	public static void printParams(AdvancedNode square, int startI, int finishI, int startJ, int finishJ, int getPlayer){
		for (int i = startI; i < finishI; i++){
			System.out.print("|");
			for (int k = startJ; k < finishJ; k++){
				System.out.print(" " + RunTTT.convertNumber(square.boards[i].position[k], getPlayer) + " |");
			}
		}
		System.out.println();
	}
}
