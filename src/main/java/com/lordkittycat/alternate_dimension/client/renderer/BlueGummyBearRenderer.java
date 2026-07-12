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

import com.lordkittycat.alternate_dimension.entity.BlueGummyBearEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelpolar_bear;

public class BlueGummyBearRenderer extends MobRenderer<BlueGummyBearEntity, LivingEntityRenderState, Modelpolar_bear> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/gummy_bear_texture_blue.png");

	public BlueGummyBearRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelpolar_bear(context.bakeLayer(Modelpolar_bear.LAYER_LOCATION)), 0.8f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(BlueGummyBearEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		BlueGummyBearEntity entity = (BlueGummyBearEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "gummy_bear_texture_blue")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	public static final ContextKey<BlueGummyBearEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:blue_gummy_bear_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(BlueGummyBearRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}