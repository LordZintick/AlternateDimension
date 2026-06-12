/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import java.util.Map;

import com.lordkittycat.alternate_dimension.world.inventory.*;
import com.lordkittycat.alternate_dimension.network.MenuStateUpdateMessage;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class AlternateDimensionModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, AlternateDimensionMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<CircuitMakerGUIMenu>> CIRCUIT_MAKER_GUI = REGISTRY.register("circuit_maker_gui", () -> IMenuTypeExtension.create(CircuitMakerGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<IngotPresserGUIMenu>> INGOT_PRESSER_GUI = REGISTRY.register("ingot_presser_gui", () -> IMenuTypeExtension.create(IngotPresserGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<GeneratorGUIMenu>> GENERATOR_GUI = REGISTRY.register("generator_gui", () -> IMenuTypeExtension.create(GeneratorGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BiomeSelectorMenu>> BIOME_SELECTOR = REGISTRY.register("biome_selector", () -> IMenuTypeExtension.create(BiomeSelectorMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ImbunementGUIMenu>> IMBUNEMENT_GUI = REGISTRY.register("imbunement_gui", () -> IMenuTypeExtension.create(ImbunementGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<IncineratorGUIMenu>> INCINERATOR_GUI = REGISTRY.register("incinerator_gui", () -> IMenuTypeExtension.create(IncineratorGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CorruptorGUIMenu>> CORRUPTOR_GUI = REGISTRY.register("corruptor_gui", () -> IMenuTypeExtension.create(CorruptorGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ElectricSmelterGUIMenu>> ELECTRIC_SMELTER_GUI = REGISTRY.register("electric_smelter_gui", () -> IMenuTypeExtension.create(ElectricSmelterGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<NuclearReactorGUIMenu>> NUCLEAR_REACTOR_GUI = REGISTRY.register("nuclear_reactor_gui", () -> IMenuTypeExtension.create(NuclearReactorGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BackpackGUIMenu>> BACKPACK_GUI = REGISTRY.register("backpack_gui", () -> IMenuTypeExtension.create(BackpackGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<AlloySmelterGUIMenu>> ALLOY_SMELTER_GUI = REGISTRY.register("alloy_smelter_gui", () -> IMenuTypeExtension.create(AlloySmelterGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CheesyBackpackGUIMenu>> CHEESY_BACKPACK_GUI = REGISTRY.register("cheesy_backpack_gui", () -> IMenuTypeExtension.create(CheesyBackpackGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<EnchantedBackpackGUIMenu>> ENCHANTED_BACKPACK_GUI = REGISTRY.register("enchanted_backpack_gui", () -> IMenuTypeExtension.create(EnchantedBackpackGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CrystalChestGUIMenu>> CRYSTAL_CHEST_GUI = REGISTRY.register("crystal_chest_gui", () -> IMenuTypeExtension.create(CrystalChestGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<IntroScreenMenu>> INTRO_SCREEN = REGISTRY.register("intro_screen", () -> IMenuTypeExtension.create(IntroScreenMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				PacketDistributor.sendToPlayer(serverPlayer, new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof AlternateDimensionModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				PacketDistributor.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}