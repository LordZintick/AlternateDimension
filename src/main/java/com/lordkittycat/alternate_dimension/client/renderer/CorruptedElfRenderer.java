package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.lordkittycat.alternate_dimension.entity.CorruptedElfEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelplayer_model;

public class CorruptedElfRenderer extends MobRenderer<CorruptedElfEntity, Modelplayer_model<CorruptedElfEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/corrupted-elf.png");

	public CorruptedElfRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelplayer_model<CorruptedElfEntity>(context.bakeLayer(Modelplayer_model.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(CorruptedElfEntity entity) {
		return entityTexture;
	}
}