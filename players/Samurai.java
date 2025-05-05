package game.players;

import game.weapons.Uchigatana;

/**
 * A Samurai player class
 *
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public class Samurai extends Player{
    /**
     * Constructor for samurai class
     *
     */
    public Samurai() {
        super(455, new Uchigatana());
    }
}
