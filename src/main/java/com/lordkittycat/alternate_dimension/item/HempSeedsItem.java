package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

import com.lordkittycat.alternate_dimension.procedures.PlantHempPlantProcedure;

public class HempSeedsItem extends Item {
	public HempSeedsItem() {
		super(new Item.Properties());
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		PlantHempPlantProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getLevel().getBlockState(context.getClickedPos()), context.getClickedFace(),
				context.getItemInHand());
		return InteractionResult.SUCCESS;
	}
}