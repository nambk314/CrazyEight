import java.util.Scanner;
/**
 * 
 * @author NamBui
 * The class that simulate the game Crazy Eight
 */
public class CrazyEights {
	/**
	 * Instance variable Deck
	 */
	private Deck deck;
	
	private final int MAX_CARD = 52;
	
	private final int NUM_DECK = 2;
	/**
	 * Instance variable that keep track of the players' hands
	 */
	private Hand[] playerHand;
	/**
	 * number of players
	 */
	private int numOfPlayers;
	
	/**
	 * Instance variable represent the facing up card on table
	 */
	private Card cardOnTable;

	/**
	 * Constructor method for the crazy eight game
	 */
	
	private static Card eightCard;
	
	private final int INITIAL_CARDS = 7;
	public CrazyEights()
	{
		deck = new Deck(NUM_DECK);
		
		System.out.println("Instruction: \n\nThe game is played with two standard 52-card decks, so there are two instances \n"
				+ "of each card. At least two players are required, and each player is dealt seven cards from the shuffled \n"
				+ "double-deck. One card is placed face up for all to see. \n \n"
				+ "Each player plays a card that either matches the suit or value of the face-up card. So if the face- up \n"
				+ "card is a Jack of Clubs, the current player may play any Jack or any Club (including the other Jack of Clubs). \n"
				+ "Alternatively, the player may play any Eight, which acts as a wildcard. The player playing the Eight \n"
				+ "then announces the suit that the card will act as, which does not have to be the suit that is actually \n"
				+ "on the card. Play then goes to the next player, who must repeat the process. That is, she must play a card \n"
				+ "matching the suit or value of the new face-up card, play a card of the called suit if the previous player \n"
				+ "put down an Eight, or play an Eight herself. The winner is the first player to have no cards remaining. \n\n"
				+ "If a player has no playable cards in her hand, she must draw from the deck until she can play something.\n"
				+ "A player can only pass if there are no cards remaining to draw. However, a player may choose to draw as \n "
				+ "many cards as she wants on her turn, even if she has a playable card in her hand. \n\n");
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter number of players: ");
		numOfPlayers = scan.nextInt();
		playerHand = new Hand[numOfPlayers];
		/**
		 * Get the users' names
		 */
		for (int index = 0; index < numOfPlayers; index ++)
		{
			System.out.println("Enter player " + (index + 1) + "'s Name: ");
			String name = scan.next();
			playerHand[index] = new Hand(name);
		}
		
	}
	
	/**
	 * Method to play the game
	 * 
	 */
	public void playGame()
	{
		startGame();
		
		Scanner scan = new Scanner(System.in);
		while (checkHand() == null && checkMove() == true)
		{
			for (int index = 0; index < numOfPlayers; index++)
				{
					String name = playerHand[index].getName();
					System.out.println(name + "'s turn: \n");
					
					Boolean condition;
					do 
					{
						System.out.println(playerHand[index].toString());
						String cardOnTableStr = cardOnTable.toString();
						if (cardOnTable.getValue() != 8)
						{
							System.out.println("Top card is " + cardOnTableStr);
							System.out.println(name + "'s move ? \n"
									+ "Choose a number, or 'd' for draw, or 'p' for pass.");
						} else
						{
							System.out.println("Top card is " + cardOnTableStr);
							System.out.println("(but is acting as " + eightCard.getSuit()+ ")");
							System.out.println(name + "'s move ? \n"
									+ "Choose a number, or 'd' for draw, or 'p' for pass.");
						}
						
						if (playerHand[index].Size() == 1)
						{
							System.out.println("There is only 1 card Left");
						}
						
						if (scan.hasNextInt())
						{
							int number = scan.nextInt();
							condition = theMoveInt(number, playerHand[index]);
						} else
						{
							String action = scan.next();
							condition = theMoveStr(action, playerHand[index]);
						}
					} while (condition == false);
				}
		}
		if (checkHand() != null)
		{
			System.out.println("the winner is: " + checkHand());
		} else 
		{
			System.out.println("this is a draw game");
		}
			
	}
	 /**
	  * Method to start the game by dealing 7 cards to each player and deal one card facing up on 
	  * the table
	  */
	private void startGame()
	{
		deck.shuffle();
		for (int index = 0; index < numOfPlayers; index++)
		{
			for (int num = 0; num < INITIAL_CARDS; num ++)
			{
				Card cardToAdd = deck.deal();
				playerHand[index].addCard(cardToAdd);
			}
		}
		
		cardOnTable = deck.deal();
	}
	/**
	 * method to check whether all the hands are empty or not
	 * @return the name of the person who owns the hand that is empty
	 */
	private String checkHand()
	{
		for (int index = 0; index < numOfPlayers; index ++)
			{
				if (playerHand[index].Size() == 0)
				{
					return playerHand[index].getName();
				}
			}
		return null;
	}
	
	/**
	 * Method to check whether there are any possible move to make when there is no card left to deal
	 * @return true if there is still a possible move and false otherwise
	 */
	private boolean checkMove()
	{
		if (deck.getDealNumber() >= (MAX_CARD * NUM_DECK))
		{
			for (int num = 0; num < numOfPlayers; num ++)
			{
				for (int index = 0; index < playerHand[num].Size(); index++)
				{
					Card compareCard = playerHand[num].seekCard(index);
					if (compareCard.getValue() == cardOnTable.getValue() || 
							compareCard.getSuit().equals(cardOnTable.getSuit()))
						return true;
					else
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Method to deal with the case when the response user give is an integer
	 * @param indexPos the index position of the card in the hand that the player wants to use
	 * @param playerHand the hand of the person that is currently making the move
	 * @return False if the indexPos is larger than the size of the hand or the move is not valid
	 * 		   True if the value or the suit of the card on hand match the value or suit of the 
	 * 		   card on table, or the card is an eight.
	 */
	private boolean theMoveInt(int indexPos, Hand playerHand)
	{
		
		if (indexPos >= playerHand.Size())
		{
			System.out.println("The move you make is invalid, please try again");
			return false;
		} else
		{
			Card compareCard = playerHand.seekCard(indexPos);
			//Normal situation when the card on the table is not 8
			if (cardOnTable.getValue() != 8)
			{
				
				
				
				if (compareCard.getValue() != 8)
				{
					
					//check if the value and suit of the card match the card on table
					if (compareCard.getValue() == cardOnTable.getValue() || 
							compareCard.getSuit().equals(cardOnTable.getSuit()))
					{
							cardOnTable = compareCard;
							playerHand.removeCard(indexPos);
							return true;
					} else
				    {
							System.out.println("The move you make is invalid, please try again");
							return false;
					}
					
					
				} else
				{
					crazyEightMove();
					cardOnTable = compareCard;
					playerHand.removeCard(indexPos);
					return true;
				}
				
				
			//situation when the card on the table is an eight	
			} else 
			{
				//check if the value and suit of the card match the card on table
				if (compareCard.getValue() != 8)
				{
					
					//check if the value and suit of the card match the card on table
					if (compareCard.getSuit().equals(eightCard.getSuit()))			
					{
							cardOnTable = compareCard;
							playerHand.removeCard(indexPos);
							return true;
					} else
				    {
							System.out.println("The move you make is invalid, please try again");
							return false;
					}
					
					
				} else
				{
					crazyEightMove();
					cardOnTable = compareCard;
					playerHand.removeCard(indexPos);
					return true;
				}
			}
		}
	}
	
	/**
	 * Method to get the behavior of the player when they choose a crazy eight
	 */
	private void crazyEightMove()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("### CRAZY 8! ###");
		System.out.println("Choose a suit: 1) hearts, 2) diamonds, 3) spades, 4) clubs");
		int input = scan.nextInt();

		switch (input) 
		{
			case 1 : eightCard = new Card("hearts", 8);
						break;
			case 2 : eightCard = new Card("diamonds", 8);
						break;	
			case 3 : eightCard = new Card("spades", 8);
						break;	
			case 4 : eightCard = new Card("clubs", 8);
						break;	
			default: System.out.println("Invalid value");
					break;
		}
	}
	/**
	 * Method to deal with the move that is a String
	 * @param move the move that the player make
	 * @param playerHand the hand of the person who makes the move
	 * @return true if the player decide to pass
	 * 		   false if the player choose to draw or if they make an invalid move
	 */
	private boolean theMoveStr(String move, Hand playerHand)
	{
		if (move.equals("d") && deck.getDealNumber() >= (MAX_CARD * NUM_DECK))
		{
				System.out.println("There is no more card to deal");
				return false;
		} else if (move.equals("d") && deck.getDealNumber() < (MAX_CARD * NUM_DECK))
		{
				playerHand.addCard(deck.deal());
				return false;
		} else if (move.equals("p"))
		{
				return true;
		} else
		{
			System.out.println("The move you make is invalid, please try again");
				return false;
		}		
	}
}
