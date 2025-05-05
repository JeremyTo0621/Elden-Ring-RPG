package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.summons.Ally;
import game.summons.Invader;
import game.utils.RandomNumberGenerator;

/**
 * An action executed when player summoning at summon sign
 *
 * Created by:
 * @author Wong Yik Ping
 * Modified by:
 *
 */
public class SummonAction extends Action {

    private Location signLocation;

    /**
     * Constructor
     *
     * @param signLocation location where sign is
     */
    public SummonAction(Location signLocation) {
        this.signLocation = signLocation;
    }

    /**
     * When executed, have 50% chance to summon an Ally or Invader
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // get location of player summoning
        for (Exit exit : this.signLocation.getExits()) {
            Location currentLocation = exit.getDestination();
            if(!currentLocation.containsAnActor()){
                //50% chance to summon an Invader
                if(RandomNumberGenerator.getRandomInt(0,100) < 50){
                    currentLocation.addActor(new Invader());
                    return actor + " has summoned an Invader";
                }
                currentLocation.addActor(new Ally());
                return actor + " has summoned an Ally";
            }
        }
        return null;
    }

    /**
     * returns a description in String used for menu UI
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " summons a guest from another realm";
    }
}
