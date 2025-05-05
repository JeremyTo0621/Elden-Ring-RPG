package game.enemies;

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
import game.behaviours.*;
import game.interfaces.Behaviour;
import game.interfaces.DropRune;
import game.interfaces.Resettable;
import game.utils.RandomNumberGenerator;
import game.utils.ResetManager;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;
/**
 * Enemies abstract class
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 * Jeremy To Jun Wei & Wong Yik Ping
 */
public class Enemies extends Actor implements Resettable, DropRune {

    /**
     * Despawn Chance
     */
    private int despawnChance = 10;

    /**
     * Hashmap to store behaviours
     */
    private Map<Integer, Behaviour> behaviours;

    /**
     * Boolean to represent whether enemy is following a player
     */
    private boolean following = false;

    /**
     * Lower bound of random rune enemy can drop
     */
    public int runesLowerBound;
    /**
     * Upper bound of random rune enemy can drop
     */
    public int runesUpperBound;

    /**
     * boolean to represent if reset is happening
     */
    private boolean reset;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param type The status type of enemy
     * @param runesLowerBound the int lower bound of rune enemy can randomly drop
     * @param runesUpperBound the int upper bound of rune enemy can randomly drop
     */
    public Enemies(String name, char displayChar, int hitPoints, Status type, int runesLowerBound, int runesUpperBound) {
        super(name, displayChar, hitPoints);
        // set random rune bound
        this.runesLowerBound = runesLowerBound;
        this.runesUpperBound = runesUpperBound;
        // initialise behaviours hashmap
        this.behaviours = new HashMap<>();
        // put behaviours
        this.behaviours.put(999, new WanderBehaviour());
        this.behaviours.put(99, new AttackBehaviour(type));
        // add enemy type and capability
        this.addCapability(type);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        // register this enemy as resettable
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerResettable(this);
    }



    /**
     * At each turn, select a valid action to perform.
     * If they are dead, return the dropRunesAction.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // drop rune if dead
        if(!this.isConscious()){
            return new DropRunesAction(this);
        }
        if(reset){
            return new DespawnAction();
        }
        // 10% chance to despawn
        if(!following & RandomNumberGenerator.getRandomInt(1,100) < this.despawnChance){
            return new DespawnAction();
        }
        // goes through behaviour to get action that enemy should perform
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * The enemy can be attacked by any actor that has the PLAYER capability
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
            following = true;
        }
        return actions;
    }

    /**
     * set new despawn chance of enemy
     *
     * @param despawnChance chance to despawn
     */
    public void setdespawnChance(int despawnChance) {
        this.despawnChance = despawnChance;
    }

    /**
     * Return a random int between lower and upper bound runes enemy can drop
     * @return random int between lower and upper bound runes enemy can drop
     */
    public int getRandomRunes(){
        return RandomNumberGenerator.getRandomInt(runesLowerBound, runesUpperBound);
    }

    /**
     * When reset, enemy will despawn from map
     */
    @Override
    public void reset() {
        reset = true;
    }

}

