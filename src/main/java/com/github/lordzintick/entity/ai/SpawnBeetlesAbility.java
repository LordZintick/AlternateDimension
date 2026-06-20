package com.github.lordzintick.entity.ai;

import com.lordkittycat.alternate_dimension.procedures.SpawnBeetlesProcedure;
import com.lordkittycat.alternate_dimension.procedures.SpawnRandomBeetleProcedure;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public class SpawnBeetlesAbility extends AbilityGoal {
    public SpawnBeetlesAbility(Mob entity) {
        super(entity);
    }

    @Override
    protected int getCooldown() {
        return 100;
    }

    @Override
    protected float getChance() {
        return 0.1f;
    }

    @Override
    protected void useAbility() {
        Level level = entity.level();
        SpawnRandomBeetleProcedure.execute(level, entity.getX(), entity.getY(), entity.getZ());
    }
}
