package intro.ex9;

import java.awt.Image;

/**
 * FILE : HumanShip.java
 * EXERCISE : Intro2cs Ex9: Space Wars
 * DESCRIPTION:
 * 		This spaceship is controlled by the user. The keys
 * 		used to control the spaceship are: left-arrow, right-arrow, up-arrow to turn
 * 		left,right and accelerate. "s" to fire a shot. "d" to turn on the shield. "t" to
 * 		teleport.
 * @authors avioren & ohadcn
 *
 */
public class HumanShip extends SpaceShip {
	
	/**
	 * construct new HumanShip object
	 */
	public HumanShip(){
		super();
	}
	
    /**
	 * Does the actions of this ship for this round. 
	 * This is called once per round by the SpaceWars game driver.
	 * calls another method, to allow sub-types to add actions to the sequence
	 * @param game the game object to which this ship belongs.
	 */
	public void doAction(SpaceWars game) {
		turnSequence(game);
	}
	
	/**
	 * does the actions every human-type ship should do every turn
	 * @param game the game object to which this ship belongs.
	 */
	public void turnSequence(SpaceWars game) { 
		
		//try to teleport if asked
		if(game.getGUI().isTPressed()){
			teleport();
		}

		//move and turn or accelerate if needed
		boolean accel = game.getGUI().isUpPressed();
		int turn = 0;
		if(game.getGUI().isRightPressed()){
			turn = -1;
		}
		if(game.getGUI().isLeftPressed()){
			turn = 1;
		}
		
		getPhysics().move(accel, turn);	

		//will turn on shield if possible and asked
		if(game.getGUI().isDPressed()){
			turnOnShield();	
		}
		
		//will shoot if possible and asked
		if(game.getGUI().isSPressed()){
			shoot(game);	
		}

		timeTick();	//general sequence for every ship
	}

	/**
	 * Gets the image of this ship.
	 * This will be displayed on the GUI at the end of the round.
	 * @return the image of the ship.
	 */
	public Image getImage() {
		return (getShieldStatus()) ? GameGUI.SPACESHIP_IMAGE_SHIELD : GameGUI.SPACESHIP_IMAGE;
	}
	
}
