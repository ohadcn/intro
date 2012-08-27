/**
 * file:ex2b.java
 * 
 * @author Ohad Cohen, ohad.cohen6@mail.huji.ac.il
 * exercise : intro2cs ex2b 2011-2012 
 * description:a program that solves system of 2 linear equations.
 */

import java.util.Scanner;

public class Ex2b {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("This program solves a system of 2 linear equations.\nEnter the coefficients a11 a12 a21 a22 b1 b2: ");

		// read the numbers
		int a = in.nextInt(), b = in.nextInt(), // lowercase - for the first equation
		A = in.nextInt(), B = in.nextInt(), // uppercase - for the second equation
		c = in.nextInt(), C = in.nextInt();

		System.out.println("Eq1: " + a + "*x1+" + b + "*x2=" + c);
		System.out.println("Eq2: " + A + "*x1+" + B + "*x2=" + C);

		// check how many solutions we have
		if ((a * B) == (b * A)) {
			if ((c*A!=a*C)||(B*c!=b*C)||(a==0&&b==0&&c!=0)||(A==0&&B==0&&C!=0))
				System.out.println("No solution");
			else
				System.out.println("Many solutions");
		} 
		else {// let's solve the equations
			double sx = (B * c - b * C)/(double) (a * B - A * b),
					sy = (a * C - A* c)/(double) (a * B - A * b);
			System.out.println("Single solution: ("+Math.round(sx*1000)/1000.0 + ',' + Math.round(sy*1000)/1000.0 + ')');
		}
	}
}
