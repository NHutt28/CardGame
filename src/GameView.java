import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame{
    // Constants
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 500;
    private final int DIMENSION = 100;
    private final int HALF = 50;
    private final int TITLE_BAR_HEIGHT = 23;
    // State instance variable
    private int state;
    // Back-end
    private Game game;
    private Image back;
    // Constructor
    public GameView(Game game) {

        this.game = game;
        this.state = 0;
        this.back = new ImageIcon("Resources/Cards/back.png").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("The Board");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    // Getter / Setter
    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(int num) {
        this.state = num;
    }

    public void paint(Graphics g)
    {
        if(state == 0)
        {
            // Draws instructions
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("WAR", 200, 100);
            g.setFont(new Font("Script", Font.BOLD, 16));
            g.drawString("Each player turns up a card. The player with the higher", 20, 150);
            g.drawString("wins both cards. If the cards are the same, WAR happens.", 20, 200);
            g.drawString("In war, players place three cards face down and one", 20, 250);
            g.drawString("face up. he player with the higher face-up card wins all ", 20, 300);
            g.drawString("the cards. If another tie occurs, WAR repeats until ", 20, 350);
            g.drawString("there's a winner. Input your names to start. ", 20, 400);
        }
        else if (state == 1)
        {
            // Makes green table for gambling
            g.setColor(new Color(53,101,77));
            g.fillRect(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
            g.setColor(new Color(255,255,255));
            g.setFont(new Font("Script", Font.BOLD, 18));
            // Sets up the board
            g.drawString(game.getPlayer1().getName(), WINDOW_WIDTH/2 - 25, 50);
            g.drawImage(back, WINDOW_WIDTH/2 - 25, 75, 50,70,this);
            g.drawImage(back, WINDOW_WIDTH/2 - 25, 375,50,70,this);
            g.drawString(game.getPlayer2().getName(), WINDOW_WIDTH/2 - 25, 475);
            if (!(game.getCard1().getImage() == null) && !(game.getCard2().getImage() == null)) {
                g.drawImage(game.getCard1().getImage(), WINDOW_WIDTH / 2 - 25, 125, 50, 70, this);
                g.drawImage(game.getCard2().getImage(), WINDOW_WIDTH / 2 - 25, 350, 50, 70, this);
            }
        }
    }
}
