package com.github.lordzintick.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.Map;

public record HitEffectsComponent(List<HitEffect> effects) {
    public static final Codec<HitEffectsComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            HitEffect.CODEC.listOf().fieldOf("effects").forGetter(HitEffectsComponent::effects)
    ).apply(instance, HitEffectsComponent::new));

    public void apply(LivingEntity target, LivingEntity source) {
        effects.forEach(effect -> {
            if (source.getRandom().nextFloat() <= effect.chance) {
                effect.effects.forEach(instance -> target.addEffect(new MobEffectInstance(instance.getEffect(), instance.getDuration(), instance.getAmplifier()), source));
            }
        });
    }

    public record HitEffect(List<MobEffectInstance> effects, float chance) {
        public static final Codec<HitEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                MobEffectInstance.CODEC.listOf().fieldOf("effects").forGetter(HitEffect::effects),
                Codec.FLOAT.fieldOf("chance").forGetter(HitEffect::chance)
        ).apply(instance, HitEffect::new));
    }
}
