package intro.ex9;
import java.util.ArrayList;
import intro.ex9.*;

/**
 * This class acts as the driver for the Space Wars game. It is part of Ex57
 * (see the exercise description for more details).
 * @author avivz
 */
public class SpaceWars{
    
    /** The human-controlled spaceship type*/
    public static final int HUMAN_CONTROLLED_SHIP = 0;
    
    /** The floating spaceship type*/
    public static final int FLOATING_SHIP = 1;
    
    /** The runner spaceship type*/
    public static final int RUNNER_SHIP = 2;
    
    /** The aggressive spaceship type*/
    public static final int AGGRESSIVE_SHIP = 3;
    
    /** The basher spaceship type*/
    public static final int BASHER_SHIP = 4;

    /** The crazy-human-controlled spaceship type*/
    public static final int CRAZY_HUMAN_CONTROLLED_SHIP = 5;

    /** The special ship you designed yourself*/
    public static final int SPECIAL_SHIP = 6;
  
    /** The number of rounds a shot will exist after being fired.*/
    private static final int SHOT_TIME_TO_LIVE = 40;
    
    /** The Graphical user interface used to display the game, and get input.*/
    private GameGUI gui;
    
    /** The array of spaceships that participate in the game.*/
    private SpaceShip[] ships;
    
    /** A list of all shots that have been fired and still exist in the game.*/
    private ArrayList<ShotPhysics> shots;
    
    /** 
     * An array that specifies the number of time each spaceship has died in
     * the game.
     */
    private int[] deathCount;
    
    /**
     * Creates a new game.
     * @param args the command line arguments that indicate which types of
     * spaceships to add to the game.
     */
    public SpaceWars(String[] args){
        
        ships = SpaceShipFactory.createSpaceShips(args);
        if (ships==null || ships.length<2) {
            printUsageAndExit();
        }
        for (int i=0;i<ships.length; i++) {
            if(ships[i]==null) {
                printUsageAndExit();
            }
        }
        deathCount = new int[ships.length];
        gui = new GameGUI();
        shots = new ArrayList<ShotPhysics>();
        postDeathCountToGUI();
    }
    

    /**
     * Prints a usage message that explains how to run the game.
     */
    private static void printUsageAndExit() {
        System.out.println("Usage: \n" + 
                           "\tjava SpaceWars <spaceship types>\n\n"+
                           "Available spaceship types:\n"+
                           "\th - Human\n"+
                           "\tc - Crazy Human\n\n"+
                           "\tf - Floater\n"+
                           "\tr - Runner\n"+
                           "\ta - Aggressive\n"+
                           "\tb - Basher\n\n"+
                           "\ts - Special\n\n"+
                           "You must supply at least two spaceship types,"+
                           " and the human type can only appear once.");
        System.exit(1);
    }

    /**
     * runs the game.
     */
    private void run() {
        while(!gui.isEscPressed()){
            moveSpaceShips();
            moveShots();
            checkCollisions();
            checkHits();
            drawAllObjects();
            removeDeadShots();
            resetDeadShips();
        }
    }

    /**
     * does the action of all spaceships in this round by calling their
     * doAction() method.
     */
    private void moveSpaceShips() {
        for(int i=0; i<ships.length; i++){
            ships[i].doAction(this);
        }
    }

    /**
     * removes shots that have expired.
     */
    private void removeDeadShots() {
        for(int i=shots.size()-1; i>=0; i--){
            if( shots.get(i).expired()){
                shots.remove(i);
            }
        }
    }

    /**
     * Checks to see if a shot has hit a space ship.  If one did hit, then the
     * ship's gotHit() method is called, and the shot is removed.
     */
    private void checkHits() {
        for(int i=shots.size()-1; i>=0; i--){
            for(int j=0; j<ships.length; j++){
                if(shots.get(i).hits(ships[j].getPhysics())){
                    ships[j].gotHit();
                    shots.remove(i);
                    break;
                }
            }
        }
    }

    /**
     * Updates the position of all the shots in the game.
     */
    private void moveShots() {
        for(int i=shots.size()-1; i>=0; i--){
            shots.get(i).move();
        }
    }

    /**
     * Goes over all ships, and checks if they are dead. If so, their reset
     * method is called.
     */
    private void resetDeadShips() {
        for(int i=0; i<ships.length; i++){
            if(ships[i].isDead()){
                deathCount[i]++;
                ships[i].reset();
                postDeathCountToGUI();
            }
        }
    }

    /**
     * displays on the GUI the number of times each of the players has died.
     */
    private void postDeathCountToGUI() {
        String text = "";
        for(int i=0; i<deathCount.length; i++){
            text += "P" + (i+1) + ": " + deathCount[i] + "   ";
        }
        gui.setText(text);
        
    }

    /**
     * Draws all spaceships and shots on the GUI.
     */
    private void drawAllObjects() {
        for(int i=0; i<ships.length; i++){
            gui.addImageToBuffer(ships[i].getImage(), ships[i].getPhysics());
        }
        for(int i=shots.size()-1; i>=0; i--){
            gui.addImageToBuffer(GameGUI.SHOT_IMAGE, shots.get(i));
        }
        gui.drawBufferToScreen();
    }



    /**
     * Checks for collisions between spaceships. If two spaceships collide,
     * their speeds are adjusted, and the collideWithSpaceShip() method of each
     * spaceship is called.
     */
    private void checkCollisions() {
        for(int i=0; i<ships.length; i++){
            for(int j=i+1; j<ships.length; j++){
                if(ships[i].getPhysics().testCollisionWith(ships[j].
                                                            getPhysics())){
                    ships[i].collidedWithAnotherShip();
                    ships[j].collidedWithAnotherShip();
                }
                    
            }
        }
    }

    /**
     * Gets the GUI object we are drawing with. This method can be used by 
     * the spaceships to obtain the GUI for their own use.
     * @return the gui object.
     */
    public GameGUI getGUI() {
        return gui;
    }

    /**
     * Fires a new shot from the current location of the spaceship.  The shot
     * will automatically be managed by the SpaceWars game object.
     * @param position the position of the firing spaceship.
     */
    public void addShot(SpaceShipPhysics position) {
        shots.add(new ShotPhysics(position,SHOT_TIME_TO_LIVE));
    }

    /**
     * returns the ship that is closest to the given one.
     * @param ship the given ship
     * @return the closest ship to the given one.
     */
    public SpaceShip getClosestShipTo(SpaceShip ship) {
        double bestDistance = Double.MAX_VALUE;
        SpaceShip closest = null;
        for(int i=0; i<ships.length; i++){
            if(ships[i]!=ship){
                double distance = ships[i].getPhysics().
                    distanceFrom(ship.getPhysics());
                if(distance < bestDistance){
                    closest = ships[i];
                    bestDistance = distance;
                }
            }
        }
        return closest;
    }
    
    /**
     * Runs the game.
     * @param args command line arguments. These should describe the type of
     * the spaceships in the game.  See the exercise description for details.
     */
    public static void main(String[] args){
        SpaceWars game = new SpaceWars(args);
        game.run();
    }
}
