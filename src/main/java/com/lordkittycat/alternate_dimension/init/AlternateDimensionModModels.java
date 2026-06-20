/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import com.lordkittycat.alternate_dimension.client.model.*;

@EventBusSubscriber(Dist.CLIENT)
public class AlternateDimensionModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelmeteoroid.LAYER_LOCATION, Modelmeteoroid::createBodyLayer);
		event.registerLayerDefinition(Modelgingerbread_warrior.LAYER_LOCATION, Modelgingerbread_warrior::createBodyLayer);
		event.registerLayerDefinition(Modelcrystal_chest.LAYER_LOCATION, Modelcrystal_chest::createBodyLayer);
		event.registerLayerDefinition(Modelplayer_model.LAYER_LOCATION, Modelplayer_model::createBodyLayer);
		event.registerLayerDefinition(Modelturtle.LAYER_LOCATION, Modelturtle::createBodyLayer);
		event.registerLayerDefinition(Modelroman.LAYER_LOCATION, Modelroman::createBodyLayer);
		event.registerLayerDefinition(Modelviking.LAYER_LOCATION, Modelviking::createBodyLayer);
		event.registerLayerDefinition(Modelcat.LAYER_LOCATION, Modelcat::createBodyLayer);
		event.registerLayerDefinition(Modelwolf.LAYER_LOCATION, Modelwolf::createBodyLayer);
		event.registerLayerDefinition(Modelcow_fixed.LAYER_LOCATION, Modelcow_fixed::createBodyLayer);
		event.registerLayerDefinition(Modelcandy_jetpack.LAYER_LOCATION, Modelcandy_jetpack::createBodyLayer);
		event.registerLayerDefinition(Modelspirit.LAYER_LOCATION, Modelspirit::createBodyLayer);
		event.registerLayerDefinition(Modelshroomite.LAYER_LOCATION, Modelshroomite::createBodyLayer);
		event.registerLayerDefinition(Modelpolar_bear.LAYER_LOCATION, Modelpolar_bear::createBodyLayer);
	}
}