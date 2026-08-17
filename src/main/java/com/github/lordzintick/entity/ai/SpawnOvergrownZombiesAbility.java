package com.github.lordzintick.entity.ai;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModEntities;
import com.lordkittycat.alternate_dimension.procedures.SpawnOvergrownZombieProcedure;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public class SpawnOvergrownZombiesAbility extends AbilityGoal {
    public SpawnOvergrownZombiesAbility(Mob entity) {
        super(entity);
    }

    @Override
    protected int getCooldown() {
        return 400;
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
            for (int i = 0; i < serverLevel.getRandom().nextInt(1, 4); i++) {
                SpawnOvergrownZombieProcedure.execute(serverLevel, entity.getX(), entity.getY() + 1, entity.getZ());
            }
        }
    }
}
