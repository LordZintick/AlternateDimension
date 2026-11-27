package com.lordkittycat.alternate_dimension.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.List;

@JeiPlugin
public class AlternateDimensionModJeiInformation implements IModPlugin {
	@Override
	public Identifier getPluginUid() {
		return Identifier.parse("alternate_dimension:information");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addIngredientInfo(
				List.of(new ItemStack(AlternateDimensionModBlocks.FUSER.get()), new ItemStack(AlternateDimensionModBlocks.PHOTOLITHOGRAPHIC_MICROFABRICATOR.get()), new ItemStack(AlternateDimensionModBlocks.COMPRESSOR.get()),
						new ItemStack(AlternateDimensionModBlocks.MANA_MYSTIFIER.get()), new ItemStack(AlternateDimensionModBlocks.FUNGAL_PROCESSOR.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.alternate_dimension.basic_machine_info"));
		registration.addIngredientInfo(List.of(new ItemStack(AlternateDimensionModBlocks.MANA_PROVIDER.get()), new ItemStack(AlternateDimensionModBlocks.MANA_TANK.get()), new ItemStack(AlternateDimensionModBlocks.MANA_SYNTHESIZER.get())),
				VanillaTypes.ITEM_STACK, Component.translatable("jei.alternate_dimension.mana_logistics_info"));
	}
}