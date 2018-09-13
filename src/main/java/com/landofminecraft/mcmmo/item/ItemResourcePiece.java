package com.landofminecraft.mcmmo.item;

import com.landofminecraft.mcmmo.util.ModEnums.ModMaterials;
import com.landofminecraft.mcmmo.util.ModEnums.ResourcePieceTypes;
import com.landofminecraft.mcmmo.util.ModUtil;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemResourcePiece extends Item implements IItemModMaterial {

	private final ModMaterials metal;
	private final ResourcePieceTypes type;

	public ItemResourcePiece(final ModMaterials metal, final ResourcePieceTypes type) {
		ModUtil.setRegistryNames(this, metal, type.getNameLowercase());
		this.metal = metal;
		this.type = type;
	}

	@Override
	public final ModMaterials getModMaterial() {
		return this.metal;
	}

	public ResourcePieceTypes getType() {
		return this.type;
	}

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return ModUtil.getCreativeTabs(this);
	}

}
