/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import com.lordkittycat.alternate_dimension.client.gui.*;

@EventBusSubscriber(Dist.CLIENT)
public class AlternateDimensionModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(AlternateDimensionModMenus.CIRCUIT_MAKER_GUI.get(), CircuitMakerGUIScreen::new);
		event.register(AlternateDimensionModMenus.INGOT_PRESSER_GUI.get(), IngotPresserGUIScreen::new);
		event.register(AlternateDimensionModMenus.GENERATOR_GUI.get(), GeneratorGUIScreen::new);
		event.register(AlternateDimensionModMenus.BIOME_SELECTOR.get(), BiomeSelectorScreen::new);
		event.register(AlternateDimensionModMenus.IMBUNEMENT_GUI.get(), ImbunementGUIScreen::new);
		event.register(AlternateDimensionModMenus.INCINERATOR_GUI.get(), IncineratorGUIScreen::new);
		event.register(AlternateDimensionModMenus.CORRUPTOR_GUI.get(), CorruptorGUIScreen::new);
		event.register(AlternateDimensionModMenus.ELECTRIC_SMELTER_GUI.get(), ElectricSmelterGUIScreen::new);
		event.register(AlternateDimensionModMenus.NUCLEAR_REACTOR_GUI.get(), NuclearReactorGUIScreen::new);
		event.register(AlternateDimensionModMenus.BACKPACK_GUI.get(), BackpackGUIScreen::new);
		event.register(AlternateDimensionModMenus.ALLOY_SMELTER_GUI.get(), AlloySmelterGUIScreen::new);
		event.register(AlternateDimensionModMenus.CHEESY_BACKPACK_GUI.get(), CheesyBackpackGUIScreen::new);
		event.register(AlternateDimensionModMenus.ENCHANTED_BACKPACK_GUI.get(), EnchantedBackpackGUIScreen::new);
		event.register(AlternateDimensionModMenus.CRYSTAL_CHEST_GUI.get(), CrystalChestGUIScreen::new);
		event.register(AlternateDimensionModMenus.INTRO_SCREEN.get(), IntroScreenScreen::new);
		event.register(AlternateDimensionModMenus.PURIFIER_GUI.get(), PurifierGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}