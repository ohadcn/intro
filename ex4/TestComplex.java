
/**
 * file:TestComplex.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex4 2011-2012 
 * description: This class is defined especially for ex4.
 * Complex class implements a Complex numbers and basic operations on them.
 * this class test that the Complex class is working well
 */

public class TestComplex {
	
	
	/**
	 * those are the testing numbers
	 * keep in mind that if you add number you need to add to both
	 * and keep the arrays the same length
	 * make sure you don't put the same sort of numbers twice
	 * otherwise the equals test will not work
	 */
	private static final double[] reals = {0  ,3433.2323,   0, -23, -2.5, Complex.EPSILON},
								  imgs  = {0  ,15.6768  , 123, -56, 45.8, Math.E};

	/**
	 * those are the testing numbers for escapeTimeTest 
	 * keep the arrays same length
	 */	
	private final static int[] radiuses   = {0, 1, 2, 60, 367,},
			   				   iterations = {0,1, 2, 36,100};

	
	/**
	 * this little program check that the Complex class is working fine
	 * if there is a problem with the class it print a short message to sysout
	 * otherwise it do nothing
	 */
	public static void main(String[] args) {
		if(!complexTest()) {
			System.out.println("problem in Complex class constructor or in get methods\n"+
					"can't continue");
			return;
		}
				
		if(!getAbsAngleTest()) {
			System.out.println("problem in Complex class getAbs or getAngle methods");
		}
		
		if(!nullBehavior()) {
			System.out.println("this class ignore null behavior");
		}
		
		if(!equalsTest()) {
			System.out.println("problem in Complex class equals method");
		}
		
		if(!plusTest()) {
			System.out.println("problem in Complex class plus method");
		}
		
		if(!minusTest()) {
			System.out.println("problem in Complex class minus method");
		}
		
		if(!multTest()) {
			System.out.println("problem in Complex class mult method");
		}
		
		if(!squareTest()) {
			System.out.println("problem in Complex class square method");
		}
		
		if(!divTest()) {
			System.out.println("problem in Complex class div method");
		}
		
		if(!conjugateTest()) {
			System.out.println("problem in Complex class conjugate method");
		}
		
		if(!escapeTimeTest()) {
			System.out.println("problem in Complex class escapeTime method");
		}
		
	}
	
	/**
	 * this method tests the Complex() constructor, getImg and getReal methods
	 * @return true if it is good, false otherwise
	 */

	private static boolean complexTest() {
		for(int i=0;i<reals.length;i++) {
			Complex num = new Complex(reals[i], imgs[i]);
			if (num.getReal()!=reals[i]||num.getImg()!=imgs[i])
				return false;
		}
		return true;
	}

	/**
	 * this method tests the Complex getAbs and getAngle methods
	 * @return true it is are good, false otherwise
	 */
	private static boolean getAbsAngleTest() {
		
		//to test the class with many combination of zeroes we need two loops
		for(int j=0;j<imgs.length;j++) 
			for(int i=0;i<reals.length;i++) {

				Complex num = new Complex(reals[i], imgs[j]);
				if (Math.abs(num.getAbsValue()-
						Math.sqrt(Math.pow(reals[i], 2)+Math.pow(imgs[j], 2)))>Complex.EPSILON||
						Math.abs(num.getAngle()-Math.atan2(imgs[j], reals[i]))>Complex.EPSILON)
					return false;
			}
		return true;
	}
	
	/**
	 * this method tests the Complex equals method
	 * @return true it is are good, false otherwise
	 */
	private static boolean equalsTest() {

		//to avoid computing the numbers again and again
		Complex testing[] = new Complex[reals.length];
		for(int i=0;i<reals.length;i++) {
			testing[i] = new Complex(reals[i],imgs[i]);
		}
		
		//test each pair of numbers
		for(int i=0;i<reals.length;i++)
			for(int j=0;j<reals.length;j++)
				if (testing[i].equals(testing[j])//the tested class say they are the same
						^(i==j)) //but they are not
					return false;
		
		
		//make sure the class uses EPSILON as difference
		Complex zero=new Complex(0,0);
		Complex epsilon = new Complex(Complex.EPSILON,-Complex.EPSILON);
		if(zero.equals(epsilon))
			return false;
		return true;
		
	}

	/**
	 * this method tests the Complex plus method
	 * @return true it is are good, false otherwise
	 */
	private static boolean plusTest() {

		//to avoid computing the numbers again and again
		Complex testing[] = new Complex[reals.length];
		for(int i=0;i<reals.length;i++) {
			testing[i] = new Complex(reals[i],imgs[i]);
		}
		
		//test each pair of numbers
		for(int i=0;i<reals.length;i++)
			for(int j=0;j<reals.length;j++) {
				Complex num = testing[i].plus(testing[j]);
				if(num.getReal()!=reals[i]+reals[j]||
						num.getImg()!=imgs[i]+imgs[j])
					return false;
			}
				
		return true;
		
	}

	/**
	 * this method tests the Complex minus method
	 * @return true it is are good, false otherwise
	 */	
	private static boolean minusTest() {

		//to avoid computing the numbers again and again
		Complex testing[] = new Complex[reals.length];
		for(int i=0;i<reals.length;i++) {
			testing[i] = new Complex(reals[i],imgs[i]);
		}
		
		//test each pair of numbers
		for(int i=0;i<reals.length;i++)
			for(int j=0;j<reals.length;j++) {
				Complex num = testing[i].minus(testing[j]);
				if(num.getReal()!=reals[i]-reals[j]||
						num.getImg()!=imgs[i]-imgs[j])
					return false;
			}
				
		return true;
	}
	
	/**
	 * this method tests the Complex mult method
	 * @return true it is are good, false otherwise
	 */
	private static boolean multTest() {
		
		//to avoid computing the numbers again and again
		Complex testing[] = new Complex[reals.length];
		for(int i=0;i<reals.length;i++) {
			testing[i] = new Complex(reals[i],imgs[i]);
		}
		
		//test each pair of numbers
		for(int i=0;i<reals.length;i++)
			for(int j=0;j<reals.length;j++) {
				Complex num = testing[i].mult(testing[j]);
				if(num.getReal()!=reals[i]*reals[j]-imgs[i]*imgs[j]||
						num.getImg()!=reals[j]*imgs[i]+reals[i]*imgs[j])
					return false;
			}
				
		return true;
		
	}

	/**
	 * this method tests the Complex square method
	 * @return true it is are good, false otherwise
	 */
	private static boolean squareTest() {
		for(int i=0;i<imgs.length;i++) {
			Complex num = new Complex(reals[i],imgs[i]);
			if(!num.square().equals(num.mult(num)))
				return false;
		}
		return true;
	}

	/**
	 * this method tests the Complex div method
	 * @return true it is are good, false otherwise
	 */
	private static boolean divTest() {
		Complex testing[] = new Complex[reals.length];
		
		//to avoid computing the numbers again and again
		for(int i=0;i<reals.length;i++) {
			testing[i] = new Complex(reals[i],imgs[i]);
		}
		
		//for every combination of two numbers do:
		for(int i=0;i<testing.length;i++)
			for(int j=0;j<testing.length;j++) {
				
				//avoid dividing by zero
				if(testing[j].getAbsValue()==0)
					continue;
				
				Complex num = testing[i].div(testing[j]);
				//multiple the division and the divisor, to get the divided number
				if(!testing[j].mult(num).equals(testing[i])) {
					return false;
				}
			}
		
		return true;
	}

	/**
	 * this method tests the Complex conjugate method
	 * @return true it is are good, false otherwise
	 */
	private static boolean conjugateTest() {
		for(int i=0;i<reals.length;i++) {
			Complex num = new Complex(reals[i],imgs[i]),
					cojugated = num.conjugate();
			if(num.getReal()!=cojugated.getReal()||
					num.getImg()!=-cojugated.getImg())
				return false;
			
		}

		return true;
	}

	/**
	 * this method tests the Complex escapeTime method
	 * @return true it is are good, false otherwise
	 */
	private static boolean escapeTimeTest() {

		//do the tests for every complex number
		for(int i=0;i<imgs.length;i++) {
			Complex num = new Complex(reals[i],imgs[i]);
			
			//test for every combination of radius/iterations
			for(int j=0;j<iterations.length;j++) {
				int iter=num.escapeTime(radiuses[j], iterations[j]);
				
				//compute the iterations
				Complex current=new Complex(0,0);
				boolean outRadius = false; //the set escaped the radius
				for(int l=1;l<=iterations[j];l++) {
					current=current.square().plus(num); //next complex in set
					if (current.getAbsValue()>=radiuses[j]) {
						if(l!=iter) {
							return false;
						}
						else {
							outRadius = true;
							break;//the tested class calculation is right
						}
					}
				}
				
				//if we got here the set not getting out of the radius
				//or the class output is right
				if(iter!=-1&&!outRadius) {
					return false;
				}
			}
		}
		
		//Congratulations!
		return true;
	}
	
	/**
	 * this method checks if Complex class behave
	 * normally when it's methods gets null as parameter
	 * @return true in case of normal behavior (look in the API)
	 * or false otherwise
	 */
	private static boolean nullBehavior() {
		try {
			Complex num  = new Complex(0,0);
			
			//the class shell return false for null in it's boolean methods
			if(num.equals(null))
				return false;
			
			//the class shell return null for null in it's Complex methods
			if(num.div(null)!=null||
					num.minus(null)!=null||
					num.mult(null)!=null||
					num.plus(null)!=null)
				return false;
		}
		catch(java.lang.NullPointerException e) { //we shell not get exceptions at any time
			return false;
		}
		return true;
	}
}
