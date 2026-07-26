package com.github.lordzintick.entity.ai;

import com.lordkittycat.alternate_dimension.entity.CalcifiedZombieEntity;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModEntities;
import com.lordkittycat.alternate_dimension.procedures.SpawnCalcifiedZombieProcedure;
import com.lordkittycat.alternate_dimension.procedures.SpawnRandomBeetleProcedure;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public class SpawnCalcifiedZombiesAbility extends AbilityGoal {
    public SpawnCalcifiedZombiesAbility(Mob entity) {
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
            for (int i = 0; i < serverLevel.getRandom().nextInt(2, 5); i++) {
                SpawnCalcifiedZombieProcedure.execute(serverLevel, entity.getX(), entity.getY() + 10, entity.getZ());
            }
        }
    }
}
