package com.arise.fabricutility.features;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

public class FlightManager {
    private boolean enabled = false;

    public void toggle() {
        enabled = !enabled;
        MinecraftClient client = MinecraftClient.getInstance();
        
        if (client.player != null) {
            ClientPlayerEntity player = client.player;
            
            if (enabled) {
                // Enable flight
                player.getAbilities().allowFlying = true;
                player.getAbilities().flying = true;
                player.sendAbilitiesUpdate();
                player.sendMessage(Text.literal("§a[Flight] Enabled"), false);
            } else {
                // Disable flight (but keep allowFlying if in creative)
                boolean wasCreative = player.isCreative() || player.isSpectator();
                player.getAbilities().allowFlying = wasCreative;
                player.getAbilities().flying = false;
                player.sendAbilitiesUpdate();
                player.sendMessage(Text.literal("§c[Flight] Disabled"), false);
            }
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void updateFlight(ClientPlayerEntity player) {
        if (enabled && !player.isCreative() && !player.isSpectator()) {
            player.getAbilities().allowFlying = true;
        }
    }
}
