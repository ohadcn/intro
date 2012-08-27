package intro.ex9;

/**
 * FILE : RunnerShip.java
 * EXERCISE : Intro2cs Ex9: Space Wars
 * DESCRIPTION:
 * 			This spaceship attempts to run away from the fight. It will
 *			constantly accelerate,and turn away from the ship that is closest. The
 *			runner has the spying ability and will attempt spying on each round. It
 *			will try to get the nearest ship cannon angle from himself. if that angle
 *			is smaller than 0.2 radians (in any direction) and the distance from the
 *			nearest ship is smaller than 0.2 units, the runner will try to teleport.
 * @authors avioren & ohadcn
 *
 */
public class RunnerShip extends AIShip {

	private final double DANGER_DISTANCE = 0.2;
	
	/**
	 * construct new RunnerShip object
	 */
	public RunnerShip(){
		super();
	}
	
    /**
	 *  Does the actions of this ship for this round. 
	 *  This is called once per round by the SpaceWars game driver.
	 * @param game the game object to which this ship belongs.
	 */
	public void doAction(SpaceWars game) {
		SpaceShipPhysics nearest =  game.getClosestShipTo(this).getPhysics();
		//if runner is in danger zone - teleport
		if(isEndangered(nearest)){
			teleport();
		}
		//accelerate and move according to the angle to the nearest
		getPhysics().move(true, (angleTo(nearest) > 0 ?-1:1));
		timeTick();
	}
	
	/**
	 * Checks if the runner is being threatened by another ship
	 * runner is endangered if the distance to the other ship is less than 0.2 radians
	 * @param other the ship that might be threatening the runner
	 * @return true if Endangered, false if not
	 */
	private boolean isEndangered(SpaceShipPhysics other){
		return (getPhysics().distanceFrom(other) < DANGER_DISTANCE &&
				(other.angleTo(this.getPhysics())) < DANGER_DISTANCE);	
	}
}
