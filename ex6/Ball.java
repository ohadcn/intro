/**
 * file:Brick.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex6 2011-2012 
 * description: A Ball class implements a moving ball 
 */

import java.awt.*;
public class Ball {
    
    //the location of the ball
    private int X,Y;
    
    //the speed the ball moves
    private int speed;
    
    //the direction the ball move to
    private int direction;
    
    //the size of the ball
    private int radius;
    
    //the color of the ball
    private Color color;
    
    /**
     * Constructs a new ball. 
     * @param x the X coordinate of the center of the ball
     * @param y the Y coordinate of the center of the ball
     * @param speed the speed of the ball (should be non-negative)
     * @param direction the direction in which the ball moves.
     *  Direction is represented in degrees, e.g. a ball with direction 0 moves to the right,
     *  a ball with direction 270 moves down. direction may be ANY number, e.g 90 = 450 = -270.
     * @param radius the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int speed, int direction, int radius, Color color) {            
        this.X = x;
        this.Y = y;
        this.setDirection(direction);
        this.radius = radius;
        this.setSpeed(speed);
        this.color = new Color(color.getRGB());
    }
    
    /**
     * returns the direction of the ball
     * @return The direction returned is in the [0, 360) interval
     */
    public int getDirection() {
        return direction;
    }
    
    /**
     * returns the radius of the ball 
     * @return the radius of the ball
     */
    public int getRadius() {
        return radius;
    }
    
    /**
     * returns the speed of the ball 
     * @return the speed of the ball
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     * returns the X coordinate of the center of the ball 
     * @return the X coordinate of the center of the ball
     */
    public int getX() {
        return X;
    }
    
    /**
     * returns the Y coordinate of the center of the ball
     * @return the Y coordinate of the center of the ball
     */
    public int getY() {
        return Y;
    }
    
    /**
     * Draws the ball 
     * @param g the graphics object associated with the window in which the ball draws itself
     */
    public void paint(Graphics g) {
        g.setColor(color);
        
        //keep in mind that this method take the center
        //and the height and width of the circle, not the radius of it
        g.fillOval(X-radius, Y-radius, 2*radius+1, 2*radius+1);
    }
    
    /**
     * Sets a new direction for the ball 
     * @param direction the new direction Direction is represented in degrees,
     *  e.g. a ball with direction 0 moves to the right, a ball with direction 270 moves down.
     *  direction may be ANY number, e.g. 90 = 450 = -270.
     */
    public void setDirection(int direction) {
        this.direction = ((direction%360)+360)%360; //that way it always 0 <= direction < 360;
    }
    
    /**
     * Sets a new speed for the ball.
     *  If the given speed is larger than the radius, the method sets it to radius. 
     * @param speed the new speed.
     */
    public void setSpeed(int speed) {
        this.speed = Math.max(Math.min(speed, radius),2);
    }
    
    /**
     * A method used to inform the ball that a quantum of time has passed.
     *  The ball calculates its new position (based on its speed, direction and old position).
     */
    public void timeTick() {
        X += Math.round(getSpeed()*Math.cos(Math.PI*direction/180.0));
        Y -= Math.round(getSpeed()*Math.sin(Math.PI*direction/180.0));
    }
}
