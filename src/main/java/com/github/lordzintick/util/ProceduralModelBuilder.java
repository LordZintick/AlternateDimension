package com.github.lordzintick.util;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.BlockElementFace;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class ProceduralModelBuilder {
    private static final FaceBakery FACE_BAKERY = new FaceBakery();

    /**
     * Generates a 3D cuboid made of up to 6 BakedQuads entirely via code.
     * Coordinates are defined 0-16 (standard voxel space).
     */
    public static List<BakedQuad> createBox(float minX, float minY, float minZ,
                                            float maxX, float maxY, float maxZ,
                                            TextureAtlasSprite sprite) {
        List<BakedQuad> quads = new ArrayList<>();

        Vector3f from = new Vector3f(minX, minY, minZ);
        Vector3f to = new Vector3f(maxX, maxY, maxZ);

        // Generate a quad for all 6 faces of the cube
        for (Direction direction : Direction.values()) {
            quads.add(bakeFace(from, to, direction, sprite));
        }

        return quads;
    }

    private static BakedQuad bakeFace(Vector3f from, Vector3f to, Direction direction, TextureAtlasSprite sprite) {
        // Define UV mapping based on the face bounds (clamped 0-16)
        float[] uvData = switch (direction) {
            case DOWN, UP -> new float[]{from.x(), from.z(), to.x(), to.z()};
            case NORTH, SOUTH -> new float[]{from.x(), from.y(), to.x(), to.y()};
            case WEST, EAST -> new float[]{from.z(), from.y(), to.z(), to.y()};
        };

        BlockFaceUV faceUV = new BlockFaceUV(uvData, 0); // 0 rotation
        BlockElementFace elementFace = new BlockElementFace(direction, -1, "", faceUV);

        // Use Minecraft's internal baker to compute lighting, normals, and tinting
        return FACE_BAKERY.bakeQuad(
                from, to,
                elementFace,
                sprite,
                direction,
                BlockModelRotation.X0_Y0, // Default rotation matrix
                null, // No custom rotation
                true // Apply ambient occlusion/shade
        );
    }
}
