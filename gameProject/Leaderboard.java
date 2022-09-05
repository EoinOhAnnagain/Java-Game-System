package gameProject;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Leaderboard {
	// This class managed the leader board.
	 
	// Instance variables for names of players and their scores. Names and scores will be at the same point in the ArrayLists, eg. leaders[4]'s score will be at score[4]
	private ArrayList<String> leaders = new ArrayList<String>();
	private ArrayList<String> score = new ArrayList<String>();
	// Leader board file
	private File leaderFile;
	
	
	// Constructor
	
	public Leaderboard() {
		/* Initialises the leader board using the leader board file
		 * No input or output
		 */
		
		// Creates file variable
		this.leaderFile = new File("Leaderboard.txt");
		// If the file exists
		if (leaderFile.exists()) {
			// Try to read names and points from the file. Add them to the ArrayLists
			try {
				Scanner myReader = new Scanner(leaderFile);
				while (myReader.hasNextLine()) {
					String name = myReader.nextLine();
					String points = myReader.nextLine();
					leaders.add(name);
					score.add(points);
				}
				// Cose file reader
				myReader.close();
			// Exception handling
			} catch (FileNotFoundException e) {
		    	System.out.println("An error occurred.");
		    	e.printStackTrace();
		    }
		// If the file does not exist
		} else {
			// Try to create the file
			try {
				leaderFile.createNewFile();
			// Exception handling
			} catch (IOException e) {
				System.out.println("Error creating Leaderboard.txt file");
				e.printStackTrace();
			}
		}
	}
	
	
	// Setters
	
	public void newPlayer(String name, int points) {
		/* Adds a new player to the leader board
		 * Takes in the players name and score
		 */
		
		// If the leader board is empty add player
		if (this.leaders.isEmpty()) {
			leaders.add(name);
			score.add(Integer.toString(points));
		// If it is not empty
		} else {
			// Add player and their score to the end of the ArrayLists
			leaders.add(name);
			score.add(Integer.toString(points));
			
			// Loop backwards through array comparing the new player to the player above them.
			for (int i = score.size()-1; i>0; i-- ) {
				int one = Integer.valueOf(score.get(i));
				int two = Integer.valueOf(score.get(i-1));
				
				// If the new player has a better score swap the players and repeat
				if (one>two) {
					String nameOne = leaders.get(i);
					String nameTwo = leaders.get(i-1);
					leaders.set(i, nameTwo);
					leaders.set(i-1, nameOne);
					score.set(i, String.valueOf(two));
					score.set(i-1, String.valueOf(one));
				// If not end the loop
				} else {
					i = 0;
				}
			}
		}
		// Save the leader board
		this.saveLeader();
	}
	

	public void changeName(String name, String newName) {
		/* Change the name of a player on the leader board
		 * Takes in their old name and their new one
		 */
		
		// Location not in list to be replaced
		int loc = -1;
		// loop through names of players until player is found. Save their location.
		for (int i = 0; i < leaders.size(); i++) {
			if (leaders.get(i).equals(name)) {
				loc = i;
				break;
			}
		}
		// If the player was not found print error message
		if (loc == -1) {
			System.out.println("Player not found");
		// If player is found replace their name on leader board
		} else {
			leaders.set(loc, newName);
			this.saveLeader();
		}
	}
	
	
	public void scoreUpdate(String name ,int points) {
		/* This method updates a players score on the leader board.
		 * It takes in their name and points.
		 */
		
		// Location not in list to be replaced
		int loc = -1;
		// loop through names of players until player is found. Save their location.
		for (int i = 0; i < leaders.size(); i++) {
			if (leaders.get(i).equals(name)) {
				loc = i;
				break;
			}
		}
		// If the player was found delete them from the leader board
		if (loc != -1) {
			leaders.remove(loc);
			score.remove(loc);
		} 
		// Add player to leader board using newPlayer as this will place them in the correct position
		newPlayer(name, points);
	}
	
	public void saveLeader() {
		// This method saves the leader board
		
		// Try to write the player names and their scores to the leader board, alternating between the two, one line each.
		try {
			FileWriter myWriter = new FileWriter(leaderFile);
			for (int i = 0; i < leaders.size(); i++) {
				myWriter.write(leaders.get(i) + "\n");
				myWriter.write(score.get(i) + "\n");
			}
			// Close writter
		    myWriter.close();
		    
		// Exception handling
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	
	public void viewLeaderboard() {
		// Prints out the leaderboard for the player to see.
		System.out.println(">>>>>Current Leader Board<<<<<");
		for (int i = 0; i < leaders.size(); i++) {
			System.out.println("#" + (i+1) + "\t" + score.get(i) + "\t" + leaders.get(i));
		}
		System.out.println(">>>>>>>>>>>>>>><<<<<<<<<<<<<<<");
	}
}
