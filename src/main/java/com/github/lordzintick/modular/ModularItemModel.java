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
    private final Map<List<Identifier>, CachedMesh> meshCache = new ConcurrentHashMap<>();

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
        List<Identifier> spriteKeys = new ArrayList<>(count);

        for (int index = 0; index != count; index++) {
            var entry = sortedParts.get(index);
            Identifier texId = entry.getKey().value().texture();
            int opaqueColor = ARGB.opaque(entry.getValue().value().color());

            spriteKeys.add(texId);
            tintLayers.add(opaqueColor);
            state.appendModelIdentityElement(texId);
            state.appendModelIdentityElement(opaqueColor);
        }

        CachedMesh mesh = meshCache.computeIfAbsent(List.copyOf(spriteKeys), this::bakeLayeredMesh);

        ModelRenderProperties activeProps = isHandheld ? handheldProperties : standardProperties;
        activeProps.applyToLayer(layer, displayContext);
        layer.setLocalTransform(this.transformation);
        layer.setExtents(mesh::extents);
        layer.prepareQuadList().addAll(mesh.quads());
    }

    private CachedMesh bakeLayeredMesh(List<Identifier> textureLocations) {
        List<BakedQuad> allQuads = new ArrayList<>();
        int count = textureLocations.size();
        CuboidFace.UVs fullUV = new CuboidFace.UVs(0.0f, 0.0f, 16.0f, 16.0f);

        for (int layerIndex = 0; layerIndex != count; layerIndex++) {
            Identifier texId = textureLocations.get(layerIndex);
            Material.Baked bakedMat = this.materialBaker.get(new Material(texId), texId::toString);

            // Slight Z offset per layer prevents Z-fighting on overlapping pixels
            float zFront = 8.5f + (layerIndex * 0.03f);
            float zBack = 7.5f - (layerIndex * 0.03f);

            CuboidFace face = new CuboidFace(null, layerIndex, "", fullUV, Quadrant.R0);

            // Front face (SOUTH)
            allQuads.add(FaceBakery.bakeQuad(
                    this.modelBaker,
                    new Vector3f(0.0f, 0.0f, zFront),
                    new Vector3f(16.0f, 16.0f, zFront),
                    face,
                    bakedMat,
                    Direction.SOUTH,
                    BlockModelRotation.IDENTITY,
                    null,
                    true,
                    0
            ));

            // Back face (NORTH)
            allQuads.add(FaceBakery.bakeQuad(
                    this.modelBaker,
                    new Vector3f(0.0f, 0.0f, zBack),
                    new Vector3f(16.0f, 16.0f, zBack),
                    face,
                    bakedMat,
                    Direction.NORTH,
                    BlockModelRotation.IDENTITY,
                    null,
                    true,
                    0
            ));
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
