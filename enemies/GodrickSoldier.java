package game.enemies;

import game.utils.Status;
import game.weapons.Scimitar;
/**
 * a class for enemy GodrickSoldier
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public class GodrickSoldier extends Enemies{
    /**
     * Constructor.
     *
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198, Status.CASTLE, 38, 70);
        this.addWeaponToInventory(new Scimitar());
    }

}
