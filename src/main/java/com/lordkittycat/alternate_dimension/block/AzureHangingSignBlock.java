package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.CeilingHangingSignBlock;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModWoodTypes;

public class AzureHangingSignBlock extends CeilingHangingSignBlock {
	public AzureHangingSignBlock() {
		super(AlternateDimensionModWoodTypes.AZURE_HANGING_SIGN_WOOD_TYPE, BlockBehaviour.Properties.of().sound(SoundType.HANGING_SIGN).strength(1f).noCollission().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn());
	}
}