package com.lordkittycat.alternate_dimension.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.Identifier;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;

import com.lordkittycat.alternate_dimension.procedures.HandleEventPacketCProcedure;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

@EventBusSubscriber
public record EventPacketMessage(String extradata) implements CustomPacketPayload {
	public static final Type<EventPacketMessage> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "event_packet"));
	public static final StreamCodec<RegistryFriendlyByteBuf, EventPacketMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, EventPacketMessage message) -> {
		buffer.writeUtf(message.extradata);
	}, (RegistryFriendlyByteBuf buffer) -> new EventPacketMessage(buffer.readUtf()));

	@Override
	public Type<EventPacketMessage> type() {
		return TYPE;
	}

	public static void handleData(final EventPacketMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.CLIENTBOUND) {
			context.enqueueWork(() -> {
				Player entity = context.player();
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				String inboundString = message.extradata;

				HandleEventPacketCProcedure.execute(inboundString);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		AlternateDimensionMod.addNetworkMessage(EventPacketMessage.TYPE, EventPacketMessage.STREAM_CODEC, EventPacketMessage::handleData);
	}
}