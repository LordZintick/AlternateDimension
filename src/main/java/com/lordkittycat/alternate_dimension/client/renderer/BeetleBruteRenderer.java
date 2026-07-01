package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.SilverfishModel;

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.entity.BeetleBruteEntity;

public class BeetleBruteRenderer extends MobRenderer<BeetleBruteEntity, SilverfishModel<BeetleBruteEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/beetle.png");

	public BeetleBruteRenderer(EntityRendererProvider.Context context) {
		super(context, new SilverfishModel<BeetleBruteEntity>(context.bakeLayer(ModelLayers.SILVERFISH)), 0.5f);
	}

	@Override
	protected void scale(BeetleBruteEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(1.5f, 1.5f, 1.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(BeetleBruteEntity entity) {
		if (entity.getTexture() != "beetle")
			return ResourceLocation.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}
}