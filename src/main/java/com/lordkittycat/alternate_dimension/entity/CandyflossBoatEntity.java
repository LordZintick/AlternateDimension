package com.lordkittycat.alternate_dimension.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.EntityType;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

public class CandyflossBoatEntity extends Boat {
	public CandyflossBoatEntity(EntityType<CandyflossBoatEntity> type, Level world) {
		super(type, world, AlternateDimensionModItems.CANDYFLOSS_BOAT);
	}
}