package com.github.lordzintick.item.backpack;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public record BackpackInventoryComponent(int slots, List<ItemStack> items) {
    public static final Codec<BackpackInventoryComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ExtraCodecs.intRange(1, 54).fieldOf("slots").forGetter(BackpackInventoryComponent::slots),
            ItemStack.OPTIONAL_CODEC.listOf().optionalFieldOf("items", List.of()).forGetter(BackpackInventoryComponent::items)
    ).apply(instance, BackpackInventoryComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BackpackInventoryComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, BackpackInventoryComponent::slots,
            ItemStack.OPTIONAL_LIST_STREAM_CODEC, BackpackInventoryComponent::items,
            BackpackInventoryComponent::new
    );

    public BackpackInventoryComponent(int slots) {
        this(slots, List.of());
    }

    public BackpackInventoryComponent {
        List<ItemStack> copy = new ArrayList<>(items.size());
        for (ItemStack stack : items) {
            copy.add(stack.copy());
        }
        items = List.copyOf(copy);
    }

    public int occupiedSlots() {
        int count = 0;
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public NonNullList<ItemStack> copyItemsToList() {
        NonNullList<ItemStack> list = NonNullList.withSize(slots, ItemStack.EMPTY);
        int limit = Math.min(slots, items.size());
        for (int i = 0; i < limit; i++) {
            list.set(i, items.get(i).copy());
        }
        return list;
    }

    public BackpackInventoryComponent withItems(NonNullList<ItemStack> newItems) {
        int lastNonEmpty = -1;
        for (int i = newItems.size() - 1; i >= 0; i--) {
            if (!newItems.get(i).isEmpty()) {
                lastNonEmpty = i;
                break;
            }
        }
        if (lastNonEmpty < 0) {
            return new BackpackInventoryComponent(slots, List.of());
        }
        List<ItemStack> trimmed = new ArrayList<>(lastNonEmpty + 1);
        for (int i = 0; i <= lastNonEmpty; i++) {
            trimmed.add(newItems.get(i).copy());
        }
        return new BackpackInventoryComponent(slots, trimmed);
    }

    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BackpackInventoryComponent(int slots1, List<ItemStack> items1))) return false;
        return this.slots == slots1 && ItemStack.listMatches(this.items, items1);
    }

    @Override
    public int hashCode() {
        return 31 * slots + ItemStack.hashStackList(items);
    }
}
