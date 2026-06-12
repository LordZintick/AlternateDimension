package com.lordkittycat.alternate_dimension.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 5.1.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelshroomite<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "modelshroomite"), "main");
	public final ModelPart head;
	public final ModelPart rleg;
	public final ModelPart lleg;

	public Modelshroomite(ModelPart root) {
		this.head = root.getChild("head");
		this.rleg = this.head.getChild("rleg");
		this.lleg = this.head.getChild("lleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -5.1429F, -6.0F, 12.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(0, 39).addBox(-6.0F, -4.1429F, -7.0F, 12.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 39)
						.addBox(-6.0F, -4.1429F, 6.0F, 12.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 20).addBox(6.0F, -4.1429F, -6.0F, 1.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(26, 20)
						.addBox(-7.0F, -4.1429F, -6.0F, 1.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 18.1429F, 0.0F));
		PartDefinition rleg = head.addOrReplaceChild("rleg", CubeListBuilder.create().texOffs(0, 47).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 1.8571F, 0.0F));
		PartDefinition lleg = head.addOrReplaceChild("lleg", CubeListBuilder.create().texOffs(8, 47).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 1.8571F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}