package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.SoundType;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModWoodTypes;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class CandyflossWallSignBlock extends WallSignBlock {
	public CandyflossWallSignBlock(BlockBehaviour.Properties properties) {
		super(AlternateDimensionModWoodTypes.CANDYFLOSS_SIGN_WOOD_TYPE, properties.sound(SoundType.WOOD).strength(1f).noCollision().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn()
				.overrideLootTable(AlternateDimensionModBlocks.CANDYFLOSS_SIGN.get().getLootTable()).overrideDescription(AlternateDimensionModBlocks.CANDYFLOSS_SIGN.get().getDescriptionId()));
	}
}