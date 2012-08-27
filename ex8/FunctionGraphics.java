import java.awt.Graphics;

/**
 * file:FunctionGraphics.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex8 2011-2012 
 * description: This class graphically plots objects that implement the RealFunction interface. 
 */
public class FunctionGraphics {
	
	int size, startX, startY, endX, endY;
	
	/**
	 * Instantiates a new function graphics object.
	 * @param dimension the number of pixels (width and height) of the frame that instantiates this graph.
	 * @param minX the left boundary of the plot region.
	 * @param maxX the right boundary of the plot region.
	 * @param minY the bottom boundary of the plot region.
	 * @param maxY the top boundary of the plot region.
	 */
	public FunctionGraphics(int dimension,
            int minX,int maxX,
            int minY,int maxY) {
		
		size = dimension;
		startX = minX;
		startY = minY;
		endX = maxX;
		endY = maxY;
	}
	
	/**
	 * Draws a graph of a given RealFunction object.
	 * The function should be evaluated for each X value represented in the pixel grid.
	 * Note that the resulting Y values should be converted to integer pixel coordinates.
	 * @param function the function to draw
	 * @param g the Graphics object (of the calling Jframe)
	 */
	public void drawGraph(RealFunction function, Graphics g) {
		RealFunction equal = new LinearFunction(1, 0);
		drawGraph(equal, function, g);
	}
	
	/**
	 * Draws a graph of a parametric function defined by two RealFunction objects.
	 *  The first function f1 generates the x coordinates of the plot,
	 *  while the second function f2 generates the y coordinates. 
	 *  Both functions should be evaluated for each X value represented in the pixel grid,
	 *  thus producing a set of (X, Y) coordinates.
	 *  Note that these coordinates should be converted to integer pixel coordinates.
	 * @param f1 the x(t) function of the parametric function.
	 * @param f2 the y(t) function of the parametric function.
	 * @param g the Graphics object (of the calling Jframe)
	 */
	public void drawGraph(RealFunction f1, RealFunction f2, Graphics g) {
		int[] Xs = new int[size],
				  Ys = new int[size];
		double diff = diffX();
			
		double  t = startX;
		for(int i = 0;i<size;i++,t+=diff) {
			Xs[i] = numToPointX(f1.valueAt(t));
			Ys[i] = numToPointY(f2.valueAt(t));
		}
		g.drawPolyline(Xs, Ys, size);
	}
	
	/**
	 * returns the difference between two x values
	 * @return the difference between two x values.
	 */
	private double diffX() {
		return (endX-startX)/(double)(size-1);
	}
	
	/**
	 * returns the difference between two Y values
	 * @return the difference between two Y values.
	 */
	private double diffY() {
		return (endY-startY)/(double)(size-1);
	}
	
	/**
	 * this method gets a number and return the pixel of the X coordinate of it
	 * @param X the number who it's x coordinate you are looking for
	 * @return the pixel of the  number X
	 */
	private int numToPointX(double X) {
		return (int)((X-startX)/diffX());
	}

	/**
	 * this method gets a number and return the pixel of the Y coordinate of it
	 * @param Y the number who it's Y coordinate you are looking for
	 * @return the pixel of the  number Y
	 */
	private int numToPointY(double Y) {
		//the y coordinates of graphics starts from the top
		return (int)(size-((Y-startY)/diffY()));
	}
}
