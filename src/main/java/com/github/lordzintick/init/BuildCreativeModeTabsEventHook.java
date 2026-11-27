package com.github.lordzintick.init;

import com.github.lordzintick.modular.blueprint.BlueprintDataComponent;
import com.github.lordzintick.modular.parts.GearMaterial;
import com.github.lordzintick.modular.parts.GearPartType;
import com.github.lordzintick.modular.parts.GearPartsComponent;
import com.github.lordzintick.modular.util.ComponentMerger;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = AlternateDimensionMod.MODID)
public class BuildCreativeModeTabsEventHook {
    @SubscribeEvent
    public static void buildTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().identifier().getPath().equals("alternate_dimension_equipment")) {
            populateModularGear(event);
        }
    }

    public static void populateModularGear(BuildCreativeModeTabContentsEvent event) {
        HolderLookup.Provider registries = event.getParameters().holders();

        var partLookupOpt = registries.lookup(AlternateDimensionModRegistries.PART_TYPE_KEY);
        var matLookupOpt = registries.lookup(AlternateDimensionModRegistries.MATERIAL_KEY);
        if (partLookupOpt.isEmpty() || matLookupOpt.isEmpty()) return;

        List<Holder.Reference<GearPartType>> allParts = partLookupOpt.get().listElements().toList();
        List<Holder.Reference<GearMaterial>> allMaterials = matLookupOpt.get().listElements().toList();
        if (allParts.isEmpty() || allMaterials.isEmpty()) return;

        for (Holder.Reference<GearPartType> partHolder : allParts) {
            partHolder.value().pattern().ifPresent(pattern -> {
                ItemStack blueprintStack = new ItemStack(AlternateDimensionModItems.BLUEPRINT.get());
                blueprintStack.set(
                        AlternateDimensionModComponents.BLUEPRINT_DATA.get(),
                        new BlueprintDataComponent(partHolder, pattern.materialCost())
                );
                event.accept(blueprintStack);
            });
        }

        for (Holder.Reference<GearPartType> partHolder : allParts) {
            GearPartType part = partHolder.value();
            for (Holder.Reference<GearMaterial> matHolder : allMaterials) {
                if (!part.allowsMaterial(matHolder)) continue;

                ItemStack partStack = new ItemStack(AlternateDimensionModItems.MODULAR_ITEM.get());
                ComponentMerger.applyMergedComponents(partStack, new GearPartsComponent(Map.of(partHolder,
                        new GearPartsComponent.GearPart(matHolder, (int) (partHolder.value().baseDurability() * matHolder.value().durabilityMultiplier())))));
                event.accept(partStack);
            }
        }
    }
}
