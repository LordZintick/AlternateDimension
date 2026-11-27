package com.github.lordzintick.item;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.modular.ModularItem;
import com.github.lordzintick.modular.parts.GearPartsComponent;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosSlotTypes;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;
import java.util.Optional;

public record PermanentCurioEffectsComponent(List<MobEffectInstance> effects, String curioSlot, float damageChance) {
    public static final Codec<PermanentCurioEffectsComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            MobEffectInstance.CODEC.listOf().fieldOf("effects").forGetter(PermanentCurioEffectsComponent::effects),
            Codec.STRING.fieldOf("slot").forGetter(PermanentCurioEffectsComponent::curioSlot),
            Codec.FLOAT.optionalFieldOf("damage_chance", 0f).forGetter(PermanentCurioEffectsComponent::damageChance)
    ).apply(instance, PermanentCurioEffectsComponent::new));

    public void apply(ItemStack stack, LivingEntity entity) {
        for (MobEffectInstance effect : effects) {
            MobEffectInstance newEffect = new MobEffectInstance(effect.getEffect(), 340, effect.getAmplifier(), true, false);
            MobEffectInstance existingEffect = entity.getEffect(effect.getEffect());
            if (!entity.hasEffect(effect.getEffect()) || (existingEffect != null && existingEffect.getDuration() < 240)) {
                entity.addEffect(newEffect, entity);
                if (entity.getRandom().nextFloat() <= damageChance) {
                    GearPartsComponent parts = stack.get(AlternateDimensionModComponents.GEAR_PARTS);
                    if (parts != null) {
                        parts.damagePartWithComponent(stack, 1, AlternateDimensionModComponents.PERMANENT_EFFECTS.get());
                    }
                }
            }
        }
    }
}
