package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 *An action to remove an actor from the map
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 */
public class DespawnAction extends Action {

    /**
     * When executed, removes the actor from the map
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the actor that despawned
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return actor + " has been removed from the map.";
    }

    /**
     * Describe the despawn action
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " remove from map";
    }
}
