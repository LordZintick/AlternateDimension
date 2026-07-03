package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import com.lordkittycat.alternate_dimension.procedures.AlwaysProcedure;
import com.lordkittycat.alternate_dimension.entity.PossessedArmorEntity;
import com.lordkittycat.alternate_dimension.client.model.animations.possessed_armorAnimation;
import com.lordkittycat.alternate_dimension.client.model.Modelpossessed_armor;

public class PossessedArmorRenderer extends MobRenderer<PossessedArmorEntity, Modelpossessed_armor<PossessedArmorEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/possessed_armor.png");

	public PossessedArmorRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelpossessed_armor.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(PossessedArmorEntity entity) {
		if (entity.getTexture() != "possessed_armor")
			return ResourceLocation.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelpossessed_armor<PossessedArmorEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<PossessedArmorEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(PossessedArmorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, possessed_armorAnimation.idle, ageInTicks, 1f);
				if (AlwaysProcedure.execute())
					this.animateWalk(possessed_armorAnimation.walk, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.animationState2, possessed_armorAnimation.attack, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(PossessedArmorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}