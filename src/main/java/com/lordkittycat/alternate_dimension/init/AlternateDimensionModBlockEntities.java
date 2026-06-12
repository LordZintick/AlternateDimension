/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
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
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CircuitMakerBlockEntity>> CIRCUIT_MAKER = register("circuit_maker", AlternateDimensionModBlocks.CIRCUIT_MAKER, CircuitMakerBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<IngotPresserBlockEntity>> INGOT_PRESSER = register("ingot_presser", AlternateDimensionModBlocks.INGOT_PRESSER, IngotPresserBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GeneratorBlockEntity>> GENERATOR = register("generator", AlternateDimensionModBlocks.GENERATOR, GeneratorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL = register("solar_panel", AlternateDimensionModBlocks.SOLAR_PANEL, SolarPanelBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BasicEnergyCableBlockEntity>> BASIC_ENERGY_CABLE = register("basic_energy_cable", AlternateDimensionModBlocks.BASIC_ENERGY_CABLE, BasicEnergyCableBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ImbunementTableBlockEntity>> IMBUNEMENT_TABLE = register("imbunement_table", AlternateDimensionModBlocks.IMBUNEMENT_TABLE, ImbunementTableBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CorruptorBlockEntity>> CORRUPTOR = register("corruptor", AlternateDimensionModBlocks.CORRUPTOR, CorruptorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ElectricSmelterBlockEntity>> ELECTRIC_SMELTER = register("electric_smelter", AlternateDimensionModBlocks.ELECTRIC_SMELTER, ElectricSmelterBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NuclearReactorBlockEntity>> NUCLEAR_REACTOR = register("nuclear_reactor", AlternateDimensionModBlocks.NUCLEAR_REACTOR, NuclearReactorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AlloySmelterBlockEntity>> ALLOY_SMELTER = register("alloy_smelter", AlternateDimensionModBlocks.ALLOY_SMELTER, AlloySmelterBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PortalBlockBlockEntity>> PORTAL_BLOCK = register("portal_block", AlternateDimensionModBlocks.PORTAL_BLOCK, PortalBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CrystalChestBlockEntity>> CRYSTAL_CHEST = register("crystal_chest", AlternateDimensionModBlocks.CRYSTAL_CHEST, CrystalChestBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CIRCUIT_MAKER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, CIRCUIT_MAKER.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, INGOT_PRESSER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, INGOT_PRESSER.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, GENERATOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, GENERATOR.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SOLAR_PANEL.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, SOLAR_PANEL.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BASIC_ENERGY_CABLE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BASIC_ENERGY_CABLE.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, IMBUNEMENT_TABLE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CORRUPTOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, CORRUPTOR.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ELECTRIC_SMELTER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ELECTRIC_SMELTER.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NUCLEAR_REACTOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, NUCLEAR_REACTOR.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ALLOY_SMELTER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ALLOY_SMELTER.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PORTAL_BLOCK.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CRYSTAL_CHEST.get(), SidedInvWrapper::new);
	}
}