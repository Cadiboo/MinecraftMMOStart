package com.landofminecraft.mcmmo;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.landofminecraft.mcmmo.network.ModNetworkManager;
import com.landofminecraft.mcmmo.util.IProxy;
import com.landofminecraft.mcmmo.util.ModGuiHandler;
import com.landofminecraft.mcmmo.util.ModReference;
import com.landofminecraft.mcmmo.world.gen.ModWorldGenerator;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * MCMMO
 * @authors Telemindred, MDW_01, Cadiboo, Oscar and GlitchedWinner
 */

/*@formatter:off*/
@Mod(modid = ModReference.MOD_ID,
	name = ModReference.MOD_NAME,
	version = ModReference.Version.VERSION,
	acceptedMinecraftVersions = ModReference.ACCEPTED_VERSIONS,
	dependencies = ModReference.DEPENDENCIES,
	clientSideOnly = false,
	serverSideOnly = false,
	modLanguage = "java")
/*@formatter:on*/
public final class MinecraftMMO {

	/****** TODO LIST ******/
	// TODO Weapons
	// -Maces/Hammers
	// -Crossbows & shortbows
	// -Javelins/Spears
	// -War Axes
	// -Curved Swords
	// -Daggers/throwing knives

	// TODO Wagon/Carriage
	// - Crafting: wip
	// - Function: Wagons are things players can use to transport more items than they can carry. Carriages allow a player to transport other players or storage across distances. Carriage is pulled by a horse or donkey.

	// TODO Worktable
	// - A 1x2 table that players can use to craft. It has a 4x4 crafting grid allowing for more complex crafts.

	// TODO Blast Furnace
	// How to craft: Must craft 8 Corner Furnace blocks (5 stone bricks, 3 smooth darkstone, 1 blaze powder). Place the corners in a 2x2 radius and the furnace will be forged. When it’s built, player needs to use a pickaxe on 1 side to create the input/output slot. Then the player needs to choose another side (excluding bottom) for the fuel input.
	// Use: Furnace has two input slots making smelting faster. Speed of smelting is 50% faster than a normal furnace. Special use: By putting two ingots in the two inputs, one can make an alloy. If the ingots are not compatible, only a mixed chunk will come out. Placing an ore in a blast furnace reaps double the original haul.
	// Blast Furnace can have an output slot as well as an input slot for liquids as well. If players want to do so, they can place any metal in the third slot in the gui which will smelt the metal into a liquid. Players can either store the liquid in a bucket, or have it sent out via pipe into a tank for storage instead.
	// Fuel: Either manually or via pipes insert lava, or manually insert coal or coal blocks. (coal and other non lava materials burn twice as quickly)

	// TODO Grinder
	// Crafting: wip.
	// Use: Use to increase weapon base damage by a certain amount.

	// TODO Anvil: Players can combine metal to armors to either repair them, or increase their base defense by a certain amount.

	// TODO Tubes and Pumps
	// - Pumps:
	// https://goo.gl/images/6VLBYL
	// Crafting: wip
	// Use: By setting it up correctly, pump will bring liquid from a large body of liquid up, and can transport it into tanks or tubes. Can push liquids higher up to about 10 blocks high.
	//
	// - Tubing
	// Crafting: wip
	// Types - flammable, heat resistant // sealed, unsealed
	// Flammable or not determines whether or not the tube can catch fire or transport lava or not. Sealed means that the liquid doesn’t leak and drip out the bottom. If it is unsealed, there is a chance the liquid will drip out the bottom, losing 0.1 of the liquid and creating a puddle below.
	// Inputs - Anything that can hold or store liquids the pipes can connect to, be it tanks, blast furnace, sprayer, or if nothing is connected the liquid just pours out.

	// TODO Sprayer
	// Crafting: wip.
	// Use: Flings whatever liquid it is storing at whatever direction it’s aiming. This is useful for defenses and traps as one can fling lava out at their enemy.

	// TODO Tank
	// Crafting: wip.
	// Use: Stores liquids, whether water, lava or liquid metal. Players can place buckets beside the tank and pour liquid directly into the bucket as well. Tanks can be picked up and carried by the player.

	// TODO Casting Table
	// Crafting: wip.
	// Use: By pouring liquid metal into the casting table, you can craft any sort of metal object you wish, be it block or item. By crafting metal weapons with a full bucket of liquid metal (9 ingots), you increase the durability and damage, but also increase the cooldown and reduce swinging speed.

	@Instance(ModReference.MOD_ID)
	public static MinecraftMMO instance;

	@SidedProxy(serverSide = ModReference.SERVER_PROXY_CLASS, clientSide = ModReference.CLIENT_PROXY_CLASS)
	public static IProxy proxy;

	private static Logger logger;

	/**
	 * Run before anything else. <s>Read your config, create blocks, items, etc, and register them with the GameRegistry</s>
	 * @see {@link net.minecraftforge.common.ForgeModContainer#preInit(FMLPreInitializationEvent) ForgeModContainer.preInit}
	 */
	@EventHandler
	public void preInit(final FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.logLogicalSide();
		GameRegistry.registerWorldGenerator(new ModWorldGenerator(), 3);
		new ModNetworkManager();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());

		// register capabilities

	}

	/**
	 * Do your mod setup. Build whatever data structures you care about. Register recipes, send FMLInterModComms messages to other mods.
	 */
	@EventHandler
	public void init(final FMLInitializationEvent event) {
	}

	/**
	 * Mod compatibility, or anything which depends on other mods’ init phases being finished.
	 * @see {@link net.minecraftforge.common.ForgeModContainer#postInit(FMLPostInitializationEvent) ForgeModContainer.postInit}
	 */
	@EventHandler
	public void postInit(final FMLPostInitializationEvent event) {
	}

	private static Logger getLogger() {
		if (logger == null) {
			final Logger tempLogger = LogManager.getLogger();
			tempLogger.error("[" + MinecraftMMO.class.getSimpleName() + "]: getLogger called before logger has been initalised! Providing default logger");
			return tempLogger;
		}
		return logger;
	}

	/**
	 * Logs message object(s) with the {@link org.apache.logging.log4j.Level#DEBUG DEBUG} level.
	 * @param  messages the message objects to log.
	 * @author          Cadiboo
	 */
	public static void debug(final Object... messages) {
		for (final Object msg : messages) {
			getLogger().debug(msg);
		}
	}

	/**
	 * Logs message object(s) with the {@link org.apache.logging.log4j.Level#INFO ERROR} INFO.
	 * @param  messages the message objects to log.
	 * @author          Cadiboo
	 */
	public static void info(final Object... messages) {
		for (final Object msg : messages) {
			getLogger().info(msg);
		}
	}

	/**
	 * Logs message object(s) with the {@link org.apache.logging.log4j.Level#WARN WARN} level.
	 * @param  messages the message objects to log.
	 * @author          Cadiboo
	 */
	public static void warn(final Object... messages) {
		for (final Object msg : messages) {
			getLogger().warn(msg);
		}
	}

	/**
	 * Logs message object(s) with the {@link org.apache.logging.log4j.Level#ERROR ERROR} level.
	 * @param  messages the message objects to log.
	 * @author          Cadiboo
	 */
	public static void error(final Object... messages) {
		for (final Object msg : messages) {
			getLogger().error(msg);
		}
	}

	/**
	 * Logs message object(s) with the {@link org.apache.logging.log4j.Level#FATAL FATAL} level.
	 * @param  messages the message objects to log.
	 * @author          Cadiboo
	 */
	public static void fatal(final Object... messages) {
		for (final Object msg : messages) {
			getLogger().fatal(msg);
		}
	}

	/**
	 * Logs all {@link java.lang.reflect.Field Field}s and their values of an object with the {@link org.apache.logging.log4j.Level#INFO INFO} level.
	 * @param  objects the objects to dump.
	 * @author         Cadiboo
	 */
	public static void dump(final Object... objects) {
		for (final Object obj : objects) {
			final Field[] fields = obj.getClass().getDeclaredFields();
			info("Dump of " + obj + ":");
			for (int i = 0; i < fields.length; i++) {
				try {
					fields[i].setAccessible(true);
					info(fields[i].getName() + " - " + fields[i].get(obj));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					info("Error getting field " + fields[i].getName());
					info(e.getLocalizedMessage());
				}
			}
		}
	}

}
