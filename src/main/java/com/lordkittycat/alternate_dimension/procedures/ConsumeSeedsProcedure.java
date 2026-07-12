package com.lordkittycat.alternate_dimension.procedures;

import com.mojang.blaze3d.resource.ResourceHandle;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.transfer.IndexModifier;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;

public class ConsumeSeedsProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity.getCapability(Capabilities.Item.ENTITY, null) instanceof ResourceHandler<ItemResource> resourceHandler) {
			for (int i = 0; i < resourceHandler.size(); i++) {
				ItemStack seed = resourceHandler.getResource(i).toStack().copy();
				if (seed.is(ItemTags.create(Identifier.parse("alternate_dimension:seeds")))) {
					try (Transaction action = Transaction.openRoot()) {
						resourceHandler.extract(i, ItemResource.of(seed), 1, action);
						action.commit();
					}
					break;
				}
			}
		}
	}
}