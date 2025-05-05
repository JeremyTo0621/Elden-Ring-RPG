package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.BuyAction;
import game.actions.SellAction;
import game.interfaces.Buyable;
import game.interfaces.Sellable;
import game.utils.RunesManager;
import game.utils.Status;

/**
 * An Item to represent TradableWeapons.
 * Any weapon which can be bought or sold willinherit this class
 *
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public abstract class TradableWeapons extends WeaponItem implements Buyable, Sellable {
    /**
     * buying price of item
     */
    private int price;
    /**
     * selling price of item
     */
    private int sellingPrice;
    /**
     * item's sell action
     */
    private SellAction sellAction;
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public TradableWeapons(String name, char displayChar, int damage, String verb, int hitRate, int price, int sellingPrice) {
        super(name, displayChar, damage, verb, hitRate);
        this.price = price;
        this.sellingPrice = sellingPrice;
        this.sellAction = new SellAction(this);
    }

    /**
     * Checks if the actor has enough runes to buy the item
     * Buys the item if they can afford it
     *
     * @param actor The actor buying the item
     * @return A string showing if the purchase was successful or not
     */
    public String buyItem(Actor actor) {
        RunesManager runesManager =  RunesManager.getInstance();
        // checks if player has sufficient runes to buy
        if(runesManager.getPlayerRuneAmount() >= this.getPrice()){
            runesManager.decreaseRunes(this.getPrice());
            actor.addWeaponToInventory(this.createNewInstance());
            return actor + " has bought " + this;
        }
        return  actor + " has insufficient runes";
    }

    /**
     * Sells the item that the player selects to a trader
     * adds the runes they earned from the sale
     *
     * @param actor The actor selling the item
     * @return A string showing if the sale was successful or not
     */

    @Override
    public String sellItem(Actor actor) {
        RunesManager runesManager = RunesManager.getInstance();
        // increase player's rune amount and removes item from player's inventory
        runesManager.addRunes(this.getSellingPrice());
        actor.removeWeaponFromInventory(this);
        return actor + " has sold " + this;
    }

    /**
     * gets the selling price of the item
     * @return selling price of the item
     */
    @Override
    public int getSellingPrice() {
        return this.sellingPrice;
    }

    /**
     * returns the buying price of the item
     * @return buying price of the item
     */
    @Override
    public int getPrice(){
        return this.price;
    }

    /**
     * Checks every turn if a trader is nearby. Adds a new sellAction if there is a trader nearly
     * removes it when not in range of a trader.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        boolean hasTrader = false;
        this.removeAction(sellAction);
        // loops through this item's exit
        for (Exit exit : currentLocation.getExits()) {
            Location surroundingLocation = exit.getDestination();
            // if current location contains merchant/trader, set hasTrader as true
            if (surroundingLocation.containsAnActor() && surroundingLocation.getActor().hasCapability(Status.BUY_AND_SELL)) {
                hasTrader = true;
            }
        }
        // if hasTrader, add sell action
        if(hasTrader){
            this.addAction(sellAction);
        }
    }

    /**
     * remove sell action if weapon is on the floor
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        this.removeAction(sellAction);
    }

    /**
     * Returns a new BuyAction for the weapon
     *
     * @return a new BuyAction for the weapon
     */

    @Override
    public Action getBuyAction() {
        return new BuyAction(this);
    }

    /**
     * creates a new instance of a tradable weapon
     *
     * @return a new instance of this weapon
     */
    public abstract TradableWeapons createNewInstance();
}
