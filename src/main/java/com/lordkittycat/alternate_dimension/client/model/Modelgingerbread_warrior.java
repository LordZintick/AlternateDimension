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

// Made with Blockbench 5.1.0
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelgingerbread_warrior extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("alternate_dimension", "modelgingerbread_warrior"), "main");
	public final ModelPart body;
	public final ModelPart left_arm;
	public final ModelPart right_arm;
	public final ModelPart iron_sword;
	public final ModelPart left_leg;
	public final ModelPart right_leg;
	public final ModelPart head;
	public final ModelPart helmet;

	public Modelgingerbread_warrior(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.left_arm = this.body.getChild("left_arm");
		this.right_arm = this.body.getChild("right_arm");
		this.iron_sword = this.right_arm.getChild("iron_sword");
		this.left_leg = this.body.getChild("left_leg");
		this.right_leg = this.body.getChild("right_leg");
		this.head = this.body.getChild("head");
		this.helmet = this.head.getChild("helmet");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 18).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));
		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(20, 18).addBox(-0.5F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 1.0F, 0.0F));
		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(8, 26).addBox(-1.5F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 1.0F, 0.0F));
		PartDefinition iron_sword = right_arm.addOrReplaceChild("iron_sword", CubeListBuilder.create().texOffs(0, 0).addBox(-6.2917F, -7.2917F, 0.25F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.2083F, 4.2917F, 0.25F, 1.5708F, -0.7854F, -1.5708F));
		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 26).addBox(-0.95F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.95F, 6.0F, 0.0F));
		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(24, 26).addBox(-1.05F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.95F, 6.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(8, 8).addBox(-2.3F, -5.0F, -2.7F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(7, 34).addBox(-2.83F, -2.855F, -2.97F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.67F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(LivingEntityRenderState state) {
		float limbSwing = state.walkAnimationPos;
		float limbSwingAmount = state.walkAnimationSpeed;
		float ageInTicks = state.ageInTicks;
		float netHeadYaw = state.yRot;
		float headPitch = state.xRot;

		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
	}
}