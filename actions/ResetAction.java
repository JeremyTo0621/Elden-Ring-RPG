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
public class ResetAction extends Action {

    /**
     * Constructor
     */
    public ResetAction(){
    }

    /**
     * When executed, game will reset and player will respawn to last visited site of grace
     * or a default set spawn
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The result of reset
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run();

        // get last visited grace location
        Location playerRespawnLoc = resetManager.getPlayerRespawnLocation();
        GameMap playerRespawnMap = resetManager.getPlayerRespawnMap();
        // move player to respawn location
        playerRespawnMap.moveActor(actor, playerRespawnLoc);
        return "";
    }

    /**
     * Describes the reset action
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset";
    }
}
