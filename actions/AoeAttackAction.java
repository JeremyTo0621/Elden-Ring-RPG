package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;

/**
 * An action to attack enemy surrounding the user
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public class AoeAttackAction extends Action {

    /**
     * Weapon used for the attack
     */
    private WeaponItem weapon;

    /**
     * Constructor.
     *
     * @param weaponItem the weapon used for attack
     */
    public AoeAttackAction(WeaponItem weaponItem) {
        this.weapon = weaponItem;
    }

    /**
     * When executed, the location around the actor is checked if it contains an actor,
     * if there is and can be attacked, call Attack Action to perform an attack
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of AOE Attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        // get location of attacker
        Location location = map.locationOf(actor);
        // x and y coordinates for location surrounding the attacker
        for (Exit exit : location.getExits()) {
            Location currentLocation = exit.getDestination();
            // check if location contain actor, if yes attack
            if(currentLocation.containsAnActor()){
                Actor target = currentLocation.getActor();
                // only actor that can be attacked, exclude merchant etc
                if(target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    AttackAction attackAction = new AttackAction(target, "");
                    if(weapon != null) {
                        attackAction = new AttackAction(target, "",weapon);
                    }
                    result +=  System.lineSeparator() + attackAction.execute(actor, map);

                }
            }
        }
        return actor + " performed aoe attack and " +result;
    }

    /**
     * Describe the type of attack
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs spinning attack";
    }
}
