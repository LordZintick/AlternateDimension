package com.github.lordzintick.init;

import com.github.lordzintick.item.ring.RingEffectComponent;
import com.github.lordzintick.magic.ScrollRecipe;
import com.github.lordzintick.magic.WandRecipe;
import com.github.lordzintick.magic.loader.SpellLoader;
import com.github.lordzintick.modular.*;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@EventBusSubscriber(modid = AlternateDimensionMod.MODID)
public class BuildCreativeModeTabsEventHook {
    @SubscribeEvent
    public static void buildTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().identifier().getPath().equals("alternate_dimension_equipment")) {
            populateModularGear(event);

            BuiltInRegistries.MOB_EFFECT.forEach(effect -> {
                ItemStack wooden = AlternateDimensionModItems.WOODEN_RING.toStack();
                wooden.set(AlternateDimensionModComponents.RING_EFFECT, new RingEffectComponent(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect)));
                ItemStack aluminum = AlternateDimensionModItems.ALUMINUM_RING.toStack();
                aluminum.set(AlternateDimensionModComponents.RING_EFFECT, new RingEffectComponent(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect)));
                ItemStack titanium = AlternateDimensionModItems.TITANIUM_RING.toStack();
                titanium.set(AlternateDimensionModComponents.RING_EFFECT, new RingEffectComponent(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect)));
                ItemStack mythril = AlternateDimensionModItems.MYTHRIL_RING.toStack();
                mythril.set(AlternateDimensionModComponents.RING_EFFECT, new RingEffectComponent(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect)));

                event.accept(wooden);
                event.accept(aluminum);
                event.accept(titanium);
                event.accept(mythril);
            });

            SpellLoader.SPELLS.forEach(spell -> {
                ItemStack wand = WandRecipe.createWand(spell.id);
                ItemStack scroll = ScrollRecipe.createScroll(spell.id);
                event.accept(wand);
                event.accept(scroll);
            });
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
