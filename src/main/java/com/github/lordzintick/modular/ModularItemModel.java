package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.mojang.math.Quadrant;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.dispatch.BlockModelRotation;
import net.minecraft.client.renderer.item.CuboidItemModelWrapper;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.item.ModelRenderProperties;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ResolvedModel;
import net.minecraft.client.resources.model.cuboid.CuboidFace;
import net.minecraft.client.resources.model.cuboid.FaceBakery;
import net.minecraft.client.resources.model.cuboid.ItemModelGenerator;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.resources.model.sprite.MaterialBaker;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ModularItemModel implements ItemModel {
    private final ModelRenderProperties standardProperties;
    private final ModelRenderProperties handheldProperties;
    private final Matrix4fc transformation;
    private final ModelBaker modelBaker;
    private final MaterialBaker materialBaker;
    private record PartRenderSpec(Identifier maskTexture, List<Identifier> overlayTextures) {}
    private final Map<List<PartRenderSpec>, CachedMesh> meshCache = new ConcurrentHashMap<>();

    private record CachedMesh(List<BakedQuad> quads, Vector3fc[] extents) {}

    public ModularItemModel(
            ModelRenderProperties standardProperties,
            ModelRenderProperties handheldProperties,
            Matrix4fc transformation,
            ModelBaker modelBaker,
            MaterialBaker materialBaker
    ) {
        this.standardProperties = standardProperties;
        this.handheldProperties = handheldProperties;
        this.transformation = transformation;
        this.modelBaker = modelBaker;
        this.materialBaker = materialBaker;
    }

    @Override
    public void update(
            ItemStackRenderState state,
            ItemStack stack,
            ItemModelResolver resolver,
            ItemDisplayContext displayContext,
            @Nullable ClientLevel level,
            @Nullable ItemOwner owner,
            int seed
    ) {
        state.appendModelIdentityElement(this);

        GearPartsComponent partsComp = stack.get(AlternateDimensionModComponents.GEAR_PARTS.get());
        if (partsComp == null || partsComp.parts().isEmpty()) {
            return;
        }

        var sortedParts = partsComp.sortedByZIndex();
        int count = sortedParts.size();
        boolean isHandheld = count > 1 && sortedParts.stream().anyMatch(e -> e.getKey().value().isHandheld());

        ItemStackRenderState.LayerRenderState layer = state.newLayer();

        if (stack.hasFoil()) {
            layer.setFoilType(ItemStackRenderState.FoilType.STANDARD);
            state.appendModelIdentityElement(ItemStackRenderState.FoilType.STANDARD);
        }

        IntList tintLayers = layer.tintLayers();
        List<PartRenderSpec> renderSpecs = new ArrayList<>(count);

        for (int index = 0; index != count; index++) {
            var entry = sortedParts.get(index);
            GearPartType part = entry.getKey().value();
            GearMaterial mat = entry.getValue().value();

            Identifier maskTex = part.texture().map(
                    id -> id,
                    list -> {
                        for (PartTexture texture : list.textures()) {
                            if (texture.partTypes().stream().anyMatch(h -> partsComp.parts().containsKey(h))) {
                                return texture.texture();
                            }
                        }
                        return list.defaultTexture();
                    }
            );

            int baseColor = ARGB.opaque(mat.color());

            tintLayers.add(baseColor);
            state.appendModelIdentityElement(maskTex);
            state.appendModelIdentityElement(baseColor);

            List<Identifier> overlayIds = new ArrayList<>(mat.overlays().size());
            for (MaterialOverlay overlay : mat.overlays()) {
                if (overlay.partTypes().isPresent() && overlay.partTypes().get().stream().noneMatch(h -> partsComp.parts().containsKey(h))) continue;

                int overlayTint = ARGB.opaque(overlay.color().orElse(0xFFFFFF));
                overlayIds.add(overlay.texture());
                tintLayers.add(overlayTint);
                state.appendModelIdentityElement(overlay.texture());
                state.appendModelIdentityElement(overlayTint);
            }

            renderSpecs.add(new PartRenderSpec(maskTex, List.copyOf(overlayIds)));
        }

        CachedMesh mesh = meshCache.computeIfAbsent(List.copyOf(renderSpecs), this::bakeLayeredMesh);

        ModelRenderProperties activeProps = isHandheld ? handheldProperties : standardProperties;
        activeProps.applyToLayer(layer, displayContext);
        layer.setLocalTransform(this.transformation);
        layer.setExtents(mesh::extents);
        layer.prepareQuadList().addAll(mesh.quads());
    }

    private CachedMesh bakeLayeredMesh(List<PartRenderSpec> specs) {
        List<BakedQuad> allQuads = new ArrayList<>();
        CuboidFace.UVs frontUV = new CuboidFace.UVs(0.0f, 0.0f, 16.0f, 16.0f);
        CuboidFace.UVs backUV = new CuboidFace.UVs(16.0f, 0.0f, 0.0f, 16.0f);

        int currentTintIndex = 0;

        for (int partIndex = 0; partIndex != specs.size(); partIndex++) {
            PartRenderSpec spec = specs.get(partIndex);
            Identifier maskTexId = spec.maskTexture();
            Material.Baked maskMat = this.materialBaker.get(new Material(maskTexId), maskTexId::toString);
            var maskContents = maskMat.sprite().contents();

            float zFront = 8.5f + (partIndex * 0.06f);
            float zBack = 7.5f - (partIndex * 0.06f);

            int baseTintIndex = currentTintIndex++;
            CuboidFace frontFace = new CuboidFace(null, baseTintIndex, "", frontUV, Quadrant.R0);
            CuboidFace backFace = new CuboidFace(null, baseTintIndex, "", backUV, Quadrant.R0);

            allQuads.add(FaceBakery.bakeQuad(
                    this.modelBaker, new Vector3f(0.0f, 0.0f, zFront), new Vector3f(16.0f, 16.0f, zFront),
                    frontFace, maskMat, Direction.SOUTH, BlockModelRotation.IDENTITY, null, true, 0
            ));
            allQuads.add(FaceBakery.bakeQuad(
                    this.modelBaker, new Vector3f(0.0f, 0.0f, zBack), new Vector3f(16.0f, 16.0f, zBack),
                    backFace, maskMat, Direction.NORTH, BlockModelRotation.IDENTITY, null, true, 0
            ));

            int width = maskContents.width();
            int height = maskContents.height();
            float scaleX = 16.0f / width;
            float scaleY = 16.0f / height;

            for (int py = 0; py != height; py++) {
                for (int px = 0; px != width; px++) {
                    if (maskContents.isTransparent(0, px, py)) continue;

                    float x0 = px * scaleX;
                    float x1 = (px + 1) * scaleX;
                    float y1 = 16.0f - (py * scaleY);
                    float y0 = 16.0f - ((py + 1) * scaleY);

                    CuboidFace.UVs pixelUV = new CuboidFace.UVs(x0, py * scaleY, x1, (py + 1) * scaleY);
                    CuboidFace edgeFace = new CuboidFace(null, baseTintIndex, "", pixelUV, Quadrant.R0);

                    if (py == 0 || maskContents.isTransparent(0, px, py - 1)) {
                        allQuads.add(FaceBakery.bakeQuad(this.modelBaker, new Vector3f(x0, y1, zBack), new Vector3f(x1, y1, zFront), edgeFace, maskMat, Direction.UP, BlockModelRotation.IDENTITY, null, true, 0));
                    }
                    if (py == height - 1 || maskContents.isTransparent(0, px, py + 1)) {
                        allQuads.add(FaceBakery.bakeQuad(this.modelBaker, new Vector3f(x0, y0, zBack), new Vector3f(x1, y0, zFront), edgeFace, maskMat, Direction.DOWN, BlockModelRotation.IDENTITY, null, true, 0));
                    }
                    if (px == 0 || maskContents.isTransparent(0, px - 1, py)) {
                        allQuads.add(FaceBakery.bakeQuad(this.modelBaker, new Vector3f(x0, y0, zBack), new Vector3f(x0, y1, zFront), edgeFace, maskMat, Direction.WEST, BlockModelRotation.IDENTITY, null, true, 0));
                    }
                    if (px == width - 1 || maskContents.isTransparent(0, px + 1, py)) {
                        allQuads.add(FaceBakery.bakeQuad(this.modelBaker, new Vector3f(x1, y0, zBack), new Vector3f(x1, y1, zFront), edgeFace, maskMat, Direction.EAST, BlockModelRotation.IDENTITY, null, true, 0));
                    }
                }
            }

            for (int ovIdx = 0; ovIdx != spec.overlayTextures().size(); ovIdx++) {
                Identifier ovTexId = spec.overlayTextures().get(ovIdx);
                Material.Baked ovMat = this.materialBaker.get(new Material(ovTexId), ovTexId::toString);
                var ovContents = ovMat.sprite().contents();
                int ovTintIndex = currentTintIndex++;

                float ovZFront = zFront + ((ovIdx + 1) * 0.015f);
                float ovZBack = zBack - ((ovIdx + 1) * 0.015f);

                int ovW = ovContents.width();
                int ovH = ovContents.height();
                float ovScaleX = 16.0f / ovW;
                float ovScaleY = 16.0f / ovH;

                for (int py = 0; py != ovH; py++) {
                    for (int px = 0; px != ovW; px++) {
                        if (ovContents.isTransparent(0, px, py)) continue;

                        int maskX = Math.min(width - 1, (px * width) / ovW);
                        int maskY = Math.min(height - 1, (py * height) / ovH);
                        if (maskContents.isTransparent(0, maskX, maskY)) continue;

                        float x0 = px * ovScaleX;
                        float x1 = (px + 1) * ovScaleX;
                        float y1 = 16.0f - (py * ovScaleY);
                        float y0 = 16.0f - ((py + 1) * ovScaleY);

                        CuboidFace.UVs ovFrontUV = new CuboidFace.UVs(x0, py * ovScaleY, x1, (py + 1) * ovScaleY);
                        CuboidFace.UVs ovBackUV = new CuboidFace.UVs(x1, py * ovScaleY, x0, (py + 1) * ovScaleY);

                        CuboidFace ovFrontFace = new CuboidFace(null, ovTintIndex, "", ovFrontUV, Quadrant.R0);
                        CuboidFace ovBackFace = new CuboidFace(null, ovTintIndex, "", ovBackUV, Quadrant.R0);

                        allQuads.add(FaceBakery.bakeQuad(
                                this.modelBaker, new Vector3f(x0, y0, ovZFront), new Vector3f(x1, y1, ovZFront),
                                ovFrontFace, ovMat, Direction.SOUTH, BlockModelRotation.IDENTITY, null, true, 0
                        ));
                        allQuads.add(FaceBakery.bakeQuad(
                                this.modelBaker, new Vector3f(x0, y0, ovZBack), new Vector3f(x1, y1, ovZBack),
                                ovBackFace, ovMat, Direction.NORTH, BlockModelRotation.IDENTITY, null, true, 0
                        ));
                    }
                }
            }
        }

        List<BakedQuad> immutableQuads = List.copyOf(allQuads);
        return new CachedMesh(immutableQuads, CuboidItemModelWrapper.computeExtents(immutableQuads));
    }

    public record Unbaked() implements ItemModel.Unbaked {
        public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(new Unbaked());

        @Override
        public MapCodec<? extends ItemModel.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public void resolveDependencies(Resolver resolver) {
            resolver.markDependency(Identifier.withDefaultNamespace("item/generated"));
            resolver.markDependency(Identifier.withDefaultNamespace("item/handheld"));
        }

        @Override
        public ItemModel bake(ItemModel.BakingContext context, Matrix4fc parentTransform) {
            ModelBaker baker = context.blockModelBaker();

            ResolvedModel generatedModel = baker.getModel(Identifier.withDefaultNamespace("item/generated"));
            ModelRenderProperties generatedProps = ModelRenderProperties.fromResolvedModel(
                    baker, generatedModel, generatedModel.getTopTextureSlots()
            );

            ResolvedModel handheldModel = baker.getModel(Identifier.withDefaultNamespace("item/handheld"));
            ModelRenderProperties handheldProps = ModelRenderProperties.fromResolvedModel(
                    baker, handheldModel, handheldModel.getTopTextureSlots()
            );

            return new ModularItemModel(generatedProps, handheldProps, parentTransform, baker, context.blockModelBaker().materials());
        }
    }
}
