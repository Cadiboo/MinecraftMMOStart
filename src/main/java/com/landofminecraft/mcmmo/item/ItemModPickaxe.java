package com.landofminecraft.mcmmo.item;

import com.landofminecraft.mcmmo.util.ModEnums.ModMaterials;
import com.landofminecraft.mcmmo.util.ModUtil;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class ItemModPickaxe extends ItemPickaxe implements IItemModMaterial {

	private final ModMaterials metal;

	public ItemModPickaxe(final ModMaterials metal) {
		super(metal.getToolMaterial());
		ModUtil.setRegistryNames(this, metal, "pickaxe");
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
