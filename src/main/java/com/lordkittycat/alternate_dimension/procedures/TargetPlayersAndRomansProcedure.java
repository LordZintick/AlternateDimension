package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.Comparator;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class TargetPlayersAndRomansProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			final Vec3 _center = new Vec3(x, y, z);
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(16 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
				if ((BuiltInRegistries.ENTITY_TYPE.getKey(entityiterator.getType()).toString()).equals("minecraft:player")) {
					if (entityiterator instanceof Player _plr1 && _plr1.gameMode() == GameType.SURVIVAL && entityiterator.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).viking_reputation < 0) {
						if ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null) {
							if (entity instanceof Mob _entity && entityiterator instanceof LivingEntity _ent)
								_entity.setTarget(_ent);
						} else {
							if ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).is(TagKey.create(Registries.ENTITY_TYPE, Identifier.parse("alternate_dimension:romans")))) {
								if (entity instanceof Mob _entity && entityiterator instanceof LivingEntity _ent)
									_entity.setTarget(_ent);
							} else if ((entity.position()).distanceTo((entityiterator.position())) <= (entity.position()).distanceTo(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).position()))) {
								if (entity instanceof Mob _entity && entityiterator instanceof LivingEntity _ent)
									_entity.setTarget(_ent);
							}
						}
					}
				} else if (entityiterator.is(TagKey.create(Registries.ENTITY_TYPE, Identifier.parse("alternate_dimension:romans")))) {
					if ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null) {
						if (entity instanceof Mob _entity && entityiterator instanceof LivingEntity _ent)
							_entity.setTarget(_ent);
					} else if ((entity.position()).distanceTo((entityiterator.position())) <= (entity.position()).distanceTo(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).position()))) {
						if (entity instanceof Mob _entity && entityiterator instanceof LivingEntity _ent)
							_entity.setTarget(_ent);
					}
				}
			}
		}
	}
}