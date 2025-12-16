package com.arise.fabricutility.client;

import com.arise.fabricutility.FabricUtilityClient;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    private static KeyBinding flightKey;
    private static KeyBinding espKey;

    public static void register() {
        flightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fabricutility.flight",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F,
                "category.fabricutility"
        ));

        espKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fabricutility.esp",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                "category.fabricutility"
        ));
    }

    public static void handleKeyPresses() {
        while (flightKey.wasPressed()) {
            FabricUtilityClient.FLIGHT_MANAGER.toggle();
        }

        while (espKey.wasPressed()) {
            FabricUtilityClient.ESP_MANAGER.toggle();
        }
    }
}
