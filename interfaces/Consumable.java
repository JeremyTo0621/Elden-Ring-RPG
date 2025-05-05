package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A consumable interface
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public interface Consumable {
    /**
     * Implements what happen when a consumable is consumed
     *
     * @param actor actor consuming the item
     * @return result of consumption of item
     */
    String consumeItem(Actor actor);

    /**
     * return quantity of consumable remaining
     * @return quantity of consumable remaining
     */
    int getCurrentAmount();

    /**
     * return max quantity of consumable available
     * @return max quantity of consumable available
     */
    int getMaxAmount();
}
