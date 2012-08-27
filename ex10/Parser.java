/**
 * file:Parser.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex10 2011-2012 
 * description:
 * this class holds some methods that parses arithmetical expressions to Expression classes
 */
public class Parser {

    /**
    * Parses a factor. A Factor can be one of the following:
    *  1) number; 2) variable; 3) expression in brackets.
    * @param scan the Tokenizer
    * @return Number object if the next element is number. 
    * Variable object if the next element is variable.
    * In case of expression in brackets it calls parseExpression
    * for the expression in brackets and returns the result. 
    * Returns null otherwise.
    */
    public static Expression parseFactor(Tokenizer scan) {
    	switch (scan.peek()) {
		case NUMBER:
			return new Number(Double.parseDouble(scan.nextElement()));
			
		case VARIABLE:
			return new Variable(scan.nextElement());
			
		case BRACKET:
			scan.nextElement(); //remove the bracket
			return parseExpression(scan);
			
		default:
			return null;
		}
    }
    
    /**
    * Parses an expression. An expression can be: 1)Term; 2) Expression+Term; 3)Expression-Term.
    * Calls parseTerm to get the first expression. 
    * If operations "+" or "-" are followed, it calls ParseTerm again to get the second expression
    * and creates an Operand object with the operand acts on the two obtained expressions. 
    * The process is repeated for every addition "+" or "-" operation.
    * @param scan the Tokenizer
    * @return the result of parseTerm if only one operand exist.
    * The operation result if more than one operand exist.
    */
    public static Expression parseExpression(Tokenizer scan) {
    	Expression result = parseTerm(scan);
    	
    	while(scan.peek() == ElementType.MINUS || scan.peek() == ElementType.PLUS) {
    		Expression exp = new Operator(scan.nextElement(), result, parseTerm(scan));
    		result = exp;
    	}
    	
    	//remove closing brackets, if exist
    	if(scan.peek() == ElementType.BRACKET)
    		scan.nextElement();
    	
    	return result;
    }
    
    /**
    * Parses a term. A term can be: 1)Factor; 2)Term*Factor; 3)Term/Factor.
    * Calls parseFactor to get the first expression.
    * If operations "*" or "/" are followed, it calls ParseFactor again to get the second expression 
    * and creates an Operator object with the operand acts on the two obtained expressions.
    * The process is repeated for every addition "*" or "/" operation.
    * @param scan the Tokenizer
    * @return the result of parseFactor if only one operand exist.
    * The operation result if more than one operand exist.
    */
    public static Expression parseTerm(Tokenizer scan) {
    	Expression result = parseFactor(scan);
    	
    	while(scan.peek() == ElementType.MUL || scan.peek() == ElementType.DIV) {
    		String operation = scan.nextElement();
    		result = new Operator(operation, result, parseFactor(scan));    		
    	}
    	
    	//remove closing brackets, if exist
    	if(scan.peek() == ElementType.BRACKET)
    		scan.nextElement();
    	
    	return result;    	
    }
}
