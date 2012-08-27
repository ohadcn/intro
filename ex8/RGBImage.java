import java.util.Arrays;
import intro.ex5.*;
/**
 * This class represents a 2D color image, composed of RGB pixels.
 *  The row and column numbering start from the upper-left corner.
 *  The image is represented by a 2D array of RGBColor objects. 
 * @author ohad Cohen, ohadcn@cs.huji.ac.il
 *
 */
public class RGBImage {
    
    //this array store the pixels of the image
    private RGBColor image[][];
    
    //those numbers store the size of the array
    //i found it easier then calling array.length each time
    private int hight, width;
    
    //those are used by toIntArray, PARTS tell it how many colors construct each pixel
    private static final int RED=0, GREEN=1, BLUE=2, PARTS=3;
    
    /**
     * Construct a new black RGBImage with the given number of rows and columns.
     * All pixels in the image should be black (red = green = blue = 0).
     * Raises an error if the given rows or cols are not positive.
     * @param rows The width of the new image.
     * @param cols The height of the new image.
     */
    public RGBImage(int rows, int cols) {
        if(rows<=0||cols<=0)
            ErrorPrinter.error("call to RGBImage constractor with invalid values");
        
        hight=rows;
        width=cols;
        image=new RGBColor[hight][width];
        
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
                image[i][j] = new RGBColor();
        
    }
    
    /**
     * Construct a new RGBImage identical to the given array of pixels.
     * Assumes the given array is legal.
     * @param pixels The pixels of the new image.
     */
    public RGBImage(RGBColor[][] pixels) {
        
        hight=pixels.length;
        width=pixels[0].length;
        image=cloneArr(pixels);
    }
    
    /**
     * Construct a new RGBImage which is a copy of the given image.
     * Assumes the given image is not null. 
     * @param other The image to copy.
     */
    public RGBImage(RGBImage other) {
        this.hight=other.hight;
        this.width=other.width;
        this.image=other.toRGBColorArray();
    }
    
    /**
     * returns an array of the pixels in this image. 
     * @return An array of the pixels in this image.
     */
    public RGBColor[][] toRGBColorArray(){
        return cloneArr(image);
    }
    
    /**
     * Returns the grayscale representation of the image.
     * The grayscale representation of each pixel is calculated as defined in the API of RGBColor.
     * @return A 2d array of floats representing the image in grayscale values.
     */
    public float[][] toGrayscaleArray(){
        
        float[][] grayScale = new float[hight][width];
        
        for(int i=0;i<hight;i++)
            for(int j=0;j<width;j++)
                grayScale[i][j]=image[i][j].convertToGrayscale();
        
        return grayScale;
    }
    
    /**
     * Inverts the color of all pixels in this image,
     * by replacing each RGB value with its complement to 255.
     * For example, RGB values of [0,1,2] would be changed to [255,254,253].
     */
    public void invertColors() {
        
        for(int i=0;i<hight;i++)
            for(int j=0;j<width;j++)
                image[i][j].invert();
    }
    
    /**
     * Shifts the image down or up, according to the given offset.
     * Row 0 is moved into row offset, row 1 is moved into row offset+1, etc.
     * offset may be negative (or 0).
     * Any row that is shifted in from outside the image should be all black.
     * @param offset Offset to shift the image by.
     */
    public void shiftRow(int offset) {
        if(offset==0) //do nothing
            return;
        
        if(offset>0) {
            for(int i=hight-1;i>=offset;i--)
                image[i]=image[i-offset];//copy full rows
        
            for(int i=0;i<offset&&i<hight;i++) {
                image[i]=new RGBColor[width];
                for(int j=0;j<width;j++)
                    image[i][j]=new RGBColor();
            }
            return;
        }

        //offset<0
        if(offset+hight<0) //avoid getting exception on such case
            offset = -hight;
        
        for(int i=0;i<=hight+offset-1;i++)
            image[i]=image[i-offset];
        
        for(int i=hight+offset;i<hight;i++) {
            image[i]=new RGBColor[width];
            for(int j=0;j<width;j++)
                image[i][j]=new RGBColor();
        }

    }
    
    /**
     * Shifts the image left or right, according to the given offset.
     * Column 0 is moved into column offset, column 1 is moved into column offset+1, etc.
     * offset may be negative (or 0).
     * Any column that is shifted in from outside the image should be all black.
     * @param offset Offset to shift the image by.
     */
    public void shiftCol(int offset) {
        if(offset==0) //do nothing
            return;
        
        if(offset>0) {
            for(int i=0;i<hight;i++){
                for(int j=width-1;j>=offset;j--)
                    image[i][j] = image[i][j-offset];
                
                for(int j=0;j<offset&&j<width;j++)
                    image[i][j] = new RGBColor();
            }
            return;
        }
        
        //offset<0
        if(width+offset<0) //avoid getting exception on such case
            offset=-width;
        
        for(int i = 0; i<hight;i++) {
            for(int j=0;j<=width+offset-1;j++)
                image[i][j] = image[i][j-offset];
            
            for(int j=width+offset;j<width;j++)
                image[i][j] = new RGBColor();
        }
        
    }
    
    /**
     * Gets the pixel at the given coordinates.
     * Returns a black RGBColor if the given coordinates are outside the image.
     * @param row The row of the pixel to get.
     * @param col The column of the pixel to get.
     * @return The pixel at the given coordinates.
     */
    public RGBColor getPixel(int row, int col){
        try {
            return new RGBColor(image[row][col]);
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e) {
            return new RGBColor();
        }
    }
    
    /**
     * returns an array of the pixels in this image. 
     * @return An array of the pixels in this image.
     */
    public RGBColor[][] getPixels(){
        return this.toRGBColorArray();
    }
    
    /**
     * Sets the pixel at the given coordinates.
     * Raises an error if the given coordinates are outside the image. 
     * @param row The row of the pixel to set.
     * @param col The column of the pixel to set.
     * @param pixel Contains the RGB values to set at the given coordinates.
     */
    public void setPixel(int row, int col, RGBColor pixel) {
        if(pixel ==null) //i can't put that in the try..catch
            ErrorPrinter.error("call to setPixel with invalid values");
        try {
            image[row][col] = new RGBColor(pixel);
        }
        catch(java.lang.ArrayIndexOutOfBoundsException e) {
            ErrorPrinter.error("call to setPixel with invalid values");
        }
    }
    
    /**
     * Gets the height of the image in pixels.
     * @return The height of the image.
     */
    public int getHeight() {
        return hight;
    }
    
    /**
     * Gets the width of the image in pixels. 
     * @return The width of the image.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Flips the image around the vertical axis.
     * The first column becomes the last column, The second becomes second to last, etc.
     */
    public void flipHorizontal() {
        int middle = hight/2,odd = hight%2;
        
        // i can flip row at a time if i change the source row after that
        for(int i=0;i<middle;i++) {
            RGBColor temp[] = image[middle-i-1];
            image[middle-i-1]=image[middle+i+odd];
            image[middle+i+odd] = temp;
        }
    }
    
    /**
     * Flips the image around the horizontal axis.
     * The first row becomes the last row, The second becomes second to last, etc.
     */
    public void flipVertical() {
        int middle = width/2, odd = width%2;
        
        for(int i=0;i<hight;i++)
            for(int j=0;j<middle;j++) {
                RGBColor temp = image[i][middle-j-1];
                image[i][middle-j-1] = image[i][middle+j+odd];
                image[i][middle+j+odd] = temp;
            }
    }
    
    /**
     * Rotates the image 90 degrees counter-clockwise.
     * Note that this may change the dimensions of the image.
     */
    public void rotateCounterClockwise() {
        RGBColor[][] newImage = new RGBColor[width][hight];
        
        for(int i=0;i<hight;i++)
            for(int j=0;j<width;j++)
                newImage[width-j-1][i] = image[i][j];
        
        int temp = width;  //update width and hight
        width = hight;
        hight = temp;
        image = newImage;
    }
    
    /**
     * rotate the image 90 degrees clockwise.
     * Note that this may change the dimensions of the image.
     */
    public void rotateClockwise() {
        RGBColor[][] newImage = new RGBColor[width][hight];
        
        for(int i=0;i<hight;i++)
            for(int j=0;j<width;j++)
                newImage[j][hight-i-1] = image[i][j];
        
        int temp = width; //update width and hight
        width = hight;
        hight = temp;
        image = newImage;
    }
    
    /**
     * Blurs the image.
     * The blur operation replaces each of the pixel values of the image
     * with an average of the values in its surrounding neighborhood.
     * @param radius Radius of averaging.
     */
    public void blurImage(int radius) {
        if(radius<=0) {
            ErrorPrinter.error("can\'t blur zero or negative");
        }
        
        RGBColor newImage[][] = new RGBColor[hight][width];
        
        int num=(int)Math.pow(radius*2+1, 2); //number of pixels to blur to each pixel
        
        //for every pixel in image
        for(int i=0;i<hight;i++)
            for(int j=0;j<width;j++) {
                
                int sumRed =0, sumGreen=0, sumBlue = 0;
                //for any of the surrounding pixels
                for(int k=i-radius;k<=i+radius;k++)
                    for(int m=j-radius;m<=j+radius;m++) 
                        if((k>=0)&&(k<hight)&&(m>=0)&&(m<width)) { //verify that we are in the image
                            sumRed += image[k][m].getRed();
                            sumGreen += image[k][m].getGreen();
                            sumBlue += image[k][m].getBlue();
                    }//internal loop
                
                newImage[i][j]=new RGBColor(sumRed/num,sumGreen/num,sumBlue/num);
            }
        image = newImage;
    }
    
    /**
     * Gets an array of the color component values of this image's pixels.
     * The first index denotes the row, the second index denotes the column,
     * and the third index denotes the color component (red=0, green=1, blue=2).
     * @return An array containing the color component values of this image's pixels.
     */
    public int[][][] toIntArray(){
        int[][][] intArr = new int[hight][width][];
        for(int i=0;i<hight;i++)
            for(int j=0;j<width;j++) {
                intArr[i][j] = colorToArr(image[i][j]);
            }
        return intArr;
    }
    
    /**
     * Return true if the images are equal,
     * that is they have the same height and width,
     * and for each i and j in the legal ranges,
     * the Color in row i column j are equal.
     * @param other the other RGBImage to compare to
     * @return true if the images are equal
     */
    public boolean equals(RGBImage other) {
        if(other == null)
            ErrorPrinter.error("tring to compare image with null");
        
        if(other.hight!= this.hight || other.width != this.width)
            return false;
        
        for(int i = 0 ; i<hight ; i++)
            for(int j=0 ; j<width ; j++)
                if ( !image[i][j].equals(other.image[i][j]) )
                    return false;
        
        return true;
                        
    }
    
    /**
     * this method gets a RGBColor objects and convert it to int[] array
     * @param color the color to convert
     * @return an array of 3 int's, representing the color (red=0, green=1, blue=2)
     */
    private static int[] colorToArr(RGBColor color) {
        if(color==null) 
            ErrorPrinter.error("colorToArr failed:null");
        
        int[] arr = new int[PARTS];
        arr[RED] = color.getRed();
        arr[GREEN] = color.getGreen();
        arr[BLUE] = color.getBlue();
        return arr;
    }
    
    /**
     *  sorts the pixels in each row of this image.
     */
    public void sortRowsByGrayLevel() {
    	for(int i=0;i<hight;i++)
    		Arrays.sort(image[i]);
    }
    
    /**
     *  sorts the pixels in each column of this image.
     */
    public void sortColumnsByGrayLevel() {
    	this.rotateCounterClockwise();
    	this.sortRowsByGrayLevel();
    	this.rotateClockwise();
    }
    
    /**
     * this method return a copy of the given array
     * @param source the array to copy
     * @return an array of new RGBColors, equal to the given source
     */
    private static RGBColor[][] cloneArr(RGBColor[][] source){
    	
    	int hight = source.length,
    			width = source[0].length;
        RGBColor[][] newImage = new RGBColor[hight][width];
        
        for(int i=0;i<hight;i++)
            for(int j=0;j<width;j++)
                newImage[i][j]=new RGBColor(source[i][j]);
        
        return newImage;

    }
}
