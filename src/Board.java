//public class Board
//{
//    private Player[] players;
//    //is board actually needed?
//    private int[] board;
//
//    public Board()
//    {
//        players = new Player[Player.numPlayers];
//        for (int i = 0; i < Player.numPlayers; i++)
//        {
//            players[i] = new Player(i);
//        }
//        board = new int[54/*board size*/];
//    }
//    public boolean playTurn(Player player)
//    {
//        player.move(6/*Get input from GUI to determine x-sided die*/);
//        player.ladderMove();
//        player.snakeMove();
//        return player.checkWin();
//    }
//
//    public void play()
//    {
//        for (int i = 0; i < Player.numPlayers; i++)
//        {
//            if (playTurn(players[i]) == true)
//            {
//                return;
//            }
//        }
//    }
//}
