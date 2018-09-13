package com.landofminecraft.mcmmo.item;

import com.landofminecraft.mcmmo.util.ModEnums.ModMaterials;
import com.landofminecraft.mcmmo.util.ModUtil;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemModArmor extends ItemArmor implements IItemModMaterial {

	private final ModMaterials metal;

	public ItemModArmor(final ModMaterials metal, final EntityEquipmentSlot slotIn) {
		super(metal.getArmorMaterial(), metal.getId() + 5, slotIn);
		ModUtil.setRegistryNames(this, metal, ModUtil.getSlotGameNameLowercase(slotIn));
		this.metal = metal;
	}

	@Override
	public boolean hasOverlay(final ItemStack stack) {
		return false;
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
