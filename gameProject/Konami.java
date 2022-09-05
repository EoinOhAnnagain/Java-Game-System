package gameProject;

import java.util.Scanner;

public class Konami {
	public static void Code(Player player, Scanner input) {
		/* This is an easter egg.
		 * Players can play a game of flip the coin to double or half their score modifier.
		 * This is considered cheating and is not saved.
		 * If they loose they also loose half, or a quarter for elite players, of their total points.
		 * At the end one of three inspirational quotes is printed.
		 */
		
		
		System.out.println("Konami Code entered successfully.\n\n"
				+ "You've found the cheet code.\nLets play heads or tails to adjust your points modifer.\n"
				+ "If you win your modifer will be doubled.\n"
				+ "But be warned though. If you loose your modifer will be halved.\n"
				+ "So will your score.\n\n"
				+ "Nothing ventured, nothing gained.");
		
		// Loop while play is true
		boolean play = true;
		while (play) {
			// Flip a coin using the RNG method to return 1 or 0, heads or tails
			System.out.println("*flips coin*\n\nCall it...");
			int coin = common.RNG(1);
			
			// Get the player guess. Convert to uppercase and get first character to prevent issues with misstypes etc as cheater are punished.
			String choiceRaw = input.nextLine();
			String choice = choiceRaw.toUpperCase();
			char C = choice.charAt(0);
			
			// Cheater variable
			boolean cheater = false;
			
			// If player chose heads
			if (C == 'H') {
				// If coin is heads
				if (coin == 1) {
					System.out.println("Heads. You win.");
					// Double modifier
					player.setMod(2);
				// If tails
				} else {
					System.out.println("Tail. You loose.");
					// Halve modifier and reduce points
					
					int pp = player.getPoints();
					player.setPoints((player.getPoints()/2)*-1);
					System.out.print("Your points have fallen from "
							+ "" + pp + " to " + player.getPoints());
					player.setMod(0.5);
				}
			// If player chose tails
			} else if (C == 'T'){
				// If coin is tails
				if (coin == 0) {
					System.out.println("Tails. You win.");
					// Double modifier
					player.setMod(2);
				} else {
					// If heads
					System.out.println("Heads. You loose.");
					// Halve modifier and reduce player points
					int pp = player.getPoints();
					player.setPoints((player.getPoints()/2)*-1);
					System.out.print("Your points have fallen from "
							+ "" + pp + " to " + player.getPoints());
					player.setMod(0.5);
				}
			// If cheating minus twenty points and loop again
			} else {
				System.out.println("Are we trying to cheet by not calling it.\nThat'll be -20 points");
				player.setPoints(-20);
				System.out.println("Now lets try that again.");
				cheater = true;
			}
			// If they didnt cheat ask to play again
			if (!cheater) {
				System.out.println("\n\nAgain? (y/n)");
				if (!common.yesNo(input)) {
					play = false;
				}
			}
		}
		// Nice quotes to leave by
		int quote = common.RNG(2);
		switch (quote) {
		case 0:
			System.out.println("\nRemember:\n\tWhat's done is done.\n\t\t-William Shapespear");
			break;
		case 1:
			System.out.println("\nRemember:\n\tIf you are going through hell, keep going.\n\t\t-Winston Churchill");
			break;
		case 2:
			System.out.println("\nRemember:\n\tAll things are difficult before they are easy.\n\t\t-Thomas Fuller");
			break;
		default:
			System.out.println("You're not supposed to be able to see this.");
		}
		
		// Welcome back to main menu
		System.out.println("\n\nWelcome back to the main menu " + player.getName() + ".\nAllow me to list your options.");
		
	}
}
