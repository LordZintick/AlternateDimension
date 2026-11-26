package com.github.lordzintick;

import com.github.lordzintick.modular.ModularArmorLayer;
import com.github.lordzintick.modular.ModularArmorRenderData;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.player.PlayerModelType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod(value = AlternateDimensionMod.MODID, dist = Dist.CLIENT)
public class AlternateDimensionModClient {
    public AlternateDimensionModClient(IEventBus eventBus) {
        eventBus.addListener(this::onAddLayers);
        eventBus.addListener(ModularArmorRenderData::onRegisterRenderStateModifiers);
    }

    private void onAddLayers(EntityRenderersEvent.AddLayers event) {
        for (PlayerModelType skin : event.getSkins()) {
            AvatarRenderer<AbstractClientPlayer> renderer = event.getPlayerRenderer(skin);
            if (renderer != null) {
                boolean isSlim = (skin == PlayerModelType.SLIM);
                ArmorModelSet<PlayerModel> playerArmorModels = ArmorModelSet.bake(
                        ModelLayers.PLAYER_ARMOR,
                        event.getEntityModels(),
                        part -> new PlayerModel(part, isSlim)
                );

                renderer.addLayer(new ModularArmorLayer<>(renderer, playerArmorModels));
            }
        }
    }
}
