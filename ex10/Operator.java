import java.util.Map;
/**
 * file:Operator.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex10 2011-2012 
 * description:
 * The class Operator for ex. 10. 
 * An operator can be one of 4 basic mathematical operators (+,-,*,/), 
 * and holds the two expressions that it operates on.
 */
public class Operator implements Expression{
	
	//this values used to choose an operation
	private static final char PLUS = '+';
	private static final char MINUS = '-';
	private static final char MULTIPLE = '*';
	private static final char DIVIDE = '/';
	
	//the operation to do
	private final char operator;
	//the two expressions to calculate
	private final Expression left, right;
	
	/**
	 * Constructor that builds an Operator object.
	 * Gets the operator symbol (+,-,*,/) and the two expressions that it operates on. 
	 * @param operator String representation of the operation.
	 * @param left The left expression.
	 * @param right The right expression.
	 */
	public Operator(String operator, Expression left, Expression right) {
		this.operator = operator.charAt(0);
		this.left = left;
		this.right = right;
	}
	
    /**
    * Returns the value of the Operator.
    * @param env a Map used to evaluate unknown expressions.
    * @return the value of the Operator.
    */
	@Override
	public double eval(Map<String, Double> env) {
		switch (operator) {
		case PLUS:
			return left.eval(env) + right.eval(env);

		case MINUS:
			return left.eval(env)-right.eval(env);

		case MULTIPLE:
			return left.eval(env)*right.eval(env);

		case DIVIDE:
			return left.eval(env)/right.eval(env);
			
		} 
		return 0;
	}

    /**
    * String representation of the Operator.
    * @param order the operator order used to build the String.
    * @return the Operator as String.
    */
	@Override
	public String toString(OpOrder order) {
		switch (order) {
		case INFIX:
			return "("+left.toString(order) + operator + right.toString(order)+")";

		case POSTFIX:
			return left.toString(order) + " " + right.toString(order) + " " + operator;
			
		case PREFIX:
			return operator + " " + left.toString(order) + " " + right.toString(order);
			
		}
		return null;
	}

}
