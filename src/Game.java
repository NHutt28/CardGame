import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    // Constants and instance variables
    public static int limit = 3000;
    private Player a;
    private Player b;
    private Scanner input = new Scanner(System.in);
    private ArrayList<Card> pile;
    private int infCount = 0;
    private String playAgain;
    private Card p1;
    private Card p2;
    private String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
    private int values[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    public Game() {
        printInstructions();
        System.out.println("What is Player One's Name?");
        String name = input.nextLine();
        a = new Player(name);
        System.out.println("What is Player Two's Name?");
        name = input.nextLine();
        b = new Player(name);

        while(true) {
            infCount = 0;
            a.getHand().clear();
            b.getHand().clear();
            Deck deck = new Deck(values, ranks, suits);
            for (int i = 0; i < (deck.getCardsLeft() / 2); i++) {
                a.addCard(deck.deal());
                b.addCard(deck.deal());
            }
            pile = new ArrayList<Card>();
            playRound();
            System.out.println("\n<><><><><><><><><>\n" + a.getName() + " now has " + a.getPoints() + " win(s)!\n" + "<><><><><><><><><>\n");
            System.out.println("<><><><><><><><><>\n" + b.getName() + " now has " + b.getPoints() + " win(s)!\n" + "<><><><><><><><><>\n");
            if (playAgainCheck())
            {
                break;
            }

        }
    }
    public void printInstructions()
    {
        System.out.println("INSTRUCTIONS: \n" +
                "The deck is divided evenly, with each player receiving 26 cards, dealt one at a time, face down. \n" +
                "Anyone may deal first. Each player places their stack of cards face down, in front of them. \n" +
                "Each player turns up a card at the same time and the player with the higher card " +
                "takes both cards and puts them, face down, on the bottom of his stack.\n" +
                "If the cards are the same rank, it is War. Each player plays three cards face down and one card" +
                " face up. The player with the higher card faced up takes both piles. \n" +
                "If the turned-up cards are " +
                "again the same rank, each player places another card face down and turns another card face up. \n" +
                "The player with the higher card takes all 10 cards, and so on.");
    }
    public boolean playAgainCheck()
    {
        System.out.println("Would you like to play again? (Type no if not)");
        playAgain = input.nextLine();
        if (playAgain.toLowerCase().equals("no"))
        {
            return true;
        }
        return false;
    }
    public void playRound()
    {
        while (true) {
            pile.clear();
            p1 = a.hand.remove(0);
            p2 = b.hand.remove(0);
            pile.add(p1);
            System.out.print(a.getName() + " played " + p1);
            pile.add(p2);
            System.out.println("\t" + b.getName() + " played " + p2);
            winnerMechanism();
            if(drawCheck()){
                break;
            } else if (a.getHand().isEmpty()) {
                b.addPoints(1);
                break;
            } else if (b.getHand().isEmpty()) {
                a.addPoints(1);
                break;
            }
        }
    }
    public boolean drawCheck()
    {
        infCount++;
        if (infCount > limit) {
            System.out.println("\n***************DRAW!***************");
            return true;
        }
        return false;
    }
    public void winnerMechanism()
    {
        if (p1.getValue() > p2.getValue()) {
            System.out.println(a.getName() + " wins!");
            for (Card y : pile) {
                a.addCard(y);
            }
        } else if (p1.getValue() == p2.getValue()) {
            war();
        } else {
            System.out.println(b.getName() + " wins!");
            for (Card y : pile) {
                b.addCard(y);
            }
        }
    }
    public void war()
    {
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

        p1 = a.getHand().removeFirst();
        System.out.println(a.getName() + " played " + p1);
        p2 = b.getHand().removeFirst();
        System.out.println(b.getName() + " played " + p2);
        winnerMechanism();
        pile.clear();

    }

    public static void main(String[] args) {
        Game x = new Game();
    }
}
