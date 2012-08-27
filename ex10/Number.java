import java.util.Map;

/**
 * file:Number.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex10 2011-2012 
 * description: The class Number for ex. 10.
 * Represents a number that has a double value. 
 */
public class Number implements Expression {

	private final double value;

	/**
	 * Constructor that builds a Number object. Gets and stores the value of the number.
	 * @param value the value of the number.
	 */
	public Number(double value) {
		this.value = value;
	}
	
    /**
    * Returns the value of the number.
    * The Map parameter is for uniformity purposes and is not used here.
    * @param env a Map used to evaluate unknown expressions (unused).
    * @return the value of the number.
    */
	@Override
	public double eval(Map<String, Double> env) {
		return value;
	}

    /**
    * String representation of the number.
    * @param order the operator order used to build the String.
    * @return the number as String (regardless of the order).
    */
    @Override
	public String toString(OpOrder order) {
		return String.valueOf(value);
	}

}
