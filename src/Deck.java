import java.util.ArrayList;

public class Deck {
    // Instance variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    //Constructor
    public Deck(int[] values, String[] ranks, String[] suits) {
        cards = new ArrayList<Card>();
            for (String s : suits) {
                for (int i = 0; i < ranks.length; i++) {
                    cards.add(new Card(values[i], s, ranks[i]));
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
    // getter method for cardsLeft
    public int getCardsLeft() {
        return cardsLeft;
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
    // getter method for cards (arraylist)
    public ArrayList<Card> getCards() {
        return cards;
    }
}
