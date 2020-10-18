package com.wc.tce.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.wc.tce.common.tiles.TCETile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;

public class RenderTCE extends TileEntityRenderer<TCETile> {

    public RenderTCE(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TCETile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (tileEntityIn.getEntity() == null) return;
        matrixStackIn.scale(0.5F, 0.5F, 0.5F);
        matrixStackIn.translate(1, 0, 0.7);
        Entity entity = TCETile.createEntity(tileEntityIn.getEntity(), Minecraft.getInstance().world);
        EntityRenderer<? super Entity> renderer = Minecraft.getInstance().getRenderManager().getRenderer(entity);
        renderer.render(entity, entity.rotationYaw, 0, matrixStackIn, bufferIn, 15728880); //TODO They fucking glow at night....
    }
}
