package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class UpdateCollectorProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double slot = 0;
		ItemStack item = ItemStack.EMPTY;
		ImportItemsFromConveyorProcedure.execute(world, x, y, z, getDirectionFromBlockState(blockstate), 0);
		item = (extractFromBlockInventory(world, BlockPos.containing(x, y, z), 0, 1, true)).copy();
		slot = 0;
		while (!canInsertInBlockInventory(world, BlockPos.containing(x + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepX(), y + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepY(),
				z + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepZ()), (int) slot, 1, item)) {
			slot = slot + 1;
			if (slot >= getBlockInventorySlotCount(world, BlockPos.containing(x + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepX(), y + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepY(),
					z + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepZ()))) {
				return;
			}
		}
		insertInBlockInventory(world, BlockPos.containing(x + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepX(), y + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepY(),
				z + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepZ()), (int) slot, 1, (extractFromBlockInventory(world, BlockPos.containing(x, y, z), 0, 1, false)), false);
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}

	private static ItemStack extractFromBlockInventory(LevelAccessor world, BlockPos pos, int slotId, int amount, boolean simulate) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null && slotId >= 0 && slotId < itemHandler.size()) {
				ItemResource extractedResource = itemHandler.getResource(slotId);
				ItemStack extractedStack = ItemStack.EMPTY;
				if (extractedResource.isEmpty() || amount < 0)
					return extractedStack;
				try (var tx = Transaction.openRoot()) {
					int extracted = itemHandler.extract(slotId, extractedResource, amount, tx);
					extractedStack = extractedResource.toStack(extracted);
					if (!simulate)
						tx.commit();
				}
				return extractedStack;
			}
		}
		return ItemStack.EMPTY;
	}

	public static boolean canInsertInBlockInventory(LevelAccessor world, BlockPos pos, int slotId, int amount, ItemStack itemstack) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null && slotId >= 0 && slotId < itemHandler.size()) {
				ItemStack inserted = itemstack.copy();
				inserted.setCount(amount);
				return ItemUtil.insertItemReturnRemaining(itemHandler, slotId, inserted, true, null).getCount() == 0;
			}
		}
		return false;
	}

	private static int getBlockInventorySlotCount(LevelAccessor world, BlockPos pos) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.size();
		}
		return 0;
	}

	private static int insertInBlockInventory(LevelAccessor world, BlockPos pos, int slotId, int amount, ItemStack itemstack, boolean simulate) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null && slotId >= 0 && slotId < itemHandler.size()) {
				ItemStack inserted = itemstack.copy();
				inserted.setCount(amount);
				return ItemUtil.insertItemReturnRemaining(itemHandler, slotId, inserted, simulate, null).getCount();
			}
		}
		return itemstack.getCount();
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