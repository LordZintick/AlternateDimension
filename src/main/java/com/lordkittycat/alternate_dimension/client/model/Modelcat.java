package com.lordkittycat.alternate_dimension.client.model;

import net.minecraft.util.Mth;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelcat extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("alternate_dimension", "modelcat"), "main");
	public final ModelPart head;
	public final ModelPart body;
	public final ModelPart front_left_leg;
	public final ModelPart front_right_leg;
	public final ModelPart back_left_leg;
	public final ModelPart back_right_leg;
	public final ModelPart tail2;
	public final ModelPart tail;

	public Modelcat(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.front_left_leg = root.getChild("front_left_leg");
		this.front_right_leg = root.getChild("front_right_leg");
		this.back_left_leg = root.getChild("back_left_leg");
		this.back_right_leg = root.getChild("back_right_leg");
		this.tail2 = root.getChild("tail2");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(20, 0).addBox(-2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 22).addBox(-1.5F, -0.02F, -4.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 26)
						.addBox(-2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(28, 9).addBox(1.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 15.0F, -9.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 3.0F, -8.0F, 4.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 12.0F, -10.0F, 1.5708F, 0.0F, 0.0F));
		PartDefinition front_left_leg = partdefinition.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(20, 9).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.1F, 14.1F, -5.0F));
		PartDefinition front_right_leg = partdefinition.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(20, 9).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.1F, 14.1F, -5.0F));
		PartDefinition back_left_leg = partdefinition.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(20, 21).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.1F, 18.0F, 5.0F));
		PartDefinition back_right_leg = partdefinition.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(20, 21).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.1F, 18.0F, 5.0F));
		PartDefinition tail2 = partdefinition.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(14, 22).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 15.5F, 16.0F, 1.5708F, 0.0F, 0.0F));
		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(10, 22).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 15.5F, 8.0F, 1.5708F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	public void setupAnim(LivingEntityRenderState state) {
		float limbSwing = state.walkAnimationPos;
		float limbSwingAmount = state.walkAnimationSpeed;
		float ageInTicks = state.ageInTicks;
		float netHeadYaw = state.yRot;
		float headPitch = state.xRot;

		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.front_right_leg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.back_right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.back_left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.front_left_leg.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
	}
}