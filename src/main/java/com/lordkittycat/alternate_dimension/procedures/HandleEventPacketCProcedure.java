package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.client.Minecraft;

import com.lordkittycat.alternate_dimension.client.toasts.MoonEventEndToastToast;
import com.lordkittycat.alternate_dimension.client.toasts.BlueMoonToastToast;
import com.lordkittycat.alternate_dimension.client.toasts.BloodMoonToastToast;

public class HandleEventPacketCProcedure {
	public static void execute(String inboundString) {
		if (inboundString == null)
			return;
		if (inboundString.contains("start")) {
			if (inboundString.contains("blood_moon")) {
				Minecraft.getInstance().getToastManager().addToast(new BloodMoonToastToast());
			} else if (inboundString.contains("blue_moon")) {
				Minecraft.getInstance().getToastManager().addToast(new BlueMoonToastToast());
			}
		} else {
			if (inboundString.contains("moon")) {
				Minecraft.getInstance().getToastManager().addToast(new MoonEventEndToastToast());
			}
		}
	}
}