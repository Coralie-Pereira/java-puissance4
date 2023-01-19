package Classes;
import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        /*
         * Override compare function to sort arraylist by scores
         * @param o1(Player): player 1
         * @param o2(Player): player 2
         * @return (int): score player2 - score player1
         */
        return o2.getscore() - o1.getscore();
    }
}