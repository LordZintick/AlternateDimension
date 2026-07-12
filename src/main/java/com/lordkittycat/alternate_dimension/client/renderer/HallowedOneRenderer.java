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

import com.lordkittycat.alternate_dimension.entity.HallowedOneEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelplayer_model;

public class HallowedOneRenderer extends MobRenderer<HallowedOneEntity, LivingEntityRenderState, Modelplayer_model> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/hallowed_one.png");

	public HallowedOneRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelplayer_model(context.bakeLayer(Modelplayer_model.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(HallowedOneEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		HallowedOneEntity entity = (HallowedOneEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "hallowed_one")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	public static final ContextKey<HallowedOneEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:hallowed_one_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(HallowedOneRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}