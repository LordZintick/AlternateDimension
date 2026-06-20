/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import com.lordkittycat.alternate_dimension.client.renderer.*;

@EventBusSubscriber(Dist.CLIENT)
public class AlternateDimensionModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(AlternateDimensionModEntities.PLUS_PROJECTILE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.BEETLE.get(), BeetleRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.SEED_PROJECTILE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.VAR_PROJECTILE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.WAITER.get(), WaiterRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.FRIENDLY_BEETLE.get(), FriendlyBeetleRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.BREADSTICK_PROJECTILE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.CHEF.get(), ChefRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.RED_GUMMY_BEAR.get(), RedGummyBearRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.BLUE_GUMMY_BEAR.get(), BlueGummyBearRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.GREEN_GUMMY_BEAR_MOB.get(), GreenGummyBearMobRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.ELF.get(), ElfRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.CLOUD_KITTY.get(), CloudKittyRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.CLOUDY_WOLF.get(), CloudyWolfRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.ROCKET_PROJECTILE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.SKY_TURTLE.get(), SkyTurtleRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.CORRUPTED_ELF.get(), CorruptedElfRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.N_SQUARED_PROJECTILE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.DARK_WOLF.get(), DarkWolfRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.GINGERBREAD_WARRIOR.get(), GingerbreadWarriorRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.LIFE_SPIRIT.get(), LifeSpiritRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.FIRE_SPIRIT.get(), FireSpiritRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.ICE_SPIRIT.get(), IceSpiritRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.CANDY_SPIRIT.get(), CandySpiritRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.METEOROID.get(), MeteoroidRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.GOLDEN_COW.get(), GoldenCowRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.VIKING.get(), VikingRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.SHROOMITE.get(), ShroomiteRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.BEETLE_BRUTE.get(), BeetleBruteRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.BEETLE_CHASER.get(), BeetleChaserRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.HALLOWED_ONE.get(), HallowedOneRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.ROMAN.get(), RomanRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.BEETLE_QUEEN.get(), BeetleQueenRenderer::new);
	}
}