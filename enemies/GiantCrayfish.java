package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Status;
import game.weapons.Slam;

/**
 * a class for enemy GiantCrayfish
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public class GiantCrayfish extends Enemies{
    /**
     * Constructor.
     *
     */
    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803, Status.CRUSTACEAN, 500, 2374);
        // special skill implemented as a weapon
        this.addWeaponToInventory(new Slam());
    }

    /**
     * gets GiantCrayfish's pincer which can attack a target
     * @return intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "slams", 100);
    }
}
