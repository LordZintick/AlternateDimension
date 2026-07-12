package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;

public class SwitchToCurrentDimensionProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		AlternateDimensionModVariables.PlayerVariables vars = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES);
		String planet = vars.currentPlanet;
		BlockPos spawnPos = new BlockPos((int) vars.spawnX, (int) vars.spawnY, (int) vars.spawnZ);
		if (entity instanceof ServerPlayer player) {
			ServerLevel destination = player.level().getServer().getLevel(ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, planet)));
			if (destination != null) {
				if (player.level().dimension() == destination.dimension()) return;
				player.teleport(new TeleportTransition(
						destination, spawnPos.getCenter(), Vec3.ZERO, 0, 0, TeleportTransition.DO_NOTHING
				));
			}
		}
	}
}