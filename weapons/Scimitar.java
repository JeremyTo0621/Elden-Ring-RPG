package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.AoeAttackAction;

/**
 * A Scimitar weapon that can be used to attack the enemy.
 * It deals 118 damage with 88% hit rate and have special skill
 * to spin and attack every actor at it's exit
 * can be sold for 100 runes
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class Scimitar extends TradableWeapons{
    /**
     * Constructor.
     *
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "slashes", 88, 600, 100);
    }

    /**
     * to get special skill of Scimitar (spinning attack)
     *
     * @param holder weapon holder
     * @return an action to attack the surroundings
     */
    @Override
    public Action getSkill(Actor holder) {
        return new AoeAttackAction(this);
    }

    /**
     * creates new instance of Scimitar
     * @return a new instance of Scimitar
     */
    @Override
    public TradableWeapons createNewInstance() {
        return new Scimitar();
    }
}
