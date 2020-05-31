//Joshua Ostroff
package mastermind;
import java.util.*;
public class MasterMindRun {
	public static void main(String[] args) {
		boolean newGame = true;
		while (newGame) {
			Scanner sc = new Scanner(System.in);
			int black = 0;
			
			System.out.println("Enter the number of colors you wish to work with: ");
			int colorNumb = sc.nextInt();
			String[] colors = new String[colorNumb];
			System.out.println("Please enter the names of the " + colorNumb + " colors, leaving "
					+ "a space between each color:");
			for (int i = 0; i < colorNumb; i++)
				colors[i] = sc.next();
			System.out.println("Please enter the number of positions you wish to use.");
			int positions = sc.nextInt();
			MasterMind masterMindGame = new MasterMind(colors, positions);
			
			System.out.println("Here is my guess: ");
			String[] guess = masterMindGame.nextMove();
			for (String s: guess)
				System.out.print(s + " ");
			
			while (black != positions) {
				System.out.println("\nPlease enter the number of pieces that had both colors and "
						+ "positions that were right (black pegs): ");
				black = sc.nextInt();
				
				if (black == positions){
					System.out.println("I win.\nWould you like to play again? Answer 'y' or 'n'.");
					String answer = sc.next();
					if (answer.equals("y")){
						masterMindGame.newGame();
					} else {
						newGame = false;
						return;
					}
				}
				if (black != positions) {
					System.out.println("Please enter the number of positions where the colors were "
							+ "right, but where the position was wrong (white pegs): ");
					int white = sc.nextInt();
					
					masterMindGame.response(white, black);
					
					System.out.println("Here is my guess: ");
					guess = masterMindGame.nextMove();
					for (String s: guess)
						System.out.print(s + " ");
				}
			}
		}
	}
}