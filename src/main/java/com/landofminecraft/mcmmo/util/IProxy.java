package com.landofminecraft.mcmmo.util;

import com.landofminecraft.mcmmo.MinecraftMMO;

import net.minecraftforge.fml.relauncher.Side;

/**
 * Some basic functions that differ depending on the logical side
 * @author Cadiboo
 */
public interface IProxy {

	String localize(String unlocalized);

	String localizeAndFormat(String unlocalized, Object... args);

	default void logLogicalSide() {
		MinecraftMMO.info("Logical Side: " + getSide());
	}

	Side getSide();
}