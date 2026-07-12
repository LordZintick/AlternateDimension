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
import net.minecraft.client.model.monster.silverfish.SilverfishModel;
import net.minecraft.client.model.geom.ModelLayers;

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.entity.BeetleEntity;

public class BeetleRenderer extends MobRenderer<BeetleEntity, LivingEntityRenderState, SilverfishModel> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/beetle.png");

	public BeetleRenderer(EntityRendererProvider.Context context) {
		super(context, new SilverfishModel(context.bakeLayer(ModelLayers.SILVERFISH)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(BeetleEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		BeetleEntity entity = (BeetleEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "beetle")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	@Override
	protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
		poseStack.scale(1.1f, 1.1f, 1.1f);
	}

	public static final ContextKey<BeetleEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:beetle_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(BeetleRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}