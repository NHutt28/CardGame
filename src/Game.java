import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public Game() {
        Scanner input = new Scanner(System.in);
        String playAgain;
        int infCount = 0;
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
            infCount = 0;
            a.getHand().clear();
            b.getHand().clear();
            Deck deck = new Deck(values, ranks, suits);
            for (int i = 0; i < 26; i++) {
                a.addCard(deck.deal());
                b.addCard(deck.deal());
            }
            Card p1;
            Card p2;
            ArrayList<Card> pile = new ArrayList<Card>();
            while (true) {
                pile.clear();
                p1 = a.hand.remove(0);
                p2 = b.hand.remove(0);

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
                infCount++;
                if (infCount > 2000) {
                    System.out.println("\n***************DRAW!***************");
                    break;
                } else if (a.getHand().isEmpty()) {
                    b.addPoints(1);
                    break;
                } else if (b.getHand().isEmpty()) {
                    a.addPoints(1);
                    break;
                }
            }
            System.out.println("\n<><><><><><><><><>\n" + a.getName() + " now has " + a.getPoints() + " win(s)!\n" + "<><><><><><><><><>\n");
            System.out.println("<><><><><><><><><>\n" + b.getName() + " now has " + b.getPoints() + " win(s)!\n" + "<><><><><><><><><>\n");
            System.out.println("Would you like to play again? (Type no if not)");
            playAgain = input.nextLine();
            if (playAgain.toLowerCase().equals("no"))
            {
                break;
            }

        }
    }
    public void war(ArrayList<Card> pile, Player a, Player b)
    {
        Card w1;
        Card w2;
        int lowerHand;
        int count = 0;
        if (a.getHand().isEmpty() || b.getHand().isEmpty())
        {
            return;
        }
        System.out.println("WAR!!");
        if(a.getHand().size() <= 4 || b.getHand().size() <= 4)
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
