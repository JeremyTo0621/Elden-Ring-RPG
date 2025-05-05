package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TurnBonePilesAction;
import game.utils.Status;

/**
 * an abstract class which allows new enemy of skeletal type to inherit
 * its functionalities
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public abstract class Skeletal extends Enemies{
    /**
     * Constructor for Skeletal abstract class
     *
     * @param name the name of the skeletal enemy
     * @param displayChar the character which represents the enemy
     * @param hitPoints the total health an enemy has
     * @param runesLowerBound the int lower bound of rune enemy can randomly drop
     * @param runesUpperBound the int upper bound of rune enemy can randomly drop
     */
    public Skeletal(String name, char displayChar, int hitPoints, int runesLowerBound, int runesUpperBound) {
        super(name, displayChar, hitPoints, Status.SKELETAL, runesLowerBound, runesUpperBound);
        // Skeletal do not die/despawn on death
        this.addCapability(Status.DONT_DESPAWN_ON_DEATH);
    }

    /**
     * At each turn, select a valid action to perform.
     * If skeletal is dead, return the TurnBonePilesAction to turn into Piles of Bone.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // if dead, turn into Piles of Bones
        if(!this.isConscious()){
            return new TurnBonePilesAction(this);
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
