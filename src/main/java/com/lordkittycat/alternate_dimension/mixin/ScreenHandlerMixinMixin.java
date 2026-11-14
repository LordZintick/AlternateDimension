package com.lordkittycat.alternate_dimension.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomModelData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(AbstractContainerMenu.class)
public abstract class ScreenHandlerMixinMixin {
    @Shadow
    @Final
    public NonNullList<Slot> slots;

    @Inject(at = @At("HEAD"), method = "clicked")
    public void onSlotClicked(int slotIndex, int button, ContainerInput containerInput, Player player, CallbackInfo ci) {
        if (slotIndex == 0 && containerInput == ContainerInput.PICKUP) {
            ItemStack stack = slots.get(slotIndex).getItem();
            if (stack.get(DataComponents.CUSTOM_MODEL_DATA) != null) {
                CustomModelData customModelData = Objects.requireNonNull(stack.get(DataComponents.CUSTOM_MODEL_DATA));
                if (customModelData.strings().contains("disenchanted")) {
                    stack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(customModelData.floats(), customModelData.flags(), List.of(), customModelData.colors()));
                    if (player instanceof ServerPlayer serverPlayer) {
                        if (serverPlayer.level() instanceof ServerLevel world) {
                            for (int i = 0; i < world.getRandom().nextIntBetweenInclusive(3, 6); i++) {
                                ExperienceOrb orb = new ExperienceOrb(world, player.getX(), player.getY(), player.getZ(), world.getRandom().nextIntBetweenInclusive(4, 10));
                                world.addFreshEntity(orb);
                            }
                            customModelData.strings().remove("disenchanted");
                        }
                    }
                }
            }
        }
    }
}