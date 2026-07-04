/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;

@EventBusSubscriber
public class AlternateDimensionModDispenseBehaviors {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			DispenserBlock.registerBehavior(AlternateDimensionModItems.AZURE_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModBoatTypes.AZURE_BOAT_TYPE.getValue()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.AZURE_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModBoatTypes.AZURE_CHEST_BOAT_TYPE.getValue(), true));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.SCORCHED_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModBoatTypes.SCORCHED_BOAT_TYPE.getValue()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.SCORCHED_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModBoatTypes.SCORCHED_CHEST_BOAT_TYPE.getValue(), true));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.REDWOOD_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModBoatTypes.REDWOOD_BOAT_TYPE.getValue()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.REDWOOD_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModBoatTypes.REDWOOD_CHEST_BOAT_TYPE.getValue(), true));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.PINE_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModBoatTypes.PINE_BOAT_TYPE.getValue()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.PINE_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModBoatTypes.PINE_CHEST_BOAT_TYPE.getValue(), true));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.GREENWOOD_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModBoatTypes.GREENWOOD_BOAT_TYPE.getValue()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.GREENWOOD_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModBoatTypes.GREENWOOD_CHEST_BOAT_TYPE.getValue(), true));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.CANDYFLOSS_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModBoatTypes.CANDYFLOSS_BOAT_TYPE.getValue()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.CANDYFLOSS_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModBoatTypes.CANDYFLOSS_CHEST_BOAT_TYPE.getValue(), true));
		});
	}
}