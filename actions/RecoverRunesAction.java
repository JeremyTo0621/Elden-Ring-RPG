package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Runes;
import game.utils.RunesManager;

/**
 * An Action to Recover Runes
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class RecoverRunesAction extends Action {

    /**
     * Runes dropped on the ground
     */
    Runes groundRunes;

    /**
     * Constructor
     *
     * @param rune Runes dropped on the ground
     */
    public RecoverRunesAction(Runes rune) {
        groundRunes = rune;
    }

    /**
     * When executed, it will get the rune's amount and add it to player's rune using RunesManager
     * Then the rune on the ground is removed
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of recovering runes
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int amount = groundRunes.getRuneAmount();
        RunesManager runesManager = RunesManager.getInstance();
        runesManager.addRunes(amount);
        map.locationOf(actor).removeItem(groundRunes);
        return actor + " retrieves Runes (value: "+ amount +")";
    }

    /**
     * Describes picking/recovering runes from the ground
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Pick up "+ groundRunes.getRuneAmount() + " runes";
    }
}
