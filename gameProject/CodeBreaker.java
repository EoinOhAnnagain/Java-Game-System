package gameProject;

import java.util.Scanner;

public class CodeBreaker {
	public static void CodeBreakerGame(Player player, Scanner input) {
		/* This is the game CodeBreaker.
		 * Players can bet points and try and guess the computers code.
		 * They have ten tries.
		 * It can be challenging but there is a trick to it.
		 */
		
		// Call method to print the rules if the player wants them
		common.rules("CodeBreaker", input, 1);
			
		System.out.println("\nAlright. Lets play CodeBreaker...");
		
		// Loop while play is true
		boolean play = true;
		while (play==true) {
			
			// Generate the odds for the round
			int odds = common.RNG(5, 1);
			System.out.println("\nThe odds this round are " + odds + " to 1");
			
			// Player can requests new odds three times before game goes ahead anyway
			for (int i = 0; i < 3; i++) {
				System.out.println("Happy with those odds? (y/n)");
				
				// If happy set i to 5 so the loop ends
				if (common.yesNo(input)) {
					i = 5;
				} else {
					// If at the end of the for loop don't change the odds
					if (i == 2) {
						System.out.println("Too bad. Lets start");
					} else {
						// Re-role odds
						odds = common.RNG(5, 1);
						System.out.println("What about " + odds + " to 1?");
					}
				}
			}
			
			// Use risk method so player can bet points
			int risking = common.risk(player, input);
			
			// Array to hold 4 integers
			int[] master = new int[4];
			
			// fill array with random numbers between 0 and 9 using RNG method
			for (int i = 0; i < master.length; i++) {
				master[i] = common.RNG(9);
			}
			
			// For loop for each round
			for (int r = 1; r <= 10; r++) {
				System.out.println("Round "+r);
				
				// Player's guess array
				int[] guess = new int[4];
				
				// Fill array with player's guesses. Check that the guess is valid
				for (int g = 0; g < guess.length; g++) {
					int nextGuess = 11;
					while (nextGuess>9 || nextGuess<0) {
						nextGuess = input.nextInt();
						if (nextGuess>9 || nextGuess<0) {
							System.out.println("Error: Not a valid number.\nRemember to put a space between each number or enter each seperatly.\nPlease try again.");
						}
					}
					guess[g] = nextGuess;
				}
				
				// Boolean arrays to checking which numbers are hits blows and used.		
				boolean blown[] = {false, false, false, false};
				boolean hit[] = {false, false, false, false};
				boolean used[] = {false, false, false, false};
				
				// Loop through player and computer arrays to compare numbers. 
				for (int i = 0; i<4; i++) {
					for (int g = 0; g<4; g++) {
						// If two numbers and their positions match set the hit and used positions to true
						if (g==i && guess[g]==master[i]) {
							hit[i] = true;
							used[g] = true;
						}
					}
				}
				
				// checks so that blows hits and aren't doubled as blows if a digit is doubled.
				for (int i = 0; i<4; i++) {
					if (hit[i]) {
						continue;
					}
					for (int g = 0; g<4; g++) {
						if (used[g]) {
							continue;
						}
						if (guess[g]==master[i]) {
							used[g] = true;
							blown[i] = true;
						}
						
						
					}
				}
				
				// Create and fill counters for hits and blows 
				int hits = 0;
				int blows = 0;
				
				for (int i=0; i<4; i++) {
					if (blown[i]) {
						blows++;
					}
					if (hit[i]) {
						hits++;
					}
				}
				
				// Inform player of how they did this round
				System.out.println(hits+" hits\t"+blows+" blows");
				
				// If hits is 4 the player won.
				if (hits == 4) {
					System.out.println("Congratulations. You win.");
					
					// r to 20 as anything above 10 will stop the loop
					r = 20;
					
					// Inform them of the correct answer
					System.out.println("The correct answer was: ");
					for (int i = 0; i < master.length; i++) {
						System.out.println(master[i]+" ");
					}
					
					// Use win method to award points
					common.win(player, risking, odds);
				}
				
				// If r is 10 and the player has not won the game is over
				if (r == 10) {
					System.out.println("Game Over. You loose.");
					// print the correct answer
					System.out.println("The correct answer was: ");
					for (int i = 0; i < master.length; i++) {
						System.out.println(master[i]+" ");
					}
					// Use the loose method to deduct points
					common.loose(player, risking, odds);
				}
			}
			
			// Ask if the player wants to play again. Set play based on their response to either start loop again or end the loop.
			System.out.println("Would you like to play again?");
			play = common.yesNo(input);
		}
		
		// Offer to play Rock/Paper/Scissors/Lizard/Spock or go back to the main menu.
		System.out.println("Whould your like to play Rock/Paper/Scissors/Lizard/Spock instead? (y/n)\nIf no, we will head back to the main menu.");
		// If yes send player to the game
		if (common.yesNo(input)) {
			RPSLS.RPSLSgame(player, input);
		// Otherwise inform them of their points and send them to the main menu.
		} else {
			System.out.println("\nYour score is: " + player.getPoints());
			System.out.println("\n\nWelcome back to the main menu " + player.getName() + ".\n\n Type HELP to list options.\n");
		}
	}
}
