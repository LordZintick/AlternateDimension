package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.SilverfishModel;

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.entity.FriendlyBeetleEntity;

public class FriendlyBeetleRenderer extends MobRenderer<FriendlyBeetleEntity, SilverfishModel<FriendlyBeetleEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/friendly_beetle.png");

	public FriendlyBeetleRenderer(EntityRendererProvider.Context context) {
		super(context, new SilverfishModel<FriendlyBeetleEntity>(context.bakeLayer(ModelLayers.SILVERFISH)), 0.5f);
	}

	@Override
	protected void scale(FriendlyBeetleEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(1.5f, 1.5f, 1.5f);
		poseStack.scale(entity.getAgeScale(), entity.getAgeScale(), entity.getAgeScale());
	}

	@Override
	public ResourceLocation getTextureLocation(FriendlyBeetleEntity entity) {
		return entityTexture;
	}
}