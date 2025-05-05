package game.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import game.interfaces.EnemyFactory;
import game.utils.ResetManager;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that can create different types of Enemies based on display character
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public class EnemiesFactory implements EnemyFactory {

    /**
     * Hash map with display character as key and constructor as value
     */
    private Map<Character, Constructor<?>> map;

    /**
     * Constructor, initialise the hash map
     */
    public EnemiesFactory() {
        this.map = new HashMap<Character, Constructor<?>>();
    }


    /**
     * add an enemy to the hash map
     *
     * @param enemy enemy to be added to hash map
     */
    public void addEnemyToFactory(Actor enemy){
        try {
            Class<?> cls = enemy.getClass();
            Constructor<?> constructor;
            constructor = cls.getConstructor();
            map.put(enemy.getDisplayChar(), constructor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * return a new instance of an enemy with their display character
     *
     * @param displayChar display character of enemy to be created
     * @return new instance of enemy
     */
    @Override
    public Enemies newEnemy(char displayChar) {
        try {
            return (Enemies) map.get(displayChar).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
