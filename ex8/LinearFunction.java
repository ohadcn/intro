/**
 * file:LinearFunction.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex8 2011-2012 
 * description: This class implements a linear function as RealFunction. 
 */
public class LinearFunction implements RealFunction{
	
	/**
	 * those two variables stores the coefficients of the linear functions
	 */
	private double a,b;
	
	/**
	 * Constructs a new LinearFunction.
	 *  Receives two doubles that are the coefficients of the linear function.
	 * @param a the coefficient of x int the linear equation.
	 * @param b the linear function's free coefficient.
	 */
	public LinearFunction(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	/**
	 * returns the f(x) value of the RealFunction.
	 * @param x the x value given.
	 * @return the f(x) value of this function i.e. a*x + b.
	 */
	public double valueAt(double x) {
		return a*x+b;
	}
	
	/**
	 * returns a String representation of the Linear function.
	 * @return a String representation of the Linear function.
	 */
	@Override
	public String toString(){
		return a+"*x+"+b;
	}
}
