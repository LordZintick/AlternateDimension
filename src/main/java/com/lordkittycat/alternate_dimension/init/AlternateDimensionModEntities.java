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
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
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
			EntityType.Builder.<RedGummyBearEntity>of(RedGummyBearEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.2f, 1.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<BlueGummyBearEntity>> BLUE_GUMMY_BEAR = register("blue_gummy_bear",
			EntityType.Builder.<BlueGummyBearEntity>of(BlueGummyBearEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.2f, 1.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<GreenGummyBearMobEntity>> GREEN_GUMMY_BEAR_MOB = register("green_gummy_bear_mob",
			EntityType.Builder.<GreenGummyBearMobEntity>of(GreenGummyBearMobEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.2f, 1.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<ElfEntity>> ELF = register("elf", EntityType.Builder.<ElfEntity>of(ElfEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

			.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<CloudKittyEntity>> CLOUD_KITTY = register("cloud_kitty",
			EntityType.Builder.<CloudKittyEntity>of(CloudKittyEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 0.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<CloudyWolfEntity>> CLOUDY_WOLF = register("cloudy_wolf",
			EntityType.Builder.<CloudyWolfEntity>of(CloudyWolfEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

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
			EntityType.Builder.<GingerbreadWarriorEntity>of(GingerbreadWarriorEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

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
	public static final DeferredHolder<EntityType<?>, EntityType<AzureBoatEntity>> AZURE_BOAT = register("azure_boat",
			EntityType.Builder.<AzureBoatEntity>of(AzureBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<AzureChestBoatEntity>> AZURE_CHEST_BOAT = register("azure_chest_boat",
			EntityType.Builder.<AzureChestBoatEntity>of(AzureChestBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ScorchedBoatEntity>> SCORCHED_BOAT = register("scorched_boat",
			EntityType.Builder.<ScorchedBoatEntity>of(ScorchedBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ScorchedChestBoatEntity>> SCORCHED_CHEST_BOAT = register("scorched_chest_boat",
			EntityType.Builder.<ScorchedChestBoatEntity>of(ScorchedChestBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<RedwoodBoatEntity>> REDWOOD_BOAT = register("redwood_boat",
			EntityType.Builder.<RedwoodBoatEntity>of(RedwoodBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<RedwoodChestBoatEntity>> REDWOOD_CHEST_BOAT = register("redwood_chest_boat",
			EntityType.Builder.<RedwoodChestBoatEntity>of(RedwoodChestBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
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
	public static final DeferredHolder<EntityType<?>, EntityType<PineBoatEntity>> PINE_BOAT = register("pine_boat",
			EntityType.Builder.<PineBoatEntity>of(PineBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<PineChestBoatEntity>> PINE_CHEST_BOAT = register("pine_chest_boat",
			EntityType.Builder.<PineChestBoatEntity>of(PineChestBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<RomanEntity>> ROMAN = register("roman",
			EntityType.Builder.<RomanEntity>of(RomanEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<BeetleQueenEntity>> BEETLE_QUEEN = register("beetle_queen",
			EntityType.Builder.<BeetleQueenEntity>of(BeetleQueenEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.4f, 0.3f));
	public static final DeferredHolder<EntityType<?>, EntityType<PossessedArmorEntity>> POSSESSED_ARMOR = register("possessed_armor",
			EntityType.Builder.<PossessedArmorEntity>of(PossessedArmorEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<GreenwoodBoatEntity>> GREENWOOD_BOAT = register("greenwood_boat",
			EntityType.Builder.<GreenwoodBoatEntity>of(GreenwoodBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<GreenwoodChestBoatEntity>> GREENWOOD_CHEST_BOAT = register("greenwood_chest_boat",
			EntityType.Builder.<GreenwoodChestBoatEntity>of(GreenwoodChestBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<CandyflossBoatEntity>> CANDYFLOSS_BOAT = register("candyfloss_boat",
			EntityType.Builder.<CandyflossBoatEntity>of(CandyflossBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<CandyflossChestBoatEntity>> CANDYFLOSS_CHEST_BOAT = register("candyfloss_chest_boat",
			EntityType.Builder.<CandyflossChestBoatEntity>of(CandyflossChestBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<SporeSpreaderEntity>> SPORE_SPREADER = register("spore_spreader",
			EntityType.Builder.<SporeSpreaderEntity>of(SporeSpreaderEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.9f, 1.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<FrostyZombieEntity>> FROSTY_ZOMBIE = register("frosty_zombie",
			EntityType.Builder.<FrostyZombieEntity>of(FrostyZombieEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<OvergrownZombieEntity>> OVERGROWN_ZOMBIE = register("overgrown_zombie",
			EntityType.Builder.<OvergrownZombieEntity>of(OvergrownZombieEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<CorruptedZombieEntity>> CORRUPTED_ZOMBIE = register("corrupted_zombie",
			EntityType.Builder.<CorruptedZombieEntity>of(CorruptedZombieEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<CalcifiedZombieEntity>> CALCIFIED_ZOMBIE = register("calcified_zombie",
			EntityType.Builder.<CalcifiedZombieEntity>of(CalcifiedZombieEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<CactusZombieEntity>> CACTUS_ZOMBIE = register("cactus_zombie",
			EntityType.Builder.<CactusZombieEntity>of(CactusZombieEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<BloodZombieEntity>> BLOOD_ZOMBIE = register("blood_zombie",
			EntityType.Builder.<BloodZombieEntity>of(BloodZombieEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<PumpkinZombieEntity>> PUMPKIN_ZOMBIE = register("pumpkin_zombie",
			EntityType.Builder.<PumpkinZombieEntity>of(PumpkinZombieEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<SpectralZombieEntity>> SPECTRAL_ZOMBIE = register("spectral_zombie",
			EntityType.Builder.<SpectralZombieEntity>of(SpectralZombieEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<EnchantedZombieEntity>> ENCHANTED_ZOMBIE = register("enchanted_zombie",
			EntityType.Builder.<EnchantedZombieEntity>of(EnchantedZombieEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<MagmaticZombieEntity>> MAGMATIC_ZOMBIE = register("magmatic_zombie",
			EntityType.Builder.<MagmaticZombieEntity>of(MagmaticZombieEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<SpiderHydraEntity>> SPIDER_HYDRA = register("spider_hydra",
			EntityType.Builder.<SpiderHydraEntity>of(SpiderHydraEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.2f, 0.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<IncendiarySpiderEntity>> INCENDIARY_SPIDER = register("incendiary_spider",
			EntityType.Builder.<IncendiarySpiderEntity>of(IncendiarySpiderEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(1.2f, 0.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<VenomousSpiderEntity>> VENOMOUS_SPIDER = register("venomous_spider",
			EntityType.Builder.<VenomousSpiderEntity>of(VenomousSpiderEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.2f, 0.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<IronGiantEntity>> IRON_GIANT = register("iron_giant",
			EntityType.Builder.<IronGiantEntity>of(IronGiantEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, registryname))));
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
		PossessedArmorEntity.init(event);
		SporeSpreaderEntity.init(event);
		FrostyZombieEntity.init(event);
		OvergrownZombieEntity.init(event);
		CorruptedZombieEntity.init(event);
		CalcifiedZombieEntity.init(event);
		CactusZombieEntity.init(event);
		BloodZombieEntity.init(event);
		PumpkinZombieEntity.init(event);
		SpectralZombieEntity.init(event);
		EnchantedZombieEntity.init(event);
		MagmaticZombieEntity.init(event);
		SpiderHydraEntity.init(event);
		IncendiarySpiderEntity.init(event);
		VenomousSpiderEntity.init(event);
		IronGiantEntity.init(event);
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
		event.put(POSSESSED_ARMOR.get(), PossessedArmorEntity.createAttributes().build());
		event.put(SPORE_SPREADER.get(), SporeSpreaderEntity.createAttributes().build());
		event.put(FROSTY_ZOMBIE.get(), FrostyZombieEntity.createAttributes().build());
		event.put(OVERGROWN_ZOMBIE.get(), OvergrownZombieEntity.createAttributes().build());
		event.put(CORRUPTED_ZOMBIE.get(), CorruptedZombieEntity.createAttributes().build());
		event.put(CALCIFIED_ZOMBIE.get(), CalcifiedZombieEntity.createAttributes().build());
		event.put(CACTUS_ZOMBIE.get(), CactusZombieEntity.createAttributes().build());
		event.put(BLOOD_ZOMBIE.get(), BloodZombieEntity.createAttributes().build());
		event.put(PUMPKIN_ZOMBIE.get(), PumpkinZombieEntity.createAttributes().build());
		event.put(SPECTRAL_ZOMBIE.get(), SpectralZombieEntity.createAttributes().build());
		event.put(ENCHANTED_ZOMBIE.get(), EnchantedZombieEntity.createAttributes().build());
		event.put(MAGMATIC_ZOMBIE.get(), MagmaticZombieEntity.createAttributes().build());
		event.put(SPIDER_HYDRA.get(), SpiderHydraEntity.createAttributes().build());
		event.put(INCENDIARY_SPIDER.get(), IncendiarySpiderEntity.createAttributes().build());
		event.put(VENOMOUS_SPIDER.get(), VenomousSpiderEntity.createAttributes().build());
		event.put(IRON_GIANT.get(), IronGiantEntity.createAttributes().build());
	}
}