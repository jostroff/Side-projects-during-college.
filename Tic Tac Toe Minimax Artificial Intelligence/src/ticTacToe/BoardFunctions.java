package ticTacToe;
import java.util.ArrayList;
public class BoardFunctions {
	public static void spaceChange(Node space, int j, int newState){
		space.position[j] = newState;
	}
	public static int[] getEntireBoard(Node space){
		return space.position;
	}
	public static int getSpaceOnBoard(Node space, int j){
		return space.position[j];
	}
	/**
	 * isTerminal checks if the game is done.
	 * @param space
	 * @return -1 if O wins, 1 if X wins, 0 if a draw, and -2 if the game is not over.
	 */
	public static int isTerminal(Node space){
		//Across:
		if ((space.position[0] == 1 && space.position[1] == 1 && space.position[2] == 1) ||
				(space.position[3] == 1 && space.position[4] == 1 && space.position[5] == 1) ||
				(space.position[6] == 1 && space.position[7] == 1 && space.position[8] == 1))
			return 1;
		else if ((space.position[0] == -1 && space.position[1] == -1 && space.position[2] == -1)
				|| (space.position[3] == -1 && space.position[4] == -1 && space.position[5] == -1)
				|| (space.position[6] == -1 && space.position[7] == -1 && space.position[8] == -1))
			return -1;
		//Vertical:
		if ((space.position[0] == 1 && space.position[3] == 1 && space.position[6] == 1) ||
				(space.position[1] == 1 && space.position[4] == 1 && space.position[7] == 1) ||
				(space.position[2] == 1 && space.position[5] == 1 && space.position[8] == 1))
			return 1;
		else if ((space.position[0] == -1 && space.position[3] == -1 && space.position[6] == -1)
				|| (space.position[1] == -1 && space.position[4] == -1 && space.position[7] == -1)
				|| (space.position[2] == -1 && space.position[5] == -1 && space.position[8] == -1))
			return -1;
		//Diagonal left to right:
		if (space.position[0] == 1 && space.position[4] == 1 && space.position[8] == 1)
			return 1;
		else if (space.position[0] == -1 && space.position[4] == -1 && space.position[8] == -1)
			return -1;
		//Diagonal right to left:
		if (space.position[2] == 1 && space.position[4] == 1 && space.position[6] == 1)
			return 1;
		else if (space.position[2] == -1 && space.position[4] == -1 && space.position[6] == -1)
			return -1;
		//All full but a draw:
		int counter = 0;
		for (int i = 0; i < space.position.length; i++)
			if (space.position[i] == 0)
				counter++;
		if (counter == 0)
			return 0; //Here, 0 means it's a draw.
		return -2; //Here, -2 means that the game is not over.
	}
	/**
	 * 
	 * @param space - board space to work with. Gives the array of children here.
	 * @return children of current board positions.
	 */
	public static ArrayList<Node> getChildren(Node space){
		ArrayList<Node> children = new ArrayList<Node>();
		if (isTerminal(space) != -2)
			return children;
		else {
			for (int i = 0; i < space.position.length; i++){
				if (space.position[i] == 0) {
					Node child = new Node();
					child.position = space.position.clone(); //Makes a clone that isn't a reference.
					child.parentNode = space;
//					System.out.println("TURN IS HERE: " + child.parentNode.turn);
					child.position[i] = child.parentNode.turn;
					child.lastMove = i;
					children.add(child);
//					RunTTT.printLocations(child); //JUST COMMENTED OUT!!!!!11111
				}
			}
		}
		return children;
	}
	/**
	 * miniMax - the most magical recursion to have ever existed.
	 * @param space
	 * @return the integer move where the AI should go as well as either the min or max of the
	 * 		terminal state.
	 */
	// 
	public static int[] miniMax(Node space){
		int current = isTerminal(space);
		int[] toReturn = new int[2];
		if (current != -2){
			toReturn[0] = current;
			toReturn[1] = space.lastMove; //REALLY IMPORTANT
			return toReturn; //0 for draw, 1 for X win, -1 for O win in terminal state.
		}
		// All of this stuff should happen only if current == -2.
		ArrayList<Node> children = getChildren(space);
//		System.out.println("CHILDREN GENERATED:"); //JUST COMMENTED OUT!!!!!!1111
		
		if (space.turn == 1){ //Trying to maximize.
			int max = -2;
			int move = 9;
			int[] newMiniMax = new int[2];
			for (int i = 0; i < children.size(); i++){
				children.get(i).turn = -1;
				int[] testMiniMax = miniMax(children.get(i));
				int termState = testMiniMax[0];
				if (termState > max){
					max = termState;
					newMiniMax = testMiniMax.clone();
					//finals[i] = children.get(i);
					move = children.get(i).lastMove;
				}
			}
			newMiniMax[1] = move;
			return newMiniMax;
		}
		else { //Trying to minimize.
			int min = 2;
			int move = 9;
			int[] newMiniMax = new int[2];
			for (int i = 0; i < children.size(); i++){
				children.get(i).turn = 1;
				int[] testMiniMax = miniMax(children.get(i));
				int termState = testMiniMax[0];
				if (termState < min){
					min = termState;
					newMiniMax = testMiniMax.clone();
					//finals[i] = children.get(i);
					move = children.get(i).lastMove;
				}
			}
			newMiniMax[1] = move;
			return newMiniMax;
		}
	}
}