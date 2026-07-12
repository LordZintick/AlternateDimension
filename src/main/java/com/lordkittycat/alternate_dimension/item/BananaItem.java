package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.client.multiplayer.ClientLevel;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import com.lordkittycat.alternate_dimension.procedures.PeelBananaProcedure;
import com.lordkittycat.alternate_dimension.procedures.GetBananaPeeledStateProcedure;

public class BananaItem extends Item {
	public BananaItem(Item.Properties properties) {
		super(properties.stacksTo(1));
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack itemstack) {
		return ItemUseAnimation.EAT;
	}

	@Override
	public int getUseDuration(ItemStack itemstack, LivingEntity livingEntity) {
		return 64;
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		entity.startUsingItem(hand);
		return ar;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		PeelBananaProcedure.execute(entity, itemstack);
		return retval;
	}

	public record PeeledProperty() implements RangeSelectItemModelProperty {
		public static final MapCodec<PeeledProperty> MAP_CODEC = MapCodec.unit(new PeeledProperty());

		@Override
		public float get(ItemStack itemStackToRender, @Nullable ClientLevel clientWorld, @Nullable ItemOwner owner, int seed) {
			return (float) GetBananaPeeledStateProcedure.execute(itemStackToRender);
		}

		@Override
		public MapCodec<PeeledProperty> type() {
			return MAP_CODEC;
		}
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}