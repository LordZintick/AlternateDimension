package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.Identifier;

public class CheckForSeedsProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		boolean found = false;
		found = false;
		if (entity.getCapability(Capabilities.Item.ENTITY, null) instanceof ResourceHandler<ItemResource> _resourceHandler) {
			for (int _idx = 0; _idx < _resourceHandler.size(); _idx++) {
				ItemStack itemstackiterator = ItemUtil.getStack(_resourceHandler, _idx);
				if (itemstackiterator.is(ItemTags.create(Identifier.parse("alternate_dimension:seeds")))) {
					found = true;
				}
			}
		}
		return found;
	}
}