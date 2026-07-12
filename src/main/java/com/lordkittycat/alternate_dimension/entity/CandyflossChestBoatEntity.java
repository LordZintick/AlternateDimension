package com.lordkittycat.alternate_dimension.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.entity.EntityType;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

public class CandyflossChestBoatEntity extends ChestBoat {
	public CandyflossChestBoatEntity(EntityType<CandyflossChestBoatEntity> type, Level world) {
		super(type, world, AlternateDimensionModItems.CANDYFLOSS_CHEST_BOAT);
	}
}