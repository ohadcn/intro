
/**
 * file:Mandelbrot.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * The Mandelbrot class represents a rectangular region of the complex plane as a color image,
 *  where the color of each pixel (corresponding to a point in the complex plane)
 *  is determined according to the escape time of the complex point from the Mandelbrot radius
 *  (in ex5 this radius is set to 2).
 *  The complex numbers contained in each Mandelbrot object are determined according to the size of
 *  the rectangular region, its resolution and the complex number in its top left corner.
 *  The color in the corresponding image is determined according to the escape time
 *  of each complex number and a palette of colors.
 *  This palette is an array of RGBColors
 *  where the color in index 0 represents the color of numbers in the Mandelbrot radius
 *  and the colors of the other entries are the colors of complex numbers that "escaped" from the Mandelbrot set
 *  - where in entry i lies the color of complex numbers with escape time i.
 */

import intro.ex5.*;

public class Mandelbrot {
    
    /**
     * The radius used for calculating the escape time.
     */
    public static final int RADIUS = 2;
    
    /**
     * this store the image itself
     */
    private RGBImage image;
    
    /**
     * those store information needed to recalculate the image
     */
     // the complex number that represented by the top left corner of the image
    private Complex topLeftCorn;
    
    //those two store the difference between each two pixels
    //yStep in the imaginary part and xStep in the real part
    private double xStep,yStep;
    
    //this array store the colors used to build the image
    private RGBColor[] colors;
    
    /**
     * Creates a new Mandelbrot instance - a plane of complex numbers and a color image 
     * represnting it by the escape time from Mandelbrot set of each point in the plane.
     * @param rows The height of this Mandelbrot plane (number of complex nubmers).
     * @param cols The width of this Mandelbrot plane (number of complex nubmers).
     * @param topLeftCorner The complex number at the top left corner of the plane.
     * @param xStepSize The size of the change along the x (real) line.
     * @param yStepSize The size of the change along the y (imaginary) line.
     * @param maxIterations Max number of iterations to use when computing the escape
     *  from the Mandelbrot set.
     * @param palette The color assigned for each escape time:
     *  an array of size maxIterations,
     *   where the color in entry i (palate[i]) is the color for a complex number with escape time i
     *   and the color in the first entry (palate[0]) is the color of complex numbers that "escaped"
     *   from the Mandelbrot radius.
     */
    public Mandelbrot(int rows, int cols, Complex topLeftCorner,
            double xStepSize, double yStepSize,
            int maxIterations, RGBColor[] palette) {
        
        if(topLeftCorner==null||
                palette==null||palette.length!=maxIterations+1||palette[maxIterations]==null) {
            ErrorPrinter.error("error in Mandelbrot constractor");
        }
        topLeftCorn = new Complex(topLeftCorner.getReal(), topLeftCorner.getImg());
        xStep = xStepSize;
        yStep = yStepSize;
        colors = new RGBColor[palette.length];
        for(int i=0;i<palette.length;i++)
            colors[i] = new RGBColor(palette[i]);
        
        image = calcImage(rows, cols, topLeftCorn, xStep, yStep, colors);
    }
    
    /**
     * calculate the image, see contractor for more detailes
     * @param rows The height of this Mandelbrot plane (number of complex nubmers).
     * @param cols The width of this Mandelbrot plane (number of complex nubmers).
     * @param topLeft The complex number at the top left corner of the plane.
     * @param xStepSize The size of the change along the x (real) line.
     * @param yStepSize The size of the change along the y (imaginary) line.
     * @param maxIterations Max number of iterations to use when computing the escape
     *  from the Mandelbrot set.
     * @param palette The color assigned for each escape time:
     */
    private static RGBImage calcImage(int rows, int cols,
            Complex topLeft, double xStepSize, double yStepSize,
            RGBColor[] palette) {
        
        if(topLeft==null||
                palette==null||palette[0]==null) {
            ErrorPrinter.error("can't calculate image: invalid values");
            return null;
        }
        
        RGBImage newImage = new RGBImage(rows,cols);
        double y = topLeft.getImg();
        for(int  i=0;i<rows;i++,y-=yStepSize) {
            double x=topLeft.getReal();
            for( int j=0;j<cols;j++,x+=xStepSize) {
                Complex num = new Complex(x, y);
                int escape = num.escapeTime(RADIUS, palette.length-1);
                if (escape<0)
                    escape = 0;
                newImage.setPixel(i, j, palette[escape]);
            }
        }
        return newImage;
    }
    
    /**
     * Returns a color image representing this Mandelbrot plane. 
     * @return The image representing this Mandelbrot plane.
     */
    public RGBImage getImage() {
        return new RGBImage(image);
    }
    
    /**
     * Enlarge the resolution of this Mandelbrot object (magnify)
     *  and sets the center of the plane to be the complex number at the given row at column.
     *  The total number of complex numbers in this plane 
     *  (and thus the dimensions of the image) are not changed,
     *  only the resolution and range of covered numbers. 
     * @param newCenterRow The row of the new center.
     * @param newCenterCol The column of the new center.
     * @param magnificationFactor The factor to enlarge the resolution by.
     */
    public void magnify(int newCenterRow, int newCenterCol, int magnificationFactor) {
        
        if(magnificationFactor==0) 
            ErrorPrinter.error("can't magnify by zero");
         
        //keep in mind that changing the image is done by shift method
        yStep /=magnificationFactor;    //magnify
        xStep /=magnificationFactor;
        
        //set center and calc image
        this.shift(newCenterRow*magnificationFactor, newCenterCol*magnificationFactor
                ,image.getHeight()/2, image.getWidth()/2); 
        
    }
    
    /**
     * Shift this Mandelbrot plane such the source point (srcRow,srcCol) will move to (destRow,destCol).
     * @param srcRow Row of the origin point.
     * @param srcCol Column of the origin point.
     * @param destRow Row of the destination point.
     * @param destCol Column of the destination point.
     */
    public void shift(int srcRow, int srcCol, int destRow, int destCol) {
        
        double newReal = topLeftCorn.getReal() - (destCol-srcCol)*xStep,
                newImg = topLeftCorn.getImg() + (destRow-srcRow)*yStep;
        topLeftCorn = new Complex(newReal, newImg);
        image = calcImage(image.getHeight(), image.getWidth(), topLeftCorn, xStep, yStep, colors);        
    }
    
}
