import java.util.ArrayList;


public class Player {
    // Instance Variables
    public ArrayList<Card> hand;
    private String name;
    private int points;

    // Constructor for just name
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.points = 0;
    }

    // Getters
    public ArrayList<Card> getHand() {
        return hand;
    }
    public int getHandSize()
    {
        return hand.size();
    }
    public String getName() {
        return name;
    }
    public int getPoints() {
        return points;
    }

    // Adds point to player
    public void addPoints(int x)
    {
        points+=x;
    }
    // Adds card to hand
    public void addCard(Card y)
    {
        hand.add(y);
    }
    // To string
    public String toString() {
        return name + " has " + points + " wins.\n" + name + "'s cards: " + hand;
    }
}
