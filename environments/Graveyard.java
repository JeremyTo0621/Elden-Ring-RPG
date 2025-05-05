package game.environments;
import game.enemies.HeavySkeletalSwordsman;
import game.enemies.SkeletalBandit;

/**
 * An area in the environment which has a 27% chance of
 * spawning the HeavySkeletalSwordsman enemy
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 */
public class Graveyard extends SpawningGround {
    /**
     * Constructor
     */
    public Graveyard() {
        super('n');
        this.addWestEnemySpawn(new HeavySkeletalSwordsman(), 27);
        this.addEastEnemySpawn(new SkeletalBandit(), 27);
    }


}
