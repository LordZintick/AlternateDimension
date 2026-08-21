package com.github.lordzintick.init;

import com.github.lordzintick.item.ring.RingEffectComponent;
import com.github.lordzintick.magic.ScrollRecipe;
import com.github.lordzintick.magic.WandRecipe;
import com.github.lordzintick.magic.loader.SpellLoader;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = AlternateDimensionMod.MODID)
public class BuildCreativeModeTabsEventHook {
    @SubscribeEvent
    public static void buildTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().identifier().getPath().equals("alternate_dimension_equipment")) {
            SpellLoader.SPELLS.forEach(spell -> {
                ItemStack wand = WandRecipe.createWand(spell.id);
                ItemStack scroll = ScrollRecipe.createScroll(spell.id);
                event.accept(wand);
                event.accept(scroll);
            });

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
        }
    }
}
