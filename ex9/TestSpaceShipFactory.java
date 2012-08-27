package intro.ex9;

public class TestSpaceShipFactory {

	//this array contains the char for all the available ships
	private static final char[] SHIPTYPES = {SpaceShipFactory.AGGRESIVE,
		SpaceShipFactory.BASHER,
		SpaceShipFactory.CRAZY_HUMAN,
		SpaceShipFactory.FLOATER,
		SpaceShipFactory.HUMAN,
		SpaceShipFactory.RUNNER,
		SpaceShipFactory.SPECIAL};

	//number of tests to do
	private static final int TESTS = 10;

	/**
	 * this method tests the SpaceShipFactory class
	 * if the class is working it will print nothing
	 *  otherwise it will print the char of the problematic class
	 * @param args has no effect
	 */
	public static void main(String[] args) {
		String[] types = new String[TESTS];

		//create array of random strings, for the ships to create
		for (int i=0;i<TESTS;i++){
			types[i] =  String.valueOf((SHIPTYPES[(int)(Math.random()*SHIPTYPES.length)]));
		}
		//create the ships array
		SpaceShip ships[] = SpaceShipFactory.createSpaceShips(types);

		//test the ships
		for(int i=0;i<TESTS;i++){
			if(!testShipType(ships[i], types[i].charAt(0))){
				System.out.println("error constructing " + types[i] + " ships");
			}
		}
	}

	/**
	 * this method checks if the type of ship is equivalent to the one presented by type
	 * @param ship the ship to be tested
	 * @param type the expected type of the ship
	 * @return true if the type of ship is the one presented by type and false otherwise
	 */
	private static boolean testShipType(SpaceShip ship, char type){
		switch (type) {
		case SpaceShipFactory.AGGRESIVE:
			return (ship instanceof AggressiveShip) && !(ship instanceof SpecialShip);
			//no need for break, because the return statement

		case SpaceShipFactory.BASHER:
			return (ship instanceof BasherShip);

		case SpaceShipFactory.CRAZY_HUMAN:
			return (ship instanceof CrazyHumanShip);

		case SpaceShipFactory.FLOATER:
			return (ship instanceof FloaterShip);

		case SpaceShipFactory.HUMAN:
			return ((ship instanceof HumanShip) && !(ship instanceof CrazyHumanShip));

		case SpaceShipFactory.RUNNER:
			return (ship instanceof RunnerShip);

		case SpaceShipFactory.SPECIAL:
			return (ship instanceof SpecialShip);

		default:
			//if the spaceship type is unknown don't judge
			return true;
		}
	}

}
