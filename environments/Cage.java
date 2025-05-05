package game.environments;

import game.enemies.Dog;

/**
 * An area in the environment which has a 37% chance of
 * spawning the Dog enemy
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 */
public class Cage extends SpawningGround{
    /**
     * Constructor.
     *
     */
    public Cage() {
        super('<');
        this.addWestEnemySpawn(new Dog(), 37);
        this.addEastEnemySpawn(new Dog(), 37);
    }
}
