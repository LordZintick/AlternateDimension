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

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.entity.CloudyWolfEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelwolf;

public class CloudyWolfRenderer extends MobRenderer<CloudyWolfEntity, LivingEntityRenderState, Modelwolf> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/cloudy_wolf_texture.png");

	public CloudyWolfRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelwolf(context.bakeLayer(Modelwolf.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(CloudyWolfEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		CloudyWolfEntity entity = (CloudyWolfEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "cloudy_wolf_texture")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	@Override
	protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
		poseStack.scale(3f, 3f, 3f);
	}

	public static final ContextKey<CloudyWolfEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:cloudy_wolf_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(CloudyWolfRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}