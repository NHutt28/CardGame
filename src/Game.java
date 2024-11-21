public class Game {

    private Deck a;
    public Game(Deck deck) {
        Deck a = deck;
        System.out.println(a.getCards());
    }

    public Deck getDeck() {
        return a;
    }

    public static void main(String[] args) {
        Player b = new Player("Neil");
        String[] ranks = {"Ace", "2", "3", "4","5", "6", "7", "8","9", "10", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        Deck deck = new Deck(values, ranks, suits);
        Game x = new Game(deck);
        b.addCard(deck.deal());
        b.addCard(deck.deal());
        System.out.println(b);
    }
}
