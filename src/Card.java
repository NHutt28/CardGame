public class Card {
    // Instance Variables
    private int value;
    private String rank;
    private String suit;

    // Constructor
    public Card(int value, String suit, String rank) {
        this.value = value;
        this.suit = suit;
        this.rank = rank;
    }

    // Getter and Setter Methods
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    // ToString
    public String toString() {
        return rank + " of " + suit;
    }
}
