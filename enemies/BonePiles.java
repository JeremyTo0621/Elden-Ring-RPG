package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.BoneReviveAction;
import game.actions.DropRunesAction;
import game.utils.Status;

/**
 * piles of bone enemy class
 *
 * Created by:
 * @author Erin Lai Ziyi
 * Modified by:
 *
 */
public class BonePiles extends Enemies{
    /**
     * a counter to represent how many turns bone piles existed
     */
    private int counter;
    /**
     * actor who is turning into pile of bones
     */
    private Skeletal skeletalType;

    /**
     * Constructor.
     * @param skeletal skeletal enemy that is turning into pile of bones
     */
    public BonePiles(Skeletal skeletal) {
        super("Pile of Bones", 'X', 1, Status.SKELETAL, skeletal.runesLowerBound, skeletal.runesUpperBound);
        // add weapon of skeletal to BonePiles
        for(WeaponItem weapon: skeletal.getWeaponInventory()){
            this.addWeaponToInventory(weapon);
        }
        this.addCapability(Status.PILES_OF_BONES);
        this.skeletalType = skeletal;
        this.counter = 0;
    }

    /**
     * at every turn, increment counter for BonePiles, once it reach 3 it will be revived to the Skeletal it was before it turned
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return bone revive action or do nothing action if bone piles is not getting revived.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // drop rune if dead
        if(!this.isConscious()){
            return new DropRunesAction(this);
        }
        counter ++;
        if(counter == 3){
            EnemiesFactory enemiesFactory = new EnemiesFactory();
            enemiesFactory.addEnemyToFactory(this.skeletalType);
            Location currentLocation = map.locationOf(this);
            map.removeActor(this);
            // add new revived skeletal enemy
            currentLocation.addActor(enemiesFactory.newEnemy(this.skeletalType.getDisplayChar()));
            return new BoneReviveAction();
        }
        return new DoNothingAction();
    }


}
