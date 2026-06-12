package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.lordkittycat.alternate_dimension.entity.ChefEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelplayer_model;

public class ChefRenderer extends MobRenderer<ChefEntity, Modelplayer_model<ChefEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/chef_texture.png");

	public ChefRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelplayer_model<ChefEntity>(context.bakeLayer(Modelplayer_model.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(ChefEntity entity) {
		return entityTexture;
	}
}