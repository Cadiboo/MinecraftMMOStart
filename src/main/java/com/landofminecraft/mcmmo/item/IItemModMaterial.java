package com.landofminecraft.mcmmo.item;

import javax.annotation.Nonnull;

import com.landofminecraft.mcmmo.util.ModEnums.ModMaterials;

public interface IItemModMaterial extends IModItem {

	@Nonnull
	ModMaterials getModMaterial();

}
