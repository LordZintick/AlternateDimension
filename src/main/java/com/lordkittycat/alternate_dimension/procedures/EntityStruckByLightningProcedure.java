package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.Identifier;

import javax.annotation.Nullable;

@EventBusSubscriber
public class EntityStruckByLightningProcedure {
	@SubscribeEvent
	public static void onEntityStruckByLightning(EntityStruckByLightningEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(Identifier.parse("alternate_dimension:prevents_lightning_damage")))) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
		}
	}
}