package Classes;
import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return o2.getscore() - o1.getscore();
    }
}