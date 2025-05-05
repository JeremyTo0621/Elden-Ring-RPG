package game.players;

import game.weapons.Club;
import game.weapons.GreatKnife;

/**
 * A Bandit player class
 *
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public class Bandit extends Player{
    /**
     * Constructor for Bandit class
     *
     */
    public Bandit() {
        super(414, new GreatKnife());
    }
}
