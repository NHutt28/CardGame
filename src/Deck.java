import java.util.ArrayList;

public class Deck {
    // Instance variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    //Constructor
    public Deck(int[] values, String[] ranks, String[] suits) {
        // Count for image (starts at one)
        int count = 1;
        cards = new ArrayList<Card>();
        // Initializes every card
            for (int j=0; j < values.length - 1; j++) {
                for (int i = 0; i < suits.length; i++) {
                    cards.add(new Card(values[j], suits[i], ranks[j], Integer.toString(count)));
                    count++;
                }
            }
        shuffle();

        cardsLeft = cards.size();
    }
    // Checks if the deck is empty
    public boolean isEmpty() {
         if (cardsLeft == 0)
         {
             return true;
         }
         return false;
    }
    // Deal method - gives the top card
    public Card deal()
    {
        Card c1;
        if (isEmpty())
        {
            return null;
        }
        c1 = cards.remove(cardsLeft - 1);
        cardsLeft--;
        return c1;
    }
    // Shuffle method - shuffles entire deck
    public void shuffle()
    {
        Card temp;
        int random;
        for (int i = cards.size() - 1; i > 0;i--)
        {
            random = (int) (Math.random() * i + 1);
            temp = cards.set(random, cards.get(i));
            cards.set(i, temp);
        }
        cardsLeft = cards.size();
    }
}
