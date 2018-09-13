package com.landofminecraft.mcmmo.item;
import com.landofminecraft.mcmmo.util.ModEnums.ModMaterials;
import com.landofminecraft.mcmmo.util.ModUtil;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ItemModSword extends ItemSword implements IItemModMaterial {

	protected final ModMaterials metal;

	public ItemModSword(final ModMaterials metal) {
		super(metal.getToolMaterial());
		ModUtil.setRegistryNames(this, metal, "sword");
		this.metal = metal;
	}

	@Override
	public final ModMaterials getModMaterial() {
		return this.metal;
	}

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return ModUtil.getCreativeTabs(this);
	}

}
