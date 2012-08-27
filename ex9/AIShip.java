package intro.ex9;

import java.awt.Image;
/**
 * FILE : AIShip.java
 * EXERCISE : Intro2cs Ex9: Space Wars
 * DESCRIPTION:
 * 		This abstract class is a container for all AI controlled spaceships
 * 		it implements some methods repeated in AI controlled spaceships
 * @authors avioren & ohadcn
 *
 */
public abstract class AIShip extends SpaceShip {

	/**
	 * Gets the image of this ship.
	 * This will be displayed on the GUI at the end of the round.
	 * @return the image of the ship.
	 */
	public Image getImage() {
		return (getShieldStatus()?
				GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD:
					GameGUI.ENEMY_SPACESHIP_IMAGE);
	}


	/**
	 * this method calculate the angle between other ship and this ship's cannon
	 * @param other the SpaceShipPhysics object, represent the other spaceship
	 * @return the angle between the direction of the cannon of this ship and the other ship
	 */
	protected double angleTo(SpaceShipPhysics other) {
		return this.getPhysics().angleTo(other);
	}
}
