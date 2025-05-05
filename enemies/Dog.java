package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Status;
/**
 * a class for enemy Dog
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public class Dog extends Enemies{
    /**
     * Constructor.
     *
     */
    public Dog() {
        super("Dog", 'a', 104, Status.CASTLE, 52, 1390);
    }

    /**
     * gets the skill of Dog (bites)
     * @return the damage, the skill name, and its attack damage
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }
}
