/**
 * file:Brick.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex6 2011-2012 
 * description: A Paddle class implements the paddle in the Breakout game  
 */



import java.awt.*;


public class Paddle {
    
    //the step size
    private final int step = 10;
    
    //the place of the paddle
    private int X,Y;
    
    //the size of the paddle
    private int height,width;
    
    //the color of the paddle
    private Color color;
    
    /**
     * Creates a new paddle 
     * @param x the X coordinate of upper left corner of the paddle
     * @param y the Y coordinate of upper left corner of the paddle
     * @param height the height of the paddle
     * @param width  the height of the paddle
     * @param color the color of the paddle
     */
    public Paddle(int x, int y, int height, int width, Color color) {
        this.X = x;
        this.Y = y;
        this.height = height;
        this.width = width;
        this.color = new Color(color.getRGB());
    }
    
    /**
     * Bounces the ball (if appropriate according to the exercise description).
     * If the ball is in such a position relative to the paddle that it should be bounced.
     * the method updates the ball so that its speed and direction reflect the effect of the bounce. 
     * @param ball the ball object 
     * @return true if the ball hit the paddle
     */
    public boolean bounceBall(Ball ball) {
        
        if(ball.getX()>=X-ball.getRadius() && ball.getX()<X+width+ball.getRadius() &&
                ball.getY()>=Y-ball.getRadius() && ball.getY()<Y &&
                ball.getDirection()>180) {
            
            //direct the ball to it's new direction
            ball.setDirection(360-ball.getDirection());
            return true;
        }
        return false;
    }
    
    /**
     * returns the height of the paddle 
     * @return the height of the paddle
     */
    public int getHeight() {
        return height;
    }

    /**
     * returns the width of the paddle 
     * @return the width of the paddle
     */
    public int getWidth() {
        return width;
    }

    /**
     * returns the X position of the paddles' top-left corner. 
     * @return the X position of the paddles' top-left corner.
     */
    public int getX() {
        return X;
    }
    
    /**
     * returns the Y position of the paddles' top-left corner. 
     * @return the Y position of the paddles' top-left corner.
     */
    public int getY() {
        return Y;
    }
    
    /**
     * Changes the paddle position by 10 to the left (down the X axis). 
     */
    public void moveLeft() {
        this.X-=step;
    }
    
    /**
     * Changes the paddle position by 10 to the right (up the X axis). 
     */
    public void moveRight() {
        this.X +=step;
    }
    
    /**
     * Draws the paddle 
     * @param g the graphics object associated with the window in which the paddle draws itself
     */
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(X, Y, width, height);
    }
}
