package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Returns a text when actor is revived
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public class BoneReviveAction extends Action {
    /**
     * returns text when pile of bones is revived
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return text when pile of bones is revived
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " is revived!";
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
