public class Player
{
    public static int numPlayers;
    private int boardPosition;
    private int number;

    public Player(int num)
    {
        number = num;
        boardPosition = 0;
        numPlayers++;
    }

    public static int getNumPlayers()
    {
        return numPlayers;
    }

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
        if (boardPosition == 0/*End Spot on Board*/)
        {
            return true;
        }
        return false;
    }

    public void ladderMove()
    {
        if (boardPosition == 0/*On a specific point which has a ladder*/)
        {
            //boardPosition now equals the new position
        }
        //keep repeating these ifs all the way down
    }

    public void snakeMove()
    {
        if (boardPosition == 0/*On a specific point which has a snake*/)
        {
            //boardPosition now equals the new position
        }
        //keep repeating these ifs all the way down
    }
}