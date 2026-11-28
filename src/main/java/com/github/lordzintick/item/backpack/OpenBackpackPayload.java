package com.github.lordzintick.item.backpack;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record OpenBackpackPayload() implements CustomPacketPayload {
    public static final OpenBackpackPayload INSTANCE = new OpenBackpackPayload();
    public static final Type<OpenBackpackPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "open_backpack"));

    public static final StreamCodec<ByteBuf, OpenBackpackPayload> STREAM_CODEC =
            StreamCodec.unit(INSTANCE);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleServer(OpenBackpackPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) return;

            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            BackpackInventoryComponent backpack = chest.get(AlternateDimensionModComponents.BACKPACK_INVENTORY);
            if (backpack == null) return;

            int slots = backpack.slots();
            player.openMenu(
                    new SimpleMenuProvider(
                            (containerId, playerInv, p) -> new BackpackMenu(containerId, playerInv, slots),
                            chest.getHoverName()
                    ),
                    buf -> buf.writeVarInt(slots)
            );
        });
    }
}
