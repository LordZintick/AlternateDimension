package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

public class CheckForFuzzySocksProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == AlternateDimensionModItems.FUZZY_ARMOR_BOOTS.get();
	}
}