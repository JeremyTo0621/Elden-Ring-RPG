package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;
import game.interfaces.Behaviour;
import game.actions.AttackAction;
import game.utils.RandomNumberGenerator;

/**
 * A class that uses attackAction to attack enemy which is one step
 * closer to a target Actor.
 *
 * @see edu.monash.fit2099.demo.mars.Application
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class AttackBehaviour implements Behaviour {
    /**
     * status actorType of attacker
     */
    private final Status actorType;

    /**
     * Constructor
     *
     * @param actorType status actorType of attacker
     */
    public AttackBehaviour(Status actorType) {
        this.actorType = actorType;
    }

    /**
     * Attack enemy that is at the exit of actor, also consider if actor got weapon and its skill
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return the attack action of weapon skill action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            // if this exit contain an actor, attack
            if(destination.containsAnActor()){
                Actor target = destination.getActor();
                // attack if enemy is hostile and not of the same type, Merchant etc is not hostile
                if(target.hasCapability(Status.HOSTILE_TO_ENEMY) & !target.hasCapability(this.actorType)){
                    for(WeaponItem weaponItem : actor.getWeaponInventory() ){
                        int random = RandomNumberGenerator.getRandomInt(100);
                        if(random < 50) {
                            // return skill from weapon that don requires target
                            Action weaponSkill = weaponItem.getSkill(actor);
                            if (weaponSkill != null) {
                                return weaponSkill;
                            }
                            // return skill from weapon that target one actor
                            Action weaponSkillDirection = weaponItem.getSkill(target, null);
                            if (weaponSkillDirection != null) {
                                return weaponSkillDirection;
                            }
                        }
                        AttackAction attackAction = new AttackAction(target, exit.getName(), weaponItem);
                        return attackAction;
                    }
                    AttackAction attackAction = new AttackAction(target, exit.getName());
                    return attackAction;
                }
            }
        }
        return null;
    }
}
