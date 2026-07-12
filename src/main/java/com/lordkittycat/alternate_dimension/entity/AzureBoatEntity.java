package com.lordkittycat.alternate_dimension.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.EntityType;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

public class AzureBoatEntity extends Boat {
	public AzureBoatEntity(EntityType<AzureBoatEntity> type, Level world) {
		super(type, world, AlternateDimensionModItems.AZURE_BOAT);
	}
}