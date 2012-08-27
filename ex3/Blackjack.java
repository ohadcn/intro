/**
 * file:Blackjack.java
 * @author Ohad Cohen, ohad.cohen6@mail.huji.ac.il
 * exercise : intro2cs ex3 2011-2012 
 * description: this program is a little Blackjack game
 */
import intro.blackjack.*;


public class Blackjack {
	
	//the computer strategy for playing
	//actually means when the AI stop asking for more cards
	private static final int compStrategy=16;
	private static final int blackJack=21;//the game constant
	private static final int startBalance=1000;//the starting balance of the players
	private static final int startingCards = 2;
	
	//return codes for the playTurn method
	public static final int userWins = 1;
	public static final int compWins = 2;
	public static final int aDraw = 0;
	
	//error codes
	public static final int success=0;
	public static final int notInitializedError=-1;
	public static final int invalidParamError=-2;
	
	/**
	 * this method adds a random card to the player
	 * updates UI
	 * updates AssHithLow
	 * @param UI - the game window
	 * @param player - the player to add cards to
	 * @param isUser - shell state if player is the user or the computer
	 */
	private static void addCard(BlackjackUI UI,Player player,boolean isUser) {
		Card newRandomCard = new Card();
		player.addCard(newRandomCard);
		if(isUser)
			UI.addUserCard(newRandomCard);
		else
			UI.addCompCard(newRandomCard);
		if(player.getCardsSum()>blackJack)
			player.changeAceHighToLow();
	}//addcard
	
	
	/**
	 * this method implements theAI algorithm
	 * @param AI - thAI player
	 * @return true if AI take more card
	 * false if not
	 */
	private static boolean AIMoreCards(Player AI) {
		if(AI.getCardsSum()>compStrategy)
			return false;
		return true;
	}//AIMoreCards
	
	/**
	 * this method plays a turn of blackjack
	 * @param UI - the game window
	 * @param AI - the computer player
	 * @param user - the user player
	 * @return userWins if the user wins, compWins if the computer wins
	 * 		aDraw in a case of a draw
	 * 		an error code on failure:
	 *		notInitializedError if UI, AI or user aren't initialized
	 */
	private static int playTurn(BlackjackUI UI,Player AI,Player user) {
		
		//parameters checking
		if(UI==null||user==null||AI==null)
			return notInitializedError;
		
		//the first two cards
		for(int i=0;i<startingCards;i++)
			addCard(UI,user,true);
		for(int i=0;i<startingCards;i++)
			addCard(UI,AI,false);
		
		//user turn
		boolean userFinished=false;
		while((!userFinished)&&(user.getCardsSum()<blackJack)) {//user have 21 or more 
			if(UI.askYesNo("Do you want another card?"))
				addCard(UI,user,true);
			else //the user don't want more cards
				userFinished=true;
		}
		if(user.getCardsSum()>blackJack)//if the user passes 21
			return compWins;
		
		//AI turn
		while(AIMoreCards(AI)) {
			addCard(UI,AI,false);
		}
		if(AI.getCardsSum()>blackJack) //if the computer passes 21
			return userWins;
		
		//declare winner or a draw
		if(AI.getCardsSum()==user.getCardsSum())
			return aDraw;
		return (AI.getCardsSum()>user.getCardsSum()?compWins:userWins);
	}//play turn
	
	
	/**
	 * this method gets an instance of BlackjackUI and ask the user to give a bet
	 * until the user give a valid bet, between 1 and max
	 * @param UI - the game window
	 * @param max - the maximum amount allowed for a bet
	 * @return on success - the bet amount
	 * 		an error code on failure:
	 *		notInitializedError if ui is not initialized
	 * 		invalidParamError if max is zero or less
	 */
	private static int getbet(BlackjackUI UI,int max) {
		
		//parameters check
		if (UI==null)
			return notInitializedError;
		if (max<1)
			return invalidParamError;
		
		while (true){//until we get a valid bet
			int bet=UI.askNumber("Please enter the bet value for this round:");
			if (bet>max) {
				UI.displayErrorMessage("You cannot bet on a value greater than your "+
										"or the computer's balance!");
				continue;
			}
			if(bet<=0) {
				UI.displayErrorMessage("Your bet value must be greater than zero!");
				continue;
			}
			//if we got here, the input is correct
			UI.setBet(bet);
			return bet; //get out of the method and the loop...
		}//while
	}//getbet


	public static void main(String[] args) {
		BlackjackUI ui = new BlackjackUI();
		Player user = new Player(), AI = new Player();
		
		//set starting value
		user.addCoins(startBalance);
		AI.addCoins(startBalance);
		ui.updateCompBalance(AI.getBalance());
		ui.updateUserBalance(user.getBalance());
		boolean finished=false;
		do{//each run is a round !!
			ui.clearCards();
			ui.clearBet();
			user.clearCards();
			AI.clearCards();
			int bet = getbet(ui,(user.getBalance()>AI.getBalance()?
					AI.getBalance():user.getBalance())); //set max to the lower balance
			int winner = playTurn(ui,AI,user);
			
			switch (winner) {
			
			case userWins:
				ui.displayMessage("You won the game!");
				user.addCoins(bet);
				AI.deductCoins(bet);
				ui.updateCompBalance(AI.getBalance());
				ui.updateUserBalance(user.getBalance());
				if(AI.getBalance()==0)
					finished=true;
				break;
				
			case compWins:
				ui.displayMessage("You lost the game!");
				AI.addCoins(bet);
				user.deductCoins(bet);
				ui.updateCompBalance(AI.getBalance());
				ui.updateUserBalance(user.getBalance());
				if(user.getBalance()==0)
					finished=true;
				break;
				
			case aDraw://no need to update anything
				ui.displayMessage("This game ends with a draw.");
				break;
				
			}//switch case
		}//do while
		while (!finished && ui.askYesNo("Do you want another game?"));
		
		ui.displayMessage("You finished your session with "+user.getBalance()+" coins");
		ui.close();
	}//main
}//Class
