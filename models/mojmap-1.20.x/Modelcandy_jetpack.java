// Made with Blockbench 5.1.0
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelcandy_jetpack<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "candy_jetpack"), "main");
	private final ModelPart bipedHead;
	private final ModelPart armorHead;
	private final ModelPart bipedBody;
	private final ModelPart armorBody;
	private final ModelPart jetpack;
	private final ModelPart bipedRightArm;
	private final ModelPart armorRightArm;
	private final ModelPart bipedLeftArm;
	private final ModelPart armorLeftArm;
	private final ModelPart bipedLeftLeg;
	private final ModelPart armorLeftLeg;
	private final ModelPart armorLeftBoot;
	private final ModelPart bipedRightLeg;
	private final ModelPart armorRightLeg;
	private final ModelPart armorRightBoot;

	public Modelcandy_jetpack(ModelPart root) {
		this.bipedHead = root.getChild("bipedHead");
		this.armorHead = this.bipedHead.getChild("armorHead");
		this.bipedBody = root.getChild("bipedBody");
		this.armorBody = this.bipedBody.getChild("armorBody");
		this.jetpack = this.armorBody.getChild("jetpack");
		this.bipedRightArm = root.getChild("bipedRightArm");
		this.armorRightArm = this.bipedRightArm.getChild("armorRightArm");
		this.bipedLeftArm = root.getChild("bipedLeftArm");
		this.armorLeftArm = this.bipedLeftArm.getChild("armorLeftArm");
		this.bipedLeftLeg = root.getChild("bipedLeftLeg");
		this.armorLeftLeg = this.bipedLeftLeg.getChild("armorLeftLeg");
		this.armorLeftBoot = this.bipedLeftLeg.getChild("armorLeftBoot");
		this.bipedRightLeg = root.getChild("bipedRightLeg");
		this.armorRightLeg = this.bipedRightLeg.getChild("armorRightLeg");
		this.armorRightBoot = this.bipedRightLeg.getChild("armorRightBoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bipedHead = partdefinition.addOrReplaceChild("bipedHead", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorHead = bipedHead.addOrReplaceChild("armorHead", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bipedBody = partdefinition.addOrReplaceChild("bipedBody", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorBody = bipedBody.addOrReplaceChild("armorBody", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jetpack = armorBody.addOrReplaceChild("jetpack",
				CubeListBuilder.create().texOffs(24, 16)
						.addBox(-4.0F, 0.0F, 2.0F, 8.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(46, 16)
						.addBox(4.0F, 1.0F, 2.5F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 32)
						.addBox(1.0F, 0.5F, 5.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 45)
						.addBox(-3.0F, 0.5F, 5.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 47)
						.addBox(-6.0F, 1.0F, 2.5F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bipedRightArm = partdefinition.addOrReplaceChild("bipedRightArm", CubeListBuilder.create(),
				PartPose.offset(-4.0F, 2.0F, 0.0F));

		PartDefinition armorRightArm = bipedRightArm.addOrReplaceChild("armorRightArm", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bipedLeftArm = partdefinition.addOrReplaceChild("bipedLeftArm", CubeListBuilder.create(),
				PartPose.offset(4.0F, 2.0F, 0.0F));

		PartDefinition armorLeftArm = bipedLeftArm.addOrReplaceChild("armorLeftArm", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bipedLeftLeg = partdefinition.addOrReplaceChild("bipedLeftLeg", CubeListBuilder.create(),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition armorLeftLeg = bipedLeftLeg.addOrReplaceChild("armorLeftLeg", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorLeftBoot = bipedLeftLeg.addOrReplaceChild("armorLeftBoot", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bipedRightLeg = partdefinition.addOrReplaceChild("bipedRightLeg", CubeListBuilder.create(),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition armorRightLeg = bipedRightLeg.addOrReplaceChild("armorRightLeg", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorRightBoot = bipedRightLeg.addOrReplaceChild("armorRightBoot", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		bipedHead.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bipedBody.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bipedRightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bipedLeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bipedLeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bipedRightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
	}
}