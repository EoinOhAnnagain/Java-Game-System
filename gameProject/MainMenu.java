package gameProject;

import java.util.Scanner;

public class MainMenu {
	
	public static void games(Player player, Scanner input) {
		// This is the main menu where the player can choose from several options including what games to play
		
		// Get the players name
		String name = player.getName();
		
		System.out.println("\n\n\n\n");
		System.out.println("Welcome to the main menu " + name + ".\nAllow me to list your options.");
		
		// Player options string to variable
		System.out.println("Each option has a code you can enter for your convenience.");
		String options = "\nGames:\n"
				+ "\t>>Codebreaker<<\t\t\t\t\tCode: CB\n"
				+ "\t>>Play Rock/Paper/Scissors/Lizard/Spock<<\tCode: RPSLS\n"
				+ "\nOther Options:\n"
				+ "\t>>View Your Player Card<<\t\t\tCode: PC\n"
				+ "\t>>Change Your Password<<\t\t\tCode: PASS\n"
				+ "\t>>View Leader Board<<\t\t\t\tCode: LB\n"
				+ "\t>>Save<<\t\t\t\t\tCode: SAVE\n"
				+ "\t>>Save and Quit<<\t\t\t\tCode: QUIT\n"
				+ "\n\t>>See Options Again<<\t\t\t\tCode: HELP\n";
	
		// Present player with options
		System.out.println(options);

		// While player is not finished
		boolean finished = false;	
		while (!finished) {
			
			// Ask for code of option. Treat it to avoid case issues
			System.out.println("\nPlease enter the code for what you would like to do:\n");
			String choiceRaw = input.nextLine();
			String choice = choiceRaw.toUpperCase();
			
			// Switch based on players choice 
			switch (choice) {
			// Play CodeBreaker
			case "CB":
				CodeBreaker.CodeBreakerGame(player, input);
				break;
			// Play Rock/Paper/Scissors/Lizard/Spock
			case "RPSLS":
				RPSLS.RPSLSgame(player, input);
				break;
			// Print player card
			case "PC":
				System.out.println(player.toString());
				break;
			// Change password
			case "PASS":
				player.changePassword(input);
				break;
			// View Leader board
			case "LB":
				player.viewLeaderboard();
				break;
			// Save game
			case "SAVE":
				player.save();
				break;
			// Save and quit. Exits loop, returns to Start class, ends game
			case "QUIT":
				player.save();
				finished = true;
				break;
			// Reprint main menu options
			case "HELP":
				System.out.println(options);
				break;
			// Konami code easter egg
			case "UUDDLRLRBA":
				Konami.Code(player, input);
				break;
			// Degault if there is an issue
			default:
				System.out.println("Unrecognised entry.");
			}		
		}
		// Goodbye message and leader board printed 
		System.out.println("\nSee you next time.\n");
		player.viewLeaderboard();
	}

}
