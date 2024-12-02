import java.util.ArrayList;

public class Player {
    public ArrayList<Card> hand;
    private String name;
    private int points;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.points = 0;
    }

    public Player(ArrayList<Card> hand, String name) {
        this.hand = hand;
        this.name = name;
        this.points = 0;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
    public void addPoints(int x)
    {
        points+=x;
    }
    public void addCard(Card y)
    {
        hand.add(y);
    }

    public String toString() {
        return name + " has " + points + " wins.\n" + name + "'s cards: " + hand;
    }
}
