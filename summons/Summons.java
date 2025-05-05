package game.summons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.DespawnAction;
import game.actions.DropRunesAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enemies.Enemies;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.players.*;
import game.utils.RandomNumberGenerator;
import game.utils.ResetManager;
import game.utils.Status;

import java.util.*;

/**
 * Summons abstract class for any actor that can be summoned, includes basic behaviour functionality
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public abstract class Summons extends Actor implements Resettable {

    /**
     * List of Role the summon can be, have its hp and weapon
     */
    private List<Player> ROLE_LIST = new ArrayList<>(Arrays.asList(new Samurai(), new Astrologer(), new Bandit(), new Wretch()));


    /**
     * Hashmap to store behaviours
     */
    private Map<Integer, Behaviour> behaviours;

    /**
     * boolean to represent if reset is happening
     */
    boolean reset;

    /**
     * Constructor.
     *
     * @param name            the name of the Actor
     * @param displayChar     the character that will represent the Actor in the display
     * @param hitPoints       the Actor's starting hit points
     * @param type            The status type of enemy
     */
    public Summons(String name, char displayChar, int hitPoints, Status type) {
        super(name, displayChar, hitPoints);
        //pick random role
        int sizeRole = ROLE_LIST.size();
        Player randomRole = ROLE_LIST.get(RandomNumberGenerator.getRandomInt(sizeRole));
        int newHitPoints = randomRole.getMaxHitPoints();
        this.resetMaxHp(newHitPoints);
        //give role weapon to summons, account from multiple weapon
        for(WeaponItem weapon: randomRole.getWeaponInventory()){
            this.addWeaponToInventory(weapon);
        }
        // initialise behaviours hashmap
        this.behaviours = new HashMap<>();
        // put behaviours
        this.behaviours.put(999, new WanderBehaviour());
        this.behaviours.put(99, new AttackBehaviour(type));
        // add summons type and capability, for who can attack them
        this.addCapability(type);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        //remove as resettable
        ResetManager resetManager = ResetManager.getInstance();
        //register as resettable for only during player death and not rest
        resetManager.registerPartialResettable(this);
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        //if reset, return despawn action
        if(reset){
            return new DespawnAction();
        }

        // goes through behaviour to get action that summons should perform
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * The summons can be attacked by any actor that has the PLAYER capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.PLAYER)){
            actions.add(new AttackAction(this, direction));
            // add actions for every weapon current attacking actors have
            for(WeaponItem weapon: otherActor.getWeaponInventory()){
                actions.add(new AttackAction(this, direction, weapon));
                // get skill from weapon that do not involve target actor
                Action weaponSkill = weapon.getSkill(otherActor);
                if(weaponSkill != null){
                    actions.add(weaponSkill);
                }
                // get skill from weapon that target one actor
                Action weaponSkillDirection = weapon.getSkill(this, direction);
                if(weaponSkillDirection != null){
                    actions.add(weaponSkillDirection);
                }
            }
            // add follow behaviour
            behaviours.put(0, new FollowBehaviour(otherActor));
        }
        return actions;
    }

    /**
     * When reset, summons will despawn from map
     */
    @Override
    public void reset() {
        reset = true;
    }
}
