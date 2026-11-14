package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.LiquidBlock;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModFluids;

public class LiquidMyceliumBlock extends LiquidBlock {
	public LiquidMyceliumBlock(BlockBehaviour.Properties properties) {
		super(AlternateDimensionModFluids.LIQUID_MYCELIUM.get(), properties.mapColor(MapColor.WATER).strength(100f).lightLevel(state -> 5).noCollision().noLootTable().liquid().pushReaction(PushReaction.DESTROY).sound(SoundType.EMPTY).replaceable());
	}
}