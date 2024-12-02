import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public Game() {
        Scanner input = new Scanner(System.in);
        System.out.println("What is Player One's Name?");
        String name = input.nextLine();
        Player a = new Player(name);
        System.out.println("What is Player Two's Name?");
        name = input.nextLine();
        Player b = new Player(name);
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        while(true) {

            Deck deck = new Deck(values, ranks, suits);
            for (int i = 0; i < 26; i++) {
                a.addCard(deck.deal());
                b.addCard(deck.deal());
            }
            Card p1;
            Card p2;
            Player winner;
            ArrayList<Card> pile = new ArrayList<>();
            while ((!a.getHand().isEmpty()) && (!b.getHand().isEmpty())) {
                pile.clear();
                p1 = a.hand.removeFirst();
                p2 = b.hand.removeFirst();

                pile.add(p1);
                System.out.print(a.getName() + " played " + p1);
                pile.add(p2);
                System.out.println("\t" + b.getName() + " played " + p2);

                if (p1.getValue() > p2.getValue()) {
                    System.out.println(a.getName() + " wins!");
                    for (Card y : pile) {
                        a.addCard(y);
                    }
                } else if (p1.getValue() == p2.getValue()) {
                    war(pile, a, b);
                } else {
                    System.out.println(b.getName() + " wins!");
                    for (Card y : pile) {
                        b.addCard(y);
                    }
                }
            }
            if(a.getHand().isEmpty())
            {
                winner = b;
                b.addPoints(1);
            }
            else
            {
                winner = a;
                a.addPoints(1);
            }
            System.out.println("\n<><><><><><><><><>\n" + winner.getName() + " now has " + winner.getPoints() + " win(s)!\n" + "<><><><><><><><><>\n");
            break;
        }
    }
    public void war(ArrayList<Card> pile, Player a, Player b)
    {
        Card w1;
        Card w2;
        int lowerHand;
        int count = 0;
        System.out.println("WAR!!");
        if (a.getHand().isEmpty() || b.getHand().isEmpty())
        {
            return;
        }
        else if(a.getHand().size() <= 4 || b.getHand().size() <= 4)
        {
            lowerHand = a.getHand().size();
            if(b.getHand().size() < lowerHand)
            {
                lowerHand = b.getHand().size();
            }
            for (int i = 0; i < lowerHand - 1; i++)
            {
                count++;
                pile.add(a.getHand().removeFirst());
                pile.add(b.getHand().removeFirst());
            }
            System.out.println(a.getName() + " played " + count + " cards face down");
            System.out.println(b.getName() + " played " + count + " cards face down");
        }
        else
        {
            for (int i = 0; i < 3; i++)
            {
                pile.add(a.getHand().removeFirst());
                pile.add(b.getHand().removeFirst());
            }
            System.out.println(a.getName() + " played 3 cards face down");
            System.out.println(b.getName() + " played 3 cards face down");
        }

        w1 = a.getHand().removeFirst();
        System.out.println(a.getName() + " played " + w1);
        w2 = b.getHand().removeFirst();
        System.out.println(b.getName() + " played " + w2);
        if (w1.getValue() > w2.getValue())
        {
            System.out.println(a.getName() + " wins!");
            for (Card y : pile)
            {
                a.addCard(y);
            }
        }
        else if (w1.getValue() == w2.getValue())
        {
            war(pile, a, b);
        }
        else
        {
            System.out.println(b.getName() + " wins!");
            for (Card y : pile)
            {
                b.addCard(y);
            }
        }
        pile.clear();

    }

    public static void main(String[] args) {
        Game x = new Game();
    }
}
