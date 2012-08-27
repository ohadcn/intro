/**
 * file:Brick.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex6 2011-2012 
 * description: This class implements a Brick in the Breakout game.
 *  It's also used to represent the walls of room in the game. 
 */

import java.awt.*;


public class Brick {
    
    
    //the place of the brick
    private int X,Y,
        //the size of it
        height,width;
    
    //the color of the brick
    private Color color;
    
    //is the brick active?
    private boolean active = true;
    
    /**
     * Constructs a new brick that's active upon construction. 
     * @param x the X coordinate of the top left corner of the brick
     * @param y the Y coordinate of the top left corner of the brick
     * @param height the height of the brick
     * @param width the width of the brick
     * @param color the color of the brick
     */
    public Brick(int x, int y, int height, int width, Color color) {
        this.X = x;
        this.Y = y;
        this.height = height;
        this.width = width;
        this.color = new Color(color.getRGB());
    }
    
    /**
     * Constructs a new brick
     * @param x the X coordinate of the top left corner of the brick
     * @param y the Y coordinate of the top left corner of the brick
     * @param height the height of the brick
     * @param width the width of the brick
     * @param color the color of the brick
     * @param isActive does the brick start as active or not
     */
    public Brick(int x, int y, int height, int width, Color color, boolean isActive) {
        this(x, y, height, width, color);
        this.active = isActive;
    }
    
    /**
     * Bounces the ball if it hit one of this Brick's regions.
     * If the ball hit the bottom or top regions, changes the ball's direction to 360-"direction".
     * If the ball hit the right or left regions, changes the ball's direction to 180-"direction".
     * "direction" is the ball's current direction.
     * See regions definition in exercise 5 description.
     * If this Brick is inactive, this method won't do anything. 
     * @param ball the ball object
     * @return true if the ball hit one of the brick's regions and false otherwise
     */
    public boolean bounceBall(Ball ball) {    

        //if the brick is not active..
        if(!active)
            return false;

        //store some information needed frequently in this method
        //the direction and distance of the ball
        int dir = ball.getDirection(),
            left = X - ball.getX(),
            right = ball.getX() - X - width + 1 ,
            up = Y -  ball.getY(),
            down = ball.getY() - Y - height + 1;
        
        //exit if the ball is far away from the brick
        if(Math.max(Math.max(up, down),Math.max(left, right)) > ball.getRadius())
            return false;
        
        //is the ball near the brick? from what side?
        boolean inLeft = left > 0 && (dir>270 || dir<90),
                inRight = right > 0 && dir<270 && dir>90,
                inUp = up > 0 && dir > 180,
                inDown = down > 0 && dir < 180;
        
        if(inUp || inDown) {
            int cor = Math.abs((inUp?up:down));
            if((inRight && right < cor) || 
                    (inLeft && left < cor)) {
                dir+=180; //raise it by 180 so the setDirection call will set it to 180-dir
            }
            ball.setDirection(360-dir);
            return true;            
        }
        
        if(inRight || inLeft) {
            ball.setDirection(180-dir);
            return true;
        }
        
        return false;
    }
    
    /**
     * Returns whether this Brick is active or not. 
     * @return true if this Brick is active.
     */
    public boolean getActive() {
        return this.active;
    }
    
    /**
     * returns this Brick's height. 
     * @return this Brick's height.
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * returns this Brick's width. 
     * @return this Brick's width.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * returns the X coordinate of this brick's top-left corner. 
     * @return X coordinate of this brick's top-left corner.
     */
    public int getX() {
        return X;
    }
    
    /**
     * returns the Y coordinate of this brick's top-left corner. 
     * @return Y coordinate of this brick's top-left corner.
     */
    public int getY() {
        return Y;
    }
    
    /**
     * Draws the brick
     * If this Brick is inactive, this method won't do anything. 
     * @param g the graphics object associated with the window in which the brick draws itself
     */
    public void paint(Graphics g) {        
        if(active) {
            g.setColor(color);
            g.fillRect(X, Y, width, height);
        }
    }
    
    /**
     * Sets brick's activity to the given value. 
     * @param isActive true if setting the brick to active false for inactive.
     */
    public void setActive(boolean isActive) {
        this.active = isActive;
    }
}
