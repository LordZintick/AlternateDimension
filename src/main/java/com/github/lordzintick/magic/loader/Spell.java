package com.github.lordzintick.magic.loader;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Spell implements StringConvertable {
    public final String displayName;
    public final float cooldown;
    public final String description;
    public final ArrayList<Ingredient> ingredients;
    public final ArrayList<SpellAction> actions;
    public final int manaCost;
    public Identifier id;
    public final String particleEffect;

    public Spell(String displayName, float cooldown, String description, List<Ingredient> ingredients, List<SpellAction> actions, int manaCost) {
        this.displayName = displayName;
        this.cooldown = cooldown;
        this.description = description;
        this.ingredients = new ArrayList<>(ingredients);
        this.actions = new ArrayList<>(actions);
        this.manaCost = manaCost;
        this.id = Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, displayName.toLowerCase().replace(" ", "-"));
        this.particleEffect = null;
    }

    public Spell(String displayName, float cooldown, String description, List<Ingredient> ingredients, List<SpellAction> actions, int manaCost, String particleEffect) {
        this.displayName = displayName;
        this.cooldown = cooldown;
        this.description = description;
        this.ingredients = new ArrayList<>(ingredients);
        this.actions = new ArrayList<>(actions);
        this.manaCost = manaCost;
        this.id = Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, displayName.toLowerCase().replace(" ", "-"));
        this.particleEffect = particleEffect;
    }

    public record SpellParameterProvider(Player player, ItemStack stack) {
    }

    public void cast(SpellParameterProvider parameterProvider) {
        AlternateDimensionMod.LOGGER.info("Casting spell {}!", displayName);
        for (SpellAction action : actions) {
            action.execute(parameterProvider);
        }
    }

    @Override
    public String asString() {
        return "Spell: {\"displayName\": " + this.displayName + ",\"cooldown\": " + this.cooldown + ",\"methodID\":\n" + this.actions + "\n,\"ingredient\": " + ingredients + ",\"manaCost\":" + manaCost;
    }
}
