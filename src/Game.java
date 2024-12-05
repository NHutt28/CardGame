import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    // Constants and instance variables
    // Three is the normal amount of cards played faced down for a war
    public static int three = 3;
    // limit makes sure that the game isn't infinite
    public static int limit = 3000;
    // infCount will check how many rounds has been played
    private int infCount = 0;
    // Players a and b are the ones playing war
    private Player a;
    private Player b;
    // Scanner for checking if they want to play again and the names
    private Scanner input = new Scanner(System.in);
    private String playAgain;
    // The pile of cards played - will go to the winner
    private ArrayList<Card> pile;
    // Cards p1 and p2 are the cards played by each player
    private Card p1;
    private Card p2;

    // The ranks suits and values of all cards in a normal deck
    private String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
    private int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    // The normal deck.
    private Deck deck;

    // The game method - this is the entire game
    public Game() {
        // Prints instructions to the game and sets up the players
        printInstructions();
        System.out.println("What is Player One's Name?");
        String name = input.nextLine();
        a = new Player(name);
        System.out.println("What is Player Two's Name?");
        name = input.nextLine();
        b = new Player(name);
        while(true) {
            // Resets everything as the condition will only break if they don't want to play again
            infCount = 0;
            a.getHand().clear();
            b.getHand().clear();
            deck = new Deck(values, ranks, suits);
            for (int i = 0; i < (deck.getCardsLeft() / 2); i++) {
                a.addCard(deck.deal());
                b.addCard(deck.deal());
            }
            pile = new ArrayList<Card>();
            // Plays every round
            playRound();
            // Finishing message - shows wins
            System.out.println("\n<><><><><><><><><>\n" + a.getName() + " now has " + a.getPoints() + " win(s)!\n" + "<><><><><><><><><>\n");
            System.out.println("<><><><><><><><><>\n" + b.getName() + " now has " + b.getPoints() + " win(s)!\n" + "<><><><><><><><><>\n");
            // Checks if person wants to play again
            if (playAgainCheck())
            {
                break;
            }

        }
    }
    public void printInstructions()
    {
        // Prints out the instructions to War.
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
        // Asks user if they'd like to play again - if no then it will not play again.
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
        // While nobody has one this is true.
        while (true) {
            // Clears pile after someone wins all the cards inside.
            pile.clear();
            // Plays top card of each player
            p1 = a.hand.remove(0);
            p2 = b.hand.remove(0);
            pile.add(p1);
            System.out.print(a.getName() + " played " + p1);
            pile.add(p2);
            System.out.println("\t" + b.getName() + " played " + p2);
            // Checks who won the individual fight.
            winnerMechanism();
            // Checks if it is too long and should be a draw or if someone has won.
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
        // Checks if game has been going for too long
        infCount++;
        if (infCount > limit) {
            System.out.println("\n***************DRAW!***************");
            return true;
        }
        // If it hasn't been going for too long it will be okay to keep playing
        return false;
    }
    public void winnerMechanism()
    {
        // If the player 1s card is worth more they win and vice versa
        if (p1.getValue() > p2.getValue()) {
            System.out.println(a.getName() + " wins!");
            for (Card y : pile) {
                a.addCard(y);
            }
        } else if (p1.getValue() == p2.getValue()) {
            // If cards are equal war is activated
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
        // Variables to help if someone doesn't have enough cards to play war.
        int lowerHand;
        int count = 0;
        // If the last card is played in war and another war happens, you need this to prevent a war with no cards
        if (a.getHand().isEmpty() || b.getHand().isEmpty())
        {
            return;
        }
        // Prints out war
        System.out.println("WAR!!");
        // If a player doesn't have enough cards left for a full war this will make it so that they play as many as they can
        if(a.getHand().size() <= 4 || b.getHand().size() <= 4)
        {
            // Checks who has fewer cards
            lowerHand = a.getHand().size();
            if(b.getHand().size() < lowerHand)
            {
                lowerHand = b.getHand().size();
            }
            // For as many cards as the lower person has, they'll play all but one faced down
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
            // Normally you'd just play 3 faced down
            for (int i = 0; i < three; i++)
            {
                pile.add(a.getHand().removeFirst());
                pile.add(b.getHand().removeFirst());
            }
            System.out.println(a.getName() + " played 3 cards face down");
            System.out.println(b.getName() + " played 3 cards face down");
        }
        // Prints out the face up cards
        p1 = a.getHand().removeFirst();
        System.out.println(a.getName() + " played " + p1);
        p2 = b.getHand().removeFirst();
        System.out.println(b.getName() + " played " + p2);

        //checks who won
        winnerMechanism();
    }

    public static void main(String[] args) {
        // Creates a new game
        new Game();
    }
}
