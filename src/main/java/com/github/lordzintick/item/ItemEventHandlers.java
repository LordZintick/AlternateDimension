package com.github.lordzintick.item;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.modular.GearMaterial;
import com.github.lordzintick.modular.GearPartsComponent;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = AlternateDimensionMod.MODID)
public class ItemEventHandlers {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;

        Inventory inventory = player.getInventory();
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            ItemStack stack = inventory.getItem(slot);
            if (stack.isEmpty()) continue;

            if (stack.has(AlternateDimensionModComponents.PHOTOSYNTHETIC)) {
                PhotosyntheticComponent data = stack.get(AlternateDimensionModComponents.PHOTOSYNTHETIC);
                int maxDamage = stack.getMaxDamage();
                int damage = stack.getDamageValue();

                long currentTime = player.level().getDefaultClockTime() % 24000L;
                assert data != null;
                if (data.minTime() <= currentTime && data.maxTime() >= currentTime && damage > 0 && maxDamage > 0) {
                    if (data.requiresSkyView() && !player.level().canSeeSky(player.blockPosition())) return;

                    int wholeHealing = (int) (data.factor() - data.factor() % 1);
                    stack.setDamageValue(damage - wholeHealing);
                    if (Math.random() <= data.factor() % 1) {
                        stack.setDamageValue(damage - 1);
                    }
                }
            } else if (stack.has(AlternateDimensionModComponents.PERMANENT_EFFECTS)) {
                PermanentEffectsComponent comp = stack.get(AlternateDimensionModComponents.PERMANENT_EFFECTS);
                assert comp != null;
                comp.apply(stack, player);
            }
        }
    }

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (!left.is(AlternateDimensionModItems.MODULAR_ITEM.get()) || !left.isDamaged() || right.isEmpty()) {
            return;
        }

        GearPartsComponent partsComp = left.get(AlternateDimensionModComponents.GEAR_PARTS.get());
        if (partsComp == null || partsComp.parts().size() <= 1) {
            return;
        }

        GearMaterial primaryMaterial = partsComp.materialsByZIndex().stream()
                .filter(e -> e.getKey().value().contributeMaterialName())
                .map(e -> e.getValue().value())
                .findFirst()
                .orElseGet(() -> partsComp.materialsByZIndex().getLast().getValue().value());

        if (!primaryMaterial.ingredient().test(right)) {
            return;
        }

        int maxDamage = left.getMaxDamage();
        int currentDamage = left.getDamageValue();
        int repairPerItem = Math.max(1, maxDamage / 4);

        int itemsNeeded = (int) Math.ceil((double) currentDamage / repairPerItem);
        int itemsUsed = Math.min(right.getCount(), itemsNeeded);

        if (itemsUsed <= 0) return;

        ItemStack output = left.copy();
        output.setDamageValue(Math.max(0, currentDamage - (itemsUsed * repairPerItem)));

        String newName = event.getName();
        int xpCost = itemsUsed;
        if (newName != null && !newName.isBlank() && !newName.equals(left.getHoverName().getString())) {
            output.set(DataComponents.CUSTOM_NAME, Component.literal(newName));
            xpCost += 1;
        } else if (newName == null || newName.isBlank()) {
            output.remove(DataComponents.CUSTOM_NAME);
        }

        event.setOutput(output);
        event.setMaterialCost(itemsUsed);
        event.setXpCost(xpCost);
    }

    @SubscribeEvent
    public static void entityHitWithItem(LivingDamageEvent.Pre event) {
        LivingEntity entity = event.getEntity();

        ItemStack stack = event.getSource().getWeaponItem();
        if (stack == null) return;
        if (stack.has(AlternateDimensionModComponents.SPARKING)) {
            stack.get(AlternateDimensionModComponents.SPARKING).apply(entity);
        }
    }
}
