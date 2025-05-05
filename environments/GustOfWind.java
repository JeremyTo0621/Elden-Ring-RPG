package game.environments;

import game.enemies.GiantDog;
import game.enemies.LoneWolf;

/**
 * An area in the environment which has a 33% chance of
 * spawning the LoneWolf enemy
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 */
public class GustOfWind extends SpawningGround {
    /**
     * Constructor
     */
    public GustOfWind() {
        super('&');
        this.addWestEnemySpawn(new LoneWolf(), 33);
        this.addEastEnemySpawn(new GiantDog(), 4);
    }
}

