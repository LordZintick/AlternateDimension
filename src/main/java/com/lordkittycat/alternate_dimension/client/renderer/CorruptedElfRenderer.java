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

import com.lordkittycat.alternate_dimension.entity.CorruptedElfEntity;
import com.lordkittycat.alternate_dimension.client.model.Modelplayer_model;

public class CorruptedElfRenderer extends MobRenderer<CorruptedElfEntity, LivingEntityRenderState, Modelplayer_model> {
	private final Identifier entityTexture = Identifier.parse("alternate_dimension:textures/entities/corrupted-elf.png");

	public CorruptedElfRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelplayer_model(context.bakeLayer(Modelplayer_model.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(CorruptedElfEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		CorruptedElfEntity entity = (CorruptedElfEntity) state.getRenderData(ENTITY_KEY);
		if (entity != null && entity.getTexture() != "corrupted-elf")
			return Identifier.parse("alternate_dimension:textures/entities/" + entity.getTexture() + ".png");
		return entityTexture;
	}

	public static final ContextKey<CorruptedElfEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("alternate_dimension:corrupted_elf_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(CorruptedElfRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}