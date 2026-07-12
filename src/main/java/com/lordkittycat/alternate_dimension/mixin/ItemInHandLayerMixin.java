package com.lordkittycat.alternate_dimension.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.SubmitNodeCollector;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.AlternateDimensionModPlayerAnimationAPI;

@Mixin(ItemInHandLayer.class)
public abstract class ItemInHandLayerMixin {
	private String master = null;

	@Inject(method = "submitArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/item/ItemStackRenderState;submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;III)V"))
	private void animateItem(ArmedEntityRenderState renderState, ItemStackRenderState itemStackRenderState, ItemStack itemStack, HumanoidArm arm, PoseStack poseStack, SubmitNodeCollector collector, int packedLight, CallbackInfo ci) {
		if (master == null) {
			if (!AlternateDimensionModPlayerAnimationAPI.animations.isEmpty())
				master = "alternate_dimension";
			else
				return;
		}
		if (!master.equals("alternate_dimension")) {
			return;
		}
		if (renderState instanceof AvatarRenderState state) {
			Player player = (Player) renderState.getRenderData(AlternateDimensionModPlayerAnimationAPI.ClientAttachments.PLAYER);
			if (player == null)
				return;
			AlternateDimensionModPlayerAnimationAPI.PlayerAnimation animation = AlternateDimensionModPlayerAnimationAPI.active_animations.get(player);
			if (animation == null)
				return;
			AlternateDimensionModPlayerAnimationAPI.PlayerBone bone = animation.bones.get(arm == HumanoidArm.LEFT ? "left_item" : "right_item");
			if (bone == null)
				return;
			float animationProgress = player.getPersistentData().getFloatOr("PlayerAnimationProgress", 0);
			Vec3 scale = AlternateDimensionModPlayerAnimationAPI.PlayerBone.interpolate(bone.scales, animationProgress, player);
			if (scale != null) {
				poseStack.scale((float) scale.x, (float) scale.y, (float) scale.z);
			}
			Vec3 position = AlternateDimensionModPlayerAnimationAPI.PlayerBone.interpolate(bone.positions, animationProgress, player);
			if (position != null) {
				poseStack.translate((float) -position.x * 0.0625f, (float) -position.z * 0.0625f, (float) position.y * 0.0625f);
			}
			Vec3 rotation = AlternateDimensionModPlayerAnimationAPI.PlayerBone.interpolate(bone.rotations, animationProgress, player);
			if (rotation != null) {
				poseStack.mulPose(Axis.ZP.rotationDegrees((float) rotation.y));
				poseStack.mulPose(Axis.YP.rotationDegrees((float) -rotation.z));
				poseStack.mulPose(Axis.XP.rotationDegrees((float) -rotation.x));
			}
		}
	}
}