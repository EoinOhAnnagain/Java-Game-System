package gameProject;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class common {
	
	// This class contains all the methods that are commonly used throughout the project.
	
	public static void rules(String game, Scanner input, int gameNumber) {
		/* This method prints the appropriate for each game. 
		 * It takes in the name of the game, the scanner, and a numeric code for each game.
		 * It does not return anything but will print the rules if the player wants them.
		 */
		
		// Ask player if they would like to read the rules
		System.out.print("\nWould you like to read the rules to "+game+" (y/n)");
		if (yesNo(input)) {
			
			// Switch for the rules to print.
			switch (gameNumber) {
			case 1:
				System.out.println("\n>>>>>Rules of CodeBreaker<<<<<\n\nThe computer is going to come up with a four digit code"
						+ "and you have ten turns to try and crack it.\n\n"
						+ "On each turn you can guess four digits between 0 and 9 and the computer will respond with the number\n\tof "
						+ "hits and blows you have. A hit means you have a number in the correct position, but doesn't tell you\n\twhich "
						+ "of the four numbers it is. A blow means you have a correct digit, but it is in the wrong position.");
				break;
			case 2:
				System.out.println("\n>>>>>Rules of Rock/Paper/Scissors/Lizard/Spok<<<<<\n\nEach round we will both pick one of the "
						+ "five options.\nEach defeats two of the others, but also loses to two others and draws with itself.\n"
						+ "As you have probably figured out it is a slightly more complex version of Rock/Paper/Scissors and"
						+ "the matchups are as such:\n\n"
						+ "Scissors cuts paper, paper covers rock, rock crushes lizard, lizard poisons Spock, \n"
						+ "Spock smashes scissors, scissors decapitates lizard, lizard eats paper, paper disproves Spock, \n"
						+ "Spock vaporizes rock, and as it always has, rock crushes scissors.");
				break;
			}
			System.out.println("\n\nBefore each game you will be asked how many point you want to risk on the "
					+ "outcome and be given odds on the round.\\n\"\n"
					+ "The odds will multiple the number of points you can win, but also the number of point you could loose.\\n\\n\"\n"
					+ "Good luck.\n\n>>>>>>>>>>>>>>><<<<<<<<<<<<<<<\n");
			
		}
	}

	public static int risk(Player player, Scanner input) {
		/* This method asks the player how many points they would like to gamble on the current round of the game. 
		 * It takes in the instance of the player class and the scanner
		 * It returns the integer of how much the player would like to gamble
		 */
		
		// Get the number of points the player has.
		int points = player.getPoints();
		
		// Start a loop. Ends when the player enters an amount which is returned
		while (true) {
			
			// Ask how many points the player would like to risk
			System.out.println("You have " + points + " points.");
			System.out.println("How many would you like to risk?");
			String riskS = input.nextLine();
			
			// Try block to ensure that the player entered an integer.
			try {
				// Cast number string to integer.
				int risking = Integer.parseInt(riskS);
				
				// If they tried to risk nothing or less restart the loop.
				if (risking <= 0) {
					System.out.println("You have to risk something.");
					
				// If they tried to risk more points than they had restart the loop.
				} else if (risking > points){
					System.out.println("You don't have that many points.");
					
				// Otherwise return the amount they want to risk
				} else {
					return risking;
				}
			
			// Error handling if the user tried to enter something not an integer. Restarts the loop.  
			} catch (NumberFormatException e) {
				System.out.println("Please choose a number.");
			}	
		}
	}
	
	
	public static void win(Player player, int risk, int odds) {
		/* Method for if the player won a game. 
		 * Prints how many points they won and adds them to their total by calling the class instance setter
		 */
		System.out.println("You win " + Math.round((risk*odds)*player.getMod()) + " point(s).");
		player.setPoints(risk*odds);
	}
	
	public static void loose(Player player, int risk, int odds) {
		/* Method for if the player lost a game. 
		 * Prints how many points they lost and subtracts them from their total by calling the class instance setter
		 */
		String vip = player.getStatus();
		// If player is elite they only loose half the points. Print statement is altered here. this is managed in the class.
		if (vip.equals("Elite")) {
			System.out.println("You loose " + Math.round((((risk*odds)*-1)*player.getMod())/2) + " point(s).");
		} else {
			System.out.println("You loose " + Math.round(((risk*odds)*-1)*player.getMod()) + " point(s).");
		}
		player.setPoints((risk*odds)*-1);
	}

	public static boolean yesNo(Scanner input) {
		/* Method for processing yes/no responses.
		 * Takes in the scanner as input and returns a boolean; true is yes, false if no.
		 */
		
		// Loop for getting responce
		boolean ynCheck = false; 
		while (ynCheck == false) {
			
			// Get input from player
			String res = input.nextLine();
			String response = res.toUpperCase();
			
			// Create char r, for response, here as otherwise it creates compiling issues
			char r = 'f';
			
			// If the response has a length set r to be the first character
			if (response.length()>0) {
				r = response.charAt(0);
			}
			
			// If the char is Y return true
			if (r=='Y') {
				return true;
			// If char is not N print error and restart loop
			} else if (r!='N') {
				System.out.println("please enter y or n");
			// Return false as char must be N
			} else {
				ynCheck = true;
			}
		}
		return false;
	}
	
	public static int RNG(int max) {
		/* RNG calculator between 0 and an digit provided as input
		* This is not provided by the user so I did not include checks on the input.
		*/
		int randomNumber = (int)(Math.random()*(max+1));
		return randomNumber;
	}
	
	public static int RNG(int max, int min) {
		/* RNG calculator between two digits provided as input. 
		 * These are not provided by the user so I did not include checks on the digits.
		 */
		int randomNumber = min+(int)(Math.random()*(max+1-min));
		return randomNumber;
	}
	
	public static void savePlayer(String name, String details) {
		/* Method to save player information to a file with their name as the title.
		 * It takes in the players name and a string with their details provided by the class instance.
		 * Nothing is returned but the player data is saved to a file, one line for each piece of information
		 */
		
		// Players file
		File save = new File(name + ".txt");
		
		// If the file does not exists
		if (!save.exists()) {
			
			// Try and create a new file with the players name
			try {
				save.createNewFile();
			// Exception handeling
			} catch (IOException e) {
				System.out.println("Error");
				e.printStackTrace();
			}
		}
		
		// Try and write the player data to the file
		try {
		      FileWriter myWriter = new FileWriter(save);
		      myWriter.write(details);
		      myWriter.close();
		// Exception handeling
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
}

