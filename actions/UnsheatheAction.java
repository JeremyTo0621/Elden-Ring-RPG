package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;

import java.util.Random;

/**
 * An Unsheathe action for Uchigatana special skill
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class UnsheatheAction extends Action {

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * weapon used for the attack
     */
    private WeaponItem weapon;

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * Constructor for Unsheathe Action
     * @param weaponItem weapon used for the attack
     * @param target The Actor that is to be attacked
     */
    public UnsheatheAction(WeaponItem weaponItem, Actor target) {
        this.weapon = weaponItem;
        this.target = target;
    }

    /**
     * When executed, weapon have 60% chance to hit the target.
     * If so, deal double the damage to the target and determine whether the target is killed.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of Unsheathe action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // 60% chance to hit an enemy
        if (!(rand.nextInt(100) <= 60)) {
            return actor + " misses " + target + ".";
        }

        String result = this.menuDescription(actor);
        // double the damage
        int damage = weapon.damage() * 2;
        result += System.lineSeparator() + actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        // execute death action if target is dead
        if (!target.isConscious()) {
            if(!target.hasCapability(Status.DONT_DESPAWN_ON_DEATH)) {
                result += new DeathAction().execute(target, map);
            }
            if(actor.hasCapability(Status.CAN_TAKE_RUNES)){
                result += System.lineSeparator() + target.playTurn(null,null,map,null).execute(target, map);
            }
        }
        return result;
    }

    /**
     * Describes which target the actor is unsheathing attack and with which weapon
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unsheathes " + weapon + " on " + target;
    }
}
