// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelarrow_model<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "arrow_model"), "main");
	private final ModelPart bb_main;

	public Modelarrow_model(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cross_2_r1 = bb_main
				.addOrReplaceChild("cross_2_r1",
						CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -2.5F, 0.0F, 16.0F, 5.0F, 0.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, -3.1F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cross_1_r1 = bb_main.addOrReplaceChild("cross_1_r1",
				CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -2.5F, 0.0F, 16.0F, 5.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.1F, 0.0F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition back_r1 = bb_main.addOrReplaceChild("back_r1",
				CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.1F, 7.5F, 0.0F, -1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}