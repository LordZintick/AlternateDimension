package com.github.lordzintick.modular;

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
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.EquipmentSlot;

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

            // Each slot has its own isolated Model instance inside ArmorModelSet—zero shared-state mutation!
            M armorModel = armorModels.get(slot);
            boolean isLegs = (slot == EquipmentSlot.LEGS);

            for (var partEntry : partsComp.sortedByZIndex()) {
                GearPartType part = partEntry.getKey().value();
                GearMaterial mat = partEntry.getValue().value();

                Optional<Identifier> texOpt = isLegs ? part.armorLeggingsTexture() : part.armorTexture();
                if (texOpt.isEmpty()) continue;

                int argbColor = ARGB.opaque(mat.color());
                RenderType renderType = RenderTypes.armorCutoutNoCull(texOpt.get());

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
            }
        }
    }
}
