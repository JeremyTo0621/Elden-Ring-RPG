package game.interfaces;

import game.enemies.Enemies;

/**
 * interface to create different types of Enemies based on the character that
 * represents it.
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public interface EnemyFactory {
    /**
     * Given a character, returns a new instance of the Enemies type represented by it.
     *
     * @param displayChar character that represents this Enemies in the UI
     * @return an instance of a concrete subclass of Enemies
     */
    Enemies newEnemy(char displayChar);
}
