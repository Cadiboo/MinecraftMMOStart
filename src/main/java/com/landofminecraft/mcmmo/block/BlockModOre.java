package com.landofminecraft.mcmmo.block;

import java.util.Random;

import com.landofminecraft.mcmmo.util.ModEnums.ModMaterials;
import com.landofminecraft.mcmmo.util.ModReference;
import com.landofminecraft.mcmmo.util.ModUtil;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockModOre extends Block implements IBlockModMaterial {

	protected final ModMaterials material;

	public BlockModOre(final ModMaterials material) {
		super(Material.ROCK);
		ModUtil.setRegistryNames(this, material, "ore");
		final int harvestLevel = Math.round(Math.round(ModUtil.map(0, ModMaterials.getHighestHardness(), 0, 3, material.getProperties().getHardness())));
		this.setHarvestLevel("pickaxe", harvestLevel);
		this.setHardness(material.getProperties().getHardness());
		this.material = material;
	}

	@Override
	public final ModMaterials getModMaterial() {
		return this.material;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isFullCube(final IBlockState state) {
		if (ModReference.Debug.debugOres()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int getLightValue(final IBlockState state, final IBlockAccess world, final BlockPos pos) {
		if (ModReference.Debug.debugOres()) {
			return 15;
		} else {
			return super.getLightValue(state, world, pos);
		}
	}

	@Override
	public int getLightOpacity(final IBlockState state, final IBlockAccess world, final BlockPos pos) {
		if (ModReference.Debug.debugOres()) {
			return 0;
		} else {
			return super.getLightOpacity(state, world, pos);
		}
	}

	@Override
	public boolean isOpaqueCube(final IBlockState state) {
		if (ModReference.Debug.debugOres()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		switch (this.getModMaterial().getType()) {
			case GEM :
				return this.getModMaterial().getResource();
			case METAL :
			default :
				return Item.getItemFromBlock(this);
		}
	}

	@Override
	public int quantityDropped(final Random random) {
		switch (this.getModMaterial().getType()) {
			case GEM :
				return (this.getModMaterial().getId() - ModMaterials.RUBY.getId()) + 1;
			case METAL :
			default :
				return super.quantityDropped(random);
		}
	}

	/**
	 * Get the quantity dropped based on the given fortune level
	 */
	@Override
	public int quantityDroppedWithBonus(final int fortune, final Random random) {
		if ((fortune > 0) && (Item.getItemFromBlock(this) != this.getItemDropped(this.getBlockState().getValidStates().iterator().next(), random, fortune))) {
			int i = random.nextInt(fortune + 2) - 1;

			if (i < 0) {
				i = 0;
			}

			return this.quantityDropped(random) * (i + 1);
		} else {
			return this.quantityDropped(random);
		}
	}

}
