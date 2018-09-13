package com.landofminecraft.mcmmo.block;

import com.landofminecraft.mcmmo.util.ModEnums.ModMaterials;
import com.landofminecraft.mcmmo.util.ModUtil;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockResource extends Block implements IBlockModMaterial {

	protected final ModMaterials material;

	public BlockResource(final ModMaterials materialIn) {
		super(materialIn.getVanillaMaterial());
		ModUtil.setRegistryNames(this, materialIn, "block");
		this.material = materialIn;
	}

	@Override
	public final ModMaterials getModMaterial() {
		return this.material;
	}

	@Override
	public boolean isBeaconBase(final IBlockAccess worldObj, final BlockPos pos, final BlockPos beacon) {
		return true;
	}

}
