package intro.ex9;

/**
 * FILE : SpecialShip.java
 * EXERCISE : Intro2cs Ex9: Space Wars
 * DESCRIPTION:
 * 		SpecialShip is a crazy kind of aggressiveShips
 * 		At first, it behaves like an aggressive, and after it was killed
 * 		it freaks out, coming after the closest ship and shoots without any limitations!
 * @authors avioren & ohadcn
 *
 */
public class SpecialShip extends AggressiveShip {

	private boolean isAngry;
	
	/**
	 * construct new SpecialShip object
	 */
	public SpecialShip() {
		super();
		isAngry = false;
	}
	
    /**
     * this method is called each time the SpaceShip is shooting.
     * this special spaceShip never runs out of energy and have no restrictions for shooting
     * @param game the game object to which this SpecialShip belongs.
     * @return true if the shooting succeeded, false otherwise
     */
	@Override
	protected boolean shoot(SpaceWars game) {
		if(isAngry){
			game.addShot(getPhysics());
			return true;
		}
		return super.shoot(game);
	}
	
	/**
	 * Checks if this ship is dead.
	 * @return true if the ship is dead. false otherwise.
	 */
	@Override
	public boolean isDead() {
		boolean isDead = super.isDead();
		isAngry ^= isDead;
		return isDead;
	}
}
