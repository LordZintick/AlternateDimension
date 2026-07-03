package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class CorruptLanternBlock extends Block {
	public CorruptLanternBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.GLASS).strength(2f, 1f).lightLevel(blockstate -> 15).requiresCorrectToolForDrops().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true));
	}
}