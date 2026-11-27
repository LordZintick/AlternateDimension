package com.github.lordzintick.init;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class AlternateDimensionModTags {
    public static final class Items {
        public static final TagKey<Item> STORAGE_ITEMS = createTag("storage_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, name));
        }
    }
}
