package game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.bosses.NecromancerOfNulls;
import game.enemies.GiantCrab;
import game.enemies.HeavySkeletalSwordsman;
import game.items.GoldenRunes;
import game.players.*;
import game.summons.Ally;
import game.summons.Invader;
import game.items.RemembranceOfTheGrafted;
import game.players.Bandit;
import game.players.Samurai;
import game.players.Wretch;
import game.traders.FingerReaderEnia;
import game.traders.MerchantKale;
import game.utils.FancyMessage;
import game.enemies.LoneWolf;
import game.environments.*;
import game.utils.ResetManager;
import game.utils.RunesManager;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Graveyard(), new PuddleOfWater(), new GustOfWind(), new SiteOfLostGrace(), new Cliff(), new Barrack(), new Cage(), new SummonSign());

		List<String> limgrave = Arrays.asList(
				"......................#.............#..........................+++.........",
				"......................#.............#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				"......................#...........__#............................++........",
				"......................#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"...........................................................................",
				"...........................=...............................................",
				"........++++......................###___###................................",
				"........+++++++...................________#................................",
				"..........+++.....................#________................................",
				"............+++...................#_______#................................",
				".............+....................###___###................................",
				"............++......................#___#..................................",
				"..............+...................=........................................",
				"..............++.................................................=.........",
				"..............................................++...........................",
				"..................++++......................+++...............######..##...",
				"#####___######....++...........................+++............#....____....",
				"_____________#.....++++..........................+..............__.....#...",
				"_____________#.....+....++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");
		List<String> stormveilCastle = Arrays.asList(
				"...........................................................................",
				"..................<...............<........................................",
				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#................#.............................",
				"...............................<.........<.................................",
				".....B...............B......#................#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____....&&........<..............<..............................",
				".........____......&&......................................................",
				"...._______..................<..............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");
		List<String> roundtableHold = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######");
		List<String> bossRoom = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				"..=......................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++");

		// create map and add to game map
		GameMap limgraveMap = new GameMap(groundFactory, limgrave);
		world.addGameMap(limgraveMap);
		GameMap stormveilCastleMap = new GameMap(groundFactory, stormveilCastle);
		world.addGameMap(stormveilCastleMap);
		GameMap roundtableHoldMap = new GameMap(groundFactory, roundtableHold);
		world.addGameMap(roundtableHoldMap);
		GameMap bossRoomMap = new GameMap(groundFactory, bossRoom);
		world.addGameMap(bossRoomMap);

		//add limgrave golden fog door
		GoldenFogDoor limgraveDoor1 = new GoldenFogDoor(limgraveMap, limgraveMap.at(40,11));
		GoldenFogDoor limgraveDoor2 = new GoldenFogDoor(limgraveMap, limgraveMap.at(30,3));
		GoldenFogDoor limgraveDoor3 = new GoldenFogDoor(limgraveMap, limgraveMap.at(6,22));
		//add stormveil castle golden fog door
		GoldenFogDoor stormveilDoor1 = new GoldenFogDoor(stormveilCastleMap, stormveilCastleMap.at(40,23));
		GoldenFogDoor stormveilDoor2 = new GoldenFogDoor(stormveilCastleMap, stormveilCastleMap.at(50,0));
		//add roundtable Hold golden fog door
		GoldenFogDoor roundtableHoldDoor = new GoldenFogDoor(roundtableHoldMap, roundtableHoldMap.at(9,9));

		//set door destination
		limgraveDoor1.setDestinationDoor(roundtableHoldDoor, "Roundtable Hold");
		limgraveDoor2.setDestinationDoor(stormveilDoor1, "Stormveil Castle");
		limgraveDoor3.setDestinationDoor(bossRoomMap, bossRoomMap.at(11,7), "Boss Room");
		stormveilDoor1.setDestinationDoor(limgraveDoor1, "Limgrave");
		stormveilDoor2.setDestinationDoor(bossRoomMap, bossRoomMap.at(11,7), "Boss Room");
		roundtableHoldDoor.setDestinationDoor(limgraveDoor1, "Limgrave");

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		// Ask user to pick player class
		Display display = new Display();
		// create hashmap with key as character and value as player
		HashMap<Character, Player> keyToPlayerMap = new HashMap<Character, Player>();
		// initialize all possible role/classes
		keyToPlayerMap.put('b', new Bandit());
		keyToPlayerMap.put('s', new Samurai());
		keyToPlayerMap.put('w', new Wretch());
		keyToPlayerMap.put('a', new Astrologer());
		// print out options
		display.println("Select your role:");
		display.println("b: Bandit");
		display.println("s: Samurai");
		display.println("w: Wretch");
		display.println("a: Astrologer");
		char input;
		// ask for input
		do {
			input = display.readChar();
		} while (!keyToPlayerMap.containsKey(input));
		// get selected role
		Player player = keyToPlayerMap.get(input);
		// spawns player
		world.addPlayer(player, limgraveMap.at(36, 10));
		// set default respawn point
		ResetManager resetManager = ResetManager.getInstance();
		resetManager.setPlayerRespawn(limgraveMap.at(36, 10), limgraveMap);

		// set current map player is on
		resetManager.setMap(limgraveMap);

		// spawn merchant Kale
		limgraveMap.addActor(new MerchantKale(), limgraveMap.at(38,12));

		// spawn finger of enia
		roundtableHoldMap.addActor(new FingerReaderEnia(), roundtableHoldMap.at(9,5));
		player.addItemToInventory(new RemembranceOfTheGrafted());

		// spawn Necromancer of Nulls boss at boss room
		bossRoomMap.addActor(new NecromancerOfNulls(), bossRoomMap.at(13,2));

		// The first step grace
		limgraveMap.at(37, 11).setGround(new SiteOfLostGrace("The First Step"));

		// Table of Lost Grace
		roundtableHoldMap.at(9,3).setGround(new SiteOfLostGrace("Table of Lost Grace"));

		// add golden runes to map
		limgraveMap.at(38, 9).addItem(new GoldenRunes());
		limgraveMap.at(15, 4).addItem(new GoldenRunes());
		limgraveMap.at(65, 19).addItem(new GoldenRunes());

		stormveilCastleMap.at(38, 20).addItem(new GoldenRunes());
		stormveilCastleMap.at(43, 5).addItem(new GoldenRunes());

		// run world
		world.run();
	}
}
