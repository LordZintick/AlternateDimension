package com.github.lordzintick.item;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record PermanentEffectsComponent(List<MobEffectInstance> effects, EquipmentSlotGroup slot, float damageChance) {
    public static final Codec<PermanentEffectsComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            MobEffectInstance.CODEC.listOf().fieldOf("effects").forGetter(PermanentEffectsComponent::effects),
            EquipmentSlotGroup.CODEC.fieldOf("slot").forGetter(PermanentEffectsComponent::slot),
            Codec.FLOAT.optionalFieldOf("damage_chance", 0f).forGetter(PermanentEffectsComponent::damageChance)
    ).apply(instance, PermanentEffectsComponent::new));

    public void apply(ItemStack stack, LivingEntity entity) {
        EquipmentSlot equippedSlot = entity.getEquipmentSlotForItem(stack);
        if (slot.test(equippedSlot) && entity.hasItemInSlot(equippedSlot)) {
            List<MobEffectInstance> effectsToAdd = effects.stream()
                    .filter(effect -> {
                        MobEffectInstance instance = entity.getEffect(effect.getEffect());
                        return instance == null || instance.getDuration() < effect.getDuration();
                    }).toList();

            for (MobEffectInstance effect : effectsToAdd) {
                entity.addEffect(effect);
                if (entity.getRandom().nextFloat() <= damageChance) {
                    stack.hurtAndBreak(1, entity, equippedSlot);
                }
            }
        }
    }
}
