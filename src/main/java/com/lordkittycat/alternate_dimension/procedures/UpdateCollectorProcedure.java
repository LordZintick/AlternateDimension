package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.ArrayList;

public class UpdateCollectorProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double i = 0;
		double outX = 0;
		double outY = 0;
		double outZ = 0;
		ArrayList<Object> filter = new ArrayList<>();
		for (int _i1 = 0; _i1 < 10; _i1++) {
			filter.add((BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).getItem()).toString()));
			i = i + 1;
		}
		outX = x + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepX();
		outY = y + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepY();
		outZ = z + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepZ();
		if (world.getBlockEntity(new BlockPos((int) outX, (int) outY, (int) outZ)) == null) {
			TeleportItemsFilteredProcedure.execute(world, filter, getBlockNBTLogic(world, BlockPos.containing(x, y, z), "blacklist_mode"), x + (getDirectionFromBlockState(blockstate)).getStepX(),
					y + (getDirectionFromBlockState(blockstate)).getStepY(), z + (getDirectionFromBlockState(blockstate)).getStepZ(), outX, outY, outZ);
		} else {
			ImportItemsFromConveyorFilteredWirelessAutomaticProcedure.execute(world, x + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepX(), y + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepY(),
					z + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepZ(), filter, getDirectionFromBlockState(blockstate), getBlockNBTLogic(world, BlockPos.containing(x, y, z), "blacklist_mode"), x, y, z);
		}
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null)
				return ItemUtil.getStack(itemHandler, slot);
		}
		return ItemStack.EMPTY;
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}