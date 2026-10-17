package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.ProblemReporter;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.Identifier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class AluminumStorageModuleUpgradeBoxProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
		itemstack.shrink(1);
		{
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockState _bs = AlternateDimensionModBlocks.ALUMINUM_CRATE.get().defaultBlockState();
			BlockEntity _be = world.getBlockEntity(_bp);
			CompoundTag _bnbt = null;
			if (_be != null) {
				_bnbt = _be.saveWithFullMetadata(world.registryAccess());
				_be.setRemoved();
			}
			world.setBlock(_bp, _bs, 3);
			if (_bnbt != null) {
				_be = world.getBlockEntity(_bp);
				if (_be != null) {
					try {
						_be.loadWithComponents(TagValueInput.create(ProblemReporter.DISCARDING, world.registryAccess(), _bnbt));
					} catch (Exception ignored) {
					}
				}
			}
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.smithing_table.use")), SoundSource.BLOCKS, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.smithing_table.use")), SoundSource.BLOCKS, 1, 1, false);
			}
		}
	}
}