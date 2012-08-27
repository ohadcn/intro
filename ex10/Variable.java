import java.util.Map;

/**
 * file:Variable.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex10 2011-2012 
 * description:
 * The class Variable for ex. 10.
 * A Variable is represented by its name. The values of all the variables are held in a Map.
 */
public class Variable implements Expression{

	//holds the variable name
	private final String name;

    /**
    * Constructor that builds a Variable object.
    *  Variable name is given in variable name.
    * @param variableName the name of the variable.
    */
	public Variable(String variableName) {
		this.name = new String(variableName);
	}
	
    /**
    * Returns the value of the Variable.
    * @param env a Map used to evaluate unknown expressions.
    * @return the value of the Variable.
    * Double.NaN if env doesn't contains a value for this variable.
    */
	@Override
	public double eval(Map<String, Double> env) {
		Double value = env.get(name);
		
		return (value==null ? Double.NaN : value);
	}

    /**
    * String representation of the Variable.
    * @param order the operator order used to build the String (unused).
    * @return the Variable as String.
    */
	@Override
	public String toString(OpOrder order) {
		return name;
	}

}
