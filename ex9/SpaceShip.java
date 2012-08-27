package intro.ex9;

import java.awt.Image;
/**
 * FILE : SpaceShip.java
 * EXERCISE : Intro2cs Ex9: Space Wars
 * DESCRIPTION:
 * 		This abstract class is a container for all spaceships.
 * 		this class contains the API spaceships need to implement for the SpaceWars game.
 * 		and implements some method used repeatedly in spaceships. 
 * @authors avioren & ohadcn
 *
 */
public abstract class SpaceShip{

	/**
	 * The position and physics of the ship. 
	 */
	private SpaceShipPhysics pos;

	//constant values used in the  game
	private static final int STARTING_HEALTH = 10;
	private static final int MAX_ENERGY = 200;
	private static final int STARTING_ENERGY = MAX_ENERGY;
	private static final int SHOOTING_ENERGY = 25;
	private static final int TELEPORTING_ENERGY = 3;
	private static final int SHOOTING_TIME = 8;
	private static final int SHIELD_ENERGY = 3;

	/**
	 * the health of the ship
	 */
	private int health;
	
	/**
	 * the energy of the ship
	 * the energy is used to shoot, teleport or activating shield and is increased by 1 each turn
	 */
	private int energy;
	
	/**
	 * count in how many turns the ship can shoot
	 */
	private int firingTurns;
	
	/**
	 * a boolean field mean if the shield of the ship is active or not
	 */
	private boolean shieldStatus;


	/**
	 *  Does the actions of this ship for this round. 
	 *  This is called once per round by the SpaceWars game driver.
	 * @param game the game object to which this ship belongs.
	 */
	public abstract void doAction(SpaceWars game);

	/**
	 * Gets the image of this ship.
	 * This method should return the image of the ship with or without the shield.
	 * This will be displayed on the GUI at the end of the round.
	 * @return the image of the ship.
	 */
	public abstract Image getImage();

	/**
	 * construct a new SpaceShip object
	 * cause SpaceShip is abstract class this is used by the sub-classes
	 * to initialize SpaceShip properties
	 */
	protected SpaceShip(){
		reset();
	}


	/**
	 *  This method is called every time a collision with this ship occurs.
	 *  if shield is on, no harm done...
	 */
	public void collidedWithAnotherShip() {
		if(!shieldStatus)
			health--;
	}

	/**
	 * do actions that every SpaceShip should do every turn
	 * especially update SpaceShip properties of the time passed
	 */
	protected void timeTick() {

		//check capability of shooting again
		if(firingTurns > 0){
			firingTurns --;
		}

		//reduce energy if shield is on
		if(shieldStatus){
			if(energy > SHIELD_ENERGY){
				energy -= SHIELD_ENERGY;
			}
			else{
				turnOnShield();
			}
		}
		//add energy
		energy ++;
		if (energy > MAX_ENERGY){
			energy = MAX_ENERGY;
		}
	}

	/**
	 * Gets the physics object that controls this ship.
	 * @return the physics object that controls the ship.
	 */
	public SpaceShipPhysics getPhysics() {
		return pos;
	}

	/**
	 *  This method is called by the SpaceWars game object whenever this ship
	 *  gets hit by a shot.
	 *  a ship can be hit only if shield is off
	 */
	public void gotHit() {
		if(!shieldStatus){
			health--;
		}
	}

	/**
	 *  Checks if this ship is dead.
	 * @return true if the ship is dead. false otherwise.
	 */
	public boolean isDead() {
		return (health < 1);
	}

	/**
	 *  This method is called whenever a ship has died.
	 *  It resets the ship's attributes, and starts it at a new random position.
	 */
	public void reset() {
		pos = new SpaceShipPhysics();
		health = STARTING_HEALTH;
		energy = STARTING_ENERGY;
		shieldStatus = false;
		firingTurns = 0;
	}

	/**
	 * this method is called each time the SpaceShip is shooting
	 * this method does all checks needed to verify that the spaceship can shoot
	 * and updates the spaceship properties
	 * @param game the game object to which this ship belongs.
	 * @return true if the shooting succeeded, false otherwise
	 */
	protected boolean shoot(SpaceWars game) {
		if(energy < SHOOTING_ENERGY || firingTurns > 0){
			return false;
		}
		//the ship is able to shoot
		game.addShot(getPhysics());

		energy -= SHOOTING_ENERGY;
		firingTurns = SHOOTING_TIME;

		return true;
	}

	/**
	 * returns the status of the shield
	 * @return true if the shield is active, false otherwise
	 */
	protected boolean getShieldStatus(){
		return shieldStatus;
	}

	/**
	 * this method turns the shield on/off
	 */
	protected void turnOnShield(){
		if (shieldStatus){	//shield is currently on
			shieldStatus = false;
		}
		else{
			if (energy > SHIELD_ENERGY){	//shield is off, and possible to turn on
				shieldStatus = true;
			}
		}
	}

	/**
	 * this method teleports the SpaceShip object
	 * teleport makes The spaceship disappear and reappear at a random location.
	 * @return true if teleporting succeeded, false otherwise
	 */
	protected boolean teleport() {
		if(energy > TELEPORTING_ENERGY){	//ship is able to teleport
			pos = new SpaceShipPhysics();
			energy -= TELEPORTING_ENERGY;
			return true;
		}
		return false;
	}
}