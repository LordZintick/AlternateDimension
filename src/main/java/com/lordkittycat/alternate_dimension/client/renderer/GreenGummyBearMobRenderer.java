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

import com.lordkittycat.alternate_dimension.entity.GreenGummyBearMobEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelpolar_bear;

public class GreenGummyBearMobRenderer extends MobRenderer<GreenGummyBearMobEntity, LivingEntityRenderState, Modelpolar_bear> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/gummy_bear_texture_green.png");

	public GreenGummyBearMobRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelpolar_bear(context.bakeLayer(Modelpolar_bear.LAYER_LOCATION)), 0.8f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(GreenGummyBearMobEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		GreenGummyBearMobEntity entity = (GreenGummyBearMobEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "gummy_bear_texture_green")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	public static final ContextKey<GreenGummyBearMobEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:green_gummy_bear_mob_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(GreenGummyBearMobRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}