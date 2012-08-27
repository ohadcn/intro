package intro.ex9;

import java.awt.Image;

/**
 * FILE : TestSpaceShip.java
 * EXERCISE : Intro2cs Ex9: Space Wars
 * DESCRIPTION:
 * 		This class is a tester for the spaceships.
 * @authors avioren & ohadcn
 *
 */
public class TestSpaceShip extends SpaceShip{

	//constant values used in the  game
	private static final int STARTING_HEALTH = 10;
	private static final int MAX_ENERGY = 200;
	private static final int STARTING_ENERGY = MAX_ENERGY;
	private static final int SHOOTING_ENERGY = 25;
	private static final int TELEPORTING_ENERGY = 3;
	private static final int SHOOTING_TIME = 8;
	private static final int SHIELD_ENERGY = 3;

	//choosing too much tests may cause the ship run out of energy and false positive test
	private static final int TEST_REPEAT = 3;

	/**
	 * run the testings
	 * @param args
	 */
	public static void main(String[] args) {

		showResult(testHealth(),"your health is not configured correctly!");
		showResult(testFire(),"your spaceship doesn\'t shoot!");
		showResult(testFireDelay(),"your fire delay is not configured correctly!");
		showResult(testShield(),"your sheild is not working!");
		showResult(testEnergy(),"your spaceship has problems with energy configurations!");

	}


	/**
	 * test ship firing
	 * @return true if a shot was shot, false if not
	 */
	private static boolean testFire(){

		SpaceShip ship = new TestSpaceShip();
		FakeSpaceWars wars = new FakeSpaceWars();
		if(!ship.shoot(wars) || (!wars.didShoot())){
			return false;
		}
		return true;
	}


	/**
	 * testing if the ship does not fire while it shouldn't
	 * i.e. under 8 turns since last shot
	 * @return true if shooting works properly, false if not
	 */
	private static boolean testFireDelay(){
		SpaceShip ship = new TestSpaceShip();
		FakeSpaceWars wars = new FakeSpaceWars();

		for(int j=0;j<TEST_REPEAT;j++){
			if((!ship.shoot(wars)) || (!wars.didShoot())){
				return false;
			}
			for(int i = 0;i < SHOOTING_TIME;i++){
				if(ship.shoot(wars) || wars.didShoot()){
					return false;
				}
				else{
					ship.doAction(wars); //make it know turn passed
				}
			}
		}
		return true;
	}


	/**
	 * test for health reduction when a ship is hit
	 * @return true if health is working properly, false if not
	 */
	private static boolean testHealth(){
		SpaceShip ship = new TestSpaceShip();
		for(int j=0;j<TEST_REPEAT;j++){

			for(int i=0;i<STARTING_HEALTH;i++){
				if(ship.isDead()){//if is dead when it shouldn't
					return false;
				}
				else{//hurt it until is dying
					hurt(ship);
				}
			}

			if(!ship.isDead()){//after hurting it 10 times..
				return false;
			}
			ship.reset();

		}
		return true;
	}

	/**
	 * this method demonstrates getting hit by a ship
	 * @param ship the hitting enemy ship
	 */
	private static void hurt(SpaceShip ship){
		if (Math.random()>0.5){
			ship.collidedWithAnotherShip();
		}
		else{
			ship.gotHit();
		}
	}

	/**
	 * testing energy reduction
	 * testing energy gain every turn
	 * @return true if energy management is working properly, false if not
	 */
	private static boolean testEnergy(){
		SpaceShip ship = new TestSpaceShip();
		SpaceWars game = new FakeSpaceWars();
		int energy = STARTING_ENERGY;

		for(int i=0;i<TEST_REPEAT*10;i++){
			//reduce energy when shield is on
			if(ship.getShieldStatus()){
				energy -= SHIELD_ENERGY;
			}
			//test for energy reduction after an operation
			int result = doSomething(ship, energy);
			if (result < 0){
				return false;
			}
			else{
				energy -= result;
			}
			ship.doAction(game);
			energy++;
		}
		return true;
	}

	/**
	 * choose one possible operation randomly, and commit it
	 * @param ship the spaceship object to commit the operation
	 * @param energy the current energy level
	 * @return the amount of energy used for the operation, 
	 * 			-1 if it did the operation without the needed amount of energy(which it shouldn't)
	 */
	private static int doSomething(SpaceShip ship, int energy){
		boolean result;
		switch ((int) Math.random()*3) {
		case 0:		//teleporting
			result = ship.teleport();
			if(result^(energy>TELEPORTING_ENERGY)){
				return -1;
			}
			if(energy > TELEPORTING_ENERGY){
				return TELEPORTING_ENERGY;
			}
			else{
				return 0;
			}

		case 1:	//shooting
			SpaceWars game = new FakeSpaceWars();
			result = ship.shoot(game);
			if(result^(energy>SHOOTING_ENERGY))
				return -1;
			if(energy > SHOOTING_ENERGY)
				return SHOOTING_ENERGY;
			else
				return 0;			

		case 2:	//using shield
			ship.turnOnShield();
			break;
		}
		return 0;
	}

	/**
	 * testing that ship is invulnerable when shield is on, and vulnerable when shield is off
	 * @return true if shield is working properly, false if not
	 */
	private static boolean testShield(){
		int hitCounter=0;
		SpaceShip ship = new TestSpaceShip();
		
		for(int i=0;i<TEST_REPEAT;i++){
			while(hitCounter<STARTING_HEALTH){
				if(ship.isDead()){
					return false;
				}
				hurt(ship);
				//add to hitCounter only if shield is off
				hitCounter+=(ship.getShieldStatus()?0:1);
				if(Math.random()<0.2){
					ship.turnOnShield();
				}
			}
			if(!ship.isDead()){
				return false;
			}
		}
		return true;
	}

	/**
	 * show an error message if a test has failed
	 * @param test result from each test
	 * @param message the error to print on the screen, according to the failure
	 */
	private static void showResult(boolean test,String message){
		if(!test){
			System.out.println(message);
		}
	}

	/**
	 *  Does the actions of this ship for this round. 
	 *  This is called once per round by the SpaceWars game driver.
	 * @param game the game object to which this ship belongs.
	 */
	public void doAction(SpaceWars game) {
		timeTick();

	}

	/**
	 * Gets the image of this ship.
	 * This method should return the image of the ship with or without the shield.
	 * This will be displayed on the GUI at the end of the round.
	 * @return the image of the ship.
	 * @deprecated this method is provided just because of java rules, it returns always null
	 */
	@Deprecated
	public Image getImage() {
		return null;
	}

}
