package com.landofminecraft.mcmmo.network;

import com.landofminecraft.mcmmo.util.ModReference;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * Manages the registry of the of network packets (it does more than this but everythings handled automagically by forge)
 * @author Cadiboo
 */
public final class ModNetworkManager {

	/**
	 * CHANEL CAN'T be longer than 20 characters due to Minecraft & forge's packet system
	 */
	public static final String CHANNEL = ModReference.MOD_ID + "_chanel";
	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL);

	public ModNetworkManager() {
		final int networkIds = 0;

		// TODO this
		/* Client -> Server */
		// NETWORK.registerMessage(CPacketSyncTileEntity.class,
		// CPacketSyncTileEntity.class, networkIds++, Side.SERVER);
		/* Server -> Client */
		// NETWORK.registerMessage(SPacketSyncTileEntity.class,
		// SPacketSyncTileEntity.class, networkIds++, Side.CLIENT);

	}

}
