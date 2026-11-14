package com.github.lordzintick.magic;

import com.github.lordzintick.magic.loader.Spell;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.ServerTickRateManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.hurtingprojectile.DragonFireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public final class SpellUtil {

    public static void castFireball(Spell.SpellParameterProvider paramProvider, Float speed, Boolean big) {
        Player player = paramProvider.player();
        Level world = player.level();
        if (!world.isClientSide()) {
            if (!big) {
                world.playSound(null, player.blockPosition(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1f, 1f);
                SmallFireball fireball = new SmallFireball(world, player.getX(), player.getEyeY(), player.getZ(), new Vec3(player.getLookAngle().x * speed, player.getLookAngle().y * speed, player.getLookAngle().z * speed));
                fireball.setOwner(player);
                world. addFreshEntity(fireball);
            } else {
                world.playSound(null, player.blockPosition(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1f, 0.7f);
                LargeFireball fireball = new LargeFireball(world, player, new Vec3(player.getLookAngle().x * speed, player.getLookAngle().y * speed, player.getLookAngle().z * speed), 2);
                fireball.setPos(player.getEyePosition());
                world. addFreshEntity(fireball);
            }
        }
    }


    public static void castDragonFireball(Spell.SpellParameterProvider paramProvider, Float speed) {
        Player player = paramProvider.player();
        Level world = player.level();
        if (!world.isClientSide()) {
            world.playSound(null, player.blockPosition(), SoundEvents.ENDER_DRAGON_SHOOT, SoundSource.PLAYERS, 1f, 1f);
            DragonFireball fireball = new DragonFireball(world, player, new Vec3(player.getLookAngle().x * speed, player.getLookAngle().y * speed, player.getLookAngle().z * speed));
            fireball.setPos(player.getEyePosition());
            world. addFreshEntity(fireball);
        }
    }


    public static void shootEntities(Spell.SpellParameterProvider paramProvider, EntityType<?> entityType, Integer amount, Float speed, Float delay, Float inconsistency) {
        Player player = paramProvider.player();
        Level world = player.level();
        if (!world.isClientSide()) {
            if (amount > 10) {
                world.playSound(null, player.blockPosition(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.PLAYERS, 1f, 1f);
            }
            if (delay > 0) {
                new Thread(() -> {
                    for (int i = 0; i < amount; i++) {
                        world.playSound(null, player.blockPosition(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1f, 1f);
                        Entity entity = entityType.create(world, EntitySpawnReason.MOB_SUMMONED);
                        assert entity != null;
                        entity.setPos(player.getEyePosition());
                        Random random = new Random();
                        entity.setDeltaMovement(new Vec3(
                                        player.getLookAngle().x + random.nextInt(-10, 10) * 0.1 * inconsistency,
                                        player.getLookAngle().y + random.nextInt(-10, 10) * 0.1 * inconsistency,
                                        player.getLookAngle().z + random.nextInt(-10, 10) * 0.1 * inconsistency
                                ).scale(speed)
                        );
                        entity.setYBodyRot(player.getYRot());
                        entity.setXRot(player.getXRot());
                        entity.setYHeadRot(player.getYHeadRot());
                        if (entity instanceof TamableAnimal tameable) {
                            tameable.setOwner(player);
                        }
                        if (entity instanceof ThrowableProjectile thrown) {
                            thrown.setOwner(player);
                        }
                        if (entity instanceof Projectile proj) {
                            proj.setOwner(player);
                        }
                        if (entity instanceof AbstractArrow arrow) {
                            arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }
                        world.addFreshEntity(entity);
                        try {
                            Thread.sleep((long) (delay * 1000));
                        } catch (InterruptedException e) {
                            AlternateDimensionMod.LOGGER.info("An error occurred when shooting entities: {}", e.getMessage());
                        }
                    }
                }).start();
            } else {
                for (int i = 0; i < amount; i++) {
                    world.playSound(null, player.blockPosition(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1f, 1f);
                    Entity entity = entityType.create(world, EntitySpawnReason.MOB_SUMMONED);
                    assert entity != null;
                    entity.setPos(player.getEyePosition());
                    Random random = new Random();
                    entity.setDeltaMovement(new Vec3(
                                    player.getLookAngle().x + random.nextInt(-10, 10) * 0.1 * inconsistency,
                                    player.getLookAngle().y + random.nextInt(-10, 10) * 0.1 * inconsistency,
                                    player.getLookAngle().z + random.nextInt(-10, 10) * 0.1 * inconsistency
                            ).scale(speed)
                    );
                    entity.setYBodyRot(player.getYRot());
                    entity.setXRot(player.getXRot());
                    entity.setYHeadRot(player.getYHeadRot());
                    if (entity instanceof TamableAnimal tameable) {
                        tameable.setOwner(player);
                    }
                    if (entity instanceof ThrowableProjectile thrown) {
                        thrown.setOwner(player);
                    }
                    if (entity instanceof Projectile proj) {
                        proj.setOwner(player);
                    }
                    if (entity instanceof AbstractArrow arrow) {
                        arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }
                    world.addFreshEntity(entity);
                }
            }
        }
    }


    public static void shootItems(Spell.SpellParameterProvider paramProvider, Item item, Integer amount, Float speed, Float delay, Float inconsistency) {
        Player player = paramProvider.player();
        Level world = player.level();
        if (!world.isClientSide()) {
            world.playSound(null, player.blockPosition(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.PLAYERS, 1f, 1f);
            Random random = new Random();
            if (delay > 0) {
                new Thread(() -> {
                    for (int i = 0; i < amount; i++) {
                        world.playSound(null, player.blockPosition(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1f, 1f);
                        ItemEntity entity = new ItemEntity(world, player.getEyePosition().x, player.getEyeY(), player.getEyePosition().z, item.getDefaultInstance());
                        entity.setPos(player.getEyePosition());
                        entity.setDeltaMovement(new Vec3(
                                        player.getLookAngle().x + random.nextInt(-10, 10) * 0.1 * inconsistency,
                                        player.getLookAngle().y + random.nextInt(-10, 10) * 0.1 * inconsistency,
                                        player.getLookAngle().z + random.nextInt(-10, 10) * 0.1 * inconsistency
                                ).scale(speed)
                        );
                        entity.setYBodyRot(player.getYRot());
                        entity.setXRot(player.getXRot());
                        entity.setYHeadRot(player.getYHeadRot());
                        entity.setPickUpDelay(100);
                        world.addFreshEntity(entity);
                        try {
                            Thread.sleep((long) (delay * 1000));
                        } catch (InterruptedException e) {
                            AlternateDimensionMod.LOGGER.info("An error occurred when shooting items: {}", e.getMessage());
                        }
                    }
                }).start();
            } else {
                for (int i = 0; i < amount; i++) {
                    world.playSound(null, player.blockPosition(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1f, 1f);
                    ItemEntity entity = new ItemEntity(world, player.getEyePosition().x, player.getEyeY(), player.getEyePosition().z, item.getDefaultInstance());
                    entity.setPos(player.getEyePosition());
                    entity.setDeltaMovement(new Vec3(
                                    player.getLookAngle().x + random.nextInt(-10, 10) * 0.1 * inconsistency,
                                    player.getLookAngle().y + random.nextInt(-10, 10) * 0.1 * inconsistency,
                                    player.getLookAngle().z + random.nextInt(-10, 10) * 0.1 * inconsistency
                            ).scale(speed)
                    );
                    entity.setYBodyRot(player.getYRot());
                    entity.setXRot(player.getXRot());
                    entity.setYHeadRot(player.getYHeadRot());
                    entity.setPickUpDelay(100);
                    world.addFreshEntity(entity);
                }
            }
        }
    }

    public static void evokerFangs(Spell.SpellParameterProvider paramProvider, Integer size, Boolean circle) {
        Player player = paramProvider.player();
        Level world = player.level();
        if (!world.isClientSide()) {
            world.playSound(null, player.blockPosition(), SoundEvents.EVOKER_PREPARE_ATTACK, SoundSource.PLAYERS, 1f, 1f);
            if (circle) {
                for (int dist = 0; dist < size; dist++) {
                    for (int angle = 0; angle < 360; angle += 360 / (10 + dist * 5)) {
                        Vec3 pos = player.position().add(new Vec3(Math.cos(Math.toRadians(angle)), 0, Math.sin(Math.toRadians(angle))).scale(dist));
                        EvokerFangs fangs = new EvokerFangs(world, pos.x(), pos.y(), pos.z(), player.getYHeadRot(), 2, player);
                        world.addFreshEntity(fangs);
                    }
                }
            } else {
                for (int dist = 0; dist < size; dist++) {
                    Vec3 projPos = player.position().add(player.getLookAngle().normalize().scale(dist));
                    EvokerFangs fangs = new EvokerFangs(world, projPos.x(), projPos.y(), projPos.z(), player.getYHeadRot(), 2, player);
                    world.addFreshEntity(fangs);
                }
            }
        }
    }


    public static void teleport(Spell.SpellParameterProvider parameterProvider, Float speed) {
        Player player = parameterProvider.player();
        Level world = player.level();
        if (!world.isClientSide()) {
            world.playSound(null, player.blockPosition(), SoundEvents.ENDER_PEARL_THROW, SoundSource.PLAYERS, 1f, 1f);
            ThrownEnderpearl enderPearl = new ThrownEnderpearl(world, player, Items.ENDER_PEARL.getDefaultInstance());
            enderPearl.setPos(player.getEyePosition());
            enderPearl.setDeltaMovement(player.getLookAngle().normalize().scale(speed));
            world. addFreshEntity(enderPearl);
        }
    }


    public static void teleDash(Spell.SpellParameterProvider parameterProvider, Float dist) {
        Player player = parameterProvider.player();
        Level world = player.level();
        if (!world.isClientSide()) {
            world.playSound(null, player.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1f, 1f);
            Vec3 newPos = player.position().add(player.getLookAngle().scale(dist));
            player.setPos(newPos);
        }
    }


    public static void execute(Spell.SpellParameterProvider parameterProvider, String command) {
        Player player = parameterProvider.player();
        Level world = player.level();
        if (!world.isClientSide()) {
            world.getServer().getCommands().performPrefixedCommand(world.getServer().createCommandSourceStack(), command.replace("~ ~ ~", player.getX() + " " + player.getY() + " " + player.getZ()));
        }
    }


    public static void playSound(Spell.SpellParameterProvider parameterProvider, String soundID, Float volume, Float pitch) {
        Player player = parameterProvider.player();
        Level world = player.level();
        if (!world.isClientSide()) {
            Optional<Holder.Reference<SoundEvent>> soundRef = BuiltInRegistries.SOUND_EVENT.get(Identifier.parse(soundID.replace(";",":")));
            if (soundRef.isEmpty()) return;
            world.playSound(null, player.blockPosition(), soundRef.get().value(), SoundSource.PLAYERS, volume, pitch);
        }
    }


    public static void tickRate(Spell.SpellParameterProvider parameterProvider, Integer tickrate, Float duration) {
        Player player = parameterProvider.player();
        Level world = player.level();
        if (!world.isClientSide() && world.getServer() != null) {
            ServerTickRateManager tickManager = world.getServer().tickRateManager();
            if (tickrate > 0) {
                tickManager.setTickRate(tickrate);
            } else {
                tickManager.setFrozen(true);
            }
            Timer expiretimer = new Timer();
            expiretimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (tickrate > 0) {
                        tickManager.setTickRate(20);
                    } else {
                        tickManager.setFrozen(false);
                    }
                }
            }, (long) (duration * 1000));
        }
    }


    public static void setScale(Spell.SpellParameterProvider parameterProvider, Float scale, Float duration) {
        Player player = parameterProvider.player();
        Level world = player.level();
        AttributeInstance scaleAttr = player.getAttribute(Attributes.SCALE);
        if (!world.isClientSide() && scaleAttr != null && scaleAttr.getBaseValue() == 1) {
            scaleAttr.setBaseValue(scale);
            Timer expiretimer = new Timer();
            expiretimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    scaleAttr.setBaseValue(1);
                }
            }, (long) (duration * 1000));
        }
    }


    public static void addEffect(Spell.SpellParameterProvider parameterProvider, String effectID, Integer duration, Integer amplifier) {
        Player player = parameterProvider.player();
        Level world = player.level();
        if (!world.isClientSide()) {
            world.playSound(null, player.blockPosition(), SoundEvents.BREWING_STAND_BREW, SoundSource.PLAYERS, 1f, 1f);
            Optional<Holder.Reference<MobEffect>> effect = BuiltInRegistries.MOB_EFFECT.get(Identifier.parse(effectID.replace(";",":")));
            effect.ifPresent(statusEffectReference -> player.addEffect(new MobEffectInstance(statusEffectReference, duration * 20, amplifier)));
        }
    }


    public static void spawnEntity(Spell.SpellParameterProvider parameterProvider, String entityID, Float x, Float y, Float z, Float vx, Float vy, Float vz) {
        Player player = parameterProvider.player();
        Level world = player.level();
        if (!world.isClientSide()) {
            Optional<Holder.Reference<EntityType<?>>> entityRef = BuiltInRegistries.ENTITY_TYPE.get(Identifier.parse(entityID.replace(";",":")));
            if (entityRef.isEmpty()) return;
            Entity entity = entityRef.get().value().create(world, EntitySpawnReason.MOB_SUMMONED);
            assert entity != null;
            entity.setPos(x, y, z);
            entity.setDeltaMovement(vx, vy, vz);
            world. addFreshEntity(entity);
        }
    }


    public static void setBlock(Spell.SpellParameterProvider parameterProvider, String blockID, Integer x, Integer y, Integer z) {
        Player player = parameterProvider.player();
        Level world = player.level();
        if (!world.isClientSide()) {
            Optional<Holder.Reference<Block>> blockRef = BuiltInRegistries.BLOCK.get(Identifier.parse(blockID.replace(";",":")));
            if (blockRef.isEmpty()) return;
            Block block = blockRef.get().value();
            world.setBlock(new BlockPos(x,y,z), block.defaultBlockState(), 3);
        }
    }


    public static void fillBlocks(Spell.SpellParameterProvider parameterProvider, String blockID, Integer x1, Integer y1, Integer z1, Integer x2, Integer y2, Integer z2) {
        Player player = parameterProvider.player();
        Level world = player.level();
        if (!world.isClientSide()) {
            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    for (int z = z1; z < z2; z++) {
                        Optional<Holder.Reference<Block>> blockRef = BuiltInRegistries.BLOCK.get(Identifier.parse(blockID.replace(";",":")));
                        if (blockRef.isEmpty()) return;
                        Block block = blockRef.get().value();
                        world.setBlock(new BlockPos(x,y,z), block.defaultBlockState(), 3);
                    }
                }
            }
        }
    }
}
