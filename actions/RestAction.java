package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.ResetManager;

/**
 * An Action to reset the game.
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class RestAction extends Action {

    /**
     * Location of the site of grace player is resting at
     */
    private Location playerRespawnLocation;

    /**
     * name of the site of grace player is resting at
     */
    private String graceName;

    /**
     * Constructor
     *
     * @param location Location of the site of grace player is resting at
     * @param newGraceName name of the site of grace player is resting at
     */
    public RestAction(Location location, String newGraceName){
        playerRespawnLocation = location;
        graceName = newGraceName;
    }

    /**
     * When executed, player will rest and game will reset the site of grace location
     * is saved as well for respawning
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The result of reset
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // save site of lost grace location for respawn
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.setPlayerRespawn(playerRespawnLocation,map);
        // reset game without resetting runes
        resetManager.runRest();
        return actor + " has rested";
    }

    /**
     * Describes the rest action at Site of Lost Grace
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at " + graceName + " Site of Lost Grace";
    }
}
