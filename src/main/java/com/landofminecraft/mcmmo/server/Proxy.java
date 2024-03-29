package com.landofminecraft.mcmmo.server;

import com.landofminecraft.mcmmo.util.IProxy;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;

public class Proxy implements IProxy {

	@Override
	public String localizeAndFormat(final String unlocalized, final Object... args) {
		return I18n.translateToLocalFormatted(unlocalized, args);
	}

	@Override
	public String localize(final String unlocalized) {
		return I18n.translateToLocal(unlocalized);
	}

	@Override
	public Side getSide() {
		return Side.SERVER;
	}

}
