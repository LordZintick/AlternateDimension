package com.github.lordzintick.magic.loader;

import com.github.lordzintick.magic.SpellUtil;
import com.google.gson.Gson;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SpellLoader {
    public static final SpellContainer SPELLS = new SpellContainer();
    private static final SpellContainer BEFORE_RELOAD_SPELLS = new SpellContainer();
    public static final BasicRegistry<Class<?>> SPELL_PROVIDERS = new BasicRegistry<>();

    public static final class BasicRegistry<T> {
        private final ArrayList<String> IDS = new ArrayList<>();
        private final ArrayList<T> VALUES = new ArrayList<>();

        /**
         * Registers "value" to the registry with an identifier of "id"
         * @param id The ID to register to
         * @param value The value to register
         */
        public void register(String id, T value) {
            if (!IDS.contains(id)) {
                IDS.add(id);
            } else {
                AlternateDimensionMod.LOGGER.warn("Identifier is already registered: \"{}\"", id);
            }
            if (!VALUES.contains(value)) {
                VALUES.add(value);
            } else {
                AlternateDimensionMod.LOGGER.warn("Value is already registered: \"{}\"", value.toString());
            }
        }

        public T get(String id) {
            if (IDS.contains(id)) {
                try {
                    return VALUES.get(IDS.indexOf(id));
                } catch (Exception e) {
                    AlternateDimensionMod.LOGGER.warn("Could not get value for identifier: \"{}\"", id);
                    return null;
                }
            } else {
                AlternateDimensionMod.LOGGER.warn("Could not find identifier: \"{}\"", id);
                return null;
            }
        }

        public String getID(T value) {
            if (VALUES.contains(value)) {
                try {
                    return IDS.get(VALUES.indexOf(value));
                } catch (Exception e) {
                    AlternateDimensionMod.LOGGER.warn("Could not get identifier for value: \"{}\"", value.toString());
                    return "";
                }
            } else {
                AlternateDimensionMod.LOGGER.warn("Could not find value: \"{}\"", value.toString());
                return "";
            }
        }

        public ArrayList<String> getIDs() {
            return IDS;
        }

        public ArrayList<T> getValues() {
            return VALUES;
        }
    }

    public static final class SpellContainer extends ArrayList<Spell> {
        public static final ArrayList<Spell> DEFAULT_SPELLS = new ArrayList<>();
        private boolean hasDefaults = true;
        
        static {
            DEFAULT_SPELLS.add(new Spell("Arrow Blast",
                    2f,
                    "Shoots a blast of arrows in every direction",
                    List.of(Ingredient.of(Items.ARROW), Ingredient.of(Items.TNT)),
                    List.of(p -> SpellUtil.shootEntities(p, EntityType.ARROW, 200, 0.5f, 0f, 2f)),
                    50)
            );
        }
        
        public SpellContainer() {
            addAll(DEFAULT_SPELLS);
        }

        public void removeDefaults() {
            this.removeIf(this::removeDefaultsCondition);
            if (hasDefaults) {
                hasDefaults = false;
            }
        }

        private boolean removeDefaultsCondition(Spell spell) {
            return DEFAULT_SPELLS.contains(spell) && hasDefaults;
        }

        public void addDefaults() {
            if (!hasDefaults) {
                hasDefaults = true;
                addAll(DEFAULT_SPELLS);
            }
        }

        public void castSpell(Identifier id, Spell.SpellParameterProvider parameterProvider) {
            boolean foundSpell = false;
            for (Spell spell : this) {
                if (Objects.equals(spell.id, id) && !foundSpell) {
                    spell.cast(parameterProvider);
                    foundSpell = true;
                }
            }
            if (!foundSpell) {
                AlternateDimensionMod.LOGGER.warn("Could not find spell with id: {}", id.toString());
            }
        }

        public Spell getByIngredients(ArrayList<Identifier> ingredients) {
            for (Spell spell : this) {
                ArrayList<String> ingredientsStrings = new ArrayList<>();
                for (Identifier id : ingredients) {ingredientsStrings.add(id.toString());}
                if (spell.ingredients.containsAll(ingredientsStrings) && spell.ingredients.size() == ingredients.size()) {
                    return spell;
                } else {
                    AlternateDimensionMod.LOGGER.debug("Spell with ingredients: {} does not match ingredients provided: {}", spell.ingredients, ingredientsStrings);
                }
            }
            AlternateDimensionMod.LOGGER.warn("Could not find spell with ingredients: {} (make sure that spell ingredients are valid identifiers)", ingredients.toString());
            return null;
        }

        @Nullable
        public Spell getByID(Identifier id) {
            for (Spell spell : this) {
                if (Objects.equals(spell.id, id)) {
                    return spell;
                }
            }
            return null;
        }
    }

    public static void reloadData(ResourceManager resourceManager) {
        AlternateDimensionMod.LOGGER.info("Reloading spell data...");
        SpellContainer beforeSpellsNoDefaults = new SpellContainer();
        beforeSpellsNoDefaults.addAll(SPELLS);
        beforeSpellsNoDefaults.removeDefaults();
        BEFORE_RELOAD_SPELLS.clear();
        BEFORE_RELOAD_SPELLS.addAll(beforeSpellsNoDefaults);
        SPELLS.clear();
        AlternateDimensionMod.LOGGER.info("Cleared spell data - Spells cleared: {}", BEFORE_RELOAD_SPELLS.size());
        if (SPELLS.hasDefaults) {
            SPELLS.addAll(SpellContainer.DEFAULT_SPELLS);
        }
        loadData(resourceManager);
    }

    public static void loadData(ResourceManager manager) {
        Gson gson = new Gson();
        AlternateDimensionMod.LOGGER.info("Loading spell data...");
        ArrayList<Spell> changed = new ArrayList<>();
        Map<Identifier, Resource> resources = manager.listResources("spells", path -> path.getPath().endsWith(".json"));
        for(Resource resource : resources.values()) {
            Identifier id = new ArrayList<>(resources.keySet()).get(new ArrayList<>(resources.values()).indexOf(resource));
            try (InputStream stream = manager.open(id)) {
                InputStreamReader inputReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                BufferedReader bufReader = new BufferedReader(inputReader);
                Spell spell = gson.fromJson(bufReader, Spell.class);
                bufReader.close();
                inputReader.close();
                spell.id = Identifier.parse(id.toString().replace("spells/", "").replace(".json",""));
                SPELLS.add(spell);
                Spell beforeSpell = BEFORE_RELOAD_SPELLS.getByID(id);
                if (beforeSpell != null) {
                    if (!Objects.equals(beforeSpell.asString(), spell.asString())) {
                        changed.add(spell);
                    }
                }
            } catch (Exception e) {
                AlternateDimensionMod.LOGGER.error("An error occurred when loading spell: {}", e.getMessage());
                e.printStackTrace();
            }
        }
        AlternateDimensionMod.LOGGER.info("Finished loading spell data. Spells loaded: {}, New Spells: {}, Removed Stale: {}, Changed: {}", SPELLS.size(), Math.max(0, SPELLS.size() - BEFORE_RELOAD_SPELLS.size()), Math.max(0, BEFORE_RELOAD_SPELLS.size() - SPELLS.size()), changed.size());
    }
}