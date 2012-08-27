import intro.ex7.*;

/**
 * file:Hadamard.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex7 2011-2012 
 * description: A class defined especially for ex7.
 *  Contains a method that draws the Hadamard pattern (as defined in the ex7 description)
 *  on a BlackWhiteGrid. 
 */
public class Hadamard {
	/**
	 *Given the parameter n, creates an instance of BlackWhiteGrid
	 * and draws the (2^n)x(2^n) Hadamard pattern on it
	 * @param n log 2 of the Hadamard pattern size. 
	 * @return The created grid.
	 */
	public static BlackWhiteGrid paintPattern(int n) {
		BlackWhiteGrid grid = new BlackWhiteGrid();
		paintPatternRecursive(n,0,0,grid.getHeight(),false,grid);
		return grid;
	}
	
	/**
	 * paint the hadamard pattern on BlackWhiteGrid matrix
	 * @param depth the size of the pattern to draw
	 * @param topLeftX the top left corner x coordinate to start at
	 * @param topLeftY the top left corner y coordinate to start at
	 * @param size the size to paint on
	 * @param color a boolean represent the color of the painting
	 * @param matrix the BlackWhiteGrid to paint on
	 */
	private static void paintPatternRecursive(int depth,int topLeftX,int topLeftY, int size,
			boolean color, BlackWhiteGrid matrix) {
		
		if(depth == 0) {
			matrix.paintSquare(topLeftX, topLeftY, size, color);
			return;
		}
		
		int nSize = size/2, nDepth = depth -1;
		paintPatternRecursive(nDepth,topLeftX,topLeftY,nSize,color,matrix);
		paintPatternRecursive(nDepth,topLeftX+nSize,topLeftY,nSize,color,matrix);
		paintPatternRecursive(nDepth,topLeftX,topLeftY+nSize,nSize,color,matrix);
		
		//change the color of right bottom
		paintPatternRecursive(nDepth,topLeftX+nSize,topLeftY+nSize,nSize,!(color),matrix);
	}
}
