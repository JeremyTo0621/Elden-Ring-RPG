package game.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.interfaces.Buyable;

import java.util.ArrayList;
import java.util.List;

import static game.utils.Status.BUY_AND_SELL;

/**
 * A Merchant which can buy and sell items to players
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public abstract class Merchant extends Actor {

    /**
     * List of buyables Merchant is selling
     */
    private List<Buyable> buyables = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Merchant(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(BUY_AND_SELL);
    }

    /**
     *
     * The merchant can trade with any actor
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // loops through all buyable items merchant has and adds buy action for that item
        for (Buyable buyable : buyables){
            actions.add(buyable.getBuyAction());
        }
        return actions;
    }

    /**
     *
     * The merchant currently does not have any actions
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Add a new buyable item to Merchant's List of buyables
     *
     * @param buyable new buyable item Merchant is selling
     */
    public void addNewBuyable(Buyable buyable){
        buyables.add(buyable);
    }
}

