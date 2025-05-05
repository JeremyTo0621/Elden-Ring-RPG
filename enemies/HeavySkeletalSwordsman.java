package game.enemies;

import game.weapons.Grossmesser;

/**
 * a class for enemy HeavySkeletalSwordsman which extends
 * Skeletal abstract class
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public class HeavySkeletalSwordsman extends Skeletal{
    /**
     * Constructor
     */
    public HeavySkeletalSwordsman (){
        super("Heavy Skeletal Swordsman", 'q', 153, 35, 892);
        this.addWeaponToInventory(new Grossmesser());
    }

}
