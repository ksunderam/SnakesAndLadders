// Kayan Sunderam

import java.awt.*;

public class Game
{
    private GameViewer gameViewer;
    private Player[] players;

    public Game()
    {
        // Creates instance of the front end
        gameViewer = new GameViewer(this);
    }

    // Creates and adds players to the player array in game
    // Based on the num of players from the front end, this also specifically creates the different colors for players
    // It also holds the x and y offsets for drawing the player pieces on the board
    // (There needed to be offsets so that all pieces could be visible on the same square)
    public void makePlayers()
    {
        players = new Player[gameViewer.getNumPlayers()];
        for (int i = 0; i < gameViewer.getNumPlayers(); i++)
        {
            if (i == 0)
            {
                players[i] = new Player(i, new Color(150, 0, 0), 0, 0);
            }
            if (i == 1)
            {
                players[i] = new Player(i, new Color(0, 27, 159), 64, 0);
            }

            if (i == 2)
            {
                players[i] = new Player(i, new Color(5, 86, 0), 0, 64);
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

    // Actually plays the game on the back end, stops if there is a winner and the game is over
    public void playGame()
    {
        for (int i = 0; i < gameViewer.getNumPlayers(); i++)
        {
            if (players[i].playTurn(gameViewer.getDieSides()) == true)
            {
                return;
            }
        }
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.playGame();
    }
}