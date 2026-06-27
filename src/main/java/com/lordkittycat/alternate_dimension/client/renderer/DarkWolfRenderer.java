package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.entity.DarkWolfEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelwolf;

public class DarkWolfRenderer extends MobRenderer<DarkWolfEntity, Modelwolf<DarkWolfEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/dark_wolf_glowing_texture.png");

	public DarkWolfRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelwolf<DarkWolfEntity>(context.bakeLayer(Modelwolf.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<DarkWolfEntity, Modelwolf<DarkWolfEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = ResourceLocation.parse("alternate_dimension:textures/entities/dark_wolf_texture.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, DarkWolfEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
				EntityModel model = new Modelwolf(Minecraft.getInstance().getEntityModels().bakeLayer(Modelwolf.LAYER_LOCATION));
				this.getParentModel().copyPropertiesTo(model);
				model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
				model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				model.renderToBuffer(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
			}
		});
	}

	@Override
	protected void scale(DarkWolfEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(2f, 2f, 2f);
	}

	@Override
	public ResourceLocation getTextureLocation(DarkWolfEntity entity) {
		if (entity.getTexture() != "dark_wolf_glowing_texture")
			return ResourceLocation.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}
}