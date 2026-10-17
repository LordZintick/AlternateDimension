package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

public class ChanceConditionProcedure {
	public static boolean execute() {
		return Mth.nextInt(RandomSource.create(), 0, 10) == 0;
	}
}