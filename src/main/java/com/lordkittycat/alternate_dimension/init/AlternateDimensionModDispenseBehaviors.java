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
			DispenserBlock.registerBehavior(AlternateDimensionModItems.AZURE_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.AZURE_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.AZURE_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.AZURE_CHEST_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.SCORCHED_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.SCORCHED_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.SCORCHED_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.SCORCHED_CHEST_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.REDWOOD_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.REDWOOD_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.REDWOOD_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.REDWOOD_CHEST_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.PINE_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.PINE_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.PINE_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.PINE_CHEST_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.GREENWOOD_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.GREENWOOD_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.GREENWOOD_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.GREENWOOD_CHEST_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.CANDYFLOSS_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.CANDYFLOSS_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.CANDYFLOSS_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.CANDYFLOSS_CHEST_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.WILLOW_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.WILLOW_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.WILLOW_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.WILLOW_CHEST_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.BRIMWOOD_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.BRIMWOOD_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.BRIMWOOD_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.BRIMWOOD_CHEST_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.SOLAR_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.SOLAR_BOAT.get()));
			DispenserBlock.registerBehavior(AlternateDimensionModItems.SOLAR_CHEST_BOAT.get(), new BoatDispenseItemBehavior(AlternateDimensionModEntities.SOLAR_CHEST_BOAT.get()));
		});
	}
}