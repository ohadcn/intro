package intro.ex9;

/**
 * FILE : SpaceShipFactory.java
 * EXERCISE : Intro2cs Ex9: Space Wars
 * DESCRIPTION:
 * 		This class contains a single static method that is used to create spaceships
 * @authors avioren & ohadcn
 *
 */
public class SpaceShipFactory {

	public static final char HUMAN ='h';
	public static final char HUMAN_UPPER ='H';
	
	public static final char CRAZY_HUMAN ='c';
	public static final char CRAZY_HUMAN_UPPER ='C';
	
	public static final char RUNNER ='r';
	public static final char RUNNER_UPPER ='R';
	
	public static final char FLOATER ='f';
	public static final char FLOATER_UPPER ='F';
	
	public static final char SPECIAL ='s';
	public static final char SPECIAL_UPPER ='S';
	
	public static final char BASHER ='b';
	public static final char BASHER_UPPER ='B';
	
	public static final char AGGRESIVE ='a';
	public static final char AGGRESIVE_UPPER ='A';

	/**
	 * Creates the spaceships in the game according to the passed array of 
	 * spaceships names (h,r,f,s,...).
	 * See how it is used in SpaceWars.java main method.
	 * @param spaceships the command line arguments of SpaceWars 
	 * (e.g. spaceships={"h","r","f"}).
	 * @return the array of spaceships.
	 */
	public static SpaceShip[] createSpaceShips(String[] spaceships) {
		SpaceShip ships[] =  new SpaceShip[spaceships.length];
		for(int i=0;i<spaceships.length;i++)
			switch (spaceships[i].charAt(0)) {
			case HUMAN: case HUMAN_UPPER:
				ships[i] = new HumanShip();
				break;
			case CRAZY_HUMAN: case CRAZY_HUMAN_UPPER:
				ships[i] = new CrazyHumanShip();
				break;
				
			case RUNNER: case RUNNER_UPPER:
				ships[i] = new RunnerShip();
				break;
			
			case FLOATER: case FLOATER_UPPER:
				ships[i] = new FloaterShip();
				break;
			
			case SPECIAL: case SPECIAL_UPPER:
				ships[i] = new SpecialShip();
				break;
			
			case BASHER: case BASHER_UPPER:
				ships[i] = new BasherShip();
				break;
			
			case AGGRESIVE: case AGGRESIVE_UPPER:
				ships[i] = new AggressiveShip();
				break;

			default:
				break;
			}
		return ships;
	}
}
