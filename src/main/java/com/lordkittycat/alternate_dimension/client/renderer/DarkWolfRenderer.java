package com.lordkittycat.alternate_dimension.client.renderer;

import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.util.context.ContextKey;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.entity.DarkWolfEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelwolf;

public class DarkWolfRenderer extends MobRenderer<DarkWolfEntity, LivingEntityRenderState, Modelwolf> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/dark_wolf_glowing_texture.png");

	public DarkWolfRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelwolf(context.bakeLayer(Modelwolf.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<>(this) {
			final Identifier LAYER_TEXTURE = Identifier.parse("alternate_dimension:textures/entities/dark_wolf_texture.png");
			final RenderType RENDER_TYPE = RenderTypes.eyes(LAYER_TEXTURE);

			@Override
			public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, LivingEntityRenderState state, float headYaw, float headPitch) {
				EntityModel model = new Modelwolf(Minecraft.getInstance().getEntityModels().bakeLayer(Modelwolf.LAYER_LOCATION));
				model.setupAnim(state);
				submitNodeCollector.submitModel(model, state, poseStack, RENDER_TYPE, light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
			}
		});
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(DarkWolfEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		DarkWolfEntity entity = (DarkWolfEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "dark_wolf_glowing_texture")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	@Override
	protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
		poseStack.scale(1.5f, 1.5f, 1.5f);
	}

	public static final ContextKey<DarkWolfEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:dark_wolf_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(DarkWolfRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}