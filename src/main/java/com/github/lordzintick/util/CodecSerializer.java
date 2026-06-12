package com.github.lordzintick.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CodecSerializer {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Serializes any object into a Gson JsonObject using its Codec.
     */
    public static <T> JsonObject serializeObjectToJson(Codec<T> codec, T objectInstance) {
        // Encode the object using Mojang's JsonOps system
        return codec.encodeStart(JsonOps.INSTANCE, objectInstance)
                .map(JsonElement::getAsJsonObject) // Extract it as a standard GSON object
                .resultOrPartial(error -> LOGGER.error("Failed to serialize runtime data pack entry: {}", error))
                .orElseGet(JsonObject::new); // Fallback to safe empty object if serialization breaks
    }
}
