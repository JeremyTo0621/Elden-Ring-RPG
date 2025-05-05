package game.enemies;

import game.weapons.Scimitar;

/**
 * Skeletal Bandit enemy class
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class SkeletalBandit extends Skeletal{
    /**
     * Constructor
     *
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184, 35, 892);
        this.addWeaponToInventory(new Scimitar());
    }
}
