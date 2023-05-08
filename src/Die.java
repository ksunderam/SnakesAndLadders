import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Die implements ActionListener
{
    private int sides;

    public Die(int numSides)
    {
        sides = numSides;
    }

    public int getSides()
    {
        return sides;
    }

    /**
     * Returns a random int between 1 and
     * the number of sides on the Die
     */
    public int roll()
    {
        int num = (int)(Math.random()*sides) + 1;
        return num;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
