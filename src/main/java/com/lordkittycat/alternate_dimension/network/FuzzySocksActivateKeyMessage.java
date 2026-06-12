package com.lordkittycat.alternate_dimension.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.SectionPos;

import com.lordkittycat.alternate_dimension.procedures.ActivateFuzzySocksAbilityProcedure;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

@EventBusSubscriber
public record FuzzySocksActivateKeyMessage(int eventType, int pressedms) implements CustomPacketPayload {
	public static final Type<FuzzySocksActivateKeyMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AlternateDimensionMod.MODID, "key_fuzzy_socks_activate_key"));
	public static final StreamCodec<RegistryFriendlyByteBuf, FuzzySocksActivateKeyMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, FuzzySocksActivateKeyMessage message) -> {
		buffer.writeInt(message.eventType);
		buffer.writeInt(message.pressedms);
	}, (RegistryFriendlyByteBuf buffer) -> new FuzzySocksActivateKeyMessage(buffer.readInt(), buffer.readInt()));

	@Override
	public Type<FuzzySocksActivateKeyMessage> type() {
		return TYPE;
	}

	public static void handleData(final FuzzySocksActivateKeyMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				pressAction(context.player(), message.eventType, message.pressedms);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void pressAction(Player entity, int type, int pressedms) {
		Level world = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		// security measure to prevent arbitrary chunk generation
		if (!world.getChunkSource().hasChunk(SectionPos.blockToSectionCoord(x), SectionPos.blockToSectionCoord(z)))
			return;
		if (type == 0) {

			ActivateFuzzySocksAbilityProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		AlternateDimensionMod.addNetworkMessage(FuzzySocksActivateKeyMessage.TYPE, FuzzySocksActivateKeyMessage.STREAM_CODEC, FuzzySocksActivateKeyMessage::handleData);
	}
}