public class Game
{
    private GameViewer gameViewer;
    private Board board;

    public Game()
    {
        board = new Board();
        GameViewer gamViewer = new GameViewer(this);
    }

    public void playGame()
    {
        board.play();
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.playGame();
    }
}
