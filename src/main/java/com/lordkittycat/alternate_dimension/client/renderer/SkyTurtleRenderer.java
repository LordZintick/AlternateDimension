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

import com.lordkittycat.alternate_dimension.entity.SkyTurtleEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelturtle;

public class SkyTurtleRenderer extends MobRenderer<SkyTurtleEntity, LivingEntityRenderState, Modelturtle> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/cloud_turtle.png");

	public SkyTurtleRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelturtle(context.bakeLayer(Modelturtle.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(SkyTurtleEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		SkyTurtleEntity entity = (SkyTurtleEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "cloud_turtle")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	@Override
	protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
		poseStack.scale(3f, 3f, 3f);
	}

	public static final ContextKey<SkyTurtleEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:sky_turtle_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(SkyTurtleRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}