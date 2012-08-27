/**
 * file:CompositeFunction.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex8 2011-2012 
 * description: This class represents a RealFunction that is the composition of the two functions
 * i.e. f(x) = f1(f2(x))
 */
public class CompositeFunction implements RealFunction{

	/**
	 * those two variables stores the two functions
	 */
	private RealFunction f1,f2;
	
	/**
	 * Constructs a new CompositeFunction object that is the composition
	 *  of the 1st function on the 2nd. 
	 * @param f1 the first function used.
	 * @param f2 the second function used.
	 */
	public CompositeFunction(RealFunction f1, RealFunction f2) {
		this.f1 = f1;
		this.f2 = f2;
	}
	
	/**
	 * returns the f(x) value of the composite function  
	 * @param x the x value given.
	 * @return the f(x) value of this function i.e. f(x)= f1(f2(x))
	 */
	public double valueAt(double x) {
		return f1.valueAt(f2.valueAt(x));
	}
	
	/**
	 * returns a String representation of the function.
	 * @return a String representation of the composite function. 
	 */
	@Override
	public String toString(){
		String f1str = f1.toString();
		if(f1str.contains("x"))
			return f1str.replace("x", f2.toString());
		return f1str + "<-(" + f2.toString() + ")";
	}

}
