package me.swirtzly.tce.renders;

import com.mojang.blaze3d.platform.GlStateManager;
import me.swirtzly.tce.entity.ScaledEntity;
import me.swirtzly.tce.util.TCEUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * Created by Swirtzly
 * on 15/01/2020 @ 21:16
 */
public class RenderScaledEntity extends EntityRenderer<ScaledEntity> {

    private EntityRenderer render = null;

    public RenderScaledEntity(EntityRendererManager renderer) {
        super(renderer);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(ScaledEntity entity) {
        return null;
    }
    
    //doRender
    @Override
    public void func_76986_a(ScaledEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.scalef(0.2f, 0.2f, 0.2f);
        Minecraft.getInstance().getRenderManager().func_188388_a(TCEUtil.createEntity(entity.getEntityTag(), entity.world), 1, true);
        GlStateManager.popMatrix();
    }

}
