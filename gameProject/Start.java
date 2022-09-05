package gameProject;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

public class Start {
	
	public static void main(String[] args) {
		/* Main method.
		 * This is the start point and either sets up new players or loads old ones before sending them to the mainMenu
		 */
		
		// Open the scanner
		final Scanner input = new Scanner(System.in);
		
		// File for player names
		File pNames = new File("playerNames.txt");
		
		// Array list for player names. Filled using players method
		ArrayList<String> players = players(input, pNames); 
		
		System.out.println("Hello :)\nAre you a returning player? (y/n)");
		
		// Booleans to check for returning players and if they were set up correctly
		boolean returning = common.yesNo(input);
		boolean setup = false;
		
		// If they are returning
		if (returning) {
			
			// While loop
			while (returning) {
				
				// Get player name
				System.out.println("What is your name?");
				String name = input.nextLine();
				
				// If their name is in the list of names
				if (players.contains(name)) {
					
					// returning to false so the loop can be exited
					returning = false;
					
					// Attempt to load data using load method.
					setup = load(name, input);
					
					// If load failed check if they got game over previously. 
					if (!setup) {
						System.out.println("\nDid you previously get a 'Game Over'? (y/n)");
					    if (common.yesNo(input)) {
					    	System.out.println("You'll have to start a new save then. Sorry");
					    } else {
					    	System.out.print("Are you sure?");
					    }
					}
					
				// If their name wasn't on the list
				} else {
					System.out.println("Sorry, I don't recognise that name."
							+ "\nWould you like to start as a new player? (y/n)");
					// set returning based on their response so the loop can restart or exit
					returning = !common.yesNo(input);
				}
			}
		}
		
		// If they are not returning players
		if (returning == false & setup != true) {
			
			// Get player name
			System.out.println("\nAlright. \nLets set you up as a new player.\n\n"
					+ "Let's start with your name.\nWhat would you like your name to "
					+ "be? (Leave blank for a default name)");
			String playerName = input.nextLine();
			
			// If no name given create default name using newName method
			if (playerName.equals("")) {
				System.out.println("A default name it is.");
				playerName = newName("Player", players);
				
			// If name is taken give variation using newName method
			} else if (players.contains(playerName)) {
				System.out.println("Another player already has this name.");
				playerName = newName(playerName, players);
				System.out.println("Lest call you " + playerName + " instead.");
			
			// Players name was available
			} else {
				System.out.println("Awesome. " + playerName + " it is.");
			}
			
			// Offer for player to become elite player
			System.out.println("\nWe have elite players and standard players.\nElite players have several"
					+ " perks such as they start with extra points, can change their password, and don't loose as many "
					+ "points if a game goes wrong.\nWould you like to be an elite player? (y/n)");
			boolean eliteChoice = common.yesNo(input);
			
			// If rejected ask if they are sure
			if (!eliteChoice) {
				System.out.println("Are you sure. You'll start withn 1337 points compared to on 100 for standard player.");
				eliteChoice = common.yesNo(input);
			}
			
			// If they want to be elite players 
			if (eliteChoice) {
				
				// Request credit card details
				System.out.println("Please enter your credit card details: ");
				String card = input.nextLine();
				
				// While loop for checking credit card
				boolean cardCheck = true;
				while (cardCheck) {
					
					// If failed ask if they want to try again
					if (!CardTest.card(card)) {
						System.out.println("Invalid card. Would you like to try another one? (y/n)");
						
						// If no exit loop
						if (!common.yesNo(input)) {
							System.out.println("No prolem. We will set you up with a standard account then.");
						 	cardCheck = false;
						
						// Get card details for next loop
						} else {
							System.out.println("Please enter your credit card details: ");
							card = input.nextLine();
						}
					
					// If card is valid welcome them to the elite
					} else {
						System.out.println("Wonderful. Welcome to the elite.");
						
						// Create instance of elite player
						ElitePlayer player = new ElitePlayer(playerName, input);
						
						// Add player name to the list of players
						players.add(playerName);
						
						// Save the list of players to playerName.txt
						savePlayers(pNames, players);
						
						// Set cardCheck to false so loop wont be repeated
						cardCheck = false;
						
						// Bring elite player to main menu
						MainMenu.games(player, input);
					}
				}
				
			// Standard player setup
			} else {
				System.out.println("No problem.");
				
				// Create instance of standard player
				StandardPlayer player = new StandardPlayer(playerName, input);
				
				// Add player name to the list of players
				players.add(playerName);
				
				// Save the list of players to playerName.txt
				savePlayers(pNames, players);
				
				// Bring elite player to main menu
				MainMenu.games(player, input);
			}
		}
		// Close scanner as, if here, player has quit the game
		input.close();
	}
	
	public static ArrayList<String> players(Scanner input, File pNames) {
		/* This method takes the scanner and the playerNames.txt file as input.
		 * It populates the players ArrayList with the names of all players.
		 */
		
		// Create players ArrayList
		ArrayList<String> players = new ArrayList<String>();
		
		// If playerNames.txt exists
		if (pNames.exists()) {
			
			try {
				// Add each name in the file to the players ArrayList using file scanner
				Scanner myReader = new Scanner(pNames);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					players.add(data);
		      	}
				
				// Close file scanner
		      	myReader.close();
		      	
		    } catch (FileNotFoundException e) {
		    	System.out.println("An error occurred.");
		    	e.printStackTrace();
		    }
			
		// If file does not exit create the file. This should only happen the first time the project is run
		} else {
			try {
				pNames.createNewFile();
			} catch (IOException e) {
				System.out.println("Error creating players.txt file");
				e.printStackTrace();
			}
		}
		
		// Return players ArrayList
		return players;
	}
	
		
	
	
	public static boolean load(String name, Scanner input) {
		/* This method takes the player name and scanner as input.
		 * It attempts to load the player data from their save file and create a class instance using the data in the file.
		 * If it is successful it sends the player to the mainMenu.
		 * Otherwise it returns false.
		 */
		
		// Player file
		File load = new File(name + ".txt");
		
		// If the player file exists
		if (load.exists()) {
			try {
				// Get player data from file
				Scanner myReader = new Scanner(load);
				String vip = myReader.nextLine();
				String password = myReader.nextLine();
				String pointsS = myReader.nextLine();
				
				// Convert sting version of points to integer
				int points = Integer.parseInt(pointsS);
				
				// Close file scanner
				myReader.close();
				
				// Loop for checking player password
				boolean pass = false;
				while (!pass) {
					
					// Enter password
					System.out.println("Please enter your password: ");
					String entered = input.nextLine();
					
					// If password is correct exit loop
					if (entered.equals(password)) {
						pass = true;
						
					// If incorrect ask to re-enter or return false
					} else {
						System.out.println("Incorrect Password. Try again? (y/n)");
						pass = !common.yesNo(input);
						if (pass) {
							return false;
						}
					}
					
				}
				
				// If player is not elite
				if (vip.contains("false")) {
					boolean vipB = false;
					
					// Create standard player instance
					StandardPlayer player = new StandardPlayer(name, vipB, points, password);
					System.out.println("Welcome back " + name);
					
					// Send player to main menu
					MainMenu.games(player, input);
					
					// return true
					return true;
					
				// If player is elite
				} else {
					boolean vipB = true;
					
					// Create elite player instance
					ElitePlayer player = new ElitePlayer(name, vipB, points, password);
					System.out.println("Welcome back " + name);
					
					// Send player to main menu
					MainMenu.games(player, input);
					
					// Return true
					return true;
				}
			
			// Error handeling
			} catch (FileNotFoundException e) {
		    	System.out.println("An error occurred.");
		    	e.printStackTrace();
		    }
		
		// If save file does not exist 
		} else {
		    System.out.println("No save data found.");
		}
		
		// Return false
		return false;
		
	}
	
	
	public static String newName(String name, ArrayList<String> players) {
		/* This method takes a potential player name as input, and the player name array.
		 * It is called if the players chosen name was already taken.
		 * It increments a number after the players name until a version is found that is not in the player ArrayList.
		 * It returns this new name.
		 */
		
		// Digit to append
		int digit = 1;
		
		// Loop until name is found. Broken by the name returning
		while (true) {
			
			// Append digit to name and check if new name exists
			String newName = name + Integer.toString(digit);
			
			// If name exists increment digit
			if (players.contains(newName)) {
				digit ++;
				
			// If it doesn't exist return the new name
			} else {
				return newName;
			}
			
			
		}
	}
	
	
	public static void savePlayers(File pNames, ArrayList<String> players) {
		/* This method takes in the playerNames.txt file, and the ArrayList of player names.
		 * It does not return anything but saves the player names to the file, one per line.
		 */
		
		try {
			// Open file writter
			FileWriter myWriter = new FileWriter(pNames);
			
			// Write each name in the ArrayList to the file
			for (String p : players) {
				myWriter.write(p + "\n");
			}
			
			// Close the writter
		    myWriter.close();
		    
		// Exception management 
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}

}
