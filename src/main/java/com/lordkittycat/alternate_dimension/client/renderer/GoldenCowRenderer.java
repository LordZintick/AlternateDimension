package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.lordkittycat.alternate_dimension.entity.GoldenCowEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelcow_fixed;

public class GoldenCowRenderer extends MobRenderer<GoldenCowEntity, Modelcow_fixed<GoldenCowEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/golden_cow.png");

	public GoldenCowRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelcow_fixed<GoldenCowEntity>(context.bakeLayer(Modelcow_fixed.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(GoldenCowEntity entity) {
		if (entity.getTexture() != "golden_cow")
			return ResourceLocation.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}
}