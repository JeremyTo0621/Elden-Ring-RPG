package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Enemies;
import game.enemies.EnemiesFactory;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Spawning ground abstract class for grounds that spawn enemy
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public abstract class SpawningGround extends Ground {

    /**
     * Hashmap to store enemy that can be spawned and their spawn chance
     */
    private Map<Enemies, Integer> enemiesSpawnChance = new HashMap<>();

    /**
     * List of enemies spawning at east
     */
    private List<Enemies> eastEnemies = new ArrayList<>() ;

    /**
     * List of enemies spawning at west
     */
    private List<Enemies> westEnemies = new ArrayList<>() ;

    /**
     * East Enemies Factory used to produce new Instance of enemy spawning at east
     */
    private EnemiesFactory eastEnemiesFactory =  new EnemiesFactory();

    /**
     * West Enemies Factory used to produce new Instance of enemy spawning at west
     */
    private EnemiesFactory westEnemiesFactory = new EnemiesFactory();


    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGround(char displayChar) {
        super(displayChar);
    }

    /**
     * have a chance to spawn an enemy at every turn
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // get x range use to calculate whether location is east or west
        int xRange = location.map().getXRange().max();
        // initialize enemies and factory as east
        EnemiesFactory currentEnemiesFactory = eastEnemiesFactory;
        List<Enemies> enemiesList = eastEnemies;
        // calculate if location is west, if yes change all list to west
        if(location.x() < xRange/2){
            currentEnemiesFactory = westEnemiesFactory;
            enemiesList = westEnemies;
        }
        // loops through enemy and tries to spawn them
        for(Enemies enemy : enemiesList){
            int spawnChance = enemiesSpawnChance.get(enemy);
            if (!location.containsAnActor() && RandomNumberGenerator.getRandomInt(100) < spawnChance){
                // get factory that spawn this enemy and add/spawn it
                location.addActor(currentEnemiesFactory.newEnemy(enemy.getDisplayChar()));
            }
        }
    }

    /**
     * getter for list of enemies
     * @return list of enemies
     */
    public List<Enemies> getEnemies(){
        List<Enemies> newList = new ArrayList<>();
        newList.addAll(eastEnemies);
        newList.addAll(westEnemies);
        return newList;
    }

    /**
     * add a new enemy that can be spawned on east
     * @param enemy an Instance of Enemies class
     * @param spawnChance an integer representing chance the enemy can spawn
     */
    public void addEastEnemySpawn(Enemies enemy, int spawnChance){
        this.eastEnemies.add(enemy);
        this.enemiesSpawnChance.put(enemy, spawnChance);
        this.eastEnemiesFactory.addEnemyToFactory(enemy);
    }

    /**
     * add a new enemy that can be spawned on west
     * @param enemy an Instance of Enemies class
     * @param spawnChance an integer representing chance the enemy can spawn
     */
    public void addWestEnemySpawn(Enemies enemy, int spawnChance){
        this.westEnemies.add(enemy);
        this.enemiesSpawnChance.put(enemy, spawnChance);
        this.westEnemiesFactory.addEnemyToFactory(enemy);
    }
}