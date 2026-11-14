// Made with Blockbench 5.1.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelspider_hydra<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "spider_hydra"), "main");
	private final ModelPart neck;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
	private final ModelPart leg5;
	private final ModelPart leg6;
	private final ModelPart leg7;
	private final ModelPart leg8;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart head3;
	private final ModelPart head2;

	public Modelspider_hydra(ModelPart root) {
		this.neck = root.getChild("neck");
		this.leg1 = this.neck.getChild("leg1");
		this.leg2 = this.neck.getChild("leg2");
		this.leg3 = this.neck.getChild("leg3");
		this.leg4 = this.neck.getChild("leg4");
		this.leg5 = this.neck.getChild("leg5");
		this.leg6 = this.neck.getChild("leg6");
		this.leg7 = this.neck.getChild("leg7");
		this.leg8 = this.neck.getChild("leg8");
		this.body = this.neck.getChild("body");
		this.head = this.neck.getChild("head");
		this.head3 = this.neck.getChild("head3");
		this.head2 = this.neck.getChild("head2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition neck = partdefinition.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 0).addBox(
				-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.5F, 0.0F));

		PartDefinition leg1 = neck.addOrReplaceChild("leg1",
				CubeListBuilder.create().texOffs(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 0.0F, 2.0F, 0.0F, 0.4363F, -0.6109F));

		PartDefinition leg2 = neck.addOrReplaceChild("leg2",
				CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, 0.0F, 2.0F, 0.0F, -0.4363F, 0.6109F));

		PartDefinition leg3 = neck.addOrReplaceChild("leg3",
				CubeListBuilder.create().texOffs(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 0.0F, 1.0F, 0.0F, 0.1309F, -0.6109F));

		PartDefinition leg4 = neck.addOrReplaceChild("leg4",
				CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, 0.0F, 1.0F, 0.0F, -0.1309F, 0.6109F));

		PartDefinition leg5 = neck.addOrReplaceChild("leg5",
				CubeListBuilder.create().texOffs(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 0.0F, -1.0F, 0.0F, -0.1309F, -0.6109F));

		PartDefinition leg6 = neck.addOrReplaceChild("leg6",
				CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, 0.0F, -1.0F, 0.0F, 0.1309F, 0.6109F));

		PartDefinition leg7 = neck.addOrReplaceChild("leg7",
				CubeListBuilder.create().texOffs(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 0.0F, -2.0F, 0.0F, -0.4363F, -0.6109F));

		PartDefinition leg8 = neck.addOrReplaceChild("leg8",
				CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, 0.0F, -2.0F, 0.0F, 0.4363F, 0.6109F));

		PartDefinition body = neck.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 12).addBox(-5.0F,
				-4.0F, -6.0F, 10.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 4).addBox(-4.0F,
				-4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition head3 = neck.addOrReplaceChild("head3",
				CubeListBuilder.create().texOffs(33, 5).addBox(-4.0F, -3.5F, -7.0F, 7.0F, 7.0F, 7.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, 0.0F, -3.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition head2 = neck.addOrReplaceChild("head2",
				CubeListBuilder.create().texOffs(34, 5).addBox(-3.0F, -3.5F, -7.0F, 7.0F, 7.0F, 7.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, 0.0F, -3.0F, 0.0F, 0.6981F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		neck.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.head2.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head2.xRot = headPitch / (180F / (float) Math.PI);
		this.head3.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head3.xRot = headPitch / (180F / (float) Math.PI);
	}
}