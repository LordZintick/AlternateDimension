package com.github.lordzintick.modular.rendering;

import com.github.lordzintick.modular.parts.GearMaterial;
import com.github.lordzintick.modular.parts.GearPartType;
import com.github.lordzintick.modular.parts.GearPartsComponent;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ModularArmorLayer<S extends HumanoidRenderState, M extends HumanoidModel<S>> extends RenderLayer<S, M> {
    private final ArmorModelSet<M> armorModels;

    public ModularArmorLayer(RenderLayerParent<S, M> parent, ArmorModelSet<M> armorModels) {
        super(parent);
        this.armorModels = armorModels;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int packedLight, S state, float yRot, float xRot) {
        Map<EquipmentSlot, GearPartsComponent> wornParts = state.getRenderData(ModularArmorRenderData.KEY);
        if (wornParts == null || wornParts.isEmpty()) return;

        for (var slotEntry : wornParts.entrySet()) {
            EquipmentSlot slot = slotEntry.getKey();
            GearPartsComponent partsComp = slotEntry.getValue();

            M armorModel = armorModels.get(slot);
            boolean isLegs = (slot == EquipmentSlot.LEGS);

            List<Map.Entry<Holder<GearPartType>, GearPartsComponent.GearPart>> sortedParts = partsComp.parts().entrySet()
                    .stream()
                    .sorted(Comparator
                            .comparingInt((Map.Entry<Holder<GearPartType>, GearPartsComponent.GearPart> e) ->
                                    getArmorZIndex(e.getKey().value()))
                            .thenComparing(e -> e.getKey().getRegisteredName()))
                    .toList();

            int renderedLayerIndex = 0;
            for (var partEntry : sortedParts) {
                GearPartType part = partEntry.getKey().value();
                GearMaterial mat = partEntry.getValue().material().value();

                Optional<Identifier> texOpt = isLegs ? part.armorLeggingsTexture() : part.armorTexture();
                if (texOpt.isEmpty()) continue;

                int argbColor = ARGB.opaque(mat.color());
                RenderType renderType = RenderTypes.armorCutoutNoCull(texOpt.get());

                poseStack.pushPose();
                if (renderedLayerIndex > 0) {
                    float scale = 1.0f + (renderedLayerIndex * 0.012f);
                    poseStack.scale(scale, scale, scale);
                }

                collector.submitModel(
                        armorModel,
                        state,
                        poseStack,
                        renderType,
                        packedLight,
                        OverlayTexture.NO_OVERLAY,
                        argbColor,
                        null,
                        state.outlineColor,
                        null
                );

                poseStack.popPose();
                renderedLayerIndex++;
            }
        }
    }

    private static int getArmorZIndex(GearPartType part) {
        return part.zIndex();
    }
}
