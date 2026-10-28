package com.lordkittycat.alternate_dimension;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.fml.util.thread.SidedThreadGroups;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.TickTask;
import net.minecraft.resources.Identifier;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.registries.BuiltInRegistries;

import javax.annotation.Nullable;

import java.util.function.Consumer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

import java.lang.invoke.MethodType;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandle;

import it.unimi.dsi.fastutil.ints.IntObjectPair;
import it.unimi.dsi.fastutil.ints.IntObjectImmutablePair;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;
import com.lordkittycat.alternate_dimension.init.*;

import com.github.lordzintick.magic.loader.SpellLoader;
import com.github.lordzintick.magic.SpellUtil;
import com.github.lordzintick.init.AlternateDimensionModRecipes;
import com.github.lordzintick.init.AlternateDimensionModComponents;

@Mod("alternate_dimension")
public class AlternateDimensionMod {
	public static final Logger LOGGER = LogManager.getLogger(AlternateDimensionMod.class);
	public static final String MODID = "alternate_dimension";

	public AlternateDimensionMod(IEventBus modEventBus) {
		// Start of user code block mod constructor
		// End of user code block mod constructor
		NeoForge.EVENT_BUS.register(this);
		modEventBus.addListener(this::registerNetworking);
		AlternateDimensionModSounds.REGISTRY.register(modEventBus);
		AlternateDimensionModBlocks.REGISTRY.register(modEventBus);
		AlternateDimensionModBlockEntities.REGISTRY.register(modEventBus);
		AlternateDimensionModItems.REGISTRY.register(modEventBus);
		AlternateDimensionModEntities.REGISTRY.register(modEventBus);
		AlternateDimensionModTabs.REGISTRY.register(modEventBus);
		AlternateDimensionModVariables.ATTACHMENT_TYPES.register(modEventBus);
		AlternateDimensionModMobEffects.REGISTRY.register(modEventBus);
		AlternateDimensionModMenus.REGISTRY.register(modEventBus);
		AlternateDimensionModFluids.REGISTRY.register(modEventBus);
		AlternateDimensionModFluidTypes.REGISTRY.register(modEventBus);
		// Start of user code block mod init
		AlternateDimensionModComponents.register(modEventBus);
		AlternateDimensionModRecipes.register(modEventBus);
		SpellLoader.SPELLS.addDefaults();
		SpellLoader.SPELL_PROVIDERS.register(MODID, SpellUtil.class);
		// End of user code block mod init
	}

	// Start of user code block mod methods
	private static final Map<Runnable, Long> scheduledTasks = new HashMap<>();

	public static void scheduleTask(Runnable task, int delay) {
		long targetTick = System.currentTimeMillis() + (delay * 50L);
		scheduledTasks.put(task, targetTick);
	}

	public static void scheduleRepeatedTask(Consumer<Integer> task, int delay, int times) {
		for (int i = 0; i < times; i++) {
			int finalI = i;
			scheduleTask(() -> task.accept(finalI), delay);
		}
	}

	@SubscribeEvent
	public void serverTick(ServerTickEvent.Post event) {
		long time = System.currentTimeMillis();
		scheduledTasks.entrySet().removeIf(entry -> {
			if (time >= entry.getValue()) {
				entry.getKey().run();
				return true;
			}
			return false;
		});
	}

	// End of user code block mod methods
	private static boolean networkingRegistered = false;
	private static final Map<CustomPacketPayload.Type<?>, NetworkMessage<?>> MESSAGES = new HashMap<>();

	private record NetworkMessage<T extends CustomPacketPayload>(StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
	}

	public static <T extends CustomPacketPayload> void addNetworkMessage(CustomPacketPayload.Type<T> id, StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
		if (networkingRegistered)
			throw new IllegalStateException("Cannot register new network messages after networking has been registered");
		MESSAGES.put(id, new NetworkMessage<>(reader, handler));
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void registerNetworking(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar(MODID);
		MESSAGES.forEach((id, networkMessage) -> registrar.playBidirectional(id, ((NetworkMessage) networkMessage).reader(), ((NetworkMessage) networkMessage).handler(), ((NetworkMessage) networkMessage).handler()));
		networkingRegistered = true;
	}

	private static final Queue<IntObjectPair<Runnable>> workToBeScheduled = new ConcurrentLinkedQueue<>();
	private static final PriorityQueue<TickTask> workQueue = new PriorityQueue<>(Comparator.comparingInt(TickTask::getTick));

	public static void queueServerWork(int delay, Runnable action) {
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
			workToBeScheduled.add(new IntObjectImmutablePair<>(delay, action));
	}

	@SubscribeEvent
	public void tick(ServerTickEvent.Post event) {
		int currentTick = event.getServer().getTickCount();
		IntObjectPair<Runnable> work;
		while ((work = workToBeScheduled.poll()) != null) {
			workQueue.add(new TickTask(currentTick + work.leftInt(), work.right()));
		}
		while (!workQueue.isEmpty() && currentTick >= workQueue.peek().getTick()) {
			workQueue.poll().run();
		}
	}

	private static Object minecraft;
	private static MethodHandle playerHandle;

	@Nullable
	public static Player clientPlayer() {
		if (FMLEnvironment.getDist().isClient()) {
			try {
				if (minecraft == null || playerHandle == null) {
					Class<?> minecraftClass = Class.forName("net.minecraft.client.Minecraft");
					minecraft = MethodHandles.publicLookup().findStatic(minecraftClass, "getInstance", MethodType.methodType(minecraftClass)).invoke();
					playerHandle = MethodHandles.publicLookup().findGetter(minecraftClass, "player", Class.forName("net.minecraft.client.player.LocalPlayer"));
				}
				return (Player) playerHandle.invoke(minecraft);
			} catch (Throwable e) {
				LOGGER.error("Failed to get client player", e);
				return null;
			}
		} else {
			return null;
		}
	}

	public static class CuriosApiHelper {
		private static final EntityCapability<ResourceHandler, Void> CURIOS_INVENTORY = EntityCapability.createVoid(Identifier.fromNamespaceAndPath("curios", "item_handler"), ResourceHandler.class);

		public static ResourceHandler<ItemResource> getCuriosInventory(Player player) {
			if (ModList.get().isLoaded("curios")) {
				return player.getCapability(CURIOS_INVENTORY);
			}
			return null;
		}

		public static boolean isCurioItem(ItemStack itemstack) {
			return BuiltInRegistries.ITEM.getTags().filter(named -> named.key().location().getNamespace().equals("curios")).anyMatch(named -> itemstack.is(named.key()));
		}
	}
}