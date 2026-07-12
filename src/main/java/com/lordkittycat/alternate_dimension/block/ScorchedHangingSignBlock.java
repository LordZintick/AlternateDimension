package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.CeilingHangingSignBlock;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModWoodTypes;

public class ScorchedHangingSignBlock extends CeilingHangingSignBlock {
	public ScorchedHangingSignBlock(BlockBehaviour.Properties properties) {
		super(AlternateDimensionModWoodTypes.SCORCHED_HANGING_SIGN_WOOD_TYPE, properties.sound(SoundType.HANGING_SIGN).strength(1f).noCollision().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn());
	}
}