package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class PlanetCoreBlock extends Block {
	public PlanetCoreBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.NETHER_BRICKS).strength(-1, 3600000).lightLevel(blockstate -> 15).postProcess((bs, br, bp) -> bp).emissiveRendering((bs, br, bp) -> true));
	}
}