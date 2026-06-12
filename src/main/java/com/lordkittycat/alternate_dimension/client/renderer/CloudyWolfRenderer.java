package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.entity.CloudyWolfEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelwolf;

public class CloudyWolfRenderer extends MobRenderer<CloudyWolfEntity, Modelwolf<CloudyWolfEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/cloudy_wolf_texture.png");

	public CloudyWolfRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelwolf<CloudyWolfEntity>(context.bakeLayer(Modelwolf.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(CloudyWolfEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(5f, 5f, 5f);
	}

	@Override
	public ResourceLocation getTextureLocation(CloudyWolfEntity entity) {
		return entityTexture;
	}
}