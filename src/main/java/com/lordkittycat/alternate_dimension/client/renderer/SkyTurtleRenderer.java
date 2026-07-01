package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.entity.SkyTurtleEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelturtle;

public class SkyTurtleRenderer extends MobRenderer<SkyTurtleEntity, Modelturtle<SkyTurtleEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/cloud_turtle.png");

	public SkyTurtleRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelturtle<SkyTurtleEntity>(context.bakeLayer(Modelturtle.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(SkyTurtleEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(3f, 3f, 3f);
	}

	@Override
	public ResourceLocation getTextureLocation(SkyTurtleEntity entity) {
		if (entity.getTexture() != "cloud_turtle")
			return ResourceLocation.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}
}