package com.github.lordzintick.item;

import com.github.lordzintick.util.SoundData;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record HitEffectsComponent(List<HitEffect> effects) {
    public static final Codec<HitEffectsComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            HitEffect.CODEC.listOf().fieldOf("effects").forGetter(HitEffectsComponent::effects)
    ).apply(instance, HitEffectsComponent::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, HitEffectsComponent> STREAM_CODEC =
            ByteBufCodecs.fromCodecWithRegistries(CODEC);

    public void apply(LivingEntity target, LivingEntity source) {
        effects.forEach(effect -> {
            if (source.getRandom().nextFloat() <= effect.chance) {
                effect.sound.ifPresent(sound -> {
                    if (source.level() instanceof ServerLevel level) {
                        sound.play(level, source.blockPosition(), SoundSource.PLAYERS);
                    }
                });
                effect.effects.forEach(instance -> target.addEffect(new MobEffectInstance(instance.getEffect(), instance.getDuration(), instance.getAmplifier()), source));
            }
        });
    }

    public HitEffectsComponent mergeWith(HitEffectsComponent other) {
        List<HitEffect> merged = new ArrayList<>(this.effects.size() + other.effects.size());
        merged.addAll(this.effects);
        merged.addAll(other.effects);
        return new HitEffectsComponent(List.copyOf(merged));
    }

    public record HitEffect(List<MobEffectInstance> effects, Optional<SoundData> sound, float chance) {
        public static final Codec<HitEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                MobEffectInstance.CODEC.listOf().fieldOf("effects").forGetter(HitEffect::effects),
                SoundData.CODEC.optionalFieldOf("sound").forGetter(HitEffect::sound),
                Codec.FLOAT.fieldOf("chance").forGetter(HitEffect::chance)
        ).apply(instance, HitEffect::new));
    }
}
