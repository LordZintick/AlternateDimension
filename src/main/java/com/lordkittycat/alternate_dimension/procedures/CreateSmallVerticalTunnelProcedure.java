package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class CreateSmallVerticalTunnelProcedure {
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
		yoff = 0;
		for (int index237 = 0; index237 < 15; index237++) {
			if ((world.getBlockState(BlockPos.containing(x, y + yoff, z))).is(BlockTags.create(Identifier.parse("alternate_dimension:miner_items_whitelist")))) {
				{
					BlockPos _pos = BlockPos.containing(x, y + yoff, z);
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
	}
}