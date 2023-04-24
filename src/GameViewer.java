import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame
{
    private Game game;
    private int WINDOW_WIDTH = 1200;
    private int WINDOW_HEIGHT = 800;
    public static Image boardPic;

    public GameViewer(Game gm)
    {
        game = gm;
        boardPic = new ImageIcon("Resources/Snakes_And_Ladders_Board_Picture.png").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Snakes & Ladders");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g)
    {

    }
}
