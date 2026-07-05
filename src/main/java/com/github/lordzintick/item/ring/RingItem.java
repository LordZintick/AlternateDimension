package com.github.lordzintick.item.ring;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.util.ColorUtil;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.item.LightBlueGummyBearItem;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public abstract class RingItem extends Item {
    public static final HashMap<ResourceLocation, Holder<MobEffect>> RING_INGREDIENTS = new HashMap<>();

    public RingItem(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        if (!stack.has(AlternateDimensionModComponents.RING_EFFECT)) {return super.getName(stack);}
        Holder<MobEffect> effectHolder = stack.get(AlternateDimensionModComponents.RING_EFFECT).effect();
        MobEffect effect = BuiltInRegistries.MOB_EFFECT.getOrThrow(effectHolder.getKey());
        return super.getName(stack).copy()
                .append(
                        Component.literal(" [")
                                .append(Component.translatable(effect.getDescriptionId()))
                                .append(Component.literal("]"))
                                .withColor(effect.getColor())
                );
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> content, TooltipFlag flag) {
        if (!stack.has(AlternateDimensionModComponents.RING_EFFECT)) return;
        content.add(Component.literal("Power: " + this.getPower()).withColor(getPowerColor(this.getPower())));
    }

    private static int getPowerColor(int power) {
        return switch (power) {
            case 2 -> ColorUtil.fromRGB(255, 255, 0);
            case 3 -> ColorUtil.fromRGB(255, 128, 0);
            case 4 -> ColorUtil.fromRGB(255, 0, 0);
            default -> ColorUtil.fromRGB(255, 255, 255);
        };
    }

    protected abstract int getPower();

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (selected) {
            if (stack.has(AlternateDimensionModComponents.RING_EFFECT) && entity instanceof LivingEntity livingEntity) {
                livingEntity.forceAddEffect(new MobEffectInstance(stack.get(AlternateDimensionModComponents.RING_EFFECT).effect(), 320, this.getPower() - 1), null);
            }
        }
    }

    static {
        RING_INGREDIENTS.put(ResourceLocation.fromNamespaceAndPath(AlternateDimensionMod.MODID, "light_blue_gummy_bear"), MobEffects.MOVEMENT_SPEED);
    }
}
