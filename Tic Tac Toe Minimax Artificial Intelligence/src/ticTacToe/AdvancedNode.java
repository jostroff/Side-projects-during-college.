package ticTacToe;

public class AdvancedNode{
	//States:
	Node[] boards; //Spaces of the board. 0 for blank, 1 for X, 2 for O.
	int turn; //The turn of the NEXT person's move.
	AdvancedNode parentAdvancedNode;
	int[] lastAdvancedMove;
	public AdvancedNode(){
		lastAdvancedMove = new int[2];
		lastAdvancedMove[0] = -1;
		lastAdvancedMove[1] = -1;
		boards = new Node[9];
		for (int i = 0; i < boards.length; i++)
			boards[i] = new Node();
	}
	public void setTurn(int changeTo){
		turn = changeTo;
		for (int i = 0; i < boards.length; i++)
			boards[i].turn = changeTo;
	}
}