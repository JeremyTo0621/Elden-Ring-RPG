package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Wall extends Ground {
	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
	}

	/**
	 * no actor can enter
	 * @param actor the Actor to check
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * no object can be dropped at the wall
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
