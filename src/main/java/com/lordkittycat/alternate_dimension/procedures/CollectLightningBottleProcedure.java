package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

public class CollectLightningBottleProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == AlternateDimensionModItems.BIG_BOTTLE.get()) {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(AlternateDimensionModItems.LIGHTNING_BOTTLE.get()).copy();
				_setstack.setCount(1);
				_player.getInventory().placeItemBackInInventory(_setstack);
			}
		}
	}
}