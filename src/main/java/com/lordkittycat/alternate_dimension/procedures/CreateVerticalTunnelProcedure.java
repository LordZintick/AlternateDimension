package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class CreateVerticalTunnelProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Direction direction, ItemStack itemstack) {
		if (direction == null)
			return;
		double xoff = 0;
		double yoff = 0;
		double zoff = 0;
		if (!((direction.getOpposite()) == Direction.UP) && !((direction.getOpposite()) == Direction.DOWN)) {
			return;
		}
		itemstack.shrink(1);
		xoff = -1;
		yoff = 0;
		zoff = -1;
		for (int index302 = 0; index302 < 3; index302++) {
			for (int index303 = 0; index303 < 3; index303++) {
				for (int index304 = 0; index304 < 64; index304++) {
					if ((world.getBlockState(BlockPos.containing(x + xoff, y + yoff, z + zoff))).is(BlockTags.create(Identifier.parse("alternate_dimension:miner_items_whitelist")))) {
						{
							BlockPos _pos = BlockPos.containing(x + xoff, y + yoff, z + zoff);
							Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
							world.destroyBlock(_pos, false);
						}
					}
					if ((direction.getOpposite()) == Direction.UP) {
						yoff = yoff + 1;
					} else {
						yoff = yoff - 1;
					}
				}
				yoff = 0;
				zoff = zoff + 1;
			}
			zoff = -1;
			xoff = xoff + 1;
		}
	}
}