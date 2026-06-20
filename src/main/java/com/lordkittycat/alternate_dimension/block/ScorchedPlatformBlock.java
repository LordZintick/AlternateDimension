package com.lordkittycat.alternate_dimension.block;

import com.github.lordzintick.block.PlatformBlock;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class ScorchedPlatformBlock extends PlatformBlock {
	public ScorchedPlatformBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 2f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
	}
}