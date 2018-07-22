import java.util.Scanner;

public class PigLatinTranslator {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// initial loop condition
		boolean cont = true;

		System.out.println("Welcome to the Pig Latin Translator!");

		do {
			System.out.print("Enter a line to be translated: ");
			String str = scan.nextLine();

			// check for empty input
			if (str.equals("")) {
				System.out.println("You didn't enter any text!");

			// send input string to checkWords method
			} else {
				splitWords(str);
			}

			// assembleResult method prints here
			
			System.out.println();
			System.out.println("Translate another line? (y/n): ");

			// set new loop condition
			if (scan.nextLine().equalsIgnoreCase("n")) {
				cont = false;
			}

		} while (cont == true);

		// end program
		scan.close();
	}

	public static void splitWords(String str) {

		// convert string to lower case
		String lowerCase = str.toLowerCase();

		// split lower case string around spaces
		String[] splitArray = lowerCase.split("\\s+");
		String split = "";

		// send split words to the findVowels method
		for (int i = 0; i < splitArray.length; i++) {
			split = splitArray[i];
			checkWords(split);
		}
	}

	public static void checkWords(String split) {

		// condition for characters not being numbers or symbols
		boolean alpha = true;

		// check if chars are letters or single-quotes (to allow contractions)
		for (int i = 0; i < split.length(); i++) {
			if (!(Character.isLetter(split.charAt(i)) || ((int) split.charAt(i) == 39))) {
				alpha = false;
			}
		}

		// if all characters are valid, send result to splitWords method
		if (alpha == true) {
			findVowels(split);

		// if numbers or symbols are detected, skip to assembleResult
		} else {
			assembleResult(split, -2);
		}
	}

	public static void findVowels(String split) {

		// position of first vowel in word
		// value of -1 indicates no vowel
		int position = -1;

		String vowels = "aeiou";

		// iterate through each character, find if it matches any vowels
		// break loop when first vowel is found
		boolean finished = false;
		for (int i = 0; i < split.length(); i++) {
			if (finished == true) {
				break;
			}
			for (int j = 0; j <= 4; j++) {
				if (split.charAt(i) == vowels.charAt(j)) {
					position = i;
					finished = true;
				}
			}
		}

		// send words and vowel positions to assembleResult method
		assembleResult(split, position);
	}

	public static void assembleResult(String split, int position) {
		String result = new String();
		String ending1 = "ay";
		String ending2 = "way";
		String firstPart = new String();

		// containing non-alphabetic characters, from checkWords method
		if (position == -2) {
			result = split;
		}

		// no vowel
		else if (position == -1) {
			result = split.concat(ending1);

		// begins with vowel
		} else if (position == 0) {
			result = split.concat(ending2);

		// vowel inside word
		} else {
			firstPart = split.substring(0, position);
			result = split.substring(position).concat(firstPart).concat(ending1);
		}

		System.out.print(result + " ");
	}
}
