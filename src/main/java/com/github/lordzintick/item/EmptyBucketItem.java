package com.github.lordzintick.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.common.SoundAction;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.registries.DeferredItem;

public class EmptyBucketItem extends Item {
    private final DeferredItem<Item> waterBucket;
    private final DeferredItem<Item> lavaBucket;

    public EmptyBucketItem(Properties props, DeferredItem<Item> waterBucket, DeferredItem<Item> lavaBucket) {
        super(props);
        this.waterBucket = waterBucket;
        this.lavaBucket = lavaBucket;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);

        if (blockHitResult.getType() == HitResult.Type.MISS || blockHitResult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemStack);
        }

        BlockPos pos = blockHitResult.getBlockPos();
        BlockState state = level.getBlockState(pos);

        // Check if the block can be picked up by a bucket (implements BucketPickup)
        if (state.getBlock() instanceof BucketPickup bucketPickup) {
            Fluid fluid = level.getFluidState(pos).getType();

            // Determine which filled bucket to give the player based on the fluid
            ItemStack filledBucket = getFilledBucketFor(fluid);

            if (!filledBucket.isEmpty()) {
                // Remove the fluid block from the world and get the item stack
                ItemStack resultStack = bucketPickup.pickupBlock(player, level, pos, state);

                if (!resultStack.isEmpty()) {
                    player.awardStat(Stats.ITEM_USED.get(this));

                    // Play the fluid pickup sound
                    SoundEvent sound = fluid.getFluidType().getSound(SoundActions.BUCKET_FILL);
                    if (sound == null) sound = SoundEvents.BUCKET_FILL;

                    player.playSound(sound, 1.0F, 1.0F);
                    level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);

                    if (player instanceof ServerPlayer serverPlayer) {
                        CriteriaTriggers.FILLED_BUCKET.trigger(serverPlayer, filledBucket);
                    }

                    // Handles creative mode vs survival mode item consumption
                    ItemStack remainingStack = ItemUtils.createFilledResult(itemStack, player, filledBucket);
                    return InteractionResultHolder.sidedSuccess(remainingStack, level.isClientSide());
                }
            }
        }

        return InteractionResultHolder.pass(itemStack);
    }

    // Helper method matching your fluids to your custom filled buckets
    private ItemStack getFilledBucketFor(Fluid fluid) {
        if (fluid == Fluids.WATER) {
            return new ItemStack(waterBucket.get());
        } else if (fluid == Fluids.LAVA) {
            return new ItemStack(lavaBucket.get());
        }

        return ItemStack.EMPTY;
    }
}
