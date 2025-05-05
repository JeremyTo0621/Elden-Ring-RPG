package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.utils.Status;

/**
 * A Golden Fog Door Ground
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by: Wong Yik Ping
 *
 */
public class GoldenFogDoor extends Ground {

    /**
     * map where the door is at
     */
    GameMap doorMap;

    /**
     * location where the door is at
     */
    Location doorLocation;

    /**
     * Location where this door teleport to
     */
    Location destinationLocation;

    /**
     * Map where this door teleport to
     */
    GameMap destinationMap;

    /**
     * destination Name
     */
    String destinationName;

    /**
     * Constructor.
     *
     */
    public GoldenFogDoor(GameMap doorMap, Location doorLocation) {
        super('D');
        this.doorMap = doorMap;
        this.doorLocation = doorLocation;
        doorLocation.setGround(this);
    }

    /**
     * Set the destination GameMap and Location from destination Door
     *
     * @param destinationDoor Golden Fog Door this door teleports to
     * @param destinationName name of destination
     */
    public void setDestinationDoor(GoldenFogDoor destinationDoor, String destinationName) {
        this.destinationMap = destinationDoor.getDoorMap();
        this.destinationLocation = destinationDoor.getDoorLocation();
        this.destinationName = destinationName;
    }

    /**
     * Set the destination GameMap and Location from their map and location
     *
     * @param map map where this door teleport to
     * @param location location where this door teleport to
     * @param destinationName name of destination
     */
    public void setDestinationDoor(GameMap map, Location location, String destinationName) {
        this.destinationMap = map;
        this.destinationLocation = location;
        this.destinationName = destinationName;
    }

    /**
     * setter for door map
     *
     * @return Map this door is at
     */
    public GameMap getDoorMap() {
        return doorMap;
    }

    /**
     * setter for door location
     *
     * @return location this door is at
     */
    public Location getDoorLocation() {
        return doorLocation;
    }

    /**
     * If player is standing on Door, return Teleport action that allow player to teleport
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return ActionList contain all allowable action
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        return new ActionList(new TeleportAction(destinationMap, destinationLocation, destinationName));
    }
}
