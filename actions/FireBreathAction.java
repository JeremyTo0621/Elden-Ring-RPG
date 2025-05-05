package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.weapons.FireBreath;

/**
 * An action to perform the fire breath attack, attacking all actors in a certain radius
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public class FireBreathAction extends Action {

    /**
     * Constructor
     */
    public FireBreathAction() {
    }

    /**
     * Checks the surrounding location of the actor up to 3 squares away
     * if there is an Actor, call Attack Action to perform an attack
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string showing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        Location actorLocation = map.locationOf(actor);
        int maxX = map.getXRange().max();
        int maxY = map.getYRange().max();
        int top_left_x = actorLocation.x() - 3;
        int top_left_y = actorLocation.y() - 3;
        for(int i = top_left_x; i <= top_left_x + 6; i++ ){
            for(int j = top_left_y; j <= top_left_y + 6; j++ ){
                // i is x coord and j is y coord
                // only check current location when within range of map
                if(i >=0 & j >= 0 & i <= maxX & j <=maxY ){
                    Location currentLoc = map.at(i,j);
                    if(currentLoc.containsAnActor() && currentLoc.getActor() != actor){
                        result += System.lineSeparator() + new AttackAction(currentLoc.getActor(), "", new FireBreath()).execute(actor, map);
                    }
                }
            }
        }
        return actor + " breaths fire all around itself" + result;
    }


    /**
     * Describe the type of attack
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs fire breath attack";
    }
}



