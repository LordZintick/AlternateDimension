/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.Registries;

import com.lordkittycat.alternate_dimension.entity.*;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

@EventBusSubscriber
public class AlternateDimensionModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, AlternateDimensionMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<PlusProjectileEntity>> PLUS_PROJECTILE = register("plus_projectile",
			EntityType.Builder.<PlusProjectileEntity>of(PlusProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<BeetleEntity>> BEETLE = register("beetle",
			EntityType.Builder.<BeetleEntity>of(BeetleEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.4f, 0.3f));
	public static final DeferredHolder<EntityType<?>, EntityType<SeedProjectileEntity>> SEED_PROJECTILE = register("seed_projectile",
			EntityType.Builder.<SeedProjectileEntity>of(SeedProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<VarProjectileEntity>> VAR_PROJECTILE = register("var_projectile",
			EntityType.Builder.<VarProjectileEntity>of(VarProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<WaiterEntity>> WAITER = register("waiter",
			EntityType.Builder.<WaiterEntity>of(WaiterEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<FriendlyBeetleEntity>> FRIENDLY_BEETLE = register("friendly_beetle",
			EntityType.Builder.<FriendlyBeetleEntity>of(FriendlyBeetleEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.4f, 0.3f));
	public static final DeferredHolder<EntityType<?>, EntityType<BreadstickProjectileEntity>> BREADSTICK_PROJECTILE = register("breadstick_projectile",
			EntityType.Builder.<BreadstickProjectileEntity>of(BreadstickProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<ChefEntity>> CHEF = register("chef",
			EntityType.Builder.<ChefEntity>of(ChefEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<RedGummyBearEntity>> RED_GUMMY_BEAR = register("red_gummy_bear",
			EntityType.Builder.<RedGummyBearEntity>of(RedGummyBearEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.2f, 1.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<BlueGummyBearEntity>> BLUE_GUMMY_BEAR = register("blue_gummy_bear",
			EntityType.Builder.<BlueGummyBearEntity>of(BlueGummyBearEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.2f, 1.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<GreenGummyBearMobEntity>> GREEN_GUMMY_BEAR_MOB = register("green_gummy_bear_mob",
			EntityType.Builder.<GreenGummyBearMobEntity>of(GreenGummyBearMobEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.2f, 1.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<ElfEntity>> ELF = register("elf", EntityType.Builder.<ElfEntity>of(ElfEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

			.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<CloudKittyEntity>> CLOUD_KITTY = register("cloud_kitty",
			EntityType.Builder.<CloudKittyEntity>of(CloudKittyEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 0.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<CloudyWolfEntity>> CLOUDY_WOLF = register("cloudy_wolf",
			EntityType.Builder.<CloudyWolfEntity>of(CloudyWolfEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 0.9f));
	public static final DeferredHolder<EntityType<?>, EntityType<RocketProjectileEntity>> ROCKET_PROJECTILE = register("rocket_projectile",
			EntityType.Builder.<RocketProjectileEntity>of(RocketProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<SkyTurtleEntity>> SKY_TURTLE = register("sky_turtle",
			EntityType.Builder.<SkyTurtleEntity>of(SkyTurtleEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<CorruptedElfEntity>> CORRUPTED_ELF = register("corrupted_elf",
			EntityType.Builder.<CorruptedElfEntity>of(CorruptedElfEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<NSquaredProjectileEntity>> N_SQUARED_PROJECTILE = register("n_squared_projectile",
			EntityType.Builder.<NSquaredProjectileEntity>of(NSquaredProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<DarkWolfEntity>> DARK_WOLF = register("dark_wolf",
			EntityType.Builder.<DarkWolfEntity>of(DarkWolfEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 0.9f));
	public static final DeferredHolder<EntityType<?>, EntityType<GingerbreadWarriorEntity>> GINGERBREAD_WARRIOR = register("gingerbread_warrior",
			EntityType.Builder.<GingerbreadWarriorEntity>of(GingerbreadWarriorEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.4f, 0.9f));
	public static final DeferredHolder<EntityType<?>, EntityType<LifeSpiritEntity>> LIFE_SPIRIT = register("life_spirit",
			EntityType.Builder.<LifeSpiritEntity>of(LifeSpiritEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<FireSpiritEntity>> FIRE_SPIRIT = register("fire_spirit",
			EntityType.Builder.<FireSpiritEntity>of(FireSpiritEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<IceSpiritEntity>> ICE_SPIRIT = register("ice_spirit",
			EntityType.Builder.<IceSpiritEntity>of(IceSpiritEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<CandySpiritEntity>> CANDY_SPIRIT = register("candy_spirit",
			EntityType.Builder.<CandySpiritEntity>of(CandySpiritEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<MeteoroidEntity>> METEOROID = register("meteoroid",
			EntityType.Builder.<MeteoroidEntity>of(MeteoroidEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(5f, 5f));
	public static final DeferredHolder<EntityType<?>, EntityType<GoldenCowEntity>> GOLDEN_COW = register("golden_cow",
			EntityType.Builder.<GoldenCowEntity>of(GoldenCowEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<VikingEntity>> VIKING = register("viking",
			EntityType.Builder.<VikingEntity>of(VikingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<ShroomiteEntity>> SHROOMITE = register("shroomite",
			EntityType.Builder.<ShroomiteEntity>of(ShroomiteEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<BeetleBruteEntity>> BEETLE_BRUTE = register("beetle_brute",
			EntityType.Builder.<BeetleBruteEntity>of(BeetleBruteEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.4f, 0.3f));
	public static final DeferredHolder<EntityType<?>, EntityType<BeetleChaserEntity>> BEETLE_CHASER = register("beetle_chaser",
			EntityType.Builder.<BeetleChaserEntity>of(BeetleChaserEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.4f, 0.3f));
	public static final DeferredHolder<EntityType<?>, EntityType<HallowedOneEntity>> HALLOWED_ONE = register("hallowed_one",
			EntityType.Builder.<HallowedOneEntity>of(HallowedOneEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<RomanEntity>> ROMAN = register("roman",
			EntityType.Builder.<RomanEntity>of(RomanEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<BeetleQueenEntity>> BEETLE_QUEEN = register("beetle_queen",
			EntityType.Builder.<BeetleQueenEntity>of(BeetleQueenEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.4f, 0.3f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(RegisterSpawnPlacementsEvent event) {
		BeetleEntity.init(event);
		WaiterEntity.init(event);
		FriendlyBeetleEntity.init(event);
		ChefEntity.init(event);
		RedGummyBearEntity.init(event);
		BlueGummyBearEntity.init(event);
		GreenGummyBearMobEntity.init(event);
		ElfEntity.init(event);
		CloudKittyEntity.init(event);
		CloudyWolfEntity.init(event);
		SkyTurtleEntity.init(event);
		CorruptedElfEntity.init(event);
		DarkWolfEntity.init(event);
		GingerbreadWarriorEntity.init(event);
		LifeSpiritEntity.init(event);
		FireSpiritEntity.init(event);
		IceSpiritEntity.init(event);
		CandySpiritEntity.init(event);
		MeteoroidEntity.init(event);
		GoldenCowEntity.init(event);
		VikingEntity.init(event);
		ShroomiteEntity.init(event);
		BeetleBruteEntity.init(event);
		BeetleChaserEntity.init(event);
		HallowedOneEntity.init(event);
		RomanEntity.init(event);
		BeetleQueenEntity.init(event);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(BEETLE.get(), BeetleEntity.createAttributes().build());
		event.put(WAITER.get(), WaiterEntity.createAttributes().build());
		event.put(FRIENDLY_BEETLE.get(), FriendlyBeetleEntity.createAttributes().build());
		event.put(CHEF.get(), ChefEntity.createAttributes().build());
		event.put(RED_GUMMY_BEAR.get(), RedGummyBearEntity.createAttributes().build());
		event.put(BLUE_GUMMY_BEAR.get(), BlueGummyBearEntity.createAttributes().build());
		event.put(GREEN_GUMMY_BEAR_MOB.get(), GreenGummyBearMobEntity.createAttributes().build());
		event.put(ELF.get(), ElfEntity.createAttributes().build());
		event.put(CLOUD_KITTY.get(), CloudKittyEntity.createAttributes().build());
		event.put(CLOUDY_WOLF.get(), CloudyWolfEntity.createAttributes().build());
		event.put(SKY_TURTLE.get(), SkyTurtleEntity.createAttributes().build());
		event.put(CORRUPTED_ELF.get(), CorruptedElfEntity.createAttributes().build());
		event.put(DARK_WOLF.get(), DarkWolfEntity.createAttributes().build());
		event.put(GINGERBREAD_WARRIOR.get(), GingerbreadWarriorEntity.createAttributes().build());
		event.put(LIFE_SPIRIT.get(), LifeSpiritEntity.createAttributes().build());
		event.put(FIRE_SPIRIT.get(), FireSpiritEntity.createAttributes().build());
		event.put(ICE_SPIRIT.get(), IceSpiritEntity.createAttributes().build());
		event.put(CANDY_SPIRIT.get(), CandySpiritEntity.createAttributes().build());
		event.put(METEOROID.get(), MeteoroidEntity.createAttributes().build());
		event.put(GOLDEN_COW.get(), GoldenCowEntity.createAttributes().build());
		event.put(VIKING.get(), VikingEntity.createAttributes().build());
		event.put(SHROOMITE.get(), ShroomiteEntity.createAttributes().build());
		event.put(BEETLE_BRUTE.get(), BeetleBruteEntity.createAttributes().build());
		event.put(BEETLE_CHASER.get(), BeetleChaserEntity.createAttributes().build());
		event.put(HALLOWED_ONE.get(), HallowedOneEntity.createAttributes().build());
		event.put(ROMAN.get(), RomanEntity.createAttributes().build());
		event.put(BEETLE_QUEEN.get(), BeetleQueenEntity.createAttributes().build());
	}
}