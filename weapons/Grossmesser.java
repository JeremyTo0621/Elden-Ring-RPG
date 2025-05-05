package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AoeAttackAction;

/**
 * A Grossmesser weapon that can be used to attack the enemy.
 * It deals 115 damage with 85% hit rate and have special skill
 * to spin and attack every actor at it's exit
 * can be sold for 100 runes
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public class Grossmesser extends TradableWeapons{
    /**
     * Constructor.
     *
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "slash", 85, 0, 100);
    }

    /**
     * to get special skill of grossmesser (spinning attack)
     *
     * @param holder weapon holder
     * @return an action to attack the surroundings
     */
    @Override
    public Action getSkill(Actor holder) {
        return new AoeAttackAction(this);
    }

    /**
     * creates a new instance of Grossmesser
     * @return new instance of Grossmesser
     */
    @Override
    public TradableWeapons createNewInstance() {
        return new Grossmesser();
    }
}
