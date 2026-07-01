package com.lordkittycat.alternate_dimension.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class MainConfigConfiguration {
	public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	public static final ModConfigSpec SPEC;
	public static final ModConfigSpec.ConfigValue<List<? extends String>> HINTS;
	static {
		HINTS = BUILDER.comment("The hints shown by the hint book").defineList("hints", List.of("The refinery requires\n two blocks to function:\n the base and the controller.",
				"There are eight types\nof crafting tables:\nscorched, pine, redwood,\nazure, candy, planty, and mushy.", "Need storage? Try a box\nor a backpack!"), entry -> true);

		SPEC = BUILDER.build();
	}

}