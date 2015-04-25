package intro.ex9;

/**
 *  An abstract class that represents a position and orientation (angle).
 *  The coordinates are in the plane and between 0 and 1, and the angle is in radians.
 *  @author oop  
 */
public abstract class Physics {

    /** the x coordinate */
    protected double x;
    
    /** the y coordinate */
    protected double y;
    
    /** the angle */
    protected double angle; //the angle from the x axis. 

    /**
     * Constructs a new physics object.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param angle the angle.
     */
    Physics(double x, double y, double angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
    
    /** 
     * gets the x coordinate.
     * 
     * @return the x coordinate.
     */
    public double getX(){
        return this.x;
    }
    
    /**
     * gets the y coordinate.
     * @return the y coordinate.
     */
    public double getY(){
        return this.y;
    }

    /**
     * gets the angle.
     * @return the angle.
     */
    public double getAngle(){
        return this.angle;
    }

    /**
     * performs modulo 1 on the coordinates so that they are looped around for a 
     * toroidal geometry.
     */
    void loopCoord() {
        while(this.x>1){
            this.x-=1;
        }
        while(this.x<0){
            this.x+=1;
        }
        while(this.y>1){
            this.y-=1;
        }
        while(this.y<0){
            this.y+=1;
        }
    }
    
    /** 
     * gets the difference in x coordinates between two positions. The computation
     * takes into account the toroidal structure of the space.
     * @param other the other position
     * @return the difference in x coordinates
     */
    double getDx(Physics other){
        return(correctDif(this.x - other.x));
    }

    /**
     * Corrects the difference in coordinates between coordinates in a torus.
     * @param num the given coordinate.
     * @return the corrected coordinate.
     */
    static double correctDif(double num){
        if(num>0.5)
            return num-1;
        else if (num<-0.5)
            return 1+num;
        else
            return num;        
    }

    /** 
     * gets the difference in y coordinates between two positions. The computation
     * takes into account the toroidal structure of the space.
     * 
     * @param other the other position
     * @return the difference in y coordinates
     */
    double getDy(Physics other){
        return(correctDif(this.y - other.y));
    }

    /**
     * Computes the distance from another position. This computation takes the 
     * toroidal structure of space into account. 
     */
    public double distanceFrom(Physics other) {
        double dx = getDx(other);
        double dy = getDy(other);
        return Math.sqrt(dx*dx+dy*dy);
    }
}
