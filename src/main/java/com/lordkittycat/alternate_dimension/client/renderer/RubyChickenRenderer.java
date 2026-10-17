package com.lordkittycat.alternate_dimension.client.renderer;

import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.util.context.ContextKey;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.animal.chicken.AdultChickenModel;

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.entity.RubyChickenEntity;

public class RubyChickenRenderer extends MobRenderer<RubyChickenEntity, ChickenRenderState, AdultChickenModel> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/ruby_chicken.png");

	public RubyChickenRenderer(EntityRendererProvider.Context context) {
		super(context, new AdultChickenModel(context.bakeLayer(ModelLayers.CHICKEN)), 0.25f);
	}

	@Override
	public ChickenRenderState createRenderState() {
		return new ChickenRenderState();
	}

	@Override
	public void extractRenderState(RubyChickenEntity entity, ChickenRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(ChickenRenderState state) {
		RubyChickenEntity entity = (RubyChickenEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "ruby_chicken")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	@Override
	protected void scale(ChickenRenderState state, PoseStack poseStack) {
		poseStack.scale(state.ageScale, state.ageScale, state.ageScale);
	}

	public static final ContextKey<RubyChickenEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:ruby_chicken_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(RubyChickenRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}