import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(int[] values, String[] ranks, String[] suits) {

            for (String s : suits) {
                for (int i = 0; i < ranks.length; i++) {
                    cards.add(new Card(values[i], s, ranks[i]));
                }
            }
// cards.shuffle

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


}
