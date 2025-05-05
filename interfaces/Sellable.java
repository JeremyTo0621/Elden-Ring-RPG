package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface for items that are sellable
 * ensures that sellable items have the buyItem method
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public interface Sellable {
    /**
     * Sells the item that the player selects to a trader
     * adds the runes player earned from the sale
     *
     * @param actor The actor selling the item
     * @return A string showing if the sale was successful or not
     */
    String sellItem(Actor actor);

    /**
     * gets the selling price of the item
     * @return selling price of the item
     */
    int getSellingPrice();
}
