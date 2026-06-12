package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModMobEffects;

public class RandomGoodProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (Mth.nextInt(RandomSource.create(), 0, 100) == 0) {
			if (world instanceof ServerLevel _level)
				FallingBlockEntity.fall(_level, BlockPos.containing(x, y, z), Blocks.LAPIS_BLOCK.defaultBlockState());
		}
		if (Mth.nextInt(RandomSource.create(), 0, 125) == 0) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 1));
		}
		if (Mth.nextInt(RandomSource.create(), 0, 125) == 0) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(AlternateDimensionModMobEffects.ACROBATIC_EFFECT, 300, 1));
		}
		if (Mth.nextInt(RandomSource.create(), 0, 125) == 0) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 300, 2));
		}
		if (Mth.nextInt(RandomSource.create(), 0, 125) == 0) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.LUCK, 300, 0));
		}
		if (Mth.nextInt(RandomSource.create(), 0, 225) == 0) {
			if (entity instanceof Player _player)
				_player.giveExperiencePoints(500);
		}
		if (Mth.nextInt(RandomSource.create(), 0, 150) == 0) {
			if (entity instanceof Player _player)
				_player.giveExperiencePoints(50);
		}
	}
}