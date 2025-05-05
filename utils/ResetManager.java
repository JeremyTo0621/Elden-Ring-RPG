package game.utils;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Resettable;
import game.items.Runes;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Wong Yik Ping
 */
public class ResetManager {

    /**
     * list of resettables
     */
    private List<Resettable> resettables;

    /**
     * list of partial resettables, resettables that does not reset on rest
     */
    private List<Resettable> partialResettables;

    /**
     * a instance of ResetManager
     */
    private static ResetManager instance;

    /**
     * Location of player's last visited site of grace
     */
    private Location playerRespawnLoc;

    /**
     * Map of player's last visited site of grace
     */
    private GameMap playerRespawnMap;

    /**
     * Location of player's previous turn location
     */
    private Location playerPrevLoc;

    /**
     * current map player is on
     */
    private GameMap map;

    /**
     * private constructor
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
        this.partialResettables = new ArrayList<>();
    }

    /**
     * return an instance of ResetManager, making sure there's only 1 instance created
     * @return an instance of ResetManager
     */
    public static ResetManager getInstance(){
        if (instance == null ) {
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * set map player is on
     * @param map map player is on
     */
    public void setMap(GameMap map){
        this.map = map;
    }

    /**
     * return map player is on
     * @return map player is on
     */
    public GameMap getMap(){
        return this.map;
    }

    /**
     * reset all the resettable when game reset
     */
    public void run() {
        for(Resettable resettable : resettables){
            resettable.reset();
        }
        for(Resettable resettable: partialResettables){
            resettable.reset();
        }
    }

    /**
     * reset all the resettable when resting at Site of Lost Grace but not runes
     */
    public void runRest(){
        for(Resettable resettable : resettables){
            resettable.reset();
        }
    }

    /**
     * add a new resettable to ResetManager list of resettables
     *
     * @param resettable resettable to be added to list of resettable
     */
    public void registerResettable(Resettable resettable) {
        resettables.add(resettable);
    }

    /**
     * add a new partial resettable to ResetManager list of resettable runes
     *
     * @param partialResettable partial resettable to be added to list of partial resettables
     */
    public void registerPartialResettable(Resettable partialResettable) {
        partialResettables.add(partialResettable);
    }

    /**
     * remove a resettable from ResetManager's list of resettables
     *
     * @param resettable resettable to be removed from the list of resettable
     */
    public void removeResettable(Resettable resettable) {
        resettables.remove(resettable);
    }

    /**
     * set location player's respawn, will be the last visited site of grace
     *
     * @param location player's respawn location
     */
    public void setPlayerRespawn(Location location, GameMap map){
        playerRespawnLoc = location;
        playerRespawnMap = map;
    }

    /**
     * return location player's respawn, will be the last visited site of grace
     *
     * @return location of player's respawn, their last visited site of lost grace
     */
    public Location getPlayerRespawnLocation(){
        return playerRespawnLoc;
    }

    /**
     * return player's respawn map, will be the last visited site of grace
     *
     * @return map of player's respawn, their last visited site of lost grace
     */
    public GameMap getPlayerRespawnMap(){
        return playerRespawnMap;
    }

    /**
     * set location player's previous turn Location, will be used when player dies
     *
     * @param location player's respawn location
     */
    public void setPlayerPrevLocation(Location location){
        playerPrevLoc = location;
    }

    /**
     * return location player's previous turn Location, will be used when player dies
     *
     * @return location of player's previous turn Location, will be used when player dies
     */
    public Location getPlayerPrevLocation(){
        return playerPrevLoc;
    }
}
