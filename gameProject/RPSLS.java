package gameProject;

import java.util.Scanner;

public class RPSLS {
	public static void RPSLSgame(Player player, Scanner input) {
		/* This is the game Rock/Paper/Scissors/Lizard/Spock.
		 * Players can bet points and try to beat the computer in this adaption of Rock/Paper/Scissors
		 */
		
		// Call method to print the rules if the player wants them
		common.rules("Rock, Paper, Scissors, Lizard, Spock", input, 2);
			
		System.out.println("\nAlright. Lets play Rock, Paper, Scissors, Lizard, Spock...");
		
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
			
			// Computer uses the RNG method make choice
			String comp = "";
			switch (common.RNG(5, 1)) {
			case 1:
				comp = "Rock";
				break;
			case 2:
				comp = "Paper";
				break;
			case 3:
				comp = "Scissors";
				break;
			case 4:
				comp = "Lizard";
				break;
			case 5:
				comp = "Spock";
				break;
			}
			
			// Prompt player to make choice and treat the response for case sensitivity
			System.out.println("Please choose rock, paper, scissors, lizard, or spock");
			System.out.println("This is not case sensitive but you will need to enter the full word.");
			String handRaw = input.next();
			String hand = handRaw.toUpperCase();
			
			/* If statements for player's choice with switch statements for the computers
			 * The win and loose methods are called as appropriate.
			 * This goes on until line 190
			 */
			if (hand.equals("ROCK")) {
				System.out.println("\nYou chose:\t" + handRaw + "\nComputer chose:\t" + comp + "\n");
				switch(comp) {
				case "Rock":
					System.out.println("Draw...");
					break;
				case "Paper":
					System.out.println("Paper covers Rock.");
					common.loose(player, risking, odds);
					break;
				case "Scissors":
					System.out.println("Rock smashes Scissors.");
					common.win(player, risking, odds);
					break;
				case "Lizard":
					System.out.println("Rock crushes Lizard.");
					common.win(player, risking, odds);
					break;
				case "Spock":
					System.out.println("Spock vaporises Rock.");
					common.loose(player, risking, odds);
					break;
				}	
			} else if (hand.equals("PAPER")) {
				System.out.println("\nYou chose:\t" + handRaw + "\nComputer chose:\t" + comp + "\n");
				switch(comp) {
				case "Paper":
					System.out.println("Draw...");
					break;
				case "Rock":
					System.out.println("Paper covers Rock.");
					common.win(player, risking, odds);
					break;
				case "Scissors":
					System.out.println("Scissors cuts Paper.");
					common.loose(player, risking, odds);
					break;
				case "Lizard":
					System.out.println("Lizard eats Paper.");
					common.loose(player, risking, odds);
					break;
				case "Spock":
					System.out.println("Paper disproves Spock.");
					common.win(player, risking, odds);
					break;
				}
			} else if (hand.equals("SCISSORS")) {
				System.out.println("\nYou chose:\t" + handRaw + "\nComputer chose:\t" + comp + "\n");
				switch(comp) {
				case "Scissors":
					System.out.println("Draw...");
					break;
				case "Paper":
					System.out.println("Scissors cuts Paper.");
					common.win(player, risking, odds);
					break;
				case "Rock":
					System.out.println("Rock smashes Scissors.");
					common.loose(player, risking, odds);
					break;
				case "Lizard":
					System.out.println("Scissors decapitates Lizard.");
					common.win(player, risking, odds);
					break;
				case "Spock":
					System.out.println("Spock breaks Scissors.");
					common.loose(player, risking, odds);
					break;
				}
			} else if (hand.equals("LIZARD")) {
				System.out.println("\nYou chose:\t" + handRaw + "\nComputer chose:\t" + comp + "\n");
				switch(comp) {
				case "Lizard":
					System.out.println("Draw...");
					break;
				case "Paper":
					System.out.println("Lizard eats Paper.");
					common.win(player, risking, odds);
					break;
				case "Scissors":
					System.out.println("Scissors decapitates Lizard.");
					common.loose(player, risking, odds);
					break;
				case "Rock":
					System.out.println("Rock crushes Lizard.");
					common.loose(player, risking, odds);
					break;
				case "Spock":
					System.out.println("Lizard poisons Spock.");
					common.win(player, risking, odds);
					break;
				}
			} else if (hand.equals("SPOCK")) {
				System.out.println("\nYou chose:\t" + handRaw + "\nComputer chose:\t" + comp + "\n");
				switch(comp) {
				case "Spock":
					System.out.println("Draw...");
					break;
				case "Paper":
					System.out.println("Paper disproves Spock.");
					common.loose(player, risking, odds);
					break;
				case "Scissors":
					System.out.println("Spock breaks Scissors.");
					common.win(player, risking, odds);
					break;
				case "Lizard":
					System.out.println("Lizard poisons Spock.");
					common.loose(player, risking, odds);
					break;
				case "Rock":
					System.out.println("Spock vaporises Rock.");
					common.win(player, risking, odds);
					break;
				}
			// Statement for if the player failed to choose
			} else {
				System.out.println("Woops: Looks like you didn't choose one of the options.");
			}
			
			// Ask the player if they would like to play again
			System.out.println("Would you like to play again? (y/n)");
			// If no exit play loop
			if (!common.yesNo(input)) {
				System.out.println("Good game.\n\n");
				play = false;
			}
			
		}
		// Offer to play CodeBreaker instead or go back to main menu
		System.out.println("Whould your like to play CodeBreaker instead? (y/n)\nIf no, we will head back to the main menu.");
		// If yes send player to the game
		if (common.yesNo(input)) {
			CodeBreaker.CodeBreakerGame(player, input);
		// Otherwise inform them of their points and send them to the main menu.
		} else {
			System.out.println("\nYour score is: " + player.getPoints());
			System.out.println("\n\nWelcome back to the main menu " + player.getName() + ".\n\n Type HELP to list options.\n");
		}
		
		
	}
	
}
