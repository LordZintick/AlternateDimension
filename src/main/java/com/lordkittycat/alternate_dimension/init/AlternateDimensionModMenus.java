/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

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
	public static final DeferredHolder<MenuType<?>, MenuType<IncineratorGUIMenu>> INCINERATOR_GUI = REGISTRY.register("incinerator_gui", () -> IMenuTypeExtension.create(IncineratorGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BackpackGUIMenu>> BACKPACK_GUI = REGISTRY.register("backpack_gui", () -> IMenuTypeExtension.create(BackpackGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CheesyBackpackGUIMenu>> CHEESY_BACKPACK_GUI = REGISTRY.register("cheesy_backpack_gui", () -> IMenuTypeExtension.create(CheesyBackpackGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<EnchantedBackpackGUIMenu>> ENCHANTED_BACKPACK_GUI = REGISTRY.register("enchanted_backpack_gui", () -> IMenuTypeExtension.create(EnchantedBackpackGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ElementalInscriberGUIMenu>> ELEMENTAL_INSCRIBER_GUI = REGISTRY.register("elemental_inscriber_gui", () -> IMenuTypeExtension.create(ElementalInscriberGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<HintBookGUIMenu>> HINT_BOOK_GUI = REGISTRY.register("hint_book_gui", () -> IMenuTypeExtension.create(HintBookGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<WoodenBoxGUIMenu>> WOODEN_BOX_GUI = REGISTRY.register("wooden_box_gui", () -> IMenuTypeExtension.create(WoodenBoxGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<MetalCrateGUIMenu>> METAL_CRATE_GUI = REGISTRY.register("metal_crate_gui", () -> IMenuTypeExtension.create(MetalCrateGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<EssencePouchGUIMenu>> ESSENCE_POUCH_GUI = REGISTRY.register("essence_pouch_gui", () -> IMenuTypeExtension.create(EssencePouchGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<HuntersPouchGUIMenu>> HUNTERS_POUCH_GUI = REGISTRY.register("hunters_pouch_gui", () -> IMenuTypeExtension.create(HuntersPouchGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<FruitPouchGUIMenu>> FRUIT_POUCH_GUI = REGISTRY.register("fruit_pouch_gui", () -> IMenuTypeExtension.create(FruitPouchGUIMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				PacketDistributor.sendToPlayer(serverPlayer, new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide()) {
				if (Minecraft.getInstance().screen instanceof AlternateDimensionModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				ClientPacketDistributor.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
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