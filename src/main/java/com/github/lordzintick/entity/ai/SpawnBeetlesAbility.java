package com.github.lordzintick.entity.ai;

import com.lordkittycat.alternate_dimension.procedures.SpawnBeetlesProcedure;
import com.lordkittycat.alternate_dimension.procedures.SpawnRandomBeetleProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public class SpawnBeetlesAbility extends AbilityGoal {
    public SpawnBeetlesAbility(Mob entity) {
        super(entity);
    }

    @Override
    protected int getCooldown() {
        return 200;
    }

    @Override
    protected float getChance() {
        return 1f;
    }

    @Override
    protected void useAbility() {
        Level level = entity.level();
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.HOSTILE);
            SpawnRandomBeetleProcedure.execute(serverLevel, entity.getX(), entity.getY(), entity.getZ());
        }
    }
}
