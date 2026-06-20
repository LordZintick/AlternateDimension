package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.lordkittycat.alternate_dimension.entity.HallowedOneEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelplayer_model;

public class HallowedOneRenderer extends MobRenderer<HallowedOneEntity, Modelplayer_model<HallowedOneEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/hallowed_one.png");

	public HallowedOneRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelplayer_model<HallowedOneEntity>(context.bakeLayer(Modelplayer_model.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(HallowedOneEntity entity) {
		return entityTexture;
	}
}