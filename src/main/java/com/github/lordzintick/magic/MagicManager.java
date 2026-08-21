package com.github.lordzintick.magic;

import com.github.lordzintick.magic.loader.Spell;
import com.github.lordzintick.magic.loader.SpellLoader;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSource;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class MagicManager {
    public static boolean checkIfMagicalItem(ItemStack stack) {
        String name = Objects.requireNonNull(stack.getOrDefault(DataComponents.CUSTOM_NAME, Component.literal("")).getString());
        if (name.contains("Wand") || name.contains("Scroll")) {
            return stack.getOrDefault(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(List.of(), List.of(), List.of(), List.of())).floats().contains(750001f);
        }
        return false;
    }

    public static InteractionResult wandHandler(Player player, Level regWorld, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (regWorld instanceof ServerLevel world) {
            CustomModelData customModelData = Objects.requireNonNull(stack.get(DataComponents.CUSTOM_MODEL_DATA));
            boolean validSpell = SpellLoader.SPELLS.getByID(Identifier.parse(customModelData.strings().getFirst())) != null;
            if (validSpell) {
                Spell spell = SpellLoader.SPELLS.getByID(Identifier.parse(customModelData.strings().getFirst()));
                assert spell != null;
                stack.set(DataComponents.CUSTOM_NAME, Component.literal("Wand (" + spell.displayName + ") (" + spell.manaCost + " mana)").copy().withStyle(ChatFormatting.RESET, ChatFormatting.LIGHT_PURPLE));
                stack.set(DataComponents.LORE, new ItemLore(List.of(
                        Component.literal("A magical wand that can cast a certain spell!"),
                        Component.literal("This wand casts: "),
                        Component.literal(spell.displayName + " (costs " + spell.manaCost + " mana)"),
                        Component.literal(spell.description)
                )));
                if (!player.isCreative() && !player.isSpectator()) {
                    AttributeInstance mana = Objects.requireNonNull(player.getAttribute(Attributes.TEMPT_RANGE));
                    if (mana.getValue() >= spell.manaCost) {
                        mana.setBaseValue(mana.getBaseValue() - spell.manaCost);
                        return castSpell(player, world, stack, spell, customModelData);
                    } else {
                        AttributeInstance NEMInstance = Objects.requireNonNull(player.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE));
                        NEMInstance.setBaseValue(1);
                    }
                } else {
                    return castSpell(player, world, stack, spell, customModelData);
                }
            } else {
                stack.set(DataComponents.CUSTOM_NAME, Component.literal("Wand (Invalid spell)").copy().withStyle(ChatFormatting.RESET, ChatFormatting.LIGHT_PURPLE));
                stack.set(DataComponents.LORE, new ItemLore(List.of(
                        Component.literal("Invalid spell")
                )));
            }
        }
        return InteractionResult.PASS;
    }

    public static InteractionResult scrollHandler(Player player, Level regWorld, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (regWorld instanceof ServerLevel world) {
            CustomModelData customModelData = Objects.requireNonNull(stack.get(DataComponents.CUSTOM_MODEL_DATA));
            boolean validSpell = SpellLoader.SPELLS.getByID(Identifier.parse(customModelData.strings().getFirst())) != null;
            if (validSpell) {
                Spell spell = SpellLoader.SPELLS.getByID(Identifier.parse(customModelData.strings().getFirst()));
                assert spell != null;
                stack.set(DataComponents.CUSTOM_NAME, Component.literal("Scroll (" + spell.displayName + ") (" + spell.manaCost + " mana)").copy().withStyle(ChatFormatting.RESET, ChatFormatting.LIGHT_PURPLE));
                stack.set(DataComponents.LORE, new ItemLore(List.of(
                        Component.literal("A magical scroll that can cast a certain spell!"),
                        Component.literal("One-Time use"),
                        Component.literal("Scroll: "),
                        Component.literal(spell.displayName + " (costs " + spell.manaCost + " mana)"),
                        Component.literal(spell.description)
                )));
                if (!player.isCreative() && !player.isSpectator()) {
                    AttributeInstance mana = Objects.requireNonNull(player.getAttribute(Attributes.TEMPT_RANGE));
                    if (mana.getValue() >= spell.manaCost) {
                        mana.setBaseValue(mana.getBaseValue() - spell.manaCost);
                        stack.shrink(1);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOOK_PAGE_TURN, SoundSource.PLAYERS, 1f, 1f);
                        return castSpell(player, world, stack, spell, customModelData);
                    } else {
                        AttributeInstance NEMInstance = Objects.requireNonNull(player.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE));
                        NEMInstance.setBaseValue(1);
                    }
                } else {
                    stack.shrink(1);
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOOK_PAGE_TURN, SoundSource.PLAYERS, 1f, 1f);
                    return castSpell(player, world, stack, spell, customModelData);
                }
            } else {
                stack.set(DataComponents.CUSTOM_NAME, Component.literal("Scroll (Invalid spell)").copy().withStyle(ChatFormatting.RESET, ChatFormatting.LIGHT_PURPLE));
                stack.set(DataComponents.LORE, new ItemLore(List.of(
                        Component.literal("Invalid spell")
                )));
            }
        }
        return InteractionResult.PASS;
    }

    public static InteractionResult castSpell(Player player, ServerLevel world, ItemStack stack, Spell spell, CustomModelData customModelData) {
        assert spell != null;
        if (!player.isCreative() && !player.isSpectator()) {
            if (!player.getCooldowns().isOnCooldown(stack)) {
                player.getCooldowns().addCooldown(stack, (int) (spell.cooldown * 20));
                Spell.SpellParameterProvider parameterProvider = new Spell.SpellParameterProvider(player, stack);
                SpellLoader.SPELLS.castSpell(Identifier.parse(customModelData.strings().getFirst()), parameterProvider);
                return InteractionResult.SUCCESS;
            }
        } else {
            if (!player.getCooldowns().isOnCooldown(stack)) {
                player.getCooldowns().addCooldown(stack, 1);
                Spell.SpellParameterProvider parameterProvider = new Spell.SpellParameterProvider(player, stack);
                SpellLoader.SPELLS.castSpell(Identifier.parse(customModelData.strings().getFirst()), parameterProvider);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
