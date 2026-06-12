package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.SoundType;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModWoodTypes;

public class RedwoodSignBlock extends StandingSignBlock {
	public RedwoodSignBlock() {
		super(AlternateDimensionModWoodTypes.REDWOOD_SIGN_WOOD_TYPE, BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f).noCollission().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn());
	}
}