package game.environments;

import game.enemies.GiantCrab;
import game.enemies.GiantCrayfish;

/**
 * An area in the environment which has a 2% chance of
 * spawning the GiantCrab enemy
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 */
public class PuddleOfWater extends SpawningGround {
    /**
     * Constructor
     */
    public PuddleOfWater() {
        super('~');
        this.addWestEnemySpawn(new GiantCrab(), 2);
        this.addEastEnemySpawn(new GiantCrayfish(), 1);
    }
}
