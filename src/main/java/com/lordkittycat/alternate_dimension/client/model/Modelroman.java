package com.lordkittycat.alternate_dimension.client.model;

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

// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelroman extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("alternate_dimension", "modelroman"), "main");
	public final ModelPart body;
	public final ModelPart left_arm;
	public final ModelPart right_arm;
	public final ModelPart spear;
	public final ModelPart left_leg;
	public final ModelPart right_leg;
	public final ModelPart head;
	public final ModelPart helmet;

	public Modelroman(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.left_arm = this.body.getChild("left_arm");
		this.right_arm = this.body.getChild("right_arm");
		this.spear = this.right_arm.getChild("spear");
		this.left_leg = this.body.getChild("left_leg");
		this.right_leg = this.body.getChild("right_leg");
		this.head = this.body.getChild("head");
		this.helmet = this.head.getChild("helmet");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));
		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -4.0F, 0.0F));
		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -4.0F, 0.0F));
		PartDefinition spear = right_arm.addOrReplaceChild("spear", CubeListBuilder.create(), PartPose.offset(-1.0F, 11.0F, 0.1667F));
		PartDefinition tip_r1 = spear.addOrReplaceChild("tip_r1", CubeListBuilder.create().texOffs(8, 32).addBox(-4.0F, 9.0F, 0.0F, 8.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -5.1667F, -1.5708F, 0.0F, -1.5708F));
		PartDefinition tip_r2 = spear.addOrReplaceChild("tip_r2", CubeListBuilder.create().texOffs(8, 32).addBox(-4.0F, 9.0F, 0.0F, 8.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -5.1667F, -1.5708F, 0.0F, 0.0F));
		PartDefinition handle_r1 = spear.addOrReplaceChild("handle_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -15.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.8333F, -1.5708F, 0.0F, 0.0F));
		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 6.0F, 0.0F));
		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 6.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));
		PartDefinition helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create(), PartPose.offset(0.0F, -8.9971F, 1.5451F));
		PartDefinition endcap_r1 = helmet.addOrReplaceChild("endcap_r1", CubeListBuilder.create().texOffs(9, 0).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 4.4971F, 4.4549F, -1.5708F, 0.0F, 0.0F));
		PartDefinition plume_r1 = helmet.addOrReplaceChild("plume_r1", CubeListBuilder.create().texOffs(50, 0).addBox(-2.0F, -1.5F, -1.5F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 1.7471F, 5.4549F, -1.5708F, 0.0F, 0.0F));
		PartDefinition plume_r2 = helmet.addOrReplaceChild("plume_r2", CubeListBuilder.create().texOffs(36, 0).addBox(-2.0F, -1.5F, -1.5F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.5029F, 4.7049F, -1.1345F, 0.0F, 0.0F));
		PartDefinition plume_r3 = helmet.addOrReplaceChild("plume_r3", CubeListBuilder.create().texOffs(50, 0).addBox(-2.0F, -1.5F, -1.5F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.2529F, 3.2049F, -0.6109F, 0.0F, 0.0F));
		PartDefinition plume_r4 = helmet.addOrReplaceChild("plume_r4", CubeListBuilder.create().texOffs(36, 0).addBox(-2.0F, -1.5F, -1.5F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.2529F, 0.9549F, -0.2618F, 0.0F, 0.0F));
		PartDefinition plume_r5 = helmet.addOrReplaceChild("plume_r5", CubeListBuilder.create().texOffs(50, 0).addBox(-2.0F, -1.5F, -1.5F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.7529F, -1.0451F, -0.0873F, 0.0F, 0.0F));
		PartDefinition plume_r6 = helmet.addOrReplaceChild("plume_r6", CubeListBuilder.create().texOffs(36, 0).addBox(-2.0F, -1.5F, -1.5F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.5029F, -3.0451F, 0.1309F, 0.0F, 0.0F));
		PartDefinition plume_r7 = helmet.addOrReplaceChild("plume_r7", CubeListBuilder.create().texOffs(50, 0).addBox(-2.0F, -1.5F, -1.5F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.5029F, -5.0451F, 0.3927F, 0.0F, 0.0F));
		PartDefinition endcap_r2 = helmet.addOrReplaceChild("endcap_r2", CubeListBuilder.create().texOffs(9, 0).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.4971F, -6.0451F, 0.7854F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(LivingEntityRenderState state) {
		float limbSwing = state.walkAnimationPos;
		float limbSwingAmount = state.walkAnimationSpeed;
		float ageInTicks = state.ageInTicks;
		float netHeadYaw = state.yRot;
		float headPitch = state.xRot;

	}
}