package game.summons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.Status;

/**
 * Ally that can be summoned
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class Ally extends Summons{

    /**
     * Constructor.
     *
     */
    public Ally() {
        super("Ally", 'A', 0, Status.NOT_HOSTILE_SUMMONS);
    }

    /**
     * Since Ally can't be attacked by player unlike any other enemy, there are no allowable actions
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return empty action list
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if(otherActor.hasCapability(Status.PLAYER)){
            return new ActionList();
        }
        return super.allowableActions(otherActor, direction, map);
    }
}
