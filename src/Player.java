import java.awt.*;

public class Player
{
    //private int numPlayers;
    private int boardPosition;
    private int number;
    private Color color;
    private int xOffset;
    private int yOffset;

    public Player(int num, Color col, int x, int y)
    {
        number = num;
        boardPosition = 0;
        color = col;
        xOffset = 25 + x;
        yOffset = 670 + y;
    }

//    public Player(int num, int players)
//    {
//        number = num;
//        boardPosition = 0;
//        numPlayers = players;
//    }

//    public int getNumPlayers()
//    {
//        return numPlayers;
//    }

    public int getBoardPosition()
    {
        return boardPosition;
    }

    public int getNumber()
    {
        return number;
    }

    public void move(int sides)
    {
        Die dice = new Die(sides);
        boardPosition += dice.roll();
    }

    public boolean checkWin()
    {
        if (boardPosition == 54/*0 End Spot on Board*/)
        {
            return true;
        }
        return false;
    }

    public void ladderMove()
    {
        if (boardPosition == 3/*On a specific point which has a ladder*/)
        {
            //boardPosition now equals the new position
            boardPosition = 17;
        }
        if (boardPosition == 10/*On a specific point which has a ladder*/)
        {
            //boardPosition now equals the new position
            boardPosition = 27;
        }
        if (boardPosition == 20/*On a specific point which has a ladder*/)
        {
            //boardPosition now equals the new position
            boardPosition = 39;
        }
        if (boardPosition == 26/*On a specific point which has a ladder*/)
        {
            //boardPosition now equals the new position
            boardPosition = 49;
        }
        //keep repeating these ifs all the way down
    }

    public void snakeMove()
    {
        if (boardPosition == 12/*On a specific point which has a snake*/)
        {
            //boardPosition now equals the new position
            boardPosition = 6;
        }
        if (boardPosition == 34/*On a specific point which has a snake*/)
        {
            //boardPosition now equals the new position
            boardPosition = 22;
        }
        if (boardPosition == 40/*On a specific point which has a snake*/)
        {
            //boardPosition now equals the new position
            boardPosition = 13;
        }
        if (boardPosition == 46/*On a specific point which has a snake*/)
        {
            //boardPosition now equals the new position
            boardPosition = 28;
        }
        if (boardPosition == 53/*On a specific point which has a snake*/)
        {
            //boardPosition now equals the new position
            boardPosition = 36;
        }
        //keep repeating these ifs all the way down
    }
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
        if (boardPosition >= 46 && boardPosition <= 54)
        {
            y = 128*5;
            x = (54 - boardPosition)*128;
        }

        g.setColor(color);
        g.fillOval(xOffset + x, yOffset + y, 64, 64);

//        if (number == 0)
//        {
//            g.setColor(new Color(196, 0, 0));
//            g.fillOval(25 + x, 670 + y, 64, 64);
//        }
//
//        if (number == 1)
//        {
//            g.setColor(new Color(0, 27, 159));
//            x += 64;
//            g.fillOval(25 + x, 670 + y, 64, 64);
//        }
//
//        if (number == 2)
//        {
//            g.setColor(new Color(6, 101, 0));
//            y += 64;
//            g.fillOval(25 + x, 670 + y, 64, 64);
//        }
//
//        if (number == 3)
//        {
//            g.setColor(new Color(224, 188, 0));
//            x += 64;
//            y += 64;
//            g.fillOval(25 + x, 670 + y, 64, 64);
//        }


//        g.setColor(Color.BLACK);
//        g.fillOval(27, 740, 50, 50);
    }
}