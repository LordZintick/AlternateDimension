package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.entity.ElfEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelplayer_model;

public class ElfRenderer extends MobRenderer<ElfEntity, Modelplayer_model<ElfEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/elf.png");

	public ElfRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelplayer_model<ElfEntity>(context.bakeLayer(Modelplayer_model.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(ElfEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(1.2f, 1.2f, 1.2f);
	}

	@Override
	public ResourceLocation getTextureLocation(ElfEntity entity) {
		return entityTexture;
	}
}