package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Sellable;

/**
 * An action to sell a sellable item to a trader
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */

public class SellAction extends Action{

    /**
     * item that is going to be sold
     */
    private Sellable item;

    /**
     * Constructor
     *
     * @param item
     */
    public SellAction(Sellable item) {
        this.item = item;

    }

    @Override
    /**
     * Calls the sellItem method in the TradableWeapons class
     *
     *
     *@param target The actor performing the action.
     *@param map The map the actor is on.
     *@return result from the sellItem method (a string showing the result of the purchase)
     */
    public String execute(Actor actor, GameMap map) {
        return item.sellItem(actor);
    }

    @Override
    /**
     * Describes what item the actor has sold and for how much
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    public String menuDescription(Actor actor) {
        return actor + " sells "+ item + " for " + item.getSellingPrice() + " runes";
    }
}


