package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RestAction;

/**
 * A class that represents the Site Of Lost Grace.
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class SiteOfLostGrace extends Ground {

    /**
     * name of Site of Lost Grace
     */
    private String graceName;

    /**
     * Constructor
     */
    public SiteOfLostGrace() {
        super('U');
        graceName = "Site of Lost Grace";
    }
    /**
     * Constructor
     */
    public SiteOfLostGrace(String newGraceName) {
        super('U');
        graceName = newGraceName;
    }

    /**
     * Return a list of all actions allowed by Site of Lost Grace
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return List of actions allowed by Site of Lost Grace
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        return new ActionList(new RestAction(location, graceName));
    }

    /**
     * Check if a given actor can enter Site of Lost Grace
     *
     * @param actor the Actor to check
     * @return boolean true if the actor can enter, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }
}
