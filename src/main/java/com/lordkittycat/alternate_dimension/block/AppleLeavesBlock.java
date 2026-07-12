package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ColorParticleOption;

public class AppleLeavesBlock extends UntintedParticleLeavesBlock {
	public AppleLeavesBlock(BlockBehaviour.Properties properties) {
		super(0.01f, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, -10396928),
				properties.sound(SoundType.GRASS).strength(0.5f, 0f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false).isSuffocating((bs, br, bp) -> false).isViewBlocking((bs, br, bp) -> false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return true;
	}

	@Override
	public int getLightDampening(BlockState state) {
		return 0;
	}
}