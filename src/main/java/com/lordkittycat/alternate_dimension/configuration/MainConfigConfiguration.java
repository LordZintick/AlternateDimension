package com.lordkittycat.alternate_dimension.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class MainConfigConfiguration {
	public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	public static final ModConfigSpec SPEC;
	public static final ModConfigSpec.ConfigValue<List<? extends String>> SPELL_POWERS;
	public static final ModConfigSpec.ConfigValue<List<? extends String>> SPELL_MANA_COSTS;
	static {
		BUILDER.push("magic");
		SPELL_POWERS = BUILDER.comment("Spell Powers").defineList("spell_powers", List.of("fireball:1"), entry -> true);
		SPELL_MANA_COSTS = BUILDER.comment("Spell Mana Costs").defineList("spell_mana_costs", List.of("fireball:10"), entry -> true);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}