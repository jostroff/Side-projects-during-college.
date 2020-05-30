package ticTacToe;

import java.util.Arrays;

public class Node {
	//States:
	int[] position; //Spaces of the board. 0 for blank, 1 for X, 2 for O.
	int turn; //The turn of the NEXT person's move.
	Node parentNode;
	int lastMove;
	public Node(){
		position = new int[9];
		for (int i = 0; i < position.length; i++)
			position[i] = 0; //Blank Space
	}
	public Node klone(){
		Node josh = new Node();
		//josh.position = this.position.clone();
		josh.position=Arrays.copyOf(position, position.length);
		josh.turn = this.turn;
		josh.parentNode = this.parentNode;
		josh.lastMove = this.lastMove;
		return josh;
	}
}