package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.lordkittycat.alternate_dimension.entity.RedGummyBearEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelpolar_bear;

public class RedGummyBearRenderer extends MobRenderer<RedGummyBearEntity, Modelpolar_bear<RedGummyBearEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/gummy_bear_texture_red.png");

	public RedGummyBearRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelpolar_bear<RedGummyBearEntity>(context.bakeLayer(Modelpolar_bear.LAYER_LOCATION)), 0.8f);
	}

	@Override
	public ResourceLocation getTextureLocation(RedGummyBearEntity entity) {
		if (entity.getTexture() != "gummy_bear_texture_red")
			return ResourceLocation.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}
}