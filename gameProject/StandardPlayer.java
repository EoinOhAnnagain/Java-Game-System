package gameProject;

import java.util.Scanner;

public class StandardPlayer extends Player {
	/* Class instance of the StandardPlayer
	 * This extends the abstract class Player
	 * There are no no-argument constructors as the code to get to these as been designed to only call with certain information.
	 */

	
	// Constructors
	
	public StandardPlayer(String name, Scanner input) {
		/* This constructor takes in a name and the scanner and passes them onto the super class along with a flag for the standard status and starting points.
		 * It is intended for new players
		 */
		super(name, false, 100, input);
	}
	
	public StandardPlayer(String name, boolean vip, int points, String password) {
		/* This constructor is used to create an instance using save file data. It takes a name, status, points, and a password, passing all along to the super class
		 * It is intended for returning players loading from a save file.
		 */
		super(name, vip, points, password);
	}
	
	
	// Override methods
	
	@Override
	public void changePassword(Scanner input) {
		/* This method overrides the changePassword method.
		 * As this is reserved for only elite players it prints a massage to inform the player as such
		 * The players password is not changed.
		 */
		System.out.println("Only elite players can change their password.");
	}
}
