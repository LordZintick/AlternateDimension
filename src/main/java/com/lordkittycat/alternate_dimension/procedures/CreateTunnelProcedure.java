package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
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
		if (!(direction == Direction.NORTH) && !(direction == Direction.EAST) && !(direction == Direction.SOUTH) && !(direction == Direction.WEST)) {
			return;
		}
		xoff = (direction.getOpposite()).getStepX() == 1 ? 0 : -1;
		yoff = -1;
		zoff = (direction.getOpposite()).getStepZ() == 1 ? 0 : -1;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.dragon_fireball.explode")), SoundSource.PLAYERS, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.dragon_fireball.explode")), SoundSource.PLAYERS, 1, 1, false);
			}
		}
		itemstack.shrink(1);
		for (int index0 = 0; index0 < 3; index0++) {
			for (int index1 = 0; index1 < 3; index1++) {
				for (int index2 = 0; index2 < 64; index2++) {
					current = (world.getBlockState(BlockPos.containing(x + xoff, y + yoff, z + zoff)));
					if (current.is(BlockTags.create(ResourceLocation.parse("alternate_dimension:miner_items_whitelist")))) {
						{
							BlockPos _pos = BlockPos.containing(x + xoff, y + yoff, z + zoff);
							Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
							world.destroyBlock(_pos, false);
						}
					}
					if ((direction.getOpposite()).getStepZ() == 1) {
						zoff = zoff + 1;
					} else {
						xoff = xoff + 1;
					}
				}
				yoff = yoff + 1;
				if ((direction.getOpposite()).getStepZ() == 1) {
					zoff = -1;
				} else {
					xoff = -1;
				}
			}
			yoff = -1;
			if ((direction.getOpposite()).getStepX() == 1) {
				zoff = zoff + 1;
			} else {
				xoff = xoff + 1;
			}
		}
	}
}