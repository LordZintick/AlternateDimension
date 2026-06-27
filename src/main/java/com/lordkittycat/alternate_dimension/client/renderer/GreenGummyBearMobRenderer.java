package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.lordkittycat.alternate_dimension.entity.GreenGummyBearMobEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelpolar_bear;

public class GreenGummyBearMobRenderer extends MobRenderer<GreenGummyBearMobEntity, Modelpolar_bear<GreenGummyBearMobEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/gummy_bear_texture_green.png");

	public GreenGummyBearMobRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelpolar_bear<GreenGummyBearMobEntity>(context.bakeLayer(Modelpolar_bear.LAYER_LOCATION)), 0.8f);
	}

	@Override
	public ResourceLocation getTextureLocation(GreenGummyBearMobEntity entity) {
		if (entity.getTexture() != "gummy_bear_texture_green")
			return ResourceLocation.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}
}