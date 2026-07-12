package com.github.lordzintick.item.ring;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.util.ColorUtil;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.item.LightBlueGummyBearItem;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class RingItem extends Item {
    public static final HashMap<Identifier, Holder<MobEffect>> RING_INGREDIENTS = new HashMap<>();

    public RingItem(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        if (!stack.has(AlternateDimensionModComponents.RING_EFFECT)) {return super.getName(stack);}
        Holder<MobEffect> effectHolder = stack.get(AlternateDimensionModComponents.RING_EFFECT).effect();
        MobEffect effect = BuiltInRegistries.MOB_EFFECT.getOrThrow(effectHolder.getKey()).value();
        return super.getName(stack).copy()
                .append(
                        Component.literal(" [")
                                .append(Component.translatable(effect.getDescriptionId()))
                                .append(Component.literal("]"))
                                .withColor(effect.getColor())
                );
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        if (!itemStack.has(AlternateDimensionModComponents.RING_EFFECT)) return;
        builder.accept(Component.literal("Power: " + this.getPower()).withColor(getPowerColor(this.getPower())));
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
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        super.inventoryTick(itemStack, level, owner, slot);

        if (owner instanceof ServerPlayer player) {
            ItemStack off = player.getOffhandItem();
            ItemStack main = player.getMainHandItem();
            if (off.has(AlternateDimensionModComponents.RING_EFFECT) && off.getItem() == itemStack.getItem()) {
                player.forceAddEffect(new MobEffectInstance(off.get(AlternateDimensionModComponents.RING_EFFECT).effect(), 320, this.getPower() - 1), null);
            }
            if (main.has(AlternateDimensionModComponents.RING_EFFECT) && main.getItem() == itemStack.getItem()) {
                player.forceAddEffect(new MobEffectInstance(main.get(AlternateDimensionModComponents.RING_EFFECT).effect(), 320, this.getPower() - 1), null);
            }
        }
    }

    static {
        RING_INGREDIENTS.put(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "light_blue_gummy_bear"), MobEffects.SPEED);
        RING_INGREDIENTS.put(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "green_gummy_bear"), MobEffects.JUMP_BOOST);
        RING_INGREDIENTS.put(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "blue_gummy_bear_item"), MobEffects.WATER_BREATHING);
        RING_INGREDIENTS.put(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "red_gummy_bear_item"), MobEffects.REGENERATION);
        RING_INGREDIENTS.put(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "orange_gummy_bear"), MobEffects.STRENGTH);
        RING_INGREDIENTS.put(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "yellow_gummy_bear"), MobEffects.HASTE);
        RING_INGREDIENTS.put(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "graphite"), MobEffects.RESISTANCE);
    }
}
