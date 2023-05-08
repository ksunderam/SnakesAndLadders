import java.awt.*;

public class Game
{
    private GameViewer gameViewer;
    //private Board board;
    private Player[] players;
    private boolean gameOver;
    private Graphics g;

    public Game()
    {
        //board = new Board();
        gameViewer = new GameViewer(this);
        gameOver = false;
        players = new Player[gameViewer.getNumPlayers()];
        for (int i = 0; i < gameViewer.getNumPlayers(); i++)
        {
            if (i == 0)
            {
                players[i] = new Player(i, new Color(196, 0, 0), 0, 0);
            }
            if (i == 1)
            {
                players[i] = new Player(i, new Color(0, 27, 159), 64, 0);
            }

            if (i == 2)
            {
                players[i] = new Player(i, new Color(6, 101, 0), 0, 64);
            }

            if (i == 3)
            {
                players[i] = new Player(i, new Color(224, 188, 0), 64, 64);
            }

        }
    }

    public Player getPlayer(int i)
    {
        return players[i];
    }
    public Player[] getPlayers()
    {
        return players;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }

    public boolean playTurn(Player player, int dieSides, Graphics g)
    {
        player.move(dieSides);
        player.ladderMove();
        player.snakeMove();
        //player.drawPlayer(g);
        return player.checkWin();
    }

    public void playGame()
    {
        for (int i = 0; i < gameViewer.getNumPlayers(); i++)
        {
            //players[i].drawPlayer(g);
            if (playTurn(players[i], gameViewer.getDieSides(), g) == true)
            {
                return;
            }
        }
    }
    public void draw(Graphics g)
    {
//        g.fillOval(27, 740, 50, 50);
//        g.setColor(new Color(196, 0, 0));
//        g.fillOval(25, 30, 64, 64);
//        g.setColor(new Color(0, 27, 159));
//        g.fillOval(89, 30, 64, 64);
//        g.setColor(new Color(6, 101, 0));
//        g.fillOval(25, 94, 64, 64);
//        g.setColor(new Color(224, 188, 0));
//        g.fillOval(89, 94, 64, 64);

//        g.setColor(new Color(196, 0, 0));
//        g.fillOval(25, 670, 64, 64);
//        g.setColor(new Color(0, 27, 159));
//        g.fillOval(89, 670, 64, 64);
//        g.setColor(new Color(6, 101, 0));
//        g.fillOval(25, 734, 64, 64);
//        g.setColor(new Color(224, 188, 0));
//        g.fillOval(89, 734, 64, 64);

//        for (int i = 0; i < gameViewer.getNumPlayers(); i++)
//        {
//            players[i].drawPlayer(g);
//        }
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.playGame();
    }
}