package intro.ex9;

/**
 * This object represents the physical properties of a shot in the SpaceWars game. 
 * @author oop 
 */
public class ShotPhysics extends Physics {
   
    /** The maximal velocity of the shot.*/
    private static final double MAX_VELOCITY=0.015;
    
    /** The size of the shot.*/
    private static final double RADIUS = 0.01;
    
    /** a small value.*/
    private static final double EPS = 0.001;
    
    /** the time to live of this shot (in rounds)*/
    private int _ttl;

    /** 
     * Create a new ShotPhysics object.
     * @param shipPosition the position of the ship that fires this shot.
     * @param ttl the number of rounds this shot will exist.
     */
    public ShotPhysics(SpaceShipPhysics shipPosition, int ttl){
        super(shipPosition.getX()+
                Math.cos(shipPosition.getAngle())*
                (RADIUS+SpaceShipPhysics.RADIUS+EPS),
                shipPosition.getY()+
                Math.sin(shipPosition.getAngle())*
                (RADIUS+SpaceShipPhysics.RADIUS+EPS),
                shipPosition.getAngle());
        _ttl = ttl;
    }
           

    /** 
     * moves this shot and updates its position.
     */
    public void move(){
        this.x += MAX_VELOCITY * Math.cos(this.angle);
        this.y += MAX_VELOCITY * Math.sin(this.angle);
        _ttl--;
        loopCoord();
    }

    /**
     * Checks if the shot hits a spaceship.
     * @param spaceship the spaceship
     * @return true if the shot hits this spaceship.
     */
    public boolean hits(SpaceShipPhysics spaceship) {
        double dx = getDx(spaceship);
        double dy = getDy(spaceship);
        return (Math.sqrt(dx*dx+dy*dy)<=(ShotPhysics.RADIUS + SpaceShipPhysics.RADIUS));
    }
    
    /**
     * Checks if this shot has expired (after living for too long)
     * @return true if the shot has expired.
     */
    public boolean expired() {
        return _ttl <=0;
    }
}
