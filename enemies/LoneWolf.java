package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Status;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class LoneWolf extends Enemies{
    /**
     * Constructor
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102, Status.CANINE, 55, 1470);
    }
    /**
     * gets the skill of LoneWolf (bites)
     * @return the damage, the skill name, and its attack damage
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }
}
