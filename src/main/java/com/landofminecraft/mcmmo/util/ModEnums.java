package com.landofminecraft.mcmmo.util;

import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.landofminecraft.mcmmo.block.BlockModOre;
import com.landofminecraft.mcmmo.block.BlockResource;
import com.landofminecraft.mcmmo.item.ItemModArmor;
import com.landofminecraft.mcmmo.item.ItemModAxe;
import com.landofminecraft.mcmmo.item.ItemModHoe;
import com.landofminecraft.mcmmo.item.ItemModPickaxe;
import com.landofminecraft.mcmmo.item.ItemModShovel;
import com.landofminecraft.mcmmo.item.ItemModSword;
import com.landofminecraft.mcmmo.item.ItemResource;
import com.landofminecraft.mcmmo.item.ItemResourcePiece;

import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

/**
 * Holds all enums and enum-related stuff for this mod
 * @author Cadiboo
 */
public final class ModEnums {

	/**
	 * provides some default methods for formatting enum names
	 * @author Cadiboo
	 */
	public interface IEnumNameFormattable {

		/**
		 * Converts the name to lowercase as per {@link java.lang.String#toLowerCase() String.toLowerCase}.
		 */
		default String getNameLowercase() {
			return this.name().toLowerCase();
		}

		/**
		 * Converts the name to uppercase as per {@link java.lang.String#toUpperCase() String.toUpperCase}.
		 */
		default String getNameUppercase() {
			return this.getNameLowercase().toUpperCase();
		}

		/**
		 * Capitalizes the name of the material as per {@link org.apache.commons.lang3.StringUtils#capitalize(String) StringUtils.capitalize}.
		 */
		default String getNameFormatted() {
			return org.apache.commons.lang3.StringUtils.capitalize(this.getNameLowercase());
		}

		/**
		 * very slightly hacky - this method is provided by Enum
		 * @return the unformatted name of the enum
		 */
		String name();

	}

	/**
	 * MOHS Hardness from <a href= "https://en.wikipedia.org/wiki/Mohs_scale_of_mineral_hardness">Wikipedia</a> and <a href= "http://periodictable.com/Properties/A/MohsHardness.v.html">Periodictable</a>
	 * @author Cadiboo
	 */

	public static enum ModMaterials implements IEnumNameFormattable {

		/* ore block ingot armor tools hard */
		// TODO Weight "Stronger but heavier than steel" & "Strong as but lighter than steel"

		BRONZE(0, ModMaterialTypes.METAL, new ModMaterialProperties(false, true, true, true, true, 3.00f)),

		STEEL(1, ModMaterialTypes.METAL, new ModMaterialProperties(false, true, true, true, true, 4.50f)),

		SILVER(2, ModMaterialTypes.METAL, new ModMaterialProperties(true, true, true, true, true, 2.50f)),

		DARK_IRON(3, ModMaterialTypes.METAL, new ModMaterialProperties(true, true, true, true, true, 5.00f)),

		LIGHT_STEEL(4, ModMaterialTypes.METAL, new ModMaterialProperties(false, true, true, true, true, 5.00f)),

		QUICK_SILVER(5, ModMaterialTypes.METAL, new ModMaterialProperties(false, true, true, true, true, 5.50f)),

		TITANIUM(6, ModMaterialTypes.METAL, new ModMaterialProperties(true, true, true, true, true, 6.00f)),

		/* technically diamond is the highest the scale goes at 10, but "-Alverium: A unique Alloy that’s stronger than Diamonds" */
		ALVERIUM(7, ModMaterialTypes.METAL, new ModMaterialProperties(true, true, true, true, true, 11.00f)),

		MIXED_CHUNK(8, ModMaterialTypes.METAL, new ModMaterialProperties(false, true, true, false, false, 3.00f)),

		GOLD(9, ModMaterialTypes.METAL, new ModMaterialProperties(true, true, true, true, true, 2.50f)),

		IRON(10, ModMaterialTypes.METAL, new ModMaterialProperties(true, true, true, true, true, 4.00f)),

		RUBY(11, ModMaterialTypes.GEM, new ModMaterialProperties(true, true, true, true, true, 4.00f)),

		SAPHIRE(12, ModMaterialTypes.GEM, new ModMaterialProperties(true, true, true, true, true, 4.00f)),

		AMETHYST(13, ModMaterialTypes.GEM, new ModMaterialProperties(true, true, true, true, true, 4.00f)),

		TOPAZ(14, ModMaterialTypes.GEM, new ModMaterialProperties(true, true, true, true, true, 4.00f));

		private final int id;
		private final ModMaterialTypes type;
		private final ModMaterialProperties properties;
		private final ArmorMaterial armorMaterial;
		private final ToolMaterial toolMaterial;

		ModMaterials(final int id, final ModMaterialTypes type, final ModMaterialProperties properties) {
			this.id = id;
			this.type = type;
			this.properties = properties;
			this.armorMaterial = this.generateArmorMaterial();
			this.toolMaterial = this.generateToolMaterial();

		}
		public int getId() {
			return this.id;
		}

		public ModMaterialTypes getType() {
			return this.type;
		}

		public ModMaterialProperties getProperties() {
			return this.properties;
		}

		public ArmorMaterial getArmorMaterial() {
			return this.armorMaterial;
		}

		public ToolMaterial getToolMaterial() {
			return this.toolMaterial;
		}

		private ToolMaterial generateToolMaterial() {
			if (!this.getProperties().hasTools()) {
				return null;
			} else {
				final String name = this.getNameUppercase();
				final int harvestLevel = Math.min(3, Math.round(this.getProperties().getHardness() / 3f));
				final int maxUses = Math.round(this.getProperties().getHardness() * 150f);
				final float efficiency = this.getProperties().getHardness();
				final float damageVsEntity = this.getProperties().getHardness();
				final int enchantability = Math.round(this.getProperties().getHardness() * 10);

				final ToolMaterial toolMaterial = EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damageVsEntity, enchantability);
				return toolMaterial;
			}
		}

		private ArmorMaterial generateArmorMaterial() {
			if (!this.getProperties().hasArmor()) {
				return null;
			} else {
				final String name = this.getNameUppercase();

				final String textureName = new ResourceLocation(this.getResouceLocationDomain("helmet", ForgeRegistries.ITEMS), this.getNameLowercase()).toString();

				final int durability = Math.round(this.getProperties().getHardness() * 5);

				final int[] reductionAmounts = new int[4];
				Arrays.fill(reductionAmounts, Math.round(this.getProperties().getHardness() / 2f));

				final int enchantability = Math.round(this.getProperties().getHardness() * 10);

				final SoundEvent soundOnEquip = SoundEvents.ITEM_ARMOR_EQUIP_IRON;

				final float toughness = Math.round(this.getProperties().getHardness() / 5f);

				final ArmorMaterial armorMaterial = EnumHelper.addArmorMaterial(name, textureName, durability, reductionAmounts, enchantability, soundOnEquip, toughness);
				// TODO TEST THIS!!
				armorMaterial.setRepairItem(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.getResouceLocationDomain("helmet", ForgeRegistries.ITEMS), this.getNameLowercase()))));
				return armorMaterial;

			}
		}

		public String getResouceLocationDomain(final String nameSuffix, final IForgeRegistry registry) {
			for (final ModContainer mod : Loader.instance().getActiveModList()) {
				if (!mod.getModId().equals(ModReference.MOD_ID)) {
					if (registry.containsKey(new ResourceLocation(mod.getModId(), this.getVanillaNameLowercase(nameSuffix) + "_" + nameSuffix))) {
						return mod.getModId();
					}
				}
			}
			return ModReference.MOD_ID;
		}

		public String getVanillaNameLowercase(final String suffix) {
			switch (suffix.toLowerCase()) {
				case "sword" :
				case "shovel" :
				case "pickaxe" :
				case "axe" :
				case "hoe" :
				case "helmet" :
				case "chestplate" :
				case "leggings" :
				case "boots" :
				case "apple" :
				case "carrot" :
				case "horse_armor" :
					return this.getNameLowercase() + (this.getNameLowercase().contains("gold") ? "en" : "");
				default :
					return this.getNameLowercase();
			}

		}

		public String getVanillaNameUppercase(final String suffix) {
			return this.getVanillaNameLowercase(suffix).toUpperCase();
		}

		public String getVanillaNameFormatted(final String suffix) {
			return StringUtils.capitalize(this.getVanillaNameLowercase(suffix));
		}

		@Nullable
		public BlockModOre getOre() {
			if (!this.getProperties().hasOre()) {
				return null;
			}
			return (BlockModOre) this.getRegistryValue(ForgeRegistries.BLOCKS, "ore");
		}

		@Nullable
		public BlockResource getBlock() {
			if (!this.getProperties().hasBlock()) {
				return null;
			}
			return (BlockResource) this.getRegistryValue(ForgeRegistries.BLOCKS, "block");
		}

		@Nullable
		public ItemResource getResource() {
			if (!this.getProperties().hasResource()) {
				return null;
			}
			return (ItemResource) this.getRegistryValue(ForgeRegistries.ITEMS, this.getResourceNameSuffix());
		}

		public String getResourceNameSuffix() {
			if (!this.getProperties().hasResource()) {
				return null;
			} else {
				return this.getType().getGameNameLowercase();
			}
		}
		@Nullable
		public ItemResourcePiece getResourcePiece() {
			if ((!this.getProperties().hasResource()) || (!this.getType().hasResourcePiece())) {
				return null;
			} else {
				return (ItemResourcePiece) this.getRegistryValue(ForgeRegistries.ITEMS, this.getResourcePieceNameSuffix());
			}
		}

		public String getResourcePieceNameSuffix() {
			if (!this.getProperties().hasResource()) {
				return null;
			} else {
				return this.getProperties().getResourcePieceType().getNameLowercase();
			}
		}

		@Nullable
		public ItemModArmor getHelmet() {
			if (!this.getProperties().hasArmor()) {
				return null;
			}
			return (ItemModArmor) this.getRegistryValue(ForgeRegistries.ITEMS, "helmet");
		}

		@Nullable
		public ItemModArmor getChestplate() {
			if (!this.getProperties().hasArmor()) {
				return null;
			}
			return (ItemModArmor) this.getRegistryValue(ForgeRegistries.ITEMS, "chestplate");
		}

		@Nullable
		public ItemModArmor getLeggings() {
			if (!this.getProperties().hasArmor()) {
				return null;
			}
			return (ItemModArmor) this.getRegistryValue(ForgeRegistries.ITEMS, "leggings");
		}

		@Nullable
		public ItemModArmor getBoots() {
			if (!this.getProperties().hasArmor()) {
				return null;
			}
			return (ItemModArmor) this.getRegistryValue(ForgeRegistries.ITEMS, "boots");
		}

		@Nullable
		public ItemModPickaxe getPickaxe() {
			if (!this.getProperties().hasTools()) {
				return null;
			}
			return (ItemModPickaxe) this.getRegistryValue(ForgeRegistries.ITEMS, "pickaxe");
		}

		@Nullable
		public ItemModAxe getAxe() {
			if (!this.getProperties().hasTools()) {
				return null;
			}
			return (ItemModAxe) this.getRegistryValue(ForgeRegistries.ITEMS, "axe");
		}

		@Nullable
		public ItemModSword getSword() {
			if (!this.getProperties().hasTools()) {
				return null;
			}
			return (ItemModSword) this.getRegistryValue(ForgeRegistries.ITEMS, "sword");
		}

		@Nullable
		public ItemModShovel getShovel() {
			if (!this.getProperties().hasTools()) {
				return null;
			}
			return (ItemModShovel) this.getRegistryValue(ForgeRegistries.ITEMS, "shovel");
		}

		@Nullable
		public ItemModHoe getHoe() {
			if (!this.getProperties().hasTools()) {
				return null;
			}
			return (ItemModHoe) this.getRegistryValue(ForgeRegistries.ITEMS, "hoe");
		}

		@Nullable
		private <T> T getRegistryValue(@Nonnull final IForgeRegistry<? extends IForgeRegistryEntry<T>> registry, @Nonnull String nameSuffix) {
			nameSuffix = nameSuffix.toLowerCase();
			return (T) registry.getValue(new ResourceLocation(this.getResouceLocationDomain(nameSuffix, registry), this.getNameLowercase() + "_" + nameSuffix));
		}

		public static float getHighestHardness() {
			float highest = 0;
			for (final ModMaterials material : values()) {
				if (material.getProperties().getHardness() > highest) {
					highest = material.getProperties().getHardness();
				}
			}
			return highest;
		}

		public Material getVanillaMaterial() {
			return Material.IRON;
		}

		public static ModMaterials byId(final int id) {
			return values()[Math.min(Math.abs(id), values().length)];
		}

	}

	public static enum ModMaterialTypes implements IEnumNameFormattable {

		METAL(0), GEM(1);

		private final int id;

		ModMaterialTypes(final int id) {
			this.id = id;
		}

		public int getId() {
			return this.id;
		}

		public static ModMaterialTypes byId(final int id) {
			return values()[Math.min(Math.abs(id), values().length)];
		}

		public boolean hasResourcePiece() {
			boolean hasResourcePiece;
			switch (byId(this.getId())) {
				case GEM :
					hasResourcePiece = false;
					break;
				default :
				case METAL :
					hasResourcePiece = true;
					break;
			}
			return hasResourcePiece;
		}

		public String getGameNameLowercase() {
			String gameName;
			switch (byId(this.getId())) {
				case GEM :
					gameName = "gem";
					break;
				default :
				case METAL :
					gameName = "ingot";
					break;
			}
			return gameName;
		}
	}

	public enum ResourcePieceTypes implements IEnumNameFormattable {

		NUGGET(0),

		SHARD(1);

		final int id;
		ResourcePieceTypes(final int id) {
			this.id = id;
		}

		public int getId() {
			return this.id;
		}

		public static ResourcePieceTypes byId(final int id) {
			return values()[Math.min(Math.abs(id), values().length)];
		}

	}

}
