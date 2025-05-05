package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ExchangeAction;
import game.actions.SellAction;
import game.interfaces.Sellable;
import game.utils.RunesManager;
import game.utils.Status;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

/**
 * An items that can be exchanged for weapons with certain traders
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public class RemembranceOfTheGrafted extends Item implements Sellable {
    /**
     * selling price of item
     */
    private int sellingPrice;

    /**
     * item's sell action
     */
    private SellAction sellAction;

    /**
     * AxeOfGodrick sell action
     */
    private ExchangeAction exchangeAxeOfGodrickAction;

    /**
     * GraftedDragon sell action
     */
    private ExchangeAction exchangeGraftedDragonAction;
    /***
     * Constructor.
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance Of The Grafted", 'O', true);
        sellingPrice = 20000;
        this.sellAction = new SellAction(this);
        this.exchangeAxeOfGodrickAction = new ExchangeAction(this, new AxeOfGodrick());
        this.exchangeGraftedDragonAction = new ExchangeAction(this, new GraftedDragon());
    }

    /**
     * Sells the item that the player selects to a trader
     * adds the runes they earned from the sale
     *
     * @param actor The actor selling the item
     * @return a string showing the item that was sold
     */
    @Override
    public String sellItem(Actor actor) {
        RunesManager runesManager = RunesManager.getInstance();
        // increase player's rune amount and removes item from player's inventory
        runesManager.addRunes(this.getSellingPrice());
        actor.removeItemFromInventory(this);
        return  actor + " has sold " + this;
    }

    /**
     * returns the selling price
     * @return the selling price of the item
     */
    @Override
    public int getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Checks every turn if an actor with the BUY_AND_SELL capability is nearby. Adds a new sellAction if there is.
     * Also for the TRADE_WITH_REMEMBRANCE capability.
     * Adds a new sellAction for sell action for Axe of Godrick and Grafted Dragon if there is
     * removes each action when not in range of an actor with the required capabilities
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        boolean hasTrader = false;
        boolean hasRemembranceTrader = false;
        this.removeAction(sellAction);
        this.removeAction(exchangeAxeOfGodrickAction);
        this.removeAction(exchangeGraftedDragonAction);
        // loops through this item's exit
        for (Exit exit : currentLocation.getExits()) {
            Location surroundingLocation = exit.getDestination();
            // if current location contains merchant/trader, set hasTrader as true
            if (surroundingLocation.containsAnActor() && surroundingLocation.getActor().hasCapability(Status.BUY_AND_SELL)) {
                hasTrader = true;
                if(surroundingLocation.getActor().hasCapability(Status.TRADE_WITH_REMEMBRANCE)) {
                    hasRemembranceTrader = true;
                }
            }
        }
        // if hasTrader, add sell action
        if(hasTrader){
            this.addAction(sellAction);
        }
        // if hasRemembranceTrader, add sell action for Axe of Godrick and Grafted Dragon
        if (hasRemembranceTrader){
            this.addAction(exchangeAxeOfGodrickAction);
            this.addAction(exchangeGraftedDragonAction);
        }
    }
}
