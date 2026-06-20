package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.SilverfishModel;

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.entity.BeetleQueenEntity;

public class BeetleQueenRenderer extends MobRenderer<BeetleQueenEntity, SilverfishModel<BeetleQueenEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/beetle.png");

	public BeetleQueenRenderer(EntityRendererProvider.Context context) {
		super(context, new SilverfishModel<BeetleQueenEntity>(context.bakeLayer(ModelLayers.SILVERFISH)), 0.5f);
	}

	@Override
	protected void scale(BeetleQueenEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(15f, 15f, 15f);
	}

	@Override
	public ResourceLocation getTextureLocation(BeetleQueenEntity entity) {
		if (entity.getTexture() != "beetle")
			return ResourceLocation.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}
}