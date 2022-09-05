package gameProject;

public class CardTest {
	
	public static boolean card(String cardString) {
		/* This tests that the users credit card is a valid credit card.
		 * To do this it performs three tests on it: a check on the number of digits, the prefix, and the luhn test.
		 * It returns true if the card is valid, false otherwise.
		 */
		
		// Removes any spaces in the credit card number.
		String card = cardString.replace(" ", "");
		
		// If any of the checks fail return false. Otherwise return true.
		if (checkable(card)==false || prefixCheck(card)==false || Luhn(card)==false) {
			return false;
		} else {
			return true;
		}
		
		
			
	}
	
	public static boolean checkable(String card) {
		/* Takes the card number as input and checks it is the layout of a credit card number, ie. 13-16 digits. 
		 * Returns true if passed, flase otherwise.
		 */
		
		// If the string contains anything that is not a digit return false
		if (!card.matches("[0-9]+")) {
			return false;
		// If it is not an acceptable card length return false.
		} else if (card.length() < 13 || card.length() > 16) {
			return false;
		// Otherwise return truw=e
		} else {
			return true;
		}
	}
	
	public static boolean prefixCheck(String card) {
		/* Takes the card number as input and checks that is has an acceptable prefix, ie. 4, 5, 6, 7, or 37.
		 * Returns false is failed, true otherwise.
		 */
		
		// If the prefix is correct return true. Otherwise false.
		char pre = card.charAt(0);
		if (pre == '4' || pre == '5' || pre == '7' || pre == '6' || (pre == '3' && card.charAt(1) == '7')) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean Luhn(String card) {
		/* Takes the card number as input and performs the luhm test on it.
		 * To do this it reverses the card numbers and suns the digits in odd positions.
		 * It also sums the doubles of the digits in even positions, adding the digits together if the double is more than one digit.
		 * It then adds these both together and checks that that last number digit is a 0, ie. divisible by 10.
		 * If this passes it returns true, otherwise it returns false.
		 */
		
		// Counters for the digits.
		int countEven = 0;
		int countOdd = 0;
		
		// Variables to hold the current number, it doubled.
		int current = 0;
		int currentDoubled = 0;
		
		// Reversing the card string
		String reversed = "";
		for (int i = card.length()-1; i >= 0; i--) {
			reversed += Character.toString(card.charAt(i));
		}
			
		// For each digit in the reversed card
		for (int i = 0; i < reversed.length(); i++) {
			char ct = reversed.charAt(i);
			current = Character.getNumericValue(ct);
			// If it is in an even position double it.
			if (i%2==1) {
				currentDoubled = current*2;
				// If the double has two digits sum them. Add digits onto even counter.
				if (currentDoubled >= 10) {
					countEven += ((currentDoubled/10)+(currentDoubled-10));
				} else {
					countEven += currentDoubled;
				}
			} else {
				// Add digit onto odd counter.
				countOdd += current;
			}
		}
		// Sum counters and check if they are divisible by 10. If so return true, otherwise, return false.
		if ((countEven+countOdd)%10==0) {
			return true;
		} else {
			return false;
		}
		
	}


}
