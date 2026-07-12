package com.lordkittycat.alternate_dimension.client.renderer.item;

import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.Identifier;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.Minecraft;

import java.util.Map;
import java.util.Collections;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.lordkittycat.alternate_dimension.client.model.Modelcandy_jetpack;

@EventBusSubscriber(Dist.CLIENT)
public class CandyArmorArmor {
	@SubscribeEvent
	public static void registerItemExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(new IClientItemExtensions() {
			private final Identifier armorTexture = Identifier.parse("alternate_dimension:textures/entities/candy_jetpack_texture.png");
			private HumanoidModel<?> armorModel = null;

			@Override
			public Model getHumanoidArmorModel(ItemStack itemStack, EquipmentClientInfo.LayerType layerType, Model original) {
				if (armorModel == null) {
					Modelcandy_jetpack model = new Modelcandy_jetpack(Minecraft.getInstance().getEntityModels().bakeLayer(Modelcandy_jetpack.LAYER_LOCATION));
					armorModel = new HumanoidModel<>(new ModelPart(Collections.emptyList(),
							Map.of("body", model.jetpack, "left_arm", model.armorLeftArm, "right_arm", model.armorRightArm, "head", new ModelPart(Collections.emptyList(), Map.of("hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()))),
									"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
				}
				return armorModel;
			}

			@Override
			public Identifier getArmorTexture(ItemStack stack, EquipmentClientInfo.LayerType type, EquipmentClientInfo.Layer layer, Identifier original) {
				return armorTexture;
			}
		}, AlternateDimensionModItems.CANDY_ARMOR_CHESTPLATE.get());
	}
}