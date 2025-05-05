package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * An QuickStep action for Great Knife special skill, attack and evade/move away
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class QuickStepAction extends Action {

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Weapon used for the attack
     */
    private WeaponItem weapon;

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * Constructor for Quick Step action
     *
     * @param weaponItem Weapon used for the attack
     * @param direction The direction of incoming attack.
     * @param target The Actor that is to be attacked
     */
    public QuickStepAction(WeaponItem weaponItem, String direction, Actor target) {
        this.weapon = weaponItem;
        this.direction = direction;
        this.target = target;
    }

    /**
     * When executed, the actor will attack target using AttackAction and once attack is done
     * attacker will evade/move away to an empty location at his exits
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of quickstep action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Attack the enemy using attack action
        String result = new AttackAction(target, direction, weapon).execute(actor, map);
        // iterate through actor/attacker exits and move to first location with no actor
        for(Exit exit : map.locationOf(actor).getExits()){
            Location currentLocation = exit.getDestination();
            if(!currentLocation.containsAnActor()){
                map.moveActor(actor,currentLocation);
                result += System.lineSeparator() + actor + " moves to (" + currentLocation.x() + ", " + currentLocation.y() + ")";
                break;
            }
        }
        return result;
    }

    /**
     * Describes which target the actor is attacking and evading/moving away
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " and moves away";
    }
}
