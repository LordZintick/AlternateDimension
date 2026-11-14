package com.lordkittycat.alternate_dimension.client.renderer;

import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.util.context.ContextKey;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.animation.AnimationDefinition;

import java.util.Map;

import com.lordkittycat.alternate_dimension.entity.GingerbreadWarriorEntity;
import com.lordkittycat.alternate_dimension.client.model.animations.gingerbread_warriorAnimation;
import com.lordkittycat.alternate_dimension.client.model.Modelgingerbread_warrior;

public class GingerbreadWarriorRenderer extends MobRenderer<GingerbreadWarriorEntity, LivingEntityRenderState, Modelgingerbread_warrior> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/gingerbread_warrior_texture_new.png");

	public GingerbreadWarriorRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelgingerbread_warrior.LAYER_LOCATION)), 0.4f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(GingerbreadWarriorEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		GingerbreadWarriorEntity entity = (GingerbreadWarriorEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "gingerbread_warrior_texture_new")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelgingerbread_warrior {
		private final KeyframeAnimation keyframeAnimation0;
		private final KeyframeAnimation keyframeAnimation1;

		public AnimatedModel(ModelPart root) {
			super(root);
			this.keyframeAnimation0 = safeBake(gingerbread_warriorAnimation.idle);
			this.keyframeAnimation1 = safeBake(gingerbread_warriorAnimation.walk);
		}

		private KeyframeAnimation safeBake(AnimationDefinition source) {
			try {
				return source.bake(root);
			} catch (IllegalArgumentException e) {
				return new AnimationDefinition(0, false, Map.of()).bake(root);
			}
		}

		@Override
		public void setupAnim(LivingEntityRenderState state) {
			this.root().getAllParts().forEach(ModelPart::resetPose);
			GingerbreadWarriorEntity entity = state.getRenderData(ENTITY_KEY);
			this.keyframeAnimation0.apply(entity.animationState0, state.ageInTicks, 1f);
			this.keyframeAnimation1.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 1f, 1f);
			super.setupAnim(state);
		}
	}

	public static final ContextKey<GingerbreadWarriorEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:gingerbread_warrior_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(GingerbreadWarriorRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}