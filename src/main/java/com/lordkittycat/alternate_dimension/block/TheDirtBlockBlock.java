package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import java.util.function.Consumer;

import com.lordkittycat.alternate_dimension.procedures.CancelItemstackConsumptionProcedure;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class TheDirtBlockBlock extends Block {
	public TheDirtBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.GRAVEL).strength(1f, 10f).postProcess((bs, br, bp) -> bp).emissiveRendering((bs, br, bp) -> true));
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);
		CancelItemstackConsumptionProcedure.execute(itemstack);
	}

	public static class Item extends BlockItem {
		public Item(Item.Properties properties) {
			super(AlternateDimensionModBlocks.THE_DIRT_BLOCK.get(), properties);
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
			componentConsumer.accept(Component.translatable("block.alternate_dimension.the_dirt_block.description_0"));
			componentConsumer.accept(Component.translatable("block.alternate_dimension.the_dirt_block.description_1"));
			componentConsumer.accept(Component.translatable("block.alternate_dimension.the_dirt_block.description_2"));
		}
	}
}