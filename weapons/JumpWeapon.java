package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.FireBreathAction;
import game.actions.JumpAttackAction;

/**
 * Jump Weapon that allows holder to use a jump attack
 * It deals 100 damage with 80% hit rate and have special skill
 * to perform multiple turn jump attack
 *
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public class JumpWeapon extends WeaponItem {
    /**
     * Constructor.
     */
    public JumpWeapon() {
        super("Jump Attack", '.', 100, "pulverize", 80);
    }

    /**
     * returns special skill of Jump to attack jump attack the target
     *
     * @param target target actor
     * @param direction
     * @return
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new JumpAttackAction(target);
    }


}
