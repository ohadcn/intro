package intro.ex9;

/**
 * FILE : BassherShip.java
 * EXERCISE : Intro2cs Ex9: Space Wars
 * DESCRIPTION:
 * 		    This ship attempts to collide with other ships. It will always
 *			accelerate, and turn towards the closest ship. If it gets within a distance
 *			of 0.2 units from another ship, it will turn on its shield.
 * @authors avioren & ohadcn
 *
 */
public class BasherShip extends AIShip {

	
	private final double SHIELD_DISTANCE = 0.2;

	/**
	 * construct new BasherShip object
	 */
	public BasherShip(){
		super();
	}
	
    /**
	 *  Does the actions of this ship for this round. 
	 *  This is called once per round by the SpaceWars game driver.
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		SpaceShipPhysics nearest = game.getClosestShipTo(this).getPhysics();
		//activate shield when getting close to the opponent, unless it is already active
		if(( nearest.distanceFrom(this.getPhysics()) < SHIELD_DISTANCE)^getShieldStatus()){
			turnOnShield();
		}
		
		getPhysics().move(true, (angleTo(nearest) > 0 ?1:-1));
		timeTick();
	}
}
