
package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action to describe actor in the air
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class JumpingAction extends Action {

    /**
     * JumpAttack action, returned when actor jump to attack
     */
    private JumpAttackAction jumpAttackAction;

    /**
     * Constructor.
     *
     * @param jumpAttackAction JumpAttack action
     */
    public JumpingAction(JumpAttackAction jumpAttackAction) {
        this.jumpAttackAction = jumpAttackAction;
    }

    /**
     * When executed, return string to show actor is in the air
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string to show actor is in the air
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " jumped and is currently in the air.";
    }

    /**
     * Describe the Jump action
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps.";
    }

    /**
     * Will return itself if jumpCount have not exceeds max jumpCount, once it exceeds return
     * the JumpAttack action.
     *
     * @return next action actor should perform
     */
    @Override
    public Action getNextAction() {
        int currJumpCount = this.jumpAttackAction.getJumpCount();
        int maxJumpCount = this.jumpAttackAction.getMaxJumpCount();
        if(currJumpCount < maxJumpCount){
            this.jumpAttackAction.incrementJumpCount();
            return this;
        }
        return this.jumpAttackAction;
    }
}
