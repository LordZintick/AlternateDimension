package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModEntities;

public class BeetleSwordAbilityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity) {
		if (sourceentity == null)
			return;
		if (Mth.nextInt(RandomSource.create(), 1, 3) == 2) {
			if ((world instanceof ServerLevel _level1 ? AlternateDimensionModEntities.FRIENDLY_BEETLE.get().spawn(_level1, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED) : null) instanceof TamableAnimal _toTame
					&& sourceentity instanceof Player _owner)
				_toTame.tame(_owner);
		}
	}
}