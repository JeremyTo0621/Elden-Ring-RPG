package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.ResetManager;

/**
 * An action executed when player Teleport
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class TeleportAction extends Action {

    /**
     * map the door can bring player to
     */
    GameMap teleportMap;

    /**
     * location where the door is at on the map player is teleporting to
     */
    Location doorLocation;

    /**
     * destination Name
     */
    String destinationName;

    /**
     * Constructor.
     *
     */
    public TeleportAction(GameMap map, Location doorLocation, String destinationName) {
        this.teleportMap = map;
        this.doorLocation = doorLocation;
        this.destinationName = destinationName;
    }

    /**
     * When executed, remove player from current map and add player to map they teleporting to
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //change map player is on
        ResetManager.getInstance().setMap(teleportMap);
        //move player to other map
        Location location = map.locationOf(actor);
        Actor player = location.getActor();
        location.map().removeActor(player);
        teleportMap.addActor(player, doorLocation);
        return actor + " has traveled to " + this.destinationName;
    }

    /**
     * Describe the teleport action
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + this.destinationName;
    }
}
