//Joshua Ostroff
package mastermind;
import java.util.*;
public class MasterMind {
	String[] tokencolors;
	int positions;
	ArrayList<ArrayList<String>> list;
	String[] guess;
	public MasterMind(String[] tokencolors, int positions) {
		this.tokencolors = tokencolors;
		this.positions = positions;
		newGame();
	}
	//This function adds all possible permutations of the given colors to the arrayList, list.
	public void createList(String[] tokencolors, int positions){
		int tk = tokencolors.length;
		for (int i = 0; i < positions; i++)
			for (int k = 0; k < Math.pow(tk, positions); k += Math.pow(tk, positions - i))
				for (int j = k; j < Math.pow(tk, positions - i) + k; j += Math.pow(tk, positions - i - 1))
					for (int l = k; l < Math.pow(tk, positions - i - 1) + k; l++)
						list.get(j + l - k).add(0, tokencolors[(j - k)/(int)(Math.pow(tk, positions - i - 1))]);
	}
	// cuts the list down based on black and white pegs
	public void response(int colorsRightPositionWrong, int positionsAndColorRight) {
		int black = positionsAndColorRight;
		int i = 0;
		// ** use "guess" **
		//This is how all of the guesses are arranged.
		while (i < list.size()){
			boolean checked = false;
			int corrects = 0;
			ArrayList<Integer> blackSpace = new ArrayList<Integer>();
			for (int k = 0; k < guess.length; k++){
				// Now loop through how many of these things have EXACTLY the right places 
				// and colors.
				if (list.get(i).get(k) == guess[k]){
					corrects++;
					blackSpace.add(k);
				}
			}
			if (corrects != black){
				checked = true;
			}
			boolean maybe = false;
			//maybe = findWhites(white, guess.length, i, blackSpace);
			if (maybe)
				checked = maybe;
			if (checked) {
				list.remove(i);
				i--;
			}
			i++;
		}
	}
	public void newGame() { // Resets the game, all positions are now possible.
		list = new ArrayList<ArrayList<String>>();
		String[] str = new String[positions];
		for (int i = 0; i < Math.pow(tokencolors.length, positions); i++){
			for (int j = 0; j < positions; j++)
				str[j] = Integer.toString(i);
			list.add(new ArrayList<String>());
		}
		createList(tokencolors, positions);
	}
	public String [] nextMove() { // return the next guess
		ArrayList<String> g = list.get(0); // retrieves the first element left in the array
		guess = new String[g.size()];
		for (int i = 0; i < g.size(); i++){
			guess[i] = g.get(i);
		}
		return guess;
	}
}