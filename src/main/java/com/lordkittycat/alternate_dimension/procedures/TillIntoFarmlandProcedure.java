package com.lordkittycat.alternate_dimension.procedures;

import com.github.lordzintick.modular.ModularItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class TillIntoFarmlandProcedure {
	public static InteractionResult execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity instanceof Player player) {
			ItemStack stack = player.getMainHandItem();
			if (!stack.isEmpty()) {
				if (ModularItem.isHoeTool(stack)) {
					world.setBlock(BlockPos.containing(x, y, z), AlternateDimensionModBlocks.RICH_FARMLAND.get().defaultBlockState(), 3);

					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("item.hoe.till")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("item.hoe.till")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
					if (world instanceof ServerLevel _level) {
						player.getMainHandItem().hurtAndBreak(1, _level, player, _ -> {
						});
					}

					return InteractionResult.SUCCESS;
				}
			}
		}
		return InteractionResult.PASS;
	}
}