package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class BlindingLightMobEffect extends MobEffect {
	public BlindingLightMobEffect() {
		super(MobEffectCategory.HARMFUL, -256);
	}
}