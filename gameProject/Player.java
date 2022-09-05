package gameProject;

import java.util.Scanner;
import java.io.File;

public abstract class Player {
	/* Abstract player class
	 * extended by ElitePlayer and StandardPlayer
	 */
	
	// Instance variables
	private String name;
	private boolean vip;
	private int points;
	private double mod = 1;
	private String password;
	// Create leader board
	private Leaderboard L = new Leaderboard();
	
	
	// Constructors
	
	protected Player(String name, boolean vip, int points, Scanner input) {
		/* Constructor for new players. 
		 * Password needs to be set and player added to leader board
		 */
		this.name = name;
		this.vip = vip;
		this.points = points;
		this.setPassword(input);
		this.save();
		L.newPlayer(name, points);
	}
	
	protected Player(String name, boolean vip, int points, String password) {
		/* Constructor for returning players.
		 * Password is already set so it is just added and player is already on the leader board
		 */
		this.name = name;
		this.vip = vip;
		this.points = points;
		this.password = password;
		this.save();
	}
	
	
	// Getters
	
	public String getName() {
		// Get player name
		return name;
	}
	
	public String getStatus() {
		// Get player status
		if (vip) {
			return "Elite";
		} else {
			return "Standard";
		}
	}
	
	public int getPoints() {
		// Get current points
		return points;
	}
	
	public double getMod() {
		// Get current modifer
		return mod;
	}
	
	
	// Setters
	
	public void setPassword(Scanner input) {
		/* Method to set password
		 * Player will need to enter it twice correctly
		 * Takes scanner as input
		 */
		
		// Loop for while the password is not good
		boolean good = false;
		while (!good) {
			// Player enters password twice
			System.out.println("Please enter a password:");
			String password = input.nextLine();
			System.out.println("Please re-enter your password.");
			String password2 = input.nextLine();
			
			// Check the passwords are the same. If they are end loop and set password. If not repeat loop
			if (password.equals(password2)) {
				this.password = password;
				good = true;
			} else {
				System.out.println("Passwords don't match. Please try again.");
			} 
			
		}
	}
	
	public void changePassword(Scanner input) {
		/* Method to change a players password.
		 * Takes scanner as input.
		 * Player will need to enter their old password and the new one twice correctly.
		 */
		
		// Loop for while the password is not good
		boolean good = false;
		while (!good) {
			// Enter old password
			System.out.println("Please enter your old password:");
			String oldPassword = input.nextLine();
			// If incorrect return to main menu
			if (!oldPassword.equals(this.password)) {
				System.out.println("Incorrect Password.");
				return;
				}
			// Player enters new password twice
			System.out.println("Please enter a new password:");
			String password = input.nextLine();
			System.out.println("Please re-enter your new password.");
			String password2 = input.nextLine();
			
			// Check the passwords are the same. If they are end loop and set password.
			if (password.equals(password2)) {
				this.password = password;
				this.save();
				System.out.println("Password has been successfully changed.");
				good = true;
			// Incorrect passwords. Ask player is they would like to try again and, if so, repeat the loop. 
			} else {
				System.out.println("Passwords don't match. Please try again.\n"
						+ "Would you like to try again? (y/n)");
				if (!common.yesNo(input)) {
					System.out.println("Password is unchanged.");
					good = true;
				}
			} 
			
		}
		this.save();
	}
	
	
	public void setPoints(int points) {
		/* Method to set a players points.
		 * It might be more correct to say it adds or subtracts from the current points
		 * Takes points as input. Can be positive or negative.
		 */
		
		// Multiply points my modifier and add them, points could be negative, to current points
		this.points += (points * this.mod);
		// Update leader board
		L.scoreUpdate(name, this.points);
		
		// If player has no more points left---game over
		if (this.points <= 0) {
			
			System.out.println("\nYou've run out of points...\n\n:(\n\n");
			System.out.println("^^^^^^^^^^Game Over^^^^^^^^^^");
			
			// Delete players save file
			File f = new File(name + ".txt");
			f.delete();
			
			// Exit the program
			System.exit(0);
			
		// If the player still has points save
		} else {
			this.save();
		}
	}
	
	public void setMod(double mod) {
		// This method doubles or halves the score modifier depending on the input
		this.mod *= mod;
		System.out.println("Your score modifer is now " + this.mod);
	}
	
	
	// Class Methods
	
	@Override
	public String toString() {
		// This method prints the player card to the console
		return ">>>>>>>>><<<<<<<<<\nName:\t" + name + "\nStatus:\t" + 
				getStatus() + "\nPoints:\t" + points + "\nMod:\tx" + 
				mod + "\n>>>>>>>>><<<<<<<<<";
	}
	
	public void save() {
		// This method saves the players current status. Modifier is not saved as it is considered a cheat
		String details = vip + "\n" + password + "\n" + points;
		common.savePlayer(name, details);
	}
	
	public void viewLeaderboard() {
		// Calls viewLeaderboard to print the leader board to the console
		L.viewLeaderboard();
	}
	
}
