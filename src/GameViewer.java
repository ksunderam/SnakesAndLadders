import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class GameViewer extends JFrame implements MouseListener, KeyListener
{
    private Game game;
    private int WINDOW_WIDTH = 1200;
    private int WINDOW_HEIGHT = 800;
    public static Image boardPic;
    public static Image snake;
    public static Image die3;
    public static Image die6;
    public static Image die8;
    private int x;
    private int a;
    private int numPlayers;
    private int dieSides;
    private boolean hasChosenPlayers;
    private boolean hasChosenDie;
    private int y;
    private static final int WELCOME_SCREEN = 18;
    private static final int CHOOSE_PLAYERS = 36;
    private static final int PLAYING = 54;
    private static final int CHOOSE_DIE = 72;
    private static final int GAME_OVER = 90;
    private static int state = WELCOME_SCREEN;

    public GameViewer(Game gm)
    {
        a = -400;
        hasChosenDie = false;
        hasChosenPlayers = false;
        x = -300;
        numPlayers = 0;
        dieSides = 0;
        y = 0;
        game = gm;
        boardPic = new ImageIcon("Resources/Snakes_And_Ladders_Board_Picture.png").getImage();
        snake = new ImageIcon("Resources/Snake.png").getImage();
        die3 = new ImageIcon("Resources/3_Sided_Die.png").getImage();
        die6 = new ImageIcon("Resources/6_Sided_Die.png").getImage();
        die8 = new ImageIcon("Resources/8_Sided_Die.png").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Snakes & Ladders");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        addMouseListener(this);
        addKeyListener(this);
        setVisible(true);
    }

    public int getNumPlayers()
    {
        return numPlayers;
    }

    public int getDieSides()
    {
        return dieSides;
    }

//    public void actionPerformed(ActionEvent e)
//    {
//        if (state == WELCOME_SCREEN)
//        {
//
//        }
//        else if (state == PLAYING)
//        {
//            // b.move();
//            // maybe a for loop
//            // other stuff
//
//
//        }
//
//
//
//        repaint();
//    }


    public void paintInstructions(Graphics g)
    {
        g.drawImage(snake, 190, 20, 800, 297, this);
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(20f));
        g.drawString("This is Snakes and Ladders.", 466, 330);
        g.drawString("To play, first pick the amount of players. You can only have up to 4!", 275, 360);
        g.drawString("You can also choose one player, and just play against yourself.", 307, 390);
        g.drawString("For the actual game, everyone will start at the beginning of the board.", 272, 420);
        g.drawString("Each player will take turns rolling the die to advance their position.", 281, 450);
        g.drawString("Whoever makes it to the final position on the board wins!", 330, 480);
        g.drawString("But beware: at some positions, you'll snake back down the board.", 293, 510);
        g.drawString("At other positions, you'll take a ladder up the board.", 355, 540);
        g.drawString("In order to increase your chances at hitting the right square,", 319, 570);
        g.drawString("choose from a 3, 6, or 8 sided die", 435, 600);
        g.drawString("Good Luck!", 541, 630);
        g.setFont(g.getFont().deriveFont(17f));
        g.drawString("To move on and choose the number of players, press SPACE", 342, 750);
    }
    public void paintNumbers(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(42f));
        String s = "𝙿𝚕𝚎𝚊𝚜𝚎 𝚜𝚎𝚕𝚎𝚌𝚝 𝚝𝚑𝚎 𝚗𝚞𝚖𝚋𝚎𝚛 𝚘𝚏 𝚙𝚕𝚊𝚢𝚎𝚛𝚜 𝚠𝚒𝚝𝚑 𝚢𝚘𝚞𝚛 𝚖𝚘𝚞𝚜𝚎:";
        g.drawString(s, 40, 100);
        g.setFont(g.getFont().deriveFont(500f));
        g.setColor(new Color(196, 0, 0));
        g.drawString("\uD835\uDFD9", 10, 500);
        g.setColor(new Color(0, 27, 159));
        g.drawString("\uD835\uDFDA", 267, 500);
        g.setColor(new Color(6, 101, 0));
        g.drawString("\uD835\uDFDB", 590, 500);
        g.setColor(new Color(224, 188, 0));
        g.drawString("\uD835\uDFDC", 910, 500);
    }
    public void paintSquare(Graphics g)
    {
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(6));
        g.drawLine(5 + x, 510, 270 + x, 510);
        g.drawLine(5 + x, 510, 5 + x, 166);
        g.drawLine(270 + x, 166, 5 + x, 166);
        g.drawLine(270 + x, 166, 270 + x, 510);

//        g.drawLine(WINDOW_WIDTH / 3, WINDOW_HEIGHT / 3 * 2, WINDOW_WIDTH / 3 * 2, WINDOW_HEIGHT / 3 * 2);
//        g.drawLine(WINDOW_WIDTH / 3 * 2, WINDOW_HEIGHT / 3 * 2, WINDOW_WIDTH / 3 * 2, WINDOW_HEIGHT / 3);
//        g.drawLine(WINDOW_WIDTH / 3 * 2, WINDOW_HEIGHT / 3, WINDOW_WIDTH / 3, WINDOW_HEIGHT / 3);
    }

    public void paintGame(Graphics g)
    {
//            g.drawImage(boardPic, 25, 25, this); orginal
        g.drawImage(boardPic, 25, 30, this);
        g.setColor(Color.BLACK);
        g.fillRect(300 + y, 300, 50, 50);
        //x += 20;
        g.setColor(Color.CYAN);
        g.drawLine(WINDOW_WIDTH / 3, WINDOW_HEIGHT / 3, WINDOW_WIDTH / 3, WINDOW_HEIGHT / 3 * 2);
        g.drawLine(WINDOW_WIDTH / 3, WINDOW_HEIGHT / 3 * 2, WINDOW_WIDTH / 3 * 2, WINDOW_HEIGHT / 3 * 2);
        g.drawLine(WINDOW_WIDTH / 3 * 2, WINDOW_HEIGHT / 3 * 2, WINDOW_WIDTH / 3 * 2, WINDOW_HEIGHT / 3);
        g.drawLine(WINDOW_WIDTH / 3 * 2, WINDOW_HEIGHT / 3, WINDOW_WIDTH / 3, WINDOW_HEIGHT / 3);
        //for (Player player : game.getPlayers())
        for (int i = 0; i < 4; i++)
        {
            //game.getPlayer(i).drawPlayer(g);
            game.getPlayer(i).drawPlayer(g);
            System.out.println("BLAH");
        }
        g.setColor(Color.RED);
        g.drawRect(25, 30, 128, 128);
        g.drawRect(25, 670, 128, 128);
        g.drawRect(1048, 30, 128, 128);
        g.drawRect(1048, 670, 128, 128);

//            g.drawLine(25, 30, 25, 150);
//            g.drawLine(25, 150, 150, 30);
//            g.drawLine(150, 30, 150, 150);
//            g.drawLine(150, 150, 25, 150);
    }

    public void paintSquareDie(Graphics g)
    {
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(6));
//        g.drawLine(100 + a, 450, 270 + a, 450);
//        g.drawLine(100 + a, 450, 100 + a, 250);
//        g.drawLine(270 + a, 166, 100 + a, 250);
//        g.drawLine(270 + a, 166, 270 + a, 450);
        g.drawRect(163 + a, 175, 200, 240);
    }




    public void paint(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        if (state == WELCOME_SCREEN)
        {
            paintInstructions(g);
        }
        else if (state == CHOOSE_PLAYERS)
        {
            paintNumbers(g);
            paintSquare(g);
            if (hasChosenPlayers)
            {
                g.setColor(Color.BLACK);
                g.setFont(g.getFont().deriveFont(25f));
                String s = "You've chosen " + numPlayers + " players to play with. Press ENTER to move on to the game.";
                g.drawString(s, 150, 750);
            }
        }
        else if (state == PLAYING)
        {
            paintGame(g);
            a = -400;
            hasChosenDie = false;
            if (game.isGameOver())
            {
                state = GAME_OVER;
            }

        }
        else if (state == CHOOSE_DIE)
        {
//            setOpacity(0.5F);
//            paintGame(g);

            g.setColor(Color.BLACK);
            //hasChosenDie = false;
            g.setFont(g.getFont().deriveFont(44f));
            String s = "𝚆𝚑𝚒𝚌𝚑 𝚘𝚏 𝚝𝚑𝚎𝚜𝚎 𝚍𝚒𝚎𝚜 𝚍𝚘 𝚢𝚘𝚞 𝚠𝚊𝚗𝚝 𝚝𝚘 𝚛𝚘𝚕𝚕?";
            g.drawString(s, 166, 100);
            s = "𝙲𝚑𝚘𝚘𝚜𝚎 𝚜𝚝𝚛𝚊𝚝𝚎𝚐𝚒𝚌𝚊𝚕𝚕𝚢!";
            g.drawString(s, 342, 150);
            g.setFont(g.getFont().deriveFont(333f));
            g.setColor(new Color(247,185,108));
            g.drawString("\uD835\uDFF9", 165, 400);
            g.setColor(new Color(234,161,83));
            g.drawString("\uD835\uDFFC", 510, 400);
            g.setColor(new Color(221,138,60));
            g.drawString("\uD835\uDFFE", 860, 400);
            g.drawImage(die3, 88, 435, 344, 302, this);
            g.drawImage(die6, 417, 435, 380, 261/*.25*/, this);
            g.drawImage(die8, 825, 435, 286, 300, this);
            paintSquareDie(g);
            if (hasChosenDie)
            {
                g.setColor(Color.BLACK);
                g.setFont(g.getFont().deriveFont(25f));
                s = "You've chosen a " + dieSides + " sided die. Press ENTER to roll it.";
                g.drawString(s, 300, 775);
            }


        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        System.out.println("ItWorks");
        if (state == WELCOME_SCREEN)
        {
            if (e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                state = CHOOSE_PLAYERS;
            }
        }
        else if (state == CHOOSE_PLAYERS)
        {
            if (e.getKeyCode() == KeyEvent.VK_ENTER && hasChosenPlayers)
            {
                state = PLAYING;
            }
        }
        else if (state == PLAYING)
        {
            if (e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                state = CHOOSE_DIE;
            }
        }
        else if (state == CHOOSE_DIE)
        {
            if (e.getKeyCode() == KeyEvent.VK_ENTER && hasChosenDie)
            {
                state = PLAYING;
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (state == PLAYING)
        {
            if (e.getX() >= WINDOW_WIDTH / 3 && e.getX() <= WINDOW_WIDTH / 1.5 && e.getY() >= WINDOW_HEIGHT / 3 && e.getY() <= WINDOW_HEIGHT / 1.5)
            {
                y += 20;
            }
        }
        else if (state == CHOOSE_PLAYERS)
        {
            if (e.getX() >= 5 && e.getX() <= 270 && e.getY() >= 166 && e.getY() <= 540)
            {
                hasChosenPlayers = true;
                numPlayers = 1;
                x = 0;
            }
            else if (e.getX() >= 267 && e.getX() <= 532 && e.getY() >= 166 && e.getY() <= 540)
            {
                hasChosenPlayers = true;
                numPlayers = 2;
                x = 262;
            }
            else if(e.getX() >= 591 && e.getX() <= 856 && e.getY() >= 166 && e.getY() <= 540)
            {
                hasChosenPlayers = true;
                numPlayers = 3;
                x = 586;
            }
            else if(e.getX() >= 915 && e.getX() <= 1180 && e.getY() >= 166 && e.getY() <= 540)
            {
                hasChosenPlayers = true;
                numPlayers = 4;
                x = 910;
            }
        }
        else if (state == CHOOSE_DIE)
        {
            //163 + a, 225, 200, 240
            if (e.getX() >= 163 && e.getX() <= 363 && e.getY() >= 175 && e.getY() <= 415)
            {
                hasChosenDie = true;
                dieSides = 3;
                a = 0;
            }
            //348
            else if (e.getX() >= 511 && e.getX() <= 711 && e.getY() >= 175 && e.getY() <= 415)
            {
                hasChosenDie = true;
                dieSides = 6;
                a = 348;
            }
            else if(e.getX() >= 860 && e.getX() <= 1060 && e.getY() >= 175 && e.getY() <= 415)
            {
                hasChosenDie = true;
                dieSides = 8;
                a = 697;
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}