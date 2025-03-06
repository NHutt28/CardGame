import javax.swing.*;
import java.awt.*;

public class Card {
    // Instance Variables
    private int value;
    private String rank;
    private String suit;
    private Image image;

    // Constructor
    public Card(int value, String suit, String rank, String card)
    {
        this.value = value;
        this.suit = suit;
        this.rank = rank;
        this.image = new ImageIcon("Resources/Cards/" + card + ".png").getImage();

    }
    // Getter and Setter Methods
    public Image getImage() {
        return image;
    }

    public int getValue()
    {
        return value;
    }
    // ToString
    public String toString()
    {
        return rank + " of " + suit;
    }
}
