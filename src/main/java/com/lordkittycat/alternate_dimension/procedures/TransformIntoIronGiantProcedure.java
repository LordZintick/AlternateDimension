package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModEntities;
import com.lordkittycat.alternate_dimension.entity.CalcifiedZombieEntity;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class TransformIntoIronGiantProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (itemstack.getItem() == AlternateDimensionModItems.CORRUPTED_FLESH.get() && !(entity instanceof CalcifiedZombieEntity _datEntL2 && _datEntL2.getEntityData().get(CalcifiedZombieEntity.DATA_transforming))) {
			itemstack.shrink(1);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.zombie_villager.cure")), SoundSource.HOSTILE, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.zombie_villager.cure")), SoundSource.HOSTILE, 1, 1, false);
				}
			}
			if (entity instanceof CalcifiedZombieEntity _datEntSetL)
				_datEntSetL.getEntityData().set(CalcifiedZombieEntity.DATA_transforming, true);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.FALLING_OBSIDIAN_TEAR, x, y, z, 50, 1, 2, 1, 0);
			AlternateDimensionMod.queueServerWork(200, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.end_portal.spawn")), SoundSource.HOSTILE, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.end_portal.spawn")), SoundSource.HOSTILE, 1, 1, false);
					}
				}
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = AlternateDimensionModEntities.IRON_GIANT.get().spawn(_level, BlockPos.containing(x, y, z), EntitySpawnReason.MOB_SUMMONED);
					if (entityToSpawn != null) {
						entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
					}
				}
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.FALLING_OBSIDIAN_TEAR, x, y, z, 250, 10, 20, 10, 0);
				if (!entity.level().isClientSide())
					entity.discard();
			});
		}
	}
}