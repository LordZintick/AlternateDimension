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

import com.lordkittycat.alternate_dimension.entity.CloudKittyEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelcat;

public class CloudKittyRenderer extends MobRenderer<CloudKittyEntity, LivingEntityRenderState, Modelcat> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/cloud_kitty_texture.png");

	public CloudKittyRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelcat(context.bakeLayer(Modelcat.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(CloudKittyEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		CloudKittyEntity entity = (CloudKittyEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "cloud_kitty_texture")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	@Override
	protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
		poseStack.scale(3f, 3f, 3f);
	}

	public static final ContextKey<CloudKittyEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:cloud_kitty_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(CloudKittyRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}