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

import com.lordkittycat.alternate_dimension.entity.MeteoroidEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelmeteoroid;

public class MeteoroidRenderer extends MobRenderer<MeteoroidEntity, LivingEntityRenderState, Modelmeteoroid> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/meteoroid_texture.png");

	public MeteoroidRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelmeteoroid(context.bakeLayer(Modelmeteoroid.LAYER_LOCATION)), 0f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(MeteoroidEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		MeteoroidEntity entity = (MeteoroidEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "meteoroid_texture")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	public static final ContextKey<MeteoroidEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:meteoroid_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(MeteoroidRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}