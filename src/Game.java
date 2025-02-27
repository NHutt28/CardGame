import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final int WAR_CARDS = 3;
    private final int MAX_ROUNDS = 3000;

    private Player player1;
    private Player player2;
    private Deck deck;
    private ArrayList<Card> pile;
    private Scanner input;
    private int roundCount;

    // The ranks suits and values of all cards in a normal deck
    private String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
    private int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    public Game() {
        input = new Scanner(System.in);
    }

    public void run() {
        printInstructions();
        initializeGame();

        while (true) {
            resetGame();
            playGame();
            showScore();
            if (!askToPlayAgain()) break;
        }
    }

    private void initializeGame() {
        System.out.print("What is Player One's Name: ");
        player1 = new Player(input.nextLine());
        System.out.print("What is Player Two's Name: ");
        player2 = new Player(input.nextLine());
    }

    private void resetGame() {
        roundCount = 0;
        player1.getHand().clear();
        player2.getHand().clear();
        deck = new Deck(values, ranks, suits);
        dealCards();
        pile = new ArrayList<>();
    }

    private void dealCards()
    {
        while (!deck.isEmpty())
        {
            player1.addCard(deck.deal());
            player2.addCard(deck.deal());
        }
    }

    private void playGame()
    {
        while (true)
        {
            pile.clear();
            if (!playTurn()) {
                break;
            }
            if (checkGameState()) {
                break;
            }
        }
    }

    private boolean playTurn() {
        if (player1.getHand().isEmpty() || player2.getHand().isEmpty())
        {
            return false;
        }

        Card card1 = player1.getHand().remove(0);
        Card card2 = player2.getHand().remove(0);

        pile.add(card1);
        pile.add(card2);

        System.out.println(player1.getName() + " played " + card1 + " | " + player2.getName() + " played " + card2);
        determineRoundWinner(card1, card2);
        return true;
    }

    private void determineRoundWinner(Card card1, Card card2) {
        if (card1.getValue() > card2.getValue())
        {
            System.out.println(player1.getName() + " wins!");
            for (Card b : pile)
                player1.addCard(b);
        } else if (card1.getValue() < card2.getValue()) {
            System.out.println(player2.getName() + " wins!");
            for (Card b : pile)
                player2.addCard(b);
        } else {
            System.out.println("WAR!");
            war();
        }
    }

    private void war() {
        if (player1.getHand().size() < WAR_CARDS + 1 || player2.getHand().size() < WAR_CARDS + 1) {
            System.out.println("Not enough cards for war. The player with more cards wins!");
            if (player1.getHand().size() > player2.getHand().size()) {
                for (Card b : pile)
                    player1.addCard(b);
            } else {
                for (Card b : pile)
                player2.addCard(b);
            }
            return;
        }

        for (int i = 0; i < WAR_CARDS; i++) {
            pile.add(player1.getHand().remove(0));
            pile.add(player2.getHand().remove(0));
        }

        System.out.println("Both players placed " + WAR_CARDS + " cards face down.");
        playTurn();
    }

    private boolean checkGameState() {
        roundCount++;
        if (roundCount >= MAX_ROUNDS) {
            System.out.println("DRAW");
            return true;
        }
        if (player1.getHand().isEmpty()) {
            System.out.println(player2.getName() + " wins!");
            player2.addPoints(1);
            return true;
        }
        if (player2.getHand().isEmpty()) {
            System.out.println(player1.getName() + " wins!");
            player1.addPoints(1);
            return true;
        }
        return false;
    }

    private void showScore() {
        System.out.println("\nSCORES:");
        System.out.println(player1.getName() + " has " + player1.getPoints() + " wins");
        System.out.println(player2.getName() + " has " + player2.getPoints() + " wins");
    }

    private boolean askToPlayAgain()
    {
        System.out.print("Do you want to play again? ");
        if(input.nextLine().toLowerCase().equals("no"))
        {
            return false;
        }
        return true;
    }

    private void printInstructions() {
        System.out.println("INSTRUCTIONS: \nEach player turns up a card. The player with the higher card wins both cards."
   + "\n If the cards are the same, WAR occurs: each player places three cards face down and one face up." +
 "\n The player with the higher face-up card wins all the cards."
        + "\nIf another tie occurs, WAR repeats until there’s a winner.");
    }

    public static void main(String[] args) {
       Game g1 = new Game();
       g1.run();
    }
}
