package com.github.lordzintick.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public abstract class AbilityGoal extends Goal {
    protected final Mob entity;
    private int remainingCooldown = 0;

    protected AbilityGoal(Mob entity) {
        this.entity = entity;
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    protected abstract int getCooldown();
    protected abstract float getChance();
    protected abstract void useAbility();

    @Override
    public boolean canUse() {
        LivingEntity target = entity.getTarget();
        if (target == null || !target.isAlive()) return false;
        return remainingCooldown <= 0 && entity.getRandom().nextFloat() <= getChance();
    }

    @Override
    public boolean canContinueToUse() {
        return remainingCooldown <= 0;
    }

    @Override
    public void start() {
        useAbility();
        remainingCooldown = getCooldown();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (remainingCooldown > 0) remainingCooldown--;
    }
}
