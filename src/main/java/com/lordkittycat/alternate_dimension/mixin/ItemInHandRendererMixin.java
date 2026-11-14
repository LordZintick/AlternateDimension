package com.lordkittycat.alternate_dimension.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.vertex.PoseStack;

import com.lordkittycat.alternate_dimension.AlternateDimensionModPlayerAnimationAPI;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
	private String master = null;
	private Minecraft mc = Minecraft.getInstance();
	private EntityRenderDispatcher dispatcher = null;

	@Inject(method = "renderHandsWithItems", at = @At("HEAD"), cancellable = true)
	private void renderHandsWithItems(float f, PoseStack poseStack, SubmitNodeCollector collector, LocalPlayer localPlayer, int i, CallbackInfo ci) {
		if (master == null) {
			if (!AlternateDimensionModPlayerAnimationAPI.animations.isEmpty())
				master = "alternate_dimension";
			else
				return;
		}
		if (!master.equals("alternate_dimension"))
			return;
		if (localPlayer instanceof Player player && mc.player == player && (mc.screen == null || mc.screen instanceof ChatScreen)) {
			if (dispatcher == null)
				dispatcher = mc.getEntityRenderDispatcher();
			CompoundTag playerData = player.getPersistentData();
			// Hack to make animations progress when in first person without first person mode enabled
			if (!playerData.getStringOr("PlayerCurrentAnimation", "").isEmpty() && (!playerData.getBooleanOr("FirstPersonAnimation", false) || playerData.getBooleanOr("ResetPlayerAnimation", false))) {
				AvatarRenderer renderer = (AvatarRenderer) dispatcher.getRenderer((AbstractClientPlayer) player);
				PlayerModel model = (PlayerModel) renderer.getModel();
				AvatarRenderState renderState = (AvatarRenderState) renderer.createRenderState((AbstractClientPlayer) player, f);
				renderState.ageInTicks = player.tickCount + f;
				model.setupAnim(renderState);
			}
			if (playerData.getBooleanOr("FirstPersonAnimation", false))
				ci.cancel();
		}
	}
}