package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

public class GrowRandomTreeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double rand = 0;
		world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
		rand = Mth.nextDouble(RandomSource.create(), 0, 5);
		if (rand <= 1) {
			if (world instanceof ServerLevel _level)
				_level.holderOrThrow(ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.parse("alternate_dimension:lush_forest_tree"))).value().place(_level, _level.getChunkSource().getGenerator(), _level.getRandom(),
						BlockPos.containing(x, y, z));
		} else if (rand <= 2) {
			if (world instanceof ServerLevel _level)
				_level.holderOrThrow(ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.parse("alternate_dimension:azure_tree"))).value().place(_level, _level.getChunkSource().getGenerator(), _level.getRandom(),
						BlockPos.containing(x, y, z));
		} else if (rand <= 3) {
			if (world instanceof ServerLevel _level)
				_level.holderOrThrow(ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.parse("alternate_dimension:willow_tree"))).value().place(_level, _level.getChunkSource().getGenerator(), _level.getRandom(),
						BlockPos.containing(x, y, z));
		} else if (rand <= 4) {
			if (world instanceof ServerLevel _level)
				_level.holderOrThrow(ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.parse("alternate_dimension:fruit_tree"))).value().place(_level, _level.getChunkSource().getGenerator(), _level.getRandom(),
						BlockPos.containing(x, y, z));
		} else if (rand <= 5) {
			if (world instanceof ServerLevel _level)
				_level.holderOrThrow(ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.parse("alternate_dimension:solar_tree"))).value().place(_level, _level.getChunkSource().getGenerator(), _level.getRandom(),
						BlockPos.containing(x, y, z));
		}
	}
}