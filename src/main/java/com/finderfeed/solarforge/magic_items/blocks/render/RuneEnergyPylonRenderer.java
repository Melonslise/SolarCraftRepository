package com.finderfeed.solarforge.magic_items.blocks.render;

import com.finderfeed.solarforge.magic_items.blocks.blockentities.RuneEnergyPylonTile;
import com.finderfeed.solarforge.rendering.shaders.Shaders;
import com.finderfeed.solarforge.rendering.shaders.Uniform;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;

import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;

import com.mojang.math.Vector3f;

public class RuneEnergyPylonRenderer implements BlockEntityRenderer<RuneEnergyPylonTile> {
    public final ResourceLocation MAIN = new ResourceLocation("solarforge","textures/misc/tile_energy_pylon.png");
    public RuneEnergyPylonRenderer(BlockEntityRendererProvider.Context ctx) {

    }

    @Override
    public void render(RuneEnergyPylonTile tile, float partialTicks, PoseStack matrices, MultiBufferSource buffer, int light, int overlay) {
        if (tile.getEnergyType() != null) {
            VertexConsumer vertex = buffer.getBuffer(RenderType.text(new ResourceLocation("solarforge", "textures/misc/tile_energy_pylon_" + tile.getEnergyType().id + ".png")));
            float time = (Minecraft.getInstance().level.getGameTime()+partialTicks);
            for (int i = 0; i < 8; i ++) {
                matrices.pushPose();

                matrices.translate(0.5, 0.5, 0.5f);
                matrices.scale(0.7f,0.7f,0.7f);
                matrices.mulPose(Vector3f.YP.rotationDegrees(i*45+time%360));
                Matrix4f matrix1 = matrices.last().pose();
                vertex.vertex(matrix1, -0.5f, 0.5f, 1.5f).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
                vertex.vertex(matrix1, 0.5f, 0.5f, 1.5f).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
                vertex.vertex(matrix1, 0.5f, -0.5f, 1.5f).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
                vertex.vertex(matrix1, -0.5f, -0.5f, 1.5f).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();

                vertex.vertex(matrix1, -0.5f, -0.5f, 1.5f).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
                vertex.vertex(matrix1, 0.5f, -0.5f, 1.5f).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
                vertex.vertex(matrix1, 0.5f, 0.5f, 1.5f).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
                vertex.vertex(matrix1, -0.5f, 0.5f, 1.5f).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
                matrices.popPose();
            }
        }


        matrices.pushPose();
        VertexConsumer vertex = buffer.getBuffer(RenderType.text(MAIN));
        matrices.translate(0.5,0.5,0.5);



        Quaternion quaternion = Minecraft.getInstance().gameRenderer.getMainCamera().rotation();

        float time = (Minecraft.getInstance().level.getGameTime()+partialTicks)*5;

        matrices.mulPose(quaternion);
        matrices.mulPose(Vector3f.ZP.rotationDegrees(time%360));
        matrices.scale(1.5f,1.5f,1.5f);
        Matrix4f matrix = matrices.last().pose();
        vertex.vertex(matrix, -0.5f,0.5f,0).color(255, 255, 0, 200).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
        vertex.vertex(matrix,  0.5f,0.5f,0).color(255, 255, 0, 200).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
        vertex.vertex(matrix,  0.5f,-0.5f,0).color(255, 255, 0, 200).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
        vertex.vertex(matrix,  -0.5f,-0.5f,0).color(255, 255, 0, 200).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
        matrices.popPose();

        matrices.pushPose();
        matrices.translate(0.5,0.5,0.5);
        matrices.mulPose(quaternion);
        matrices.mulPose(Vector3f.ZN.rotationDegrees(time%360));
        Matrix4f matrix2 = matrices.last().pose();
        matrices.scale(1.5f,1.5f,1.5f);
        vertex.vertex(matrix2, -0.5f,0.5f,0.001f).color(255, 255, 0, 200).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
        vertex.vertex(matrix2,  0.5f,0.5f,0.001f).color(255, 255, 0, 200).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
        vertex.vertex(matrix2,  0.5f,-0.5f,0.001f).color(255, 255, 0, 200).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();
        vertex.vertex(matrix2,  -0.5f,-0.5f,0.001f).color(255, 255, 0, 200).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).endVertex();

//        Vector3d pos = new Vector3d(tile.getBlockPos().getX()+0.5f,tile.getBlockPos().getY()+0.5f,tile.getBlockPos().getZ()+0.5f);
//        Vector3d playerpos = Minecraft.getInstance().player.position().add(0,1.5,0);
//
//        float length = (float)new Vector3d(pos.x - playerpos.x,pos.y - playerpos.y,pos.z - playerpos.z).length();


//        matrices.popPose();
//
////        Matrix4f projectionMatrix = Minecraft.getInstance().gameRenderer.getProjectionMatrix(Minecraft.getInstance().gameRenderer.getMainCamera(),1,true);
////
////        matrices.pushPose();
////        matrices.translate(0.5,0.5,0.5);
////        Matrix4f matrix4f = matrices.last().pose();
////        Shaders.TEST.getShader().addUniform(new Uniform("modelview",matrix4f,Shaders.TEST.getShader().getSHADER()));
////        Shaders.TEST.getShader().addUniform(new Uniform("projection",projectionMatrix,Shaders.TEST.getShader().getSHADER()));
////
////        Shaders.TEST.getShader().addUniform(new Uniform("intensity",5f,Shaders.TEST.getShader().getSHADER()));
////        Shaders.TEST.getShader().addUniform(new Uniform("scaleFactor",200f,Shaders.TEST.getShader().getSHADER()));
////        Shaders.TEST.getShader().addUniform(new Uniform("distance",length*length,Shaders.TEST.getShader().getSHADER()));
////
////        Shaders.TEST.getShader().setActive(true);
//        matrices.popPose();
        matrices.popPose();
    }
}
