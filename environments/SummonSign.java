package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SummonAction;
import game.utils.Status;

/**
 * Summon sign ground
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class SummonSign extends Ground {
    /**
     * Constructor.
     *
     */
    public SummonSign() {
        super('=');
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        return new ActionList(new SummonAction(location));
    }
}
