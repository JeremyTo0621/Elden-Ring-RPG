package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * An action to exchange an item for another item with a trader
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public class ExchangeAction extends Action {
    /**
     * The item that is going to be gained after the exchange
     */
    private WeaponItem item;

    /**
     * The item that is going to be loss after the exchange
     */
    private Item itemToBeExchanged;

    /**
     * Constructor.
     *
     * @param itemToBeExchanged The item being exchanged
     * @param item The item being bought
     */
    public ExchangeAction(Item itemToBeExchanged, WeaponItem item) {
        this.itemToBeExchanged = itemToBeExchanged;
        this.item = item;
    }

    @Override
    /**
     * removes the item to be exchanged and adds the item the player exchanged for
     *
     *@param target The actor performing the action.
     *@param map The map the actor is on.
     *@return result from the buyItem method (a string showing the result of the purchase)
     */
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(itemToBeExchanged);
        actor.addWeaponToInventory(item);
        return actor + " exchanged " + itemToBeExchanged + " for " + item;
    }

    @Override
    /**
     * Describes what item the actor has bought
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    public String menuDescription(Actor actor) {
        return actor + " exchanges "+ "Remembrance of the Grafted" + " for " + item + " ";
    }
}
