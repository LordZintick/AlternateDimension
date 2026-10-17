package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.SoundType;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModWoodTypes;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class WillowWallHangingSignBlock extends WallHangingSignBlock {
	public WillowWallHangingSignBlock(BlockBehaviour.Properties properties) {
		super(AlternateDimensionModWoodTypes.WILLOW_HANGING_SIGN_WOOD_TYPE, properties.sound(SoundType.HANGING_SIGN).strength(1f).noCollision().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn()
				.overrideLootTable(AlternateDimensionModBlocks.WILLOW_HANGING_SIGN.get().getLootTable()).overrideDescription(AlternateDimensionModBlocks.WILLOW_HANGING_SIGN.get().getDescriptionId()));
	}
}