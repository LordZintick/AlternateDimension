package com.github.lordzintick.util;

import com.google.gson.JsonObject;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.AbstractPackResources;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.KnownPack;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;

import javax.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class InMemoryPackResources extends AbstractPackResources {
    private final Map<ResourceLocation, String> virtualFiles = new HashMap<>();
    private final PackType type;

    public InMemoryPackResources(String id, Component title, PackType type, PackSource packSource) {
        super(new PackLocationInfo(id, title, packSource, Optional.empty()));
        this.type = type;
    }

    public void addJson(ResourceLocation location, JsonObject json) {
        String prefix = this.type == PackType.SERVER_DATA ? "data" : "assets";
        ResourceLocation pathKey = ResourceLocation.fromNamespaceAndPath(
                location.getNamespace(),
                prefix + "/" + location.getNamespace() + "/" + location.getPath()
        );
        this.virtualFiles.put(pathKey, json.toString());
    }

    @Nullable
    @Override
    public IoSupplier<InputStream> getRootResource(String... elements) {
        // Handles internal pack.mcmeta reading
        if (elements.length == 1 && elements[0].equals("pack.mcmeta")) {
            String mockMeta = "{\"pack\":{\"description\":\"In-memory resources for \"" + this.packId() + "\",\"pack_format\":" + SharedConstants.getCurrentVersion().getPackVersion(this.type);
            return () -> new ByteArrayInputStream(mockMeta.getBytes(StandardCharsets.UTF_8));
        }
        return null;
    }

    @Nullable
    @Override
    public IoSupplier<InputStream> getResource(PackType type, ResourceLocation location) {
        if (this.type != type) return null;
        String content = this.virtualFiles.get(location);
        if (content == null) return null;
        return () -> new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void listResources(PackType type, String namespace, String path, ResourceOutput output) {
        if (this.type != type) return;
        String prefix = (this.type == PackType.SERVER_DATA ? "data/" : "assets/") + namespace + "/" + path;

        for (Map.Entry<ResourceLocation, String> entry : this.virtualFiles.entrySet()) {
            String fullPath = entry.getKey().getPath();
            if (entry.getKey().getNamespace().equals(namespace) && fullPath.startsWith(prefix)) {
                // Strip metadata prefix back to vanilla clean format for scanner
                String cleanedPath = fullPath.substring((this.type == PackType.SERVER_DATA ? "data/" : "assets/").length() + namespace.length() + 1);
                ResourceLocation cleanLoc = ResourceLocation.fromNamespaceAndPath(namespace, cleanedPath);
                output.accept(cleanLoc, () -> new ByteArrayInputStream(entry.getValue().getBytes(StandardCharsets.UTF_8)));
            }
        }
    }

    @Override
    public Set<String> getNamespaces(PackType type) {
        if (this.type != type) return Collections.emptySet();
        Set<String> namespaces = new HashSet<>();
        for (ResourceLocation loc : this.virtualFiles.keySet()) {
            namespaces.add(loc.getNamespace());
        }
        return namespaces;
    }

    @Override
    public void close() {}
}
