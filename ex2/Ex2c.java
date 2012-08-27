/**
 * file:ex2c.java
 * @author Ohad Cohen, ohad.cohen6@mail.huji.ac.il
 * exercise : intro2cs ex2c 2011-2012 
 * description:a program that do some work on strings.
 */

import java.util.Scanner;

public class Ex2c {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter a sentence: ");
		String sentence = in.nextLine();
		System.out.println("1) Number of characters\n"
				+ "2) Number of characters without spaces\n"
				+ "3) Convert to upper/lower case\n"
				+ "4) Trim N characters from start/end\n"
				+ "5) Replace a letter\n" + "6) Remove the middle characters\n"
				+ "7) Replace first and last word\n"
				+ "Choose option to execute: ");
		int option = in.nextInt();
		switch (option) {

		case 1:// Number of characters
			System.out.println("The answer is: " + sentence.length());
			break;

		case 2:// Number of characters without spaces
			System.out.println("The answer is: " + sentence.replace(" ", "").length());
			break;

		case 3:// Convert to upper/lower case
			System.out.println("Upper (u) or Lower (l) case? ");
			System.out.println("The answer is: " +
					(in.next().charAt(0) == 'u' ? sentence.toUpperCase() : 
						sentence.toLowerCase()));
			break;

		case 4:// Trim N characters from start/end
			System.out.println("Number of characters to trim: ");
			int n = in.nextInt();
			System.out.println("Trim from start (s) or end (e): ");
			System.out.println("The answer is: "
					+ (in.next().charAt(0) == 's' ? sentence.substring(n):
						sentence.substring(0, sentence.length()-n)));
			break;

		case 5:// Replace a letter
			System.out.println("Letter to replace: ");
			char source = in.next().charAt(0);
			System.out.println("New letter: ");
			char destination = in.next().charAt(0);
			System.out.println("The answer is: " + sentence.replace(source, destination));
			break;

		case 6:// Remove the middle characters
			System.out.println("The answer is: "
					+sentence.substring(0, sentence.length()/2-1+sentence.length()%2)+ 
					sentence.substring(sentence.length()/2+1));
			break;

		case 7:// Replace first and last words
			if(!sentence.contains(" ")) {//don't want an exception when there is only one word
				System.out.println("The answer is: "+sentence);
				break;
			}
			System.out.println("The answer is: "
					+ sentence.substring(sentence.lastIndexOf(' ') + 1) +              
					sentence.substring(sentence.indexOf(' '), sentence.lastIndexOf(' ') + 1) +
					sentence.substring(0,sentence.indexOf(' ')));                     
			break;
		}
	}
}
