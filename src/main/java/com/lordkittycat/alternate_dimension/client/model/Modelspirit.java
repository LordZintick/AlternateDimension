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

// Made with Blockbench 5.1.0
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelspirit<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "modelspirit"), "main");
	public final ModelPart head;
	public final ModelPart ring1;
	public final ModelPart stick1;
	public final ModelPart stick2;
	public final ModelPart stick3;
	public final ModelPart stick4;
	public final ModelPart ring2;
	public final ModelPart stick5;
	public final ModelPart stick6;
	public final ModelPart stick7;
	public final ModelPart stick8;
	public final ModelPart ring3;
	public final ModelPart stick9;
	public final ModelPart stick10;
	public final ModelPart stick11;
	public final ModelPart stick12;

	public Modelspirit(ModelPart root) {
		this.head = root.getChild("head");
		this.ring1 = this.head.getChild("ring1");
		this.stick1 = this.ring1.getChild("stick1");
		this.stick2 = this.ring1.getChild("stick2");
		this.stick3 = this.ring1.getChild("stick3");
		this.stick4 = this.ring1.getChild("stick4");
		this.ring2 = this.head.getChild("ring2");
		this.stick5 = this.ring2.getChild("stick5");
		this.stick6 = this.ring2.getChild("stick6");
		this.stick7 = this.ring2.getChild("stick7");
		this.stick8 = this.ring2.getChild("stick8");
		this.ring3 = this.head.getChild("ring3");
		this.stick9 = this.ring3.getChild("stick9");
		this.stick10 = this.ring3.getChild("stick10");
		this.stick11 = this.ring3.getChild("stick11");
		this.stick12 = this.ring3.getChild("stick12");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -28.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition ring1 = head.addOrReplaceChild("ring1", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));
		PartDefinition stick1 = ring1.addOrReplaceChild("stick1", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, -4.0F, -7.0F));
		PartDefinition stick2 = ring1.addOrReplaceChild("stick2", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(7.0F, -4.0F, -7.0F));
		PartDefinition stick3 = ring1.addOrReplaceChild("stick3", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(7.0F, -4.0F, 7.0F));
		PartDefinition stick4 = ring1.addOrReplaceChild("stick4", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, -4.0F, 7.0F));
		PartDefinition ring2 = head.addOrReplaceChild("ring2", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 0.0F));
		PartDefinition stick5 = ring2.addOrReplaceChild("stick5", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -4.0F, -5.0F));
		PartDefinition stick6 = ring2.addOrReplaceChild("stick6", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -4.0F, -5.0F));
		PartDefinition stick7 = ring2.addOrReplaceChild("stick7", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -4.0F, 5.0F));
		PartDefinition stick8 = ring2.addOrReplaceChild("stick8", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -4.0F, 5.0F));
		PartDefinition ring3 = head.addOrReplaceChild("ring3", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 0.0F));
		PartDefinition stick9 = ring3.addOrReplaceChild("stick9", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -4.0F, -3.0F));
		PartDefinition stick10 = ring3.addOrReplaceChild("stick10", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, -4.0F, -3.0F));
		PartDefinition stick11 = ring3.addOrReplaceChild("stick11", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, -4.0F, 3.0F));
		PartDefinition stick12 = ring3.addOrReplaceChild("stick12", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -4.0F, 3.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
	}
}