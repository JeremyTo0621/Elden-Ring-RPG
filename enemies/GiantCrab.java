package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Status;
import game.weapons.Slam;

/**
 * Giant Crab enemy class
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public class GiantCrab extends Enemies{
    /**
     * Constructor.
     *
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 407, Status.CRUSTACEAN, 318, 4961);
        // special skill implemented as a weapon
        this.addWeaponToInventory(new Slam());
    }

    /**
     * gets GiantCrab's special skill (slam) which attacks a target
     * @return intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "slams", 90);
    }
}
