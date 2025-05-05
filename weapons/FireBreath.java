package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AoeAttackAction;
import game.actions.FireBreathAction;

/**
 * A Fire Breath weapon used to represent some enemies special attack.
 * It deals 80 damage with 70% hit rate and have special skill
 * to burn far out its surrounding
 *
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 */
public class FireBreath extends WeaponItem {
    /**
     * Constructor.
     *
     */
    public FireBreath() {
        super("FireBreath", '.', 80, "breath fires at", 70);
        // not a weapon, can't be dropped
        this.togglePortability();
    }

    /**
     * returns special skill of fire breath that attacks up to 3 squares surrounding the holder
     *
     * @param holder weapon holder
     * @return an action to attack the surroundings
     */
    @Override
    public Action getSkill(Actor holder) {
        return new FireBreathAction();
    }



}
