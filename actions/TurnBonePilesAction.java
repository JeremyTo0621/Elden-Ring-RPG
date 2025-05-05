package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.BonePiles;
import game.enemies.Skeletal;

/**
 * A action to turn Skeletal enemy to pile of bones
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class TurnBonePilesAction extends Action {

    /**
     * skeletal that is turning into piles of bone
     */
    private Skeletal turningSkeletal;

    /**
     * Constructor
     *
     * @param skeletal skeletal that is turning into piles of bone
     */
    public TurnBonePilesAction(Skeletal skeletal) {
        this.turningSkeletal = skeletal;
    }

    /**
     * When executed, get location of actor who is the skeletal and save its location and remove it
     * once removed, add a BonePiles actor.
     *
     * @param actor The actor performing the action/.
     * @param map The map the actor is on.
     * @return result of turning into pile of bones
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        map.removeActor(actor);
        map.addActor(new BonePiles(turningSkeletal), currentLocation);
        return  turningSkeletal + " turned into a Pile of Bones!";
    }

    /**
     * Describe the turning into bone piles action
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " turns into a Pile of Bones!";
    }
}
