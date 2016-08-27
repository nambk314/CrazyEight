/**
 * 
 * @author Nam Bui
 *This class is for reading cards with their suit and value.
 */
public class Card 
	{
	private String suit;
	private int value;
	
	public Card( String suit, int value) 
	{
		this.suit = suit.toUpperCase();
		this.value = value;
	}
	
	/**
	 * get the suit of the card
	 * @return the string that contains the suit 
	 */
	public String getSuit()
	{
		return(suit);
	}
	/**
	 * Bonus method from pro2
	 * Method to get the value of the card
	 * @return value of the card
	 */
	public int getValue()
	{
		return(value);
	}
	/**
	 * method to set the suit for the card
	 * @param suit
	 */
	public void setSuit(String suit)
	{
		this.suit = suit;
	}
	
	
	/**
	 * this method read a card as a String
	 * @return String represent the card
	 */
	public String toString() 
	{
		String cardString;
		if (value == 1) 
		{
			cardString = "Ace of " + suit;
		} else if (value == 11)
		{
			cardString = "Jack of " + suit;
		} else if (value == 12)
		{
			cardString = "Queen of " + suit;
		} else if (value == 13)
		{
			cardString = "King of " + suit;
		} else
		{
			cardString =  value + " of " + suit;
		}
		
		return cardString;
		
	}
}
