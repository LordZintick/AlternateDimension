package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.SilverfishModel;

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.entity.BeetleEntity;

public class BeetleRenderer extends MobRenderer<BeetleEntity, SilverfishModel<BeetleEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/beetle.png");

	public BeetleRenderer(EntityRendererProvider.Context context) {
		super(context, new SilverfishModel<BeetleEntity>(context.bakeLayer(ModelLayers.SILVERFISH)), 0.5f);
	}

	@Override
	protected void scale(BeetleEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(1.1f, 1.1f, 1.1f);
	}

	@Override
	public ResourceLocation getTextureLocation(BeetleEntity entity) {
		return entityTexture;
	}
}