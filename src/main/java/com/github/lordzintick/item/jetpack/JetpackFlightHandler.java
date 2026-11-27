package com.github.lordzintick.item.jetpack;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = AlternateDimensionMod.MODID)
public class JetpackFlightHandler {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.isSpectator() || player.getAbilities().flying || player.isInWater() || player.isFallFlying()) {
            return;
        }

        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        JetpackComponent jetpack = chest.get(AlternateDimensionModComponents.JETPACK);
        if (jetpack == null || !jetpack.engineOn()) return;

        JetpackFuelComponent fuelComp = chest.getOrDefault(AlternateDimensionModComponents.JETPACK_FUEL, JetpackFuelComponent.EMPTY);
        int storedFuel = fuelComp.fuel();
        int cost = jetpack.fuelUsage();
        if (!player.isCreative() && storedFuel < cost) {
            return;
        }

        boolean isClient = player.level().isClientSide();
        boolean isJumping = isClient ? player.isJumping() : (player.isJumping() || (!player.onGround() && !jetpack.hoverOn() && player.getDeltaMovement().y > -0.05));
        boolean isSneaking = player.isShiftKeyDown();
        boolean isHovering = jetpack.hoverOn() && !player.onGround();

        if (!isJumping && !isHovering) return;

        Vec3 motion = player.getDeltaMovement();
        double vy = motion.y;

        if (isJumping) {
            double accel = jetpack.hoverOn() ? jetpack.verticalSpeed() * 0.65 : jetpack.verticalSpeed();
            double maxClimb = jetpack.hoverOn() ? jetpack.verticalSpeed() * 3.0 : jetpack.verticalSpeed() * 4.5;
            vy = Math.min(maxClimb, vy + accel);
        } else {
            double targetVy = isSneaking ? -jetpack.hoverDescentSpeed() * 4.0 : -jetpack.hoverDescentSpeed();

            if (vy > targetVy) {
                vy = Math.max(targetVy, vy - 0.12);
            } else {
                vy = Math.min(targetVy, vy + 0.12);
            }
        }

        double vx = motion.x;
        double vz = motion.z;
        if (player.zza != 0.0f || player.xxa != 0.0f) {
            float yawRad = (float) Math.toRadians(player.getYRot());
            double forwardX = -Math.sin(yawRad) * player.zza + Math.cos(yawRad) * player.xxa;
            double forwardZ = Math.cos(yawRad) * player.zza + Math.sin(yawRad) * player.xxa;

            vx += forwardX * jetpack.horizontalSpeed();
            vz += forwardZ * jetpack.horizontalSpeed();
        }

        player.setDeltaMovement(vx, vy, vz);
        player.fallDistance = 0.0f;

        if (isClient) {
            float yawRad = (float) Math.toRadians(player.getYRot());
            double backX = player.getX() + Math.sin(yawRad) * 0.25;
            double backZ = player.getZ() - Math.cos(yawRad) * 0.25;

            player.level().addParticle(
                    ParticleTypes.FLAME,
                    backX, player.getY() + 0.9, backZ,
                    0.0, -0.15, 0.0
            );
            if (player.tickCount % 2 == 0) {
                player.level().addParticle(
                        ParticleTypes.SMOKE,
                        backX, player.getY() + 0.85, backZ,
                        0.0, -0.1, 0.0
                );
            }
        } else if (!player.isCreative() && !player.onGround()) {
            int fuelLeft = Math.max(0, storedFuel - cost);
            chest.set(AlternateDimensionModComponents.JETPACK_FUEL, new JetpackFuelComponent(fuelLeft));

            if (fuelLeft < jetpack.fuelUsage() * 200) {
                player.sendOverlayMessage(Component.translatable("tooltip.alternate_dimension.jetpack_fuel", fuelLeft, jetpack.fuelCapacity())
                        .withStyle(ChatFormatting.RED));
            }
        }
    }
}
