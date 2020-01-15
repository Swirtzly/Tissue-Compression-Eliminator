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

    public RenderScaledEntity(EntityRendererManager p_i46179_1_) {
        super(p_i46179_1_);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(ScaledEntity p_110775_1_) {
        return null;
    }

    @Override
    public void func_76986_a(ScaledEntity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        GlStateManager.pushMatrix();
        GlStateManager.scalef(0.2f, 0.2f, 0.2f);
        Minecraft.getInstance().getRenderManager().func_188388_a(TCEUtil.createEntity(p_76986_1_.getEntityTag(), p_76986_1_.world), 1, true);
        GlStateManager.popMatrix();
    }

}
