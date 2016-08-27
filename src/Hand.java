/**
 * The class representing the cards in the hand of the players
 * @author Nam Bui
 *
 */
 class Hand {
	/**
	 * Instance variable for person who own this hand
	 */
	private String player;
	
	/**
	 * An list of Card that keep track of the cards this person has
	 */
	private Card[] cardsInHand;
	
	/**
	 * An integer represent number of cards in hand
	 */
	private int numOfCards;
	
	
	/**
	 * Constructor method to identify who own this hand
	 * @param playerName
	 */
	public Hand(String playerName)
	{
		player = playerName;
		cardsInHand = new Card[0];
		numOfCards = 0;
		
	}
	/**
	 * Method to resize the size of the array contains the cards in the hand
	 * 
	 */
	
	public void resizeHand(int capacity)
	{
		int oldCapacity = Size();
		Card[] temp = new Card[capacity];
		if (capacity < oldCapacity)
		{
			for (int index = 0; index < capacity; index++)
			{
				temp[index] = cardsInHand[index];
			}
			cardsInHand = temp;
		} else
		{
			for (int index = 0; index < oldCapacity; index++)
			{
				temp[index] = cardsInHand[index];
			}
			cardsInHand = temp;
		}
		
	}
	
	/**
	 * Method to add card to the hand
	 * @param cardToAdd
	 */
	public void addCard(Card cardToAdd)
	{
			resizeHand(numOfCards + 1);
			cardsInHand[numOfCards] = cardToAdd;
			numOfCards += 1;
	}
	
	/**
	 * Method to remove card from hand
	 * @param cardToRemove the position in the array of the card that needs to be removed
	 * @return the card to remove
	 */
	public Card removeCard(int cardToRemove)
	{
		int currentNum = Size();
		if (cardToRemove < currentNum)
		{
			Card cardRemove = cardsInHand[cardToRemove];
			for (int num = cardToRemove; num < (currentNum -1); num ++)
			{
				cardsInHand[num] = cardsInHand[num + 1];
			}
			

			numOfCards -= 1;
			resizeHand(numOfCards);

			return cardRemove;
		} else
		{
			return null;
		}

		
	}
	
	/**
	 * Method to seek card
	 * @param cardToSeek the index value of the card to seek
	 * @return the card that is seek
	 */
	public Card seekCard(int cardToSeek)
	{
		int currentNum = Size();
		if (cardToSeek < currentNum)
		{
			Card cardSeek = cardsInHand[cardToSeek];
			return(cardSeek);
		} else
		{
			return null;
		}
	}
	
	/**
	 * Method to get the number of cards in the hand
	 * @return number of cards
	 */
	public int Size()
	{
		return(numOfCards);
	}
	
	/**
	 * Method to get the name of the player who owns the hand
	 * @return Player's name
	 */
	public String getName()
	{
		return(player);
	}
	
	/**
	 * Method that read the hand with the person's name and all the cards in hand
	 * @return a string represent person's name and all the cards in hand
	 */
	
	public String toString()
	{
		int currentNum = numOfCards;
		String handString = player + "'s hand: \n \n";
		for (int index = 0; index < currentNum; index ++ )
		{
			handString = handString + index + ") " + cardsInHand[index] + "\n";
		}
		return(handString);
	}
}

