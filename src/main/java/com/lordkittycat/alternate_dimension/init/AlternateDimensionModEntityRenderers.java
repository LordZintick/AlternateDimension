/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.BoatRenderer;

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
		event.registerEntityRenderer(AlternateDimensionModEntities.AZURE_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.AZURE_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.AZURE_CHEST_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.AZURE_CHEST_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.SCORCHED_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.SCORCHED_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.SCORCHED_CHEST_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.SCORCHED_CHEST_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.REDWOOD_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.REDWOOD_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.REDWOOD_CHEST_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.REDWOOD_CHEST_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.GOLDEN_COW.get(), GoldenCowRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.VIKING.get(), VikingRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.SHROOMITE.get(), ShroomiteRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.BEETLE_BRUTE.get(), BeetleBruteRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.BEETLE_CHASER.get(), BeetleChaserRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.HALLOWED_ONE.get(), HallowedOneRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.PINE_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.PINE_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.PINE_CHEST_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.PINE_CHEST_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.ROMAN.get(), RomanRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.BEETLE_QUEEN.get(), BeetleQueenRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.POSSESSED_ARMOR.get(), PossessedArmorRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.GREENWOOD_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.GREENWOOD_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.GREENWOOD_CHEST_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.GREENWOOD_CHEST_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.CANDYFLOSS_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.CANDYFLOSS_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.CANDYFLOSS_CHEST_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.CANDYFLOSS_CHEST_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.SPORE_SPREADER.get(), SporeSpreaderRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.FROSTY_ZOMBIE.get(), FrostyZombieRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.OVERGROWN_ZOMBIE.get(), OvergrownZombieRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.CORRUPTED_ZOMBIE.get(), CorruptedZombieRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.CALCIFIED_ZOMBIE.get(), CalcifiedZombieRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.CACTUS_ZOMBIE.get(), CactusZombieRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.BLOOD_ZOMBIE.get(), BloodZombieRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.PUMPKIN_ZOMBIE.get(), PumpkinZombieRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.SPECTRAL_ZOMBIE.get(), SpectralZombieRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.ENCHANTED_ZOMBIE.get(), EnchantedZombieRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.MAGMATIC_ZOMBIE.get(), MagmaticZombieRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.SPIDER_HYDRA.get(), SpiderHydraRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.INCENDIARY_SPIDER.get(), IncendiarySpiderRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.VENOMOUS_SPIDER.get(), VenomousSpiderRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.IRON_GIANT.get(), IronGiantRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.PERMACHILL_ZOMBIE.get(), PermachillZombieRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.VERDANT_ZOMBIE.get(), VerdantZombieRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.ELECTRIFIED_SPIDER.get(), ElectrifiedSpiderRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.WILLOW_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.WILLOW_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.WILLOW_CHEST_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.WILLOW_CHEST_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.AXIOMORPH.get(), AxiomorphRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.HERO_ZOMBRINE.get(), HeroZombrineRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.MYTHRIL_COW.get(), MythrilCowRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.ALUMINUM_COW.get(), AluminumCowRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.EVILSTONE_COW.get(), EvilstoneCowRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.MANA_CRYSTAL_COW.get(), ManaCrystalCowRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.TITANIUM_COW.get(), TitaniumCowRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.URANIUM_COW.get(), UraniumCowRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.QUARTZ_COW.get(), QuartzCowRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.BRIMWOOD_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.BRIMWOOD_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.BRIMWOOD_CHEST_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.BRIMWOOD_CHEST_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.SOLAR_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.SOLAR_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.SOLAR_CHEST_BOAT.get(), context -> new BoatRenderer(context, AlternateDimensionModModels.SOLAR_CHEST_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(AlternateDimensionModEntities.ARMINIONITE.get(), ArminioniteRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.SURVIVALIST.get(), SurvivalistRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.FROSTFIRE_STEVE.get(), FrostfireSteveRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.MANA_CRYSTAL_PROJECTILE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.MANA_NINJA.get(), ManaNinjaRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.SAPPHIRE_CHICKEN.get(), SapphireChickenRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.RUBY_CHICKEN.get(), RubyChickenRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.TOPAZ_CHICKEN.get(), TopazChickenRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.ONYX_CHICKEN.get(), OnyxChickenRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.KYANITE_CHICKEN.get(), KyaniteChickenRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.JADE_CHICKEN.get(), JadeChickenRenderer::new);
		event.registerEntityRenderer(AlternateDimensionModEntities.TOURMALINE_CHICKEN.get(), TourmalineChickenRenderer::new);
	}
}