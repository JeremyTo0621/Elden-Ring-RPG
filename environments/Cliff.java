package game.environments;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.utils.Status;

/**
 * A Cliff Ground
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public class Cliff extends Ground {
    /**
     * Constructor.
     */
    public Cliff() {
        super('+');
    }

    /**
     * checks if a player enters a cliff and executes a new DeathAction on the player if there is a player
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if(location.containsAnActor()){
            location.getActor().hurt(99999);
            new DeathAction().execute(location.getActor(), location.map());
       }
    }

    /**
     *
     * @param actor the Actor to check
     * @return true if actor can enter false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if(actor.hasCapability(Status.PLAYER)){
            return true;
        }
        return false;
    }
}
