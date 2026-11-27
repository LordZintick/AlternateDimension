package com.lordkittycat.alternate_dimension.procedures;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.github.lordzintick.modular.blueprint.BlueprintDataComponent;
import com.github.lordzintick.modular.parts.GearPartType;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class OpenStarterBlueprintBundleProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		itemstack.shrink(1);
		if (entity instanceof Player player) {
			Consumer<ItemStack> add = player.getInventory()::add;
			Registry<GearPartType> partTypeRegistry = player.level().registryAccess().lookupOrThrow(AlternateDimensionModRegistries.PART_TYPE_KEY);
			Function<String, Holder.Reference<GearPartType>> getPartType = id -> partTypeRegistry.getOrThrow(ResourceKey.create(partTypeRegistry.key(), Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, id)));
			BiFunction<Holder.Reference<GearPartType>, Integer, ItemStack> getBlueprint = (partType, materialCost) -> {
				ItemStack stack = AlternateDimensionModItems.BLUEPRINT.toStack();
				stack.set(AlternateDimensionModComponents.BLUEPRINT_DATA, new BlueprintDataComponent(partType, materialCost));
				return stack;
			};

			add.accept(getBlueprint.apply(getPartType.apply("pickaxe_head"), 3));
			add.accept(getBlueprint.apply(getPartType.apply("axe_head"), 3));
			add.accept(getBlueprint.apply(getPartType.apply("shovel_head"), 1));
			add.accept(getBlueprint.apply(getPartType.apply("sword_head"), 2));
			add.accept(getBlueprint.apply(getPartType.apply("hoe_head"), 2));
			add.accept(getBlueprint.apply(getPartType.apply("tool_rod"), 2));
		}
	}
}