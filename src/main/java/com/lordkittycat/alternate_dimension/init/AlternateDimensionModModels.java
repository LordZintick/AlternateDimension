/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.resources.Identifier;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;

import com.lordkittycat.alternate_dimension.client.model.*;

@EventBusSubscriber(Dist.CLIENT)
public class AlternateDimensionModModels {
	public static final ModelLayerLocation AZURE_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("alternate_dimension:boat/azure_boat"), "main");
	public static final ModelLayerLocation AZURE_CHEST_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("alternate_dimension:chest_boat/azure_chest_boat"), "main");
	public static final ModelLayerLocation SCORCHED_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("alternate_dimension:boat/scorched_boat"), "main");
	public static final ModelLayerLocation SCORCHED_CHEST_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("alternate_dimension:chest_boat/scorched_chest_boat"), "main");
	public static final ModelLayerLocation REDWOOD_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("alternate_dimension:boat/redwood_boat"), "main");
	public static final ModelLayerLocation REDWOOD_CHEST_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("alternate_dimension:chest_boat/redwood_chest_boat"), "main");
	public static final ModelLayerLocation PINE_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("alternate_dimension:boat/pine_boat"), "main");
	public static final ModelLayerLocation PINE_CHEST_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("alternate_dimension:chest_boat/pine_chest_boat"), "main");
	public static final ModelLayerLocation GREENWOOD_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("alternate_dimension:boat/greenwood_boat"), "main");
	public static final ModelLayerLocation GREENWOOD_CHEST_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("alternate_dimension:chest_boat/greenwood_chest_boat"), "main");
	public static final ModelLayerLocation CANDYFLOSS_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("alternate_dimension:boat/candyfloss_boat"), "main");
	public static final ModelLayerLocation CANDYFLOSS_CHEST_BOAT_LAYER_LOCATION = new ModelLayerLocation(Identifier.parse("alternate_dimension:chest_boat/candyfloss_chest_boat"), "main");

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelcrystal_chest.LAYER_LOCATION, Modelcrystal_chest::createBodyLayer);
		event.registerLayerDefinition(Modelspore_spreader.LAYER_LOCATION, Modelspore_spreader::createBodyLayer);
		event.registerLayerDefinition(Modelspider_hydra.LAYER_LOCATION, Modelspider_hydra::createBodyLayer);
		event.registerLayerDefinition(Modelroman.LAYER_LOCATION, Modelroman::createBodyLayer);
		event.registerLayerDefinition(Modelviking.LAYER_LOCATION, Modelviking::createBodyLayer);
		event.registerLayerDefinition(Modelcat.LAYER_LOCATION, Modelcat::createBodyLayer);
		event.registerLayerDefinition(Modelwolf.LAYER_LOCATION, Modelwolf::createBodyLayer);
		event.registerLayerDefinition(Modelcow_fixed.LAYER_LOCATION, Modelcow_fixed::createBodyLayer);
		event.registerLayerDefinition(Modelshroomite.LAYER_LOCATION, Modelshroomite::createBodyLayer);
		event.registerLayerDefinition(Modelpossessed_armor.LAYER_LOCATION, Modelpossessed_armor::createBodyLayer);
		event.registerLayerDefinition(Modelmeteoroid.LAYER_LOCATION, Modelmeteoroid::createBodyLayer);
		event.registerLayerDefinition(Modelgingerbread_warrior.LAYER_LOCATION, Modelgingerbread_warrior::createBodyLayer);
		event.registerLayerDefinition(Modelzombie.LAYER_LOCATION, Modelzombie::createBodyLayer);
		event.registerLayerDefinition(Modelplayer_model.LAYER_LOCATION, Modelplayer_model::createBodyLayer);
		event.registerLayerDefinition(Modelturtle.LAYER_LOCATION, Modelturtle::createBodyLayer);
		event.registerLayerDefinition(Modelspider.LAYER_LOCATION, Modelspider::createBodyLayer);
		event.registerLayerDefinition(Modelspirit.LAYER_LOCATION, Modelspirit::createBodyLayer);
		event.registerLayerDefinition(Modelcandy_jetpack.LAYER_LOCATION, Modelcandy_jetpack::createBodyLayer);
		event.registerLayerDefinition(Modelpolar_bear.LAYER_LOCATION, Modelpolar_bear::createBodyLayer);
		event.registerLayerDefinition(Modelarrow_model.LAYER_LOCATION, Modelarrow_model::createBodyLayer);
		event.registerLayerDefinition(AZURE_BOAT_LAYER_LOCATION, BoatModel::createBoatModel);
		event.registerLayerDefinition(AZURE_CHEST_BOAT_LAYER_LOCATION, BoatModel::createChestBoatModel);
		event.registerLayerDefinition(SCORCHED_BOAT_LAYER_LOCATION, BoatModel::createBoatModel);
		event.registerLayerDefinition(SCORCHED_CHEST_BOAT_LAYER_LOCATION, BoatModel::createChestBoatModel);
		event.registerLayerDefinition(REDWOOD_BOAT_LAYER_LOCATION, BoatModel::createBoatModel);
		event.registerLayerDefinition(REDWOOD_CHEST_BOAT_LAYER_LOCATION, BoatModel::createChestBoatModel);
		event.registerLayerDefinition(PINE_BOAT_LAYER_LOCATION, BoatModel::createBoatModel);
		event.registerLayerDefinition(PINE_CHEST_BOAT_LAYER_LOCATION, BoatModel::createChestBoatModel);
		event.registerLayerDefinition(GREENWOOD_BOAT_LAYER_LOCATION, BoatModel::createBoatModel);
		event.registerLayerDefinition(GREENWOOD_CHEST_BOAT_LAYER_LOCATION, BoatModel::createChestBoatModel);
		event.registerLayerDefinition(CANDYFLOSS_BOAT_LAYER_LOCATION, BoatModel::createBoatModel);
		event.registerLayerDefinition(CANDYFLOSS_CHEST_BOAT_LAYER_LOCATION, BoatModel::createChestBoatModel);
	}
}