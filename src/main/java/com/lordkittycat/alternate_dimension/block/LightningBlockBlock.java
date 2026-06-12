package com.lordkittycat.alternate_dimension.block;

import org.checkerframework.checker.units.qual.s;

import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.RandomSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.procedures.ElectricParticleEffectProcedure;
import com.lordkittycat.alternate_dimension.procedures.CollectLightningBottleProcedure;

public class LightningBlockBlock extends Block {
	public LightningBlockBlock() {
		super(BlockBehaviour.Properties.of()
				.sound(new DeferredSoundType(1.0f, 1.0f, () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.lightning_bolt.impact")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.lightning_bolt.thunder")), () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.lightning_bolt.impact")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.lightning_bolt.thunder")), () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.lightning_bolt.impact"))))
				.strength(3f, 100f).lightLevel(s -> 15).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		ElectricParticleEffectProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		CollectLightningBottleProcedure.execute(world, x, y, z, entity);
		return InteractionResult.SUCCESS;
	}
}