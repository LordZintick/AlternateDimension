package com.github.lordzintick.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlatformBlock extends Block {
    private static final VoxelShape shape = Block.box(0, 12, 0, 16, 16, 16);

    public PlatformBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state) {
        return true;
    }

    @Override
    protected int getLightDampening(BlockState state) {
        return 0;
    }

    @Override
    protected VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return shape;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityContext) {
            Entity entity = entityContext.getEntity();
            if (entity != null) {
                boolean above = entity.getY() >= pos.getY() + 1;
                boolean falling = entity.getDeltaMovement().y <= 0.005;

                if (above && falling && !entity.isShiftKeyDown()) return shape;
            }
        }
        return Shapes.empty();
    }
}
