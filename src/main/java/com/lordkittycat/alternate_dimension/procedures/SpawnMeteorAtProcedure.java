package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class SpawnMeteorAtProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		SpawnMeteorProcedure.execute(world, DoubleArgumentType.getDouble(arguments, "x"),
				world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) DoubleArgumentType.getDouble(arguments, "x"), (int) DoubleArgumentType.getDouble(arguments, "z")), DoubleArgumentType.getDouble(arguments, "z"));
	}
}