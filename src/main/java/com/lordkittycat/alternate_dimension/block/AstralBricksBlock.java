package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class AstralBricksBlock extends Block {
	public AstralBricksBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.TRIAL_SPAWNER).strength(-1, 3600000).lightLevel(blockstate -> 6).postProcess((bs, br, bp) -> bp).emissiveRendering((bs, br, bp) -> true));
	}

	public static class Item extends BlockItem {
		public Item(Item.Properties properties) {
			super(AlternateDimensionModBlocks.ASTRAL_BRICKS.get(), properties);
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
			componentConsumer.accept(Component.translatable("block.alternate_dimension.astral_bricks.description_0"));
		}
	}
}