package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AoeAttackAction;
/**
 * A slam weapon used to represent some enemies special attack.
 * It deals 208 damage with 90% hit rate and have special skill
 * to slams and attack every actor at it's exit
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Erin Lai Ziyi
 */
public class Slam extends WeaponItem {
    /**
     * Constructor.
     *
     */
    public Slam() {
        super("Slam", '-', 208, "slams", 90);
        // not a weapon, can't be dropped
        this.togglePortability();
    }

    /**
     * returns special skill of slam of giant crab to attack all enemies in surrounding
     *
     * @param holder weapon holder
     * @return an action to attack the surroundings
     */
    @Override
    public Action getSkill(Actor holder) {
        return new AoeAttackAction(this);
    }


}
