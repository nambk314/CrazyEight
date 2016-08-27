/**
 * 
 * @author Nam Bui
 * This class is representing a deck of card with the method to shuffle, deal cards
 *
 */
import java.util.Random;

public class Deck 
{
	/**
	 *  This is the total value of cards in the deck.
	 */
	private static final int MAX_VALUE = 13;
	
	/**
	 *  All the suit from the card.
	 */
	private static final String[] TOTAL_SUIT = {"HEARTS","DIAMONDS","CLUBS","SPADES"};
	
	private static final int MAX_CARD = 52;
	
	/**
	 *  instance variables deck
	 */
	private Card[] deck;
	
	/**
	 * The variable keep track of the next card to deal
	 */
	private int dealNumber;
	
	/**
	 * The varible keep track of the number of decks
	 */
	
	private int numOfDeck; 
	
	/**
	 * Default constructor method
	 */
	public Deck()
	{
		numOfDeck = 1;
		deck  = new Card[MAX_CARD];
		int index = 0;
			for (String suit : TOTAL_SUIT)
			{
				/**
				 * Start from 1 because deck start from 1
				 */
				for (int value = 1; value <= MAX_VALUE; value++)	
				{
					deck[index] = new Card(suit,value);
					index += 1;
				}
			}
		dealNumber = 0;		
	}
	
	/**
	 * Customized constructor method that can create multiple decks of cards
	 * @param numOfDeck
	 */
	public Deck(int numOfDeck)
	{
		this.numOfDeck = numOfDeck;
		deck = new Card[MAX_CARD * numOfDeck];
		int index = 0;
		for (int num = 0; num < numOfDeck; num++)
		{
			for(String suit : TOTAL_SUIT)
			{
				for (int value = 1; value <= MAX_VALUE; value ++ )
				{
					deck[index] = new Card(suit,value);
					index += 1;
				}
			}
			dealNumber = 0;
		}
	}
	
	/**
	 * This method read the whole deck as a String.
	 * @return the String represent the deck
	 */
	public String toString()
	{
		String stringDeck = "";
		for (int index = 0; index < MAX_CARD * numOfDeck; index ++)
		{
			stringDeck = stringDeck + deck[index].toString() + "\n";
		}
		
		return stringDeck;
	}
	
	/**
	 * Deal method
	 * @return the next Card to deal
	 */
	public Card deal()
	{
		if (dealNumber < MAX_CARD * numOfDeck)
		{
			Card nextCard = deck[dealNumber];
			dealNumber++;
			return nextCard;
		} else
		{	
			return null;	
		}
	}
	/**
	 * method to get the deal number
	 * @return deal Number
	 */
	public int getDealNumber()
	{
		return(dealNumber);
	}
	
	/**
	 * Method to shuffle the deck
	 * @return nothing
	 */
	public void shuffle()
	{
		Random random = new Random();
		for (int index = 0; index < MAX_CARD * numOfDeck; index++)
		{
			
			int randomNum = random.nextInt(MAX_VALUE);
			
			//Switch the position of the two cards
			Card holder = deck[index];
			deck[index] = deck[randomNum];
			deck[randomNum] = holder;
						
		}
	}
	
}
