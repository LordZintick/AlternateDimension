package com.lordkittycat.alternate_dimension.client.renderer.block;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.procedures.CheckOpenProcedure;
import com.lordkittycat.alternate_dimension.procedures.CheckClosedProcedure;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlockEntities;
import com.lordkittycat.alternate_dimension.client.model.animations.crystal_chestAnimation;
import com.lordkittycat.alternate_dimension.client.model.Modelcrystal_chest;
import com.lordkittycat.alternate_dimension.block.entity.CrystalChestBlockEntity;

@EventBusSubscriber(Dist.CLIENT)
public class CrystalChestRenderer implements BlockEntityRenderer<CrystalChestBlockEntity> {
	private final CustomHierarchicalModel model;
	private final ResourceLocation texture;

	CrystalChestRenderer(BlockEntityRendererProvider.Context context) {
		this.model = new CustomHierarchicalModel(context.bakeLayer(Modelcrystal_chest.LAYER_LOCATION));
		this.texture = ResourceLocation.parse("alternate_dimension:textures/block/crystal_chest.png");
	}

	private void updateRenderState(CrystalChestBlockEntity blockEntity) {
		int tickCount = (int) blockEntity.getLevel().getGameTime();
		blockEntity.animationState0.animateWhen(CheckOpenProcedure.execute(blockEntity.getLevel(), blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY(), blockEntity.getBlockPos().getZ()), tickCount);
		blockEntity.animationState1.animateWhen(CheckClosedProcedure.execute(blockEntity.getLevel(), blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY(), blockEntity.getBlockPos().getZ()), tickCount);
	}

	@Override
	public void render(CrystalChestBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource renderer, int light, int overlayLight) {
		updateRenderState(blockEntity);
		poseStack.pushPose();
		poseStack.scale(-1, -1, 1);
		poseStack.translate(-0.5, -0.5, 0.5);
		poseStack.translate(0, -1, 0);
		VertexConsumer builder = renderer.getBuffer(RenderType.entityCutout(texture));
		model.setupBlockEntityAnim(blockEntity, blockEntity.getLevel().getGameTime() + partialTick);
		model.renderToBuffer(poseStack, builder, light, overlayLight);
		poseStack.popPose();
	}

	@SubscribeEvent
	public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(AlternateDimensionModBlockEntities.CRYSTAL_CHEST.get(), CrystalChestRenderer::new);
	}

	private static final class CustomHierarchicalModel extends Modelcrystal_chest {
		private final ModelPart root;
		private final BlockEntityHierarchicalModel animator = new BlockEntityHierarchicalModel();

		public CustomHierarchicalModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		public void setupBlockEntityAnim(CrystalChestBlockEntity blockEntity, float ageInTicks) {
			animator.setupBlockEntityAnim(blockEntity, ageInTicks);
			super.setupAnim(null, 0, 0, ageInTicks, 0, 0);
		}

		private class BlockEntityHierarchicalModel extends HierarchicalModel<Entity> {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			}

			public void setupBlockEntityAnim(CrystalChestBlockEntity blockEntity, float ageInTicks) {
				animator.root().getAllParts().forEach(ModelPart::resetPose);
				animator.animate(blockEntity.animationState0, crystal_chestAnimation.open, ageInTicks, 1f);
				animator.animate(blockEntity.animationState1, crystal_chestAnimation.close, ageInTicks, 1f);
			}
		}
	}
}