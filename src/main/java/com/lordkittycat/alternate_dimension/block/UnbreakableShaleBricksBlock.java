package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class UnbreakableShaleBricksBlock extends Block {
	public UnbreakableShaleBricksBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.BASALT).strength(-1, 3600000));
	}
}