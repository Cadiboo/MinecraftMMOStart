package com.landofminecraft.mcmmo.util;

import com.landofminecraft.mcmmo.util.ModEnums.ResourcePieceTypes;

public class ModMaterialProperties {

	private final boolean hasOre;
	private final boolean hasBlock;
	private final boolean hasResouce;
	private final ResourcePieceTypes resourcePieceTypes;
	private final boolean hasArmor;
	private final boolean hasTools;
	private final float hardness;

	public ModMaterialProperties(final boolean hasOre, final boolean hasBlock, final boolean hasResouce, final boolean hasArmor, final boolean hasTools, final float MOHS_Hardness) {
		this.hasOre = hasOre;
		this.hasBlock = hasBlock;
		this.hasResouce = hasResouce;
		this.resourcePieceTypes = MOHS_Hardness < 5 ? ResourcePieceTypes.NUGGET : ResourcePieceTypes.SHARD;
		this.hasArmor = hasArmor;
		this.hasTools = hasTools;
		this.hardness = MOHS_Hardness;
	}

	public final boolean hasOre() {
		return this.hasOre;
	}

	public final boolean hasBlock() {
		return this.hasBlock;
	}

	public final boolean hasResource() {
		return this.hasResouce;
	}

	public final ResourcePieceTypes getResourcePieceType() {
		return this.resourcePieceTypes;
	}

	public final boolean hasArmor() {
		return this.hasArmor;
	}

	public final boolean hasTools() {
		return this.hasTools;
	}

	public final float getHardness() {
		return this.hardness;
	}

}
