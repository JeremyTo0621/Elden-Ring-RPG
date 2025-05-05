package game.traders;

import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Uchigatana;

/**
 * Merchant Kale, inherits the Merchant class, so he can buy and sell to players
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 */
public class MerchantKale extends Merchant{
    /**
     * constructor
     */
    public MerchantKale() {
        super("Merchant Kale", 'K', 0);
        // adds item merchant kale sells
        this.addNewBuyable(new Club());
        this.addNewBuyable(new Uchigatana());
        this.addNewBuyable(new GreatKnife());
    }



}
