import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Deck a;
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
        Card p1;
        Card p2;
        Card w1;
        Card w2;
        ArrayList<Card> pile = new ArrayList<>();
        while((!a.getHand().isEmpty()) && (!b.getHand().isEmpty()))
        {
            pile.clear();
            p1 = a.hand.remove(0);
            p2 = b.hand.remove(0);

            pile.add(p1);
            System.out.print(a.getName() + " played " + p1);
            pile.add(p2);
            System.out.println("\t" + b.getName() + " played " + p2);

            if (p1.getValue() > p2.getValue())
            {
                System.out.println(a.getName() + " wins!");
                for (Card x : pile)
                {
                    a.addCard(x);
                }
            }
            else if (p1.getValue() == p2.getValue())
            {
                war(pile, a, b);
            }
            else
            {
                System.out.println(b.getName() + " wins!");
                for (Card x : pile)
                {
                    b.addCard(x);
                }
            }
        }


    }
    public void war(ArrayList<Card> pile, Player a, Player b)
    {
        Card w1;
        Card w2;
        System.out.println("WAR!!");
        for (int i = 0; i < 3; i++)
        {
            pile.add(a.getHand().remove(0));
            pile.add(b.getHand().remove(0));
        }
        System.out.println(a.getName() + " played 3 cards face down");
        System.out.println(b.getName() + " played 3 cards face down");
        w1 = a.getHand().remove(0);
        System.out.println(a.getName() + " played " + w1);
        w2 = b.getHand().remove(0);
        System.out.println(b.getName() + " played " + w2);
        if (w1.getValue() > w2.getValue())
        {
            System.out.println(a.getName() + " wins!");
            for (Card x : pile)
            {
                a.addCard(x);
            }
        }
        else if (w1.getValue() == w2.getValue())
        {
            war(pile, a, b);
        }
        else
        {
            System.out.println(b.getName() + " wins!");
            for (Card x : pile)
            {
                b.addCard(x);
            }
        }
        pile.clear();

    }

    public static void main(String[] args) {
        Game x = new Game();
    }
}
