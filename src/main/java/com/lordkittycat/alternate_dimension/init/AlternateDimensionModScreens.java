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
		event.register(AlternateDimensionModMenus.INCINERATOR_GUI.get(), IncineratorGUIScreen::new);
		event.register(AlternateDimensionModMenus.BACKPACK_GUI.get(), BackpackGUIScreen::new);
		event.register(AlternateDimensionModMenus.CHEESY_BACKPACK_GUI.get(), CheesyBackpackGUIScreen::new);
		event.register(AlternateDimensionModMenus.ENCHANTED_BACKPACK_GUI.get(), EnchantedBackpackGUIScreen::new);
		event.register(AlternateDimensionModMenus.ELEMENTAL_INSCRIBER_GUI.get(), ElementalInscriberGUIScreen::new);
		event.register(AlternateDimensionModMenus.WOODEN_BOX_GUI.get(), WoodenBoxGUIScreen::new);
		event.register(AlternateDimensionModMenus.METAL_CRATE_GUI.get(), MetalCrateGUIScreen::new);
		event.register(AlternateDimensionModMenus.ESSENCE_POUCH_GUI.get(), EssencePouchGUIScreen::new);
		event.register(AlternateDimensionModMenus.HUNTERS_POUCH_GUI.get(), HuntersPouchGUIScreen::new);
		event.register(AlternateDimensionModMenus.FRUIT_POUCH_GUI.get(), FruitPouchGUIScreen::new);
		event.register(AlternateDimensionModMenus.EXTRACTOR_GUI.get(), ExtractorGUIScreen::new);
		event.register(AlternateDimensionModMenus.COLLECTOR_GUI.get(), CollectorGUIScreen::new);
		event.register(AlternateDimensionModMenus.COMBO_POUCH_GUI.get(), ComboPouchGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}