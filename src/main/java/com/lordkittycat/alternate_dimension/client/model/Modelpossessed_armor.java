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
public class Modelpossessed_armor extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("alternate_dimension", "modelpossessed_armor"), "main");
	public final ModelPart body;
	public final ModelPart waist;
	public final ModelPart left_leg;
	public final ModelPart left_shoe;
	public final ModelPart right_leg;
	public final ModelPart right_shoe;
	public final ModelPart head;
	public final ModelPart left_arm;
	public final ModelPart right_arm;

	public Modelpossessed_armor(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.waist = this.body.getChild("waist");
		this.left_leg = this.waist.getChild("left_leg");
		this.left_shoe = this.left_leg.getChild("left_shoe");
		this.right_leg = this.waist.getChild("right_leg");
		this.right_shoe = this.right_leg.getChild("right_shoe");
		this.head = this.body.getChild("head");
		this.left_arm = this.body.getChild("left_arm");
		this.right_arm = this.body.getChild("right_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(17, 17).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, 3.0F, 0.0F));
		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 0.0F));
		PartDefinition left_leg = waist.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(1, 50).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(1.9F, 1.0F, 0.0F));
		PartDefinition left_shoe = left_leg.addOrReplaceChild("left_shoe", CubeListBuilder.create().texOffs(1, 17).mirror().addBox(-2.0F, 1.25F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition right_leg = waist.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(1, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-1.9F, 1.0F, 0.0F));
		PartDefinition right_shoe = right_leg.addOrReplaceChild("right_shoe", CubeListBuilder.create().texOffs(1, 17).addBox(-2.0F, 1.25F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(1, 1).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, -6.0F, 0.0F));
		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(41, 17).mirror().addBox(0.5F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false), PartPose.offset(5.0F, -2.0F, 0.0F));
		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(41, 17).addBox(-4.5F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(-5.0F, -2.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 66, 67);
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