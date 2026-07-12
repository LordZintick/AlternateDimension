package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class PlantHempPlantProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Direction direction, ItemStack itemstack) {
		if (direction == null)
			return;
		if (direction == Direction.UP && blockstate.is(BlockTags.create(Identifier.parse("minecraft:dirt")))) {
			itemstack.shrink(1);
			world.setBlock(BlockPos.containing(x, y + 1, z), AlternateDimensionModBlocks.HEMP_PLANT.get().defaultBlockState(), 3);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("item.crop.plant")), SoundSource.PLAYERS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("item.crop.plant")), SoundSource.PLAYERS, 1, 1, false);
				}
			}
		}
	}
}