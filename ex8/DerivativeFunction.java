/**
 * file:DerivativeFunction.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex8 2011-2012 
 * description: This class represents a RealFunction that is the derivative of an other real function. 
 */
public class DerivativeFunction implements RealFunction{
	/**
	 * those two variables stores the two functions
	 */
	private RealFunction f;
	private double epsilon;
	
	/**
	 * Constructs a new DerivativeFunction object.
	 *  Numerical computation of the derivative is performed at each point,
	 *   epsilon is used to approximate the lim (f(x+t/2) - f(x-t/2))/t.
	 * @param f the function whose derivative is to be approximated
	 * @param epsilon the value used instead of t-->0 in the derivative formula.
	 */
	public DerivativeFunction(RealFunction f, double epsilon) {
		this.f = f;
		this.epsilon = epsilon;
	}
	
	/**
	 * returns the f(x) value of the RealFunction.
	 * @param x the x value given.
	 * @return the f(x) value of this function i.e. a*x + b.
	 */
	public double valueAt(double x) {
		return  (f.valueAt(x + epsilon/2) - f.valueAt(x- epsilon/2) ) / epsilon;
	}
	
	/**
	 * returns a String representation of the Linear function.
	 * @return a String representation of the Linear function.
	 */
	@Override
	public String toString(){
		return "("+f.toString()+")\' {" + epsilon + "}";
	}

}
