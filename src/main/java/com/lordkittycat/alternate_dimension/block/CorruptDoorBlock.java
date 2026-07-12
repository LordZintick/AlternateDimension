package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.DoorBlock;

public class CorruptDoorBlock extends DoorBlock {
	public CorruptDoorBlock(BlockBehaviour.Properties properties) {
		super(BlockSetType.STONE, properties.strength(3f).requiresCorrectToolForDrops().noOcclusion().pushReaction(PushReaction.DESTROY).isRedstoneConductor((bs, br, bp) -> false));
	}
}