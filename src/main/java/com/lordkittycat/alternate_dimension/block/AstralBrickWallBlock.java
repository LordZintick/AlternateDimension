package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class AstralBrickWallBlock extends WallBlock {
	public AstralBrickWallBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.TRIAL_SPAWNER).strength(2f, 10f).lightLevel(blockstate -> 6).requiresCorrectToolForDrops().postProcess((bs, br, bp) -> bp).emissiveRendering((bs, br, bp) -> true).forceSolidOn());
	}
}