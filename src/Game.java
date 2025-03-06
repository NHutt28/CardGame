import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    // Constants
    private final int WAR_CARDS = 3;
    private final int MAX_ROUNDS = 400;

    // Instance variables
    private Player player1;
    private Player player2;
    private Player winner;
    private Card card1 = null;
    private Card card2 = null;
    private Deck deck;
    private ArrayList<Card> pile;
    private Scanner input;
    private int roundCount;
    private GameView screen;
    public boolean warState = false;
    // The ranks suits and values of all cards in a normal deck
    private final String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private final String[] suits = {"Spades","Hearts", "Diamonds", "Clubs"};
    private final int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    // Getters
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Card getCard1() {
        return card1;
    }

    public Card getCard2() {
        return card2;
    }

    public Player getWinner() {
        return winner;
    }

    // Constuctor (initialize scanner)
    public Game() {
        input = new Scanner(System.in);
        screen = new GameView(this);
    }

    // Run game
    public void run() {
        // Start up game
        System.out.println(getInstructions());
        screen.repaint();
        initializeGame();
        screen.setState(1);
        screen.repaint();
        // Game goes until they don't want to play again
        while (true) {
            // Does the game
            resetGame();
            playGame();
            showWins();
            // check play again
            if (askToPlayAgain() == false)
            {
                break;
            }
        }
    }

    private void initializeGame() {
        System.out.println("Player One's Name? ");
        player1 = new Player(input.nextLine());
        System.out.println("Player Two's Name? ");
        player2 = new Player(input.nextLine());
    }

    // Resets the game after someone wins
    private void resetGame() {
        // Clears hands
        roundCount = 0;
        player1.getHand().clear();
        player2.getHand().clear();
        // makes new deck
        deck = new Deck(values, ranks, suits);
        dealCards();
        // makes new pile
        pile = new ArrayList<Card>();
        screen.repaint();
    }

    private void dealCards()
    {
        // While there are still cards in the deck, deal
        while (deck.isEmpty() != true)
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
            // if game can't be played - break
            if (doTurn() == false)
            {
                break;
            }
            // If someone won - break
            if (checkWin())
            {
                break;
            }
        }
    }

    private boolean doTurn() {

        // Checks if game over
        if (player1.getHand().isEmpty() || player2.getHand().isEmpty())
        {
            return false;
        }
        screen.repaint();
        // play down both cards
        card1 = player1.getHand().remove(0);
        card2 = player2.getHand().remove(0);

        pile.add(card1);
        pile.add(card2);
        // repaints screen
        screen.repaint();

        // I used my brother's help for this (pause mechanism)
        try
        {
            // Half a second wait time
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {

        }

        System.out.println(player1.getName() + " played " + card1 + " ||| " + player2.getName() + " played " + card2);
        // checks who won
        findRoundWinner(card1, card2);
        // continues game
        warState = false;
        return true;
    }

    private void findRoundWinner(Card card1, Card card2) {
        // See who won
        if (card1.getValue() > card2.getValue())
        {
            // Player 1 won
            winner = player1;
            System.out.println(player1.getName() + " wins!");
            // Adds winning cards to winner
            for (Card b : pile)
                player1.addCard(b);

        }
        else if (card1.getValue() < card2.getValue())
        {
            // Player 2 won
            winner = player2;
            System.out.println(player2.getName() + " wins!");
            // adds winning cards to winner
            for (Card b : pile)
                player2.addCard(b);
        }
        else
        {
            // Enters war
            System.out.println("WAR!");
            war();
        }
    }

    private void war()
    {
        // If there's not enough cards
        warState = true;
        if (player1.getHand().size() < WAR_CARDS + 1 || player2.getHand().size() < WAR_CARDS + 1)
        {
            warState = false;
            System.out.println("Not enough cards for war \nGame Over.");
            if (player1.getHand().size() > player2.getHand().size())
            {
                // adds all cards to the winner to end game
                for (Card b : pile)
                {
                    player1.addCard(b);
                }
            }
            else
            {
                for (Card b : pile)
                {
                    player2.addCard(b);
                }

            }
            // Ends game
            checkWin();
            screen.repaint();
            return;
        }
        // else just play the war
        for (int i = 0; i < WAR_CARDS; i++)
        {
            pile.add(player1.getHand().remove(0));
            pile.add(player2.getHand().remove(0));
        }
        // Does normal round with more cards in the pile
        System.out.println("Both players placed " + WAR_CARDS + " cards face down.");
        doTurn();
    }

    private boolean checkWin() {
        // Check if it goes on way too long (Draw)
        roundCount++;
        if (roundCount >= MAX_ROUNDS)
        {
            System.out.println("DRAW");
            screen.setState(2);
            screen.repaint();
            return true;
        }
        // Check who won by seeing if hand is empty
        if (player1.getHand().isEmpty())
        {
            System.out.println(player2.getName() + " wins the GAME!");
            player2.addPoints(1);
            screen.setState(2);
            screen.repaint();
            return true;
        }
        if (player2.getHand().isEmpty()) {
            System.out.println(player1.getName() + " wins the GAME!");
            player1.addPoints(1);
            screen.setState(2);
            screen.repaint();
            return true;
        }
        return false;
    }

    // Show the scores after the round
    private void showWins() {
        System.out.println("\nSCORES:");
        System.out.println(player1.getName() + " has " + player1.getPoints() + " wins");
        System.out.println(player2.getName() + " has " + player2.getPoints() + " wins");
    }

    private boolean askToPlayAgain()
    {
        // Ask to play again
        System.out.print("Do you want to play again? ");
        if(input.nextLine().toLowerCase().equals("no"))
        {
            return false;
        }
        screen.setState(1);
        screen.repaint();
        return true;
    }

    // printInstructions
    public String getInstructions() {
        return ("WAR: Each player turns up a card. The player with the higher card wins both cards."
   + "\nIf the cards are the same, WAR happens. In WAR, each player places three cards face down and one face up." +
 "\nThe player with the higher face-up card wins all the cards."
        + "\nIf another tie occurs, WAR repeats until there’s a winner.");
    }

    public static void main(String[] args) {
        // Main
       Game g1 = new Game();
       g1.run();
    }
}
