
/**
 * file:Complex.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex4 2011-2012 
 * description: This class is defined especially for ex4.
 * This class implements a Complex numbers and basic operations on them.
 * Notice that this class is immutable, once created
 */

public final class Complex {
	
	/**
	 * constant, EPSILON, is used when comparing double numbers.
	 *	2 double variables are considered equal if the distance between them is less than EPSILON. 
	 */
	public static final double EPSILON = 1.0E-6;

	/**
	 * those variables stores the real and the imaginary parts of the complex number
	 */
	private final double real,img;
	
	/**
	 * those constants sets what methods shell return when they get NULL as parameter
	 * NULLBOOL - the return code for boolean methods
	 * NULLOBJECT - the return code for Complex methods
	 */
	private static final boolean NULLBOOL = false;
	private static final Complex NULLOBJECT = (Complex)null;
	
	/**
	 * Constructs a complex number. 
	 * @param real The real part of the complex number.
	 * @param img The imaginary part of the complex number.
	 */
	Complex(double real, double img) {
		this.real=real;
		this.img=img;
	}
		
	/**
	 * Returns the real part of the complex number. 
	 * @return the real part of the complex number.
	 */
	public double getReal() {
		return real;
	}
	
	/**
	 * Returns the imaginary part of the complex number. 
	 * @return the imaginary part of the complex number. 
	 */
	public double getImg() {
		return img;
	}
	
	/**
	 * Returns the absolute value of the complex number. 
	 * @return the absolute value of the complex number.
	 */
	public double getAbsValue() {
		return Math.sqrt(Math.pow(real, 2)+ Math.pow(img,2));
	}
	
	/**
	 * the polar angle of the complex number.
	 * The returned angle should be between -pi (greater than) to pi (less equal than).
	 * -pi < getAngle() <= pi .
	 * @return The polar angle of this complex number, in the range of -pi to pi.
	 */
	public double getAngle() {
		return Math.atan2(img, real);
	}
	
	/**
	 * Compares this complex number to other and check if they are equal.
	 * @param other The other complex number to compare. 
	 * @return True if this equals to other, false otherwise.
	 *  (false for null input as well)
	 */
	public boolean equals(Complex other) {
		if (other==null)
			return NULLBOOL;
		
		//remember that the difference can be to both sides
		if((Math.abs(other.real-real)<EPSILON)&& 
				(Math.abs(other.img-img)<EPSILON))
			return true;
		
		return false;
	}
	
	/**
	 * Adds the other complex number to this complex number.
	 * The result is returned as a new complex number.
	 * Other and this complex numbers are not affected. 
	 * @param other The complex number to add. 
	 * @return The resulted complex number, null in case of null input.
	 */
	public Complex plus(Complex other) {
		if (other == null)
			return NULLOBJECT;
		return new Complex(real+other.real,img+other.img);
	}
	
	/**
	 * Subtracts the given complex number from this complex number.
	 * The result is returned as a new complex num.
	 * Other and this complex numbers are not affected. 
	 * @param other The complex number to subtract. 
	 * @return The resulted complex number, null in case of null input.
	 */
	public Complex minus(Complex other) {
		if (other == null)
			return NULLOBJECT;
		return new Complex(real-other.real,img-other.img);
	}
	
	/**
	 * Performs multiplication with the given complex number.
	 * The result is returned as a new complex num.
	 * @param other The complex number to perform the multiplication with. 
	 * @return The multiplication result (complex number), null in case of null input.
	 */
	public Complex mult(Complex other) {
		if (other == null)
			return NULLOBJECT;
		return new Complex(real*other.real-img*other.img,
				real*other.img+img*other.real);
	}
	
	/**
	 * Finds the square of this complex number. The result is returned as a new complex num. 
	 * @return The square of this complex number.
	 */
	public Complex square() {
		return this.mult(this);
	}
	
	/**
	 * Perform division with the given complex number.
	 * The result is returned in a new complex num.
	 * Assumes that division by zero will not be required. 
	 * @param other The divisor (complex number).
	 * @return The division result (complex number), null in case of null input.
	 */
	public Complex div(Complex other) {
		if (other == null)
			return NULLOBJECT;
		
		//calculating division is easier when using polar coordinates
		double abs = this.getAbsValue()/other.getAbsValue();
		double angle = this.getAngle() - other.getAngle();
		
		return fromPolar(abs,angle);
	}
	
	/**
	 * Finds the conjugate of this complex number. 
	 * @return The conjugate of this complex number.
	 */
	public Complex conjugate() {
		return new Complex(real,-img);
	}
	
	/**
	 * Finds the escape time of this complex number from the given Mandelbrot radius:
	 * The escape time is the index of the first element in the sequence (z_i) which is outside the circle with the radius given.
	 * Further explanations are found in ex4.
	 * If this complex number did not 'escaped' in given number of iteration we assume it is part of the Mandelbort set.
     * e.g. if maxIterations=100 the last element sequence to be checked is z_100.
	 * @param radius The Mandelbrot radius.
	 * @param maxIterations The maximum escape time to check. 
	 * @return
	 * The escape time of this complex number,
	 * for example if z_5 is the first sequence element outside the radius circle we will return 5.
	 * If this complex number did not escape in maxIteration return -1. 
	 */
	public int escapeTime(int radius,int maxIterations) {
		
		int iterations;
		Complex current=new Complex(0,0);
		for(iterations=1;iterations<=maxIterations;iterations++) {
			current=current.square().plus(this);
			if (current.getAbsValue()>=radius) {
				//we escaped from the Mandelbrot radius
				return iterations;
			}
		}
		return -1;
	}
	
	/**
	 * this method gets a complex number by polar coordinates
	 * and returns the same number in Cartesian coordinates
	 * this should be a contractor but i can't overload it
	 * @param abs the radius of the number
	 * @param angle the angle between the number and the real coordinate
	 * @return a new complex number
	 */
	public static Complex fromPolar(double abs,double angle) {
		return new Complex(abs*Math.cos(angle),abs*Math.sin(angle));
	}	
}
