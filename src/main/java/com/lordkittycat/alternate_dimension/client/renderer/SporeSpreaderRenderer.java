package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import com.lordkittycat.alternate_dimension.procedures.AlwaysProcedure;
import com.lordkittycat.alternate_dimension.entity.SporeSpreaderEntity;
import com.lordkittycat.alternate_dimension.client.model.animations.spore_spreaderAnimation;
import com.lordkittycat.alternate_dimension.client.model.Modelspore_spreader;

public class SporeSpreaderRenderer extends MobRenderer<SporeSpreaderEntity, Modelspore_spreader<SporeSpreaderEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/spore_spreader_texture.png");

	public SporeSpreaderRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelspore_spreader.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(SporeSpreaderEntity entity) {
		if (entity.getTexture() != "spore_spreader_texture")
			return ResourceLocation.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelspore_spreader<SporeSpreaderEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<SporeSpreaderEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(SporeSpreaderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, spore_spreaderAnimation.idle, ageInTicks, 1f);
				if (AlwaysProcedure.execute())
					this.animateWalk(spore_spreaderAnimation.walk, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.animationState2, spore_spreaderAnimation.attack, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(SporeSpreaderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}