/**
 * file:HelloTurtle.java
 * @author Ohad Cohen, ohad.cohen6@mail.huji.ac.il
 * exercise : intro2cs ex1 2011-2012 
 * description: a simple program that draw a triangular prism and a ray of light that 
 * got broken in it.
 */
import intro.ex1.*;

public class HelloTurtle {
	
/*
 * 
 * this function draw lines of different colors 
 * all the lines started at the same point (where the turtle is when the function called)
 * all the lines have the width and length specified and the angle between them as specified
 * the function returns 0 in case of success,1 when turtle is not initialized or 2 when it gets no colors
 * when finished drawing, the turtle will be at the same place but the other states as they was
 * cause there is no way to get them...
 */
	public static int lines(IntroTurtle turtle,int width,int length,int angle,int[] colors)
	{
		//some checks before we draw
		if (turtle == null)
			return 1;
		if (colors.length == 0)
				return 2;
		
		//to avoid recalculating...
		//java sin() function get radiant, no degrees, so i needed to convert
		int sz = (int) Math.round(length*Math.sin(Math.PI*angle/360)*2), angl = 90+(angle)/2 ,angl2 = 180-angle;
		turtle.setPenSize(width);
		turtle.penDown();
		for(int i=0;i<colors.length;i++){
			turtle.setPenColor(colors[i]);
			turtle.forward(length);
			turtle.penUp();
			i++;
			if (i == colors.length)
			{
				//the end of the array
				turtle.back(length);
				break;
			}
			turtle.right(angl);
			turtle.forward(sz);
			turtle.right(angl);
			turtle.penDown();
			turtle.setPenColor(colors[i]);
			turtle.forward(length);
			turtle.left(angl2);
		}
		//return success code
		return 0;
	}
	
	//program entry point
	public static void main(String[] args) {
		IntroTurtle Udinka = new IntroTurtle();
		int[] Colors = {4,14,6,2,1,13}; //to change the colors or their order, just edit this
		
		Udinka.hideTurtle();

		//the triangle
		Udinka.left(90);
		Udinka.penDown();
		Udinka.forward(100);
		Udinka.right(120);
		Udinka.forward(200);
		Udinka.right(120);
		Udinka.forward(200);
		Udinka.right(120);
		Udinka.forward(100);
		Udinka.right(60);
		
		//the light ray
		Udinka.penUp();
		Udinka.forward(100);
		Udinka.penDown();
		Udinka.left(75);
		Udinka.forward(120);
		Udinka.penUp();
		Udinka.left(173);
		Udinka.forward(218);
		Udinka.right(37);
		
		//the rainbow :)
		lines(Udinka,5,120,2,Colors);
	}

}
