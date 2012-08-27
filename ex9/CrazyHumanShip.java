package intro.ex9;

/**
 * FILE : CrazyHumanShip.java
 * EXERCISE : Intro2cs Ex9: Space Wars
 * DESCRIPTION:
 * 		This spaceship is controlled by the user. The keys
 * 		used to control the spaceship are: left-arrow, right-arrow, up-arrow to turn
 * 		left,right and accelerate. "s" to fire a shot. "d" to turn on the shield. "t" to
 * 		teleport.
 * 		in addition there is a 2 percent chance this ship will teleport itself,
 * 	 	if it has enough energy.
 * @authors avioren & ohadcn
 *
 */
public class CrazyHumanShip extends HumanShip {
	
	private static final double CHANCE_TO_TELEPORT = 2.0;
	
	/**
	 * construct new CrazyHumanShip object
	 */
	public CrazyHumanShip(){
		super();
	}
	
    /**
	 *  Does the actions of this ship for this round. 
	 *  This is called once per round by the SpaceWars game driver.
	 * @param game the game object to which this ship belongs.
	 */
	public void doAction(SpaceWars game){
		
		//apply doAction method of HumanShip
		super.doAction(game);
		
		//crazy has 2% to teleport every turn
		double chanceToTeleport = Math.random()*100;
		if(chanceToTeleport > 0.0 && chanceToTeleport <= CHANCE_TO_TELEPORT){
			teleport();
		}
	}
}
