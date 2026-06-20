package com.lordkittycat.alternate_dimension.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import com.lordkittycat.alternate_dimension.entity.GingerbreadWarriorEntity;
import com.lordkittycat.alternate_dimension.client.model.animations.gingerbread_warriorAnimation;
import com.lordkittycat.alternate_dimension.client.model.Modelgingerbread_warrior;

public class GingerbreadWarriorRenderer extends MobRenderer<GingerbreadWarriorEntity, Modelgingerbread_warrior<GingerbreadWarriorEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("alternate_dimension:textures/entities/gingerbread_warrior_texture_new.png");

	public GingerbreadWarriorRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelgingerbread_warrior.LAYER_LOCATION)), 0.4f);
	}

	@Override
	public ResourceLocation getTextureLocation(GingerbreadWarriorEntity entity) {
		if (entity.getTexture() != "gingerbread_warrior_texture_new")
			return ResourceLocation.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelgingerbread_warrior<GingerbreadWarriorEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<GingerbreadWarriorEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(GingerbreadWarriorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, gingerbread_warriorAnimation.idle, ageInTicks, 1f);
				this.animateWalk(gingerbread_warriorAnimation.walk, limbSwing, limbSwingAmount, 1f, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(GingerbreadWarriorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}