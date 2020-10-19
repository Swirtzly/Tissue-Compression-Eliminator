package com.wc.tce.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.wc.tce.common.tiles.TCETile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3f;

public class RenderTCE extends TileEntityRenderer<TCETile> {

    public RenderTCE(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TCETile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (tileEntityIn.getEntity() == null) return;
        matrixStackIn.scale(0.2F, 0.2F, 0.2F);
        matrixStackIn.translate(2.5, 0, 2.5);
        Entity entity = TCETile.createEntity(tileEntityIn.getEntity(), Minecraft.getInstance().world);
        if(entity == null) return;
        EntityRenderer<? super Entity> renderer = Minecraft.getInstance().getRenderManager().getRenderer(entity);
        renderer.render(entity, entity.rotationYaw, 0, matrixStackIn, bufferIn, 15728880); //TODO They fucking glow at night....
    }
}
