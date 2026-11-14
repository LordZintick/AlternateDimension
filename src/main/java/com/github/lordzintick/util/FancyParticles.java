package com.github.lordzintick.util;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

public final class FancyParticles {
    public static void spawnParticleRing(ServerLevel level, ParticleOptions particle, Vec3 pos, float radius, int delay) {
        float diameter = radius * 2;
        double circumference = diameter * Math.PI;
        if (delay == 0) {
            for (int i = 0; i < 360; i += (int) (360 / (circumference * 6))) {
                level.sendParticles(particle, pos.x + Math.cos(i), pos.y, pos.z + Math.sin(i), 1, 0, 0, 0, 0);
            }
        } else {
            AlternateDimensionMod.scheduleRepeatedTask(
                    i -> level.sendParticles(particle, pos.x + Math.cos(i), pos.y, pos.z + Math.sin(i), 1, 0, 0, 0, 0),
                    delay, (int) (360 / (circumference * 6)));
        }
    }
    public static void spawnParticleRing(ServerLevel level, ParticleOptions particle, Vec3 pos, float radius) {
        spawnParticleRing(level, particle, pos, radius, 0);
    }
}
