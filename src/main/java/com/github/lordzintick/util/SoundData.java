package com.github.lordzintick.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public record SoundData(Holder<SoundEvent> sound, float volume, float pitch) {
    public static final Codec<SoundData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            SoundEvent.CODEC.fieldOf("sound").forGetter(SoundData::sound),
            Codec.FLOAT.optionalFieldOf("volume", 1.0F).forGetter(SoundData::volume),
            Codec.FLOAT.optionalFieldOf("pitch", 1.0F).forGetter(SoundData::pitch)
    ).apply(instance, SoundData::new));

    public void play(ServerLevel level, BlockPos pos, SoundSource source) {
        level.playSound(null, pos, sound.value(), source, volume, pitch);
    }
}
