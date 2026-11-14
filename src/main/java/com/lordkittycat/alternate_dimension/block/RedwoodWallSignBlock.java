package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.SoundType;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModWoodTypes;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class RedwoodWallSignBlock extends WallSignBlock {
	public RedwoodWallSignBlock(BlockBehaviour.Properties properties) {
		super(AlternateDimensionModWoodTypes.REDWOOD_SIGN_WOOD_TYPE, properties.sound(SoundType.WOOD).strength(1f).noCollision().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn()
				.overrideLootTable(AlternateDimensionModBlocks.REDWOOD_SIGN.get().getLootTable()).overrideDescription(AlternateDimensionModBlocks.REDWOOD_SIGN.get().getDescriptionId()));
	}
}