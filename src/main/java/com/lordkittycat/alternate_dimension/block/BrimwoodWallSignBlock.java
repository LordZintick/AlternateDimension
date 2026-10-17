package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.SoundType;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModWoodTypes;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class BrimwoodWallSignBlock extends WallSignBlock {
	public BrimwoodWallSignBlock(BlockBehaviour.Properties properties) {
		super(AlternateDimensionModWoodTypes.BRIMWOOD_SIGN_WOOD_TYPE, properties.sound(SoundType.WOOD).strength(1f).noCollision().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn()
				.overrideLootTable(AlternateDimensionModBlocks.BRIMWOOD_SIGN.get().getLootTable()).overrideDescription(AlternateDimensionModBlocks.BRIMWOOD_SIGN.get().getDescriptionId()));
	}
}