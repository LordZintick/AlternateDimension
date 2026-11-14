package com.lordkittycat.alternate_dimension.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.entity.EntityType;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

public class GreenwoodChestBoatEntity extends ChestBoat {
	public GreenwoodChestBoatEntity(EntityType<GreenwoodChestBoatEntity> type, Level world) {
		super(type, world, AlternateDimensionModItems.GREENWOOD_CHEST_BOAT);
	}
}