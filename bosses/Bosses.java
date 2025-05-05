package game.bosses;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.DropRunesAction;
import game.enemies.Enemies;
import game.utils.FancyMessage;
import game.utils.ResetManager;
import game.utils.Status;
import game.weapons.FireBreath;
import game.weapons.Slam;

import java.util.ArrayList;

/**
 * Bosses abstract class
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public class Bosses extends Enemies {

    /**
     * list of weapon boss can use to attack, use to rotate weapon so that all weapon is used in a specific order.
     */
    private ArrayList<WeaponItem> weaponList = new ArrayList<>();

    /**
     * last weapon boss used
     */
    private WeaponItem lastWeaponUsed;


    /**
     * Constructor.
     *
     * @param name            the name of the Actor
     * @param displayChar     the character that will represent the Actor in the display
     * @param hitPoints       the Actor's starting hit points
     * @param runesLowerBound the int lower bound of rune enemy can randomly drop
     * @param runesUpperBound the int upper bound of rune enemy can randomly drop
     */
    public Bosses(String name, char displayChar, int hitPoints, int runesLowerBound, int runesUpperBound) {
        super(name, displayChar, hitPoints, Status.BOSS, runesLowerBound, runesUpperBound);
        // boss is not resettable
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.removeResettable(this);
        // all boss would have slam as default attack
        WeaponItem slam = new Slam();
        this.addWeaponToInventory(slam);
        lastWeaponUsed = slam;
        // boss can't despawn
        this.setdespawnChance(0);
    }

    /**
     * At each turn, select a valid action to perform.
     * Weapon list used to remove and add weapon so that a specific weapon will be used to perform attack
     * this is because attackBehaviour take enemy's first weapon to use to attack
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
            // Print demigod felled message
            for (String line : FancyMessage.DEMIGOD_FELLED.split("\n")) {
                new Display().println(line);
                try {
                    Thread.sleep(200);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            return new DropRunesAction(this);
        }
        // if action has next action, it will have next when it is multi turn action
        Action nxtAction = lastAction.getNextAction();
        if(nxtAction != null){
            return nxtAction;
        }
        // remove and add weapon boss should use at current turn
        this.removeWeaponFromInventory(lastWeaponUsed);
        WeaponItem addWeapon = weaponList.get(0);
        weaponList.remove(0);
        weaponList.add(lastWeaponUsed);
        this.addWeaponToInventory(addWeapon);
        lastWeaponUsed = addWeapon;

        //change boss weapon for attack it will perform now
        return super.playTurn(actions,lastAction,map,display);
    }

    /**
     * add a weapon to weapon list, used to perform attack
     *
     * @param weapon weapon used as an attack or just weapon
     */
    public void addWeaponAttack(WeaponItem weapon){
        this.weaponList.add(weapon);
    }
}
