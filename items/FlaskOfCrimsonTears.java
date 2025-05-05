package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.interfaces.Consumable;
import game.utils.ResetManager;
import game.interfaces.Resettable;
import game.actions.ConsumeAction;

/**
 * A simple item that can be used to heal
 * It heals 250 hit points
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class FlaskOfCrimsonTears extends Item implements Resettable, Consumable{

    /**
     * quantity of flask available to use
     */
    private int flaskQuantity;

    /**
     * max quantity of flask available
     */
    private int maxFlaskQuantity;

    /**
     * amount of hit points flask can heal
     */
    public static final int RESTORE_AMOUNT = 250;

    /***
     * Constructor.
     */
    public FlaskOfCrimsonTears() {
        super("Flask Of Crimson Tears", '-', false);
        this.maxFlaskQuantity = 2 ;
        this.flaskQuantity = 2;
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerResettable(this);
        // add consume action
        this.addAction(new ConsumeAction(this));
    }

    /**
     * Reset Flask Of Crimson Tears quantity during game reset
     */
    @Override
    public void reset() {
        this.flaskQuantity = 2;
    }

    /**
     * heal actor and decrease Flask Of Crimson Tears quantity after consumption
     * @param actor actor consuming the item
     * @return result of consumption of item
     */
    @Override
    public String consumeItem(Actor actor) {
        if (flaskQuantity == 0){
            return "No more Flask";
        }
        actor.heal(RESTORE_AMOUNT);
        this.flaskQuantity --;
        return actor + " consumed " + this + " " + this.printAmount();
    }

    /**
     * returns string amount of flask remaining (eg.(1/2))
     * @return string amount of flask remaining
     */
    public String printAmount(){
        return "(" + this.getCurrentAmount() + "/" + this.getMaxAmount() + ")";
    }

    /**
     * Return amount of flask left currently
     * @return amount of flask left currently
     */
    @Override
    public int getCurrentAmount() {
        return this.flaskQuantity;
    }

    /**
     * Return max amount of flask available
     * @return max amount of flask available
     */
    @Override
    public int getMaxAmount() {
        return this.maxFlaskQuantity;
    }
}
