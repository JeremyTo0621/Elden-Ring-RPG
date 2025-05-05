package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Buyable;

/**
 * An action to buy a buyable item from a trader
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public class BuyAction extends Action {
    /**
     * The item that is going to be bought
     */
    private Buyable item;

    /**
     * Constructor.
     *
     * @param item The item being bought
     */
    public BuyAction(Buyable item) {
        this.item = item;
    }

    @Override
    /**
     * Calls the buyItem method in the Tradable Weapons class
     *
     *
     *@param target The actor performing the action.
     *@param map The map the actor is on.
     *@return result from the buyItem method (a string showing the result of the purchase)
     */
    public String execute(Actor actor, GameMap map) {
        return item.buyItem(actor);
    }

    @Override
    /**
     * Describes what item the actor has bought and for how much
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    public String menuDescription(Actor actor) {
        return actor + " purchases "+ item + " for " + item.getPrice();
    }
}
