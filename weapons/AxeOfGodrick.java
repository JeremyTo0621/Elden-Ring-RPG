package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.AoeAttackAction;

/**
 * An Axe Of Godrick weapon that can be used to attack the enemy.
 * It deals 142 damage with 84% hit rate
 * It has a skill that attacks every actor in the holder's surroundings
 *
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public class AxeOfGodrick extends TradableWeapons{
    /**
     * Constructor.
     */
    public AxeOfGodrick() {
        super("Axe Of Godrick", 'T', 142 , "slashes", 84, 1, 100);
    }


    /**
     * to get special skill of Axe Of Godrick (aoe attack)
     *
     * @param holder weapon holder
     * @return an action to attack the surroundings
     */
    @Override
    public Action getSkill(Actor holder) {
        return new AoeAttackAction(this);
    }

    /**
     * creates a new instance of AxeOfGodrick
     * @return new instance of AxeOfGodrick
     */
    @Override
    public TradableWeapons createNewInstance() {
        return new AxeOfGodrick();
    }
}
