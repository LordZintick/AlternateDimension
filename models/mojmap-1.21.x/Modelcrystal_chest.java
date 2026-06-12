// Made with Blockbench 5.1.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelcrystal_chest<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "crystal_chest"), "main");
	private final ModelPart base;
	private final ModelPart lid;
	private final ModelPart knob;

	public Modelcrystal_chest(ModelPart root) {
		this.base = root.getChild("base");
		this.lid = this.base.getChild("lid");
		this.knob = this.lid.getChild("knob");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition base = partdefinition.addOrReplaceChild("base",
				CubeListBuilder.create().texOffs(0, 19).addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.0F, 24.0F, 8.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition lid = base
				.addOrReplaceChild("lid",
						CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -0.5F, 0.0F, 14.0F, 5.0F, 14.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(8.0F, 9.5F, 1.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition knob = lid
				.addOrReplaceChild("knob",
						CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, -0.5F, 14.5F, 0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
	}
}