package com.lordkittycat.alternate_dimension.client.renderer.block;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.animation.AnimationDefinition;

import java.util.Map;

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.procedures.CheckOpenProcedure;
import com.lordkittycat.alternate_dimension.procedures.CheckClosedProcedure;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlockEntities;
import com.lordkittycat.alternate_dimension.client.model.animations.crystal_chestAnimation;
import com.lordkittycat.alternate_dimension.client.model.Modelcrystal_chest;
import com.lordkittycat.alternate_dimension.block.entity.CrystalChestBlockEntity;

@EventBusSubscriber(Dist.CLIENT)
public class CrystalChestRenderer implements BlockEntityRenderer<CrystalChestBlockEntity, CrystalChestRenderer.CustomRenderState> {
	private final CustomHierarchicalModel model;
	private final Identifier texture;

	CrystalChestRenderer(BlockEntityRendererProvider.Context context) {
		this.model = new CustomHierarchicalModel(context.bakeLayer(Modelcrystal_chest.LAYER_LOCATION));
		this.texture = Identifier.parse("alternate_dimension:textures/block/crystal_chest.png");
	}

	@Override
	public CustomRenderState createRenderState() {
		return new CustomRenderState();
	}

	@Override
	public void extractRenderState(CrystalChestBlockEntity blockEntity, CustomRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.CrumblingOverlay breakProgress) {
		BlockEntityRenderState.extractBase(blockEntity, state, breakProgress);
		state.blockEntity = blockEntity;
		state.blockState = blockEntity.getBlockState();
		int tickCount = (int) blockEntity.getLevel().getGameTime();
		state.entityRenderState.ageInTicks = tickCount + partialTicks;
		blockEntity.animationState0.animateWhen(CheckOpenProcedure.execute(blockEntity.getLevel(), blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY(), blockEntity.getBlockPos().getZ()), tickCount);
		blockEntity.animationState1.animateWhen(CheckClosedProcedure.execute(blockEntity.getLevel(), blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY(), blockEntity.getBlockPos().getZ()), tickCount);
	}

	@Override
	public void submit(CustomRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		poseStack.pushPose();
		poseStack.scale(-1, -1, 1);
		poseStack.translate(-0.5, -0.5, 0.5);
		poseStack.translate(0, -1, 0);
		model.setupBlockEntityAnim(renderState.blockEntity, renderState.entityRenderState);
		submitNodeCollector.submitModel(this.model, renderState.entityRenderState, poseStack, RenderTypes.entityCutout(texture), renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0, null);
		poseStack.popPose();
	}

	@SubscribeEvent
	public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(AlternateDimensionModBlockEntities.CRYSTAL_CHEST.get(), CrystalChestRenderer::new);
	}

	public static class CustomRenderState extends BlockEntityRenderState {
		protected final LivingEntityRenderState entityRenderState = new LivingEntityRenderState();
		protected CrystalChestBlockEntity blockEntity;
		protected BlockState blockState;
	}

	private static final class CustomHierarchicalModel extends Modelcrystal_chest {
		private final KeyframeAnimation keyframeAnimation0;
		private final KeyframeAnimation keyframeAnimation1;

		public CustomHierarchicalModel(ModelPart root) {
			super(root);
			this.keyframeAnimation0 = safeBake(crystal_chestAnimation.open);
			this.keyframeAnimation1 = safeBake(crystal_chestAnimation.close);
		}

		private KeyframeAnimation safeBake(AnimationDefinition source) {
			try {
				return source.bake(root);
			} catch (IllegalArgumentException e) {
				return new AnimationDefinition(0, false, Map.of()).bake(root);
			}
		}

		public void setupBlockEntityAnim(CrystalChestBlockEntity blockEntity, LivingEntityRenderState state) {
			this.root().getAllParts().forEach(ModelPart::resetPose);
			this.keyframeAnimation0.apply(blockEntity.animationState0, state.ageInTicks, 1f);
			this.keyframeAnimation1.apply(blockEntity.animationState1, state.ageInTicks, 1f);
			super.setupAnim(state);
		}
	}
}