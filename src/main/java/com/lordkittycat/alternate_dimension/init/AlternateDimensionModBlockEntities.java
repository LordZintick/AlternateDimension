/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import com.lordkittycat.alternate_dimension.block.entity.*;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

@EventBusSubscriber
public class AlternateDimensionModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AlternateDimensionMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PortalBlockBlockEntity>> PORTAL_BLOCK = register("portal_block", AlternateDimensionModBlocks.PORTAL_BLOCK, PortalBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ShaleFurnaceBlockEntity>> SHALE_FURNACE = register("shale_furnace", AlternateDimensionModBlocks.SHALE_FURNACE, ShaleFurnaceBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MantleFurnaceBlockEntity>> MANTLE_FURNACE = register("mantle_furnace", AlternateDimensionModBlocks.MANTLE_FURNACE, MantleFurnaceBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<VeridiumFurnaceBlockEntity>> VERIDIUM_FURNACE = register("veridium_furnace", AlternateDimensionModBlocks.VERIDIUM_FURNACE, VeridiumFurnaceBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ElementalInscriberBlockEntity>> ELEMENTAL_INSCRIBER = register("elemental_inscriber", AlternateDimensionModBlocks.ELEMENTAL_INSCRIBER, ElementalInscriberBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ScorchedBoxBlockEntity>> SCORCHED_BOX = register("scorched_box", AlternateDimensionModBlocks.SCORCHED_BOX, ScorchedBoxBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PineBoxBlockEntity>> PINE_BOX = register("pine_box", AlternateDimensionModBlocks.PINE_BOX, PineBoxBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RedwoodBoxBlockEntity>> REDWOOD_BOX = register("redwood_box", AlternateDimensionModBlocks.REDWOOD_BOX, RedwoodBoxBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AzureBoxBlockEntity>> AZURE_BOX = register("azure_box", AlternateDimensionModBlocks.AZURE_BOX, AzureBoxBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GreenwoodBoxBlockEntity>> GREENWOOD_BOX = register("greenwood_box", AlternateDimensionModBlocks.GREENWOOD_BOX, GreenwoodBoxBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CandyflossBoxBlockEntity>> CANDYFLOSS_BOX = register("candyfloss_box", AlternateDimensionModBlocks.CANDYFLOSS_BOX, CandyflossBoxBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FuserBlockEntity>> FUSER = register("fuser", AlternateDimensionModBlocks.FUSER, FuserBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ManaProviderBlockEntity>> MANA_PROVIDER = register("mana_provider", AlternateDimensionModBlocks.MANA_PROVIDER, ManaProviderBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ManaTankBlockEntity>> MANA_TANK = register("mana_tank", AlternateDimensionModBlocks.MANA_TANK, ManaTankBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ManaSynthesizerBlockEntity>> MANA_SYNTHESIZER = register("mana_synthesizer", AlternateDimensionModBlocks.MANA_SYNTHESIZER, ManaSynthesizerBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ItemElevatorBlockEntity>> ITEM_ELEVATOR = register("item_elevator", AlternateDimensionModBlocks.ITEM_ELEVATOR, ItemElevatorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PhotolithographicMicrofabricatorBlockEntity>> PHOTOLITHOGRAPHIC_MICROFABRICATOR = register("photolithographic_microfabricator",
			AlternateDimensionModBlocks.PHOTOLITHOGRAPHIC_MICROFABRICATOR, PhotolithographicMicrofabricatorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CompressorBlockEntity>> COMPRESSOR = register("compressor", AlternateDimensionModBlocks.COMPRESSOR, CompressorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CollectorBlockEntity>> COLLECTOR = register("collector", AlternateDimensionModBlocks.COLLECTOR, CollectorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AluminumCrateBlockEntity>> ALUMINUM_CRATE = register("aluminum_crate", AlternateDimensionModBlocks.ALUMINUM_CRATE, AluminumCrateBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ManaMystifierBlockEntity>> MANA_MYSTIFIER = register("mana_mystifier", AlternateDimensionModBlocks.MANA_MYSTIFIER, ManaMystifierBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GravestoneBlockEntity>> GRAVESTONE = register("gravestone", AlternateDimensionModBlocks.GRAVESTONE, GravestoneBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> new BlockEntityType(supplier, block.get()));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.Item.BLOCK, PORTAL_BLOCK.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, SHALE_FURNACE.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, MANTLE_FURNACE.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, VERIDIUM_FURNACE.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, ELEMENTAL_INSCRIBER.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, SCORCHED_BOX.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, PINE_BOX.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, REDWOOD_BOX.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, AZURE_BOX.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, GREENWOOD_BOX.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, CANDYFLOSS_BOX.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, FUSER.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Energy.BLOCK, FUSER.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.Item.BLOCK, MANA_PROVIDER.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, MANA_TANK.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Energy.BLOCK, MANA_TANK.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.Item.BLOCK, MANA_SYNTHESIZER.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Energy.BLOCK, MANA_SYNTHESIZER.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.Item.BLOCK, ITEM_ELEVATOR.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, PHOTOLITHOGRAPHIC_MICROFABRICATOR.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Energy.BLOCK, PHOTOLITHOGRAPHIC_MICROFABRICATOR.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.Item.BLOCK, COMPRESSOR.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Energy.BLOCK, COMPRESSOR.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.Item.BLOCK, COLLECTOR.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, ALUMINUM_CRATE.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, MANA_MYSTIFIER.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Energy.BLOCK, MANA_MYSTIFIER.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.Item.BLOCK, GRAVESTONE.get(), WorldlyContainerWrapper::new);
	}
}