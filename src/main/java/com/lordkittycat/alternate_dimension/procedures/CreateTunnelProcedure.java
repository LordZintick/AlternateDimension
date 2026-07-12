package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class CreateTunnelProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Direction direction, ItemStack itemstack) {
		if (direction == null)
			return;
		double xoff = 0;
		double yoff = 0;
		double zoff = 0;
		BlockState current = Blocks.AIR.defaultBlockState();
		if (direction == Direction.UP || direction == Direction.DOWN) {
			return;
		}
		xoff = Math.abs((direction.getOpposite()).getStepX()) == 1 ? 0 : (direction.getOpposite()).getStepZ() * (-1);
		yoff = -1;
		zoff = Math.abs((direction.getOpposite()).getStepZ()) == 1 ? 0 : (direction.getOpposite()).getStepX() * (-1);
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.dragon_fireball.explode")), SoundSource.PLAYERS, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.dragon_fireball.explode")), SoundSource.PLAYERS, 1, 1, false);
			}
		}
		itemstack.shrink(1);
		for (int index13 = 0; index13 < 3; index13++) {
			for (int index14 = 0; index14 < 3; index14++) {
				for (int index15 = 0; index15 < 64; index15++) {
					current = (world.getBlockState(BlockPos.containing(x + xoff, y + yoff, z + zoff)));
					if (current.is(BlockTags.create(Identifier.parse("alternate_dimension:miner_items_whitelist")))) {
						{
							BlockPos _pos = BlockPos.containing(x + xoff, y + yoff, z + zoff);
							Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
							world.destroyBlock(_pos, false);
						}
					}
					if (Math.abs((direction.getOpposite()).getStepZ()) == 1) {
						zoff = zoff + (direction.getOpposite()).getStepZ();
					} else {
						xoff = xoff + (direction.getOpposite()).getStepX();
					}
				}
				yoff = yoff + 1;
				if (Math.abs((direction.getOpposite()).getStepZ()) == 1) {
					zoff = 0;
				} else {
					xoff = 0;
				}
			}
			yoff = -1;
			if (Math.abs((direction.getOpposite()).getStepX()) == 1) {
				zoff = zoff + (direction.getOpposite()).getStepX();
			} else {
				xoff = xoff + (direction.getOpposite()).getStepZ();
			}
		}
	}
}