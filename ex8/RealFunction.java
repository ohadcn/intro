/**
 * file:RealFunction.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex8 2011-2012 
 * description: A interface defined especially for ex8.
 * An interface that represents classes that are real valued functions.
 */
public interface RealFunction {
	
	/**
	 * returns the f(x) value of the RealFunction.
	 * @param x the x value given.
	 * @return the f(x) value of this function.
	 */
	public double valueAt(double x);
	
	/**
	 * returns a String representation of the function. 
	 * @return a String representation of the function. 
	 */
	@Override
	public String toString();
}
