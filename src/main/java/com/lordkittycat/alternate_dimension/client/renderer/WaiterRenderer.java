package com.lordkittycat.alternate_dimension.client.renderer;

import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.util.context.ContextKey;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.lordkittycat.alternate_dimension.entity.WaiterEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelplayer_model;

public class WaiterRenderer extends MobRenderer<WaiterEntity, LivingEntityRenderState, Modelplayer_model> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/waiter.png");

	public WaiterRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelplayer_model(context.bakeLayer(Modelplayer_model.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(WaiterEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		WaiterEntity entity = (WaiterEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "waiter")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	public static final ContextKey<WaiterEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:waiter_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(WaiterRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}