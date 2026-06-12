package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;

public class TransferEnergyProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		Direction iterator2 = Direction.NORTH;
		for (Direction directioniterator : Direction.values()) {
			PullEnergyFromProcedure.execute(world, x, y, z, directioniterator);
			if (!(directioniterator == Direction.DOWN)) {
				PushEnergyToProcedure.execute(world, x, y, z, Direction.DOWN);
			}
			if (!(directioniterator == Direction.UP)) {
				PushEnergyToProcedure.execute(world, x, y, z, Direction.UP);
			}
			if (!(directioniterator == Direction.NORTH)) {
				PushEnergyToProcedure.execute(world, x, y, z, Direction.NORTH);
			}
			if (!(directioniterator == Direction.SOUTH)) {
				PushEnergyToProcedure.execute(world, x, y, z, Direction.SOUTH);
			}
			if (!(directioniterator == Direction.WEST)) {
				PushEnergyToProcedure.execute(world, x, y, z, Direction.WEST);
			}
			if (!(directioniterator == Direction.EAST)) {
				PushEnergyToProcedure.execute(world, x, y, z, Direction.EAST);
			}
		}
	}
}