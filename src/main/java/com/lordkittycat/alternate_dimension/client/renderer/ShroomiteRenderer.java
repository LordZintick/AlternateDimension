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

import com.lordkittycat.alternate_dimension.procedures.AlwaysProcedure;
import com.lordkittycat.alternate_dimension.entity.ShroomiteEntity;
import com.lordkittycat.alternate_dimension.client.model.animations.shroomiteAnimation;
import com.lordkittycat.alternate_dimension.client.model.Modelshroomite;

public class ShroomiteRenderer extends MobRenderer<ShroomiteEntity, LivingEntityRenderState, Modelshroomite> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/shroomite_texture.png");

	public ShroomiteRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelshroomite.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(ShroomiteEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		ShroomiteEntity entity = (ShroomiteEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "shroomite_texture")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelshroomite {
		private final KeyframeAnimation keyframeAnimation0;
		private final KeyframeAnimation keyframeAnimation1;

		public AnimatedModel(ModelPart root) {
			super(root);
			this.keyframeAnimation0 = safeBake(shroomiteAnimation.idle);
			this.keyframeAnimation1 = safeBake(shroomiteAnimation.walk);
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
			ShroomiteEntity entity = state.getRenderData(ENTITY_KEY);
			this.keyframeAnimation0.apply(entity.animationState0, state.ageInTicks, 1f);
			if (AlwaysProcedure.execute())
				this.keyframeAnimation1.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 1f, 1f);
			super.setupAnim(state);
		}
	}

	public static final ContextKey<ShroomiteEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:shroomite_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(ShroomiteRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}