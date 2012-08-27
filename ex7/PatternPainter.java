import intro.ex7.*;

/**
 * file:PatternPainter.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex7 2011-2012 
 * description: A class defined especially for ex7.
 *  Contains a method that draws the (2^n)x(2^n) left corner
 *  of the (2^(n+1))x(2^(n+1)) pattern defined in ex7 on a BlackWhiteGrid. 
 */
public class PatternPainter {
	
	/**
	 * Given the parameter n, creates an instance of BlackWhiteGrid
	 *  and draws on it the (2^n)x(2^n) left corner of the (2^(n+1))x(2^(n+1)) pattern defined in ex7
	 * @param n log of the PatternPainter pattern size.
	 * @return The created grid.
	 */
	public static BlackWhiteGrid paintPattern(int n) {
		
		BlackWhiteGrid grid = new BlackWhiteGrid();
		paintPatternRecursive(n+1,0,0,grid.getHeight(),false,true,true,grid);
		return grid;
	}
	
	/**
	 * draw a corner of the extended pattern as defined in ex7
	 * @param depth the depth to go in
	 * @param topLeftX the top left x coordinate of the pattern
	 * @param topLeftY the top left y coordinate of the pattern
	 * @param size the size of the pattern to draw
	 * @param color a boolean represent the color of the painting
	 * @param isLeft is this corner in the the left?
	 * @param isTop is this corner in the top?
	 * @param matrix the BlackWhiteGrid to paint on
	 */
	private static void paintPatternRecursive(int depth,int topLeftX,int topLeftY, int size,
			boolean color,boolean isLeft,Boolean isTop, BlackWhiteGrid matrix) {
		
		if(depth == 1) {
			matrix.paintSquare(topLeftX, topLeftY, size, color);
			return;
		}
		
		//some calculations needed commonly in this method
		int nSize = size/2, nDepth = depth -1;
		
		//for each corner the method find if it's in the opposite position to the current
		//corner and then xoring it with that boolean value, as xoring with true change booleans
		
		//the top left corner
		paintPatternRecursive(nDepth, topLeftX, topLeftY, nSize,
				(!(isLeft||isTop))^color, true, true,  matrix);

		//the top right
		paintPatternRecursive(nDepth, topLeftX+nSize, topLeftY, nSize,
				(isLeft&&(!isTop))^color, false, true, matrix);
		
		//lower left
		paintPatternRecursive(nDepth, topLeftX, topLeftY+nSize, nSize,
				((!isLeft)&&isTop)^color, true, false, matrix);
		
		//lower right
		paintPatternRecursive(nDepth, topLeftX+nSize, topLeftY+nSize, nSize,
				(isLeft&&isTop)^color, false, false, matrix);
	}
}
