package game.interfaces;

/**
 * Interface for actor that can drop rune, used for DropRuneAction
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public interface DropRune {
    /**
     * Return a random int between lower and upper bound runes actor can drop
     * @return random int between lower and upper bound runes actor can drop
     */
    int getRandomRunes();
}
