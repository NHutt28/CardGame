import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

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

    public boolean isEmpty() {
         if (cardsLeft == 0)
         {
             return true;
         }
         return false;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }
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

    public ArrayList<Card> getCards() {
        return cards;
    }
}
