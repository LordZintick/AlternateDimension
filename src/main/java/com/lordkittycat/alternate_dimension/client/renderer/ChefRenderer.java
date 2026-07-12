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

import com.lordkittycat.alternate_dimension.entity.ChefEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelplayer_model;

public class ChefRenderer extends MobRenderer<ChefEntity, LivingEntityRenderState, Modelplayer_model> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/chef_texture.png");

	public ChefRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelplayer_model(context.bakeLayer(Modelplayer_model.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(ChefEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		ChefEntity entity = (ChefEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "chef_texture")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	public static final ContextKey<ChefEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:chef_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(ChefRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}