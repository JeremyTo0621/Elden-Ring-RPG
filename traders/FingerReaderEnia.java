package game.traders;

import game.utils.Status;
import game.weapons.*;

/**
 * FingerReaderEnia, inherits the Merchant class, so she can buy and sell to players
 * has the TRADE_WITH_REMEMBRANCE capability so players can trade Remembrance of Grafted with this Merchant
 *
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 */
public class FingerReaderEnia extends Merchant{

    /**
     * constructor
     */
    public FingerReaderEnia() {
        super("Finger Reader Enia", 'E', 0);
        this.addCapability(Status.TRADE_WITH_REMEMBRANCE);
    }

}

