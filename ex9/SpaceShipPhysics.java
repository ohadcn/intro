package intro.ex9;

import java.util.Random;

/**
 * This class represents the physical aspects of a spaceship and can be used to manipulate them.
 * These include, the position, speed and heading of the spaceship. 
 * @author avivz
 */
public class SpaceShipPhysics extends Physics {
    
    /** The velocity in the x direction.*/
    private double _vx;
    
    /** The velocity in the y direction.*/
    private double _vy;
    
    /** The maximal speed of the ship. */
    private static final double MAX_VELOCITY=0.005;
    
    /** The acceleration rate. */
    private static final double ACCELERATION_RATE = 0.0002;
    
    /** The rate of turning*/
    private static final double TURN_RATE=0.05;
    
    /** 
     * The size of the spaceship. This constant is used to determine collisions
     * between spaceships (used automatically by the SpaceWars object)
     */
    public static final double RADIUS = 0.03;
    
    /** The collision modifier. (1 would be a perfect elastic collision)*/
    private static final double PLASTIC_COLLISION_MOD = 0.8;
    
    /** The random generator used for initialization of the loaction and speed
        This field is used for testing purposes, and there is no need to use it
        within your solution.*/
    public static Random randomGenerator = new Random(System.currentTimeMillis());

    /**
     * Creates a new physics object with a random position, heading and speed.
     */
    public SpaceShipPhysics(){
        super(randomGenerator.nextDouble(),randomGenerator.nextDouble(),randomGenerator.nextDouble()*Math.PI*2);
        _vx=(randomGenerator.nextDouble()-0.5)*MAX_VELOCITY;
        _vy=(randomGenerator.nextDouble()-0.5)*MAX_VELOCITY;
    }
    

    /**
     * Call this method once per game round to update the position of the spaceship. 
     * This method should be called even if no action is taken by the spaceship so that it will
     * continue to move in a constant speed. The parameters of acceleration and turn
     * can be used to make the spaceship turn and accelerate.
     * @param accel this should be true if the spaceship is accelerating, false otherwise (in which case it will continue with constant speed)
     * @param turn this parameter indicates where the space ship is turning. -1 indicates a right turn, 1 indicates a left turn and 0 indicates no turn. Any other value results in an error.
     */
    public void move(boolean accel,int turn){
        if(turn<-1 || turn >1){
            throw new IllegalArgumentException("Values of turn can only be -1,0, or 1");
        }
        angle += turn*TURN_RATE;
        angle = angle %(2*Math.PI);

        x += _vx;
        y += _vy;
        loopCoord();
        
        if(accel){
            _vx+=ACCELERATION_RATE * Math.cos(angle);
            _vy+=ACCELERATION_RATE* Math.sin(angle);
        }
        
        double vel = Math.sqrt(_vx*_vx+_vy*_vy);
        if(vel>MAX_VELOCITY){
            _vx*=MAX_VELOCITY/vel;
            _vy*=MAX_VELOCITY/vel;
        }
    }
    
    /**
     * This method is used by the StarWars game object to check if two ships
     * have collided. If a collision occurs, then the speed of the objects is
     * updated to reflect the collision.
     * <p>
     * You do not need to use this method in your implementation
     * 
     * @param other the other position
     * @return true if a collision occurs, false otherwise.
     */
    public boolean testCollisionWith(SpaceShipPhysics other){
        //relative position:
        double dx = getDx(other);
        double dy = getDy(other);
        double norm = Math.sqrt(dx*dx+dy*dy);
        if(norm<SpaceShipPhysics.RADIUS/2){
            _vx+=ACCELERATION_RATE*2;
            other._vx-=ACCELERATION_RATE;
        }
        
        //relative speed:
        double dvx = correctDif(_vx - other._vx); 
        double dvy = correctDif(_vy - other._vy);
        if(norm<SpaceShipPhysics.RADIUS*2 && dx*dvx+dy*dvy<0){
            if(norm<=0){
                norm = 1;
                dx=1;
                dy=0;
            }
            //normalize the relative position:
            dx = dx/norm;
            dy = dy/norm;
            
            double dot = dvx*dx+dvy*dy;
            _vx -= dot*dx*PLASTIC_COLLISION_MOD;
            _vy -= dot*dy*PLASTIC_COLLISION_MOD;
            
            other._vx += dot*dx*PLASTIC_COLLISION_MOD;
            other._vy += dot*dy*PLASTIC_COLLISION_MOD;
                 
            return true;
        }
        return false;
    }

    /**
     * Checks the angle this spaceship should turn in order to face the position of another ship.
     * A negative angle (between 0 and -PI) means that it must turn right. 
     * a positive angle (between 0 and +PI) implies turning left.
     * @param other the other ship's position.
     * @return the relative angle to turn.
     */
    public double angleTo(SpaceShipPhysics other) {
        double dx = -getDx(other);
        double dy = -getDy(other);
        double angleFromX = Math.asin(dy/(Math.sqrt(dy*dy+dx*dx)));
        if (dx<0){
            angleFromX = Math.PI-angleFromX;
        }
        double difAngle = angleFromX-angle;
        difAngle = difAngle % (2*Math.PI);
        if(difAngle>Math.PI){
            difAngle-=Math.PI*2;
        }else if(difAngle<-Math.PI){
            difAngle+=Math.PI*2;
        }
        return difAngle;
    }
}
