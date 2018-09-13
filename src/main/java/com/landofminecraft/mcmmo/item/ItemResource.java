package com.landofminecraft.mcmmo.item;

import com.landofminecraft.mcmmo.util.ModEnums.ModMaterialTypes;
import com.landofminecraft.mcmmo.util.ModEnums.ModMaterials;
import com.landofminecraft.mcmmo.util.ModUtil;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemResource extends Item implements IItemModMaterial {

	private final ModMaterials material;
	private final ModMaterialTypes type;

	public ItemResource(final ModMaterials material, final ModMaterialTypes type) {
		ModUtil.setRegistryNames(this, material, material.getResourceNameSuffix());
		this.material = material;
		this.type = type;
	}

	@Override
	public final ModMaterials getModMaterial() {
		return this.material;
	}

	public ModMaterialTypes getType() {
		return this.type;
	}

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return ModUtil.getCreativeTabs(this);
	}

}
