// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelspore_spreader<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "spore_spreader"), "main");
	private final ModelPart head;
	private final ModelPart rleg;
	private final ModelPart lleg;

	public Modelspore_spreader(ModelPart root) {
		this.head = root.getChild("head");
		this.rleg = this.head.getChild("rleg");
		this.lleg = this.head.getChild("lleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-6.0F, -15.1429F, -6.0F, 12.0F, 18.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(0, 29)
						.addBox(-6.0F, -14.1429F, -7.0F, 12.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(12, 13)
						.addBox(-6.0F, -14.1429F, 6.0F, 12.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(12, 2)
						.addBox(6.0F, -14.1429F, -6.0F, 1.0F, 17.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(10, 2)
						.addBox(-7.0F, -14.1429F, -6.0F, 1.0F, 17.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 18.1429F, 0.0F));

		PartDefinition rleg = head.addOrReplaceChild("rleg", CubeListBuilder.create().texOffs(0, 47).addBox(-1.0F, 0.0F,
				-1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 1.8571F, 0.0F));

		PartDefinition lleg = head.addOrReplaceChild("lleg", CubeListBuilder.create().texOffs(8, 47).addBox(-1.0F, 0.0F,
				-1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 1.8571F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}