package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import com.lordkittycat.alternate_dimension.entity.FireSpiritEntity;
import com.lordkittycat.alternate_dimension.client.model.animations.spiritAnimation;
import com.lordkittycat.alternate_dimension.client.model.Modelspirit;

public class FireSpiritRenderer extends MobRenderer<FireSpiritEntity, Modelspirit<FireSpiritEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/fire_spirit_new.png");

	public FireSpiritRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelspirit.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(FireSpiritEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelspirit<FireSpiritEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<FireSpiritEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(FireSpiritEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, spiritAnimation.idle, ageInTicks, 1f);
				this.animateWalk(spiritAnimation.walk, limbSwing, limbSwingAmount, 1f, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(FireSpiritEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}