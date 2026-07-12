package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.*;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.client.multiplayer.ClientLevel;

import javax.annotation.Nullable;

import java.util.function.Consumer;

import com.mojang.serialization.MapCodec;

import com.lordkittycat.alternate_dimension.procedures.GetBowPullingStateProcedure;
import com.lordkittycat.alternate_dimension.procedures.GetBowPullingAmountProcedure;
import com.lordkittycat.alternate_dimension.entity.PlusProjectileEntity;

public class ScorchedBowItem extends Item {
	public ScorchedBowItem(Item.Properties properties) {
		super(properties.durability(244).enchantable(12));
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack itemstack) {
		return ItemUseAnimation.BOW;
	}

	@Override
	public int getUseDuration(ItemStack itemstack, LivingEntity livingEntity) {
		return 72000;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.alternate_dimension.scorched_bow.description_0"));
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = InteractionResult.FAIL;
		if (entity.getAbilities().instabuild || findAmmo(entity) != ItemStack.EMPTY) {
			ar = InteractionResult.CONSUME;
			entity.startUsingItem(hand);
		}
		return ar;
	}

	@Override
	public boolean releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int time) {
		if (!world.isClientSide() && entity instanceof ServerPlayer player) {
			float pullingPower = BowItem.getPowerForTime(this.getUseDuration(itemstack, player) - time);
			if (pullingPower < 0.1)
				return false;
			ItemStack stack = findAmmo(player);
			if (player.getAbilities().instabuild || stack != ItemStack.EMPTY) {
				PlusProjectileEntity projectile = PlusProjectileEntity.shoot(world, entity, world.getRandom(), pullingPower);
				itemstack.hurtAndBreak(1, entity, entity.getUsedItemHand().asEquipmentSlot());
				if (player.getAbilities().instabuild) {
					projectile.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
				} else {
					if (stack.isDamageableItem()) {
						if (world instanceof ServerLevel serverLevel)
							stack.hurtAndBreak(1, serverLevel, player, _stkprov -> {
							});
					} else {
						stack.shrink(1);
					}
				}
			}
		}
		return super.releaseUsing(itemstack, world, entity, time);
	}

	private ItemStack findAmmo(Player player) {
		ItemStack stack = ProjectileWeaponItem.getHeldProjectile(player, e -> e.getItem() == PlusProjectileEntity.PROJECTILE_ITEM.getItem());
		if (stack == ItemStack.EMPTY) {
			for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
				ItemStack teststack = player.getInventory().getItem(i);
				if (teststack != null && teststack.getItem() == PlusProjectileEntity.PROJECTILE_ITEM.getItem()) {
					stack = teststack;
					break;
				}
			}
		}
		return stack;
	}

	public record PullProperty() implements RangeSelectItemModelProperty {
		public static final MapCodec<PullProperty> MAP_CODEC = MapCodec.unit(new PullProperty());

		@Override
		public float get(ItemStack itemStackToRender, @Nullable ClientLevel clientWorld, @Nullable ItemOwner owner, int seed) {
			return (float) GetBowPullingAmountProcedure.execute(owner.asLivingEntity(), itemStackToRender);
		}

		@Override
		public MapCodec<PullProperty> type() {
			return MAP_CODEC;
		}
	}

	public record PullingProperty() implements RangeSelectItemModelProperty {
		public static final MapCodec<PullingProperty> MAP_CODEC = MapCodec.unit(new PullingProperty());

		@Override
		public float get(ItemStack itemStackToRender, @Nullable ClientLevel clientWorld, @Nullable ItemOwner owner, int seed) {
			return (float) GetBowPullingStateProcedure.execute(owner.asLivingEntity(), itemStackToRender);
		}

		@Override
		public MapCodec<PullingProperty> type() {
			return MAP_CODEC;
		}
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}