package com.github.lordzintick.modular.parts;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.github.lordzintick.modular.util.ComponentMerger;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.LegacyRandomSource;

import java.util.*;

public record GearPartsComponent(Map<Holder<GearPartType>, GearPart> parts) {
    public static final GearPartsComponent EMPTY = new GearPartsComponent(Map.of());

    public static final Codec<GearPartsComponent> CODEC = Codec.unboundedMap(
            RegistryFixedCodec.create(AlternateDimensionModRegistries.PART_TYPE_KEY),
            GearPart.CODEC
    ).xmap(GearPartsComponent::new, GearPartsComponent::parts);

    public static final StreamCodec<RegistryFriendlyByteBuf, GearPartsComponent> STREAM_CODEC = ByteBufCodecs.map(
            HashMap::new,
            ByteBufCodecs.holderRegistry(AlternateDimensionModRegistries.PART_TYPE_KEY),
            GearPart.STREAM_CODEC
    ).map(GearPartsComponent::new, comp -> new HashMap<>(comp.parts));

    public List<MaterialEntry> materialsByZIndex() {
        return parts.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getKey().value().zIndex()))
                .map(MaterialEntry::new)
                .toList();
    }

    public int getTotalDurability() {
        return parts.values().stream().mapToInt(GearPart::durability).sum();
    }

    public int getTotalMaxDurability() {
        return parts.entrySet().stream()
                .map(e -> Math.round(e.getKey().value().baseDurability() * e.getValue().material.value().durabilityMultiplier()))
                .mapToInt(Integer::intValue).sum();
    }

    public void damageBy(ItemStack stack, int amount) {
        RandomSource random = new LegacyRandomSource(System.currentTimeMillis());

        List<Holder<GearPartType>> gearPartTypes = parts.keySet().stream().toList();
        Optional<Holder<GearPartType>> target = gearPartTypes.stream()
                .filter(h -> parts.get(h).durability > 0)
                .max(Comparator.comparingDouble(h ->
                        Math.pow(random.nextDouble(), 1.0 / parts.get(h).durability)));
        if (target.isEmpty()) return;

        GearPart part = parts.get(target.get());
        GearPart newPart = new GearPart(part.material, part.durability - amount);
        HashMap<Holder<GearPartType>, GearPart> partsMap = new HashMap<>(this.parts);

        if (newPart.durability <= 0) {
            partsMap.remove(target.get());
        } else {
            partsMap.put(target.get(), newPart);
        }

        ComponentMerger.applyMergedComponents(stack, new GearPartsComponent(partsMap));
    }

    public void damagePartWithComponent(ItemStack stack, int amount, DataComponentType<?> type) {
        List<Holder<GearPartType>> partTypes = parts.keySet().stream().toList();
        Optional<Holder<GearPartType>> target = partTypes.stream()
                .filter(h -> h.value().components().entrySet().stream()
                        .anyMatch(e -> e.getKey() == type))
                .findFirst();

        target.ifPresent(partType -> {
            GearPart part = parts.get(partType);
            GearPart newPart = new GearPart(part.material, part.durability - amount);
            HashMap<Holder<GearPartType>, GearPart> partsMap = new HashMap<>(this.parts);

            if (newPart.durability <= 0) {
                partsMap.remove(partType);
            } else {
                partsMap.put(partType, newPart);
            }

            ComponentMerger.applyMergedComponents(stack, new GearPartsComponent(partsMap));
        });
    }

    public static final class MaterialEntry implements Map.Entry<Holder<GearPartType>, Holder<GearMaterial>> {
        private final Map.Entry<Holder<GearPartType>, GearPart> entry;

        public MaterialEntry(Map.Entry<Holder<GearPartType>, GearPart> entry) {
            this.entry = entry;
        }

        public int getMaxDurability() {
            return (int) (getKey().value().baseDurability() * getValue().value().durabilityMultiplier());
        }

        public int getDurability() {
            return entry.getValue().durability;
        }

        @Override
        public Holder<GearPartType> getKey() {
            return entry.getKey();
        }

        @Override
        public Holder<GearMaterial> getValue() {
            return entry.getValue().material;
        }

        @Override
        public Holder<GearMaterial> setValue(Holder<GearMaterial> value) {
            return null;
        }
    }

    public record GearPart(Holder<GearMaterial> material, int durability) {
        public static final Codec<GearPart> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                RegistryFixedCodec.create(AlternateDimensionModRegistries.MATERIAL_KEY).fieldOf("material").forGetter(GearPart::material),
                Codec.INT.fieldOf("durability").forGetter(GearPart::durability)
        ).apply(instance, GearPart::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, GearPart> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.holderRegistry(AlternateDimensionModRegistries.MATERIAL_KEY), GearPart::material,
                ByteBufCodecs.VAR_INT, GearPart::durability,
                GearPart::new
        );
    }
}


