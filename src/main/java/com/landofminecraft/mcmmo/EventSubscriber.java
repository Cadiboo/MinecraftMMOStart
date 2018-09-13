package com.landofminecraft.mcmmo;

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
import com.landofminecraft.mcmmo.item.ModItemBlock;
import com.landofminecraft.mcmmo.util.ModEnums.ModMaterials;
import com.landofminecraft.mcmmo.util.ModReference;
import com.landofminecraft.mcmmo.util.ModUtil;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID)
public final class EventSubscriber {

	private static int entityId = 0;

	@SubscribeEvent
	public static void onRegisterBlocksEvent(final RegistryEvent.Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();

		registerBlocksForMaterials(registry);

		MinecraftMMO.info("Registered blocks");

		registerTileEntities();

		MinecraftMMO.debug("Registered tile entities");

	}

	private static void registerBlocksForMaterials(final IForgeRegistry<Block> registry) {
		for (final ModMaterials material : ModMaterials.values()) {
			if (material.getProperties().hasOre()) {
				registry.register(new BlockModOre(material));
			}

			if (material.getProperties().hasBlock()) {
				registry.register(new BlockResource(material));
			}

		}
		MinecraftMMO.debug("Registered blocks for materials");
	}

	private static void registerTileEntities() {
		// registerTileEntity(TileEntity.class);
		// registerTileEntity(TileEntity.class);
	}

	private static void registerTileEntity(final Class<? extends TileEntity> clazz) {
		try {
			GameRegistry.registerTileEntity(clazz, new ResourceLocation(ModReference.MOD_ID, ModUtil.getRegistryNameForClass(clazz, "TileEntity")));
		} catch (final Exception e) {
			MinecraftMMO.error("Error registering Tile Entity " + clazz.getSimpleName());
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	public static void onRegisterItemsEvent(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		registerItemsForMaterials(registry);

		MinecraftMMO.info("Registered items");

	}

	private static void registerItemsForMaterials(final IForgeRegistry<Item> registry) {
		for (final ModMaterials material : ModMaterials.values()) {
			if (material.getProperties().hasOre()) {
				registry.register(new ModItemBlock(material.getOre(), new ResourceLocation(material.getResouceLocationDomain("ore", ForgeRegistries.ITEMS), material.getNameLowercase() + "_ore")));
			}

			if (material.getProperties().hasBlock()) {
				registry.register(new ModItemBlock(material.getBlock(), new ResourceLocation(material.getResouceLocationDomain("block", ForgeRegistries.ITEMS), material.getNameLowercase() + "_block")));
			}

			if (material.getProperties().hasResource()) {
				registry.register(new ItemResource(material, material.getType()));
				if (material.getType().hasResourcePiece()) {
					registry.register(new ItemResourcePiece(material, material.getProperties().getResourcePieceType()));
				}
			}

			if (material.getProperties().hasArmor()) {
				registry.register(new ItemModArmor(material, EntityEquipmentSlot.HEAD));
				registry.register(new ItemModArmor(material, EntityEquipmentSlot.CHEST));
				registry.register(new ItemModArmor(material, EntityEquipmentSlot.LEGS));
				registry.register(new ItemModArmor(material, EntityEquipmentSlot.FEET));
			}

			if (material.getProperties().hasTools()) {
				registry.register(new ItemModPickaxe(material));
				registry.register(new ItemModAxe(material));
				registry.register(new ItemModSword(material));
				registry.register(new ItemModShovel(material));
				registry.register(new ItemModHoe(material));
			}

		}

		MinecraftMMO.debug("Registered items for materials");

	}

	@SubscribeEvent
	public static void onRegisterEntitiesEvent(final RegistryEvent.Register<EntityEntry> event) {
		final IForgeRegistry<EntityEntry> registry = event.getRegistry();

		registerEntitiesForMaterials(registry);

		MinecraftMMO.info("Registered entities");

	}

	private static void registerEntitiesForMaterials(final IForgeRegistry<EntityEntry> registry) {
		MinecraftMMO.debug("Registered entities for materials");
	}

	private static EntityEntry buildEntityEntryFromClass(final Class<? extends Entity> clazz, final boolean hasEgg, final int range, final int updateFrequency, final boolean sendVelocityUpdates) {
		return buildEntityEntryFromClassWithName(clazz, new ResourceLocation(ModReference.MOD_ID, ModUtil.getRegistryNameForClass(clazz, "Entity")), hasEgg, range, updateFrequency, sendVelocityUpdates);
	}

	private static EntityEntry buildEntityEntryFromClassWithName(final Class<? extends Entity> clazz, final ResourceLocation registryName, final boolean hasEgg, final int range, final int updateFrequency, final boolean sendVelocityUpdates) {
		EntityEntryBuilder<Entity> builder = EntityEntryBuilder.create();
		builder = builder.entity(clazz);
		builder = builder.id(registryName, entityId++);
		builder = builder.name(registryName.getResourcePath());
		builder = builder.tracker(range, updateFrequency, sendVelocityUpdates);

		if (hasEgg) {
			builder = builder.egg(0xFFFFFF, 0xAAAAAA);
		}

		return builder.build();
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onRegisterModelsEvent(final ModelRegistryEvent event) {

		registerTileEntitySpecialRenderers();
		MinecraftMMO.info("Registered tile entity special renderers");

		registerEntityRenderers();
		MinecraftMMO.info("Registered entity renderers");

		registerModelsForMaterials();

		MinecraftMMO.info("Registered models");

	}

	@SideOnly(Side.CLIENT)
	private static void registerTileEntitySpecialRenderers() {
		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntity.class, new TileEntityRenderer());
		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntity.class, new TileEntityRenderer());
	}

	@SideOnly(Side.CLIENT)
	private static void registerEntityRenderers() {
		// RenderingRegistry.registerEntityRenderingHandler(Entity.class, renderManager -> new EntityRenderer(renderManager));
	}

	@SideOnly(Side.CLIENT)
	private static void registerModelsForMaterials() {

		for (final ModMaterials material : ModMaterials.values()) {
			if (material.getProperties().hasOre()) {
				if (material.getOre() != null) {
					registerItemBlockModel(material.getOre());
				}
			}

			if (material.getProperties().hasBlock()) {
				if (material.getBlock() != null) {
					registerItemBlockModel(material.getBlock());
				}
			}

			if (material.getProperties().hasResource()) {
				if ((material.getResource() != null) && (material.getResouceLocationDomain(material.getResourceNameSuffix().toLowerCase(), ForgeRegistries.ITEMS).equals(ModReference.MOD_ID))) {
					registerItemModel(material.getResource());
				}
				if ((material.getResourcePiece() != null) && (material.getResouceLocationDomain(material.getResourcePieceNameSuffix().toLowerCase(), ForgeRegistries.ITEMS).equals(ModReference.MOD_ID))) {
					registerItemModel(material.getResourcePiece());
				}
			}

			if (material.getProperties().hasArmor()) {
				if (material.getHelmet() != null) {
					registerItemModel(material.getHelmet());
				}
				if (material.getChestplate() != null) {
					registerItemModel(material.getChestplate());
				}
				if (material.getLeggings() != null) {
					registerItemModel(material.getLeggings());
				}
				if (material.getBoots() != null) {
					registerItemModel(material.getBoots());
				}
			}

			if (material.getProperties().hasTools()) {
				if (material.getPickaxe() != null) {
					registerItemModel(material.getPickaxe());
				}
				if (material.getAxe() != null) {
					registerItemModel(material.getAxe());
				}
				if (material.getSword() != null) {
					registerItemModel(material.getSword());
				}
				if (material.getShovel() != null) {
					registerItemModel(material.getShovel());
				}
				if (material.getHoe() != null) {
					registerItemModel(material.getHoe());
				}
			}

		}
		MinecraftMMO.debug("Registered models for materials");
	}

	@SideOnly(Side.CLIENT)
	private static void registerItemModel(final Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "normal"));
	}

	@SideOnly(Side.CLIENT)
	private static void registerItemBlockModel(final Block block) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
	}
}