package intro.ex9;

/**
 * FILE : AggressiveShip.java
 * EXERCISE : Intro2cs Ex9: Space Wars
 * DESCRIPTION:
 * 		This ship pursues other ships and tries to fire at them. It
 *		will always accelerate, and turn towards the nearest ship. If its angle from
 *		the nearest ship is 0.2 radians or less (in any direction) then it will open fire.
 * @authors avioren & ohadcn
 *
 */
public class AggressiveShip extends AIShip {

	private final double SHOOTING_ANGLE = 0.2;

	/**
	 * construct new AggressiveShip object
	 */
	public AggressiveShip(){
		super();
	}

	/**
	 *  Does the actions of this ship for this round. 
	 *  This is called once per round by the SpaceWars game driver.
	 * @param game the game object to which this ship belongs.
	 */
	public void doAction(SpaceWars game) {
		SpaceShipPhysics nearest = game.getClosestShipTo(this).getPhysics();
		if(toShoot(nearest)){	//close enough to shoot
			shoot(game);	
		}
		//move right or left according to the angle to the nearest ship
		getPhysics().move(true, (angleTo(nearest) > 0 ?1:-1));
		timeTick();
	}

	/**
	 * this method checks if AggressiveShip object should shoot or not
	 * @param other the SpaceShipPhysics object, represent the nearest spaceship
	 * @return true if AggressiveShip should shoot, false otherwise
	 */
	private boolean toShoot(SpaceShipPhysics other){
		if(Math.abs(angleTo(other)) < SHOOTING_ANGLE){
			return true;
		}
		return false;
	}
}
