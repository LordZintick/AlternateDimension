package com.github.lordzintick.entity.ai;

import com.lordkittycat.alternate_dimension.entity.SporeSpreaderEntity;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public class SporeCloudAbility extends AbilityGoal {
    public SporeCloudAbility(Mob entity) {
        super(entity);
    }

    @Override
    protected int getCooldown() {
        return 100;
    }

    @Override
    protected float getChance() {
        return 0.5f;
    }

    @Override
    protected void useAbility() {
        Level level = entity.level();
        if (level instanceof ServerLevel serverLevel) {
            AreaEffectCloud cloud = new AreaEffectCloud(serverLevel, entity.getX(), entity.getY(), entity.getZ());
            cloud.addEffect(
                    new MobEffectInstance(
                            AlternateDimensionModMobEffects.SHROOMY_INFECTION,
                            200, 0
                    )
            );
            cloud.setDuration(200);
            cloud.setRadius(0.5f);
            cloud.setRadiusPerTick(0.05f);
            cloud.setRadiusOnUse(0);
            cloud.setDurationOnUse(0);
            cloud.setOwner(entity);
            serverLevel.addFreshEntity(cloud);

            if (entity instanceof SporeSpreaderEntity sporeSpreader) {
                sporeSpreader.getEntityData().set(SporeSpreaderEntity.ANIM, 1000);
                sporeSpreader.getEntityData().set(SporeSpreaderEntity.ANIM, 2);
            }
        }
    }
}
