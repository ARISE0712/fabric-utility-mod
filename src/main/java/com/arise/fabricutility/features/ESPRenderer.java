package com.arise.fabricutility.features;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class ESPRenderer {
    
    public static void renderEntityESP(MatrixStack matrices, Entity entity, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        Camera camera = client.gameRenderer.getCamera();
        Vec3d cameraPos = camera.getPos();
        
        // Get entity position
        Vec3d entityPos = entity.getPos();
        double x = entityPos.x - cameraPos.x;
        double y = entityPos.y - cameraPos.y;
        double z = entityPos.z - cameraPos.z;
        
        // Get entity bounding box
        Box box = entity.getBoundingBox().offset(-entityPos.x, -entityPos.y, -entityPos.z);
        
        matrices.push();
        matrices.translate(x, y, z);
        
        // Determine color based on entity type
        float red, green, blue;
        if (entity instanceof PlayerEntity && !(entity == client.player)) {
            // Players: Red
            red = 1.0f;
            green = 0.0f;
            blue = 0.0f;
        } else if (entity instanceof MobEntity) {
            // Mobs: Orange
            red = 1.0f;
            green = 0.5f;
            blue = 0.0f;
        } else if (entity instanceof ItemEntity) {
            // Items: Green
            red = 0.0f;
            green = 1.0f;
            blue = 0.0f;
        } else {
            matrices.pop();
            return;
        }
        
        // Render the box
        renderBox(matrices, box, red, green, blue, 1.0f);
        
        matrices.pop();
    }
    
    private static void renderBox(MatrixStack matrices, Box box, float red, float green, float blue, float alpha) {
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
        
        // Draw the 12 edges of the box
        float minX = (float) box.minX;
        float minY = (float) box.minY;
        float minZ = (float) box.minZ;
        float maxX = (float) box.maxX;
        float maxY = (float) box.maxY;
        float maxZ = (float) box.maxZ;
        
        // Bottom face
        buffer.vertex(matrix, minX, minY, minZ).color(red, green, blue, alpha);
        buffer.vertex(matrix, maxX, minY, minZ).color(red, green, blue, alpha);
        
        buffer.vertex(matrix, maxX, minY, minZ).color(red, green, blue, alpha);
        buffer.vertex(matrix, maxX, minY, maxZ).color(red, green, blue, alpha);
        
        buffer.vertex(matrix, maxX, minY, maxZ).color(red, green, blue, alpha);
        buffer.vertex(matrix, minX, minY, maxZ).color(red, green, blue, alpha);
        
        buffer.vertex(matrix, minX, minY, maxZ).color(red, green, blue, alpha);
        buffer.vertex(matrix, minX, minY, minZ).color(red, green, blue, alpha);
        
        // Top face
        buffer.vertex(matrix, minX, maxY, minZ).color(red, green, blue, alpha);
        buffer.vertex(matrix, maxX, maxY, minZ).color(red, green, blue, alpha);
        
        buffer.vertex(matrix, maxX, maxY, minZ).color(red, green, blue, alpha);
        buffer.vertex(matrix, maxX, maxY, maxZ).color(red, green, blue, alpha);
        
        buffer.vertex(matrix, maxX, maxY, maxZ).color(red, green, blue, alpha);
        buffer.vertex(matrix, minX, maxY, maxZ).color(red, green, blue, alpha);
        
        buffer.vertex(matrix, minX, maxY, maxZ).color(red, green, blue, alpha);
        buffer.vertex(matrix, minX, maxY, minZ).color(red, green, blue, alpha);
        
        // Vertical edges
        buffer.vertex(matrix, minX, minY, minZ).color(red, green, blue, alpha);
        buffer.vertex(matrix, minX, maxY, minZ).color(red, green, blue, alpha);
        
        buffer.vertex(matrix, maxX, minY, minZ).color(red, green, blue, alpha);
        buffer.vertex(matrix, maxX, maxY, minZ).color(red, green, blue, alpha);
        
        buffer.vertex(matrix, maxX, minY, maxZ).color(red, green, blue, alpha);
        buffer.vertex(matrix, maxX, maxY, maxZ).color(red, green, blue, alpha);
        
        buffer.vertex(matrix, minX, minY, maxZ).color(red, green, blue, alpha);
        buffer.vertex(matrix, minX, maxY, maxZ).color(red, green, blue, alpha);
        
        BufferRenderer.drawWithGlobalProgram(buffer.end());
    }
}
