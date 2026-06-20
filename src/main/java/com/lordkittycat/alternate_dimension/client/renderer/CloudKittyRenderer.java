package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.entity.CloudKittyEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelcat;

public class CloudKittyRenderer extends MobRenderer<CloudKittyEntity, Modelcat<CloudKittyEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/cloud_kitty_texture.png");

	public CloudKittyRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelcat<CloudKittyEntity>(context.bakeLayer(Modelcat.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(CloudKittyEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(3f, 3f, 3f);
	}

	@Override
	public ResourceLocation getTextureLocation(CloudKittyEntity entity) {
		return entityTexture;
	}
}