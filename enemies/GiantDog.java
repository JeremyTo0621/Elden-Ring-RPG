package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Status;
import game.weapons.Slam;

/**
 * a class for enemy GiantDog
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public class GiantDog extends Enemies{
    /**
     * Constructor.
     *
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693 , Status.CANINE, 313, 1808);
        // weapon used to slam surrounding enemies
        this.addWeaponToInventory(new Slam());
    }

    /**
     * gets GiantDog's head slam which can attack a target
     * @return intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "head slams", 90);
    }
}
