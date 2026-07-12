package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class ChargeBuildupProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		itemstack.setDamageValue(0);
		if (x - entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).prevPosX > 0.1 || x - entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).prevPosX < -0.1
				|| z - entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).prevPosZ > 0.1 || z - entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).prevPosZ < -0.1) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.wool.step")), SoundSource.PLAYERS, (float) 0.3,
							(float) (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).charge / 500));
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.wool.step")), SoundSource.PLAYERS, (float) 0.3, (float) (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).charge / 500),
							false);
				}
			}
			{
				AlternateDimensionModVariables.PlayerVariables _vars = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES);
				_vars.charge = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).charge + 1;
				_vars.markSyncDirty();
			}
		}
		if (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).charge == 500) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.note_block.chime")), SoundSource.PLAYERS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.note_block.chime")), SoundSource.PLAYERS, 1, 1, false);
				}
			}
		}
		{
			AlternateDimensionModVariables.PlayerVariables _vars = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES);
			_vars.prevPosX = x;
			_vars.prevPosZ = z;
			_vars.markSyncDirty();
		}
	}
}