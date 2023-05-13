// Kayan Sunderam

import java.awt.*;

public class Player
{
    private int boardPosition;
    private int number;
    private Color color;
    private int xOffset;
    private int yOffset;
    private int lastRoll;
    private int isMovement;

    public Player(int num, Color col, int x, int y)
    {
        number = num;
        // Everyone starts at position 1 on the board--the starting spot
        boardPosition = 1;
        color = col;
        // Helps draw the player's piece
        xOffset = 25 + x;
        yOffset = 670 + y;
        //Saves the player's last roll to display on the front end
        lastRoll = 0;
        isMovement = 0;
    }

    public int getBoardPosition()
    {
        return boardPosition;
    }

    public int getNumber()
    {
        return number;
    }

    public Color getColor()
    {
        return color;
    }

    public int getIsMovement()
    {
        return isMovement;
    }

    public int getLastRoll()
    {
        return lastRoll;
    }

    // Rolls the die with 3, 6, or 8 sides, and updates the player's position accordingly
    public int move(int sides)
    {
        isMovement = 0;
        Die dice = new Die(sides);
        lastRoll = dice.roll();
        boardPosition += lastRoll;
        return lastRoll;
    }

    // The player has won if they've reacheed the final spot on the board--54
    public boolean checkWin()
    {
        if (boardPosition >= 54/* Final Spot on Board*/)
        {
            return true;
        }
        return false;
    }

    public void ladderMove()
    {
        // If the player is at a specific point which has a ladder
        if (boardPosition == 3)
        {
            // boardPosition now equals the new position
            boardPosition = 17;
            // Saves the original boardPosition they landed at before the power up
            isMovement = 3;
        }
        if (boardPosition == 10)
        {
            boardPosition = 27;
            isMovement = 10;
        }
        if (boardPosition == 20)
        {
            boardPosition = 39;
            isMovement = 20;
        }
        if (boardPosition == 26)
        {
            boardPosition = 49;
            isMovement = 26;
        }
    }

    public void snakeMove()
    {
        // If the player is at a specific point which has a snake
        if (boardPosition == 12)
        {
            //boardPosition now equals the new position
            boardPosition = 6;
            // Saves the original boardPosition they landed at before sliding down the snake
            isMovement = 12;
        }
        if (boardPosition == 34)
        {
            boardPosition = 22;
            isMovement = 34;
        }
        if (boardPosition == 40)
        {
            boardPosition = 13;
            isMovement = 40;
        }
        if (boardPosition == 46)
        {
            boardPosition = 28;
            isMovement = 46;
        }
        if (boardPosition == 53)
        {
            boardPosition = 36;
            isMovement = 53;
        }
    }

    // Moves the player, checks for any ladders or snakes at that position and moves them if so,
    // then checks whether the player has reached the end of the board and is a winner
    public boolean playTurn(int dieSides)
    {
        move(dieSides);
        ladderMove();
        snakeMove();
        return checkWin();
    }

    // Draws the player's piece on the board (a circle of their color) based on their board position
    // Ifs just check which row the player is at on the board, and inside we get the column
    // Based on these factors, x and y are updated, and the player is then drawn
    public void drawPlayer(Graphics g)
    {
        int x = 0;
        int y = 0;
        if (boardPosition >= 1 && boardPosition <= 9)
        {
            x = (boardPosition - 1)*128;
        }
        if (boardPosition >= 10 && boardPosition <= 18)
        {
            y = 128;
            x = (18 - boardPosition)*128;
        }
        if (boardPosition >= 19 && boardPosition <= 27)
        {
            y = 128*2;
            x = (boardPosition - 19)*128;
        }
        if (boardPosition >= 28 && boardPosition <= 36)
        {
            y = 128*3;
            x = (36 - boardPosition)*128;
        }
        if (boardPosition >= 37 && boardPosition <= 45)
        {
            y = 128*4;
            x = (boardPosition - 37)*128;
        }
        if (boardPosition >= 46)
        {
            y = 128*5;
            x = (54 - boardPosition)*128;
        }
        if (boardPosition >= 54)
        {
            x = 0;
        }
        g.setColor(color);
        g.fillOval(xOffset + x, yOffset - y, 64, 64);
    }
}