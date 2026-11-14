package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class PurpleMushroomBlockBlock extends Block {
	public PurpleMushroomBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.SHROOMLIGHT).strength(1f, 10f).lightLevel(blockstate -> 12).postProcess((bs, br, bp) -> bp).emissiveRendering((bs, br, bp) -> true));
	}
}