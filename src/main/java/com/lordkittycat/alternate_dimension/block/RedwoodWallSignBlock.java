package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.SoundType;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModWoodTypes;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class RedwoodWallSignBlock extends WallSignBlock {
	public RedwoodWallSignBlock() {
		super(AlternateDimensionModWoodTypes.REDWOOD_SIGN_WOOD_TYPE,
				BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f).noCollission().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn().dropsLike(AlternateDimensionModBlocks.REDWOOD_SIGN.get()));
	}
}