package com.lordkittycat.alternate_dimension.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.entity.EntityType;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

public class SolarChestBoatEntity extends ChestBoat {
	public SolarChestBoatEntity(EntityType<SolarChestBoatEntity> type, Level world) {
		super(type, world, AlternateDimensionModItems.SOLAR_CHEST_BOAT);
	}
}