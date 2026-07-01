package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.lordkittycat.alternate_dimension.entity.BlueGummyBearEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelpolar_bear;

public class BlueGummyBearRenderer extends MobRenderer<BlueGummyBearEntity, Modelpolar_bear<BlueGummyBearEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/gummy_bear_texture_blue.png");

	public BlueGummyBearRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelpolar_bear<BlueGummyBearEntity>(context.bakeLayer(Modelpolar_bear.LAYER_LOCATION)), 0.8f);
	}

	@Override
	public ResourceLocation getTextureLocation(BlueGummyBearEntity entity) {
		if (entity.getTexture() != "gummy_bear_texture_blue")
			return ResourceLocation.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}
}