package gameProject;

import java.util.Scanner;

public class ElitePlayer extends Player {
	/* Class instance of the ElitePlayer
	 * This extends the abstract class Player
	 * There are no no-argument constructors as the code to get to these as been designed to only call with certain information.
	 */

	
	// Constructors
	
	public ElitePlayer(String name, Scanner input) {
		/* This constructor takes in a name and the scanner, passing both along to the superclass along with a flag for the elite status and starting points.
		 * It is intended for new players
		 */
		super(name, true, 1337, input);
	}
	
	public ElitePlayer(String name, boolean vip, int points, String password) {
		/* This constructor is used to create an instance using save file data. It takes a name, status, points, and a password, passing all along to the super class
		 * It is intended for returning players loading from a save file.
		 */
		super(name, vip, points, password);
	}
	
	// Override methods
	
	@Override
	public void setPoints(int points) {
		// If an elite player is loosing points this override method halves them
		if (points < 0) {
			points /= 2;
		}
		super.setPoints(points);
	}

}
