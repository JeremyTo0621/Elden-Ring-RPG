package game.weapons;

/**
 * A Club weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Erin Lai Ziyi
 */
public class Club extends TradableWeapons{

    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80, 600, 100);
    }

    /**
     * creates a new instance of Club
     * @return new instance of Club
     */
    @Override
    public TradableWeapons createNewInstance() {
        return new Club();
    }
}
