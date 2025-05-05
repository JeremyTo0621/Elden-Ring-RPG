package game.bosses;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.FireBreath;
import game.weapons.JumpWeapon;

/**
 * Necromancer of Nulls, The First Object-Oriented Lord class
 * a boss that has multiple weapon and can use all of it to attack
 * attacks in specific pattern [slams --> jump attack --> fire breath if hp < 50%]
 *
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class NecromancerOfNulls extends Bosses {

    /**
     * boolean to represent whether weapon to add when Necromancer's hp falls below 50% is added
     */
    private boolean addedWeapon = false;

    /**
     * Constructor.
     *
     */
    public NecromancerOfNulls() {
        super("Necromancer Of Nulls, The First Object-Oriented Lord", 'ඕ', 2000, 20000, 20000);
        this.addWeaponAttack(new JumpWeapon());
    }

    /**
     * At each turn, if boss hp is below 50%, give him fire breath weapon to perform fire breath attacks
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // if hitpoints < 50%, Necromancer can now perform large surrounding fire breath
        if(this.isConscious() & this.hitPoints <= (0.5 * this.getMaxHp()) & !addedWeapon){
            addedWeapon = true;
            this.addWeaponAttack(new FireBreath());
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}