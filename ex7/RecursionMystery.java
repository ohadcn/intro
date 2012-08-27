/**
 * file:RecursionMystery.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex7 2011-2012 
 * description: a class that implements two methods:
 * the first calculate Fibonacci sequence
 * and one who converts numbers to binary base
 */
public class RecursionMystery {
	
	/**
	 * this method returns the n'th number in Fibonacci sequence
	 * @param n the index of number in Fibonacci sequence
	 * @return the number at the n'th place in Fibonacci sequence
	 */
    public static int mystery1(int n){
    	if(n<=1)
    		return n;
    	return mystery1(n-1) + mystery1(n-2);
    }
    
    /**
     * this method creates an array of integers presenting the binary value of N
     * @param N the number to convert to binary
     * @param res the array for output
     */
    public static void mystery2(int N, int[] res){
    	
    	res[(int)(Math.log10(N)/Math.log10(2))] = N%2;
    	if(N<=1) 
    		return;
    	mystery2(N/2, res);
    }
    
}
