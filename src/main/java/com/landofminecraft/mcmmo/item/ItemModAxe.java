package com.landofminecraft.mcmmo.item;

import com.landofminecraft.mcmmo.util.ModEnums.ModMaterials;
import com.landofminecraft.mcmmo.util.ModUtil;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

public class ItemModAxe extends ItemAxe implements IItemModMaterial {

	private final ModMaterials metal;

	public ItemModAxe(final ModMaterials metal) {
		super(metal.getToolMaterial(), metal.getToolMaterial().getAttackDamage(), metal.getToolMaterial().getEfficiency());
		ModUtil.setRegistryNames(this, metal, "axe");
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
