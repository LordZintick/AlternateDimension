package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import com.lordkittycat.alternate_dimension.procedures.AlwaysProcedure;
import com.lordkittycat.alternate_dimension.entity.ShroomiteEntity;
import com.lordkittycat.alternate_dimension.client.model.animations.shroomiteAnimation;
import com.lordkittycat.alternate_dimension.client.model.Modelshroomite;

public class ShroomiteRenderer extends MobRenderer<ShroomiteEntity, Modelshroomite<ShroomiteEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/shroomite_texture.png");

	public ShroomiteRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelshroomite.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(ShroomiteEntity entity) {
		if (entity.getTexture() != "shroomite_texture")
			return ResourceLocation.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelshroomite<ShroomiteEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<ShroomiteEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(ShroomiteEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, shroomiteAnimation.idle, ageInTicks, 1f);
				if (AlwaysProcedure.execute())
					this.animateWalk(shroomiteAnimation.walk, limbSwing, limbSwingAmount, 1f, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(ShroomiteEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}