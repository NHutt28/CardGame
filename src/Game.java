import java.util.Scanner;

public class Game {
    private Deck a;
    private Player p1;
    private Player p2;
    public Game() {
        Scanner input = new Scanner(System.in);
        System.out.println("What is Player One's Name?");
        String name = input.nextLine();
        Player a = new Player(name);
        System.out.println("What is Player Two's Name?");
        name = input.nextLine();
        Player b = new Player(name);
        String[] ranks = {"Ace", "2", "3", "4","5", "6", "7", "8","9", "10", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        Deck deck = new Deck(values, ranks, suits);
        for (int i = 0; i < 26; i++) {
            a.addCard(deck.deal());
            b.addCard(deck.deal());
        }
        System.out.println(a);
        System.out.println(b);



    }
    public Deck getDeck() {
        return a;
    }

    public static void main(String[] args) {
        Game x = new Game();
    }
}
