package com.arise.fabricutility;

import com.arise.fabricutility.client.KeyBindings;
import com.arise.fabricutility.features.FlightManager;
import com.arise.fabricutility.features.ESPManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class FabricUtilityClient implements ClientModInitializer {
    public static final FlightManager FLIGHT_MANAGER = new FlightManager();
    public static final ESPManager ESP_MANAGER = new ESPManager();

    @Override
    public void onInitializeClient() {
        // Register keybindings
        KeyBindings.register();

        // Register tick event to handle key presses
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            KeyBindings.handleKeyPresses();
        });

        System.out.println("Fabric Utility Mod initialized!");
    }
}
