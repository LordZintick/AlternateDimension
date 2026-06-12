package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.lordkittycat.alternate_dimension.entity.MeteoroidEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelmeteoroid;

public class MeteoroidRenderer extends MobRenderer<MeteoroidEntity, Modelmeteoroid<MeteoroidEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/meteoroid_texture.png");

	public MeteoroidRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelmeteoroid<MeteoroidEntity>(context.bakeLayer(Modelmeteoroid.LAYER_LOCATION)), 0f);
	}

	@Override
	public ResourceLocation getTextureLocation(MeteoroidEntity entity) {
		return entityTexture;
	}
}