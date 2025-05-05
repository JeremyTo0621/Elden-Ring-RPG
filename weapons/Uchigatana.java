package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.UnsheatheAction;
/**
 * A Uchigatana weapon that can be used to attack the enemy.
 * It deals 115 damage with 80% hit rate
 * It has a special skill that deals double the damage to the target (UnsheatheAction)
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public class Uchigatana extends TradableWeapons {
    /**
     * Constructor
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "slashes", 80, 5000, 500);
    }

    /**
     * returns the special skill for Uchigatana which deals double the damage to the target
     *
     * @param target target actor
     * @param direction
     * @return the special skill for Uchigatana, the UnsheatheAction
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new UnsheatheAction(this, target);
    }

    /**
     * creates a new instance of Uchigatana
     * @return new instance of Uchigatana
     */
    @Override
    public TradableWeapons createNewInstance() {
        return new Uchigatana();
    }
}
