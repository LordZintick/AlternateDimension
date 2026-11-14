package com.lordkittycat.alternate_dimension.mixin;

import com.github.lordzintick.magic.loader.Spell;
import com.github.lordzintick.magic.loader.SpellLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomModelData;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.ref.Reference;
import java.util.Objects;
import java.util.Optional;

@Mixin(Player.class)
public abstract class PlayerEntityMixinMixin {
    @Shadow
    public abstract void sendOverlayMessage(Component message);

    @Inject(at= @At("TAIL"), method = "tick")
    public void tick(CallbackInfo ci) {
        Player clientplayer = (Player) (Object) this;
        if (clientplayer instanceof ServerPlayer player) {
            AttributeInstance mana = Objects.requireNonNull(player.getAttribute(Attributes.TEMPT_RANGE));
            AttributeInstance maxMana = Objects.requireNonNull(player.getAttribute(Attributes.FOLLOW_RANGE));
            AttributeInstance NEMInstance = Objects.requireNonNull(player.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE));
            if (NEMInstance.getValue() < 0) {
                NEMInstance.setBaseValue(0);
            }
            boolean showingNEM = Objects.requireNonNull(player.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE)).getValue() > 0;
            if (maxMana.getValue() < 100) {
                maxMana.setBaseValue(100d);
            }
            if (mana.getValue() < maxMana.getValue()) {
                ServerLevel world = player.level();
                mana.setBaseValue(mana.getBaseValue() + 1);
            }

            ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
            Item item = stack.getItem();
            if (item == Items.STICK) {
                if (stack.get(DataComponents.CUSTOM_NAME) != null && stack.get(DataComponents.CUSTOM_MODEL_DATA) != null) {
                    if (Objects.requireNonNull(Objects.requireNonNull(stack.get(DataComponents.CUSTOM_NAME)).getString()).contains("Wand")) {
                        if (Objects.requireNonNull(stack.get(DataComponents.CUSTOM_MODEL_DATA)).floats().contains(750001f)) {
                            CustomModelData customModelData = Objects.requireNonNull(stack.get(DataComponents.CUSTOM_MODEL_DATA));
                            Identifier spellID = Identifier.parse(customModelData.strings().getFirst());
                            Spell spell = SpellLoader.SPELLS.getByID(spellID);
                            if (spell != null) {
                                if (spell.particleEffect != null) {
                                    if (BuiltInRegistries.PARTICLE_TYPE.containsKey(Identifier.parse(spell.particleEffect))) {
                                        Optional<Holder.Reference<ParticleType<?>>> particleRef = BuiltInRegistries.PARTICLE_TYPE.get(Identifier.parse(spell.particleEffect));
                                        if (particleRef.isEmpty()) return;
                                        ParticleType<?> particle = particleRef.get().value();
                                        (player).level().addParticle((ParticleOptions) () -> particle,
                                                clientplayer.getX() + clientplayer.getRandom().nextIntBetweenInclusive(-5, 5) / 10,
                                                clientplayer.getY() + clientplayer.getRandom().nextIntBetweenInclusive(0,20) / 10,
                                                clientplayer.getZ() + clientplayer.getRandom().nextIntBetweenInclusive(-5, 5) / 10,
                                                clientplayer.getRandom().nextIntBetweenInclusive(-10,10) / 100,
                                                clientplayer.getRandom().nextIntBetweenInclusive(0,20) / 100,
                                                clientplayer.getRandom().nextIntBetweenInclusive(-10,10) / 100
                                        );
                                    }
                                }
                            }
                            if (showingNEM) {
                                sendOverlayMessage(Component.literal("Not enough mana!").copy().withStyle(ChatFormatting.RED));
                                NEMInstance.setBaseValue(NEMInstance.getValue() - 0.1);
                            } else {
                                sendOverlayMessage(Component.literal("Mana: " + (int) player.getAttributeValue(Attributes.TEMPT_RANGE) + "/" + (int) player.getAttributeValue(Attributes.FOLLOW_RANGE)).copy().withStyle(ChatFormatting.BLUE));
                            }
                        }
                    }
                }
            } else if (mana.getValue() < maxMana.getValue()) {
                if (showingNEM) {
                    sendOverlayMessage(Component.literal("Not enough mana!").copy().withStyle(ChatFormatting.RED));
                    NEMInstance.setBaseValue(NEMInstance.getValue() - 0.1);
                } else {
                    sendOverlayMessage(Component.literal("Mana: " + (int) player.getAttributeValue(Attributes.TEMPT_RANGE) + "/" + (int) player.getAttributeValue(Attributes.FOLLOW_RANGE)).copy().withStyle(ChatFormatting.BLUE));
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "createAttributes", cancellable = true)
    private static void createAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.setReturnValue(LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 1.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.1F)
                .add(Attributes.ATTACK_SPEED)
                .add(Attributes.LUCK)
                .add(Attributes.BLOCK_INTERACTION_RANGE)
                .add(Attributes.BLOCK_BREAK_SPEED)
                .add(Attributes.SUBMERGED_MINING_SPEED)
                .add(Attributes.SNEAKING_SPEED)
                .add(Attributes.MINING_EFFICIENCY)
                .add(Attributes.SWEEPING_DAMAGE_RATIO)
                .add(Attributes.WAYPOINT_TRANSMIT_RANGE, 6.0E7F)
                .add(Attributes.WAYPOINT_RECEIVE_RANGE, 6.0E7F)
                .add(NeoForgeMod.CREATIVE_FLIGHT)
                .add(Attributes.FOLLOW_RANGE, 100d)
                .add(Attributes.TEMPT_RANGE, 100d)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0d));
    }
}