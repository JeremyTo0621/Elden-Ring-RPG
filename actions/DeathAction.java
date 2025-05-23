package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Runes;
import game.utils.ResetManager;
import game.utils.RunesManager;
import game.utils.Status;
import game.enemies.BonePiles;
import game.utils.FancyMessage;


/**
 * An action executed if an actor is killed.
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Erin Lai Ziyi
 */
public class DeathAction extends Action {
    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";
        // if actor has ability to reset game on death, allow it to reset game
        if(target.hasCapability(Status.RESET_GAME_ON_DEATH)){
            return System.lineSeparator() + "Game reset";
        }

        ActionList dropActions = new ActionList();
        // drop all items
        for (Item item : target.getItemInventory())
            dropActions.add(item.getDropAction(target));
        for (WeaponItem weapon : target.getWeaponInventory())
            dropActions.add(weapon.getDropAction(target));
        for (Action drop : dropActions)
            drop.execute(target, map);
        // remove actor
        map.removeActor(target);
        result += System.lineSeparator() + menuDescription(target);
        return result;
    }
    /**
     * Describe the death action
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
