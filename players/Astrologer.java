package game.players;

import game.weapons.Scimitar;

/**
 * An Astrologer player class
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class Astrologer extends Player{
    /**
     * Constructor for Astrologer class, with Scimitar as weapon since Astrologer's Staff is optional
     *
     */
    public Astrologer() {
        super(396, new Scimitar());
    }
}
