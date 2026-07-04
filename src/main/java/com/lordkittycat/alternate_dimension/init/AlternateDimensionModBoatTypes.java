/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.function.Supplier;

public class AlternateDimensionModBoatTypes {
	public static final EnumProxy<Boat.Type> AZURE_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "alternate_dimension:azure_boat", AlternateDimensionModItems.AZURE_BOAT, (Supplier<Item>) () -> Items.AIR,
			(Supplier<Item>) () -> Items.STICK, false);
	public static final EnumProxy<Boat.Type> AZURE_CHEST_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "alternate_dimension:azure_chest_boat", (Supplier<Item>) () -> Items.AIR,
			AlternateDimensionModItems.AZURE_CHEST_BOAT, (Supplier<Item>) () -> Items.STICK, false);
	public static final EnumProxy<Boat.Type> SCORCHED_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "alternate_dimension:scorched_boat", AlternateDimensionModItems.SCORCHED_BOAT,
			(Supplier<Item>) () -> Items.AIR, (Supplier<Item>) () -> Items.STICK, false);
	public static final EnumProxy<Boat.Type> SCORCHED_CHEST_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "alternate_dimension:scorched_chest_boat", (Supplier<Item>) () -> Items.AIR,
			AlternateDimensionModItems.SCORCHED_CHEST_BOAT, (Supplier<Item>) () -> Items.STICK, false);
	public static final EnumProxy<Boat.Type> REDWOOD_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "alternate_dimension:redwood_boat", AlternateDimensionModItems.REDWOOD_BOAT,
			(Supplier<Item>) () -> Items.AIR, (Supplier<Item>) () -> Items.STICK, false);
	public static final EnumProxy<Boat.Type> REDWOOD_CHEST_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "alternate_dimension:redwood_chest_boat", (Supplier<Item>) () -> Items.AIR,
			AlternateDimensionModItems.REDWOOD_CHEST_BOAT, (Supplier<Item>) () -> Items.STICK, false);
	public static final EnumProxy<Boat.Type> PINE_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "alternate_dimension:pine_boat", AlternateDimensionModItems.PINE_BOAT, (Supplier<Item>) () -> Items.AIR,
			(Supplier<Item>) () -> Items.STICK, false);
	public static final EnumProxy<Boat.Type> PINE_CHEST_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "alternate_dimension:pine_chest_boat", (Supplier<Item>) () -> Items.AIR,
			AlternateDimensionModItems.PINE_CHEST_BOAT, (Supplier<Item>) () -> Items.STICK, false);
	public static final EnumProxy<Boat.Type> GREENWOOD_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "alternate_dimension:greenwood_boat", AlternateDimensionModItems.GREENWOOD_BOAT,
			(Supplier<Item>) () -> Items.AIR, (Supplier<Item>) () -> Items.STICK, false);
	public static final EnumProxy<Boat.Type> GREENWOOD_CHEST_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "alternate_dimension:greenwood_chest_boat", (Supplier<Item>) () -> Items.AIR,
			AlternateDimensionModItems.GREENWOOD_CHEST_BOAT, (Supplier<Item>) () -> Items.STICK, false);
	public static final EnumProxy<Boat.Type> CANDYFLOSS_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "alternate_dimension:candyfloss_boat", AlternateDimensionModItems.CANDYFLOSS_BOAT,
			(Supplier<Item>) () -> Items.AIR, (Supplier<Item>) () -> Items.STICK, false);
	public static final EnumProxy<Boat.Type> CANDYFLOSS_CHEST_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "alternate_dimension:candyfloss_chest_boat", (Supplier<Item>) () -> Items.AIR,
			AlternateDimensionModItems.CANDYFLOSS_CHEST_BOAT, (Supplier<Item>) () -> Items.STICK, false);
}