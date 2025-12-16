package com.arise.fabricutility.mixin;

import com.arise.fabricutility.FabricUtilityClient;
import com.arise.fabricutility.features.ESPRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    
    @Shadow @Final private MinecraftClient client;
    
    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(RenderTickCounter tickCounter, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, Matrix4f matrix4f2, CallbackInfo ci) {
        if (!FabricUtilityClient.ESP_MANAGER.isEnabled()) {
            return;
        }
        
        if (client.world == null) {
            return;
        }
        
        MatrixStack matrices = new MatrixStack();
        matrices.multiplyPositionMatrix(matrix4f);
        
        // Set up rendering for ESP (no depth testing to see through walls)
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.lineWidth(2.0f);
        
        // Render ESP for all entities
        for (Entity entity : client.world.getEntities()) {
            if (entity != client.player) {
                ESPRenderer.renderEntityESP(matrices, entity, tickCounter.getTickDelta(true));
            }
        }
        
        // Restore render state
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.lineWidth(1.0f);
    }
}
