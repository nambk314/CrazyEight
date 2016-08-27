
/**
 * Here are some tests to show you what string representation I am expecting the
 * toString methods of your Card, Deck, and Hand classes to return. Feel free to
 * add other tests that test other aspects of your code.
 *
 * @author Kristina Striegnitz
 * @version April 2016
 */

public class TestCrazyEights {

    private static void testCard() {

        System.out.println("The following tests check whether the toString  method of your Card class\n"
                + "returns a string that has the expected format.");

        Card tenOfSpades = new Card("spades", 10);
        Card aceOfHearts = new Card("hearts", 1);
        Card jackOfHearts = new Card("hearts", 11);
        Card queenOfHearts = new Card("hearts", 12);
        Card kingOfHearts = new Card("hearts", 13);

        String expected = "10 of SPADES";
        String actual = tenOfSpades.toString();
        Testing.assertEquals("The string rep of a 10 of spades", expected, actual);

        expected = "Ace of HEARTS";
        actual = aceOfHearts.toString();
        Testing.assertEquals("The string rep of an ace of hearts", expected, actual);

        expected = "Jack of HEARTS";
        actual = jackOfHearts.toString();
        Testing.assertEquals("The string rep of a jack of hearts", expected, actual);

        expected = "Queen of HEARTS";
        actual = queenOfHearts.toString();
        Testing.assertEquals("The string rep of a queen of hearts", expected, actual);

        expected = "King of HEARTS";
        actual = kingOfHearts.toString();
        Testing.assertEquals("The string rep of a king of hearts", expected, actual);
    }

    private static void testDeck() {
        Deck aDeck = new Deck();

        String expected = "The string representation returned by the toString method of the\n"
                + "Deck class should show all cards in the deck, one card per line. Each card\n"
                + "represented as shown above. The exact order of the cards will depend on the\n"
                + "order of the cards in your deck. Here is an example of what the string\n"
                + "representation of an unshuffled deck could look like:\n\n" + "Ace of HEARTS\n" + "2 of HEARTS\n"
                + "3 of HEARTS\n" + "4 of HEARTS\n" + "5 of HEARTS\n" + "6 of HEARTS\n" + "7 of HEARTS\n"
                + "8 of HEARTS\n" + "9 of HEARTS\n" + "10 of HEARTS\n" + "Jack of HEARTS\n" + "Queen of HEARTS\n"
                + "King of HEARTS\n" + "Ace of DIAMONDS" + "...\n";
        String actual = aDeck.toString();
        System.out.println(expected);
        System.out.println(actual + "\n");

    }

    private static void testHand () {
        Hand aHand = new Hand("Kristina");
        String actual = aHand.toString();
        String expected = "Kristina's hand:\n\n";
        Testing.assertEquals("A newly created hand, should be empty.", expected, actual);
        
        aHand.addCard(new Card("spades", 1));
        actual = aHand.toString();
        expected = "Kristina's hand:\n\n0) Ace of SPADES\n";
        Testing.assertEquals("We added an ace of spades to the hand.", expected, actual);
        
        aHand.addCard(new Card("spades", 1));
        actual = aHand.toString();
        expected = "Kristina's hand:\n\n0) Ace of SPADES\n1) Ace of SPADES\n";
        Testing.assertEquals("We added a second ace of spades to the hand.", expected, actual);
    }
    
    /**
     * The main method.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Testing.setVerbose(true);

        Testing.testSection("Testing the Card class");
        testCard();

        Testing.testSection("Testing the Deck class");
        testDeck();
        
        Testing.testSection("Testing the Hand class");
        testHand();
    }

}
