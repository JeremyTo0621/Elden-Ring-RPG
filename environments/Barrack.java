package game.environments;

import game.enemies.GodrickSoldier;

/**
 * An area in the environment which has a 45% chance of
 * spawning Godrick Soldiers
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 */
public class Barrack extends SpawningGround{
    /**
     * Constructor.
     *
     */
    public Barrack() {
        super('B');
        this.addWestEnemySpawn(new GodrickSoldier(), 45);
        this.addEastEnemySpawn(new GodrickSoldier(), 45);
    }
}

