/**
 * file:ex2a.java
 * @author Ohad Cohen, ohad.cohen6@mail.huji.ac.il
 * exercise : intro2cs ex2a 2011-2012 
 * description: a simple program that converts temperatures from Fahrenheit to Celsius
 * and vice versa.
 */

import java.util.Scanner;

public class Ex2a {
	
	//this method get a temperature in Celsius scale 
	//and convert it to Fahrenheit scale
	private static double toFahrenheit(double Celsius) {
		return (Celsius * 1.8 + 32);
	}
	
	//this method get a temperature in Fahrenheit scale 
	//and convert it to Celsius scale
	private static double toCelsius(double Fahrenheit) {
		return (Fahrenheit - 32) * (5 / 9.0) ;
	}
	
	public static void main(String[] args) {
		System.out.print("This program converts temperatures\n"+
				"Enter temperature and unit (e.g. 37 C or 100 F): ");
		Scanner in = new Scanner(System.in);
		double temperature = in.nextDouble(); //Temperature
		char scale = in.next().charAt(0); //scale
		System.out.println("Temperature in "+((scale=='C')?
				"Fahrenheit: "+Math.round(toFahrenheit(temperature)*100)/100.0:
			"Celsius: "+Math.round(toCelsius(temperature)*100)/100.0));
	}

}
