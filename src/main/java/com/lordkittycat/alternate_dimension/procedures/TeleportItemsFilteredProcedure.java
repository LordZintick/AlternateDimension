package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import java.util.Comparator;
import java.util.ArrayList;

public class TeleportItemsFilteredProcedure {
	public static void execute(LevelAccessor world, ArrayList filter, boolean blacklist, double inX, double inY, double inZ, double outX, double outY, double outZ) {
		if (filter == null)
			return;
		if (world.isClientSide()) {
			return;
		}
		{
			final Vec3 _center = new Vec3((inX + 0.5), (inY + 0.5), (inZ + 0.5));
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
				if (entityiterator instanceof ItemEntity && (!blacklist && filter.contains((BuiltInRegistries.ITEM.getKey((entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getItem()).toString()))
						|| blacklist && !filter.contains((BuiltInRegistries.ITEM.getKey((entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getItem()).toString())))) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(inX, inY, inZ), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.item.pickup")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(inX, inY, inZ, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.item.pickup")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
					{
						Entity _ent = entityiterator;
						double _tx = (outX + 0.5);
						double _ty = (outY + 0.5);
						double _tz = (outZ + 0.5);
						_ent.teleportTo(_tx, _ty, _tz);
						if (_ent instanceof ServerPlayer _serverPlayer)
							_serverPlayer.connection.teleport(_tx, _ty, _tz, _ent.getYRot(), _ent.getXRot());
					}
				}
			}
		}
	}
}