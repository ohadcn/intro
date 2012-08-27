/**
 * file:TestRGBColor.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex4 2011-2012 
 * description: This class is defined especially for ex4.
 * RGBColor class represents a color
 * this class test that the RGBColor class is working well
 * 
 */

public class TestRGBColor {

	/**
	 * those are the testing numbers
	 * keep in mind that if you change one you need to change them all
	 * to keep them the same length
	 * make sure you don't put the same sort of numbers twice
	 * otherwise the equals test will not work
	 */
	private final static int[] red   = {0, 255, 0,   36},
							   green = {0, 255, 200, 255},
							   blue  = {0, 255, 34,  0};
	
	/**
	 * this little program check that the RGBColor class is working fine
	 * if there is a problem with the class it print a short message to sysout
	 * otherwise it do nothing
	 */
	public static void main(String[] args) {
		if (!(RGBColor3Test() && RGBColor1Test() && RGBColor0Test())) {
			System.out.println("problem in RGBColor class constructor");
		}
		if(!getSetTest()) {
			System.out.println("problem in RGBColor class get\'s / set\'s methods");
		}
		if (!invertTest()) {
			System.out.println("problem in RGBColor invert method");
		}
		
		if(!mixTest()) {
			System.out.println("problem in RGBColor mix method");
		}
		if(!convertToGrayscaleTest()) {
			System.out.println("problem in RGBColor convertToGrayscale method");
		}
		if(!equalsTest()) {
			System.out.println("problem in RGBColor equals method");
		}
	}

	
	/**
	 * this method tests the RGBColor() constructor
	 * @return true if it is good, false otherwise
	 */
	private static boolean RGBColor3Test() {
		
		for(int i=0;i<red.length;i++) {
			RGBColor testing = new RGBColor(red[i],green[i],blue[i]);
			if (testing.getRed()!=red[i]||testing.getGreen()!=green[i]||testing.getBlue()!=blue[i])
				return false;
		}
		return true;
	}

	/**
	 * this method tests the RGBColor equals method
	 * @return true it is are good, false otherwise
	 */
	private static boolean equalsTest() {
		
		//to avoid calculating the colors again and again
		RGBColor testing[] = new RGBColor[red.length];
		for(int i=0;i<red.length;i++) {
			testing[i] = new RGBColor(red[i],green[i],blue[i]);
		}
		
		//test any pairs of colors
		for(int i=0;i<red.length;i++)
			for(int j=0;j<red.length;j++)
				if (testing[i].equals(testing[j])//the tested class say they are the same
						^(i==j)) //but they are not
					return false;
		
		return true;
	}

	/**
	 * this method tests the RGBColor convertToGrayscale method
	 * @return true it is are good, false otherwise
	 */
	private static boolean convertToGrayscaleTest() {

		//the output for each one
		float[] output= new float[red.length];
		for(int i=0;i<red.length;i++) {
			output[i] = (float) (red[i]*.3 + green[i]*.59 + blue[i]*.11);
		}
		for(int i=0;i<red.length;i++) {
			RGBColor tested = new RGBColor(red[i],green[i],blue[i]);
			if(tested.convertToGrayscale()!=output[i]) {
				return false;
			}
		}
		return true;
		
	}

	
	/**
	 * this method tests the RGBColor mix method
	 * @return true it is are good, false otherwise
	 */
	private static boolean mixTest() {
		
		//test any pair of colors
		for(int i=0;i<red.length;i++)
			for(int j=0;j<red.length;j++) {
				
				//create two colors
				RGBColor one = new RGBColor(red[i],green[i],blue[i]),
						two = new RGBColor(red[j],green[j],blue[j]);
				
				one.mix(two);
				if(one.getRed()!=(red[i]+red[j])/2 ||
						one.getGreen()!=(green[i]+green[j])/2 ||
						one.getBlue()!=(blue[i]+blue[j])/2) {
					
					return false;
				}
				
				//check what happens when mixing a color with itself
				two.mix(two);
				if(two.getRed()!=red[j]||
						two.getGreen()!=green[j]||
						two.getBlue()!=blue[j])
					return false;
			
			}
		
		return true;
	}

	
	/**
	 * this method tests the RGBColor invert method
	 * @return true it is are good, false otherwise
	 */
	private static boolean invertTest() {
		
		for(int i=0;i<red.length;i++) {
			RGBColor test = new RGBColor(red[i],green[i],blue[i]);
			test.invert();
			if(test.getRed()!=(255-red[i])||
					test.getGreen()!=(255-green[i])||
					test.getBlue()!=(255-blue[i]))
				return false;
		}
		return true;
		
	}

	/**
	 * this method tests the RGBColor get/set methods
	 * @return true if they are good, false otherwise
	 */
	private static boolean getSetTest() {
		
		RGBColor test = new RGBColor(red[0],green[0],blue[0]);
		for(int i=1;i<red.length;i++) {
			
			//change any part of the color and verify that it and only it changed
			test.setRed(red[i]);
			if(test.getRed()!=red[i]||
					test.getGreen()!=green[i-1]||
					test.getBlue()!=blue[i-1])
				return false;
			
			test.setGreen(green[i]);
			if(test.getRed()!=red[i]||
					test.getGreen()!=green[i]||
					test.getBlue()!=blue[i-1])
				return false;
			
			test.setBlue(blue[i]);
			if(test.getRed()!=red[i]||
					test.getGreen()!=green[i]||
					test.getBlue()!=blue[i])
				return false;
		}
		return true;
	}

	
	/**
	 * this method tests the RGBColor() constructor
	 * @return true if it is good, false otherwise
	 */
	private static boolean RGBColor0Test() {
		RGBColor test = new RGBColor();
		
		//gray value is zero only for black
		if(test.convertToGrayscale()!=0)
			return false;
		
		return true;
		
	}

	/**
	 * this method tests the RGBColor() constructor
	 * @return true if it is good, false otherwise
	 */
	private static boolean RGBColor1Test() {
		for(int i=0;i<red.length;i++) {
			RGBColor source = new RGBColor(red[i],green[i],blue[i]);
			RGBColor target = new RGBColor(source);
			if(!target.equals(source)) {
				return false;
			}
			
			//make sure he didn't just point to the old class
			//this test must be done before the next one
			source.invert();
			if(source.equals(target))
				return false;

			//make sure the source hasn't changed
			source = new RGBColor(red[i],green[i],blue[i]);
			if(!target.equals(source)) {
				return false;
			}

		}
		return true;
	}

}
