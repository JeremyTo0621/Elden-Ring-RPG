package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.QuickStepAction;
/**
 * A GreatKnife weapon that can be used to attack the enemy.
 * It deals 75 damage with 70% hit rate
 * It has a special skill that moves/evade after attacking (QuickStep)
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public class GreatKnife extends TradableWeapons {
    /**
     * Constructor
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "slashes", 70, 3500, 350);
    }

    /**
     * returns the special skill of GreatKnife which moves/evade from target after attacking
     *
     * @param target target actor
     * @param direction
     * @return the special skill of GreatKnife, the QuickStepAction
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new QuickStepAction(this, direction, target);
    }

    /**
     * creates a new instance of GreatKnife
     * @return new instance of GreatKnife
     */
    @Override
    public TradableWeapons createNewInstance() {
        return new GreatKnife();
    }
}
