package com.github.lordzintick.item.jetpack;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record JetpackTogglePayload(boolean toggleHover) implements CustomPacketPayload {
    public static final Type<JetpackTogglePayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "jetpack_toggle"));

    public static final StreamCodec<ByteBuf, JetpackTogglePayload> STREAM_CODEC =
            ByteBufCodecs.BOOL.map(JetpackTogglePayload::new, JetpackTogglePayload::toggleHover);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleServer(JetpackTogglePayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) return;

            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            JetpackComponent jetpack = chest.get(AlternateDimensionModComponents.JETPACK);
            if (jetpack == null) return;

            JetpackComponent updated = payload.toggleHover()
                    ? jetpack.withToggledHover()
                    : jetpack.withToggledEngine();

            chest.set(AlternateDimensionModComponents.JETPACK, updated);

            String key = payload.toggleHover()
                    ? (updated.hoverOn() ? "message.alternate_dimension.jetpack.hover_on" : "message.alternate_dimension.jetpack.hover_off")
                    : (updated.engineOn() ? "message.alternate_dimension.jetpack.engine_on" : "message.alternate_dimension.jetpack.engine_off");

            player.sendOverlayMessage(
                    Component.translatable(key).withStyle(
                            (payload.toggleHover() ? updated.hoverOn() : updated.engineOn())
                                    ? ChatFormatting.GREEN
                                    : ChatFormatting.RED
                    )
            );
        });
    }
}