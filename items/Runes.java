package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RecoverRunesAction;
import game.utils.ResetManager;
import game.interfaces.Resettable;

/**
 * An Item to represent the runes item.
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public class Runes extends Item implements Resettable {

    /**
     * Represent the number of runes
     */
    private int runeAmount;

    /**
     * boolean to represent if runes is dropped
     */
    private boolean dropped = false;

    /**
     * location of where runes are dropped
     */
    private Location droppedLocation;

    /**
     * Constructor.
     *
     */
    public Runes() {
        super("Runes", '$', false);
        runeAmount = 0;

        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerPartialResettable(this);
    }

    /***
     * Constructor with rune Amount
     */
    public Runes(int runeCount) {
        super("Runes", '$', false);
        this.runeAmount = runeCount;

        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerPartialResettable(this);
    }

    /**
     * return a DropItemAction for this runes
     * @param actor actor picking up runes
     * @return DropItemAction
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        return new DropItemAction(this);
    }

    /**
     * at every turn, check if rune's boolean attribute is true to represent dropped on the ground
     * if false, add RecoverRunesAction and set dropped location
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if(!dropped) {
            this.addAction(new RecoverRunesAction(this));
            droppedLocation = currentLocation;
            dropped = true;
        }
    }

    /**
     * set rune amount
     * @param runeAmount int amount to set runes to
     */
    public void setRuneAmount(int runeAmount) {
        this.runeAmount = runeAmount;
    }

    /**
     * It increases the runeCount based on the parameter
     *
     * @param amountToIncrease The number of runes to add
     */
    public void increaseRuneAmount(int amountToIncrease) {
        this.runeAmount += amountToIncrease;
    }

    /**
     * It decreases the runeCount based on the parameter
     *
     * @param amountToDecrease The number of runes to subtract
     */
    public void decreaseRuneAmount(int amountToDecrease){
        this.runeAmount -= amountToDecrease;
    }

    /**
     * Getter method to get the runeCount
     *
     * @return The runeCount
     */
    public int getRuneAmount() {
        return runeAmount;
    }

    /**
     * Reset rune by removing from ground if it is dropped from previous game reset
     */
    @Override
    public void reset() {
        // if coin is dropped, reset will remove
        if(dropped){
            droppedLocation.removeItem(this);
        }
    }

}
