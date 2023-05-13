// Kayan Sunderam

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameViewer extends JFrame implements MouseListener, KeyListener
{
    // Instance of the game to connect viewer with the back end
    private Game game;
    private int WINDOW_WIDTH = 1200;
    private int WINDOW_HEIGHT = 800;
    // Images that will be drawn throughout the game
    public static Image boardPic;
    public static Image snake;
    public static Image die3;
    public static Image die6;
    public static Image die8;
    public static Image snakeTrophy;
    // To track whose turn it is
    private int playerMod;
    // For choosing the number of players--where to draw the box
    private int x;
    // For choosing the die sides--where to draw the box
    private int a;
    private int numPlayers;
    // Will reset with each turn
    private int dieSides;
    // To know when to draw the boxes indicating what has been chosen
    private boolean hasChosenPlayers;
    private boolean hasChosenDie;
    private Player winner;
    // Game states to determine what to paint on the screen
    private static final int WELCOME_SCREEN = 18;
    private static final int CHOOSE_PLAYERS = 36;
    private static final int PLAYING = 54;
    private static final int CHOOSE_DIE = 72;
    private static final int GAME_OVER = 90;
    private static final int DIE_RESULT = 108;
    private static int state = WELCOME_SCREEN;

    public GameViewer(Game gm)
    {
        // So boxes are off the screen initially
        a = -400;
        x = -300;
        hasChosenDie = false;
        hasChosenPlayers = false;
        numPlayers = 0;
        dieSides = 0;
        playerMod = 0;
        game = gm;
        boardPic = new ImageIcon("Resources/Snakes_And_Ladders_Board_Picture.png").getImage();
        snake = new ImageIcon("Resources/Snake.png").getImage();
        die3 = new ImageIcon("Resources/3_Sided_Die.png").getImage();
        die6 = new ImageIcon("Resources/6_Sided_Die.png").getImage();
        die8 = new ImageIcon("Resources/8_Sided_Die.png").getImage();
        snakeTrophy = new ImageIcon("Resources/Snake_Trophy.png").getImage();
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

    // First screen, which just tells players the instructions
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

    // Prints numbers for players to choose the number of people playing
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

    // Paints the square that indicates which number has been chosen for the number of players
    public void paintSquare(Graphics g)
    {
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(6));
        g.drawLine(5 + x, 510, 270 + x, 510);
        g.drawLine(5 + x, 510, 5 + x, 166);
        g.drawLine(270 + x, 166, 5 + x, 166);
        g.drawLine(270 + x, 166, 270 + x, 510);
    }

    // Draws the board, player pieces, and information for how to roll
    public void paintGame(Graphics g)
    {
        g.drawImage(boardPic, 25, 30, this);
        for (Player player : game.getPlayers())
        {
            player.drawPlayer(g);
        }
        g.setColor(Color.RED);
        g.setFont(g.getFont().deriveFont(19f));
        String s = "𝐏𝐫𝐞𝐬𝐬 𝐒𝐏𝐀𝐂𝐄 𝐭𝐨 𝐫𝐨𝐥𝐥 𝐭𝐡𝐞 𝐃𝐢𝐞";
        g.drawString(s, 931, 793);
    }

    // Paints the square that indicates which die a player wants to roll
    public void paintSquareDie(Graphics g)
    {
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(6));
        g.drawRect(163 + a, 175, 200, 240);
    }

    // Confirms the number of players the user is choosing
    public void drawNumPlayers(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(25f));
        String s = "You've chosen " + numPlayers + " players to play with. Press ENTER to move on to the game.";
        g.drawString(s, 150, 750);
    }

    // Draws the Images and numbers of the different sides of die th user can choose
    public void paintDieSides(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(44f));
        int pNum = (playerMod%numPlayers) + 1;
        String s = "𝙿𝚕𝚊𝚢𝚎𝚛 " + pNum + ", 𝚠𝚑𝚒𝚌𝚑 𝚘𝚏 𝚝𝚑𝚎𝚜𝚎 𝚍𝚒𝚎𝚜 𝚍𝚘 𝚢𝚘𝚞 𝚠𝚊𝚗𝚝 𝚝𝚘 𝚛𝚘𝚕𝚕?";
        g.drawString(s, 43, 100);
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
    }

    // Confirms the number of sides on the die the player wants to roll
    public void drawNumSides(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(25f));
        String s = "You've chosen a " + dieSides + " sided die. Press ENTER to roll it.";
        g.drawString(s, 300, 775);
    }

    // Displays the results of the player's roll, and where they are moving to
    // Shows special information if they land on a ladder or snake, or if they win the game
    public void paintDieResult(Graphics g)
    {
        g.setColor(new Color(255, 255, 255, 142));
        g.fillRect(282, 188, 638, 424);
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(45f));
        Player p = game.getPlayer(playerMod % numPlayers);
        int playerNum = p.getNumber() + 1;
        g.setColor(p.getColor());
        String s = "Player " + playerNum + ", you rolled a " + p.getLastRoll();
        g.drawString(s, 349, 280);
        g.setColor(Color.BLACK);
        if (p.getIsMovement() == 0)
        {
            int position = p.getBoardPosition();
            if (position >= 54)
            {
                g.setFont(g.getFont().deriveFont(40f));
                g.setColor(p.getColor());
                position = 54;
                s = "𝚈𝚘𝚞 𝚛𝚎𝚊𝚌𝚑𝚎𝚍 𝚝𝚑𝚎 𝚏𝚒𝚗𝚊𝚕 𝚜𝚙𝚘𝚝!";
                g.drawString(s, 325, 466);
                g.setColor(Color.BLACK);
            }
            g.setFont(g.getFont().deriveFont(30f));
            s = "You'll move from position " + (p.getBoardPosition() - p.getLastRoll()) + " to position " + position;
            g.drawString(s, 285, 375);
        }
        else
        {
            g.setFont(g.getFont().deriveFont(29f));
            s = "You'll have a special movement!";
            g.drawString(s, 373, 350);
            g.setFont(g.getFont().deriveFont(22f));
            s = "When you move your piece, you will actually land at " + p.getIsMovement();
            g.drawString(s, 307, 420);
            s = "However, your special movement will take you from ";
            g.drawString(s, 316, 460);
            s = "this position to your new position of " + p.getBoardPosition();
            g.drawString(s, 383, 500);
        }
        g.setColor(new Color(0, 23, 52));
        g.setFont(g.getFont().deriveFont(22f));
        s = "𝐏𝐫𝐞𝐬𝐬 𝐒𝐏𝐀𝐂𝐄 𝐭𝐨 𝐠𝐨 𝐛𝐚𝐜𝐤 𝐭𝐨 𝐭𝐡𝐞 𝐛𝐨𝐚𝐫𝐝";
        g.drawString(s, 543, 609);
    }

    // Paints the images and draws winner on the final display screen
    public void paintWin(Graphics g)
    {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.drawImage(snakeTrophy, 205, 55, 768, 719, this);
        int winnerNum = winner.getNumber() + 1;
        g.setColor(winner.getColor());
        g.setFont(g.getFont().deriveFont(50f));
        g.drawString("Player " + winnerNum + ", You Win!", 420, 115);
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(27f));
        String s = "𝕊𝕟𝕒𝕜𝕖𝕤 & 𝕃𝕒𝕕𝕕𝕖𝕣𝕤";
        g.drawString(s, 417, 225);
        g.setFont(g.getFont().deriveFont(30f));
        s = "ℂ𝕊𝟚";
        g.drawString(s, 500, 280);
        g.setFont(g.getFont().deriveFont(20f));
        s = "𝕂𝕒𝕪𝕒𝕟 𝕊𝕦𝕟𝕕𝕖𝕣𝕒𝕞";
        g.drawString(s, 455, 330);
    }

    // Actual paint method that incorporates all the previous methods
    // The screen state determines what stuff is drawn on the screen, and when to draw different things
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
            // Makes sure that the user has chosen the players they want before displaying what they've chosen.
            // Otherwise, it would try to confirm that they want to play with 0 players
            if (hasChosenPlayers)
            {
                drawNumPlayers(g);
            }
        }
        else if (state == PLAYING)
        {
            paintGame(g);
            // Resets the variable that determines where the square is drawn when you choose the sides of the die
            // This needs to happen because we keep using this function every turn, unlike with choosing players
            // This is also an extra layer to make sure that the square doesn't initially show, as there is also
            // the boolean hasChosenDie, but this is an extra layer of protection
            a = -400;
            hasChosenDie = false;
            int prev = playerMod%numPlayers - 1;
            if (prev == -1)
            {
                prev = numPlayers - 1;
            }
            // If there is a winner, we go to the final screen, and set the winner
            if (game.getPlayer(prev).checkWin())
            {
                winner = game.getPlayer(prev);
                state = GAME_OVER;
            }
        }
        else if (state == CHOOSE_DIE)
        {
            paintDieSides(g);

            paintSquareDie(g);
            if (hasChosenDie)
            {
                drawNumSides(g);
            }
        }
        else if (state == DIE_RESULT)
        {
            if (hasChosenDie)
            {
                // These next three lines of code are to make the board appear in the background, faded out
                paintGame(g);
                g.setColor(new Color(0, 0, 0, 181));
                g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                paintDieResult(g);
            }
        }
        else if (state == GAME_OVER)
        {
            // This also fades out the board
            paintGame(g);
            g.setColor(new Color(0, 0, 0, 181));
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            paintWin(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    // In keyPressed, the front end first checks what screen the computer is on, and then checks for any keyboard input
    // Depending on what the user presses, the state changes from one screen to another
    @Override
    public void keyPressed(KeyEvent e)
    {
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
                state = DIE_RESULT;
                // Uses the instance of the game to play a turn, to connect it to the front end
                game.getPlayer(playerMod % numPlayers).playTurn(dieSides);
            }
        }
        else if (state == DIE_RESULT)
        {
            if (e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                state = PLAYING;
                // Increases the player mod, to signify a new player's turn
                playerMod++;
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

    }

    // This method is only used in the screen where we're selecting the number of players and die sides with the mouse
    // Depending on where the person clicks the screen, the number of players, or die sides are set, and that info
    // is given to the back end to actually play the game
    @Override
    public void mousePressed(MouseEvent e)
    {
        if (state == CHOOSE_PLAYERS)
        {
            if (e.getX() >= 5 && e.getX() <= 270 && e.getY() >= 166 && e.getY() <= 540)
            {
                // Helps to figure out when to draw the square and the line of code confirming the user's choice
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
            // Because this gets the number of players, we can create instances of the players now
            game.makePlayers();
        }
        else if (state == CHOOSE_DIE)
        {
            if (e.getX() >= 163 && e.getX() <= 363 && e.getY() >= 175 && e.getY() <= 415)
            {
                hasChosenDie = true;
                dieSides = 3;
                a = 0;
            }
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
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}