package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class ConfigureDeathPosCardProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).deathCount <= 0) {
			return;
		}
		{
			final String _tagName = "posX";
			final double _tagValue = (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).lastDeathPos.x());
			CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
		}
		{
			final String _tagName = "posY";
			final double _tagValue = (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).lastDeathPos.y());
			CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
		}
		{
			final String _tagName = "posZ";
			final double _tagValue = (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).lastDeathPos.z());
			CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
		}
		{
			final String _tagName = "number";
			final double _tagValue = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).deathCount;
			CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
		}
	}
}