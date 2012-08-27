/**
 * file:Brick.java
 * @author Ohad Cohen, ohadcn@cs.huji.ac.il
 * exercise : intro2cs ex6 2011-2012 
 * description: This class implements a Breakout game 
 */


import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Game {

    /**
     * numeric value represent the left key for Game.handleMoveEvent() method
     */
    public static final int LEFT = 0;
    /**
     * numeric value represent the right key for Game.handleMoveEvent() method
     */
    public static final int RIGHT = 1;
    /**
     * numeric value represent the right key for Game.handleMoveEvent() method
     */
    public static final int DOWN = 2;
    /**
     * numeric value represent the right key for Game.handleMoveEvent() method
     */
    public static final int UP = 3;

    /**
     * The height of the window required by the Game class.
     */
    public static final int WINDOW_HEIGHT = 400;
    /**
     * The width of the window required by the Game class
     */
    public static final int WINDOW_WIDTH = 501;

    //************constants
    
    //the number of the bricks
    private static final int ROW = 6;
    private static final int COL = 7;
    
    //walls properties
    private static final int WALLTHICK = 15;
    private static final Color WALLCOLOR = Color.blue;
    
    //bricks properties
    private static final int BRICKWIDTH = 65;
    private static final int BRICKHEIGHT = 25;
    private static final Color BRICKCOLOR = Color.green;
    private static final int PADDING = 2;//the distance between two bricks
    
    //paddle properties
    private static final int PADWIDTH = 75;
    private static final int PADHEIGHT = 10;
    private static final Color PADCOLOR = Color.black;
    
    //ball properties
    private static final int BALLRADIUS = 10;
    private static final int BALLSPEED = 6;
    private static final Color BALLCOLOR = Color.red;
    
    
    //*****properties of the game
    //the walls
    private Collection<Brick> walls =  new ArrayList<Brick>();
    //the bricks..
    private Collection<Brick> bricks = new ArrayList<Brick>();
    //the ball
    private Ball ball;
    //the paddle
    private Paddle pad;
    
    /**
     * Creates a new game object.
     * @param ballX ball's staring x position.
     * @param ballDir ball's starting direction.
     */
    public Game(int ballX, int ballDir) {
        
        //walls
        Brick rightWall = new Brick(WINDOW_WIDTH-WALLTHICK, 0, WINDOW_HEIGHT, WALLTHICK, WALLCOLOR),
                leftWall = new Brick(0, 0, WINDOW_HEIGHT, WALLTHICK, WALLCOLOR),
                upperWall = new Brick(0, 0, WALLTHICK, WINDOW_WIDTH, WALLCOLOR);
        
        walls.add(rightWall);
        walls.add(upperWall);
        walls.add(leftWall);
        
        //create bricks
        for(int i=0;i<COL;i++)
            for(int j=0;j<ROW;j++) {
                Brick brick = new Brick(WALLTHICK+(i+1)*PADDING+i*BRICKWIDTH, WALLTHICK+(j+1)*PADDING+j*BRICKHEIGHT, BRICKHEIGHT, BRICKWIDTH, BRICKCOLOR);
                bricks.add(brick);
            }
        
        //paddle
        pad = new Paddle(WINDOW_WIDTH/2-PADWIDTH/2,WINDOW_HEIGHT-PADHEIGHT, PADHEIGHT, PADWIDTH, PADCOLOR);
        
        //ball
        ball = new Ball(ballX, WINDOW_HEIGHT-PADHEIGHT-BALLRADIUS, BALLSPEED, ballDir, BALLRADIUS, BALLCOLOR);
    }
    
    /**
     * Invoked when user presses left, right, up and down arrows of the keyboard.
     * If LEFT or RIGHT were pressed, it calls the paddles' moveLeft/moveRight method accordingly.
     * If LEFT was pressed and the paddle's X position is less than or equal the the wall's width,
     *  the paddle won't move.
     * If RIGHT was pressed and the paddle's X position is more than or equal to
     * (window width - wall width - paddle width), then it won'r move either.
     * 
     * If UP or DOWN were pressed, it raises/lowers the ball's speed by 1.
     * That's unless the ball's current speed is 2 or ballradius-1. In these cases, the speed stays the same.
     * 
     * @param direction LEFT or RIGHT or UP or DOWN (according to which key the user pressed).
     */
    public void handleMoveEvent(int direction) {
        
        switch(direction) {
        case LEFT:
            if(pad.getX()>WALLTHICK)
                pad.moveLeft();
            break;
            
        case RIGHT:
            if(pad.getX()<WINDOW_WIDTH-PADWIDTH-WALLTHICK)
            pad.moveRight();
            break;
        
        case UP:
            ball.setSpeed(ball.getSpeed()+1);
            break;
        
        case DOWN:
            ball.setSpeed(ball.getSpeed()-1);
        }
    }
    
    /**
     * Invoked by external scheduler each time quantum.
     * The method computes the new state of the game.
     */
    public void handleTimeEvent() {
        
        for(Brick wall:walls.toArray(new Brick[0]))
            wall.bounceBall(ball);
        
        for(Brick brick:bricks.toArray(new Brick[0]))
            if(brick.bounceBall(ball))
                bricks.remove(brick);//brick.setActive(false);
        
        pad.bounceBall(ball);
        ball.timeTick();

    }
    
    /**
     * Returns whether the game is over or not. The game ends after the last brick was hit,
     *  or if the ball's Y coordinate is larger or equal to the window height.
     * @return Whether the game is over or not.
     */
    public boolean isGameOver() {
        
        if (bricks.size()==0)
            return true;
        
        if(ball.getY()>=WINDOW_HEIGHT)
            return true;
        
        return false;
    }
    
    /**
     * Draws all the components of the game in the window.
     * @param g The graphics object associated with this game.
     */
    public void paint(Graphics g) {
        
        //paint walls
        for(Brick wall:walls.toArray(new Brick[0]))
            wall.paint(g);

        for(Brick brick:bricks.toArray(new Brick[0]))
            brick.paint(g);
        
        ball.paint(g);
        pad.paint(g);
                
    }
}
