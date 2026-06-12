package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class EvilstoneBlockBlock extends Block {
	public EvilstoneBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(4f, 10f).requiresCorrectToolForDrops().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true));
	}
}