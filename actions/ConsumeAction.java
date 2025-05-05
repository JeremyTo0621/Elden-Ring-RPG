package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Consumable;

/**
 * An Action to consume an item.
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class ConsumeAction extends Action {
    /**
     * The consumable item
     */
    Consumable consumableItem;

    /**
     * Constructor
     *
     * @param consumable The consumable item
     */
    public ConsumeAction(Consumable consumable) {
        consumableItem = consumable;
    }

    /**
     * When executed, consume the item and apply necessary effects
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result consuming the item
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = consumableItem.consumeItem(actor);
        return result;
    }

    /**
     * Describes consume action in menu
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes "+ consumableItem + " ("+consumableItem.getCurrentAmount()+"/"+consumableItem.getMaxAmount()+")";
    }
}
