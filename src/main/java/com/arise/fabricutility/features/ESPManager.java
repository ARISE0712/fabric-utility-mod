package com.arise.fabricutility.features;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ESPManager {
    private boolean enabled = false;

    public void toggle() {
        enabled = !enabled;
        MinecraftClient client = MinecraftClient.getInstance();
        
        if (client.player != null) {
            if (enabled) {
                client.player.sendMessage(Text.literal("§a[ESP] Enabled"), false);
            } else {
                client.player.sendMessage(Text.literal("§c[ESP] Disabled"), false);
            }
        }
    }

    public boolean isEnabled() {
        return enabled;
    }
}
