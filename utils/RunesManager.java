package game.utils;

import game.items.RemembranceOfTheGrafted;
import game.items.Runes;

import java.util.ArrayList;
import java.util.List;

/**
 * A runes manager class, handles the runes of the player
 * It is a singleton class
 *
 * Created by:
 * @author Jeremy To Jun Wei
 * Modified by:
 *
 */
public class RunesManager {

    /**
     * instance of RunesManager
     */
    private static RunesManager instance;

    /**
     * Player's rune instance
     */
    private Runes playerRunes;

    /**
     * Constructor.
     *
     */
    private RunesManager(){

    }

    /**
     * Returns the instance of RunesManager or creates a new one if it doesn't exist already.
     * This ensures there is only ever 1 instance of RunesManager
     *
     * @return an the instance of RunesManager
     */
    public static RunesManager getInstance(){
        if (instance == null ) {
            instance = new RunesManager();
        }
        return instance;
    }

    /**
     *
     * @param runes The runes item
     */
    public void registerPlayerRunes(Runes runes){
        playerRunes = runes;
    }

    /**
     * Increases the player's runes
     *
     * @param amount amount to add to players runes
     */
    public void addRunes(int amount){
        playerRunes.increaseRuneAmount(amount);
    }

    /**
     * Decreases the player's runes
     *
     * @param amount amount to decrease to players runes
     */
    public void decreaseRunes(int amount){
        playerRunes.decreaseRuneAmount(amount);
    }

    /**
     * Returns the current rune count of the player
     *
     * @return The number of runes the player currently has
     */
    public int getPlayerRuneAmount(){
        return playerRunes.getRuneAmount();
    }

    /**
     * set the player's runes amount
     *
     * @param amount amount to add to players runes
     */
    public void setPlayerRunes(int amount){
        playerRunes.setRuneAmount(amount);
    }
}
