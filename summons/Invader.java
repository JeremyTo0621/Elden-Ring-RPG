package game.summons;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DropRunesAction;
import game.interfaces.DropRune;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

/**
 * Invader that can be summoned
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class Invader extends Summons implements DropRune {

    /**
     * Lower bound of random rune Invader can drop
     */
    public final int RUNES_LOWER = 1358;
    /**
     * Upper bound of random rune Invader can drop
     */
    public final int RUNES_UPPER = 5578;

    /**
     * Constructor for Invader
     *
     */
    public Invader() {
        super("Invader", 'ඞ', 0, Status.HOSTILE_SUMMONS);
        }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // drop rune if dead
        if(!this.isConscious()){
            return new DropRunesAction(this);
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public int getRandomRunes() {
        return RandomNumberGenerator.getRandomInt(RUNES_LOWER, RUNES_UPPER);
    }
}
