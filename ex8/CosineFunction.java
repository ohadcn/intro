/**
 * file:CosineFunction.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex8 2011-2012 
 * description: This class represents a Cosine Function (in radians). 
 */
public class CosineFunction implements RealFunction{
	/**
	 * Constructs a new cosine function.
	 *  The cosine function value in x is defined as cos(x) where x is in radians.
	 */
	public CosineFunction() {
		
	}
	
	/**
	 * returns the f(x) value of the cosine function.
	 * @param x the angle in radians.
	 * @return the f(x) value of this function i.e. cos(x).
	 */
	public double valueAt(double x) {
		return Math.cos(x);
	}

	/**
	 * returns a String representation of the function.
	 * @return a String representation of the function.
	 */
	@Override
	public String toString(){
		return "cos(x)";
	}
	

}
