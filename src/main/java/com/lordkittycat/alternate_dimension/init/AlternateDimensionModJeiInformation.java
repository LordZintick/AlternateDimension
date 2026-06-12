package com.lordkittycat.alternate_dimension.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.List;

@JeiPlugin
public class AlternateDimensionModJeiInformation implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return ResourceLocation.parse("alternate_dimension:information");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addIngredientInfo(List.of(new ItemStack(AlternateDimensionModBlocks.GENERATOR.get()), new ItemStack(AlternateDimensionModBlocks.SOLAR_PANEL.get())), VanillaTypes.ITEM_STACK,
				Component.translatable("jei.alternate_dimension.generator_info"));
		registration.addIngredientInfo(List.of(new ItemStack(AlternateDimensionModItems.LIGHTNING_BOTTLE.get()), new ItemStack(AlternateDimensionModBlocks.LIGHTNING_BLOCK.get())), VanillaTypes.ITEM_STACK,
				Component.translatable("jei.alternate_dimension.lightning_bottle_info"));
		registration.addIngredientInfo(List.of(new ItemStack(AlternateDimensionModBlocks.COOLANT_RODS.get()), new ItemStack(AlternateDimensionModBlocks.NUCLEAR_REACTOR.get())), VanillaTypes.ITEM_STACK,
				Component.translatable("jei.alternate_dimension.nuclear_reactor_info"));
	}
}