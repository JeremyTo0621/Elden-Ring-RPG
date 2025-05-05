package game.players;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ResetAction;
import game.items.Runes;
import game.utils.FancyMessage;
import game.utils.RunesManager;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.FlaskOfCrimsonTears;
import game.utils.ResetManager;
import game.interfaces.Resettable;
import game.utils.Status;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Wong Yik Ping
 *
 */
public abstract class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	/**
	 * player's max hit points
	 */
	private final int maxHp;

	/**
	 * Constructor.
	 *
	 * @param hitPoints   Player's starting number of hit points
	 * @param playerWeapon Player's starting weapon
	 */
	public Player(int hitPoints, WeaponItem playerWeapon) {
		super("Tarnished", '@', hitPoints);
		ResetManager resetManager = ResetManager.getInstance();
		resetManager.registerResettable(this);
		//max hp save
		maxHp = hitPoints;
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.PLAYER);
		this.addCapability(Status.CAN_TAKE_RUNES);
		this.addCapability(Status.RESET_GAME_ON_DEATH);
		this.addCapability(Status.NOT_HOSTILE_SUMMONS);
		this.addWeaponToInventory(playerWeapon);
		// Flask of Crimson tears
		FlaskOfCrimsonTears flask = new FlaskOfCrimsonTears();
		this.addItemToInventory(flask);
		// Runes
		Runes runes = new Runes(5000);
		RunesManager runeManager = RunesManager.getInstance();
		runeManager.registerPlayerRunes(runes);
	}

	/**
	 *	at each turn selects valid action to perform.
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return displays the action carried out in each turn
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		//if dead, reset
		if(!this.isConscious()){
			// move player to previous location before dying
			Location prevLocation = ResetManager.getInstance().getPlayerPrevLocation();
			if(map.locationOf(this) != prevLocation) {
				if(!prevLocation.containsAnActor()) {
					map.moveActor(this, prevLocation);
				}
				else{
					// remove any actor in that location
					Actor actor = prevLocation.getActor();
					map.removeActor(actor);
					map.moveActor(this, prevLocation);
				}
			}
			// drop rune
			RunesManager runeManager = RunesManager.getInstance();
			int playerRuneAmount = runeManager.getPlayerRuneAmount();
			//decrease player rune amount
			runeManager.setPlayerRunes(0);
			Runes dropRune = new Runes(playerRuneAmount);
			dropRune.getDropAction(this).execute(this, map);

			// run the reset action
			ResetAction resetAction = new ResetAction();
			//resetAction.execute(this, map);

			// Print death message
			for (String line : FancyMessage.YOU_DIED.split("\n")) {
				new Display().println(line);
				try {
					Thread.sleep(200);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
			return resetAction;
		}

		display.println(this + " "+this.printHp() + " runes: "+ RunesManager.getInstance().getPlayerRuneAmount());
		// set previous Location at Reset Manager
		ResetManager.getInstance().setPlayerPrevLocation(map.locationOf(this));

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * the intrinsic skill of the player (fist)
	 * @return the damage dealt, the name of the skill
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11,"punch");
	}

	/**
	 * getter for the max  hit points
	 * @return the max hit points
	 */
	public int getMaxHitPoints(){
		return this.maxHitPoints;
	}

	/**
	 * resets player
	 */
	@Override
	public void reset() {
		this.resetMaxHp(maxHp);
	}

}
