package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.weapons.JumpWeapon;

/**
 * An action to perform jump attack
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class JumpAttackAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The number of turn actor has jumped in the air
     */
    private int jumpCount = 0;

    /**
     * The number of turn actor can stay in the air after jumping
     */
    private int maxJumpCount = 4;

    /**
     * Constructor
     *
     * @param target target to attack
     */
    public JumpAttackAction(Actor target) {
        this.target = target;
    }

    /**
     * Constructor with max turn actor can stay in the air
     *
     * @param target target to attack
     * @param maxJumpCount  number of turn actor can stay in the air
     */
    public JumpAttackAction(Actor target, int maxJumpCount) {
        this.target = target;
        this.maxJumpCount = maxJumpCount;
    }

    /**
     * When executed, if it is the first time executing, the jump count start counting and show that actor jumped.
     * next action would return a JumpingAction to describe that actor is in the air and once the max jump count
     * is reached, JumpingAction would return this action to perform attack.
     * When attacking, checks for an open location at the target's exit and teleport there to perform attack.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result from Jump Attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        // if actor have yet to jump
        if(jumpCount < maxJumpCount){
            this.incrementJumpCount();
            return actor + " jumped.";
        }
        // actor jump towards target after he jumped
        for(Exit exit : map.locationOf(target).getExits()){
            Location currentLocation = exit.getDestination();
            if(!currentLocation.containsAnActor()){
                map.moveActor(actor, currentLocation);
                result += actor + " Jumps to (" + currentLocation.x() + ", " + currentLocation.y() + ")";
                result += System.lineSeparator() + new AttackAction(target, "", new JumpWeapon()).execute(actor, map);
                break;
            }
        }
        return result;
    }

    /**
     * Describe the Jump attack action
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs a jump attack";
    }

    /**
     * get next action, there will be jumpingaction if actor just jumped to show
     * that actor is currently in the air
     *
     * @return next action actor should perform
     */
    @Override
    public Action getNextAction() {
        this.incrementJumpCount();
        if(jumpCount>maxJumpCount){
            return super.getNextAction();
        }
        return new JumpingAction(this);
    }

    /**
     * getter for jumpCount
     * @return jumpCount
     */
    public int getJumpCount() {
        return jumpCount;
    }

    /**
     * getter for maxJumpCount
     * @return maxJumpCount
     */
    public int getMaxJumpCount() {
        return maxJumpCount;
    }

    /**
     * Increment the jumpCount
     */
    public void incrementJumpCount(){
        this.jumpCount++;
    }
}
