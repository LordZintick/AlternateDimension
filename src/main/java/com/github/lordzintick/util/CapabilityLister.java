package com.github.lordzintick.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.List;

public class CapabilityLister {

    // Record to store neighbor data
    public record NeighborCapability(BlockPos pos, Direction dir,
                                     IItemHandler itemHandler,
                                     IFluidHandler fluidHandler,
                                     IEnergyStorage energyHandler) {}

    public static List<NeighborCapability> findCapableNeighbors(Level level, BlockPos currentPos) {
        List<NeighborCapability> validNeighbors = new ArrayList<>();

        // 1. Loop through the 6 adjacent directions
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = currentPos.relative(dir);
            BlockState neighborState = level.getBlockState(neighborPos);

            // 2. Filter for your specific block type
            // e.g., if you are looking for a machine block:
            // if (!neighborState.is(ModBlocks.YOUR_TARGET_BLOCK.get())) continue;

            // 3. Query capabilities using NeoForge Capabilities
            // Note: Direction passed in queries the *facing side* of the neighbor.
            // We pass dir.getOpposite() because we are targeting the side facing *us*.
            Direction queryDir = dir.getOpposite();

            IItemHandler itemCap = level.getCapability(Capabilities.ItemHandler.BLOCK, neighborPos, queryDir);
            IFluidHandler fluidCap = level.getCapability(Capabilities.FluidHandler.BLOCK, neighborPos, queryDir);
            IEnergyStorage energyCap = level.getCapability(Capabilities.EnergyStorage.BLOCK, neighborPos, queryDir);

            // 4. Store only if at least one capability is present
            if (itemCap != null || fluidCap != null || energyCap != null) {
                validNeighbors.add(new NeighborCapability(neighborPos, dir, itemCap, fluidCap, energyCap));
            }
        }

        return validNeighbors;
    }
}
