/**
 * file:SineFunction.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex8 2011-2012 
 * description: This class represents a sine Function (in radians). 
 */
public class SineFunction implements RealFunction{
	
	/**
	 * Constructs a new sine function.
	 *  The sine function value in x is defined as sin(x) where x is in radians.
	 */
	public SineFunction() {
		
	}
	
	/**
	 * returns the f(x) value of the sine function.
	 * @param x the angle in radians.
	 * @return the f(x) value of this function i.e. sin(x).
	 */
	public double valueAt(double x) {
		return Math.sin(x);
	}

	/**
	 * returns a String representation of the function.
	 * @return a String representation of the function.
	 */
	@Override
	public String toString(){
		return "sin(x)";
	}
	
}
