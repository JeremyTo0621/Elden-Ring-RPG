package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.interfaces.Consumable;
import game.utils.RandomNumberGenerator;
import game.utils.RunesManager;
/**
 * An item that can be used to gain a random number of runes
 * It generates between 200 and 10,000 runes
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public class GoldenRunes extends Item implements Consumable {
    /**
     * quantity of golden runes available to use
     */
    private int goldenRunesAmount;

    /**
     * max quantity of golden runes available
     */
    private int maxGoldenRunesAmount;

    /**
     * lower bound of runes that golden runes generates
     */
    private int goldenRunesLowerBound;

    /**
     * upper bound of runes that golden runes generates
     */
    private int getGoldenRunesUpperBound;

    /**
     * shows if the item is on the floor or not
     */
    private boolean onFloor = true;

    /**
     * a consume action
     */
    private Action consumeAction;

    /***
     * Constructor.
     */
    public GoldenRunes() {
        super("Golden Runes", '*', true);
        this.goldenRunesAmount = 1;
        this.maxGoldenRunesAmount = 1;
        this.goldenRunesLowerBound = 200;
        this.getGoldenRunesUpperBound = 10000;
        consumeAction = new ConsumeAction(this);
    }

    /**
     * generates a random number of runes between 200 and 10,000
     * adds that amount of runes to player's rune count
     *
     * @param actor actor consuming the item
     * @return result of consumption of item
     */
    @Override
    public String consumeItem(Actor actor) {
        // checks if there any golden runes remaining
        if (goldenRunesAmount == 0) {
            return "No more Golden Runes";
        }
        // generates random number of runes based on lower/upper bounds
        int runeAmount = getRandomRuneAmount(goldenRunesLowerBound, getGoldenRunesUpperBound);
        RunesManager runesManager = RunesManager.getInstance();
        // increses player's runes
        runesManager.addRunes(runeAmount);
        this.goldenRunesAmount--;
        actor.removeItemFromInventory(this);
        return actor + " consumed " + this + " and gained " + runeAmount + " runes.";
    }


    @Override
    /**
     * Return amount of golden runes left currently
     * @return amount of golden runes left currently
     */
    public int getCurrentAmount() {
        return goldenRunesAmount;
    }

    /**
     * Return max amount of golden runes available
     * @return max amount of golden runes available
     */
    @Override
    public int getMaxAmount() {
        return maxGoldenRunesAmount;
    }

    /**
     * generates and returns a random number of runes
     *
     * @param lowerBound the lower bound of runes generated
     * @param upperBound the upper bound of runes generated
     * @return a random number of runes
     */
    public int getRandomRuneAmount(int lowerBound, int upperBound){
        return RandomNumberGenerator.getRandomInt(lowerBound, upperBound);
    }

    /**
     * when the item is on the floor, we remove the consume action, and set the onFloor boolean to true
     * this is to prevent the actor from consuming the item on the ground
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        onFloor = true;
        this.removeAction(this.consumeAction);
        super.tick(currentLocation);
    }

    /**
     * When the item is picked up, we add back the consume action, so the actor can consume the item
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if(onFloor){
            onFloor = false;
            this.addAction(this.consumeAction);
        }
        super.tick(currentLocation, actor);
    }
}
