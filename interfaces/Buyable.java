package game.interfaces;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface for items that are buyable
 * ensures that buyable items have the buyItem method
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public interface Buyable {
    /**
     * Checks if the actor has enough runes to buy the item
     * Buys the item if they can afford it
     *
     * @param actor actor that is buying item
     * @return A string showing if the purchase was successful or not
     */
    String buyItem(Actor actor);

    /**
     * returns the buying price of the item
     * @return buying price of the item
     */
    int getPrice();

    /**
     * returns a buy action for the buyable item
     * @return a new BuyAction
     */
    Action getBuyAction();
}
