package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.lordkittycat.alternate_dimension.entity.WaiterEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelplayer_model;

public class WaiterRenderer extends MobRenderer<WaiterEntity, Modelplayer_model<WaiterEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/waiter.png");

	public WaiterRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelplayer_model<WaiterEntity>(context.bakeLayer(Modelplayer_model.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(WaiterEntity entity) {
		if (entity.getTexture() != "waiter")
			return ResourceLocation.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}
}