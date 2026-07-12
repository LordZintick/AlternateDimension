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

import com.lordkittycat.alternate_dimension.entity.GoldenCowEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelcow_fixed;

public class GoldenCowRenderer extends MobRenderer<GoldenCowEntity, LivingEntityRenderState, Modelcow_fixed> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/golden_cow.png");

	public GoldenCowRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelcow_fixed(context.bakeLayer(Modelcow_fixed.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(GoldenCowEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		GoldenCowEntity entity = (GoldenCowEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "golden_cow")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	public static final ContextKey<GoldenCowEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:golden_cow_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(GoldenCowRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}