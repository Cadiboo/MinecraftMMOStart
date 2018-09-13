package com.landofminecraft.mcmmo.item;

import com.landofminecraft.mcmmo.util.ModEnums.ModMaterials;
import com.landofminecraft.mcmmo.util.ModUtil;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;

public class ItemModShovel extends ItemSpade implements IItemModMaterial {

	private final ModMaterials metal;

	public ItemModShovel(final ModMaterials metal) {
		super(metal.getToolMaterial());
		ModUtil.setRegistryNames(this, metal, "shovel");
		this.metal = metal;
	}

	@Override
	public ModMaterials getModMaterial() {
		return this.metal;
	}

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return ModUtil.getCreativeTabs(this);
	}

}
