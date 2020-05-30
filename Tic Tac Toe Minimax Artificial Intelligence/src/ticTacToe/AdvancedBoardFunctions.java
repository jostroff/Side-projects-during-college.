package ticTacToe;

import java.util.ArrayList;

/**
	|--------------------||--------------------||--------------------|
	| 1, 1 | 1, 2 | 1, 3 || 2, 1 | 2, 2 | 2, 3 || 3, 1 | 3, 2 | 3, 3 |
	|--------------------||--------------------||--------------------|
	| 1, 4 | 1, 5 | 1, 6 || 2, 4 | 2, 5 | 2, 6 || 3, 4 | 3, 5 | 3, 6 |
	|--------------------||--------------------||--------------------|
	| 1, 7 | 1, 8 | 1, 9 || 2, 7 | 2, 8 | 2, 9 || 3, 7 | 3, 8 | 3, 9 |
	|--------------------||--------------------||--------------------|
	|--------------------||--------------------||--------------------|
	| 4, 1 | 4, 2 | 4, 3 || 5, 1 | 5, 2 | 5, 3 || 6, 1 | 6, 2 | 6, 3 |
	|--------------------||--------------------||--------------------|
	| 4, 4 | 4, 5 | 4, 6 || 5, 4 | 5, 5 | 5, 6 || 6, 4 | 6, 5 | 6, 6 |
	|--------------------||--------------------||--------------------|
	| 4, 7 | 4, 8 | 4, 9 || 5, 7 | 5, 8 | 5, 9 || 6, 7 | 6, 8 | 6, 9 |
	|--------------------||--------------------||--------------------|
	|--------------------||--------------------||--------------------|
	| 7, 1 | 7, 2 | 7, 3 || 8, 1 | 8, 2 | 8, 3 || 9, 1 | 9, 2 | 9, 3 |
	|--------------------||--------------------||--------------------|
	| 7, 4 | 7, 5 | 7, 6 || 8, 4 | 8, 5 | 8, 6 || 9, 4 | 9, 5 | 9, 6 |
	|--------------------||--------------------||--------------------|
	| 7, 7 | 7, 8 | 7, 9 || 8, 7 | 8, 8 | 8, 9 || 9, 7 | 9, 8 | 9, 9 |
	|--------------------||--------------------||--------------------|
*/
public class AdvancedBoardFunctions{
	public static void spaceChange(AdvancedNode space, int i, int j, int newState){
		space.boards[i].position[j] = newState;
		int[] lastMove = {i, j};
		space.lastAdvancedMove = lastMove;
	}
	public static Node[] getEntireBoard(AdvancedNode space){
		return space.boards;
	}
	public static int getSpaceOnBoard(AdvancedNode space, int i, int j){
		return space.boards[i].position[j];
	}
	//NOTE: In order to save time, we only return the terminal value of the last node changed.
	public static int isTerminal(AdvancedNode space, int prevMove){
		return BoardFunctions.isTerminal(space.boards[prevMove]);
	}
	
	/**
	 * 
	 * @param space - entirety of board
	 * @param prevMove - board where the AI last went. Also called moveBoard.
	 * 
	 * //miles dan bruce alyssa minigolfing
	 */
	public static ArrayList<AdvancedNode> getChildren(AdvancedNode space, int prevMove){
		ArrayList<AdvancedNode> children = new ArrayList<AdvancedNode>();
		int testTerm = -1;
		if (prevMove != -1)
			testTerm = isTerminal(space, prevMove);
		if (testTerm == -1 || testTerm == 1)
			return children;
		else if (testTerm == 0 || prevMove == -1){
			for (int i = 0; i < space.boards.length; i++){
				for (int j = 0; j < space.boards[i].position.length; j++){
					if (space.boards[i].position[j] == 0){
						AdvancedNode child = new AdvancedNode();
						for (int k = 0; k < 9; k++) {
							child.boards[k] = space.boards[k].klone();
						}
						child.parentAdvancedNode = space;
						child.boards[i].position[j] = child.parentAdvancedNode.turn;
						int[] lastMove = {i, j};
						child.lastAdvancedMove = lastMove;
						children.add(child);
					}
				}
			}
		} else { //isTerminal == -2, i.e. it's not done. Most places go here.
			ArrayList<Node> thisPiggysChildren = BoardFunctions.getChildren(space.boards[prevMove]);
			for (int i = 0; i < thisPiggysChildren.size(); i++){
				AdvancedNode child = new AdvancedNode();
				for (int k = 0; k < 9; k++) {
					child.boards[k] = space.boards[k].klone();
				}
				child.parentAdvancedNode = space;
				child.boards[prevMove] = thisPiggysChildren.get(i);
				int[] lastMove = {prevMove, child.boards[prevMove].lastMove};
				child.lastAdvancedMove = lastMove;
//				System.out.println("Last move: " + lastMove[0] + ", " + lastMove[1]);
//				System.out.println("Terminal state: " + isTerminal(child, prevMove));
				children.add(child);
			}
		}
//		for (int i = 0; i < children.size(); i++){
//			System.out.println("NEXT");
//			RunAdvancedTTT.printLocations(children.get(i));
//			System.out.print(" HERE: " + i);
//		}
		return children;
	}
	/**
	 * 
	 * @param space
	 * @return 
	 */
	public static int[] hMiniMax(AdvancedNode space, int depth, int alpha, int beta){
		int current = -2;
		if (space.lastAdvancedMove[0] != -1)
			current = isTerminal(space, space.lastAdvancedMove[0]);
		int[] toReturn = new int[3];
		if (current != -2){
//			System.out.println("RIGHT HERE MOTHERFUCKER: " + current);
			toReturn[0] = current * 1000;
			toReturn[1] = space.lastAdvancedMove[0]; //REALLY IMPORTANT
			toReturn[2] = space.lastAdvancedMove[1]; //REALLY IMPORTANT
			return toReturn; //0 for draw, 1 for X win, -1 for O win in terminal state.
		}
		// All of this stuff should happen only if current == -2.
		ArrayList<AdvancedNode> children = getChildren(space, space.lastAdvancedMove[1]);
		
		/**
		 * Heuristic here goes to this place if it has two in a row.
		 */
		if (depth > 5){
//			int heuristic = 0;
			for (int i = 0; i < space.boards.length; i++){
				if (checkTwos(space.boards[i], space.turn)){
					toReturn[0] = -5 * space.turn;
					toReturn[1] = space.lastAdvancedMove[0];
					toReturn[2] = space.lastAdvancedMove[1];
				}
			}
			return toReturn;
		}
		
		
		
		if (space.turn == 1){ //Trying to maximize.
			int max = -1000000;
			int moveBoard = 9;
			int moveCoord = 9;
			int[] newMiniMax = new int[3];
			for (int i = 0; i < children.size(); i++){
				children.get(i).turn = -1;
				for (int k =0; k < 9; k++) {
					children.get(i).boards[k].turn = -1;
				}
				int[] testMiniMax = hMiniMax(children.get(i), depth + 1, alpha, beta);
				int termState = testMiniMax[0];
				if (termState > max){
					max = termState;
					newMiniMax = testMiniMax.clone();
					//finals[i] = children.get(i);
					moveBoard = children.get(i).lastAdvancedMove[0];
					moveCoord = children.get(i).lastAdvancedMove[1];
				}
				if (max >= beta) return newMiniMax;
				if (max > alpha) alpha = max;
			}
			newMiniMax[1] = moveBoard;
			newMiniMax[2] = moveCoord;
			return newMiniMax;
		}
		else { //Trying to minimize.
			int min = 1000000;
			int moveBoard = 9;
			int moveCoord = 9;
			int[] newMiniMax = new int[3];
			for (int i = 0; i < children.size(); i++){
				children.get(i).turn = 1;
				for (int k =0; k < 9; k++) {
					children.get(i).boards[k].turn = 1;
				}
				int[] testMiniMax = hMiniMax(children.get(i), depth + 1, alpha, beta);
				int termState = testMiniMax[0];
				if (termState < min && termState != -2){
					min = termState;
					newMiniMax = testMiniMax.clone();
					//finals[i] = children.get(i);
					moveBoard = children.get(i).lastAdvancedMove[0];
					moveCoord = children.get(i).lastAdvancedMove[1];
				}
				if (min <= alpha) return newMiniMax;
				if (min < beta) beta = min;
			}
			newMiniMax[1] = moveBoard;
			newMiniMax[2] = moveCoord;
			return newMiniMax;
		}
	}
	public static boolean checkTwos(Node space, int toCheck){
		for (int i = 0; i < 3; i++){
			//HORIZONTAL
			if (space.position[i] == toCheck && space.position[i + 2] == 0 &&
					space.position[i + 1] == toCheck)
				return true;
			if (space.position[i] == toCheck && space.position[i + 2] == toCheck &&
					space.position[i + 1] == 0)
				return true;
			if (space.position[i + 1] == toCheck && space.position[i + 2] == toCheck &&
					space.position[i] == 0)
				return true;
			//VERTICAL
			if (space.position[i] == toCheck && space.position[i + 3] == toCheck &&
					space.position[i + 6] == 0)
				return true;
			if (space.position[i] == toCheck && space.position[i + 6] == toCheck &&
					space.position[i + 3] == 0)
				return true;
			if (space.position[i + 3] == toCheck && space.position[i + 6] == toCheck &&
					space.position[i] == 0)
				return true;
		}
		//DIAGONAL TOP TO BOTTOM
		if (space.position[0] == toCheck && space.position[4] == toCheck &&
				space.position[8] == 0)
			return true;
		if (space.position[0] == toCheck && space.position[8] == toCheck &&
				space.position[4] == 0)
			return true;
		if (space.position[4] == toCheck && space.position[8] == toCheck &&
				space.position[0] == 0)
			return true;
		//DIAGONAL BOTTOM TO TOP
		if (space.position[6] == toCheck && space.position[4] == toCheck &&
				space.position[2] == 0)
			return true;
		if (space.position[6] == toCheck && space.position[2] == toCheck &&
				space.position[4] == 0)
			return true;
		if (space.position[2] == toCheck && space.position[4] == toCheck &&
				space.position[6] == 0)
			return true;
		
		return false;
	}
}